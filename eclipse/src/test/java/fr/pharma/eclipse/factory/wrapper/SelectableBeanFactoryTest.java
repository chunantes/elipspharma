package fr.pharma.eclipse.factory.wrapper;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.component.wrapper.SelectableBean;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.PharmacieUtils;

/**
 * Test de la classe {@link SelectableBeanFactory}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class SelectableBeanFactoryTest extends AbstractEclipseJUnitTest {
    /**
     * Fabrique testée.
     */
    private SelectableBeanFactory<Pharmacie> factory;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.factory = new SelectableBeanFactory<Pharmacie>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.factory = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.factory);
    }

    /**
     * Test de la méthode getInitializedObject(BEAN).
     */
    @Test
    public void testGetInitializedObjectBEAN() {
        final Pharmacie pharmacie = PharmacieUtils.makePharmacieTest(1);
        final SelectableBean<Pharmacie> wrapper = this.factory.getInitializedObject(pharmacie);
        this.verifyWrapper(wrapper, pharmacie);
    }

    /**
     * Test de la méthode getInitializedObject(ListOfBEAN).
     */
    @Test
    public void testGetInitializedObjectListOfBEAN() {
        long id = 1;
        final List<Pharmacie> pharmacies = Arrays.asList(PharmacieUtils.makePharmacieTest(id++), PharmacieUtils.makePharmacieTest(id++), PharmacieUtils.makePharmacieTest(id++));
        final List<SelectableBean<Pharmacie>> wrappers = this.factory.getInitializedObjects(pharmacies);
        Assert.assertEquals(pharmacies.size(), wrappers.size());
        for (int i = 0; i < pharmacies.size(); i++) {
            this.verifyWrapper(wrappers.get(i), pharmacies.get(i));
        }
    }
    /**
     * Méthode de vérification d'un wrapper.
     * @param wrapper Wrapper créé par lé fabrique.
     * @param pharmacie Pharmacie attendue dans le wrapper.
     */
    private void verifyWrapper(final SelectableBean<Pharmacie> wrapper,
                               final Pharmacie pharmacie) {
        Assert.assertNotNull(wrapper);
        Assert.assertFalse(wrapper.getSelected());
        Assert.assertEquals(pharmacie, wrapper.getBean());
    }

}
