package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart4;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.jasper.constants.JasperConstants;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;

/**
 * Test de la classe {@link AideDispensationPart4DestDispFiller}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AideDispensationPart4DestDispFillerTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private AideDispensationPart4DestDispFiller filler;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.filler = new AideDispensationPart4DestDispFiller();
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
     * fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean).
     */
    @Test
    public void testFillNoData() {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final JRBeanFicheAideDispensationPart4 partie4 = new JRBeanFicheAideDispensationPart4();
        this.filler.fill(essai, partie4);
        Assert.assertEquals(JasperConstants.DEFAULT_FIELD_VALUE, partie4.getDestinatairesDispensation());
    }

    /**
     * Test de la méthode fill(fr.pharma.eclipse.domain.model.essai.Essai,
     * fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean).
     */
    @Test
    public void testFillOneData() {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        essai.getDetailDonneesPharma().getInfosDispensations().setDestinatairePatient(true);
        final JRBeanFicheAideDispensationPart4 partie4 = new JRBeanFicheAideDispensationPart4();
        this.filler.fill(essai, partie4);
        Assert.assertEquals("Patient ou représentant du patient", partie4.getDestinatairesDispensation());
    }

    /**
     * Test de la méthode fill(fr.pharma.eclipse.domain.model.essai.Essai,
     * fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean).
     */
    @Test
    public void testFillAllDatas() {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        essai.getDetailDonneesPharma().getInfosDispensations().setDestinataireService(true);
        essai.getDetailDonneesPharma().getInfosDispensations().setDestinatairePatient(true);
        essai.getDetailDonneesPharma().getInfosDispensations().setDestinataireInvestigateur(true);
        final JRBeanFicheAideDispensationPart4 partie4 = new JRBeanFicheAideDispensationPart4();
        this.filler.fill(essai, partie4);
        Assert.assertEquals("Service; Patient ou représentant du patient; Investigateur", partie4.getDestinatairesDispensation());
    }
}
