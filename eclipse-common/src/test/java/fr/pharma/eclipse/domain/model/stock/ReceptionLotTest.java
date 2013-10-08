package fr.pharma.eclipse.domain.model.stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.stock.MotifRefus;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;

/**
 * Classe en charge de tester les méthodes de réception de lot.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ReceptionLotTest {
    /**
     * ReceptionLot à tester.
     */
    private ReceptionLot receptionLot;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.receptionLot = new ReceptionLot();
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.receptionLot = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.receptionLot);
    }

    /**
     * Méthode en charge de tester la maj du tableau des numéros de traitement
     * suite à la saisie du nombre de numéro de traitement.
     */
    @Test
    public void testHandleSaisieNbNumsTraitementsNotNull() {
        this.receptionLot.setNbNumerosTraitement(1);
        this.receptionLot.handleSaisieNbNumsTraitements();
        Assert.assertEquals(1, this.receptionLot.getNumsTraitements().size());
        final NumTraitement numTraitement = this.receptionLot.getNumsTraitements().get(0);
        Assert.assertNull(numTraitement.getNumTraitement());
        Assert.assertEquals(1, numTraitement.getQuantite().intValue());
    }

    /**
     * Méthode en charge de tester la maj du tableau des numéros de traitement
     * suite à la saisie du nombre de numéro de traitement.
     */
    @Test
    public void testHandleSaisieNbNumsTraitementsNull() {
        this.receptionLot.setNbNumerosTraitement(null);
        this.receptionLot.handleSaisieNbNumsTraitements();
        Assert.assertEquals(0, this.receptionLot.getNumsTraitements().size());
    }

    /**
     * Méthode en charge de tester l'ajout de numéro de traitement.
     */
    @Test
    public void testAddNumTraitement() {
        final List<NumTraitement> numsTraitements = new ArrayList<NumTraitement>();
        this.receptionLot.setNumsTraitements(numsTraitements);
        this.receptionLot.setNbNumerosTraitement(0);
        this.receptionLot.addNumTraitement();
        Assert.assertEquals(1, this.receptionLot.getNumsTraitements().size());
        Assert.assertEquals(1, this.receptionLot.getNbNumerosTraitement().intValue());
    }

    /**
     * Méthode en charge de tester la suppression de numéro de traitement.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testDelNumTraitement() {
        final List<NumTraitement> numsTraitements = new ArrayList<NumTraitement>();
        final NumTraitement numTraitement = new NumTraitement();
        numTraitement.setQuantite(1);
        numsTraitements.add(numTraitement);
        final ActionEvent event = Mockito.mock(ActionEvent.class);
        final UIComponent component = Mockito.mock(UIComponent.class);
        Mockito.when(event.getComponent()).thenReturn(component);
        final Map<String, Object> map = Mockito.mock(Map.class);
        Mockito.when(component.getAttributes()).thenReturn(map);
        Mockito.when(map.get("numTraitementToDelete")).thenReturn(numTraitement);
        this.receptionLot.setNumsTraitements(numsTraitements);
        this.receptionLot.setNbNumerosTraitement(1);
        this.receptionLot.delNumTraitement(event);
        Assert.assertEquals(0, this.receptionLot.getNumsTraitements().size());
        Assert.assertEquals(0, this.receptionLot.getNbNumerosTraitement().intValue());
    }

    /**
     * Méthode en charge de tester la maj des conditionnements suite à la
     * sélection d'un conditionnement.
     */
    @Test
    public void testHandleSelectConditionnementNull() {
        final Approvisionnement approvisionnement = new Approvisionnement();
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu select = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(select);
        Mockito.when(select.getLocalValue()).thenReturn(null);
        this.receptionLot.setAppro(approvisionnement);
        this.receptionLot.handleSelectConditionnement(event);
        Assert.assertNull(this.receptionLot.getAppro().getConditionnement());
    }

    /**
     * Méthode en charge de tester la maj des conditionnements suite à la
     * sélection d'un conditionnement.
     */
    @Test
    public void testHandleSelectConditionnement() {
        final Approvisionnement approvisionnement = new Approvisionnement();
        final Conditionnement conditionnement = new Conditionnement();
        approvisionnement.setConditionnement(conditionnement);
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu select = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(select);
        Mockito.when(select.getLocalValue()).thenReturn(conditionnement);
        this.receptionLot.setAppro(approvisionnement);
        this.receptionLot.handleSelectConditionnement(event);
        Assert.assertNotNull(this.receptionLot.getAppro().getConditionnement());
    }

    /**
     * Méthode en charge de tester la gestion de la sélection d'un état de
     * réception.
     */
    @Test
    public void testHandleSelectEtatReceptionApprouve() {
        final Approvisionnement appro = new Approvisionnement();
        this.receptionLot.setAppro(appro);
        appro.setApproApprouve(Boolean.TRUE);
        this.receptionLot.handleSelectEtatReception();
        Assert.assertNull(appro.getMotifRefus());
        Assert.assertNull(appro.getCommentaireRefus());
    }

    /**
     * Méthode en charge de tester la gestion de la sélection d'un état de
     * réception.
     */
    @Test
    public void testHandleSelectEtatReceptionRejete() {
        final Approvisionnement appro = new Approvisionnement();
        this.receptionLot.setAppro(appro);
        appro.setApproApprouve(Boolean.FALSE);
        appro.setCommentaireRefus("commentaireRefus");
        appro.setMotifRefus(MotifRefus.PB_TEMPERATURE);
        this.receptionLot.handleSelectEtatReception();
        Assert.assertNotNull(appro.getMotifRefus());
        Assert.assertNotNull(appro.getCommentaireRefus());
    }

    /**
     * Méthode en charge de tester le cumul des quantités.
     */
    @Test
    public void testGetQteCumulNumsTraitements() {
        final NumTraitement numTraitement1 = new NumTraitement();
        final NumTraitement numTraitement2 = new NumTraitement();
        numTraitement2.setNumTraitement("numTraitement2");
        final NumTraitement numTraitement3 = new NumTraitement();
        numTraitement3.setQuantite(Integer.valueOf(1));
        final NumTraitement numTraitement4 = new NumTraitement();
        numTraitement4.setNumTraitement("numTraitement4");
        numTraitement4.setQuantite(2);

        final List<NumTraitement> numsTraitements = new ArrayList<NumTraitement>();
        numsTraitements.add(numTraitement1);
        numsTraitements.add(numTraitement2);
        numsTraitements.add(numTraitement3);
        numsTraitements.add(numTraitement4);
        this.receptionLot.setNumsTraitements(numsTraitements);

        final Integer result = this.receptionLot.getQteCumulNumsTraitements();

        Assert.assertEquals(2, result.intValue());
    }

}
