package fr.pharma.eclipse.service.produit.impl;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.comparator.common.EclipseListComparator;
import fr.pharma.eclipse.comparator.produit.detail.ProduitComparator;
import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.enums.produit.TypeDetailStockage;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.produit.DetailProduit;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.produit.detail.DetailLogistique;
import fr.pharma.eclipse.domain.model.produit.detail.DetailStockage;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.domain.model.suivi.produit.ProduitSuivi;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;
import fr.pharma.eclipse.service.essai.EssaiService;

/**
 * Classe en charge de tester le service de produit.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ProduitServiceImplTest {
    /**
     * ProduitServiceImpl à tester.
     */
    private ProduitServiceImpl<Produit> service;

    /**
     * Mock de la dao de produit.
     */
    private GenericDao<Produit> mockProduitDao;

    /**
     * Mock de factory de suivi de produit.
     */
    private SuiviFactory<ProduitSuivi> mockSuiviFactory;

    /**
     * Mock du service de gestion des essais.
     */
    private EssaiService mockEssaiService;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.mockProduitDao = Mockito.mock(GenericDao.class);
        this.service = new ProduitServiceImpl<Produit>(this.mockProduitDao);
        this.mockSuiviFactory = Mockito.mock(SuiviFactory.class);
        this.service.setProduitSuiviFactory(this.mockSuiviFactory);
        this.mockEssaiService = Mockito.mock(EssaiService.class);
        this.service.setEssaiService(this.mockEssaiService);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.mockProduitDao = null;
        this.service = null;
        this.mockSuiviFactory = null;
        this.mockEssaiService = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données des tests.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.mockProduitDao);
        Assert.assertNotNull(this.service);
        Assert.assertNotNull(this.mockSuiviFactory);
        Assert.assertNotNull(this.mockEssaiService);
    }

    /**
     * Méthode en charge de tester la sauvegarde d'un produit.
     */
    @Test
    public void testSave() {
        final Produit produit = Mockito.mock(Produit.class);
        final ProduitSuivi produitSuivi = Mockito.mock(ProduitSuivi.class);
        Mockito.when(this.mockProduitDao.reattach(produit)).thenReturn(produit);
        Mockito.when(this.mockSuiviFactory.getInitializedObject()).thenReturn(produitSuivi);
        this.service.save(produit);
        Mockito.verify(this.mockProduitDao).save(produit);
        Mockito.verify(this.mockSuiviFactory).getInitializedObject();
    }

    /**
     * Méthode en charge de tester l'ajout de mise à jour de suivi sur un
     * produit.
     */
    @Test
    public void testAddMaj() {
        final Produit produit = Mockito.mock(Produit.class);
        final ProduitSuivi produitSuivi = Mockito.mock(ProduitSuivi.class);
        Mockito.when(this.mockSuiviFactory.getInitializedObject()).thenReturn(produitSuivi);
        this.service.addMaj(produit);
        Mockito.verify(this.mockSuiviFactory).getInitializedObject();
    }

    /**
     * Méthode en charge de tester la récupération des produits d'un essai et
     * d'une pharmacie.
     */
    @Test
    public void testGetProduitsEssaiPharma() {
        final Produit produit1 = new Medicament();
        produit1.setId(1L);
        final Produit produit2 = new Medicament();
        produit1.setId(2L);

        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(1L);

        final Stockage stockage = new Stockage();
        stockage.setId(1L);

        final DetailProduit detailProduit = new DetailProduit();
        final SortedSet<Produit> produits = new TreeSet<Produit>(new ProduitComparator());
        produits.add(produit1);
        produits.add(produit2);
        detailProduit.getProduits().addAll(produits);

        final SortedSet<DetailStockage> detailStockages1 = new TreeSet<DetailStockage>(new EclipseListComparator());
        final DetailStockage detailStockage1 = new DetailStockage();
        final DetailLogistique detailLogistique1 = new DetailLogistique();
        detailStockage1.setDetailLogistique(detailLogistique1);
        detailStockage1.setPharmacie(pharmacie);
        detailStockage1.setStockage(stockage);
        detailStockage1.setType(TypeDetailStockage.STOCK);
        detailStockages1.add(detailStockage1);
        detailLogistique1.setDetailsStockages(detailStockages1);
        produit1.setDetailLogistique(detailLogistique1);

        final SortedSet<DetailStockage> detailStockages2 = new TreeSet<DetailStockage>(new EclipseListComparator());
        final DetailLogistique detailLogistique2 = new DetailLogistique();
        final DetailStockage detailStockage2 = new DetailStockage();
        detailStockage2.setDetailLogistique(detailLogistique2);
        detailStockage2.setPharmacie(pharmacie);
        detailStockage2.setType(TypeDetailStockage.STOCK);
        detailStockages2.add(detailStockage2);
        detailLogistique2.setDetailsStockages(detailStockages2);
        produit2.setDetailLogistique(detailLogistique2);

        final Essai essai = Mockito.mock(Essai.class);
        Mockito.when(essai.getDetailProduit()).thenReturn(detailProduit);

        Mockito.when(this.mockEssaiService.reattach(essai)).thenReturn(essai);

        final List<Produit> result = this.service.getProduits(essai, pharmacie);
        Assert.assertEquals(1, result.size());
    }

    /**
     * Méthode en charge de tester la récupération du stockage associé à un
     * produit et une pharmacie.
     */
    @Test
    public void testGetStockageProduitPharmaOK() {
        final Produit produit = new Medicament();
        final DetailLogistique detailLogistique = new DetailLogistique();

        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(1L);

        final Stockage stockage = new Stockage();
        stockage.setId(1L);

        final SortedSet<DetailStockage> detailStockages = new TreeSet<DetailStockage>(new EclipseListComparator());
        final DetailStockage detailStockage = new DetailStockage();
        detailStockage.setDetailLogistique(detailLogistique);
        detailStockage.setPharmacie(pharmacie);
        detailStockage.setStockage(stockage);
        detailStockage.setType(TypeDetailStockage.STOCK);
        detailStockages.add(detailStockage);

        detailLogistique.setDetailsStockages(detailStockages);
        produit.setDetailLogistique(detailLogistique);

        final Stockage result = this.service.getStockageProduitPharma(produit, pharmacie);
        Assert.assertEquals(stockage, result);
    }

    /**
     * Méthode en charge de tester la récupération du stockage associé à un
     * produit et une pharmacie.
     */
    @Test
    public void testGetStockageProduitPharmaKO1() {
        final Produit produit = new Medicament();
        final DetailLogistique detailLogistique = new DetailLogistique();

        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(1L);

        final Stockage stockage = new Stockage();
        stockage.setId(1L);

        final SortedSet<DetailStockage> detailStockages = new TreeSet<DetailStockage>(new EclipseListComparator());
        final DetailStockage detailStockage = new DetailStockage();
        detailStockage.setDetailLogistique(detailLogistique);
        detailStockage.setPharmacie(pharmacie);
        detailStockage.setStockage(stockage);
        detailStockage.setType(TypeDetailStockage.RETOUR);
        detailStockages.add(detailStockage);

        detailLogistique.setDetailsStockages(detailStockages);
        produit.setDetailLogistique(detailLogistique);

        final Stockage result = this.service.getStockageProduitPharma(produit, pharmacie);
        Assert.assertNull(result);
    }

    /**
     * Méthode en charge de tester la récupération du stockage associé à un
     * produit et une pharmacie.
     */
    @Test
    public void testGetStockageProduitPharmaKO2() {
        final Produit produit = new Medicament();
        final DetailLogistique detailLogistique = new DetailLogistique();

        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(1L);

        final Stockage stockage = new Stockage();
        stockage.setId(1L);

        final SortedSet<DetailStockage> detailStockages = new TreeSet<DetailStockage>(new EclipseListComparator());
        final DetailStockage detailStockage = new DetailStockage();
        detailStockage.setDetailLogistique(detailLogistique);
        detailStockage.setPharmacie(pharmacie);
        detailStockage.setStockage(stockage);
        detailStockage.setType(TypeDetailStockage.RETOUR);
        detailStockages.add(detailStockage);

        detailLogistique.setDetailsStockages(detailStockages);
        produit.setDetailLogistique(detailLogistique);

        final Pharmacie pharmacie2 = new Pharmacie();
        pharmacie2.setId(2L);

        final Stockage result = this.service.getStockageProduitPharma(produit, pharmacie2);
        Assert.assertNull(result);
    }

}
