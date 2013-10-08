package fr.pharma.eclipse.jasper.exception;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Test de la classe {@link JasperReportBuildException}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JasperReportBuildExceptionTest {

    /**
     * Test du constructeur JasperReportBuildException(String).
     */
    @Test
    public void testJasperReportBuildExceptionString() {
        final String message = "message d'erreur";
        final Exception exc = new JasperReportBuildException(message);
        Assert.assertEquals(message, exc.getMessage());
        Assert.assertNull(exc.getCause());
    }

    /**
     * Test du constructeur JasperReportBuildException(Throwable).
     */
    @Test
    public void testJasperReportBuildExceptionThrowable() {
        final Throwable cause = Mockito.mock(Throwable.class);
        final Exception exc = new JasperReportBuildException(cause);
        Assert.assertEquals(cause, exc.getCause());
    }

    /**
     * Test du constructeur JasperReportBuildException(String,Throwable).
     */
    @Test
    public void testJasperReportBuildExceptionStringThrowable() {
        final String message = "message d'erreur";
        final Throwable cause = Mockito.mock(Throwable.class);
        final Exception exc = new JasperReportBuildException(message, cause);
        Assert.assertEquals(message, exc.getMessage());
        Assert.assertEquals(cause, exc.getCause());
    }

}
