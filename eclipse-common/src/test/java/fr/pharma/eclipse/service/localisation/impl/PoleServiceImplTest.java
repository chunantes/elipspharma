package fr.pharma.eclipse.service.localisation.impl;

import java.util.TreeSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.model.localisation.Pole;
import fr.pharma.eclipse.domain.model.suivi.localisation.PoleSuivi;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;

/**
 * Classe en charge de tester le service de gestion des poles.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PoleServiceImplTest {

    /**
     * Service de gestion de poles à tester.
     */
    private PoleServiceImpl service;

    /**
     * Dao mocké.
     */
    private GenericDao<Pole> mockDao;

    /**
     * Suivi Factory mocké.
     */
    private SuiviFactory<PoleSuivi> mockSuiviFactory;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.mockDao = Mockito.mock(GenericDao.class);
        this.service = new PoleServiceImpl(this.mockDao);
        this.mockSuiviFactory = Mockito.mock(SuiviFactory.class);
        this.service.setPoleSuiviFactory(this.mockSuiviFactory);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.mockDao = null;
        this.service = null;
        this.mockSuiviFactory = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.service);
        Assert.assertNotNull(this.mockDao);
        Assert.assertNotNull(this.mockSuiviFactory);
    }

    /**
     * Méthode en charge de tester la sauvegarde des poles.
     */
    @Test
    public void testSavePole() {
        final Pole pole = Mockito.mock(Pole.class);
        final PoleSuivi suivi = new PoleSuivi();
        Mockito.when(this.mockDao.reattach(pole)).thenReturn(pole);
        Mockito.when(this.mockSuiviFactory.getInitializedObject()).thenReturn(suivi);
        Mockito.when(pole.getModifs()).thenReturn(new TreeSet<PoleSuivi>());
        Mockito.when(this.mockDao.save(pole)).thenReturn(pole);
        final Pole result = this.service.save(pole);
        Mockito.verify(this.mockDao).reattach(pole);
        Mockito.verify(this.mockSuiviFactory).getInitializedObject();
        Assert.assertEquals(1, result.getModifs().size());
    }

}
