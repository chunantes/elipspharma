package fr.pharma.eclipse.utils;

import java.math.BigDecimal;

import fr.pharma.eclipse.domain.enums.produit.UniteDosage;
import fr.pharma.eclipse.domain.enums.produit.UniteGestion;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.DispositifMedical;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.produit.ProduitTherapeutique;
import fr.pharma.eclipse.domain.model.produit.detail.DetailLogistique;

/**
 * Classe utilitaire pour les jeux de tests de type Produit.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public final class ProduitUtils {
    /**
     * Constructeur.
     */
    private ProduitUtils() {
        super();
    }

    /**
     * Méthode de création de médicament.
     * @param id Identifiant.
     * @return Un nouveau bean Medicament.
     */
    public static Medicament makeMedicamentTest(final long id) {
        final Medicament medicament = new Medicament();
        ProduitUtils.fill(medicament, id);
        return medicament;
    }

    /**
     * Méthode de création de dispositif médical.
     * @param id Identifiant.
     * @return Un nouveau bean DispositifMedical.
     */
    public static DispositifMedical makeDispMedicalTest(final long id) {
        final DispositifMedical dispMedical = new DispositifMedical();
        ProduitUtils.fill(dispMedical, id);
        return dispMedical;
    }
    /**
     * Méthode de création de produit thérapeutique.
     * @param id Identifiant.
     * @return Un nouveau bean ProduitTherapeutique.
     */
    public static ProduitTherapeutique makePduitTherapeutiqueTest(final long id) {
        final ProduitTherapeutique pduitTherapeutique = new ProduitTherapeutique();
        ProduitUtils.fill(pduitTherapeutique, id);
        return pduitTherapeutique;
    }

    /**
     * Méthode de création de conditionnement.
     * @param id Identifiant.
     * @param uniteGestion Unité de gestion.
     * @param dosage Dosage.
     * @param uniteDosage Unité de dosage.
     * @return Un nouveau bean Conditionnement.
     */
    public static Conditionnement makeConditionnementTest(final long id,
                                                          final UniteGestion uniteGestion,
                                                          final Integer dosage,
                                                          final String uniteDosage) {
        final Conditionnement cond = new Conditionnement();
        cond.setId(id);
        cond.setUniteGestion(uniteGestion);
        cond.setDosage(new BigDecimal(5));
        cond.setUniteDosage(UniteDosage.COMPRIME);
        return cond;
    }

    /**
     * Méthode en charge de valoriser les informations communes à tous les
     * produits.
     * @param produit Produit.
     * @param id Identifiant du produit.
     */
    private static void fill(final Produit produit,
                             final long id) {
        produit.setId(id);
        final DetailLogistique detailLogistique = new DetailLogistique();
        detailLogistique.setId(id);
        produit.setDetailLogistique(detailLogistique);
    }
}
