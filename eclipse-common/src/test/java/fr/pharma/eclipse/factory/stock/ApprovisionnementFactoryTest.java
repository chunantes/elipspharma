package fr.pharma.eclipse.factory.stock;

import java.util.Calendar;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Test de la fabrique de Approvisionnement.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ApprovisionnementFactoryTest {
    /**
     * Fabrique testée.
     */
    private ApprovisionnementFactory factory;

    /**
     * Factory Spring mockée.
     */
    private BeanFactory mockedBeanFactory;

    /**
     * Méthode d'initialisation.
     */
    @Before
    public void init() {
        this.mockedBeanFactory = Mockito.mock(BeanFactory.class);
        this.factory = new ApprovisionnementFactory(Approvisionnement.class);
        this.factory.setBeanFactory(this.mockedBeanFactory);
    }

    /**
     * Méthode de finalisation.
     */
    @After
    public void end() {
        this.factory = null;
        this.mockedBeanFactory = null;
    }

    /**
     * Test de la méthode getInitializedObject.
     */
    @Test
    public void testGetInitializedObject() {
        final Approvisionnement expectedApprovisionnement = Mockito.mock(Approvisionnement.class);
        final String expectedClassName = Approvisionnement.class.getSimpleName();
        Mockito.when(this.mockedBeanFactory.getBean(expectedClassName)).thenReturn(expectedApprovisionnement);

        final Approvisionnement created = this.factory.getInitializedObject();
        Mockito.verify(this.mockedBeanFactory).getBean(expectedClassName);
        Assert.assertEquals(expectedApprovisionnement, created);
        Mockito.verify(expectedApprovisionnement).setType(TypeMvtStock.APPROVISIONNEMENT);
    }

    /**
     * Test de la méthode getInitializedObject.
     */
    @Test
    public void testGetInitializedObjectWithParams() {
        final Approvisionnement expectedApprovisionnement = Mockito.mock(Approvisionnement.class);
        final String expectedClassName = Approvisionnement.class.getSimpleName();
        Mockito.when(this.mockedBeanFactory.getBean(expectedClassName)).thenReturn(expectedApprovisionnement);

        final Essai essai = Mockito.mock(Essai.class);
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        final Calendar dateReception = Mockito.mock(Calendar.class);
        final Calendar dateArriveeColis = Mockito.mock(Calendar.class);

        final Approvisionnement created = this.factory.getInitializedObject(essai, pharmacie, dateReception, dateArriveeColis);
        Mockito.verify(this.mockedBeanFactory).getBean(expectedClassName);
        Assert.assertEquals(expectedApprovisionnement, created);
        Mockito.verify(expectedApprovisionnement).setType(TypeMvtStock.APPROVISIONNEMENT);
    }

}
