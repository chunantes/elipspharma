package fr.pharma.eclipse.service.localisation.impl;

import java.util.TreeSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.model.localisation.Etablissement;
import fr.pharma.eclipse.domain.model.suivi.localisation.EtablissementSuivi;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;

/**
 * Classe en charge de tester le service de gestion des établissements.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EtablissementServiceImplTest {

    /**
     * Service de gestion de etablissements à tester.
     */
    private EtablissementServiceImpl service;

    /**
     * Dao mocké.
     */
    private GenericDao<Etablissement> mockDao;

    /**
     * Suivi Factory mocké.
     */
    private SuiviFactory<EtablissementSuivi> mockSuiviFactory;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.mockDao = Mockito.mock(GenericDao.class);
        this.service = new EtablissementServiceImpl(this.mockDao);
        this.mockSuiviFactory = Mockito.mock(SuiviFactory.class);
        this.service.setEtabSuiviFactory(this.mockSuiviFactory);
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
     * Méthode en charge de tester la sauvegarde des etablissements.
     */
    @Test
    public void testSaveEtablissement() {
        final Etablissement etablissement = Mockito.mock(Etablissement.class);
        final EtablissementSuivi suivi = new EtablissementSuivi();
        Mockito.when(this.mockDao.reattach(etablissement)).thenReturn(etablissement);
        Mockito.when(this.mockSuiviFactory.getInitializedObject()).thenReturn(suivi);
        Mockito.when(etablissement.getModifs()).thenReturn(new TreeSet<EtablissementSuivi>());
        Mockito.when(this.mockDao.save(etablissement)).thenReturn(etablissement);
        final Etablissement result = this.service.save(etablissement);
        Mockito.verify(this.mockDao).reattach(etablissement);
        Mockito.verify(this.mockSuiviFactory).getInitializedObject();
        Assert.assertEquals(1, result.getModifs().size());
    }

}
