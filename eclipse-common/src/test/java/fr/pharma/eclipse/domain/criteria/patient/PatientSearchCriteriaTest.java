package fr.pharma.eclipse.domain.criteria.patient;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.essai.Essai;

/**
 * Classe en charge de tester les méthodes de PatientSearchCriteria.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PatientSearchCriteriaTest {

    /**
     * Méthode en charge de tester le clear des properties du critère de
     * recherche.
     */
    @Test
    public void testClear() {
        final PatientSearchCriteria criteria = new PatientSearchCriteria();
        criteria.setNom("nom");
        criteria.setNumeroIpp("num");
        criteria.setPrenom("prenom");
        criteria.setNumeroIppOrNomOrPrenom("num");
        criteria.setEssai(new Essai());
        criteria.clear();
        Assert.assertNull(criteria.getNom());
        Assert.assertNull(criteria.getPrenom());
        Assert.assertNull(criteria.getNumeroIpp());
        Assert.assertNull(criteria.getEssai());
        Assert.assertNull(criteria.getNumeroIppOrNomOrPrenom());
    }

}
