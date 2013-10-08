package fr.pharma.eclipse.component.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.faces.model.DataModel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.component.wrapper.SelectableBean;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.suivi.essai.EssaiSuivi;
import fr.pharma.eclipse.service.helper.common.BeanHelper;
import fr.pharma.eclipse.utils.EssaiUtils;

/**
 * Test de la classe BeanManagerHelper.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class BeanManagerHelperTest {
    /**
     * Classe testée.
     */
    private BeanManagerHelper<Essai> helper;

    /**
     * Mock du helper commun de la couche service.
     */
    private BeanHelper<Essai> mockedBeanHelper;

    /**
     * Méthode d'initialisation.
     */
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        this.mockedBeanHelper = Mockito.mock(BeanHelper.class);
        this.helper = new BeanManagerHelper<Essai>();
        this.helper.setBeanHelper(this.mockedBeanHelper);
    }

    /**
     * Méthode de finalisation.
     */
    @After
    public void tearDown() {
        this.helper = null;
        this.mockedBeanHelper = null;
    }

    /**
     * Test de la méthode returnAsDataModel.
     */
    @Test
    public void testReturnAsDataModel() {
        long id = 1;
        final List<Essai> essais = Arrays.asList(EssaiUtils.makeEssaiTest(id++), EssaiUtils.makeEssaiTest(id++), EssaiUtils.makeEssaiTest(id++));
        final DataModel<Essai> dataModel = this.helper.returnAsDataModel(essais);
        Assert.assertNotNull(dataModel);
        Assert.assertEquals(essais.size(), dataModel.getRowCount());
    }

    /**
     * Test de la méthode updateSelectable.
     */
    @Test
    public void testUpdateSelectable() {
        Long nextId = 1L;
        final String baseNom = "pharma_";
        final Pharmacie pharmacie1 = new Pharmacie();
        final Pharmacie pharmacie2 = new Pharmacie();
        final Pharmacie pharmacie3 = new Pharmacie();
        pharmacie1.setId(nextId++);
        pharmacie2.setId(nextId++);
        pharmacie3.setId(nextId++);
        pharmacie1.setNom(baseNom + pharmacie1.getId());
        pharmacie2.setNom(baseNom + pharmacie2.getId());
        pharmacie3.setNom(baseNom + pharmacie3.getId());

        final SortedSet<Pharmacie> selected = new TreeSet<Pharmacie>(new BeanWithNomComparator());
        selected.add(pharmacie1);
        final List<Pharmacie> selectable = Arrays.asList(pharmacie1, pharmacie2, pharmacie3);

        this.helper.updateSelectable(selected, selectable);

        Assert.assertTrue(pharmacie1.getSelected());
        Assert.assertFalse(pharmacie2.getSelected());
        Assert.assertFalse(pharmacie3.getSelected());
    }

    /**
     * Test de la méthode updateSelectableBeans.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testUpdateSelectableBeans() {
        Long nextId = 1L;
        final String baseNom = "pharma_";
        final Pharmacie pharmacie1 = new Pharmacie();
        final Pharmacie pharmacie2 = new Pharmacie();
        final Pharmacie pharmacie3 = new Pharmacie();
        pharmacie1.setId(nextId++);
        pharmacie2.setId(nextId++);
        pharmacie3.setId(nextId++);
        pharmacie1.setNom(baseNom + pharmacie1.getId());
        pharmacie2.setNom(baseNom + pharmacie2.getId());
        pharmacie3.setNom(baseNom + pharmacie3.getId());

        final SortedSet<Pharmacie> selected = new TreeSet<Pharmacie>(new BeanWithNomComparator());
        selected.add(pharmacie1);
        final SelectableBean<Pharmacie> wrapper1 = new SelectableBean<Pharmacie>(pharmacie1);
        final SelectableBean<Pharmacie> wrapper2 = new SelectableBean<Pharmacie>(pharmacie2);
        final SelectableBean<Pharmacie> wrapper3 = new SelectableBean<Pharmacie>(pharmacie3);
        final List<SelectableBean<Pharmacie>> selectable = Arrays.asList(wrapper1, wrapper2, wrapper3);

        this.helper.updateSelectableWrapped(selected, selectable);

        Assert.assertFalse(pharmacie1.getSelected());
        Assert.assertFalse(pharmacie2.getSelected());
        Assert.assertFalse(pharmacie3.getSelected());
        Assert.assertTrue(wrapper1.getSelected());
        Assert.assertFalse(wrapper2.getSelected());
        Assert.assertFalse(wrapper3.getSelected());
    }

    /**
     * Test de la méthode updateSelected.
     */
    @Test
    public void testUpdateSelected() {
        Long nextId = 1L;
        final String baseNom = "pharma_";
        final Pharmacie pharmacie1 = new Pharmacie();
        final Pharmacie pharmacie2 = new Pharmacie();
        final Pharmacie pharmacie3 = new Pharmacie();
        pharmacie1.setId(nextId++);
        pharmacie2.setId(nextId++);
        pharmacie3.setId(nextId++);
        pharmacie1.setNom(baseNom + pharmacie1.getId());
        pharmacie2.setNom(baseNom + pharmacie2.getId());
        pharmacie3.setNom(baseNom + pharmacie3.getId());

        final List<Pharmacie> selectable = Arrays.asList(pharmacie1, pharmacie2, pharmacie3);
        final SortedSet<Pharmacie> selected = new TreeSet<Pharmacie>(new BeanWithNomComparator());
        selected.add(pharmacie1);

        pharmacie1.setSelected(false);
        pharmacie2.setSelected(false);
        pharmacie3.setSelected(true);

        selected.add(pharmacie1);
        this.helper.updateSelected(selected, selectable);

        Assert.assertEquals(1, selected.size());
        Assert.assertEquals(pharmacie3, selected.first());
    }

    /**
     * Test de la méthode updateSelectedWrapped.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testUpdateSelectedWrapped() {
        Long nextId = 1L;
        final String baseNom = "pharma_";
        final Pharmacie pharmacie1 = new Pharmacie();
        final Pharmacie pharmacie2 = new Pharmacie();
        final Pharmacie pharmacie3 = new Pharmacie();
        pharmacie1.setId(nextId++);
        pharmacie2.setId(nextId++);
        pharmacie3.setId(nextId++);
        pharmacie1.setNom(baseNom + pharmacie1.getId());
        pharmacie2.setNom(baseNom + pharmacie2.getId());
        pharmacie3.setNom(baseNom + pharmacie3.getId());

        final SelectableBean<Pharmacie> wrapper1 = new SelectableBean<Pharmacie>(pharmacie1);
        final SelectableBean<Pharmacie> wrapper2 = new SelectableBean<Pharmacie>(pharmacie2);
        final SelectableBean<Pharmacie> wrapper3 = new SelectableBean<Pharmacie>(pharmacie3);
        final List<SelectableBean<Pharmacie>> selectable = Arrays.asList(wrapper1, wrapper2, wrapper3);
        final SortedSet<Pharmacie> selected = new TreeSet<Pharmacie>(new BeanWithNomComparator());
        selected.add(pharmacie1);

        wrapper1.setSelected(false);
        wrapper2.setSelected(false);
        wrapper3.setSelected(true);

        selected.add(pharmacie1);
        this.helper.updateSelectedWrapped(selected, selectable);

        Assert.assertEquals(1, selected.size());
        Assert.assertEquals(pharmacie3, selected.first());
    }

    /**
     * Test de la méthode addToCollection.
     */
    @Test
    public void testAddToCollection() {
        long ids = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(ids++, null);
        final EssaiSuivi suivi = Mockito.mock(EssaiSuivi.class);
        Assert.assertTrue(essai.getModifs().isEmpty());
        final String beanCollectionProperty = "modifs";
        this.helper.addToCollection(essai, beanCollectionProperty, suivi);
        Mockito.verify(this.mockedBeanHelper).addToCollection(essai, beanCollectionProperty, suivi);
    }

    /**
     * Test de la méthode getFirstOfCollection - pas de premier élément.
     */
    @Test
    public void testGetFirstOfCollectionKo() {
        Assert.assertNull(this.helper.getFirstOfCollection(null));
        Assert.assertNull(this.helper.getFirstOfCollection(new ArrayList<Essai>()));
    }

    /**
     * Test de la méthode getFirstOfCollection.
     */
    @Test
    public void testGetFirstOfCollectionOk() {
        long ids = 1;
        final List<Essai> essais = new ArrayList<Essai>();
        final Essai expectedEssai = EssaiUtils.makeEssaiTest(ids++);
        essais.add(expectedEssai);
        essais.add(EssaiUtils.makeEssaiTest(ids++));
        Assert.assertEquals(expectedEssai, this.helper.getFirstOfCollection(essais));
    }

    /**
     * Test de la méthode getBeansSelected.
     */
    @Test
    public void testGetBeansSelected() {
        long id = 1;
        final Essai essai1 = EssaiUtils.makeEssaiTest(id++);
        final Essai essai2 = EssaiUtils.makeEssaiTest(id++);
        final Essai essai3 = EssaiUtils.makeEssaiTest(id++);
        essai1.setSelected(false);
        essai2.setSelected(true);
        essai3.setSelected(false);

        final List<Essai> res = this.helper.getBeansSelected(Arrays.asList(essai1, essai2, essai3));
        Assert.assertNotNull(res);
        Assert.assertEquals(1, res.size());
        Assert.assertTrue(res.contains(essai2));
        Assert.assertFalse(res.contains(essai1));
        Assert.assertFalse(res.contains(essai3));
    }
}
