package fr.pharma.eclipse.component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import fr.pharma.eclipse.component.essai.TypeContact;
import fr.pharma.eclipse.domain.enums.Civilite;
import fr.pharma.eclipse.domain.enums.ConditionConservation;
import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.enums.EtatRetour;
import fr.pharma.eclipse.domain.enums.NatureRecherche;
import fr.pharma.eclipse.domain.enums.ObjetRecherche;
import fr.pharma.eclipse.domain.enums.PhaseRecherche;
import fr.pharma.eclipse.domain.enums.QualiteInsu;
import fr.pharma.eclipse.domain.enums.RealisePar;
import fr.pharma.eclipse.domain.enums.Responsabilite;
import fr.pharma.eclipse.domain.enums.Thematique;
import fr.pharma.eclipse.domain.enums.TypeAC;
import fr.pharma.eclipse.domain.enums.TypeAnonymisation;
import fr.pharma.eclipse.domain.enums.TypeCommentaireEssai;
import fr.pharma.eclipse.domain.enums.TypeDesignable;
import fr.pharma.eclipse.domain.enums.TypeDispensation;
import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.enums.TypePharmacien;
import fr.pharma.eclipse.domain.enums.TypePromoteur;
import fr.pharma.eclipse.domain.enums.TypeRecherche;
import fr.pharma.eclipse.domain.enums.TypeRechercheParEssai;
import fr.pharma.eclipse.domain.enums.TypeRetour;
import fr.pharma.eclipse.domain.enums.UniteTempsPrevision;
import fr.pharma.eclipse.domain.enums.design.TypeDesign;
import fr.pharma.eclipse.domain.enums.design.TypeRegularite;
import fr.pharma.eclipse.domain.enums.design.UniteTemps;
import fr.pharma.eclipse.domain.enums.document.TypeDocumentBrochure;
import fr.pharma.eclipse.domain.enums.document.TypeDocumentEssai;
import fr.pharma.eclipse.domain.enums.document.TypeDocumentPharmacien;
import fr.pharma.eclipse.domain.enums.document.TypeDocumentProduit;
import fr.pharma.eclipse.domain.enums.document.TypeDocumentProtocole;
import fr.pharma.eclipse.domain.enums.document.TypeDocumentStock;
import fr.pharma.eclipse.domain.enums.evenement.ResultatVisite;
import fr.pharma.eclipse.domain.enums.evenement.TypeEvenement;
import fr.pharma.eclipse.domain.enums.evenement.TypeVisite;
import fr.pharma.eclipse.domain.enums.jasper.TypeRapportJasper;
import fr.pharma.eclipse.domain.enums.produit.ClasseDM;
import fr.pharma.eclipse.domain.enums.produit.FormeConditionnement;
import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.enums.produit.NatureDM;
import fr.pharma.eclipse.domain.enums.produit.NatureProduit;
import fr.pharma.eclipse.domain.enums.produit.TypeProduit;
import fr.pharma.eclipse.domain.enums.produit.TypeProduitTherapeutique;
import fr.pharma.eclipse.domain.enums.produit.TypeUniteGestion;
import fr.pharma.eclipse.domain.enums.produit.UniteDosage;
import fr.pharma.eclipse.domain.enums.produit.UniteGestion;
import fr.pharma.eclipse.domain.enums.produit.UnitePrescription;
import fr.pharma.eclipse.domain.enums.produit.VoieAdministration;
import fr.pharma.eclipse.domain.enums.stock.MotifRefus;
import fr.pharma.eclipse.domain.enums.stock.RaisonSortie;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.enums.surcout.Acte;
import fr.pharma.eclipse.domain.enums.surcout.ModeCalcul;
import fr.pharma.eclipse.domain.enums.surcout.PerimetreCout;
import fr.pharma.eclipse.domain.enums.surcout.TypeCout;

