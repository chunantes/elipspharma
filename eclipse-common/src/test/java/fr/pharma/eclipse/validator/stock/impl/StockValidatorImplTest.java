package fr.pharma.eclipse.validator.stock.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.stock.NumTraitement;
import fr.pharma.eclipse.domain.model.stock.ReceptionLot;
import fr.pharma.eclipse.domain.model.stock.RetourPromoteur;
import fr.pharma.eclipse.domain.model.stock.Sortie;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.exception.ValidationException;

/**
 * Classe en charge de tester la validator de gestion de stock.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class StockValidatorImplTest {
    /**
     * StockValidatorImpl à tester.
     */
    private StockValidatorImpl stockValidator;

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Before
    public void init() {
        this.stockValidator = new StockValidatorImpl();
    }

    /**
     * Méthode en charge de tester la purge des données de test.
     */
    @After
    public void end() {
        this.stockValidator = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.stockValidator);
    }

    /**
     * Méthode en charge de tester la validation des lignes de sortie de stock.
     */
    @Test
    public void testValidateLignesStockSortieOK() {
        final Sortie sortie = new Sortie();
        final MvtStock mvtStock = new RetourPromoteur();
        mvtStock.setEssai(this.getEssai());
        mvtStock.setPharmacie(this.getPharmacie());
        mvtStock.setProduit(this.getProduit());
        mvtStock.setConditionnement(this.getConditionnement());
        sortie.setMvtSortie(mvtStock);
        final List<LigneStock> lignesStock = new ArrayList<LigneStock>();
        final LigneStock ligneStock = new LigneStock(this.getEssai(), this.getPharmacie(), this.getProduit(), this.getConditionnement(), Boolean.TRUE);
        ligneStock.setQteASortir(1);
        ligneStock.setQteGlobalStock(2);
        lignesStock.add(ligneStock);

        sortie.setLignesStock(lignesStock);

        final List<Sortie> sorties = new ArrayList<Sortie>();
        sorties.add(sortie);

        try {
            this.stockValidator.validateLignesStockSortie(sorties, sortie);
        } catch (final ValidationException e) {
            Assert.fail("Exception not expected");
        }
    }

    /**
     * Méthode en charge de tester la validation des lignes de sortie de stock.
     */
    @Test(expected = ValidationException.class)
    public void testValidateLignesStockSortieKOEmpty() {
        final Sortie sortie = new Sortie();
        final MvtStock mvtStock = new RetourPromoteur();
        mvtStock.setEssai(this.getEssai());
        mvtStock.setPharmacie(this.getPharmacie());
        mvtStock.setProduit(this.getProduit());
        mvtStock.setConditionnement(this.getConditionnement());
        sortie.setMvtSortie(mvtStock);
        final List<LigneStock> lignesStock = new ArrayList<LigneStock>();

        sortie.setLignesStock(lignesStock);

        final List<Sortie> sorties = new ArrayList<Sortie>();
        sorties.add(sortie);

        this.stockValidator.validateLignesStockSortie(sorties, sortie);
    }

    /**
     * Méthode en charge de tester la validation des lignes de sortie de stock.
     */
    @Test(expected = ValidationException.class)
    public void testValidateLignesStockSortieKOLignesOkEmpty() {
        final Sortie sortie = new Sortie();
        final MvtStock mvtStock = new RetourPromoteur();
        mvtStock.setEssai(this.getEssai());
        mvtStock.setPharmacie(this.getPharmacie());
        mvtStock.setProduit(this.getProduit());
        mvtStock.setConditionnement(this.getConditionnement());
        sortie.setMvtSortie(mvtStock);
        final List<LigneStock> lignesStock = new ArrayList<LigneStock>();
        final LigneStock ligneStock = new LigneStock(this.getEssai(), this.getPharmacie(), this.getProduit(), this.getConditionnement(), Boolean.TRUE);
        ligneStock.setQteASortir(0);
        ligneStock.setQteGlobalStock(0);
        lignesStock.add(ligneStock);

        sortie.setLignesStock(lignesStock);

        final List<Sortie> sorties = new ArrayList<Sortie>();
        sorties.add(sortie);

        this.stockValidator.validateLignesStockSortie(sorties, sortie);
    }

    /**
     * Méthode en charge de tester la validation des lignes de sortie de stock.
     */
    @Test(expected = ValidationException.class)
    public void testValidateLignesStockSortieKO() {
        final Sortie sortie = new Sortie();
        final MvtStock mvtStock = new RetourPromoteur();
        mvtStock.setEssai(this.getEssai());
        mvtStock.setPharmacie(this.getPharmacie());
        mvtStock.setProduit(this.getProduit());
        mvtStock.setConditionnement(this.getConditionnement());
        sortie.setMvtSortie(mvtStock);
        final List<LigneStock> lignesStock = new ArrayList<LigneStock>();
        final LigneStock ligneStock = new LigneStock(this.getEssai(), this.getPharmacie(), this.getProduit(), this.getConditionnement(), Boolean.TRUE);
        ligneStock.setQteASortir(2);
        ligneStock.setQteGlobalStock(1);
        lignesStock.add(ligneStock);

        sortie.setLignesStock(lignesStock);

        final List<Sortie> sorties = new ArrayList<Sortie>();
        sorties.add(sortie);

        this.stockValidator.validateLignesStockSortie(sorties, sortie);
    }

    /**
     * Méthode en charge de tester la validation des lignes de sortie de stock.
     */
    @Test(expected = ValidationException.class)
    public void testValidateLignesStockSortieKONegatif() {
        final Sortie sortie = new Sortie();
        final MvtStock mvtStock = new RetourPromoteur();
        mvtStock.setEssai(this.getEssai());
        mvtStock.setPharmacie(this.getPharmacie());
        mvtStock.setProduit(this.getProduit());
        mvtStock.setConditionnement(this.getConditionnement());
        sortie.setMvtSortie(mvtStock);
        final List<LigneStock> lignesStock = new ArrayList<LigneStock>();
        final LigneStock ligneStock = new LigneStock(this.getEssai(), this.getPharmacie(), this.getProduit(), this.getConditionnement(), Boolean.TRUE);
        ligneStock.setQteASortir(-1);
        ligneStock.setQteGlobalStock(1);
        lignesStock.add(ligneStock);

        sortie.setLignesStock(lignesStock);

        final List<Sortie> sorties = new ArrayList<Sortie>();
        sorties.add(sortie);

        this.stockValidator.validateLignesStockSortie(sorties, sortie);
    }

    /**
     * Méthode en charge de tester la validation des lignes de sortie de stock.
     */
    @Test(expected = ValidationException.class)
    public void testValidateLignesStockSortieKOAlready() {
        final Sortie sortie = new Sortie();
        final MvtStock mvtStock = new RetourPromoteur();
        mvtStock.setEssai(this.getEssai());
        mvtStock.setPharmacie(this.getPharmacie());
        mvtStock.setProduit(this.getProduit());
        mvtStock.setConditionnement(this.getConditionnement());
        sortie.setMvtSortie(mvtStock);
        final List<LigneStock> lignesStock = new ArrayList<LigneStock>();
        final LigneStock ligneStock = new LigneStock(this.getEssai(), this.getPharmacie(), this.getProduit(), this.getConditionnement(), Boolean.TRUE);
        ligneStock.setQteASortir(-1);
        ligneStock.setQteGlobalStock(1);
        lignesStock.add(ligneStock);

        sortie.setLignesStock(lignesStock);

        final Sortie sortieOther = new Sortie();
        final MvtStock mvtStockOther = new RetourPromoteur();
        mvtStockOther.setEssai(this.getEssai());
        mvtStockOther.setPharmacie(this.getPharmacie());
        mvtStockOther.setProduit(this.getProduit());
        mvtStockOther.setConditionnement(this.getConditionnement());
        sortieOther.setMvtSortie(mvtStockOther);

        final List<Sortie> sorties = new ArrayList<Sortie>();
        sorties.add(sortieOther);
        sorties.add(sortieOther);

        this.stockValidator.validateLignesStockSortie(sorties, sortie);
    }

    /**
     * Méthode en charge de tester une réception de lot.
     */
    @Test
    public void validateReceptionLotOkCondNull() {
        final ReceptionLot receptionLot = new ReceptionLot();
        final Approvisionnement appro = new Approvisionnement();
        receptionLot.setAppro(appro);
        try {
            this.stockValidator.validateReceptionLot(receptionLot);
        } catch (final ValidationException e) {
            Assert.fail("Exception not expected");
        }
    }

    /**
     * Méthode en charge de tester une réception de lot.
     */
    @Test
    public void validateReceptionLotOkDose() {
        final ReceptionLot receptionLot = new ReceptionLot();
        final Approvisionnement appro = new Approvisionnement();
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setModePrescription(ModePrescription.DOSE);
        receptionLot.setAppro(appro);
        appro.setConditionnement(conditionnement);
        appro.setQuantite(1);
        try {
            this.stockValidator.validateReceptionLot(receptionLot);
        } catch (final ValidationException e) {
            Assert.fail("Exception not expected");
        }
    }

    /**
     * Méthode en charge de tester une réception de lot.
     */
    @Test(expected = ValidationException.class)
    public void validateReceptionLotKoDoseQteNull() {
        final ReceptionLot receptionLot = new ReceptionLot();
        final Approvisionnement appro = new Approvisionnement();
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setModePrescription(ModePrescription.DOSE);
        receptionLot.setAppro(appro);
        appro.setConditionnement(conditionnement);
        this.stockValidator.validateReceptionLot(receptionLot);
    }

    /**
     * Méthode en charge de tester une réception de lot.
     */
    @Test(expected = ValidationException.class)
    public void validateReceptionLotKoDoseQteZero() {
        final ReceptionLot receptionLot = new ReceptionLot();
        final Approvisionnement appro = new Approvisionnement();
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setModePrescription(ModePrescription.DOSE);
        receptionLot.setAppro(appro);
        appro.setQuantite(0);
        appro.setConditionnement(conditionnement);
        this.stockValidator.validateReceptionLot(receptionLot);
    }

    /**
     * Méthode en charge de tester une réception de lot.
     */
    @Test
    public void validateReceptionLotOkNumTraitement() {
        final ReceptionLot receptionLot = new ReceptionLot();
        final Approvisionnement appro = new Approvisionnement();
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setModePrescription(ModePrescription.NUM_TRAITEMENT);
        receptionLot.setAppro(appro);
        appro.setConditionnement(conditionnement);
        receptionLot.setNbNumerosTraitement(1);
        final List<NumTraitement> numTraitements = new ArrayList<NumTraitement>();
        final NumTraitement numTraitement = new NumTraitement();
        numTraitement.setNumTraitement("numTraitement");
        numTraitement.setQuantite(1);
        numTraitements.add(numTraitement);
        receptionLot.setNumsTraitements(numTraitements);

        try {
            this.stockValidator.validateReceptionLot(receptionLot);
        } catch (final ValidationException e) {
            Assert.fail("Exception not expected");
        }
    }

    /**
     * Méthode en charge de tester une réception de lot.
     */
    @Test(expected = ValidationException.class)
    public void validateReceptionLotKoNumTraitementNbNull() {
        final ReceptionLot receptionLot = new ReceptionLot();
        final Approvisionnement appro = new Approvisionnement();
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setModePrescription(ModePrescription.NUM_TRAITEMENT);
        receptionLot.setAppro(appro);
        appro.setConditionnement(conditionnement);
        this.stockValidator.validateReceptionLot(receptionLot);
    }

    /**
     * Méthode en charge de tester une réception de lot.
     */
    @Test(expected = ValidationException.class)
    public void validateReceptionLotKoNumTraitementNbZero() {
        final ReceptionLot receptionLot = new ReceptionLot();
        final Approvisionnement appro = new Approvisionnement();
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setModePrescription(ModePrescription.NUM_TRAITEMENT);
        receptionLot.setAppro(appro);
        receptionLot.setNbNumerosTraitement(0);
        appro.setConditionnement(conditionnement);
        this.stockValidator.validateReceptionLot(receptionLot);
    }

    /**
     * Méthode en charge de tester une réception de lot.
     */
    @Test(expected = ValidationException.class)
    public void validateReceptionLotKoNumTraitementListNull() {
        final ReceptionLot receptionLot = new ReceptionLot();
        final Approvisionnement appro = new Approvisionnement();
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setModePrescription(ModePrescription.NUM_TRAITEMENT);
        receptionLot.setAppro(appro);
        receptionLot.setNbNumerosTraitement(1);
        appro.setConditionnement(conditionnement);

        final List<NumTraitement> numTraitements = new ArrayList<NumTraitement>();
        receptionLot.setNumsTraitements(numTraitements);

        this.stockValidator.validateReceptionLot(receptionLot);
    }

    /**
     * Méthode en charge de tester une réception de lot.
     */
    @Test(expected = ValidationException.class)
    public void validateReceptionLotKoNumTraitementListQteZero() {
        final ReceptionLot receptionLot = new ReceptionLot();
        final Approvisionnement appro = new Approvisionnement();
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setModePrescription(ModePrescription.NUM_TRAITEMENT);
        receptionLot.setAppro(appro);
        receptionLot.setNbNumerosTraitement(1);
        appro.setConditionnement(conditionnement);

        final List<NumTraitement> numTraitements = new ArrayList<NumTraitement>();
        final NumTraitement numTraitement = new NumTraitement();
        numTraitement.setNumTraitement("numTraitement");
        numTraitement.setQuantite(0);
        numTraitements.add(numTraitement);
        receptionLot.setNumsTraitements(numTraitements);

        this.stockValidator.validateReceptionLot(receptionLot);
    }

    /**
     * Construction d'un objet Essai de test.
     * @return Essai.
     */
    private Essai getEssai() {
        final Essai essai = new Essai();
        essai.setId(1L);
        return essai;
    }

    /**
     * Construction d'un objet Pharmacie de test.
     * @return Pharmacie.
     */
    private Pharmacie getPharmacie() {
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(1L);
        return pharmacie;
    }

    /**
     * Construction d'un objet Produit de test.
     * @return Produit.
     */
    private Produit getProduit() {
        final Produit produit = new Medicament();
        produit.setId(1L);
        return produit;
    }

    /**
     * Construction d'un objet Conditionnement de test.
     * @return Conditionnement.
     */
    private Conditionnement getConditionnement() {
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setId(1L);
        return conditionnement;
    }

}
