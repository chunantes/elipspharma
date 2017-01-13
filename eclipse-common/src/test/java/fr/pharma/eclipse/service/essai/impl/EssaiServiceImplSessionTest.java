package fr.pharma.eclipse.service.essai.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.ArcPromoteur;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.domain.model.actualite.Actualite;
import fr.pharma.eclipse.service.actualite.ActualiteService;
import fr.pharma.eclipse.service.alerte.AlerteService;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.evenement.EvenementService;
import fr.pharma.eclipse.utils.ContextSecurityHelper;

/**
 * Classe de test en session du service des essais.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class EssaiServiceImplSessionTest {
    /**
     * Service de gestion des essais.
     */
    @Resource(name = "essaiService")
    private EssaiService essaiService;

    /**
     * Service de gestion des actualités.
     */
    @Resource(name = "actualiteService")
    private ActualiteService actualiteService;

    /**
     * Service de gestion des événements.
     */
    @Resource(name = "evenementService")
    private EvenementService evenementService;

    /**
     * Service de gestion des alertes.
     */
    @Resource(name = "alerteService")
    private AlerteService alerteService;

    /**
     * Méthode d'initialisation des données de test.
     */
    @Before
    public void init() {
        ContextSecurityHelper.createSecurityContextMock();
    }

    /**
     * Méthode de purge des données de test.
     */
    @After
    public void end() {
    }

    /**
     * Test de la méthode de récupération d'un essai.
     */
    @Test
    public void testGetUnitaire() {
        final long debut = System.currentTimeMillis();
        this.essaiService.get(1L);
        final long fin = System.currentTimeMillis();
        System.out.println("Temps chargement 1 essai : " + (fin - debut) / 1000 + "s");
    }

    /**
     * Test de la méthode de récupération de tous les essais.
     */
    @Test
    public void testGetAll() {
        final long debut = System.currentTimeMillis();
        this.essaiService.getAll();
        final long fin = System.currentTimeMillis();
        System.out.println("Temps chargement all essai : " + (fin - debut) / 1000 + "s");
    }

    /**
     * Test de la méthode de récupération des actualités pour un admin.
     */
    @Test
    public void testActualitesAdmin() {
        ContextSecurityHelper.createSecurityContextMock();
        final long debut = System.currentTimeMillis();
        final List<Actualite> actualites = this.actualiteService.getLastEssais();
        for (final Actualite actualite : actualites) {
            System.out.println(actualite.getNom());
        }
        final long fin = System.currentTimeMillis();
        System.out.println("Traitement calcul actualites : " + BigDecimal.valueOf((fin - debut) / 1000) + "s");
    }

    /**
     * Test de la méthode de récupération des actualités pour un pharmacien.
     */
    @Test
    public void testActualitesPharmacien() {
        ContextSecurityHelper.createSecurityContextPharmacienMock();
        final long debut = System.currentTimeMillis();
        final List<Actualite> actualites = this.actualiteService.getLastEssais();
        for (final Actualite actualite : actualites) {
            System.out.println(actualite.getNom());
        }
        final long fin = System.currentTimeMillis();
        System.out.println("Traitement calcul actualites : " + BigDecimal.valueOf((fin - debut) / 1000) + "s");
    }

    /**
     * Test de la méthode de récupération des actualités pour un investigateur.
     */
    @Test
    public void testActualitesInvestigateur() {
        ContextSecurityHelper.createSecurityContextInvestigateurMock();
        final long debut = System.currentTimeMillis();
        final List<Actualite> actualites = this.actualiteService.getLastEssais();
        for (final Actualite actualite : actualites) {
            System.out.println(actualite.getNom());
        }
        final long fin = System.currentTimeMillis();
        System.out.println("Traitement calcul actualites : " + BigDecimal.valueOf((fin - debut) / 1000) + "s");
    }

    /**
     * Test de la méthode de récupération des événements pour un admin.
     */
    @Test
    public void testEvenementsAdmin() {
        final long debut = System.currentTimeMillis();
        this.evenementService.getNextEvenements();
        final long fin = System.currentTimeMillis();
        System.out.println("Traitement calcul evenements : " + (fin - debut) / 1000 + "s");
    }

    /**
     * Test de la méthode de récupération des événements pour un pharmacien.
     */
    @Test
    public void testEvenementsPharmacien() {
        ContextSecurityHelper.createSecurityContextPharmacienMock();
        final long debut = System.currentTimeMillis();
        this.evenementService.getNextEvenements();
        final long fin = System.currentTimeMillis();
        System.out.println("Traitement calcul evenements : " + (fin - debut) / 1000 + "s");
    }

    /**
     * Test de la méthode de récupération des événements pour un investigateur.
     */
    @Test
    public void testEvenementsInvestigateur() {
        ContextSecurityHelper.createSecurityContextInvestigateurMock();
        final long debut = System.currentTimeMillis();
        this.evenementService.getNextEvenements();
        final long fin = System.currentTimeMillis();
        System.out.println("Traitement calcul evenements : " + (fin - debut) / 1000 + "s");
    }

    /**
     * Test de la méthode de récupération des alertes.
     */
    @Ignore("Non testable sous la base en mémoire H2")
    @Test
    public void testAlertes() {
        final long debut = System.currentTimeMillis();
        this.alerteService.getAlertes();
        final long fin = System.currentTimeMillis();
        System.out.println("Traitement calcul alertes : " + (fin - debut) / 1000 + "s");
    }

    /**
     * Test de la méthode de récupération des identifiants des essais.
     */
    @Test
    public void testGetIdsEssaisOfUserPharma() {
        final Personne personne = new Pharmacien();
        personne.setId(1L);
        personne.setType(TypePersonne.PHARMACIEN);
        final List<Long> results = this.essaiService.getIdsEssaisOfUser(personne);
        Assert.assertNotNull(results);
    }

    /**
     * Test de la méthode de récupération des identifiants des essais.
     */
    @Test
    public void testGetIdsEssaisOfUserOther() {
        final Personne personne = new ArcPromoteur();
        personne.setId(1L);
        personne.setType(TypePersonne.ARC_PROMOTEUR);
        final List<Long> results = this.essaiService.getIdsEssaisOfUser(personne);
        Assert.assertNotNull(results);
    }
}
