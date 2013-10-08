package fr.pharma.eclipse.service.alerte.builder.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.enums.evenement.TypeEvenement;
import fr.pharma.eclipse.domain.model.alerte.Alerte;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.EssaiAlerte;
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.service.evenement.EvenementService;
import fr.pharma.eclipse.utils.message.MessageBuilder;

/**
 * Classe en charge de tester le builder d'alertes pour les résultats de visites
 * non rempli le lendemain de la date de visite.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AlerteResultVisiteBuilderTest {
    /**
     * AlerteResultVisiteBuilder à tester.
     */
    private AlerteResultVisiteBuilder builder;

    /**
     * Service de gestion des événements mocké.
     */
    private EvenementService mockEvenementService;

    /**
     * Builder de message mocké.
     */
    private MessageBuilder mockMessageBuilder;

    /**
     * Méthode en charge d'initialiser les données.
     */
    @Before
    public void init() {
        this.builder = new AlerteResultVisiteBuilder();
        this.mockEvenementService = Mockito.mock(EvenementService.class);
        this.builder.setEvenementService(this.mockEvenementService);
        this.mockMessageBuilder = Mockito.mock(MessageBuilder.class);
        this.builder.setMessageBuilder(this.mockMessageBuilder);

    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.builder = null;
        this.mockEvenementService = null;
        this.mockMessageBuilder = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.builder);
        Assert.assertNotNull(this.mockEvenementService);
        Assert.assertNotNull(this.mockMessageBuilder);
    }

    /**
     * Méthode en charge de tester la méthode de construction des alertes.
     */
    @Test
    public void testBuild() {
        final List<EssaiAlerte> essais = new ArrayList<EssaiAlerte>();
        final EssaiAlerte essai = new EssaiAlerte();
        essai.setId(1L);
        essais.add(essai);

        final Essai e = new Essai();

        final List<Alerte> alertes = new ArrayList<Alerte>();

        final Evenement visite1 = new Evenement();
        visite1.setTypeEvenement(TypeEvenement.VISITE);
        visite1.setEssai(e);
        final Calendar dateDebut1 = Calendar.getInstance();
        dateDebut1.add(Calendar.MONTH, 1);
        visite1.setDateDebut(dateDebut1);

        final Evenement visite2 = new Evenement();
        visite2.setTypeEvenement(TypeEvenement.VISITE);
        visite2.setEssai(e);
        final Calendar dateDebut2 = Calendar.getInstance();
        dateDebut2.add(Calendar.DAY_OF_MONTH, -1);
        visite2.setDateDebut(dateDebut2);

        final List<Evenement> evts = new ArrayList<Evenement>();
        evts.add(visite1);
        evts.add(visite2);

        Mockito.when(this.mockEvenementService.getAll((SearchCriteria) Matchers.any())).thenReturn(evts);

        this.builder.build(essais, null, alertes);

        Assert.assertEquals(1, alertes.size());
    }

}
