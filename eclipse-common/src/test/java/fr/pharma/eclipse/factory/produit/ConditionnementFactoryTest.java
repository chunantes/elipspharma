package fr.pharma.eclipse.factory.produit;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.DispositifMedical;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.ProduitTherapeutique;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la fabrique de Conditionnement.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ConditionnementFactoryTest extends AbstractEclipseJUnitTest {
    /**
     * Fabrique testée.
     */
    private ConditionnementFactory factory;

    /**
     * Factory Spring mockée.
     */
    private BeanFactory mockedBeanFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedBeanFactory = Mockito.mock(BeanFactory.class);
        this.factory = new ConditionnementFactory(Conditionnement.class);
        this.factory.setBeanFactory(this.mockedBeanFactory);

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
    public void testInit() {
        Assert.assertNotNull(this.factory);
        Assert.assertNotNull(this.mockedBeanFactory);

    }

    /**
     * Test de la méthode getInitializedObject.
     */
    @Test
    public void testGetInitializedObjectDm() {
        final DispositifMedical dm = new DispositifMedical();
        dm.setId(1L);

        Mockito.when(this.mockedBeanFactory.getBean(Conditionnement.class.getSimpleName())).thenReturn(new Conditionnement());

        final Conditionnement result = this.factory.getInitializedObject(dm);
        Mockito.verify(this.mockedBeanFactory).getBean(Conditionnement.class.getSimpleName());

        Assert.assertEquals(dm.getId(), result.getProduit().getId());
    }

    /**
     * Test de la méthode getInitializedObject.
     */
    @Test
    public void testGetInitializedObjectMedicament() {
        final Medicament medicament = new Medicament();
        medicament.setId(1L);

        Mockito.when(this.mockedBeanFactory.getBean(Conditionnement.class.getSimpleName())).thenReturn(new Conditionnement());

        final Conditionnement result = this.factory.getInitializedObject(medicament);
        Mockito.verify(this.mockedBeanFactory).getBean(Conditionnement.class.getSimpleName());

        Assert.assertEquals(medicament.getId(), result.getProduit().getId());
    }

    /**
     * Test de la méthode getInitializedObject.
     */
    @Test
    public void testGetInitializedObjectThera() {
        final ProduitTherapeutique prod = new ProduitTherapeutique();
        prod.setId(1L);

        Mockito.when(this.mockedBeanFactory.getBean(Conditionnement.class.getSimpleName())).thenReturn(new Conditionnement());

        final Conditionnement result = this.factory.getInitializedObject(prod);
        Mockito.verify(this.mockedBeanFactory).getBean(Conditionnement.class.getSimpleName());

        Assert.assertEquals(prod.getId(), result.getProduit().getId());
    }

}
