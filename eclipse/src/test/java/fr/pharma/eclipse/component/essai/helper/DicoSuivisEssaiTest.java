package fr.pharma.eclipse.component.essai.helper;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.component.helper.BeanManagerHelper;
import fr.pharma.eclipse.domain.enums.TypeHistoriqueEssai;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.suivi.essai.EssaiSuivi;
import fr.pharma.eclipse.domain.model.suivi.essai.detail.DetailAdministratifSuivi;
import fr.pharma.eclipse.domain.model.suivi.essai.detail.DetailAutresDocumentsSuivi;
import fr.pharma.eclipse.domain.model.suivi.essai.detail.DetailContactsSuivi;
import fr.pharma.eclipse.domain.model.suivi.essai.detail.DetailDatesSuivi;
import fr.pharma.eclipse.domain.model.suivi.essai.detail.DetailDesignSuivi;
import fr.pharma.eclipse.domain.model.suivi.essai.detail.DetailDonneesPharmaSuivi;
import fr.pharma.eclipse.domain.model.suivi.essai.detail.DetailFaisabiliteSuivi;
import fr.pharma.eclipse.domain.model.suivi.essai.detail.DetailProduitSuivi;
import fr.pharma.eclipse.domain.model.suivi.essai.detail.DetailRechercheSuivi;
import fr.pharma.eclipse.utils.EssaiUtils;

