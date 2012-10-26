package fr.pharma.eclipse.component.acteur;

import javax.annotation.Resource;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.component.BeansManager;
import fr.pharma.eclipse.domain.criteria.acteur.PersonneSearchCriteria;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.ArcInvestigateur;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.acteur.PersonneService;

/**
 * Manager sur les beans de gestion de Personne.
 
 * @version $Revision$ $Date$
 */
public class PersonnesManager
    extends BeansManager<Personne>
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 7759002977790495169L;

    /**
     * Service de gestion des arcInvestigateurs.
     */
    @Resource(name = "arcInvestigateurService")
    private PersonneService<ArcInvestigateur> arcInvestService;

    /**
     * Service de gestion des investigateurs.
     */
    @Resource(name = "investigateurService")
    private PersonneService<Investigateur> investService;

    /**
     * Type de personne sélectionné pour un ajout de personne.
     */
    private TypePersonne profilAjout;

    /**
     * Constructeur.
     * @param searchCriteria Critère de recherche.
     */
    public PersonnesManager(final SearchCriteria searchCriteria)
    {
        super(searchCriteria);
    }

    /**
     * Méthode en charge de savoir si les informations obligatoires de la personne sont toutes
     * remplies.
     * @param personne Informations de la personne.
     * @return résultat du test.
     */
    public boolean isComplete(final Personne personne)
    {
        // Le contrôle est fait pour les investigateurs et les arc investigateurs
        final TypePersonne typePersonne = personne.getType();

        if (TypePersonne.ARC_INVESTIGATEUR.equals(typePersonne))
        {
            final ArcInvestigateur arcInvestigateur =
                this.arcInvestService.reattach((ArcInvestigateur) personne);
            return StringUtils.isNotEmpty(arcInvestigateur.getNom())
                   && !arcInvestigateur.getServices().isEmpty();
        }
        else if (TypePersonne.INVESTIGATEUR.equals(typePersonne))
        {
            final Investigateur investigateur =
                this.investService.reattach((Investigateur) personne);
            return StringUtils.isNotEmpty(investigateur.getNom())
                   && !investigateur.getServices().isEmpty();
        }

        return true;
    }

    /**
     * Méthode appelée via la couche IHM lorsqu'une type de personne est sélectionné.
     * @param event Evénement remonté via la couche IHM.
     */
    public void handleSelectTypePersonne(final AjaxBehaviorEvent event)
    {
        // Récupération du type de personne sélectionné.
        final HtmlSelectOneMenu select = (HtmlSelectOneMenu) event.getSource();
        final TypePersonne typePersonne = (TypePersonne) select.getLocalValue();
        ((PersonneSearchCriteria) this.getSearchCriteria()).setTypePersonne(typePersonne);
    }

    /**
     * Méthode en charge de valider les données du critère de recherche.
     */
    public void validCriteria()
    {
        final PersonneSearchCriteria criteria = (PersonneSearchCriteria) this.getSearchCriteria();

        if (criteria.getEssai() != null)
        {
            if (criteria.getDateDebut() == null
                || criteria.getDateFin() == null)
            {
                throw new ValidationException("gestionPersonne.date",
                                              new String[]
                                              {"notEmpty", });
            }
            else
            {
                if (criteria.getDateFin().before(criteria.getDateDebut()))
                {
                    throw new ValidationException("gestionPersonne.date",
                                                  new String[]
                                                  {"order", });
                }
            }
        }
    }

    /**
     * Getter sur profilAjout.
     * @return Retourne le profilAjout.
     */
    public TypePersonne getProfilAjout()
    {
        return this.profilAjout;
    }

    /**
     * Setter pour profilAjout.
     * @param profilAjout le profilAjout à écrire.
     */
    public void setProfilAjout(final TypePersonne profilAjout)
    {
        this.profilAjout = profilAjout;
    }

    /**
     * Setter pour arcInvestService.
     * @param arcInvestService Le arcInvestService à écrire.
     */
    public void setArcInvestService(final PersonneService<ArcInvestigateur> arcInvestService)
    {
        this.arcInvestService = arcInvestService;
    }

    /**
     * Setter pour investService.
     * @param investService Le investService à écrire.
     */
    public void setInvestService(final PersonneService<Investigateur> investService)
    {
        this.investService = investService;
    }

}
