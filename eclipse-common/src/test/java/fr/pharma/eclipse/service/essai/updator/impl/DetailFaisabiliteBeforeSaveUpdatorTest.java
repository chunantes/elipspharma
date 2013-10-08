package fr.pharma.eclipse.service.essai.updator.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.dates.DetailDates;
import fr.pharma.eclipse.domain.model.essai.detail.faisabilite.embedded.InfosEtudeFaisabilite;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.helper.common.BeanPropertyReinitializer;
import fr.pharma.eclipse.utils.EssaiUtils;

/**
 * Test de la classe DetailFaisabiliteBeforeSaveUpdator.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DetailFaisabiliteBeforeSaveUpdatorTest {
    /**
     * Classe testée.
     */
    private DetailFaisabiliteBeforeSaveUpdator updator;

    /**
     * Méthode d'initialisation.
     */
    @Before
    public void setUp() {
        this.updator = new DetailFaisabiliteBeforeSaveUpdator();
        this.updator.setReinitializer(new BeanPropertyReinitializer<InfosEtudeFaisabilite>());
    }

    /**
     * Méthode de finalisation.
     */
    @After
    public void tearDown() {
        this.updator = null;
    }

    /**
     * Test de la méthode update : prestations particulières par la pharmacie
     * passe à false.
     */
    @Test
    public void updateWhenPrestaPharmaFalse() {
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        final InfosEtudeFaisabilite bean = EssaiUtils.makeCompleteInfoEtude(true);
        essai.setDetailDates(new DetailDates());
        essai.getDetailFaisabilite().setInfosEtude(bean);
        bean.setPrestaParticulieresPharmacie(false);
        essai.setEtat(EtatEssai.CLOTURE);
        this.updator.update(essai, Mockito.mock(EssaiService.class));

        // Vérifications
        Assert.assertNull(bean.getAccordPharmaCentrale());
        Assert.assertNull(bean.getAchatsProduitsPUI());
        Assert.assertNull(bean.getCircuitDistribDefini());
        Assert.assertTrue(bean.getCommentaires().isEmpty());
        Assert.assertTrue(bean.getCommentairesAchatsPUI().isEmpty());
        Assert.assertTrue(bean.getCommentairesDistribAutresPharma().isEmpty());
        Assert.assertNull(bean.getConditionnementPduits());
        Assert.assertNull(bean.getDemandeImportation());
        Assert.assertNull(bean.getDispensationPossEnGarde());
        Assert.assertNull(bean.getDistribAutresPharmaPossible());
        Assert.assertNull(bean.getDonneesStabilite());
        Assert.assertNull(bean.getEtiquetagesPduits());
        Assert.assertNull(bean.getGestionAveugle());
        Assert.assertNull(bean.getPreparations());
        Assert.assertFalse(bean.getPrestaParticulieresPharmacie());
        Assert.assertNull(bean.getRandomisationParPharma());
        Assert.assertNull(bean.getRandomisationPossEnGarde());
        Assert.assertNull(bean.getReconstitutions());
        Assert.assertNull(bean.getRefProduitsCHU());
        Assert.assertTrue(bean.getServicesImputation().isEmpty());
        Assert.assertNull(bean.getSocTransportDefinie());
        Assert.assertNull(bean.getSuiviStocksParPharmacie());
        Assert.assertNull(bean.getSuiviTempNecessairePdtTransp());
    }

    /**
     * Test de la méthode update : Achats de produits par la PUI. passe à false.
     */
    @Test
    public void updateWhenAchatsProduitsPUIFalse() {
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        final InfosEtudeFaisabilite bean = EssaiUtils.makeCompleteInfoEtude(true);
        essai.getDetailFaisabilite().setInfosEtude(bean);
        bean.setAchatsProduitsPUI(false);
        essai.setEtat(EtatEssai.CLOTURE);
        essai.setDetailDates(new DetailDates());
        this.updator.update(essai, Mockito.mock(EssaiService.class));

        // Vérifications
        Assert.assertNull(bean.getAccordPharmaCentrale());
        Assert.assertFalse(bean.getAchatsProduitsPUI());
        Assert.assertTrue(bean.getCircuitDistribDefini());
        Assert.assertFalse(bean.getCommentaires().isEmpty());
        Assert.assertTrue(bean.getCommentairesAchatsPUI().isEmpty());
        Assert.assertFalse(bean.getCommentairesDistribAutresPharma().isEmpty());
        Assert.assertTrue(bean.getConditionnementPduits());
        Assert.assertTrue(bean.getDemandeImportation());
        Assert.assertTrue(bean.getDispensationPossEnGarde());
        Assert.assertTrue(bean.getDistribAutresPharmaPossible());
        Assert.assertTrue(bean.getDonneesStabilite());
        Assert.assertTrue(bean.getEtiquetagesPduits());
        Assert.assertTrue(bean.getGestionAveugle());
        Assert.assertTrue(bean.getPreparations());
        Assert.assertTrue(bean.getPrestaParticulieresPharmacie());
        Assert.assertTrue(bean.getRandomisationParPharma());
        Assert.assertTrue(bean.getRandomisationPossEnGarde());
        Assert.assertTrue(bean.getReconstitutions());
        Assert.assertNull(bean.getRefProduitsCHU());
        Assert.assertTrue(bean.getServicesImputation().isEmpty());
        Assert.assertTrue(bean.getSocTransportDefinie());
        Assert.assertTrue(bean.getSuiviStocksParPharmacie());
        Assert.assertTrue(bean.getSuiviTempNecessairePdtTransp());
    }

    /**
     * Test de la méthode update : Randomisation des patiens par la pharmacie
     * passe à false.
     */
    @Test
    public void updateWhenRandomisationParPharmaFalse() {
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        final InfosEtudeFaisabilite bean = EssaiUtils.makeCompleteInfoEtude(true);
        essai.getDetailFaisabilite().setInfosEtude(bean);
        bean.setRandomisationParPharma(false);
        essai.setEtat(EtatEssai.CLOTURE);
        essai.setDetailDates(new DetailDates());
        this.updator.update(essai, Mockito.mock(EssaiService.class));

        // Vérifications
        Assert.assertTrue(bean.getAccordPharmaCentrale());
        Assert.assertTrue(bean.getAchatsProduitsPUI());
        Assert.assertTrue(bean.getCircuitDistribDefini());
        Assert.assertFalse(bean.getCommentaires().isEmpty());
        Assert.assertFalse(bean.getCommentairesAchatsPUI().isEmpty());
        Assert.assertFalse(bean.getCommentairesDistribAutresPharma().isEmpty());
        Assert.assertTrue(bean.getConditionnementPduits());
        Assert.assertTrue(bean.getDemandeImportation());
        Assert.assertTrue(bean.getDispensationPossEnGarde());
        Assert.assertTrue(bean.getDistribAutresPharmaPossible());
        Assert.assertTrue(bean.getDonneesStabilite());
        Assert.assertTrue(bean.getEtiquetagesPduits());
        Assert.assertTrue(bean.getGestionAveugle());
        Assert.assertTrue(bean.getPreparations());
        Assert.assertTrue(bean.getPrestaParticulieresPharmacie());
        Assert.assertFalse(bean.getRandomisationParPharma());
        Assert.assertNull(bean.getRandomisationPossEnGarde());
        Assert.assertTrue(bean.getReconstitutions());
        Assert.assertTrue(bean.getRefProduitsCHU());
        Assert.assertFalse(bean.getServicesImputation().isEmpty());
        Assert.assertTrue(bean.getSocTransportDefinie());
        Assert.assertTrue(bean.getSuiviStocksParPharmacie());
        Assert.assertTrue(bean.getSuiviTempNecessairePdtTransp());
    }

    /**
     * Test de la méthode update : Distribution possible à d'autres pharmacies
     * passe à false.
     */
    @Test
    public void updateWhenDistribAutresPharmaPossibleFalse() {
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        final InfosEtudeFaisabilite bean = EssaiUtils.makeCompleteInfoEtude(true);
        essai.getDetailFaisabilite().setInfosEtude(bean);
        bean.setDistribAutresPharmaPossible(false);
        essai.setEtat(EtatEssai.CLOTURE);
        essai.setDetailDates(new DetailDates());
        this.updator.update(essai, Mockito.mock(EssaiService.class));

        // Vérifications
        Assert.assertTrue(bean.getAccordPharmaCentrale());
        Assert.assertTrue(bean.getAchatsProduitsPUI());
        Assert.assertNull(bean.getCircuitDistribDefini());
        Assert.assertFalse(bean.getCommentaires().isEmpty());
        Assert.assertFalse(bean.getCommentairesAchatsPUI().isEmpty());
        Assert.assertTrue(bean.getCommentairesDistribAutresPharma().isEmpty());
        Assert.assertTrue(bean.getConditionnementPduits());
        Assert.assertTrue(bean.getDemandeImportation());
        Assert.assertTrue(bean.getDispensationPossEnGarde());
        Assert.assertFalse(bean.getDistribAutresPharmaPossible());
        Assert.assertTrue(bean.getDonneesStabilite());
        Assert.assertTrue(bean.getEtiquetagesPduits());
        Assert.assertTrue(bean.getGestionAveugle());
        Assert.assertTrue(bean.getPreparations());
        Assert.assertTrue(bean.getPrestaParticulieresPharmacie());
        Assert.assertTrue(bean.getRandomisationParPharma());
        Assert.assertTrue(bean.getRandomisationPossEnGarde());
        Assert.assertTrue(bean.getReconstitutions());
        Assert.assertTrue(bean.getRefProduitsCHU());
        Assert.assertFalse(bean.getServicesImputation().isEmpty());
        Assert.assertNull(bean.getSocTransportDefinie());
        Assert.assertNull(bean.getSuiviStocksParPharmacie());
        Assert.assertNull(bean.getSuiviTempNecessairePdtTransp());
    }

}
