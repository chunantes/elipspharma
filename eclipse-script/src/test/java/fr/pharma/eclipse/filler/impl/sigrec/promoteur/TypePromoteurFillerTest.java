package fr.pharma.eclipse.filler.impl.sigrec.promoteur;

import java.math.BigInteger;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.TypePromoteur;
import fr.pharma.eclipse.domain.model.sigrec.SponsorType;
import fr.pharma.eclipse.domain.model.sigrec.SponsorType.SponsorSsType;
import fr.pharma.eclipse.domain.model.sigrec.acteur.PromoteurSigrec;
import fr.pharma.eclipse.filler.impl.sigrec.promoteur.TypePromoteurFiller;

/**
 * Test du filler TypePromoteurFiller.
 
 * @version $Revision$ $Date$
 */
public class TypePromoteurFillerTest
{
    /**
     * Filler.
     */
    private TypePromoteurFiller filler;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void setUp()
    {
        this.filler = new TypePromoteurFiller();
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void tearDown()
    {
        this.filler = null;
    }

    @Test
    public void testSupport()
    {
        final SponsorType sponsor = new SponsorType();
        Assert.assertFalse(this.filler.support(sponsor));
        sponsor.setSponsorType(new SponsorSsType());
        Assert.assertTrue(this.filler.support(sponsor));
    }

    /**
     * Test de la méthode fill.
     */
    @Test
    public void testFillAca()
    {
        final SponsorType sponsor = new SponsorType();
        final SponsorSsType type = new SponsorSsType();
        type.setTypeId(new BigInteger("1"));
        sponsor.setSponsorType(type);
        final PromoteurSigrec promoteur = new PromoteurSigrec();
        this.filler.fill(sponsor,
                         promoteur);
        Assert.assertEquals(TypePromoteur.ACADEMIQUE,
                            promoteur.getType());
    }

    /**
     * Test de la méthode fill.
     */
    @Test
    public void testFillIndus()
    {
        final SponsorType sponsor = new SponsorType();
        final SponsorSsType type = new SponsorSsType();
        type.setTypeId(new BigInteger("2"));
        sponsor.setSponsorType(type);
        final PromoteurSigrec promoteur = new PromoteurSigrec();
        this.filler.fill(sponsor,
                         promoteur);
        Assert.assertEquals(TypePromoteur.INDUSTRIEL,
                            promoteur.getType());
    }

    /**
     * Test de la méthode fill.
     */
    @Test
    public void testFillNull()
    {
        final SponsorType sponsor = new SponsorType();
        final SponsorSsType type = new SponsorSsType();
        type.setTypeId(new BigInteger("3"));
        sponsor.setSponsorType(type);
        final PromoteurSigrec promoteur = new PromoteurSigrec();
        this.filler.fill(sponsor,
                         promoteur);
        Assert.assertNull(promoteur.getType());
    }
}
