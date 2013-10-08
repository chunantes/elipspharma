package fr.pharma.eclipse.utils;

import java.util.Calendar;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.utils.constants.EclipseConstants;
import fr.pharma.eclipse.utils.message.MessageBuilder;

/**
 * Classe en charge de tester la classe utilitaire Faces.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class FacesUtilsTest extends FacesContextUtils {
    /**
     * FacesUtils à tester.
     */
    private FacesUtils facesUtils;

    /***/
    private MessageBuilder mockMessageBuilder;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.facesUtils = new FacesUtils();
        this.mockMessageBuilder = Mockito.mock(MessageBuilder.class);
        this.facesUtils.setMessageBuilder(this.mockMessageBuilder);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.facesUtils = null;
        this.mockMessageBuilder = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.facesUtils);
        Assert.assertNotNull(this.mockMessageBuilder);
    }

    /**
     * Méthode en charge de tester l'ajout de message sur l'instance courante
     * FacesContext.
     */
    @Test
    public void testAddMessage() {
        final FacesContext facesContext = Mockito.mock(FacesContext.class);
        super.setFacesContext(facesContext);
        final String codeMessage = "codeMessage";
        this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, codeMessage);
        Mockito.verify(this.mockMessageBuilder).getMessage(codeMessage);
    }

    /**
     * Méthode en charge de tester la méthode de formattage de date.
     */
    @Test
    public void testFormatDate() {
        final Calendar calendar = Calendar.getInstance(EclipseConstants.LOCALE);
        Assert.assertNotNull(this.facesUtils.formatDate(calendar));
    }

}
