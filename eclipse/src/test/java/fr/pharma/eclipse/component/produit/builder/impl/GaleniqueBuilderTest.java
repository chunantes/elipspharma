package fr.pharma.eclipse.component.produit.builder.impl;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du builder GalelniqueBuilder.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GaleniqueBuilderTest extends AbstractEclipseJUnitTest {

    /**
     * Builder.
     */
    private GaleniqueBuilder builder;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.builder = new GaleniqueBuilder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.builder = null;
    }

    /**
     * {@inheritDoc}
     */
    @Test
    @Override
    public void testInit() {
        Assert.assertNotNull(this.builder);
    }

    /**
     * Test de la méthode build().
     */
    @Test
    public void testBuild() {
        final Conditionnement conditionnement = new Conditionnement();
        this.builder.build(conditionnement);
        Assert.assertEquals("Galénique", conditionnement.getUnitePrescription());
    }

}
