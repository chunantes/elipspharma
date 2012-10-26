package fr.pharma.eclipse.component.evenement;

import javax.annotation.Resource;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.event.SelectEvent;

import fr.pharma.eclipse.component.BeanManager;
import fr.pharma.eclipse.domain.enums.evenement.TypeEvenement;
import fr.pharma.eclipse.domain.enums.evenement.TypeVisite;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.service.evenement.EvenementService;
import fr.pharma.eclipse.service.user.UserService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Manager de gestion d'un événement.
 
 * @version $Revision$ $Date$
 */
public class EvenementManager
    extends BeanManager<Evenement>
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -8467396778907104660L;

    /**
     * Service utilisateur.
     */
    @Resource(name = "userService")
    private UserService userService;

    /**
     * Constructeur.
     * @param evenementService Service de gestion des événements.
     */
    public EvenementManager(final EvenementService evenementService)
    {
        super(evenementService);
    }

    public void handleSelectEssai(final SelectEvent event)
    {
        this.getBean().setEssai((Essai) event.getObject());
        this.buildLibelle();
    }

    /**
     * Méthode appelée via la couche IHM lorsqu'un type d'événement est sélectionné.
     * @param event Evénement remonté via la couche IHM.
     */
    public void handleSelectTypeEvenement(final ValueChangeEvent event)
    {
        final TypeEvenement typeEvenement = (TypeEvenement) event.getNewValue();
        // Récupération du type d'événement sélectionné
        this.getBean().setTypeEvenement(typeEvenement);

        // Si le type d'événement est différent de visite
        if (!TypeEvenement.VISITE.equals(typeEvenement))
        {
            this.getBean().setTypeVisite(null);
            this.getBean().setResultatVisite(null);
        }
        this.getBean().setValidation(null);
        this.getBean().setRealisePar(null);
        if (TypeEvenement.REETIQUETAGE.equals(typeEvenement))
        {
            final Personne p = this.userService.getPersonne();
            this.getBean().setValidation(p.getPrenom()
                                         + " "
                                         + p.getNom());
        }

        if (this.getBean().getTypeEvenement() != null)
        {
            this.buildLibelle();
        }
    }
    /**
     * Méthode appelée via la couche IHM lorsqu'un type de visite est sélectionné.
     * @param event Evénement remonté via la couche IHM.
     */
    public void handleSelectTypeVisite(final ValueChangeEvent event)
    {
        // Récupération du type d'événement sélectionné
        final TypeVisite typeVisite = (TypeVisite) event.getNewValue();
        this.getBean().setTypeVisite(typeVisite);
        this.buildLibelle();
    }

    /**
     * Méthode en charge de construire le libellé de l'évènement.
     */
    private void buildLibelle()
    {
        final Evenement evenement = this.getBean();
        final StringBuffer sb = new StringBuffer();
        if (evenement != null
            && evenement.getTypeEvenement() != null)
        {
            if (evenement.getTypeEvenement().equals(TypeEvenement.TACHE)
                || evenement.getTypeEvenement().equals(TypeEvenement.VISITE))
            {
                if (evenement.getTypeEvenement().equals(TypeEvenement.VISITE)
                    && evenement.getTypeVisite() != null)
                {
                    sb.append(evenement.getTypeVisite().getLibelle())
                            .append(EclipseConstants.DASH);
                }
                else
                {
                    sb.append(evenement.getTypeEvenement().getLibelle())
                            .append(EclipseConstants.DASH);
                }

                if (evenement.getEssai() != null)
                {
                    sb.append(evenement.getEssai().getPromoteur().getRaisonSociale())
                            .append(EclipseConstants.DASH)
                            .append(evenement.getEssai().getCodePromoteur());
                }
            }
            else
            {
                sb.append(evenement.getTypeEvenement().getLibelle());
            }
        }
        evenement.setLibelle(sb.toString());
    }
    /**
     * Méthode en charge de traiter le changement de valeur du booleen journee.
     * @param event Evenement JSF.
     */
    public void handleCheckJournee(final ValueChangeEvent event)
    {
        this.getBean().setJournee((Boolean) event.getNewValue());
    }

    /**
     * Setter pour userService.
     * @param userService Le userService à écrire.
     */
    public void setUserService(final UserService userService)
    {
        this.userService = userService;
    }

}
