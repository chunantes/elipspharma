package fr.pharma.eclipse.filler.impl.sigrec.essai;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.sigrec.InvestigatorType;
import fr.pharma.eclipse.domain.model.sigrec.ProposedClinicalTrialSitesType;
import fr.pharma.eclipse.domain.model.sigrec.TrialType;
import fr.pharma.eclipse.domain.model.sigrec.acteur.ARCInvestigateurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.CentreSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.CoInvestigateurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.InvestigateurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.converter.impl.GenericBeanConverter;

/**
 * Test du filler : CentreFiller.
 
 * @version $Revision$ $Date$
 */
public class CentreFillerTest
{
    /**
     * Filler.
     */
    private CentreFiller filler;

    /**
     * Converter de centre.
     */
    private GenericBeanConverter<InvestigatorType, CentreSigrec> mockedConverter;

    /**
     * Service CentreSigrec mocké.
     */
    private GenericService<CentreSigrec> centreService;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void setUp()
    {
        this.mockedConverter = Mockito.mock(GenericBeanConverter.class);
        this.centreService = Mockito.mock(GenericService.class);
        this.filler = new CentreFiller();
        this.filler.setConverter(this.mockedConverter);
        this.filler.setCentreService(this.centreService);
    }
    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void tearDown()
    {
        this.mockedConverter = null;
        this.centreService = null;
        this.filler = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.filler);
        Assert.assertNotNull(this.mockedConverter);
        Assert.assertEquals(this.mockedConverter,
                            this.filler.getConverter());
        Assert.assertEquals(this.centreService,
                            this.filler.getCentreService());
    }

    @Test
    public void testSupport()
    {
        final TrialType trial = new TrialType();
        Assert.assertFalse(this.filler.support(trial));
        trial.setProposedClinicalTrialSites(new ProposedClinicalTrialSitesType());
        Assert.assertTrue(this.filler.support(trial));
    }

    /**
     * Test de la méthode fill.
     */
    @Test
    public void testFill()
    {
        final TrialType source = this.initSource();
        final EssaiSigrec destination = this.initDestination();

        final CentreSigrec centre = new CentreSigrec();
        centre.getArcInvestigateurs().add(new ARCInvestigateurSigrec());
        centre.getInvestigateurs().add(new InvestigateurSigrec());
        centre.getCoInvestigateurs().add(new CoInvestigateurSigrec());

        Mockito
                .when(this.mockedConverter.convert(Matchers.any(InvestigatorType.class)))
                .thenReturn(centre);

        this.filler.fill(source,
                         destination);

        Mockito.verify(this.centreService,
                       Mockito.times(1)).save(Matchers.any(CentreSigrec.class));

        Assert.assertEquals(1,
                            destination.getArcInvestigateurs().size());
        Assert.assertEquals(destination.getId(),
                            destination.getArcInvestigateurs().get(0).getEssai().getId());
        Assert.assertEquals(destination.getId(),
                            destination.getCoInvestigateurs().get(0).getEssai().getId());
        Assert.assertEquals(centre.getInvestigateurs().get(0).getId(),
                            destination.getInvestigateurPrincipal().getId());
    }

    /**
     * /** Méthode en charge d'initialiser la source.
     * @return La source.
     */
    private TrialType initSource()
    {
        final TrialType trial = new TrialType();
        trial.setProposedClinicalTrialSites(new ProposedClinicalTrialSitesType());
        final InvestigatorType i1 = new InvestigatorType();

        trial.getProposedClinicalTrialSites().getInvestigator().add(i1);
        return trial;
    }
    /**
     * Méthode en charge d'initialiser la destination.
     * @return La destination.
     */
    private EssaiSigrec initDestination()
    {
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setId(1L);
        return essai;
    }
}
