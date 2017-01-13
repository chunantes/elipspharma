package fr.pharma.eclipse.jasper.engine.filler.impl.common;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensation;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart2;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.jasper.engine.factory.JRDataSourceFactory;
import fr.pharma.eclipse.jasper.engine.factory.bean.JasperReportBeanFactory;
import fr.pharma.eclipse.jasper.engine.filler.JasperReportBeanFiller;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;
import fr.pharma.eclipse.utils.introspection.BeanTool;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * Test de la classe {@link CommonJasperReportBeanFiller}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class CommonJasperReportBeanFillerTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private CommonJasperReportBeanFiller filler;

    /**
     * Fabrique mockée de JRDataSource.
     */
    private JRDataSourceFactory mockedDSFactory;

    /**
     * Fabrique mockée de bean JasperReportBean.
     */
    private JasperReportBeanFactory mockedJRbeanFactory;

    /**
     * SubFiller mocké.
     */
    private JasperReportBeanFiller mockedSubFiller;

    /**
     * Propriété à valoriser.
     */
    private final static String PROPERTY_TO_SET = "partie2";

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedDSFactory = Mockito.mock(JRDataSourceFactory.class);
        this.mockedJRbeanFactory = Mockito.mock(JasperReportBeanFactory.class);
        this.mockedSubFiller = Mockito.mock(JasperReportBeanFiller.class);
        this.filler = new CommonJasperReportBeanFiller(CommonJasperReportBeanFillerTest.PROPERTY_TO_SET);
        this.filler.setJrBeanFactory(this.mockedJRbeanFactory);
        this.filler.setJrDataSourceFactory(this.mockedDSFactory);
        this.filler.setSubFillers(Arrays.asList(this.mockedSubFiller));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.filler = null;
        this.mockedDSFactory = null;
        this.mockedJRbeanFactory = null;
        this.mockedSubFiller = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.filler);
        Assert.assertEquals(CommonJasperReportBeanFillerTest.PROPERTY_TO_SET, this.filler.getPropertyToSet());
        Assert.assertEquals(this.mockedDSFactory, this.filler.getJrDataSourceFactory());
        Assert.assertEquals(this.mockedJRbeanFactory, this.filler.getJrBeanFactory());
        Assert.assertEquals(1, this.filler.getSubFillers().size());
        Assert.assertEquals(this.mockedSubFiller, this.filler.getSubFillers().get(0));
    }

    /**
     * Test de la méthode fill(fr.pharma.eclipse.domain.model.essai.Essai,
     * fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean).
     */
    @Test
    public void testFill() {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final JasperReportBean bean = new JRBeanFicheAideDispensation();
        final JRBeanFicheAideDispensationPart2 subBean = new JRBeanFicheAideDispensationPart2();
        final JRDataSource dataSource = Mockito.mock(JRDataSource.class);

        Assert.assertTrue(BeanTool.hasProperty(bean, CommonJasperReportBeanFillerTest.PROPERTY_TO_SET));
        Assert.assertNull(BeanTool.getPropriete(bean, CommonJasperReportBeanFillerTest.PROPERTY_TO_SET));

        Mockito.when(this.mockedJRbeanFactory.getInitializedObject()).thenReturn(subBean);
        Mockito.when(this.mockedDSFactory.getInitializedObject(subBean)).thenReturn(dataSource);

        this.filler.fill(essai, bean);

        Mockito.verify(this.mockedJRbeanFactory).getInitializedObject();
        Mockito.verify(this.mockedSubFiller).fill(essai, subBean);
        Mockito.verify(this.mockedDSFactory).getInitializedObject(subBean);

        Assert.assertEquals(dataSource, BeanTool.getPropriete(bean, CommonJasperReportBeanFillerTest.PROPERTY_TO_SET));
    }
}
