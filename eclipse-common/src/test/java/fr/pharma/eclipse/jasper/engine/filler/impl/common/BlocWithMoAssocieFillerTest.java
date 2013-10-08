package fr.pharma.eclipse.jasper.engine.filler.impl.common;

import java.util.Arrays;
import java.util.Collection;

import net.sf.jasperreports.engine.JRDataSource;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import fr.pharma.eclipse.domain.jasper.model.common.JRBeanBlocWithMoAssocie;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart2;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.jasper.engine.builder.helper.common.JRBeanBlocWithMoAssocieBuilder;
import fr.pharma.eclipse.jasper.engine.factory.JRDataSourceFactory;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;

/**
 * Test de la classe {@link BlocWithMoAssocieFiller}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class BlocWithMoAssocieFillerTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private BlocWithMoAssocieFiller filler;

    /**
     * Fabrique mockée de JRDataSource.
     */
    private JRDataSourceFactory mockedFactory;

    /**
     * Helper mocké.
     */
    private JRBeanBlocWithMoAssocieBuilder mockedHelper;

    /**
     * Propriété que valorise le filler.
     */
    private static final String PROPERTY_TO_SET = "actesPharmacies";

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedFactory = Mockito.mock(JRDataSourceFactory.class);
        this.mockedHelper = Mockito.mock(JRBeanBlocWithMoAssocieBuilder.class);
        this.filler = new BlocWithMoAssocieFiller(BlocWithMoAssocieFillerTest.PROPERTY_TO_SET);
        this.filler.setJrDataSourceFactory(this.mockedFactory);
        this.filler.setHelpers(Arrays.asList(this.mockedHelper));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.filler = null;
        this.mockedFactory = null;
        this.mockedHelper = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.filler);
        Assert.assertEquals(BlocWithMoAssocieFillerTest.PROPERTY_TO_SET, this.filler.getPropertyToSet());
        Assert.assertEquals(this.mockedFactory, this.filler.getJrDataSourceFactory());
        Assert.assertEquals(1, this.filler.getHelpers().size());
        Assert.assertEquals(this.mockedHelper, this.filler.getHelpers().get(0));
    }

    /**
     * Test de la méthode fill(fr.pharma.eclipse.domain.model.essai.Essai,
     * fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean).
     */
    @Test
    public void testFill() {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final JRBeanFicheAideDispensationPart2 jrPart2 = new JRBeanFicheAideDispensationPart2();
        Assert.assertNull(jrPart2.getActesPharmacies());

        final JRBeanBlocWithMoAssocie expectedJrActePharma = Mockito.mock(JRBeanBlocWithMoAssocie.class);
        final JRDataSource expectedRes = Mockito.mock(JRDataSource.class);
        Mockito.when(this.mockedHelper.build(essai)).thenReturn(expectedJrActePharma);
        Mockito.when(this.mockedFactory.getInitializedObject(Matchers.anyCollectionOf(JRBeanBlocWithMoAssocie.class))).thenAnswer(new Answer<JRDataSource>() {

            @SuppressWarnings("unchecked")
            @Override
            public JRDataSource answer(final InvocationOnMock invocation) throws Throwable {
                final Collection<JRBeanBlocWithMoAssocie> argColl = (Collection<JRBeanBlocWithMoAssocie>) invocation.getArguments()[0];
                Assert.assertEquals(1, argColl.size());
                Assert.assertEquals(expectedJrActePharma, argColl.iterator().next());
                return expectedRes;
            }
        });

        this.filler.fill(essai, jrPart2);

        Mockito.verify(this.mockedHelper).build(essai);
        Mockito.verify(this.mockedFactory).getInitializedObject(Matchers.anyCollectionOf(JRBeanBlocWithMoAssocie.class));
        Assert.assertEquals(expectedRes, jrPart2.getActesPharmacies());

    }
}
