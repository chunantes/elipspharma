package fr.pharma.eclipse.component.stock.helper;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.stock.NumTraitement;

/**
 * Classe en charge de tester la saisie automatique des numéros de traitement.
 
 * @version $Revision$ $Date$
 */
public class AutoSaisieNumTraitementTest
{
    /**
     * AutoSaisieNumTraitement à tester.
     */
    private AutoSaisieNumTraitement autoSaisieNumTraitement;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init()
    {
        this.autoSaisieNumTraitement = new AutoSaisieNumTraitement();
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end()
    {
        this.autoSaisieNumTraitement = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.autoSaisieNumTraitement);
    }

    /**
     * Méthode en charge de tester la saisie automatique.
     */
    @Test
    public void testHandle1()
    {
        final List<NumTraitement> numsTraitements = new ArrayList<NumTraitement>();
        for (int i = 0; i <= 2; i++)
        {
            numsTraitements.add(new NumTraitement());
        }
        numsTraitements.get(0).setNumTraitement("CEL-01");

        this.autoSaisieNumTraitement.handle(numsTraitements);

        Assert.assertEquals("CEL-01",
                            numsTraitements.get(0).getNumTraitement());
        Assert.assertEquals("CEL-02",
                            numsTraitements.get(1).getNumTraitement());
        Assert.assertEquals("CEL-03",
                            numsTraitements.get(2).getNumTraitement());
    }

    /**
     * Méthode en charge de tester la saisie automatique.
     */
    @Test
    public void testHandle2()
    {
        final List<NumTraitement> numsTraitements = new ArrayList<NumTraitement>();
        for (int i = 0; i <= 2; i++)
        {
            numsTraitements.add(new NumTraitement());
        }
        numsTraitements.get(0).setNumTraitement("CEL-0");

        this.autoSaisieNumTraitement.handle(numsTraitements);

        Assert.assertEquals("CEL-0",
                            numsTraitements.get(0).getNumTraitement());
        Assert.assertEquals("CEL-1",
                            numsTraitements.get(1).getNumTraitement());
        Assert.assertEquals("CEL-2",
                            numsTraitements.get(2).getNumTraitement());
    }

    /**
     * Méthode en charge de tester la saisie automatique.
     */
    @Test
    public void testHandle3()
    {
        final List<NumTraitement> numsTraitements = new ArrayList<NumTraitement>();
        for (int i = 0; i <= 2; i++)
        {
            numsTraitements.add(new NumTraitement());
        }
        numsTraitements.get(0).setNumTraitement("00-A-000A012");

        this.autoSaisieNumTraitement.handle(numsTraitements);

        Assert.assertEquals("00-A-000A012",
                            numsTraitements.get(0).getNumTraitement());
        Assert.assertEquals("00-A-000A013",
                            numsTraitements.get(1).getNumTraitement());
        Assert.assertEquals("00-A-000A014",
                            numsTraitements.get(2).getNumTraitement());
    }

    /**
     * Méthode en charge de tester la saisie automatique.
     */
    @Test
    public void testHandle4()
    {
        final List<NumTraitement> numsTraitements = new ArrayList<NumTraitement>();
        for (int i = 0; i <= 2; i++)
        {
            numsTraitements.add(new NumTraitement());
        }
        numsTraitements.get(0).setNumTraitement("00-A-000A098");

        this.autoSaisieNumTraitement.handle(numsTraitements);

        Assert.assertEquals("00-A-000A098",
                            numsTraitements.get(0).getNumTraitement());
        Assert.assertEquals("00-A-000A099",
                            numsTraitements.get(1).getNumTraitement());
        Assert.assertEquals("00-A-000A100",
                            numsTraitements.get(2).getNumTraitement());
    }

    /**
     * Méthode en charge de tester la saisie automatique.
     */
    @Test
    public void testHandle5()
    {
        final List<NumTraitement> numsTraitements = new ArrayList<NumTraitement>();
        for (int i = 0; i <= 2; i++)
        {
            numsTraitements.add(new NumTraitement());
        }
        numsTraitements.get(0).setNumTraitement("19");

        this.autoSaisieNumTraitement.handle(numsTraitements);

        Assert.assertEquals("19",
                            numsTraitements.get(0).getNumTraitement());
        Assert.assertEquals("20",
                            numsTraitements.get(1).getNumTraitement());
        Assert.assertEquals("21",
                            numsTraitements.get(2).getNumTraitement());
    }

    /**
     * Méthode en charge de tester le lancement de la saisie.
     */
    @Test
    public void testLaunchSaisieIndex0KO()
    {
        final List<NumTraitement> numsTraitements = new ArrayList<NumTraitement>();
        for (int i = 0; i <= 2; i++)
        {
            numsTraitements.add(new NumTraitement());
        }
        this.autoSaisieNumTraitement.handle(numsTraitements);
        Assert.assertNull(numsTraitements.get(0).getNumTraitement());
        Assert.assertNull(numsTraitements.get(1).getNumTraitement());
        Assert.assertNull(numsTraitements.get(2).getNumTraitement());
    }

    /**
     * Méthode en charge de tester le lancement de la saisie.
     */
    @Test
    public void testLaunchSaisieIndexOtherKO()
    {
        final List<NumTraitement> numsTraitements = new ArrayList<NumTraitement>();
        for (int i = 0; i <= 2; i++)
        {
            numsTraitements.add(new NumTraitement());
        }
        numsTraitements.get(0).setNumTraitement("00-A-000A098");
        numsTraitements.get(1).setNumTraitement("toto");
        this.autoSaisieNumTraitement.handle(numsTraitements);
        Assert.assertEquals("00-A-000A098",
                            numsTraitements.get(0).getNumTraitement());
        Assert.assertEquals("toto",
                            numsTraitements.get(1).getNumTraitement());
        Assert.assertNull(numsTraitements.get(2).getNumTraitement());
    }

}
