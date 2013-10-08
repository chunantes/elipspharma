package fr.pharma.eclipse.domain.criteria.prescription;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.domain.model.patient.Patient;

/**
 * Classe en charge de tester les méthodes de PrescriptionSearchCriteria.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PrescriptionSearchCriteriaTest {

    /**
     * Méthode en charge de tester le clear des properties du critère de
     * recherche.
     */
    @Test
    public void testClear() {
        final PrescriptionSearchCriteria criteria = new PrescriptionSearchCriteria();
        criteria.setInclusion(new Inclusion());
        criteria.setDispense(true);
        criteria.setEssai(new Essai());
        criteria.setDateDebut(Calendar.getInstance());
        criteria.setDateFin(Calendar.getInstance());
        criteria.setPatient(new Patient());
        criteria.clear();
        Assert.assertNull(criteria.getInclusion());
        Assert.assertNull(criteria.getDispense());
        Assert.assertNull(criteria.getEssai());
        Assert.assertNull(criteria.getPatient());
        Assert.assertNull(criteria.getDateDebut());
        Assert.assertNull(criteria.getDateFin());
    }

}
