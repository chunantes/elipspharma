package fr.pharma.eclipse.component.produit.builder.impl;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.produit.UniteDosage;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du builder DosageBuilder.
 
 * @version $Revision$ $Date$
 */
public class DosageBuilderTest
    extends AbstractEclipseJUnitTest
{

    /**
     * Builder.
     */
    private DosageBuilder builder;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp()
    {
        this.builder = new DosageBuilder();
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
        conditionnement.setUniteDosage(UniteDosage.COMPRIME);
        this.builder.build(conditionnement);
        Assert.assertEquals("cp",
                            conditionnement.getUnitePrescription());
    }

}
