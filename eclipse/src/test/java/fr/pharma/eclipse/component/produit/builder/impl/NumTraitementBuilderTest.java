package fr.pharma.eclipse.component.produit.builder.impl;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.produit.UniteGestion;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du builder NumTraitementBuilder.
 
 * @version $Revision$ $Date$
 */
public class NumTraitementBuilderTest
    extends AbstractEclipseJUnitTest
{

    /**
     * Builder.
     */
    private NumTraitementBuilder builder;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp()
    {
        this.builder = new NumTraitementBuilder();
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
