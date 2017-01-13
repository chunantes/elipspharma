package fr.pharma.eclipse.component.design.helper;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.primefaces.model.TreeNode;

import fr.pharma.eclipse.comparator.design.DesignableComparator;
import fr.pharma.eclipse.domain.model.design.Bras;
import fr.pharma.eclipse.domain.model.design.Designable;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.design.DetailDesign;
import fr.pharma.eclipse.service.helper.design.DesignHelper;
import fr.pharma.eclipse.utils.EssaiUtils;

/**
 * Classe en charge de tester le helper d'arbre de design.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class TreeDesignHelperTest {
    /**
     * TreeDesignHelper à tester.
     */
    private TreeDesignHelper helper;

    /**
     * Helper mocké.
     */
    private DesignHelper mockedHelper;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.mockedHelper = Mockito.mock(DesignHelper.class);
        this.helper = new TreeDesignHelper();
        this.helper.setHelper(this.mockedHelper);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.helper = null;
        this.mockedHelper = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.helper);
        Assert.assertEquals(this.mockedHelper, this.helper.getHelper());
    }

    /**
     * Méthode en charge de tester la méthode de décomposition de noeud.
     */
    @Test
    public void testDecomposeNodesNotEmpty() {
        final String result = this.helper.decomposeNodes("0_1_2");
        Assert.assertEquals("treeDesign_0_1_2,treeDesign_0_1,treeDesign_0", result);
    }

    /**
     * Méthode en charge de tester la méthode de décomposition de noeud.
     */
    @Test
    public void testDecomposeNodesEmpty() {
        final String result = this.helper.decomposeNodes(StringUtils.EMPTY);
        Assert.assertEquals(StringUtils.EMPTY, result);
    }

    /**
     * Méthode en charge de tester la méthode de construction d'arbre.
     */
    @Test
    public void testBuildTreeWithDesignablesEmpty() {
        final DetailDesign design = new DetailDesign();
        final TreeNode tree = this.helper.buildTree(design);
        Assert.assertNotNull(tree);
    }

    /**
     * Méthode en charge de tester la méthode de construction d'arbre.
     */
    @Test
    public void testBuildTreeWithDesignablessNotEmpty() {
        final DetailDesign design = new DetailDesign();

        final SortedSet<Bras> designables = new TreeSet<Bras>(new DesignableComparator());

        final Bras parent = new Bras();
        parent.setNom("parent");
        final Bras enfant1 = new Bras();
        enfant1.setNom("enfant1");
        enfant1.setParent(parent);
        final Bras enfant2 = new Bras();
        enfant2.setNom("enfant2");
        enfant2.setParent(parent);
        parent.getSousBras().add(enfant1);
        parent.getSousBras().add(enfant2);

        designables.add(parent);
        designables.add(enfant1);
        designables.add(enfant2);

        design.setBras(designables);

        final TreeNode tree = this.helper.buildTree(design);
        Assert.assertNotNull(tree);
    }

    /**
     * Méthode en charge de tester la méthode de construction d'arbre.
     */
    @Test
    public void testBuildTreeWithBrassNotEmptyNLevel() {
        final DetailDesign design = new DetailDesign();
        final SortedSet<Bras> designables = new TreeSet<Bras>(new DesignableComparator());

        final Bras parent = new Bras();
        parent.setNom("parent");
        final Bras enfant1 = new Bras();
        enfant1.setNom("enfant1");
        enfant1.setParent(parent);
        final Bras enfant2 = new Bras();
        enfant2.setNom("enfant2");
        enfant2.setParent(parent);
        final Bras enfant11 = new Bras();
        enfant11.setNom("enfant11");
        enfant11.setParent(enfant1);

        enfant1.getSousBras().add(enfant11);
        parent.getSousBras().add(enfant1);
        parent.getSousBras().add(enfant2);

        designables.add(parent);
        designables.add(enfant1);
        designables.add(enfant2);
        designables.add(enfant11);

        design.setBras(designables);

        final TreeNode tree = this.helper.buildTree(design);
        Assert.assertNotNull(tree);
    }

    /**
     * Méthode en charge de tester le calcul des noeuds à ouvrir à l'affichage.
     */
    @Test
    public void testCalculateNodesToExpand1() {
        final DetailDesign design = new DetailDesign();
        final SortedSet<Bras> designables = new TreeSet<Bras>(new DesignableComparator());

        final Bras parent = new Bras();
        parent.setId(1L);
        parent.setNom("parent");
        final Bras enfant1 = new Bras();
        enfant1.setNom("enfant1");
        enfant1.setParent(parent);
        final Bras enfant2 = new Bras();
        enfant2.setNom("enfant2");
        enfant2.setParent(parent);
        final Bras enfant11 = new Bras();
        enfant11.setNom("enfant11");
        enfant11.setParent(enfant1);

        enfant1.getSousBras().add(enfant11);
        parent.getSousBras().add(enfant1);
        parent.getSousBras().add(enfant2);

        designables.add(parent);
        designables.add(enfant1);
        designables.add(enfant2);
        designables.add(enfant11);

        design.setBras(designables);
        final Set<Designable> expectedRoots = new HashSet<Designable>();
        expectedRoots.add(parent);
        Mockito.when(this.mockedHelper.getDesignRoots(design)).thenReturn(expectedRoots);

        final TreeNode tree = this.helper.buildTree(design);
        final String nodesToExpand = this.helper.calculateNodesToExpand(tree, enfant11);
        Mockito.verify(this.mockedHelper).getDesignRoots(design);
        Assert.assertEquals("treeDesign_0_0,treeDesign_0", nodesToExpand);
    }

    /**
     * Méthode en charge de tester le calcul des noeuds à ouvrir à l'affichage.
     */
    @Test
    public void testCalculateNodesToExpand2() {
        long id = 2;
        final DetailDesign design = new DetailDesign();
        final SortedSet<Bras> designables = new TreeSet<Bras>(new DesignableComparator());

        final Bras parent = new Bras();
        parent.setId(id++);
        parent.setNom("parent");
        final Bras enfant1 = new Bras();
        enfant1.setNom("enfant1");
        enfant1.setParent(parent);
        final Bras enfant2 = new Bras();
        enfant2.setNom("enfant2");
        enfant2.setParent(parent);
        final Bras enfant11 = new Bras();
        enfant11.setNom("enfant11");
        enfant11.setParent(enfant1);

        enfant1.getSousBras().add(enfant11);
        parent.getSousBras().add(enfant1);
        parent.getSousBras().add(enfant2);

        designables.add(parent);
        designables.add(enfant1);
        designables.add(enfant2);
        designables.add(enfant11);

        design.setBras(designables);
        final Set<Designable> expectedRoots = new HashSet<Designable>();
        expectedRoots.add(parent);
        Mockito.when(this.mockedHelper.getDesignRoots(design)).thenReturn(expectedRoots);

        final TreeNode tree = this.helper.buildTree(design);
        final String nodesToExpand = this.helper.calculateNodesToExpand(tree, enfant2);
        Mockito.verify(this.mockedHelper).getDesignRoots(design);
        Assert.assertEquals("treeDesign_0", nodesToExpand);
    }

    /**
     * Méthode en charge de tester le calcul des noeuds à ouvrir à l'affichage.
     */
    @Test
    public void testCalculateNodesToExpand3() {
        final DetailDesign design = new DetailDesign();
        final SortedSet<Bras> designables = new TreeSet<Bras>(new DesignableComparator());

        final Bras parent = new Bras();
        parent.setId(1L);
        parent.setNom("parent");
        final Bras enfant1 = new Bras();
        enfant1.setNom("enfant1");
        enfant1.setParent(parent);
        final Bras enfant11 = new Bras();
        enfant11.setNom("enfant11");
        enfant11.setParent(enfant1);
        final Bras enfant2 = new Bras();
        enfant2.setNom("enfant2");
        enfant2.setParent(parent);
        final Bras enfant21 = new Bras();
        enfant21.setNom("enfant21");
        enfant21.setParent(enfant2);

        enfant1.getSousBras().add(enfant11);
        enfant2.getSousBras().add(enfant21);
        parent.getSousBras().add(enfant1);
        parent.getSousBras().add(enfant2);

        designables.add(parent);
        designables.add(enfant1);
        designables.add(enfant2);
        designables.add(enfant11);
        designables.add(enfant21);

        design.setBras(designables);
        final Set<Designable> expectedRoots = new HashSet<Designable>();
        expectedRoots.add(parent);
        Mockito.when(this.mockedHelper.getDesignRoots(design)).thenReturn(expectedRoots);

        final TreeNode tree = this.helper.buildTree(design);
        final String nodesToExpand = this.helper.calculateNodesToExpand(tree, enfant2);
        Mockito.verify(this.mockedHelper).getDesignRoots(design);
        Assert.assertEquals("treeDesign_0_1,treeDesign_0", nodesToExpand);
    }

}
