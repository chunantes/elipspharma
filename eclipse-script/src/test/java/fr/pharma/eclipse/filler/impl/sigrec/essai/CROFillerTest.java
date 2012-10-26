package fr.pharma.eclipse.filler.impl.sigrec.essai;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.sigrec.CroType;
import fr.pharma.eclipse.domain.model.sigrec.OrganizationType;
import fr.pharma.eclipse.domain.model.sigrec.RegulatoryInformationType;
import fr.pharma.eclipse.domain.model.sigrec.TrialType;
import fr.pharma.eclipse.domain.model.sigrec.acteur.CROSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.utils.converter.impl.GenericBeanConverter;

/**
 * Test du filler : CROFiller.
 
 * @version $Revision$ $Date$
 */
public class CROFillerTest
{
    /**
     * Filler.
     */
    private CROFiller filler;

    /**
     * Converter d'intervenant mocké.
     */
    private GenericBeanConverter<OrganizationType, CROSigrec> mockedConverter;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void setUp()
    {
        this.mockedConverter = Mockito.mock(GenericBeanConverter.class);
        this.filler = new CROFiller();
        this.filler.setConverter(this.mockedConverter);
    }
    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void tearDown()
    {
        this.mockedConverter = null;
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
    }

    @Test
    public void testSupport()
    {
        final TrialType trial = new TrialType();
        Assert.assertFalse(this.filler.support(trial));
        trial.setRegulatoryInformation(new RegulatoryInformationType());
        Assert.assertFalse(this.filler.support(trial));
        trial.getRegulatoryInformation().setCro(new CroType());
        Assert.assertTrue(this.filler.support(trial));
    }

    /**
     * Test de la méthode fill.
     */
    @Test
    public void testFillIndus()
    {
        final TrialType source = this.initSource();
        final EssaiSigrec destination = this.initDestination();

        Mockito
                .when(this.mockedConverter.convert(Matchers.any(OrganizationType.class)))
                .thenReturn(new CROSigrec());

        this.filler.fill(source,
                         destination);

        Assert.assertEquals(1,
                            destination.getCros().size());
        Assert.assertEquals(destination.getId(),
                            destination.getCros().get(0).getEssai().getId());
    }

    /**
     * /** Méthode en charge d'initialiser la source.
     * @return La source.
     */
    private TrialType initSource()
    {
        final TrialType trial = new TrialType();
        trial.setRegulatoryInformation(new RegulatoryInformationType());
        trial.getRegulatoryInformation().setCro(new CroType());
        final OrganizationType o1 = new OrganizationType();
        trial.getRegulatoryInformation().getCro().getOrganization().add(o1);
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
