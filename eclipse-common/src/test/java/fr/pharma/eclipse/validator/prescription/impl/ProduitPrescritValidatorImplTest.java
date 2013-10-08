package fr.pharma.eclipse.validator.prescription.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.exception.ValidationException;

/**
 * Classe en charge de tester la validator de gestion des prescriptions pour les
 * produits prescrits.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ProduitPrescritValidatorImplTest {
    /**
     * Validator à tester.
     */
    private ProduitPrescritValidatorImpl validator;

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Before
    public void init() {
        this.validator = new ProduitPrescritValidatorImpl();
    }

    /**
     * Méthode en charge de tester la purge des données de test.
     */
    @After
    public void end() {
        this.validator = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.validator);
    }

    /**
     * Test de la méthode validateAjoutProduitPrescrit.
     */
    @Test(expected = ValidationException.class)
    public void testValidateAjoutProduitPrescritKo() {
        final ProduitPrescrit produitPrescrit = new ProduitPrescrit();
        final Conditionnement c = new Conditionnement();
        c.setModePrescription(ModePrescription.NUM_TRAITEMENT);
        produitPrescrit.setConditionnement(c);
        final Produit produit = new Medicament();
        produit.setId(1L);
        produitPrescrit.setProduit(produit);

        final List<ProduitPrescrit> produitsPrescrits = new ArrayList<ProduitPrescrit>();
        produitsPrescrits.add(produitPrescrit);
        this.validator.validateAjoutProduitPrescrit(produitPrescrit, produitsPrescrits);
    }

    /**
     * Test de la méthode validateAjoutProduitPrescrit.
     */
    @Test
    public void testValidateAjoutProduitPrescritOk() {
        final ProduitPrescrit produitPrescrit = new ProduitPrescrit();
        final Conditionnement c = new Conditionnement();
        c.setModePrescription(ModePrescription.DOSE);
        produitPrescrit.setConditionnement(c);
        final Produit produit = new Medicament();
        produit.setId(1L);
        produitPrescrit.setProduit(produit);

        final ProduitPrescrit produitPrescrit2 = new ProduitPrescrit();
        final Conditionnement c2 = new Conditionnement();
        c2.setModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE);
        produitPrescrit2.setConditionnement(c2);
        produitPrescrit2.setProduit(produit);

        final List<ProduitPrescrit> produitsPrescrits = new ArrayList<ProduitPrescrit>();
        produitsPrescrits.add(produitPrescrit2);
        this.validator.validateAjoutProduitPrescrit(produitPrescrit, produitsPrescrits);
    }

}
