package fr.pharma.eclipse.domain.criteria.dispensation;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Patient;

/**
 * Classe en charge de tester les méthodes de DispensationSearchCriteria.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DispensationSearchCriteriaTest {

    /**
     * Méthode en charge de tester le clear des properties du critère de
     * recherche.
     */
    @Test
    public void testClear() {
        final DispensationSearchCriteria criteria = new DispensationSearchCriteria();
        criteria.setPatient(new Patient());
        criteria.setDateDebut(Calendar.getInstance());
        criteria.setDateFin(Calendar.getInstance());
        criteria.setDispense(true);
        criteria.setEssai(new Essai());
        criteria.clear();
        Assert.assertNull(criteria.getPatient());
        Assert.assertNull(criteria.getEssai());
        Assert.assertNull(criteria.getDateDebut());
        Assert.assertNull(criteria.getDateFin());
        Assert.assertNull(criteria.getDispense());
    }

}
