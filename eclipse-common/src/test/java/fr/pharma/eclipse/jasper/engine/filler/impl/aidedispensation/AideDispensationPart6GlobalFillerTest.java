package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.Responsabilite;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart6;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.jasper.constants.JasperConstants;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;

/**
 * Test de la classe {@link AideDispensationPart6GlobalFiller}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AideDispensationPart6GlobalFillerTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private AideDispensationPart6GlobalFiller filler;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.filler = new AideDispensationPart6GlobalFiller();
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
        final JRBeanFicheAideDispensationPart6 part6 = new JRBeanFicheAideDispensationPart6();
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final Responsabilite gestionRetour = null;
        final TypeMvtStock typeRetour = null;
        essai.getDetailDonneesPharma().getInfosComplementaires().setGestionRetour(gestionRetour);
        essai.getDetailDonneesPharma().getInfosComplementaires().setTypeRetour(typeRetour);

        this.filler.fill(essai, part6);
        Assert.assertEquals(JasperConstants.DEFAULT_FIELD_VALUE, part6.getRespRetour());
        Assert.assertFalse(part6.getShowTypeRetour());
        Assert.assertEquals(JasperConstants.DEFAULT_FIELD_VALUE, part6.getTypeRetour());
    }

    /**
     * Test de la méthode fill(fr.pharma.eclipse.domain.model.essai.Essai,
     * fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean).
     */
    @Test
    public void testFillNoPharma() {
        long id = 1;
        final JRBeanFicheAideDispensationPart6 part6 = new JRBeanFicheAideDispensationPart6();
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final Responsabilite gestionRetour = Responsabilite.SERVICE;
        final TypeMvtStock typeRetour = TypeMvtStock.DESTRUCTION;
        essai.getDetailDonneesPharma().getInfosComplementaires().setGestionRetour(gestionRetour);
        essai.getDetailDonneesPharma().getInfosComplementaires().setTypeRetour(typeRetour);

        this.filler.fill(essai, part6);
        Assert.assertEquals(gestionRetour.getLibelle(), part6.getRespRetour());
        Assert.assertFalse(part6.getShowTypeRetour());
        Assert.assertEquals(JasperConstants.DEFAULT_FIELD_VALUE, part6.getTypeRetour());
    }

    /**
     * Test de la méthode fill(fr.pharma.eclipse.domain.model.essai.Essai,
     * fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean).
     */
    @Test
    public void testFillPharma() {
        long id = 1;
        final JRBeanFicheAideDispensationPart6 part6 = new JRBeanFicheAideDispensationPart6();
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final Responsabilite gestionRetour = Responsabilite.PHARMACIE;
        final TypeMvtStock typeRetour = TypeMvtStock.DESTRUCTION;
        essai.getDetailDonneesPharma().getInfosComplementaires().setGestionRetour(gestionRetour);
        essai.getDetailDonneesPharma().getInfosComplementaires().setTypeRetour(typeRetour);

        this.filler.fill(essai, part6);
        Assert.assertEquals(gestionRetour.getLibelle(), part6.getRespRetour());
        Assert.assertTrue(part6.getShowTypeRetour());
        Assert.assertEquals(typeRetour.getLibelle(), part6.getTypeRetour());
    }
}
