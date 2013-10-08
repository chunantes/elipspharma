package fr.pharma.eclipse.utils.introspection.fetcher;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.NatureRecherche;
import fr.pharma.eclipse.domain.enums.ObjetRecherche;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.recherche.DetailRecherche;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.detail.DetailRechercheSigrec;
import fr.pharma.eclipse.utils.introspection.GenericFetcher;

/**
 * Classe de test de GenericFetcher.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GenericFetcherTest {
    /**
     * Fetcher à tester.
     */
    private GenericFetcher<EssaiSigrec, Essai> fetcher;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void setUp() {
        this.fetcher = new GenericFetcher<EssaiSigrec, Essai>();
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
        this.fetcher.setMapping(this.initMapping());
        final EssaiSigrec essaiSigrec = new EssaiSigrec();
        essaiSigrec.setNom("nom");
        final DetailRechercheSigrec detailSigrec = new DetailRechercheSigrec();
        detailSigrec.setNatureRecherche(NatureRecherche.AUTRE);
        detailSigrec.setNumEnregistrement("numero");
        detailSigrec.setObjetRecherche(ObjetRecherche.ALICAMENT);
        essaiSigrec.setDetailRecherche(detailSigrec);

        final Essai essai = new Essai();
        essai.setDetailRecherche(new DetailRecherche());

        this.fetcher.fetch(essaiSigrec, essai);
        Assert.assertEquals(essaiSigrec.getNom(), essai.getNom());
        Assert.assertEquals(essaiSigrec.getDetailRecherche().getNumEnregistrement(), essai.getDetailRecherche().getNumEnregistrement());
        Assert.assertEquals(essaiSigrec.getDetailRecherche().getNatureRecherche(), essai.getDetailRecherche().getNatureRecherche());
        Assert.assertEquals(essaiSigrec.getDetailRecherche().getObjetRecherche(), essai.getDetailRecherche().getObjetRecherche());

    }
    private Map<String, String> initMapping() {
        final Map<String, String> mapping = new HashMap<String, String>();
        mapping.put("nom", "nom");
        mapping.put("detailRecherche.numEnregistrement", "detailRecherche.numEnregistrement");
        mapping.put("detailRecherche.objetRecherche", "detailRecherche.objetRecherche");
        mapping.put("detailRecherche.natureRecherche", "detailRecherche.natureRecherche");
        return mapping;
    }
}
