package fr.pharma.eclipse.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.common.BeanWithNom;
import fr.pharma.eclipse.domain.model.produit.Medicament;

/**
 * Test de la classe BeanWithNomHelper.
 
 * @version $Revision$ $Date$
 */
public class BeanWithNomHelperTest
{
    /**
     * Helper testé.
     */
    private BeanWithNomHelper helper;

    /**
     * Méthode d'initialisation.
     */
    @Before
    public void setUp()
    {
        this.helper = new BeanWithNomHelper();
    }

    /**
     * Méthode de finalisation.
     */
    @After
    public void tearDown()
    {
        this.helper = null;
    }

    /**
     * Test de la méthode getListByName().
     */
    @Test
    public void testGetListByName()
    {
        final Medicament m1 = new Medicament();
        final Medicament m2 = new Medicament();
        final Medicament m3 = new Medicament();

        m1.setDenomination("m1");
        m2.setDenomination("m2");
        m3.setDenomination("m3");

        final Collection<BeanWithNom> collection = new ArrayList<BeanWithNom>();
        collection.add(m1);
        collection.add(m2);
        collection.add(m3);

        final Map<String, BeanWithNom> result = this.helper.getListByName(collection);
        Assert.assertEquals(3,
                            result.size());
        Assert.assertEquals("m1",
                            result.get("m1").getNom());
        Assert.assertEquals("m2",
                            result.get("m2").getNom());
        Assert.assertEquals("m3",
                            result.get("m3").getNom());
    }
}
