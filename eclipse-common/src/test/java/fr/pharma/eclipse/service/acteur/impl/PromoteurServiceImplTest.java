package fr.pharma.eclipse.service.acteur.impl;

import java.util.TreeSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.model.acteur.Promoteur;
import fr.pharma.eclipse.domain.model.suivi.acteur.PromoteurSuivi;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;

/**
 * Classe en charge de tester le service de gestion des promoteurs.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PromoteurServiceImplTest {

    /**
     * Service de gestion de promoteurs à tester.
     */
    private PromoteurServiceImpl service;

    /**
     * Dao mocké.
     */
    private GenericDao<Promoteur> mockDao;

    /**
     * Suivi Factory mocké.
     */
    private SuiviFactory<PromoteurSuivi> mockSuiviFactory;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.mockDao = Mockito.mock(GenericDao.class);
        this.service = new PromoteurServiceImpl(this.mockDao);
        this.mockSuiviFactory = Mockito.mock(SuiviFactory.class);
        this.service.setPromoteurSuiviFactory(this.mockSuiviFactory);
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
     * Méthode en charge de tester la sauvegarde des promoteurs.
     */
    @Test
    public void testSavePromoteur() {
        final Promoteur promoteur = Mockito.mock(Promoteur.class);
        final PromoteurSuivi suivi = new PromoteurSuivi();
        Mockito.when(this.mockDao.reattach(promoteur)).thenReturn(promoteur);
        Mockito.when(this.mockSuiviFactory.getInitializedObject()).thenReturn(suivi);
        Mockito.when(promoteur.getModifs()).thenReturn(new TreeSet<PromoteurSuivi>());
        Mockito.when(this.mockDao.save(promoteur)).thenReturn(promoteur);
        final Promoteur result = this.service.save(promoteur);
        Mockito.verify(this.mockDao).reattach(promoteur);
        Mockito.verify(this.mockSuiviFactory).getInitializedObject();
        Assert.assertEquals(1, result.getModifs().size());
    }

}
