package fr.pharma.eclipse.filler.impl.eclipse;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.ArcPromoteur;
import fr.pharma.eclipse.domain.model.acteur.Promoteur;
import fr.pharma.eclipse.domain.model.sigrec.acteur.ARCInvestigateurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.ARCPromoteurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.CentreSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.CoInvestigateurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.InvestigateurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.PromoteurSigrec;
import fr.pharma.eclipse.utils.converter.BeanConverter;
import fr.pharma.eclipse.utils.converter.impl.GenericBeanConverter;

/**
 * Test du filler : ARCPromoteurEclipseFiller.
 
 * @version $Revision$ $Date$
 */
public class ARCPromoteurEclipseFillerTest
{
    /**
     * Filler.
     */
    private ARCPromoteurFiller filler;

    /**
     * Converter de centre.
     */
    private BeanConverter<ARCPromoteurSigrec, ArcPromoteur> mockedConverter;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void setUp()
    {
        this.mockedConverter = Mockito.mock(GenericBeanConverter.class);
        this.filler = new ARCPromoteurFiller();
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
        Assert.assertFalse(this.filler.support(null));
        final PromoteurSigrec prom = Mockito.mock(PromoteurSigrec.class);
        Mockito.when(prom.getArcs()).thenReturn(null);
        Assert.assertFalse(this.filler.support(prom));
        Mockito.when(prom.getArcs()).thenReturn(new ArrayList<ARCPromoteurSigrec>());
        Assert.assertTrue(this.filler.support(prom));
    }

    /**
     * Test de la méthode fill.
     */
    @Test
    public void testFill()
    {
        final PromoteurSigrec source = this.initSource();
        final Promoteur destination = this.initDestination();

        final CentreSigrec centre = new CentreSigrec();
        centre.getArcInvestigateurs().add(new ARCInvestigateurSigrec());
        centre.getInvestigateurs().add(new InvestigateurSigrec());
        centre.getCoInvestigateurs().add(new CoInvestigateurSigrec());

        Mockito
                .when(this.mockedConverter.convert(Matchers.any(ARCPromoteurSigrec.class)))
                .thenReturn(new ArcPromoteur());

        this.filler.fill(source,
                         destination);

        Mockito.verify(this.mockedConverter).convert(Matchers.any(ARCPromoteurSigrec.class));
        Assert.assertEquals(destination.getId(),
                            destination.getArcPromoteurs().first().getPromoteur().getId());
        Assert.assertEquals(TypePersonne.ARC_PROMOTEUR,
                            destination.getArcPromoteurs().first().getType());
    }
    /**
     * /** Méthode en charge d'initialiser la source.
     * @return La source.
     */
    private PromoteurSigrec initSource()
    {
        final PromoteurSigrec p = new PromoteurSigrec();
        p.getArcs().add(new ARCPromoteurSigrec());
        return p;
    }
    /**
     * Méthode en charge d'initialiser la destination.
     * @return La destination.
     */
    private Promoteur initDestination()
    {
        final Promoteur essai = new Promoteur();
        essai.setId(1L);
        return essai;
    }
}
