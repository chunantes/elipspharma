package fr.pharma.eclipse.service.alerte.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.enums.RolePersonne;
import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.enums.alerte.TypeAlerte;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.domain.model.alerte.Alerte;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.EssaiAlerte;
import fr.pharma.eclipse.domain.model.essai.detail.dates.DetailDates;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.user.UserSecurity;
import fr.pharma.eclipse.service.alerte.builder.AlerteBuilder;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.stockage.PharmacieService;
import fr.pharma.eclipse.service.user.UserService;
import fr.pharma.eclipse.utils.ContextSecurityHelper;

/**
 * Classe en charge de tester le service de gestion des alertes.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AlerteServiceImplTest {
    /**
     * AlerteServiceImpl à tester.
     */
    private AlerteServiceImpl service;

    /**
     * Service de gestion des essais mocké.
     */
    private EssaiService mockEssaiService;

    /**
     * Service de gestion des pharmacies mocké.
     */
    private PharmacieService mockPharmacieService;

    /**
     * Service de gestion des utilisateurs mocké.
     */
    private UserService mockUserService;

    /**
     * Map des builders d'alerte.
     */
    private final Map<TypeAlerte, AlerteBuilder> builders = new HashMap<TypeAlerte, AlerteBuilder>();

    /**
     * Méthode en charge d'initialiser les données.
     */
    @Before
    public void init() {
        this.service = new AlerteServiceImpl();
        this.mockEssaiService = Mockito.mock(EssaiService.class);
        this.service.setEssaiService(this.mockEssaiService);
        this.mockPharmacieService = Mockito.mock(PharmacieService.class);
        this.service.setPharmacieService(this.mockPharmacieService);
        this.mockUserService = Mockito.mock(UserService.class);
        this.service.setUserService(this.mockUserService);

        this.builders.put(TypeAlerte.DDES_DOTATIONS_A_TRAITER, Mockito.mock(AlerteBuilder.class));

        this.service.setBuilders(this.builders);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.service = null;
        this.mockEssaiService = null;
        this.mockPharmacieService = null;
        this.mockUserService = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.service);
        Assert.assertNotNull(this.mockEssaiService);
        Assert.assertNotNull(this.mockPharmacieService);
        Assert.assertNotNull(this.mockUserService);
    }

    /**
     * Méthode en charge de tester la récupération des alertes.
     */
    @Test
    public void testGetAlertes() {

        ContextSecurityHelper.createSecurityContextMock();
        // creation objet personne
        final Personne personne = new Pharmacien();
        // set du type de personne
        personne.setType(TypePersonne.PHARMACIEN);
        // definition de son role
        personne.setIsAdmin(true);
        // verification que la methode getPersonne de la classe UserService à
        // bien été appelée
        // Si c'est le cas retour de la personne
        Mockito.when(this.mockUserService.getPersonne()).thenReturn(personne);

        // definition de la liste des alertes
        final List<Alerte> alertes = new ArrayList<Alerte>();
        // Mock de la classe UserSecurity
        final UserSecurity userSecurity = Mockito.mock(UserSecurity.class);
        // verification que la methode getUser() à bien été appelée dans la
        // classe UserService
        // si oui retour de l'objet userSecurity
        Mockito.when(this.mockUserService.getUser()).thenReturn(userSecurity);
        // verification que la methode getRole()à bien été appelée dans la
        // classe UserSecurity
        // retour de des droit de la personne
        Mockito.when(userSecurity.getRole()).thenReturn(RolePersonne.ADMIN);

        // mock de la liste des essais
        final List essais = Mockito.mock(List.class);
        // creation d'un essaiAlerte
        final EssaiAlerte essai = new EssaiAlerte();
        essai.setId(1L);
        // Ajout de l'essai à la liste
        essais.add(essai);
        // verfication que liste des essais est bien trouvée
        Mockito.when(this.mockEssaiService.executeSQLQuery(AlerteServiceImpl.SELECT_ESSAIS_FOR_ADMIN, null, AlerteServiceImpl.ESSAI_COLS, EssaiAlerte.class,
                                                           AlerteServiceImpl.scalars)).thenReturn(essais);

        // definition de la liste des pharmacies
        final List<Pharmacie> pharmacies = new ArrayList<Pharmacie>();
        // creation d'une pharmacie
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(1L);
        // ajout de la pharmacie à la liste
        pharmacies.add(pharmacie);
        // verification que la methode getAll()à bien été appelée dans la
        // classe GenericService
        // retour de la liste des pharmacies
        Mockito.when(this.mockPharmacieService.getAll()).thenReturn(pharmacies);

        // appel de la methode getAlertes() AlerteServiceImpl
        this.service.getAlertes();
        // test de la methode build de la classe AlerteBuilder pour chaque
        // élément de la map builder
        for (final Map.Entry<TypeAlerte, AlerteBuilder> entry : this.builders.entrySet()) {
            Mockito.verify(entry.getValue()).build(essais, pharmacies, alertes);
        }
    }

    /**
     * Méthode en charge de tester la récupération des alertes.
     */
    @Test
    public void testGetAlertesForEssai() {
        ContextSecurityHelper.createSecurityContextMock();
        final Personne personne = new Pharmacien();
        personne.setType(TypePersonne.PHARMACIEN);
        personne.setIsAdmin(true);
        Mockito.when(this.mockUserService.getPersonne()).thenReturn(personne);

        final UserSecurity userSecurity = Mockito.mock(UserSecurity.class);
        Mockito.when(this.mockUserService.getUser()).thenReturn(userSecurity);
        Mockito.when(userSecurity.getRole()).thenReturn(RolePersonne.ADMIN);

        final Essai essai = new Essai();
        essai.setAlerteActive(Boolean.TRUE);
        essai.setEtat(EtatEssai.EN_COURS);
        essai.setDetailDates(new DetailDates());
        essai.setId(1L);

        final List<Pharmacie> pharmacies = new ArrayList<Pharmacie>();
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(1L);
        pharmacies.add(pharmacie);
        Mockito.when(this.mockPharmacieService.getAll()).thenReturn(pharmacies);

        final List<Alerte> result = this.service.getAlertes(essai);
        Assert.assertNotNull(result);
    }

    /**
     * Méthode en charge de tester la récupération des alertes.
     */
    @Test
    public void testGetAlertesForEssaiNotSave() {
        final UserSecurity userSecurity = Mockito.mock(UserSecurity.class);
        Mockito.when(this.mockUserService.getUser()).thenReturn(userSecurity);
        Mockito.when(userSecurity.getRole()).thenReturn(RolePersonne.ADMIN);

        final Essai essai = new Essai();
        essai.setAlerteActive(Boolean.TRUE);
        essai.setEtat(EtatEssai.EN_COURS);

        final List<Pharmacie> pharmacies = new ArrayList<Pharmacie>();
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(1L);
        pharmacies.add(pharmacie);
        Mockito.when(this.mockPharmacieService.getAll()).thenReturn(pharmacies);

        final List<Alerte> result = this.service.getAlertes(essai);
        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }

    /**
     * Méthode en charge de tester la récupération des alertes.
     */
    @Test
    public void testGetAlertesWithEssaisEmpty() {
        ContextSecurityHelper.createSecurityContextMock();
        final Personne personne = new Pharmacien();
        personne.setType(TypePersonne.PHARMACIEN);
        personne.setIsAdmin(true);
        Mockito.when(this.mockUserService.getPersonne()).thenReturn(personne);
        final UserSecurity userSecurity = Mockito.mock(UserSecurity.class);
        Mockito.when(this.mockUserService.getUser()).thenReturn(userSecurity);
        Mockito.when(userSecurity.getRole()).thenReturn(RolePersonne.ADMIN);
        final List<Essai> essais = new ArrayList<Essai>();
        Mockito.when(this.mockEssaiService.getAll()).thenReturn(essais);
        final List<Alerte> alertes = this.service.getAlertes();
        Assert.assertEquals(0, alertes.size());
    }

    /**
     * Méthode en charge de tester la méthode de lancement des calculs d'alerte.
     */
    @Test
    public void testIsAdminOrPharmacienAdmin() {
        final UserSecurity userSecurity = Mockito.mock(UserSecurity.class);
        Mockito.when(this.mockUserService.getUser()).thenReturn(userSecurity);
        Mockito.when(userSecurity.getRole()).thenReturn(RolePersonne.ADMIN);
        Assert.assertTrue(this.service.isAdminOrPharmacien());
    }

    /**
     * Méthode en charge de tester la méthode de lancement des calculs d'alerte.
     */
    @Test
    public void testIsAdminOrPharmacienPharmacien() {
        final UserSecurity userSecurity = Mockito.mock(UserSecurity.class);
        Mockito.when(this.mockUserService.getUser()).thenReturn(userSecurity);
        Mockito.when(userSecurity.getRole()).thenReturn(RolePersonne.PHARMACIEN);
        Assert.assertTrue(this.service.isAdminOrPharmacien());
    }

    /**
     * Méthode en charge de tester la méthode de lancement des calculs d'alerte.
     */
    @Test
    public void testIsAdminOrPharmacienPharmacienExterne() {
        final UserSecurity userSecurity = Mockito.mock(UserSecurity.class);
        Mockito.when(this.mockUserService.getUser()).thenReturn(userSecurity);
        Mockito.when(userSecurity.getRole()).thenReturn(RolePersonne.PHARMACIEN_EXTERNE);
        Assert.assertTrue(this.service.isAdminOrPharmacien());
    }

    /**
     * Méthode en charge de tester la méthode de lancement des calculs d'alerte.
     */
    @Test
    public void testIsAdminOrPharmacienPharmacienInterne() {
        final UserSecurity userSecurity = Mockito.mock(UserSecurity.class);
        Mockito.when(this.mockUserService.getUser()).thenReturn(userSecurity);
        Mockito.when(userSecurity.getRole()).thenReturn(RolePersonne.PHARMACIEN_INTERNE);
        Assert.assertTrue(this.service.isAdminOrPharmacien());
    }

    /**
     * Méthode en charge de tester la méthode de lancement des calculs d'alerte.
     */
    @Test
    public void testIsAdminOrPharmacienPharmacienPreparateur() {
        final UserSecurity userSecurity = Mockito.mock(UserSecurity.class);
        Mockito.when(this.mockUserService.getUser()).thenReturn(userSecurity);
        Mockito.when(userSecurity.getRole()).thenReturn(RolePersonne.PHARMACIEN_PREPARATEUR);
        Assert.assertTrue(this.service.isAdminOrPharmacien());
    }

    /**
     * Méthode en charge de tester la méthode de lancement des calculs d'alerte.
     */
    @Test
    public void testIsAdminOrPharmacienPharmacienTitulaire() {
        final UserSecurity userSecurity = Mockito.mock(UserSecurity.class);
        Mockito.when(this.mockUserService.getUser()).thenReturn(userSecurity);
        Mockito.when(userSecurity.getRole()).thenReturn(RolePersonne.PHARMACIEN_TITULAIRE);
        Assert.assertTrue(this.service.isAdminOrPharmacien());
    }

    /**
     * Méthode en charge de tester la méthode de lancement des calculs d'alerte.
     */
    @Test
    public void testIsAdminOrPharmacienCro() {
        final UserSecurity userSecurity = Mockito.mock(UserSecurity.class);
        Mockito.when(this.mockUserService.getUser()).thenReturn(userSecurity);
        Mockito.when(userSecurity.getRole()).thenReturn(RolePersonne.CRO);
        Assert.assertFalse(this.service.isAdminOrPharmacien());
    }

}
