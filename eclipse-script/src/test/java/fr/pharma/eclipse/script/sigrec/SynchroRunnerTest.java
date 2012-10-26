package fr.pharma.eclipse.script.sigrec;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import fr.pharma.eclipse.processor.impl.SynchroProcessor;
import fr.pharma.eclipse.script.ScriptParameterBuilder;

/**
 * Test du runner SynchroRunner.
 
 * @version $Revision$ $Date$
 */
public class SynchroRunnerTest
{
    /**
     * Runner à tester.
     */
    private SynchroRunner runner;

    /**
     * Processor mocké.
     */
    private SynchroProcessor processor;

    /**
     * Script parameter builder.
     */
    private ScriptParameterBuilder scriptParameterBuilder;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void setUp()
    {
        this.scriptParameterBuilder = Mockito.mock(ScriptParameterBuilder.class);
        this.processor = Mockito.mock(SynchroProcessor.class);
        this.runner = new SynchroRunner();
        this.runner.setSynchroProcessor(this.processor);
        this.runner.setScriptParameterBuilder(this.scriptParameterBuilder);
    }
    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void tearDown()
    {
        this.scriptParameterBuilder = null;
        this.processor = null;
        this.runner = null;
    }

    /**
     *Test init.
     */
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.runner);
        Assert.assertEquals(this.processor,
                            this.runner.getSynchroProcessor());
        Assert.assertEquals(this.scriptParameterBuilder,
                            this.runner.getScriptParameterBuilder());
    }
    /**
     * Test de la méthode run().
     */
    @Test
    public void testRun()
    {

        final String[] args = new String[1];
        args[0] = null;
        final SynchroParameter sp = new SynchroParameter();
        Mockito
                .doAnswer(new Answer() {
                    public Object answer(final InvocationOnMock invocation)
                    {
                        final Object[] args = invocation.getArguments();
                        final SynchroParameter param = (SynchroParameter) args[1];
                        param.setFilePath("");
                        return null;
                    }
                })
                .when(this.scriptParameterBuilder)
                .getScriptParameter(Matchers.any(String[].class),
                                    Matchers.any(SynchroParameter.class));
        this.runner.run(args);
        Mockito.verify(this.scriptParameterBuilder,
                       Mockito.times(1)).getScriptParameter(Matchers.any(String[].class),
                                                            Matchers.any(SynchroParameter.class));
        Mockito.verify(this.processor,
                       Mockito.times(1)).process();
        Mockito.verify(this.processor,
                       Mockito.times(0)).setCheminFichier(Matchers.anyString());
    }
    /**
     * Test de la méthode run().
     */
    @Test
    public void testRunWithArg()
    {

        final String[] args = new String[1];
        args[0] = null;
        final SynchroParameter sp = new SynchroParameter();
        Mockito
                .doAnswer(new Answer() {
                    public Object answer(final InvocationOnMock invocation)
                    {
                        final Object[] args = invocation.getArguments();
                        final SynchroParameter param = (SynchroParameter) args[1];
                        param.setFilePath("dddd");
                        return null;
                    }
                })
                .when(this.scriptParameterBuilder)
                .getScriptParameter(Matchers.any(String[].class),
                                    Matchers.any(SynchroParameter.class));
        this.runner.run(args);
        Mockito.verify(this.scriptParameterBuilder,
                       Mockito.times(1)).getScriptParameter(Matchers.any(String[].class),
                                                            Matchers.any(SynchroParameter.class));
        Mockito.verify(this.processor,
                       Mockito.times(1)).process();
        Mockito.verify(this.processor,
                       Mockito.times(1)).setCheminFichier(Matchers.anyString());
    }
}
