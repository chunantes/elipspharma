package fr.pharma.eclipse.jasper.engine.builder.helper.common;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.jasper.model.common.JRBeanHeader;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe JRBeanHeaderBuilder.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JRBeanHeaderBuilderTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private JRBeanHeaderBuilder builder;

    /**
     * Chemin d'accès au logo du CHU.
     */
    private static final String PATH_LOGO = "C:";

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.builder = new JRBeanHeaderBuilder(JRBeanHeaderBuilderTest.PATH_LOGO);
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
    @Override
    public void testInit() {
        Assert.assertNotNull(this.builder);
        Assert.assertEquals(JRBeanHeaderBuilderTest.PATH_LOGO, this.builder.getCheminLogoCHU());
    }

    /**
     * Test de la méthode build(java.lang.String, java.lang.String,
     * java.lang.String, java.lang.String).
     */
    @Test
    public void testBuild() {
        final String diffuseur = "Diffuseur";
        final String themes = "Thèmes";
        final String processus = "processus";
        final String sousTitre = "mon sous-titre";
        final JRBeanHeader header = this.builder.build(sousTitre, processus, themes, diffuseur);
        Assert.assertNotNull(header);
        Assert.assertEquals(diffuseur, header.getDiffusionPar());
        Assert.assertEquals(themes, header.getThemes());
        Assert.assertEquals(processus, header.getProcessus());
        Assert.assertEquals(sousTitre, header.getSousTitre());
        Assert.assertEquals(JRBeanHeaderBuilderTest.PATH_LOGO, header.getUrlImage());
    }

}
