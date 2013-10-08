package fr.pharma.eclipse.factory.produit;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.enums.produit.TypeDetailStockage;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.detail.DetailLogistique;
import fr.pharma.eclipse.domain.model.produit.detail.DetailStockage;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la fabrique de DetailStockage.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DetailStockageFactoryTest extends AbstractEclipseJUnitTest {
    /**
     * Fabrique testée.
     */
    private DetailStockageFactory<DetailStockage> factory;

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
        this.factory = new DetailStockageFactory<DetailStockage>(DetailStockage.class);
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
        final Medicament produit = new Medicament();
        final DetailLogistique detail = new DetailLogistique();
        detail.setId(1L);
        produit.setDetailLogistique(detail);

        Mockito.when(this.mockedBeanFactory.getBean(DetailStockage.class.getSimpleName())).thenReturn(new DetailStockage());

        final DetailStockage result = this.factory.getInitializedObject(produit, TypeDetailStockage.STOCK);
        Mockito.verify(this.mockedBeanFactory).getBean(DetailStockage.class.getSimpleName());

        Assert.assertEquals(detail.getId(), result.getDetailLogistique().getId());
        Assert.assertEquals(TypeDetailStockage.STOCK, result.getType());
    }

}
