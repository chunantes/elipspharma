package fr.pharma.eclipse.component.design;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.component.design.helper.TreeDesignHelper;
import fr.pharma.eclipse.component.essai.EssaiManager;

/**
 * Classe en charge de tester le manager de Pharmacie.
 
 * @version $Revision$ $Date$
 */
public class DesignManagerTest
{
    /**
     * PharmacieManager à tester.
     */
    private DesignsManager manager;

    /**
     * Mock du helper de gestion de design sous forme d'arbre.
     */
    private TreeDesignHelper mockTreeDesignHelper;

    /**
     * Mock du manager mockEssaiManager.
     */
    private EssaiManager mockedEssaiManager;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init()
    {
        this.mockedEssaiManager = Mockito.mock(EssaiManager.class);
        this.mockTreeDesignHelper = Mockito.mock(TreeDesignHelper.class);
        this.manager = new DesignsManager();
        this.manager.setTreeDesignHelper(this.mockTreeDesignHelper);
        this.manager.setEssaiManager(this.mockedEssaiManager);
    }
    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end()
    {
        this.mockedEssaiManager = null;
        this.manager = null;
        this.mockTreeDesignHelper = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.mockedEssaiManager);
        Assert.assertNotNull(this.manager);
        Assert.assertNotNull(this.mockTreeDesignHelper);
    }

}
