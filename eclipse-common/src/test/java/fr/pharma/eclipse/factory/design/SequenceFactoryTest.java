package fr.pharma.eclipse.factory.design;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.enums.TypeDesignable;
import fr.pharma.eclipse.domain.model.design.Bras;
import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.design.DetailDesign;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la factory SequenceFactory.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class SequenceFactoryTest extends AbstractEclipseJUnitTest {

    /**
     * Factory de Sequence.
     */
    private SequenceFactory factory;

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
        this.factory = new SequenceFactory(Sequence.class);
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
     * Test de la méthdoe : getInitializedObject.
     */
    @Test
    public void testGetInitializedObject() {
        final Essai essai = new Essai();
        essai.setId(1L);

        final Bras b = new Bras();
        b.setId(1L);
        b.setNom("b");
        essai.setDetailDesign(new DetailDesign());
        essai.getDetailDesign().getBras().add(b);

        Mockito.when(this.mockedBeanFactory.getBean(Sequence.class.getSimpleName())).thenReturn(new Sequence());

        final Sequence result = this.factory.getInitializedObject(essai, "b");

        Assert.assertEquals(TypeDesignable.SEQUENCE, result.getType());
        Assert.assertEquals(b.getId(), result.getParent().getId());
    }

}
