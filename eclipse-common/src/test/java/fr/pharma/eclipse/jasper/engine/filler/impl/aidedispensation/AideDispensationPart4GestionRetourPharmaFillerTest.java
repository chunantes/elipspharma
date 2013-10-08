package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.Responsabilite;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart4;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;

/**
 * Test de la classe {@link AideDispensationPart4GestionRetourPharmaFiller}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AideDispensationPart4GestionRetourPharmaFillerTest extends AbstractEclipseJUnitTest {
    /**
     * Filler testé.
     */
    private AideDispensationPart4GestionRetourPharmaFiller filler;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.filler = new AideDispensationPart4GestionRetourPharmaFiller();
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
    public void testFillNull() {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final JRBeanFicheAideDispensationPart4 part4 = new JRBeanFicheAideDispensationPart4();
        final Responsabilite gestionRetour = null;
        essai.getDetailDonneesPharma().getInfosComplementaires().setGestionRetour(gestionRetour);

        Assert.assertFalse(part4.getHasGestionRetoursPharma());
        this.filler.fill(essai, part4);
        Assert.assertFalse(part4.getHasGestionRetoursPharma());
    }

    /**
     * Test de la méthode fill(fr.pharma.eclipse.domain.model.essai.Essai,
     * fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean).
     */
    @Test
    public void testFillPharma() {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final JRBeanFicheAideDispensationPart4 part4 = new JRBeanFicheAideDispensationPart4();
        final Responsabilite gestionRetour = Responsabilite.PHARMACIE;
        essai.getDetailDonneesPharma().getInfosComplementaires().setGestionRetour(gestionRetour);

        Assert.assertFalse(part4.getHasGestionRetoursPharma());
        this.filler.fill(essai, part4);
        Assert.assertTrue(part4.getHasGestionRetoursPharma());
    }

    /**
     * Test de la méthode fill(fr.pharma.eclipse.domain.model.essai.Essai,
     * fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean).
     */
    @Test
    public void testFillNotPharma() {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final JRBeanFicheAideDispensationPart4 part4 = new JRBeanFicheAideDispensationPart4();
        final Responsabilite gestionRetour = Responsabilite.INVESTIGATEUR;
        essai.getDetailDonneesPharma().getInfosComplementaires().setGestionRetour(gestionRetour);

        Assert.assertFalse(part4.getHasGestionRetoursPharma());
        this.filler.fill(essai, part4);
        Assert.assertFalse(part4.getHasGestionRetoursPharma());
    }
}
