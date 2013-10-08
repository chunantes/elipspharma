package fr.pharma.eclipse.domain.criteria.ordonnancier;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de tester les méthodes de OrdonnancierSearchCriteria.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class OrdonnancierSearchCriteriaTest {
    /**
     * Méthode en charge de tester le clear des properties du critère de
     * recherche.
     */
    @Test
    public void testClear() {
        final OrdonnancierSearchCriteria criteria = new OrdonnancierSearchCriteria();
        final Pharmacie pharmacie = new Pharmacie();
        criteria.setPharmacie(pharmacie);
        criteria.setDateDebut(Calendar.getInstance(EclipseConstants.LOCALE));
        criteria.setDateFin(Calendar.getInstance(EclipseConstants.LOCALE));
        criteria.clear();
        Assert.assertNull(criteria.getPharmacie());
        Assert.assertNull(criteria.getDateDebut());
        Assert.assertNull(criteria.getDateFin());
    }

}
