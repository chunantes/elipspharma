package fr.pharma.eclipse.filler.impl.sigrec.promoteur;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.sigrec.FonctionsType;
import fr.pharma.eclipse.domain.model.sigrec.IntervenantsType;
import fr.pharma.eclipse.domain.model.sigrec.SponsorType;
import fr.pharma.eclipse.domain.model.sigrec.IntervenantsType.Intervenant;
import fr.pharma.eclipse.domain.model.sigrec.acteur.ARCPromoteurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.PromoteurSigrec;
import fr.pharma.eclipse.filler.impl.sigrec.promoteur.ARCFiller;
import fr.pharma.eclipse.utils.converter.impl.GenericBeanConverter;

/**
 * Test du filler : ArcFiller.
 
 * @version $Revision$ $Date$
 */
public class ARCFillerTest
{
    /**
     * Filler.
     */
    private ARCFiller filler;

    /**
     * Converter d'intervenant mocké.
     */
    private GenericBeanConverter<Intervenant, ARCPromoteurSigrec> mockedConverter;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void setUp()
    {
        this.mockedConverter = Mockito.mock(GenericBeanConverter.class);
        this.filler = new ARCFiller();
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
        final SponsorType sponsor = new SponsorType();
        Assert.assertFalse(this.filler.support(sponsor));
        sponsor.setSponsorIntervenants(new IntervenantsType());
        Assert.assertTrue(this.filler.support(sponsor));
    }

    /**
     * Test de la méthode fill.
     */
    @Test
    public void testFillIndus()
    {
        final SponsorType source = this.initSource();
        final PromoteurSigrec destination = this.initDestination();

        Mockito
                .when(this.mockedConverter.convert(Matchers.any(Intervenant.class)))
                .thenReturn(new ARCPromoteurSigrec());

        this.filler.fill(source,
                         destination);

        Assert.assertEquals(1,
                            destination.getArcs().size());
        Assert.assertEquals(destination.getId(),
                            destination.getArcs().get(0).getPromoteur().getId());
    }

    /**
     * /** Méthode en charge d'initialiser la source.
     * @return La source.
     */
    private SponsorType initSource()
    {
        final SponsorType promoteur = new SponsorType();
        promoteur.setSponsorIntervenants(new IntervenantsType());
        final Intervenant i1 = new Intervenant();
        i1.setFonctions(new FonctionsType());
        i1
                .getFonctions()
                .getFonctionIdAndFonctionLibelle()
                .add(new JAXBElement<String>(new QName("fonction-id"),
                                             String.class,
                                             ARCFiller.FONCTION_ARC));
        final Intervenant i2 = new Intervenant();
        promoteur.getSponsorIntervenants().getIntervenant().add(i1);
        promoteur.getSponsorIntervenants().getIntervenant().add(i2);
        return promoteur;
    }
    /**
     * Méthode en charge d'initialiser la destination.
     * @return La destination.
     */
    private PromoteurSigrec initDestination()
    {
        final PromoteurSigrec arc = new PromoteurSigrec();
        arc.setId(1L);
        return arc;
    }
}
