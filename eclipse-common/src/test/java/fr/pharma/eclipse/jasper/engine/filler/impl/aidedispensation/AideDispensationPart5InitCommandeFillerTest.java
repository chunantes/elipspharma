package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.Responsabilite;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart5;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.jasper.constants.JasperConstants;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;

/**
 * Test de la classe {@link AideDispensationPart5InitCommandeFiller}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AideDispensationPart5InitCommandeFillerTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private AideDispensationPart5InitCommandeFiller filler;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.filler = new AideDispensationPart5InitCommandeFiller();
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
        final JRBeanFicheAideDispensationPart5 partie5 = new JRBeanFicheAideDispensationPart5();

        this.filler.fill(essai, partie5);

        Assert.assertEquals(JasperConstants.DEFAULT_FIELD_VALUE, partie5.getRespCommande());
    }

    /**
     * Test de la méthode fill(fr.pharma.eclipse.domain.model.essai.Essai,
     * fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean) -
     * responsabilité indiquée.
     */
    @Test
    public void testFillNominal() {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final Responsabilite responsabiliteCommande = Responsabilite.SERVICE;
        essai.getDetailDonneesPharma().getInfosComplementaires().setResponsabiliteCommande(responsabiliteCommande);
        final JRBeanFicheAideDispensationPart5 partie5 = new JRBeanFicheAideDispensationPart5();

        this.filler.fill(essai, partie5);

        Assert.assertEquals(responsabiliteCommande.getLibelle(), partie5.getRespCommande());
    }
}
