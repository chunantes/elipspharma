package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.Responsabilite;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart7;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.document.DocumentRespInsu;
import fr.pharma.eclipse.jasper.constants.JasperConstants;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;

/**
 * Test de la classe {@link AideDispensationPart7GlobalFiller}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AideDispensationPart7GlobalFillerTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private AideDispensationPart7GlobalFiller filler;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.filler = new AideDispensationPart7GlobalFiller();
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
        final JRBeanFicheAideDispensationPart7 part7 = new JRBeanFicheAideDispensationPart7();
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final Responsabilite responsabiliteInsu = null;
        final DocumentRespInsu documentResponsabiliteInsu = null;
        essai.getDetailDonneesPharma().getInfosComplementaires().setResponsabiliteInsu(responsabiliteInsu);
        essai.getDetailDonneesPharma().getInfosComplementaires().setDocumentResponsabiliteInsu(documentResponsabiliteInsu);

        this.filler.fill(essai, part7);
        Assert.assertEquals(JasperConstants.DEFAULT_FIELD_VALUE, part7.getRespLeveeInsu());
        Assert.assertFalse(part7.getShowMoAssocie());
        Assert.assertEquals(JasperConstants.DEFAULT_FIELD_VALUE, part7.getMoAssocie());
    }

    /**
     * Test de la méthode fill(fr.pharma.eclipse.domain.model.essai.Essai,
     * fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean).
     */
    @Test
    public void testFillNoPharma() {
        long id = 1;
        final JRBeanFicheAideDispensationPart7 part7 = new JRBeanFicheAideDispensationPart7();
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final Responsabilite responsabiliteInsu = Responsabilite.SERVICE;
        final DocumentRespInsu documentResponsabiliteInsu = new DocumentRespInsu();
        documentResponsabiliteInsu.setId(id++);
        documentResponsabiliteInsu.setNomUtilisateur("monFichier.xml");
        essai.getDetailDonneesPharma().getInfosComplementaires().setResponsabiliteInsu(responsabiliteInsu);
        essai.getDetailDonneesPharma().getInfosComplementaires().setDocumentResponsabiliteInsu(documentResponsabiliteInsu);

        this.filler.fill(essai, part7);
        Assert.assertEquals(responsabiliteInsu.getLibelle(), part7.getRespLeveeInsu());
        Assert.assertFalse(part7.getShowMoAssocie());
        Assert.assertEquals(JasperConstants.DEFAULT_FIELD_VALUE, part7.getMoAssocie());
    }

    /**
     * Test de la méthode fill(fr.pharma.eclipse.domain.model.essai.Essai,
     * fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean).
     */
    @Test
    public void testFillPharma() {
        long id = 1;
        final JRBeanFicheAideDispensationPart7 part7 = new JRBeanFicheAideDispensationPart7();
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final Responsabilite responsabiliteInsu = Responsabilite.PHARMACIE;
        final DocumentRespInsu documentResponsabiliteInsu = new DocumentRespInsu();
        documentResponsabiliteInsu.setId(id++);
        documentResponsabiliteInsu.setNomUtilisateur("monFichier.xml");
        essai.getDetailDonneesPharma().getInfosComplementaires().setResponsabiliteInsu(responsabiliteInsu);
        essai.getDetailDonneesPharma().getInfosComplementaires().setDocumentResponsabiliteInsu(documentResponsabiliteInsu);

        this.filler.fill(essai, part7);
        Assert.assertEquals(responsabiliteInsu.getLibelle(), part7.getRespLeveeInsu());
        Assert.assertTrue(part7.getShowMoAssocie());
        Assert.assertEquals(documentResponsabiliteInsu.getNomUtilisateur(), part7.getMoAssocie());
    }
}
