package fr.pharma.eclipse.component.produit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.component.helper.BeanManagerHelper;
import fr.pharma.eclipse.domain.criteria.produit.ProduitSearchCriteria;
import fr.pharma.eclipse.domain.enums.produit.TypeProduit;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.produit.DetailProduit;
import fr.pharma.eclipse.domain.model.produit.DispositifMedical;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.produit.ProduitTherapeutique;
import fr.pharma.eclipse.domain.model.produit.detail.DetailLogistique;
import fr.pharma.eclipse.redirect.RedirectHandler;
import fr.pharma.eclipse.validator.remove.RemoveValidator;

/**
 * Classe en charge de tester le manager de Produit.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ProduitListManagerTest {

    /**
     * ProduitsManager à tester.
     */
    private ProduitListManager produitsManager;

    /**
     * Critère de recherche de personne.
     */
    private ProduitSearchCriteria criteria;

    /**
     * Mock du handler de redirection.
     */
    private RedirectHandler mockRedirect;

    /**
     * helper.
     */
    private BeanManagerHelper<Produit> helper;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.criteria = new ProduitSearchCriteria();
        this.helper = Mockito.mock(BeanManagerHelper.class);
        this.produitsManager = new ProduitListManager(this.criteria);
        this.mockRedirect = Mockito.mock(RedirectHandler.class);
        this.produitsManager.setHelper(this.helper);
        this.produitsManager.setRedirectHandler(this.mockRedirect);
        final Map<TypeProduit, String> typeProduitDictionary = new HashMap<TypeProduit, String>();
        typeProduitDictionary.put(TypeProduit.DISPOSITIF_MEDICAL, "dispositifsMedicaux");
        this.produitsManager.setTypeProduitDictionary(typeProduitDictionary);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.criteria = null;
        this.produitsManager = null;
        this.mockRedirect = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.criteria);
        Assert.assertNotNull(this.produitsManager);
        Assert.assertNotNull(this.mockRedirect);
        Assert.assertNull(this.produitsManager.getTypeProduit());
    }

    /**
     * Méthode en charge de tester la redirection dans le cas d'une édition de
     * personne.
     * @throws IOException Exception Input/Output.
     */
    @Test
    public void testRedirectEditProduitMedicament() throws IOException {
        final Medicament medicament = new Medicament();
        medicament.setId(1L);
        medicament.setType(TypeProduit.MEDICAMENT);
        this.produitsManager.setBeanSelected(medicament);

        this.produitsManager.redirectEditProduit();

        final String urlRedirect = "editMedicament?idMedicament=1";
        Mockito.verify(this.mockRedirect).redirect(urlRedirect);
        Assert.assertNull(this.produitsManager.getBeanSelected());
    }

    /**
     * Méthode en charge de tester la redirection dans le cas d'une édition de
     * dispositif medical.
     * @throws IOException Exception Input/Output.
     */
    @Test
    public void testRedirectEditProduitDm() throws IOException {
        final DispositifMedical dm = new DispositifMedical();
        dm.setId(1L);
        dm.setType(TypeProduit.DISPOSITIF_MEDICAL);
        this.produitsManager.setBeanSelected(dm);

        this.produitsManager.redirectEditProduit();

        final String urlRedirect = "editDispositifMedical?idDm=1";
        Mockito.verify(this.mockRedirect).redirect(urlRedirect);
        Assert.assertNull(this.produitsManager.getBeanSelected());
    }

    /**
     * Méthode en charge de tester la redirection dans le cas d'une édition de
     * produit therapeutique.
     * @throws IOException Exception Input/Output.
     */
    @Test
    public void testRedirectEditProduitProduitTherapeutique() throws IOException {
        final ProduitTherapeutique prod = new ProduitTherapeutique();
        prod.setId(1L);
        prod.setType(TypeProduit.PRODUIT_THERAPEUTIQUE);
        this.produitsManager.setBeanSelected(prod);

        this.produitsManager.redirectEditProduit();

        final String urlRedirect = "editProduitTherapeutique?idProduitThera=1";
        Mockito.verify(this.mockRedirect).redirect(urlRedirect);
        Assert.assertNull(this.produitsManager.getBeanSelected());
    }

    /**
     * Méthode en charge de tester la redirection dans le cas d'un ajout de
     * produit therapeutique.
     * @throws IOException Exception Input/Output.
     */
    @Test
    public void testRedirectAjoutProduitProduitTherapeutique() throws IOException {
        final ProduitTherapeutique prod = new ProduitTherapeutique();
        prod.setId(1L);
        prod.setType(TypeProduit.PRODUIT_THERAPEUTIQUE);
        this.produitsManager.setBeanSelected(prod);
        this.produitsManager.setTypeProduit(TypeProduit.PRODUIT_THERAPEUTIQUE);

        this.produitsManager.redirectAjoutProduit();

        final String urlRedirect = "editProduitTherapeutique";
        Mockito.verify(this.mockRedirect).redirect(urlRedirect);
        Assert.assertNull(this.produitsManager.getBeanSelected());
    }

    /**
     * Méthode en charge de tester la redirection dans le cas d'un ajout de dm.
     * @throws IOException Exception Input/Output.
     */
    @Test
    public void testRedirectAjoutDM() throws IOException {
        final DispositifMedical prod = new DispositifMedical();
        prod.setId(1L);
        prod.setType(TypeProduit.DISPOSITIF_MEDICAL);
        this.produitsManager.setBeanSelected(prod);
        this.produitsManager.setTypeProduit(TypeProduit.DISPOSITIF_MEDICAL);

        this.produitsManager.redirectAjoutProduit();

        final String urlRedirect = "editDispositifMedical";
        Mockito.verify(this.mockRedirect).redirect(urlRedirect);
        Assert.assertNull(this.produitsManager.getBeanSelected());
    }

    /**
     * Méthode en charge de tester la redirection dans le cas d'un ajout de
     * medicament.
     * @throws IOException Exception Input/Output.
     */
    @Test
    public void testRedirectAjoutMedicament() throws IOException {
        final DispositifMedical prod = new DispositifMedical();
        prod.setId(1L);
        prod.setType(TypeProduit.MEDICAMENT);
        this.produitsManager.setBeanSelected(prod);
        this.produitsManager.setTypeProduit(TypeProduit.MEDICAMENT);

        this.produitsManager.redirectAjoutProduit();

        final String urlRedirect = "editMedicament";
        Mockito.verify(this.mockRedirect).redirect(urlRedirect);
        Assert.assertNull(this.produitsManager.getBeanSelected());
    }

    /**
     * Test de la méthode dupliquer.
     */
    @Test
    public void testDupliquer() {
        final Essai essai = new Essai();
        essai.setDetailProduit(new DetailProduit());
        final DispositifMedical d1 = new DispositifMedical();
        d1.setDenomination("denomination");
        d1.setSelected(true);
        d1.setDetailLogistique(new DetailLogistique());
        d1.setId(1L);
        d1.setType(TypeProduit.DISPOSITIF_MEDICAL);
        essai.getDetailProduit().getMedicaments().add(new Medicament());
        essai.getDetailProduit().getProduitsTherapeutiques().add(new ProduitTherapeutique());
        essai.getDetailProduit().getDispositifsMedicaux().add(d1);
        Mockito.when(this.helper.getBeansSelected(Matchers.anyCollection())).thenReturn(new ArrayList(essai.getDetailProduit().getDispositifsMedicaux()));
        this.produitsManager.dupliquer(essai);
        Assert.assertEquals(2, essai.getDetailProduit().getDispositifsMedicaux().size());
        Assert.assertEquals(1, essai.getDetailProduit().getMedicaments().size());
        Assert.assertEquals(1, essai.getDetailProduit().getProduitsTherapeutiques().size());
    }

    /**
     * Test de la méthode remove.
     */
    @Test
    public void testRemove() {
        final Essai essai = new Essai();
        essai.setDetailProduit(new DetailProduit());
        final DispositifMedical d1 = new DispositifMedical();
        d1.setSelected(true);
        d1.setDetailLogistique(new DetailLogistique());
        d1.setId(1L);
        d1.setType(TypeProduit.DISPOSITIF_MEDICAL);
        essai.getDetailProduit().getMedicaments().add(new Medicament());
        essai.getDetailProduit().getProduitsTherapeutiques().add(new ProduitTherapeutique());
        essai.getDetailProduit().getDispositifsMedicaux().add(d1);
        final RemoveValidator v = Mockito.mock(RemoveValidator.class);
        this.produitsManager.setRemoveValidator(v);
        Mockito.when(this.helper.getBeansSelected(Matchers.anyCollection())).thenReturn(new ArrayList(essai.getDetailProduit().getDispositifsMedicaux()));
        this.produitsManager.remove(essai);
        Assert.assertEquals(0, essai.getDetailProduit().getDispositifsMedicaux().size());
        Assert.assertEquals(1, essai.getDetailProduit().getMedicaments().size());
        Assert.assertEquals(1, essai.getDetailProduit().getProduitsTherapeutiques().size());
    }
}
