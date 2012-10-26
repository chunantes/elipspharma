package fr.pharma.eclipse.component.produit.builder.impl;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.produit.UniteGestion;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du builder ConditionnementPrimaireBuilder.
 
 * @version $Revision$ $Date$
 */
public class ConditionnementPrimaireBuilderTest
    extends AbstractEclipseJUnitTest
{

    /**
     * Builder.
     */
    private ConditionnementPrimaireBuilder builder;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp()
    {
        this.builder = new ConditionnementPrimaireBuilder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown()
    {
        this.builder = null;
    }

    /**
     * {@inheritDoc}
     */
    @Test
    @Override
    public void testInit()
    {
        Assert.assertNotNull(this.builder);
    }

    /**
     * Test de la méthode build().
     */
    @Test
    public void testBuild()
    {
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setUniteGestion(UniteGestion.BOITE);
        this.builder.build(conditionnement);
        Assert.assertEquals("Boîte",
                            conditionnement.getUnitePrescription());
    }

}
