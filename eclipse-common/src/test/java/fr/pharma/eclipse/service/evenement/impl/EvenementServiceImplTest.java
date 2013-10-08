package fr.pharma.eclipse.service.evenement.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import org.joda.time.DateMidnight;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.dao.search.AclSearchDao;
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.domain.model.suivi.evenement.EvenementSuivi;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.evenement.updator.EvenementBeforeSaveUpdator;
import fr.pharma.eclipse.utils.ContextSecurityHelper;

/**
 * Classe en charge de tester le service des événements.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EvenementServiceImplTest {
    /**
     * EvenementServiceImpl à tester.
     */
    private EvenementServiceImpl service;

    /**
     * Mock de la dao des evenements.
     */
    private GenericDao<Evenement> mockDao;

    /**
     * Factory de suivi de evenement mocké.
     */
    private SuiviFactory<EvenementSuivi> mockEvtSuiviFactory;

    /**
     * Service de gestion des essais mocké.
     */
    private EssaiService mockEssaiService;

    /**
     * Updator.
     */
    private EvenementBeforeSaveUpdator updator;

    /**
     * DAO de recherche des ACLs.
     */
    private AclSearchDao mockAclSearchDao;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.updator = Mockito.mock(EvenementBeforeSaveUpdator.class);
        this.mockDao = Mockito.mock(GenericDao.class);
        this.service = new EvenementServiceImpl(this.mockDao);
        this.mockEvtSuiviFactory = Mockito.mock(SuiviFactory.class);
        this.service.setEvtSuiviFactory(this.mockEvtSuiviFactory);
        final List<EvenementBeforeSaveUpdator> liste = new ArrayList<EvenementBeforeSaveUpdator>();
        liste.add(this.updator);
        this.service.setUpdators(liste);
        this.mockEssaiService = Mockito.mock(EssaiService.class);
        this.service.setEssaiService(this.mockEssaiService);
        this.mockAclSearchDao = Mockito.mock(AclSearchDao.class);
        this.service.setAclSearchDao(this.mockAclSearchDao);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.mockDao = null;
        this.service = null;
        this.mockEvtSuiviFactory = null;
        this.mockAclSearchDao = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.mockDao);
        Assert.assertNotNull(this.service);
        Assert.assertNotNull(this.mockEvtSuiviFactory);
    }

    /**
     * Méthode en charge de tester la sauvegarde d'un événement.
     */
    @Test
    public void testSaveEvenement() {
        final Evenement evenement = Mockito.mock(Evenement.class);
        Mockito.when(evenement.getDateDebut()).thenReturn(Calendar.getInstance());
        final EvenementSuivi suivi = new EvenementSuivi();
        Mockito.when(this.mockDao.reattach(evenement)).thenReturn(evenement);
        Mockito.when(this.mockEvtSuiviFactory.getInitializedObject()).thenReturn(suivi);
        Mockito.when(evenement.getModifs()).thenReturn(new TreeSet<EvenementSuivi>());
        Mockito.when(this.mockDao.save(evenement)).thenReturn(evenement);

        final Evenement result = this.service.save(evenement);

        Mockito.verify(this.mockDao).reattach(evenement);
        Mockito.verify(this.mockEvtSuiviFactory).getInitializedObject();
        Assert.assertEquals(1, result.getModifs().size());
    }

    /**
     * Méthode en charge de tester la sauvegarde d'un événement avec des heures
     * minutes valides.
     */
    @Test
    public void testSaveEvenementWithHeuresOK() {
        final Evenement evenement = new Evenement();
        evenement.setHeureDebut("10:30");
        evenement.setDateDebut(Calendar.getInstance());

        final EvenementSuivi suivi = new EvenementSuivi();
        Mockito.when(this.mockDao.reattach((Evenement) Matchers.any())).thenReturn(evenement);
        Mockito.when(this.mockEvtSuiviFactory.getInitializedObject()).thenReturn(suivi);

        Mockito.when(this.mockDao.save((Evenement) Matchers.any())).thenReturn(evenement);

        final Evenement result = this.service.save(evenement);

        Assert.assertEquals(Integer.valueOf("10").intValue(), result.getDateDebut().get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals(Integer.valueOf("30").intValue(), result.getDateDebut().get(Calendar.MINUTE));

    }

    /**
     * Méthode en charge de tester la sauvegarde d'un événement avec des heures
     * minutes valides.
     */
    @Test(expected = ValidationException.class)
    public void testSaveEvenementWithHeuresKOArray() {
        final Evenement evenement = new Evenement();
        evenement.setHeureDebut("10");
        evenement.setDateDebut(Calendar.getInstance());

        final EvenementSuivi suivi = new EvenementSuivi();
        Mockito.when(this.mockDao.reattach((Evenement) Matchers.any())).thenReturn(evenement);
        Mockito.when(this.mockEvtSuiviFactory.getInitializedObject()).thenReturn(suivi);

        Mockito.when(this.mockDao.save((Evenement) Matchers.any())).thenReturn(evenement);

        final Evenement result = this.service.save(evenement);

        Assert.assertEquals(Integer.valueOf("0").intValue(), result.getDateDebut().get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals(Integer.valueOf("0").intValue(), result.getDateDebut().get(Calendar.MINUTE));
        Assert.assertNull(evenement.getHeureDebut());
    }

    /**
     * Méthode en charge de tester la sauvegarde d'un événement avec des heures
     * minutes valides.
     */
    @Test(expected = ValidationException.class)
    public void testSaveEvenementWithHeuresKONumber() {
        final Evenement evenement = new Evenement();
        evenement.setHeureDebut("^^:ll");
        evenement.setDateDebut(Calendar.getInstance());

        final EvenementSuivi suivi = new EvenementSuivi();
        Mockito.when(this.mockDao.reattach((Evenement) Matchers.any())).thenReturn(evenement);
        Mockito.when(this.mockEvtSuiviFactory.getInitializedObject()).thenReturn(suivi);

        Mockito.when(this.mockDao.save((Evenement) Matchers.any())).thenReturn(evenement);

        final Evenement result = this.service.save(evenement);

        Assert.assertEquals(Integer.valueOf("0").intValue(), result.getDateDebut().get(Calendar.HOUR_OF_DAY));
        Assert.assertEquals(Integer.valueOf("0").intValue(), result.getDateDebut().get(Calendar.MINUTE));
        Assert.assertNull(evenement.getHeureDebut());
    }

    /**
     * Méthode en charge de tester la récupération des prochains événements.
     */
    @Test
    public void testGetNextEvenementsAdmin() {
        ContextSecurityHelper.createSecurityContextMock();
        final List<Long> idsEssais = new ArrayList<Long>();
        Mockito.when(this.mockAclSearchDao.findIdsEssais()).thenReturn(idsEssais);
        final String paramIdsEssais = Arrays.toString(idsEssais.toArray(new Object[idsEssais.size()])).replace("[", "(").replace("]", ")");

        final Date dateDebut = new DateMidnight().toDate();
        final Date dateFin = new DateMidnight().plusDays(EvenementServiceImpl.NB_JOURS_RECUP_EVT + 1).toDate();

        final List<Evenement> evenements = new ArrayList<Evenement>();
        Mockito.when(this.mockDao.executeSQLQuery(MessageFormat.format(EvenementServiceImpl.SELECT_EVENEMENTS, paramIdsEssais), new Object[]{dateDebut, dateFin })).thenReturn(evenements);
        final List<Evenement> result = this.service.getNextEvenements();
        Assert.assertEquals(0, result.size());
    }

}
