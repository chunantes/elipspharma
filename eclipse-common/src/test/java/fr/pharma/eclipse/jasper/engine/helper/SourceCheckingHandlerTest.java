package fr.pharma.eclipse.jasper.engine.helper;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.jasper.exception.JasperReportBuildException;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe {@link SourceCheckingHandler}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class SourceCheckingHandlerTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private SourceCheckingHandler handler;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.handler = new SourceCheckingHandler();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.handler = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.handler);
    }

    /**
     * Test de la méthode handleCheck(boolean, java.lang.String) - check ko.
     */
    @Test
    public void testHandleCheckKo() {
        final String message = "Message d'erreur";
        try {
            this.handler.handleCheck(false, message);
            Assert.fail("Exception attendue.");
        } catch (final JasperReportBuildException e) {
            Assert.assertEquals(message, e.getMessage());
        }
    }

    /**
     * Test de la méthode handleCheck(boolean, java.lang.String) - check ok.
     */
    @Test
    public void testHandleCheckOk() {
        final String message = "Message d'erreur";
        try {
            this.handler.handleCheck(true, message);
        } catch (final JasperReportBuildException e) {
            Assert.fail("Exception non attendue.");
        }
    }
}
