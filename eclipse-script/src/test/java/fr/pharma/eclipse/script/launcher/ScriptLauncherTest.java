package fr.pharma.eclipse.script.launcher;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.script.ScriptRunner;

/**
 * Test du launcher ScriptLauncher.
 
 * @version $Revision$ $Date$
 */
public class ScriptLauncherTest
{
    /**
     * Launcher à tester.
     */
    private ScriptLauncher launcher;

    /**
     * Runner mocké.
     */
    private ScriptRunner runner;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void setUp()
    {
        this.runner = Mockito.mock(ScriptRunner.class);
        this.launcher = new ScriptLauncher();
        final Map<String, ScriptRunner> map = new HashMap<String, ScriptRunner>();
        map.put("runner",
                this.runner);
        this.launcher.setScriptRunnerByScriptNames(map);
    }
    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void tearDown()
    {
        this.runner = null;
        this.launcher = null;
    }

    /**
     *Test init.
     */
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.runner);
    }
    /**
     * Test de la méthode run().
     */
    @Test
    public void testRun()
    {
        this.launcher.run("runner",
                          null);
        Mockito.verify(this.runner).run(null);
    }
}
