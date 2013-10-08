package fr.pharma.eclipse.utils.introspection.fetcher;

import java.util.Calendar;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.pharma.eclipse.domain.model.essai.detail.administratif.embedded.InfosAssurance;
import fr.pharma.eclipse.domain.model.sigrec.acteur.ContactSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.detail.AssuranceSigrec;
import fr.pharma.eclipse.utils.constants.EclipseConstants;
import fr.pharma.eclipse.utils.introspection.GenericFetcher;

/**
 * Classe de test de GenericFetcher pour le bean Assurance.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
public class AssuranceFetcherTest {
    /**
     * Fetcher à tester.
     */
    @Resource(name = "assuranceFetcher")
    private GenericFetcher<AssuranceSigrec, InfosAssurance> fetcher;

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
        final AssuranceSigrec source = this.initSource();
        final InfosAssurance destination = this.initDestination();

        this.fetcher.fetch(source, destination);
        Assert.assertEquals(source.getDateDebutValidite(), destination.getDateDebutValidite());
        Assert.assertEquals(source.getDateFinValidite(), destination.getDateFinValidite());
        Assert.assertEquals(source.getNumeroContrat(), destination.getNumeroContrat());
        Assert.assertEquals(source.getContact().getNom(), destination.getNomCompagnie());
        Assert.assertEquals(source.getContact().getVille(), destination.getVille());
        Assert.assertEquals(source.getContact().getCodePostal(), destination.getCodePostal());

    }

    /**
     * Méthode en charge d'initialiser le bean source.
     * @return L'assurance Sigrec.
     */
    private AssuranceSigrec initSource() {
        final AssuranceSigrec source = new AssuranceSigrec();
        source.setDateDebutValidite(Calendar.getInstance(EclipseConstants.LOCALE));
        source.setDateDebutValidite(Calendar.getInstance(EclipseConstants.LOCALE));
        source.setNumeroContrat("numero");
        final ContactSigrec contact = new ContactSigrec();
        contact.setNom("assurance");
        contact.setVille("ville");
        contact.setCodePostal("code");
        source.setContact(contact);
        return source;
    }

    /**
     * Méthode en charge d'initialiser le bean destination.
     * @return le bean destination
     */
    private InfosAssurance initDestination() {
        return new InfosAssurance();
    }
}
