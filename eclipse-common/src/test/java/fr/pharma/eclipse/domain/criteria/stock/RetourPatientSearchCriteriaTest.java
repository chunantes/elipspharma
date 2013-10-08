package fr.pharma.eclipse.domain.criteria.stock;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Classe en charge de tester les méthodes de RetourPatientSearchCriteria.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class RetourPatientSearchCriteriaTest {
    /**
     * Méthode en charge de tester le clear des properties du critère de
     * recherche.
     */
    @Test
    public void testClear() {
        final RetourPatientSearchCriteria criteria = new RetourPatientSearchCriteria();
        final Essai essai = new Essai();
        essai.setId(1L);
        criteria.setEssai(essai);
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(1L);
        final Produit produit = new Medicament();
        produit.setId(1L);
        criteria.setProduit(produit);
        final Patient patient = new Patient();
        patient.setId(1L);
        criteria.setPatient(patient);
        criteria.clear();
        Assert.assertNull(criteria.getEssai());
        Assert.assertNull(criteria.getProduit());
        Assert.assertNull(criteria.getPatient());
    }

}
