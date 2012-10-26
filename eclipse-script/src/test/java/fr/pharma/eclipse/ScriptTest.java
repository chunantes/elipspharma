package fr.pharma.eclipse;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.pharma.eclipse.script.promoteur.PromoteurRunner;
import fr.pharma.eclipse.script.sigrec.SynchroRunner;

/**
 * Création du contexte Spring.
 
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
{"classpath*:config/applicationContext-script.xml" })
public class ScriptTest
{

    @Resource(name = "synchroRunner")
    private SynchroRunner synchroRunner;

    @Resource(name = "promoteurRunner")
    private PromoteurRunner promoteurRunner;

    @Before
    public void setUp()
    {

    }

    @Test
    public void testSynchro()
    {
        this.synchroRunner.run(new String[0]);
    }

    @Test
    public void testPromoteur()
    {
        this.promoteurRunner.run(new String[0]);
    }
}
