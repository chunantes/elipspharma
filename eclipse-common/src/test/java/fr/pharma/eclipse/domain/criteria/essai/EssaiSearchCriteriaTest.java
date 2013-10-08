package fr.pharma.eclipse.domain.criteria.essai;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.acteur.Promoteur;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.domain.model.localisation.Site;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Classe en charge de tester les méthodes du critère de recherche de Essai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EssaiSearchCriteriaTest {

    /**
     * Méthode en charge de tester le clear des properties du critère de
     * recherche.
     */
    @Test
    public void testClear() {
        final EssaiSearchCriteria criteria = new EssaiSearchCriteria();
        criteria.setDci("dci");
        criteria.setEtat(EtatEssai.EN_ATTENTE_CLOTURE);
        criteria.setNom("nom");
        criteria.setNumInterne("numInterne");
        final Pharmacie pharmacie = new Pharmacie();
        criteria.setPharmacie(pharmacie);
        final Promoteur promoteur = new Promoteur();
        criteria.setPromoteur(promoteur);
        final Site site = new Site();
        criteria.setSite(site);
        final Investigateur investigateur = new Investigateur();
        criteria.setInvestigateur(investigateur);
        final Service service = new Service();
        criteria.setService(service);
        criteria.setEssaisDispensationGlobale(Boolean.TRUE);
        criteria.setAnneeCreation(Integer.valueOf("2010"));
        criteria.setNumInterneOrNomOrPromoteur("numInterneOrNomOrPromoteur");
        criteria.setMotsCles("motsCles");
        criteria.clear();
        Assert.assertNull(criteria.getDci());
        Assert.assertNull(criteria.getEtat());
        Assert.assertNull(criteria.getNom());
        Assert.assertNull(criteria.getNumInterne());
        Assert.assertNull(criteria.getPharmacie());
        Assert.assertNull(criteria.getPromoteur());
        Assert.assertNull(criteria.getSite());
        Assert.assertNull(criteria.getService());
        Assert.assertNull(criteria.getInvestigateur());
        Assert.assertNull(criteria.getEssaisDispensationGlobale());
        Assert.assertNull(criteria.getAnneeCreation());
        Assert.assertNull(criteria.getNumInterneOrNomOrPromoteur());
        Assert.assertNull(criteria.getMotsCles());
    }

}
