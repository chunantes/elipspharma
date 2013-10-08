package fr.pharma.eclipse.component.essai.helper.impl;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.TypePharmacien;
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;
import fr.pharma.eclipse.utils.PersonneUtils;

/**
 * Test de la classe PharmacienDroitHabilitationInitializer.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PharmacienDroitHabilitationInitializerTest extends AbstractEclipseJUnitTest {
    /**
     * Initialiseur.
     */
    private PharmacienDroitHabilitationInitializer initializer;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.initializer = new PharmacienDroitHabilitationInitializer();
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
    }

    /**
     * Test de la méthode initialize.
     */
    @Test
    public void testInitialize() {
        final TypePharmacien type = TypePharmacien.PREPARATEUR;
        final Pharmacien pharmacien = PersonneUtils.makePharmacien(1, type);
        final Habilitation habilitation = EssaiUtils.makeHabilitationTest(1L);
        habilitation.setPersonne(pharmacien);
        Assert.assertNull(habilitation.getDroit());
        this.initializer.initialize(habilitation);
        Assert.assertEquals(type.getDroit(), habilitation.getDroit());
    }
}
