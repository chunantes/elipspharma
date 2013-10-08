package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation;

import org.apache.commons.collections.Predicate;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart4;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;

/**
 * Test de la classe {@link AideDispensationPart4NumerodationCondFiller}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AideDispensationPart4NumerodationCondFillerTest extends AbstractEclipseJUnitTest {
    /**
     * Filler testé.
     */
    private AideDispensationPart4NumerodationCondFiller filler;

    /**
     * Prédicat mocké.
     */
    private Predicate mockedPredicate;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedPredicate = Mockito.mock(Predicate.class);
        this.filler = new AideDispensationPart4NumerodationCondFiller(this.mockedPredicate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.filler = null;
        this.mockedPredicate = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.filler);
        Assert.assertEquals(this.mockedPredicate, this.filler.getEssaiPredicate());
    }

    /**
     * Test de la méthode fill(fr.pharma.eclipse.domain.model.essai.Essai,
     * fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean) - cas
     * nominal TRUE.
     */
    @Test
    public void testFillNominalTrue() {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final JRBeanFicheAideDispensationPart4 partie4 = new JRBeanFicheAideDispensationPart4();
        Mockito.when(this.mockedPredicate.evaluate(essai)).thenReturn(true);

        Assert.assertNull(partie4.getHasNumeroTraitement());
        this.filler.fill(essai, partie4);
        Mockito.verify(this.mockedPredicate).evaluate(essai);
        Assert.assertTrue(partie4.getHasNumeroTraitement());

    }

    /**
     * Test de la méthode fill(fr.pharma.eclipse.domain.model.essai.Essai,
     * fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean) - cas
     * nominal FALSE.
     */
    @Test
    public void testFillNominalFalse() {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final JRBeanFicheAideDispensationPart4 partie4 = new JRBeanFicheAideDispensationPart4();
        Mockito.when(this.mockedPredicate.evaluate(essai)).thenReturn(false);

        Assert.assertNull(partie4.getHasNumeroTraitement());
        this.filler.fill(essai, partie4);
        Mockito.verify(this.mockedPredicate).evaluate(essai);
        Assert.assertFalse(partie4.getHasNumeroTraitement());

    }
}
