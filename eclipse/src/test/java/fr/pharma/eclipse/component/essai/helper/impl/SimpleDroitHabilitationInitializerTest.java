package fr.pharma.eclipse.component.essai.helper.impl;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.component.essai.TypeContact;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe SimpleDroitHabilitationInitializer.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class SimpleDroitHabilitationInitializerTest extends AbstractEclipseJUnitTest {

    /**
     * Classe testée.
     */
    private SimpleDroitHabilitationInitializer initializer;

    /**
     * Type de contact associé.
     */
    private static final TypeContact TYPE_CONTACT = TypeContact.CRO;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.initializer = new SimpleDroitHabilitationInitializer(SimpleDroitHabilitationInitializerTest.TYPE_CONTACT.name());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.initializer = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.initializer);
        Assert.assertEquals(SimpleDroitHabilitationInitializerTest.TYPE_CONTACT, this.initializer.getTypeContact());
    }

    /**
     * Test de la méthode initialize. .
     */
    @Test
    public void testInitialize() {
        final Habilitation hab = new Habilitation();
        Assert.assertNull(hab.getDroit());
        this.initializer.initialize(hab);
        Assert.assertEquals(SimpleDroitHabilitationInitializerTest.TYPE_CONTACT.getDroit(), hab.getDroit());
    }
}
