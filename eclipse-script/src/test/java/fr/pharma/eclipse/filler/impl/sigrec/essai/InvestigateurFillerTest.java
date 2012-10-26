package fr.pharma.eclipse.filler.impl.sigrec.essai;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.sigrec.FonctionsType;
import fr.pharma.eclipse.domain.model.sigrec.IntervenantType;
import fr.pharma.eclipse.domain.model.sigrec.IntervenantsType;
import fr.pharma.eclipse.domain.model.sigrec.InvestigatorType;
import fr.pharma.eclipse.domain.model.sigrec.IntervenantsType.Intervenant;
import fr.pharma.eclipse.domain.model.sigrec.acteur.ARCInvestigateurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.CentreSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.CoInvestigateurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.InvestigateurSigrec;
import fr.pharma.eclipse.utils.converter.impl.GenericBeanConverter;

/**
 * Test du filler : InvestigateurFiller.
 
 * @version $Revision$ $Date$
 */
public class InvestigateurFillerTest
{
    /**
     * Filler.
     */
    private InvestigateurFiller filler;

    /**
     * Converter arcInvestigateur.
     */
    private GenericBeanConverter<IntervenantType, ARCInvestigateurSigrec> mockedArcConverter;

    /**
     * Converter coinvesigateur.
     */
    private GenericBeanConverter<IntervenantType, CoInvestigateurSigrec> mockedCoConverter;

    /**
     * Converter investigateur.
     */
    private GenericBeanConverter<IntervenantType, InvestigateurSigrec> mockedConverter;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void setUp()
    {
        this.mockedConverter = Mockito.mock(GenericBeanConverter.class);
        this.mockedArcConverter = Mockito.mock(GenericBeanConverter.class);
        this.mockedCoConverter = Mockito.mock(GenericBeanConverter.class);
        this.filler = new InvestigateurFiller();
        this.filler.setInvestigateurConverter(this.mockedConverter);
        this.filler.setCoInvestigateurConverter(this.mockedCoConverter);
        this.filler.setArcInvestigateurConverter(this.mockedArcConverter);
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
                            this.filler.getInvestigateurConverter());
        Assert.assertEquals(this.mockedCoConverter,
                            this.filler.getCoInvestigateurConverter());
        Assert.assertEquals(this.mockedArcConverter,
                            this.filler.getArcInvestigateurConverter());
    }

    @Test
    public void testSupport()
    {
        final InvestigatorType centre = new InvestigatorType();
        Assert.assertFalse(this.filler.support(centre));
        centre.setIntervenants(new IntervenantsType());
        Assert.assertTrue(this.filler.support(centre));
    }

    /**
     * Test de la méthode fill.
     */
    @Test
    public void testFill()
    {
        final InvestigatorType source = this.initSource();
        final CentreSigrec destination = this.initDestination();

        Mockito
                .when(this.mockedConverter.convert(Matchers.any(Intervenant.class)))
                .thenReturn(new InvestigateurSigrec());

        Mockito
                .when(this.mockedCoConverter.convert(Matchers.any(Intervenant.class)))
                .thenReturn(new CoInvestigateurSigrec());

        Mockito
                .when(this.mockedArcConverter.convert(Matchers.any(Intervenant.class)))
                .thenReturn(new ARCInvestigateurSigrec());

        this.filler.fill(source,
                         destination);

        Assert.assertEquals(1,
                            destination.getArcInvestigateurs().size());
        Assert.assertEquals(destination.getId(),
                            destination.getArcInvestigateurs().get(0).getCentre().getId());
        Assert.assertEquals(destination.getId(),
                            destination.getCoInvestigateurs().get(0).getCentre().getId());
        Assert.assertEquals(destination.getId(),
                            destination.getInvestigateurs().get(0).getCentre().getId());
    }

    /**
     * /** Méthode en charge d'initialiser la source.
     * @return La source.
     */
    private InvestigatorType initSource()
    {
        final InvestigatorType trial = new InvestigatorType();
        trial.setIntervenants(new IntervenantsType());
        final Intervenant i1 = new Intervenant();
        i1.setFonctions(new FonctionsType());
        i1
                .getFonctions()
                .getFonctionIdAndFonctionLibelle()
                .add(new JAXBElement<String>(new QName("fonction-id"),
                                             String.class,
                                             InvestigateurFiller.FONCTION_ARC));
        final Intervenant i2 = new Intervenant();
        i2.setFonctions(new FonctionsType());
        i2
                .getFonctions()
                .getFonctionIdAndFonctionLibelle()
                .add(new JAXBElement<String>(new QName("fonction-id"),
                                             String.class,
                                             InvestigateurFiller.FONCTION_ASSO));
        final Intervenant i3 = new Intervenant();
        i3.setFonctions(new FonctionsType());
        i3
                .getFonctions()
                .getFonctionIdAndFonctionLibelle()
                .add(new JAXBElement<String>(new QName("fonction-id"),
                                             String.class,
                                             InvestigateurFiller.FONCTION_PRINCIPAL));
        trial.getIntervenants().getIntervenant().add(i1);
        trial.getIntervenants().getIntervenant().add(i2);
        trial.getIntervenants().getIntervenant().add(i3);
        return trial;
    }
    /**
     * Méthode en charge d'initialiser la destination.
     * @return La destination.
     */
    private CentreSigrec initDestination()
    {
        final CentreSigrec centre = new CentreSigrec();
        centre.setId(1L);
        return centre;
    }
}
