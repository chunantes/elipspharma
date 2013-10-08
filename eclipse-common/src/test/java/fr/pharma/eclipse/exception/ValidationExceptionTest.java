package fr.pharma.eclipse.exception;

import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester la classe de gestion des exceptions de validation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ValidationExceptionTest {
    /**
     * Méthode en charge de tester la construction d'une ValidationException à
     * partir d'une String (code erreur).
     */
    @Test
    public void testValidationExceptionString() {
        final ValidationException exception = new ValidationException("msg");
        Assert.assertEquals("msg", exception.getErrorCode());
        Assert.assertEquals(0, exception.getValues().length);
        Assert.assertNull(exception.getSource());
    }

    /**
     * Méthode en charge de tester la construction d'une ValidationException à
     * partir de 2 String (code erreur, valeur).
     */
    @Test
    public void testValidationExceptionStringString() {
        final ValidationException exception = new ValidationException("msg", "valeur");
        Assert.assertEquals("msg", exception.getErrorCode());
        Assert.assertEquals(1, exception.getValues().length);
        Assert.assertEquals("valeur", exception.getValues()[0]);
        Assert.assertNull(exception.getSource());
    }

}
