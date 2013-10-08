package fr.pharma.eclipse.factory.design;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.enums.TypeDesignable;
import fr.pharma.eclipse.domain.model.design.Bras;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.design.DetailDesign;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe BrasFactory.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class BrasFactoryTest extends AbstractEclipseJUnitTest {

    /**
     * Factory de bras.
     */
    private BrasFactory factory;

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
        this.factory = new BrasFactory(Bras.class);
        this.factory.setBeanFactory(this.mockedBeanFactory);
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
     * Test de la méthode getInitializedObject.
     */
    @Test
    public void testGetInitialisedObject() {

        final Essai essai = new Essai();
        final DetailDesign design = new DetailDesign();
        design.setId(1L);
        essai.setDetailDesign(design);
        Mockito.when(this.mockedBeanFactory.getBean(Bras.class.getSimpleName())).thenReturn(new Bras());

        final Bras result = this.factory.getInitializedObject(essai);

        Assert.assertEquals(design.getId(), result.getDetailDesign().getId());
        Assert.assertEquals(1, design.getBras().size());
        Assert.assertEquals(TypeDesignable.BRAS, result.getType());

    }

}
