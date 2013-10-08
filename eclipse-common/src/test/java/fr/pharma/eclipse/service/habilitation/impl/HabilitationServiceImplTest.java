package fr.pharma.eclipse.service.habilitation.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;

/**
 * Classe en charge de tester le service de gestion des établissements.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class HabilitationServiceImplTest {

    /**
     * Service de gestion de habilitations à tester.
     */
    private HabilitationServiceImpl service;

    /**
     * Dao mocké.
     */
    private GenericDao<Habilitation> mockDao;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.mockDao = Mockito.mock(GenericDao.class);
        this.service = new HabilitationServiceImpl(this.mockDao);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.mockDao = null;
        this.service = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.service);
        Assert.assertNotNull(this.mockDao);
    }

    /**
     * Méthode en charge de tester la sauvegarde des habilitations.
     */
    @Test
    public void testSaveHabilitation() {
        final Habilitation habilitation = Mockito.mock(Habilitation.class);
        Mockito.when(this.mockDao.reattach(habilitation)).thenReturn(habilitation);
        Mockito.when(this.mockDao.save(habilitation)).thenReturn(habilitation);
        final Habilitation result = this.service.save(habilitation);
        Mockito.verify(this.mockDao).reattach(habilitation);
        Mockito.verify(this.mockDao).save(habilitation);
        Assert.assertEquals(habilitation, result);
    }

}
