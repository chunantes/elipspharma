package fr.pharma.eclipse.domain.criteria.patient;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du criteria InclusionSearchCriteria.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class InclusionSearchCriteriaTest extends AbstractEclipseJUnitTest {
    /**
     * Criteria à tester.
     */
    private InclusionSearchCriteria criteria;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.criteria = new InclusionSearchCriteria();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.criteria = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.criteria);
    }

    /**
     * Test de la méthode clear().
     */
    @Test
    public void testClear() {
        this.criteria.setActif(true);
        this.criteria.setDateDebut(Calendar.getInstance());
        this.criteria.setDateFin(Calendar.getInstance());
        this.criteria.setEssai(new Essai());
        this.criteria.clear();
        Assert.assertNull(this.criteria.getActif());
        Assert.assertNull(this.criteria.getDateDebut());
        Assert.assertNull(this.criteria.getDateFin());
        Assert.assertNull(this.criteria.getEssai());
    }

}
