package fr.pharma.eclipse.service.alerte.builder.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.alerte.Alerte;
import fr.pharma.eclipse.domain.model.essai.EssaiAlerte;
import fr.pharma.eclipse.utils.message.MessageBuilder;

/**
 * Classe en charge de tester le builder d'alertes sur la destruction des essais
 * 15 ans après la date de la visite de cloture.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AlerteDestructionEssaiBuilderTest {
    /**
     * AlerteDestructionEssaiBuilder à tester.
     */
    private AlerteDestructionEssaiBuilder builder;

    /**
     * Builder de message mocké.
     */
    private MessageBuilder mockMessageBuilder;

    /**
     * Méthode en charge d'initialiser les données.
     */
    @Before
    public void init() {
        this.builder = new AlerteDestructionEssaiBuilder();
        this.mockMessageBuilder = Mockito.mock(MessageBuilder.class);
        this.builder.setMessageBuilder(this.mockMessageBuilder);

    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.builder = null;
        this.mockMessageBuilder = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.builder);
        Assert.assertNotNull(this.mockMessageBuilder);
    }

    /**
     * Méthode en charge de tester la méthode de construction des alertes.
     */
    @Test
    public void testBuild() {
        final List<EssaiAlerte> essais = new ArrayList<EssaiAlerte>();

        final EssaiAlerte essai1 = new EssaiAlerte();
        essais.add(essai1);

        final EssaiAlerte essai2 = new EssaiAlerte();
        final Calendar cloture = Calendar.getInstance();
        cloture.set(Calendar.HOUR_OF_DAY, 0);
        cloture.set(Calendar.MINUTE, 0);
        cloture.set(Calendar.SECOND, 0);
        cloture.add(Calendar.YEAR, -AlerteDestructionEssaiBuilder.NB_ANNEES);
        cloture.add(Calendar.DAY_OF_MONTH, -1);
        essai2.setCloture(cloture);
        essais.add(essai2);

        final List<Alerte> alertes = new ArrayList<Alerte>();
        this.builder.build(essais, null, alertes);

        Assert.assertEquals(1, alertes.size());
    }

}
