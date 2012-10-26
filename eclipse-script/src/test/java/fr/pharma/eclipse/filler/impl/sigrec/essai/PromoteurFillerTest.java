package fr.pharma.eclipse.filler.impl.sigrec.essai;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.sigrec.acteur.PromoteurSigrecSearchCriteria;
import fr.pharma.eclipse.domain.model.sigrec.SponsorInformationType;
import fr.pharma.eclipse.domain.model.sigrec.SponsorType;
import fr.pharma.eclipse.domain.model.sigrec.TrialType;
import fr.pharma.eclipse.domain.model.sigrec.acteur.PromoteurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Test du filler : PromoteurFiller.
 
 * @version $Revision$ $Date$
 */
public class PromoteurFillerTest
{
    /**
     * Filler à tester.
     */
    private PromoteurFiller filler;

    /**
     * Service promoteur sigrec mocké.
     */
    private GenericService<PromoteurSigrec> promoteurService;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void setUp()
    {
        this.promoteurService = Mockito.mock(GenericService.class);
        this.filler = new PromoteurFiller();
        this.filler.setPromoteurService(this.promoteurService);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void tearDown()
    {
        this.promoteurService = null;
        this.filler = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.filler);
        Assert.assertEquals(this.promoteurService,
                            this.filler.getPromoteurService());
    }

    @Test
    public void testSupport()
    {
        final TrialType essai = new TrialType();
        Assert.assertFalse(this.filler.support(essai));
        essai.setSponsorInformation(new SponsorInformationType());
        Assert.assertTrue(this.filler.support(essai));
    }
    /**
     * Test de la méthode fill().
     */
    @Test
    public void testFill()
    {
        final TrialType source = this.initTrial();
        final EssaiSigrec destination = this.initEssai();

        final List<PromoteurSigrec> result = new ArrayList<PromoteurSigrec>();
        final PromoteurSigrec promoteur = new PromoteurSigrec();
        promoteur.setId(1L);
        result.add(promoteur);

        Mockito.when(this.promoteurService.getAll(Matchers
                .any(PromoteurSigrecSearchCriteria.class))).thenReturn(result);

        this.filler.fill(source,
                         destination);

        Assert.assertEquals(promoteur.getId(),
                            destination.getPromoteur().getId());
        Assert.assertEquals(promoteur.getType(),
                            destination.getPromoteur().getType());
    }

    /**
     * Initialise la source.
     * @return la source.
     */
    private EssaiSigrec initEssai()
    {
        final EssaiSigrec essai = new EssaiSigrec();
        return essai;
    }

    /**
     * Initialise la destination.
     * @return la destination.
     */
    private TrialType initTrial()
    {

        final TrialType trial = new TrialType();
        trial.setSponsorInformation(new SponsorInformationType());
        trial.getSponsorInformation().getSponsor().add(new SponsorType());
        return trial;
    }
}
