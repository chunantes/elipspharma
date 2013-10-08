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
 * Classe en charge de tester le builder d'alertes pour les essais dont la date
 * de fin est atteinte.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AlerteDateFinEssaiBuilderTest {
    /**
     * AlerteDateFinEssaiBuilder à tester.
     */
    private AlerteDateFinEssaiBuilder builder;

    /**
     * Builder de message mocké.
     */
    private MessageBuilder mockMessageBuilder;

    /**
     * Méthode en charge d'initialiser les données.
     */
    @Before
    public void init() {
        this.builder = new AlerteDateFinEssaiBuilder();
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
        final Calendar finEtude = Calendar.getInstance();
        finEtude.add(Calendar.DAY_OF_MONTH, -1);
        essai2.setFinEtude(finEtude);
        essais.add(essai2);

        final List<Alerte> alertes = new ArrayList<Alerte>();
        this.builder.build(essais, null, alertes);

        Assert.assertEquals(1, alertes.size());
    }

    /**
     * Méthode en charge de tester la méthode de construction des alertes.
     */
    @Test
    public void testBuildNoAlerte() {
        final List<EssaiAlerte> essais = new ArrayList<EssaiAlerte>();

        final EssaiAlerte essai1 = new EssaiAlerte();
        essais.add(essai1);

        final EssaiAlerte essai2 = new EssaiAlerte();
        final Calendar finEtude = Calendar.getInstance();
        finEtude.add(Calendar.DAY_OF_MONTH, 1);
        essai2.setFinEtude(finEtude);
        essais.add(essai2);

        final List<Alerte> alertes = new ArrayList<Alerte>();
        this.builder.build(essais, null, alertes);

        Assert.assertEquals(0, alertes.size());
    }

}
