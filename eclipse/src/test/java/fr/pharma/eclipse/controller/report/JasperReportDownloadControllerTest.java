package fr.pharma.eclipse.controller.report;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import fr.pharma.eclipse.domain.enums.jasper.TypeRapportJasper;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.factory.primefaces.DefaultStreamedContentFactory;
import fr.pharma.eclipse.jasper.engine.manager.JasperReportBuildManager;
import fr.pharma.eclipse.jasper.exception.JasperReportBuildException;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;

/**
 * Test de la classe {@link JasperReportDownloadController}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JasperReportDownloadControllerTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private JasperReportDownloadController controller;

    /**
     * Fabrique mockée des objets StreamedContent.
     */
    private DefaultStreamedContentFactory mockedStreamedContentFactory;

    /**
     * Manager de build de jasper.
     */
    private JasperReportBuildManager mockedBuildManager;

    /**
     * Type de rapport supporté.
     */
    private static final TypeRapportJasper TYPE_RAPPORT = TypeRapportJasper.FICHE_INFO_ESSAI;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedStreamedContentFactory = Mockito.mock(DefaultStreamedContentFactory.class);
        this.mockedBuildManager = Mockito.mock(JasperReportBuildManager.class);
        this.controller = new JasperReportDownloadController();
        this.controller.setStreamedContentFactory(this.mockedStreamedContentFactory);
        this.controller.setBuildManagers(new HashMap<String, JasperReportBuildManager>());
        this.controller.getBuildManagers().put(JasperReportDownloadControllerTest.TYPE_RAPPORT.name(), this.mockedBuildManager);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.controller = null;
        this.mockedStreamedContentFactory = null;
        this.mockedBuildManager = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.controller);
        Assert.assertEquals(this.mockedStreamedContentFactory, this.controller.getStreamedContentFactory());
        Assert.assertEquals(this.mockedBuildManager, this.controller.getBuildManagers().get(JasperReportDownloadControllerTest.TYPE_RAPPORT.name()));
    }

    /**
     * Test de la méthode
     * downloadRapport(fr.pharma.eclipse.domain.enums.jasper.TypeRapportJasper).
     * .
     */
    @Test
    public void testDownloadRapportTypeRapportJasperOk() {
        final String expectedFileName =
            JasperReportDownloadControllerTest.TYPE_RAPPORT.getReportName() + JasperReportDownloadControllerTest.TYPE_RAPPORT.getTypeExport().getExtension();
        final Essai source = EssaiUtils.makeEssaiTest(1);
        try {
            final byte[] expectedBytes = new byte[]{1, 5, 3 };
            final DefaultStreamedContent expectedRes = Mockito.mock(DefaultStreamedContent.class);
            Mockito.when(this.mockedBuildManager.build(source)).thenReturn(expectedBytes);
            Mockito.when(this.mockedBuildManager.buildFileName(source)).thenReturn(expectedFileName);
            Mockito.when(this.mockedStreamedContentFactory.getInitializedObject(expectedBytes, expectedFileName)).thenReturn(expectedRes);
            final StreamedContent actualRes = this.controller.downloadRapport(source, JasperReportDownloadControllerTest.TYPE_RAPPORT);
            Mockito.verify(this.mockedBuildManager).build(source);
            Mockito.verify(this.mockedBuildManager).buildFileName(source);
            Mockito.verify(this.mockedStreamedContentFactory).getInitializedObject(expectedBytes, expectedFileName);
            Assert.assertEquals(expectedRes, actualRes);
        } catch (final JasperReportBuildException e) {
            Assert.fail("Erreur de paramétrage des mocks");
        }
    }

    /**
     * Test de la méthode
     * downloadRapport(fr.pharma.eclipse.domain.enums.jasper.TypeRapportJasper)
     * - exception levée.
     */
    @Test
    public void testDownloadRapportTypeRapportJasperKo() {
        final Essai source = EssaiUtils.makeEssaiTest(1);
        try {
            final DefaultStreamedContent expectedRes = Mockito.mock(DefaultStreamedContent.class);
            final Throwable expectedException = Mockito.mock(JasperReportBuildException.class);
            Mockito.when(this.mockedBuildManager.build(source)).thenThrow(expectedException);
            Mockito.when(this.mockedStreamedContentFactory.getInitializedObjectInError()).thenReturn(expectedRes);
            final StreamedContent actualRes = this.controller.downloadRapport(source, JasperReportDownloadControllerTest.TYPE_RAPPORT);
            Mockito.verify(this.mockedBuildManager).build(source);
            Mockito.verify(this.mockedStreamedContentFactory).getInitializedObjectInError();
            Assert.assertEquals(expectedRes, actualRes);
            Assert.fail("JasperReportBuildException is expected");
        } catch (final JasperReportBuildException e) {
            // JasperReportBuildException is expected
        }
    }
}
