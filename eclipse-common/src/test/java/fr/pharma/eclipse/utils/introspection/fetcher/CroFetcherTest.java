package fr.pharma.eclipse.utils.introspection.fetcher;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.pharma.eclipse.domain.model.acteur.Cro;
import fr.pharma.eclipse.domain.model.sigrec.acteur.CROSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.ContactSigrec;
import fr.pharma.eclipse.utils.introspection.GenericFetcher;

/**
 * Classe de test de GenericFetcher pour le bean Assurance.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
public class CroFetcherTest {
    /**
     * Fetcher à tester.
     */
    @Resource(name = "croFetcher")
    private GenericFetcher<CROSigrec, Cro> fetcher;

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
        final CROSigrec source = this.initSource();
        final Cro destination = this.initDestination();

        this.fetcher.fetch(source, destination);
        Assert.assertEquals(source.getContact().getAdresse(), destination.getAdresse());
        Assert.assertEquals(source.getContact().getCodePostal(), destination.getCodePostal());
        Assert.assertEquals(source.getContact().getRaisonSociale(), destination.getNom());
        Assert.assertEquals(source.getContact().getRaisonSociale(), destination.getNomSociete());
        Assert.assertEquals(source.getContact().getVille(), destination.getVille());

    }

    /**
     * Méthode en charge d'initialiser le bean source.
     * @return
     */
    private CROSigrec initSource() {
        final CROSigrec source = new CROSigrec();

        final ContactSigrec contact = new ContactSigrec();
        contact.setRaisonSociale("raison");
        contact.setVille("ville");
        contact.setCodePostal("code");
        contact.setAdresse("adresse");
        source.setContact(contact);
        return source;
    }

    /**
     * Méthode en charge d'initialiser le bean destination.
     * @return le bean destination
     */
    private Cro initDestination() {
        return new Cro();
    }
}
