package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.Responsabilite;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart3;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.document.DocumentRespRandomisation;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.embedded.InfosComplementaires;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;

/**
 * Test de la classe {@link AideDispensationPart3RandomisationFiller}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AideDispensationPart3RandomisationFillerTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private AideDispensationPart3RandomisationFiller filler;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.filler = new AideDispensationPart3RandomisationFiller();
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
     * Test de la classe fill(fr.pharma.eclipse.domain.model.essai.Essai,
     * fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean).
     */
    @Test
    public void testFill1() {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final JRBeanFicheAideDispensationPart3 part3 = new JRBeanFicheAideDispensationPart3();
        final Responsabilite expectedResp = Responsabilite.PHARMACIE;
        final String expectedNomUtilisateur = "monDoc.txt";
        final DocumentRespRandomisation expectedDoc = new DocumentRespRandomisation();
        expectedDoc.setId(id++);
        expectedDoc.setNomUtilisateur(expectedNomUtilisateur);

        final InfosComplementaires infosComplementaires = essai.getDetailDonneesPharma().getInfosComplementaires();
        infosComplementaires.setResponsabiliteRandomisation(expectedResp);
        infosComplementaires.setDocumentResponsabiliteRandomisation(expectedDoc);

        this.filler.fill(essai, part3);

        Assert.assertEquals(expectedResp.getLibelle(), part3.getRespRandomisation());
        Assert.assertEquals(expectedNomUtilisateur, part3.getMoAssocie());
        Assert.assertTrue(part3.getHasMoAssocie());

    }

    /**
     * Test de la classe fill(fr.pharma.eclipse.domain.model.essai.Essai,
     * fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean).
     */
    @Test
    public void testFill2() {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final JRBeanFicheAideDispensationPart3 part3 = new JRBeanFicheAideDispensationPart3();
        final Responsabilite expectedResp = Responsabilite.INVESTIGATEUR;
        final String expectedNomUtilisateur = "monDoc.txt";
        final DocumentRespRandomisation expectedDoc = new DocumentRespRandomisation();
        expectedDoc.setId(id++);
        expectedDoc.setNomUtilisateur(expectedNomUtilisateur);

        final InfosComplementaires infosComplementaires = essai.getDetailDonneesPharma().getInfosComplementaires();
        infosComplementaires.setResponsabiliteRandomisation(expectedResp);
        infosComplementaires.setDocumentResponsabiliteRandomisation(expectedDoc);

        this.filler.fill(essai, part3);

        Assert.assertEquals(expectedResp.getLibelle(), part3.getRespRandomisation());
        Assert.assertEquals(expectedNomUtilisateur, part3.getMoAssocie());
        Assert.assertFalse(part3.getHasMoAssocie());

    }
}
