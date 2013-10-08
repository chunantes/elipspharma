package fr.pharma.eclipse.domain.criteria.habilitation;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.model.acteur.ArcInvestigateur;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.essai.Essai;

/**
 * Classe en charge de tester les méthodes du critère de recherche de
 * Habilitation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class HabilitationSearchCriteriaTest {

    /**
     * Méthode en charge de tester le clear des properties du critère de
     * recherche.
     */
    @Test
    public void testClear() {
        final HabilitationSearchCriteria criteria = new HabilitationSearchCriteria();
        final Essai essai = new Essai();
        criteria.setEssai(essai);
        final Personne personne = new ArcInvestigateur();
        criteria.setPersonne(personne);
        criteria.setDroit(Droit.ARC_INVESTIGATEUR);
        criteria.setActive(true);
        criteria.clear();
        Assert.assertNull(criteria.getEssai());
        Assert.assertNull(criteria.getPersonne());
        Assert.assertNull(criteria.getDroit());
        Assert.assertNull(criteria.getActive());
    }
}
