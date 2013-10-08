package fr.pharma.eclipse.component;

import javax.faces.model.SelectItem;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.component.essai.TypeContact;
import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.enums.NatureRecherche;
import fr.pharma.eclipse.domain.enums.ObjetRecherche;
import fr.pharma.eclipse.domain.enums.PhaseRecherche;
import fr.pharma.eclipse.domain.enums.Thematique;
import fr.pharma.eclipse.domain.enums.TypeAC;
import fr.pharma.eclipse.domain.enums.TypeCommentaireEssai;
import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.enums.TypePharmacien;
import fr.pharma.eclipse.domain.enums.TypePromoteur;
import fr.pharma.eclipse.domain.enums.TypeRecherche;
import fr.pharma.eclipse.domain.enums.document.TypeDocumentEssai;
import fr.pharma.eclipse.domain.enums.evenement.ResultatVisite;
import fr.pharma.eclipse.domain.enums.evenement.TypeEvenement;
import fr.pharma.eclipse.domain.enums.evenement.TypeVisite;
import fr.pharma.eclipse.domain.enums.jasper.TypeRapportJasper;
import fr.pharma.eclipse.domain.enums.stock.MotifRefus;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;

/**
 * Classe en charge de tester le manager d'enums de l'application.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EnumManagerTest {
    /**
     * EnumManager à tester.
     */
    private EnumManager enumManager;

    /**
     * Clé d'énumération présente dans le manager.
     */
    private static final String CLE_TYPE_COMMENTAIRE = "TypeCommentaireEssai";

    /**
     * Clé d'énumération non présente dans le manager.
     */
    private static final String CLE_ENUM_ABSENTE = "EnumInexistante";

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.enumManager = new EnumManager();
        Assert.assertFalse(EnumManager.getEnums().isEmpty());
        Assert.assertTrue(EnumManager.getEnums().containsKey(EnumManagerTest.CLE_TYPE_COMMENTAIRE));
        Assert.assertFalse(EnumManager.getEnums().containsKey(EnumManagerTest.CLE_ENUM_ABSENTE));
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.enumManager = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.enumManager);
    }

    /**
     * Méthode en charge de tester la méthode de récupération des valeurs de
     * l'enum TypePromoteur.
     */
    @Test
    public void testGetValuesTypePromoteur() {
        final SelectItem[] items = this.enumManager.getValues("TypePromoteur");
        Assert.assertEquals(items.length, TypePromoteur.values().length);
    }

    /**
     * Méthode en charge de tester la méthode de récupération des valeurs de
     * l'enum EtatEssai.
     */
    @Test
    public void testGetValuesEtatEssai() {
        final SelectItem[] items = this.enumManager.getValues("EtatEssai");
        Assert.assertEquals(items.length, EtatEssai.values().length);
    }

    /**
     * Méthode en charge de tester la méthode de récupération des valeurs de
     * l'enum TypeRecherche.
     */
    @Test
    public void testGetValuesTypeRecherche() {
        final SelectItem[] items = this.enumManager.getValues("TypeRecherche");
        Assert.assertEquals(items.length, TypeRecherche.values().length);
    }

    /**
     * Méthode en charge de tester la méthode de récupération des valeurs de
     * l'enum ObjetRecherche.
     */
    @Test
    public void testGetValuesObjetRecherche() {
        final SelectItem[] items = this.enumManager.getValues("ObjetRecherche");
        Assert.assertEquals(items.length, ObjetRecherche.values().length);
    }

    /**
     * Méthode en charge de tester la méthode de récupération des valeurs de
     * l'enum NatureRecherche.
     */
    @Test
    public void testGetValuesNatureRecherche() {
        final SelectItem[] items = this.enumManager.getValues("NatureRecherche");
        Assert.assertEquals(items.length, NatureRecherche.values().length);
    }

    /**
     * Méthode en charge de tester la méthode de récupération des valeurs de
     * l'enum PhaseRecherche.
     */
    @Test
    public void testGetValuesPhaseRecherche() {
        final SelectItem[] items = this.enumManager.getValues("PhaseRecherche");
        Assert.assertEquals(items.length, PhaseRecherche.values().length);
    }

    /**
     * Méthode en charge de tester la méthode de récupération des valeurs de
     * l'enum Thematique.
     */
    @Test
    public void testGetValuesThematique() {
        final SelectItem[] items = this.enumManager.getValues("Thematique");
        Assert.assertEquals(items.length, Thematique.values().length);
    }

    /**
     * Méthode en charge de tester la méthode de récupération des valeurs de
     * l'enum TypePharmacien.
     */
    @Test
    public void testGetValuesTypePharmacien() {
        final SelectItem[] items = this.enumManager.getValues("TypePharmacien");
        Assert.assertEquals(items.length, TypePharmacien.values().length);
    }

    /**
     * Méthode en charge de tester la méthode de récupération des valeurs de
     * l'enum TypePersonne.
     */
    @Test
    public void testGetValuesTypePersonne() {
        final SelectItem[] items = this.enumManager.getValues("TypePersonne");
        Assert.assertEquals(items.length, TypePersonne.values().length);
    }

    /**
     * Méthode en charge de tester la méthode de récupération des valeurs de
     * l'enum TypeCommentaireEssai.
     */
    @Test
    public void testGetValuesTypeCommentaireEssai() {
        final SelectItem[] items = this.enumManager.getValues("TypeCommentaireEssai");
        Assert.assertEquals(items.length, TypeCommentaireEssai.values().length);
    }

    /**
     * Méthode en charge de tester la méthode de récupération des valeurs de
     * l'enum TypeAC.
     */
    @Test
    public void testGetValuesTypeAC() {
        final SelectItem[] items = this.enumManager.getValues("TypeAC");
        Assert.assertEquals(items.length, TypeAC.values().length);
    }

    /**
     * Méthode en charge de tester la méthode de récupération des valeurs de
     * l'enum TypeDocumentEssai.
     */
    @Test
    public void testGetValuesTypeDocumentEssai() {
        final SelectItem[] items = this.enumManager.getValues("TypeDocumentEssai");
        Assert.assertEquals(items.length, TypeDocumentEssai.values().length);
    }

    /**
     * Méthode en charge de tester la méthode de récupération des valeurs de
     * l'enum TypeContact.
     */
    @Test
    public void testGetValuesTypeContact() {
        final SelectItem[] items = this.enumManager.getValues("TypeContact");
        Assert.assertEquals(items.length, TypeContact.values().length);
    }

    /**
     * Méthode en charge de tester la méthode de récupération des valeurs de
     * l'enum TypeRapportJasper.
     */
    @Test
    public void testGetValuesTypeRapportJasper() {
        final SelectItem[] items = this.enumManager.getValues("TypeRapportJasper");
        Assert.assertEquals(items.length, TypeRapportJasper.values().length);
    }

    /**
     * Méthode en charge de tester la méthode de récupération des valeurs de
     * l'enum MotifRefus.
     */
    @Test
    public void testGetValuesMotifRefus() {
        final SelectItem[] items = this.enumManager.getValues("MotifRefus");
        Assert.assertEquals(items.length, MotifRefus.values().length);
    }

    /**
     * Méthode en charge de tester la méthode de récupération des valeurs de
     * l'enum TypeMvtStock.
     */
    @Test
    public void testGetValuesTypeMvtStock() {
        final SelectItem[] items = this.enumManager.getValues("TypeMvtStock");
        Assert.assertEquals(items.length, TypeMvtStock.values().length);
    }

    /**
     * Méthode en charge de tester la méthode de récupération des valeurs de
     * l'enum TypeMvtStock de type Sortie.
     */
    @Test
    public void testGetValuesTypeMvtStockSortie() {
        final SelectItem[] items = this.enumManager.getValues("TypeMvtStockSortie");
        Assert.assertEquals(items.length, TypeMvtStock.SORTIES.length);
    }

    /**
     * Test de la méthode getEnumValue Ok.
     */
    @Test
    public void testGetEnumValueOk() {
        final TypeCommentaireEssai expectedEnumValue = TypeCommentaireEssai.FAISABILITE_CONCL;
        Assert.assertEquals(expectedEnumValue, this.enumManager.getEnumValue(EnumManagerTest.CLE_TYPE_COMMENTAIRE, expectedEnumValue.name()));
    }

    /**
     * Test de la méthode getEnumValue Ko : clé inexistante.
     */
    @Test
    public void testGetEnumValueKoInvalidKey() {
        final TypeCommentaireEssai expectedEnumValue = TypeCommentaireEssai.FAISABILITE_CONCL;
        Assert.assertNull(this.enumManager.getEnumValue(EnumManagerTest.CLE_ENUM_ABSENTE, expectedEnumValue.name()));
    }

    /**
     * Test de la méthode getEnumValue Ko : valeur d'enum inexistante.
     */
    @Test
    public void testGetEnumValueKoInvalidEnumValueName() {
        final TypeCommentaireEssai expectedEnumValue = TypeCommentaireEssai.FAISABILITE_CONCL;
        Assert.assertNull(this.enumManager.getEnumValue(EnumManagerTest.CLE_TYPE_COMMENTAIRE, expectedEnumValue.name().concat("_badPart")));
    }

    /**
     * Méthode en charge de tester la méthode de récupération des valeurs de
     * l'enum TypeEvenement.
     */
    @Test
    public void testGetValuesTypeEvenement() {
        final SelectItem[] items = this.enumManager.getValues("TypeEvenement");
        Assert.assertEquals(items.length, TypeEvenement.values().length);
    }

    /**
     * Méthode en charge de tester la méthode de récupération des valeurs de
     * l'enum TypeVisite.
     */
    @Test
    public void testGetValuesTypeVisite() {
        final SelectItem[] items = this.enumManager.getValues("TypeVisite");
        Assert.assertEquals(items.length, TypeVisite.values().length);
    }

    /**
     * Méthode en charge de tester la méthode de récupération des valeurs de
     * l'enum ResultatVisite.
     */
    @Test
    public void testGetValuesResultatVisite() {
        final SelectItem[] items = this.enumManager.getValues("ResultatVisite");
        Assert.assertEquals(items.length, ResultatVisite.values().length);
    }

}
