package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.TypeDispensation;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart4;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.jasper.constants.JasperConstants;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;

/**
 * Test de la classe AideDispensationPart4TypeDispFiller.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AideDispensationPart4TypeDispFillerTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private AideDispensationPart4TypeDispFiller filler;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.filler = new AideDispensationPart4TypeDispFiller();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.filler = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.filler);
    }

    /**
     * Test de la méthode fill(fr.pharma.eclipse.domain.model.essai.Essai,
     * fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean) - pas de
     * donnée.
     */
    @Test
    public void testFillNoData() {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final JRBeanFicheAideDispensationPart4 partie4 = new JRBeanFicheAideDispensationPart4();

        this.filler.fill(essai, partie4);

        Assert.assertEquals(JasperConstants.DEFAULT_FIELD_VALUE, partie4.getTypeDispensation());
        Assert.assertFalse(partie4.getHasTracabilite());
        Assert.assertNull(partie4.getTracabiliteObligatoire());
    }

    /**
     * Test de la méthode fill(fr.pharma.eclipse.domain.model.essai.Essai,
     * fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean) - type de
     * dispensation autre que Globale.
     */
    @Test
    public void testFillDisp() {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final TypeDispensation typeDispensation = TypeDispensation.NOMINATIVE;
        essai.getDetailDonneesPharma().getInfosDispensations().setTypeDispensation(typeDispensation);
        essai.getDetailDonneesPharma().getInfosDispensations().setTracabilitePatient(true);
        final JRBeanFicheAideDispensationPart4 partie4 = new JRBeanFicheAideDispensationPart4();

        this.filler.fill(essai, partie4);

        Assert.assertEquals(typeDispensation.getLibelle(), partie4.getTypeDispensation());
        Assert.assertFalse(partie4.getHasTracabilite());
        Assert.assertNull(partie4.getTracabiliteObligatoire());
    }

    /**
     * Test de la méthode fill(fr.pharma.eclipse.domain.model.essai.Essai,
     * fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean) - type de
     * dispensation Globale.
     */
    @Test
    public void testFillDispGlobale() {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final TypeDispensation typeDispensation = TypeDispensation.GLOBALE;
        final Boolean tracabilitePatient = false;
        essai.getDetailDonneesPharma().getInfosDispensations().setTypeDispensation(typeDispensation);
        essai.getDetailDonneesPharma().getInfosDispensations().setTracabilitePatient(tracabilitePatient);
        final JRBeanFicheAideDispensationPart4 partie4 = new JRBeanFicheAideDispensationPart4();

        this.filler.fill(essai, partie4);

        Assert.assertEquals(typeDispensation.getLibelle(), partie4.getTypeDispensation());
        Assert.assertTrue(partie4.getHasTracabilite());
        Assert.assertEquals(tracabilitePatient, partie4.getTracabiliteObligatoire());
    }
}
