package fr.pharma.eclipse.integration.factory.essai;

import java.util.Calendar;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.factory.common.BeanObjectFactory;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Test d'intégration de la fabrique d'objets Essai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class EssaiFactoryIntegTest {
    /**
     * Fabrique d'essais.
     */
    @Resource(name = "essaiFactory")
    private BeanObjectFactory<Essai> essaiFactory;

    /**
     * Test d'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.essaiFactory);
    }

    /**
     * Test de la création d'un bean Essai.
     */
    @Test
    public void testCreation() {
        final Essai essai = this.essaiFactory.getInitializedObject();
        final int cetteAnnee = Calendar.getInstance(EclipseConstants.LOCALE).get(Calendar.YEAR);
        essai.setId(1L); // pour les equals suivants

        // Vérifications bean Essai.
        Assert.assertNotNull(essai);
        Assert.assertEquals(EtatEssai.EN_EVALUATION, essai.getEtat());
        Assert.assertEquals(cetteAnnee, essai.getAnneeCreation().intValue());
        Assert.assertTrue(essai.getModifs().isEmpty());
        Assert.assertTrue(essai.getAlerteActive());

        // Vérifications bean DetailRecherche.
        this.verifyDetailRecherche(essai);

        // Vérifications bean DetailContacts.
        this.verifyDetailContacts(essai);

        // Vérification bean DetailFaisabilite.
        this.verifyDetailFaisabilite(essai);

        // Vérification bean DetailDates.
        this.verifyDetailDates(essai);

        // Vérification bean DetailAdministratif.
        this.verifyDetailAdmin(essai);

        // Vérification bean DetailProduit.
        this.verifyDetailProduits(essai);

        // Vérification bean DetailAutresDocuments
        this.verifyDetailAutresDoc(essai);

    }

    /**
     * Vérification bean DetailAutresDocuments.
     * @param essai Essai.
     */
    private void verifyDetailAutresDoc(final Essai essai) {
        Assert.assertNotNull(essai.getDetailAutresDocuments());
        Assert.assertEquals(essai, essai.getDetailAutresDocuments().getEssai());
        Assert.assertTrue(essai.getDetailAutresDocuments().getDocuments().isEmpty());
        Assert.assertTrue(essai.getDetailAutresDocuments().getModifs().isEmpty());
    }

    /**
     * Vérification bean DetailContacts.
     * @param essai Essai.
     */
    private void verifyDetailContacts(final Essai essai) {
        Assert.assertNotNull(essai.getDetailContacts());
        Assert.assertEquals(essai, essai.getDetailContacts().getEssai());
        Assert.assertTrue(essai.getDetailContacts().getHabilitations().isEmpty());
        Assert.assertTrue(essai.getDetailContacts().getModifs().isEmpty());
    }

    /**
     * Vérification bean DetailProduit.
     * @param essai Essai.
     */
    private void verifyDetailProduits(final Essai essai) {
        Assert.assertNotNull(essai.getDetailProduit());
        Assert.assertEquals(essai, essai.getDetailProduit().getEssai());
        Assert.assertTrue(essai.getDetailProduit().getModifs().isEmpty());
        Assert.assertTrue(essai.getDetailProduit().getMedicaments().isEmpty());
        Assert.assertTrue(essai.getDetailProduit().getMedicaments().isEmpty());
    }

    /**
     * Vérification bean DetailAdministratif.
     * @param essai Essai.
     */
    private void verifyDetailAdmin(final Essai essai) {
        Assert.assertNotNull(essai.getDetailAdministratif());
        Assert.assertNotNull(essai.getDetailAdministratif().getInfosAssurance());
        Assert.assertNotNull(essai.getDetailAdministratif().getInfosAC());
        Assert.assertTrue(essai.getDetailAdministratif().getInfosAC().getDocuments().isEmpty());
        Assert.assertFalse(essai.getDetailAdministratif().getInfosAC().isDocsDossierPapier());
        Assert.assertNotNull(essai.getDetailAdministratif().getInfosCPP());
        Assert.assertTrue(essai.getDetailAdministratif().getInfosCPP().getDocuments().isEmpty());
        Assert.assertFalse(essai.getDetailAdministratif().getInfosCPP().isDocsDossierPapier());
        Assert.assertNotNull(essai.getDetailAdministratif().getInfosConvention());
        Assert.assertTrue(essai.getDetailAdministratif().getInfosConvention().getDocuments().isEmpty());
        Assert.assertFalse(essai.getDetailAdministratif().getInfosConvention().isDocsDossierPapier());
        Assert.assertNotNull(essai.getDetailAdministratif().getInfosAssurance());
        Assert.assertTrue(essai.getDetailAdministratif().getInfosAssurance().getDocuments().isEmpty());
        Assert.assertFalse(essai.getDetailAdministratif().getInfosAssurance().isDocsDossierPapier());
        Assert.assertNotNull(essai.getDetailAdministratif().getInfosProtocole());
        Assert.assertTrue(essai.getDetailAdministratif().getInfosProtocole().getDocuments().isEmpty());
        Assert.assertFalse(essai.getDetailAdministratif().getInfosProtocole().isDocsDossierPapier());
        Assert.assertTrue(essai.getDetailAdministratif().getInfosProtocole().getDocuments().isEmpty());
        Assert.assertFalse(essai.getDetailAdministratif().getInfosProtocole().isDocsDossierPapier());
        Assert.assertNotNull(essai.getDetailAdministratif().getInfosBrochure());
        Assert.assertTrue(essai.getDetailAdministratif().getInfosBrochure().getDocuments().isEmpty());
        Assert.assertFalse(essai.getDetailAdministratif().getInfosBrochure().isDocsDossierPapier());
        Assert.assertNotNull(essai.getDetailAdministratif().getInfosArchivage());
        Assert.assertEquals(essai, essai.getDetailAdministratif().getEssai());
        Assert.assertTrue(essai.getDetailAdministratif().getModifs().isEmpty());
    }

    /**
     * Vérification bean DetailDates..
     * @param essai Essai.
     */
    private void verifyDetailDates(final Essai essai) {
        Assert.assertNotNull(essai.getDetailDates());
        Assert.assertEquals(essai, essai.getDetailDates().getEssai());
        Assert.assertTrue(essai.getDetailDates().getModifs().isEmpty());
    }

    /**
     * Vérification bean DetailFaisabilite..
     * @param essai Essai.
     */
    private void verifyDetailFaisabilite(final Essai essai) {
        Assert.assertNotNull(essai.getDetailFaisabilite());
        Assert.assertNotNull(essai.getDetailFaisabilite().getInfosConclusion());
        Assert.assertNotNull(essai.getDetailFaisabilite().getInfosEtude());
        Assert.assertEquals(essai, essai.getDetailFaisabilite().getEssai());
        Assert.assertTrue(essai.getDetailFaisabilite().getModifs().isEmpty());
    }

    /**
     * Vérifications bean DetailRecherche.
     * @param essai Essai.
     */
    private void verifyDetailRecherche(final Essai essai) {
        Assert.assertNotNull(essai.getDetailRecherche());
        Assert.assertEquals(essai, essai.getDetailRecherche().getEssai());
        Assert.assertTrue(essai.getDetailRecherche().getModifs().isEmpty());
    }
}
