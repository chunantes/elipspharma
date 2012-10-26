package fr.pharma.eclipse.component.indicateur;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.indicateur.Indicateur;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Description de la classe.
 
 * @version $Revision$ $Date$
 */
public class IndicateurManagerTest
    extends AbstractEclipseJUnitTest
{

    /**
     * Manager.
     */
    private IndicateurManager manager;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp()
    {
        this.manager = new IndicateurManager();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown()
    {
        this.manager = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.manager);
    }

    /**
     * Test de la méthode init().
     */
    @Test
    public void testInitMethod()
    {
        this.manager.setIndicateurs(new ArrayList<Indicateur>());
        this.manager.setDateDebut(Calendar.getInstance());
        this.manager.setDateFin(Calendar.getInstance());
        this.manager.setPharmacie(new Pharmacie());
        this.manager.init();
        Assert.assertNull(this.manager.getDateDebut());
        Assert.assertNull(this.manager.getDateFin());
        Assert.assertNull(this.manager.getIndicateurs());
        Assert.assertNull(this.manager.getPharmacie());
    }
}
