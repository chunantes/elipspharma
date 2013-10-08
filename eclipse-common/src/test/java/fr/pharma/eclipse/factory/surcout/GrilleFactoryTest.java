package fr.pharma.eclipse.factory.surcout;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.model.surcout.Categorie;
import fr.pharma.eclipse.domain.model.surcout.Grille;
import fr.pharma.eclipse.domain.model.surcout.GrilleModele;
import fr.pharma.eclipse.domain.model.surcout.Item;
import fr.pharma.eclipse.domain.model.surcout.Theme;
import fr.pharma.eclipse.factory.common.BeanObjectFactory;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la factory GrilleFactory.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GrilleFactoryTest extends AbstractEclipseJUnitTest {

    /**
     * Factory à tester.
     */
    private GrilleFactory factory;

    /**
     * Factory Spring mockée.
     */
    private BeanFactory mockedBeanFactory;

    /**
     * Factory d'item.
     */
    private BeanObjectFactory<Item> itemFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedBeanFactory = Mockito.mock(BeanFactory.class);
        this.itemFactory = Mockito.mock(BeanObjectFactory.class);
        this.factory = new GrilleFactory(Grille.class);
        this.factory.setBeanFactory(this.mockedBeanFactory);
        this.factory.setItemFactory(this.itemFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.factory = null;
        this.mockedBeanFactory = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.factory);
        Assert.assertNotNull(this.mockedBeanFactory);
    }

    /**
     * Test de la méthode getInitializedObject(GrilleModele).
     */
    @Test
    public void testGetInitializedObject() {

        Mockito.when(this.mockedBeanFactory.getBean(Grille.class.getSimpleName())).thenReturn(new Grille());

        Mockito.when(this.itemFactory.getInitializedObject()).thenReturn(new Item(), new Item(), new Item(), new Item(), new Item());
        final Grille grille = this.factory.getInitializedObject(this.initGrilleModele());
        Assert.assertEquals(5, grille.getItems().size());
    }
    /**
     * Initialise une grille modèle.
     * @return grille modèle.
     */
    private GrilleModele initGrilleModele() {
        final GrilleModele grille = new GrilleModele();

        final Theme t1 = new Theme();
        t1.setLibelle("theme 1");
        final Theme t2 = new Theme();
        t2.setLibelle("theme 2");
        final Theme t3 = new Theme();
        t3.setLibelle("theme categorie");

        final Categorie c11 = new Categorie();
        final Categorie c12 = new Categorie();
        final Categorie c21 = new Categorie();
        final Categorie c22 = new Categorie();

        c11.setLibelle("categorie 1 - 1");
        c12.setLibelle("categorie 1 - 2");
        c21.setLibelle("categorie 2 - 1");
        c22.setLibelle("categorie 2 - 2");

        t1.getCategories().add(c11);
        t1.getCategories().add(c12);
        t2.getCategories().add(c21);
        t2.getCategories().add(c22);

        grille.getThemes().add(t1);
        grille.getThemes().add(t2);
        grille.getThemes().add(t3);
        return grille;
    }
}
