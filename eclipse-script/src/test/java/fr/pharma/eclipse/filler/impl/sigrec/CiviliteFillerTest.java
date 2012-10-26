package fr.pharma.eclipse.filler.impl.sigrec;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.Civilite;
import fr.pharma.eclipse.domain.model.sigrec.IntervenantsType.Intervenant;
import fr.pharma.eclipse.domain.model.sigrec.acteur.ARCPromoteurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.ContactSigrec;
import fr.pharma.eclipse.filler.impl.sigrec.CiviliteFiller;

/**
 * Test du filler : CiviliteFiller.
 
 * @version $Revision$ $Date$
 */
public class CiviliteFillerTest
{

    /**
     * Filler.
     */
    private CiviliteFiller filler;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void setUp()
    {
        this.filler = new CiviliteFiller();
    }
    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void tearDown()
    {
        this.filler = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.filler);
    }

    @Test
    public void testSupport()
    {
        Assert.assertFalse(this.filler.support(null));
        final Intervenant it = new Intervenant();
        Assert.assertFalse(this.filler.support(it));
        it.setCivilite("");
        Assert.assertTrue(this.filler.support(it));
    }
    /**
     * Test méthode fill().
     */
    @Test
    public void testFillMr()
    {
        final Intervenant source = new Intervenant();
        source.setCivilite("Mr");
        final ARCPromoteurSigrec destination = this.initDestination();

        this.filler.fill(source,
                         destination);
        Assert.assertEquals(Civilite.MR,
                            destination.getContact().getCivilite());
    }

    /**
     * Test méthode fill().
     */
    @Test
    public void testFillMme()
    {
        final Intervenant source = new Intervenant();
        source.setCivilite("Mme");
        final ARCPromoteurSigrec destination = this.initDestination();

        this.filler.fill(source,
                         destination);
        Assert.assertEquals(Civilite.MME,
                            destination.getContact().getCivilite());
    }

    /**
     * Test méthode fill().
     */
    @Test
    public void testFillNull()
    {
        final Intervenant source = new Intervenant();
        final ARCPromoteurSigrec destination = this.initDestination();
        source.setCivilite("");
        this.filler.fill(source,
                         destination);
        Assert.assertEquals(null,
                            destination.getContact().getCivilite());
    }

    /**
     * Test méthode fill().
     */
    @Test
    public void testFillMlle()
    {
        final Intervenant source = new Intervenant();
        source.setCivilite("Mlle");
        final ARCPromoteurSigrec destination = this.initDestination();
        this.filler.fill(source,
                         destination);
        Assert.assertEquals(Civilite.MLLE,
                            destination.getContact().getCivilite());
    }

    /**
     * Méthode en charge d'initialiser la destination.
     * @return la destination.
     */
    private ARCPromoteurSigrec initDestination()
    {
        final ARCPromoteurSigrec arc = new ARCPromoteurSigrec();
        arc.setContact(new ContactSigrec());
        return arc;

    }
}
