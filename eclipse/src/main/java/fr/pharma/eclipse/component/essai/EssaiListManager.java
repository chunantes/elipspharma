package fr.pharma.eclipse.component.essai;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.component.BeanListManager;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.predicate.essai.HabilitationDroitPredicate;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe de gestion des managers d'une liste d'essais.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EssaiListManager extends BeanListManager<Essai> {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -2646269388797085663L;

    /**
     * Service de gestion des essais.
     */
    @Resource(name = "essaiService")
    private EssaiService essaiService;

    /**
     * Constructeur.
     * @param searchCriteria Critère de recherche.
     */
    public EssaiListManager(final SearchCriteria searchCriteria) {
        super(searchCriteria);
    }

    /**
     * Méthode en charge de retourner l'investigateur principal d'un essai.
     * @param essaiIn Essai.
     * @return "nom prenom" de l'investigateur principal s'il existe ;
     * StringUtils.EMPTY sinon.
     */
    public String getInvestigateurPrincipal(final Essai essaiIn) {
        // Chercher l'objet dans la session hibernate
        // TODO : chercher directement l'investigateur au lieu de charger essai
        final Essai essai = this.essaiService.get(essaiIn.getId());

        // Récupération dans la liste des habilitations de l'essai
        final Predicate predicat = new HabilitationDroitPredicate(Droit.INVESTIGATEUR_PRINCIPAL);

        final Habilitation hab = (Habilitation) CollectionUtils.find(essai.getDetailContacts().getHabilitations(), predicat);
        if (hab != null) {
            final Investigateur investigateur = (Investigateur) hab.getPersonne();
            return investigateur.getNom() + EclipseConstants.SPACE + investigateur.getPrenom();
        } else {
            return StringUtils.EMPTY;
        }
    }

    /**
     * Setter pour essaiService.
     * @param essaiService Le essaiService à écrire.
     */
    public void setEssaiService(final EssaiService essaiService) {
        this.essaiService = essaiService;
    }

}