/**
 * ManagedBean pour java faces.<br>
 * Pour rendres la classe (et donc tous les enum referenciel) accessible dans
 * les pages xhtml.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@ManagedBean
@ApplicationScoped
public class EnumManager implements Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4642633735330950461L;

    /**
     * Map contenant les informations de l'enum.
     */
    private static final Map<String, Enum<?>[]> ENUMS = new TreeMap<String, Enum<?>[]>();

    static {
        EnumManager.ENUMS.put("TypePromoteur", TypePromoteur.values());
        EnumManager.ENUMS.put("EtatEssai", EtatEssai.values());
        EnumManager.ENUMS.put("TypeRecherche", TypeRecherche.values());
        EnumManager.ENUMS.put("ObjetRecherche", ObjetRecherche.values());
        EnumManager.ENUMS.put("NatureRecherche", NatureRecherche.values());
        EnumManager.ENUMS.put("PhaseRecherche", PhaseRecherche.values());
        EnumManager.ENUMS.put("Thematique", Thematique.values());
        EnumManager.ENUMS.put("TypePersonne", TypePersonne.values());
        EnumManager.ENUMS.put("TypeCommentaireEssai", TypeCommentaireEssai.values());
        EnumManager.ENUMS.put("TypeAC", TypeAC.values());
        EnumManager.ENUMS.put("TypeDocumentEssai", TypeDocumentEssai.values());
        EnumManager.ENUMS.put("TypeDocumentProduit", TypeDocumentProduit.values());
        EnumManager.ENUMS.put("TypeProduit", TypeProduit.values());
        EnumManager.ENUMS.put("NatureProduit", NatureProduit.values());
        EnumManager.ENUMS.put("NatureDM", NatureDM.values());
        EnumManager.ENUMS.put("ConditionConservation", ConditionConservation.values());
        EnumManager.ENUMS.put("ClasseDM", ClasseDM.values());
        EnumManager.ENUMS.put("ModePrescription", ModePrescription.values());
        EnumManager.ENUMS.put("VoieAdministration", VoieAdministration.values());
        EnumManager.ENUMS.put("UnitePrescription", UnitePrescription.values());
        EnumManager.ENUMS.put("UniteGestion", UniteGestion.values());
        EnumManager.ENUMS.put("TypeProduitTherapeutique", TypeProduitTherapeutique.values());
        EnumManager.ENUMS.put("TypePharmacien", TypePharmacien.values());
        EnumManager.ENUMS.put("TypeContact", TypeContact.values());
        EnumManager.ENUMS.put("QualiteInsu", QualiteInsu.values());
        EnumManager.ENUMS.put("Responsabilite", Responsabilite.values());
        EnumManager.ENUMS.put("TypeMvtStock", TypeMvtStock.values());
        EnumManager.ENUMS.put("TypeMvtStockSortie", TypeMvtStock.SORTIES);
        EnumManager.ENUMS.put("TypeMvtStockSortieSaufPreparation", TypeMvtStock.SORTIES_SANS_PREPARATION);
        EnumManager.ENUMS.put("TypeDispensation", TypeDispensation.values());
        EnumManager.ENUMS.put("TypeDesignable", TypeDesignable.values());
        EnumManager.ENUMS.put("UniteTemps", UniteTemps.values());
        EnumManager.ENUMS.put("TypeRegularite", TypeRegularite.values());
        EnumManager.ENUMS.put("TypeRapportJasper", TypeRapportJasper.values());
        EnumManager.ENUMS.put("MotifRefus", MotifRefus.values());
        EnumManager.ENUMS.put("Civilite", Civilite.values());
        EnumManager.ENUMS.put("TypeDocumentStock", TypeDocumentStock.values());
        EnumManager.ENUMS.put("TypeEvenement", TypeEvenement.values());
        EnumManager.ENUMS.put("TypeVisite", TypeVisite.values());
        EnumManager.ENUMS.put("ResultatVisite", ResultatVisite.values());
        EnumManager.ENUMS.put("TypeCout", TypeCout.values());
        EnumManager.ENUMS.put("ModeCalcul", ModeCalcul.values());
        EnumManager.ENUMS.put("PerimetreCout", PerimetreCout.values());
        EnumManager.ENUMS.put("Acte", Acte.values());
        EnumManager.ENUMS.put("UniteTempsPrevision", UniteTempsPrevision.values());
        EnumManager.ENUMS.put("FormeConditionnement", FormeConditionnement.values());
        EnumManager.ENUMS.put("UniteDosage", UniteDosage.values());
        EnumManager.ENUMS.put("EtatRetour", EtatRetour.values());
        EnumManager.ENUMS.put("TypeRetour", TypeRetour.values());
        EnumManager.ENUMS.put("Droit", Droit.values());
        EnumManager.ENUMS.put("TypeRechercheParEssai", TypeRechercheParEssai.values());
        EnumManager.ENUMS.put("RaisonSortie", RaisonSortie.values());
        EnumManager.ENUMS.put("TypeAnonymisation", TypeAnonymisation.values());
        EnumManager.ENUMS.put("RealisePar", RealisePar.values());
        EnumManager.ENUMS.put("TypeDesign", TypeDesign.values());
        EnumManager.ENUMS.put("TypeDocumentProtocole", TypeDocumentProtocole.values());
        EnumManager.ENUMS.put("TypeDocumentBrochure", TypeDocumentBrochure.values());
        EnumManager.ENUMS.put("TypeDocumentPharmacien", TypeDocumentPharmacien.values());
        EnumManager.ENUMS.put("TypeUniteGestion", TypeUniteGestion.values());
    }

    /**
     * Méthode en charge de retourner les valeurs d'une Enum correspondant au
     * nom.
     * @param name Nom de l'enum.
     * @return Liste des valeurs formatées pour l'affichage.
     */
    public SelectItem[] getValues(final String name) {
        final Enum<?>[] list = EnumManager.ENUMS.get(name);
        final SelectItem[] items = new SelectItem[list.length];
        int i = 0;
        for (final Enum<?> element : list) {
            items[i++] = new SelectItem(element, element.toString());
        }
        return items;
    }

    /**
     * Méthode en charge de récupérer une certaine valeur d'une énumération
     * managée.
     * @param enumKey Clé de l'énumération dans le manager.
     * @param enumValueName Nom de la valeur de l'énumération cherchée.
     * @return La valeur de l'énumération cherchée, null si pas de
     * correspondance.
     */
    public Enum<?> getEnumValue(final String enumKey,
                                final String enumValueName) {
        final Enum<?>[] enumValues = EnumManager.ENUMS.get(enumKey);
        if (enumValues == null) {
            return null;
        }
        return (Enum<?>) CollectionUtils.find(Arrays.asList(enumValues), new EnumValuePredicate(enumValueName));
    }

    /**
     * Méthode en charge de récupérer une certaine valeur d'une énumération
     * managée et l'encapsuler dans un SelectItem.
     * @param enumKey Clé de l'énumération dans le manager.
     * @param enumValueName Nom de la valeur de l'énumération cherchée.
     * @return Le selectItem contenant la valeur de l'énumération.
     */
    public SelectItem getEnumValueAsSelectItem(final String enumKey,
                                               final String enumValueName) {
        final Enum<?> element = this.getEnumValue(enumKey, enumValueName);
        return new SelectItem(element, element.toString());
    }
    /**
     * Classe interne de prédicat pour la sélection des valeurs d'énumération.
     * @author Netapsys
     * @version $Revision$ $Date$
     */
    private class EnumValuePredicate implements Predicate {
        /**
         * Nom de la valeur d'énumération recherchée.
         */
        private final String searchedValueName;

        /**
         * Constructeur.
         * @param searchedValueName Nom de la valeur d'énumération recherchée.
         */
        public EnumValuePredicate(final String searchedValueName) {
            this.searchedValueName = searchedValueName;
        }

        @Override
        public boolean evaluate(final Object object) {
            final Enum<?> value = (Enum<?>) object;
            return value.name().equals(this.searchedValueName);
        }
    }

    /**
     * Getter sur enums.
     * @return Retourne le enums.
     */
    static Map<String, Enum<?>[]> getEnums() {
        return EnumManager.ENUMS;
    }
}
