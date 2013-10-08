package fr.pharma.eclipse.component.accueil;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.alerte.TypeAlerte;
import fr.pharma.eclipse.domain.model.actualite.Actualite;
import fr.pharma.eclipse.domain.model.alerte.Alerte;
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.service.actualite.ActualiteService;
import fr.pharma.eclipse.service.alerte.AlerteService;
import fr.pharma.eclipse.service.evenement.EvenementService;

/**
 * Classe en charge de tester le manager sur Accueil.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AccueilManagerTest {
    /**
     * AccueilManager à tester.
     */
    private AccueilManager manager;

    /**
     * Service de gestion des actualités mocké.
     */
    private ActualiteService mockActualiteService;

    /**
     * Service de gestion des événements mocké.
     */
    private EvenementService mockEvenementService;

    /**
     * Service de gestion des alertes mocké.
     */
    private AlerteService mockAlerteService;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.manager = new AccueilManager();
        this.mockActualiteService = Mockito.mock(ActualiteService.class);
        this.manager.setActualiteService(this.mockActualiteService);
        this.mockEvenementService = Mockito.mock(EvenementService.class);
        this.manager.setEvenementService(this.mockEvenementService);
        this.mockAlerteService = Mockito.mock(AlerteService.class);
        this.manager.setAlerteService(this.mockAlerteService);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.manager = null;
        this.mockActualiteService = null;
        this.mockEvenementService = null;
        this.mockAlerteService = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInitData() {
        Assert.assertNotNull(this.manager);
        Assert.assertNotNull(this.mockActualiteService);
        Assert.assertNotNull(this.mockEvenementService);
        Assert.assertNotNull(this.mockAlerteService);
    }

    /**
     * Méthode en charge de tester la construction des informations sur la page
     * d'accueil.
     */
    @Test
    public void testBuildInfosAccueil() {
        final List<Actualite> essais = new ArrayList<Actualite>();
        final Actualite essai = new Actualite();
        essai.setId(1L);
        essais.add(essai);

        final List<Evenement> evenements = new ArrayList<Evenement>();
        final Evenement evenement = new Evenement();
        evenements.add(evenement);

        final List<Alerte> alertes = new ArrayList<Alerte>();
        final Alerte alerte = new Alerte(TypeAlerte.DDES_DOTATIONS_A_TRAITER, "", "");
        alertes.add(alerte);

        Mockito.when(this.mockActualiteService.getLastEssais()).thenReturn(essais);
        Mockito.when(this.mockEvenementService.getNextEvenements()).thenReturn(evenements);
        Mockito.when(this.mockAlerteService.getAlertes()).thenReturn(alertes);

        this.manager.buildInfosAccueil();

        Assert.assertEquals(1, this.manager.getEssaisActualites().size());
        Assert.assertEquals(1, this.manager.getEvenements().size());
        Assert.assertEquals(1, this.manager.getAlertes().size());
    }

    /**
     * Méthode en charge de tester l'affectation de l'essai sélectionné.
     */
    @Test
    public void testSetActualiteSelected() {
        final Actualite essai = new Actualite();
        essai.setId(1L);
        this.manager.setActualiteSelected(essai);
        Assert.assertNotNull(this.manager.getActualiteSelected());
    }

    /**
     * Méthode en charge de tester l'affectation de l'événement sélectionné.
     */
    @Test
    public void testSetEvenementSelected() {
        final Evenement evenement = new Evenement();
        evenement.setId(1L);
        this.manager.setEvenementSelected(evenement);
        Assert.assertNotNull(this.manager.getEvenementSelected());
    }

}