/**
 * Test de la classe DicoSuivisEssai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DicoSuivisEssaiTest {
    /**
     * Classe testée.
     */
    private DicoSuivisEssai dico;

    /**
     * Helper (non mocké car le mock de getFirstOfCollection ne fonctionne pas).
     */
    private BeanManagerHelper<Essai> helper;

    /**
     * Méthode d'initialisation.
     */
    @Before
    public void setUp() {
        this.helper = new BeanManagerHelper<Essai>();
        this.dico = new DicoSuivisEssai();
        this.dico.setHelper(this.helper);
    }

    /**
     * Méthode de finalisation.
     */
    @After
    public void tearDown() {
        this.dico = null;
        this.helper = null;
    }

    /**
     * Test de la méthode init.
     */
    @Test
    public void testInit() {
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        final EssaiSuivi expectedModifGenerale = Mockito.mock(EssaiSuivi.class);
        final DetailRechercheSuivi expectedModifOngRecherche = Mockito.mock(DetailRechercheSuivi.class);
        final DetailContactsSuivi expectedModifOngContacts = Mockito.mock(DetailContactsSuivi.class);
        final DetailFaisabiliteSuivi expectedModifOngFaisabilite = Mockito.mock(DetailFaisabiliteSuivi.class);
        final DetailAdministratifSuivi expectedModifOngAdmin = Mockito.mock(DetailAdministratifSuivi.class);
        final DetailProduitSuivi expectedModifOngProduit = Mockito.mock(DetailProduitSuivi.class);
        final DetailDonneesPharmaSuivi expectedModifOngPharma = Mockito.mock(DetailDonneesPharmaSuivi.class);
        final DetailDesignSuivi expectedModifOngDesign = Mockito.mock(DetailDesignSuivi.class);
        final DetailDatesSuivi expectedModifOngDates = Mockito.mock(DetailDatesSuivi.class);
        final DetailAutresDocumentsSuivi expectedModifOngAutresDocs = Mockito.mock(DetailAutresDocumentsSuivi.class);
        essai.getModifs().add(expectedModifGenerale);
        essai.getDetailRecherche().getModifs().add(expectedModifOngRecherche);
        essai.getDetailContacts().getModifs().add(expectedModifOngContacts);
        essai.getDetailFaisabilite().getModifs().add(expectedModifOngFaisabilite);
        essai.getDetailDates().getModifs().add(expectedModifOngDates);
        essai.getDetailAdministratif().getModifs().add(expectedModifOngAdmin);
        essai.getDetailProduit().getModifs().add(expectedModifOngProduit);
        essai.getDetailDonneesPharma().getModifs().add(expectedModifOngPharma);
        essai.getDetailDesign().getModifs().add(expectedModifOngDesign);
        essai.getDetailAutresDocuments().getModifs().add(expectedModifOngAutresDocs);
        
        //Les derniers historiques pour l'onglet design à été mis en
        //commentaire le 23/10/2014 (nom commit : Nouvelle correction du NPE sur la création d'un essai)
        //ancienne valeur expectedSizeDico = 10
        final int expectedSizeDico = 9;

        this.dico.init(essai);
        Assert.assertEquals(expectedSizeDico, this.dico.getDerniersHistoriques().size());
        Assert.assertEquals(expectedModifGenerale, this.dico.getDerniersHistoriques().get(TypeHistoriqueEssai.GENERAL));
        Assert.assertEquals(expectedModifOngRecherche, this.dico.getDerniersHistoriques().get(TypeHistoriqueEssai.ONG_RECHERCHE));
        Assert.assertEquals(expectedModifOngContacts, this.dico.getDerniersHistoriques().get(TypeHistoriqueEssai.ONG_CONTACTS));
        Assert.assertEquals(expectedModifOngFaisabilite, this.dico.getDerniersHistoriques().get(TypeHistoriqueEssai.ONG_FAISABILITE));
        Assert.assertEquals(expectedModifOngDates, this.dico.getDerniersHistoriques().get(TypeHistoriqueEssai.ONG_DATES));
        Assert.assertEquals(expectedModifOngAdmin, this.dico.getDerniersHistoriques().get(TypeHistoriqueEssai.ONG_ADMIN_REG));
        Assert.assertEquals(expectedModifOngProduit, this.dico.getDerniersHistoriques().get(TypeHistoriqueEssai.ONG_PRODUITS));
        Assert.assertEquals(expectedModifOngPharma, this.dico.getDerniersHistoriques().get(TypeHistoriqueEssai.ONG_DATA_PHARMA));
        
        //Idem que pour le commentaire cité plus haut
        //Assert.assertEquals(expectedModifOngDesign, this.dico.getDerniersHistoriques().get(TypeHistoriqueEssai.ONG_DESIGN));
        
        Assert.assertEquals(expectedModifOngAutresDocs, this.dico.getDerniersHistoriques().get(TypeHistoriqueEssai.ONG_AUTRES_DOCS));
    }

    /**
     * Test de la méthode hasDerniereModif.
     */
    @Test
    public void testHasDerniereModif() {
        final EssaiSuivi expectedModifGenerale = Mockito.mock(EssaiSuivi.class);
        final DetailRechercheSuivi expectedModifOngRecherche = null;
        this.dico.getDerniersHistoriques().put(TypeHistoriqueEssai.GENERAL, expectedModifGenerale);
        this.dico.getDerniersHistoriques().put(TypeHistoriqueEssai.ONG_RECHERCHE, expectedModifOngRecherche);
        Assert.assertTrue(this.dico.hasDerniereModif(TypeHistoriqueEssai.GENERAL));
        Assert.assertFalse(this.dico.hasDerniereModif(TypeHistoriqueEssai.ONG_RECHERCHE));
        Assert.assertFalse(this.dico.hasDerniereModif(TypeHistoriqueEssai.ONG_PRODUITS));
    }

    /**
     * Test de la méthode getDerniereModif.
     */
    @Test
    public void testGetDerniereModif() {
        final EssaiSuivi expectedModifGenerale = Mockito.mock(EssaiSuivi.class);
        final DetailRechercheSuivi expectedModifOngRecherche = null;
        this.dico.getDerniersHistoriques().put(TypeHistoriqueEssai.GENERAL, expectedModifGenerale);
        this.dico.getDerniersHistoriques().put(TypeHistoriqueEssai.ONG_RECHERCHE, expectedModifOngRecherche);

        Assert.assertEquals(expectedModifGenerale, this.dico.getDerniereModif(TypeHistoriqueEssai.GENERAL));
        Assert.assertEquals(expectedModifOngRecherche, this.dico.getDerniereModif(TypeHistoriqueEssai.ONG_RECHERCHE));

    }

}
