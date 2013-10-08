package fr.pharma.eclipse.exception;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester la classe de gestion des exceptions techniques.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class TechnicalExceptionTest {
    /**
     * Méthode en charge de tester la construction d'une TechnicalException à
     * partir d'une String.
     */
    @Test
    public void testTechnicalExceptionString() {
        final TechnicalException exception = new TechnicalException("msg");
        Assert.assertEquals("msg", exception.getMessage());
    }

    /**
     * Méthode en charge de tester la construction d'une TechnicalException à
     * partir d'une Throwable.
     */
    @Test
    public void testTechnicalExceptionThrowable() {
        final Throwable throwable = new Throwable("myThrowable");
        final TechnicalException exception = new TechnicalException(throwable);
        Assert.assertEquals("myThrowable", exception.getCause().getMessage());
    }

    /**
     * Méthode en charge de tester la construction d'une TechnicalException à
     * partir d'une String et d'une Throwable.
     */
    @Test
    public void testTechnicalExceptionStringThrowable() {
        final Throwable throwable = new Throwable("myThrowable");
        final TechnicalException exception = new TechnicalException("msg", throwable);
        Assert.assertEquals("msg", exception.getMessage());
        Assert.assertEquals("myThrowable", exception.getCause().getMessage());
    }

}
