package fr.pharma.eclipse.utils.introspection.fetcher;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.sigrec.acteur.ContactSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.InvestigateurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.common.IntervenantSigrec;
import fr.pharma.eclipse.utils.introspection.GenericFetcher;

/**
 * Classe de test de GenericFetcher pour le bean Investigateur.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
public class InvestigateurFetcherTest {
    /**
     * Fetcher à tester.
     */
    @Resource(name = "investigateurFetcher")
    private GenericFetcher<IntervenantSigrec, Investigateur> fetcher;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void setUp() {
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void tearDown() {
        this.fetcher = null;
    }

    /**
     * Test de la méthode Fetch().
     */
    @Test
    public void testFetch() {
        final InvestigateurSigrec source = this.initSource();
        final Investigateur destination = this.initDestination();

        this.fetcher.fetch(source, destination);
        Assert.assertEquals(source.getContact().getNom(), destination.getNom());
        Assert.assertEquals(source.getContact().getPrenom(), destination.getPrenom());

    }

    /**
     * Méthode en charge d'initialiser le bean source.
     * @return
     */
    private InvestigateurSigrec initSource() {
        final InvestigateurSigrec source = new InvestigateurSigrec();
        final ContactSigrec contact = new ContactSigrec();
        contact.setNom("nom");
        contact.setPrenom("prenom");
        source.setContact(contact);
        return source;
    }

    /**
     * Méthode en charge d'initialiser le bean destination.
     * @return le bean destination
     */
    private Investigateur initDestination() {
        return new Investigateur();
    }
}
