package fr.pharma.eclipse.component.essai.helper.impl;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.PersonneUtils;

/**
 * Test de la classe InvestigateurDroitHabilitationInitializer.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class InvestigateurDroitHabilitationInitializerTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private InvestigateurDroitHabilitationInitializer initializer;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.initializer = new InvestigateurDroitHabilitationInitializer();
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
     * Test de la méthode initialize - investigateur co.
     */
    @Test
    public void testInitializeCo() {
        final Personne personne = PersonneUtils.makeInvestigateur(1);
        final Habilitation habilitation = new Habilitation();
        habilitation.setPersonne(personne);
        Assert.assertNull(habilitation.getDroit());
        this.initializer.initialize(habilitation);
        Assert.assertEquals(Droit.INVESTIGATEUR_CO, habilitation.getDroit());
    }

    /**
     * Test de la méthode initialize - investigateur principal.
     */
    @Test
    public void testInitializePrincipal() {
        final Personne personne = PersonneUtils.makeInvestigateur(1);
        personne.setSelected(true);
        final Habilitation habilitation = new Habilitation();
        habilitation.setPersonne(personne);
        Assert.assertNull(habilitation.getDroit());
        this.initializer.initialize(habilitation);
        Assert.assertEquals(Droit.INVESTIGATEUR_PRINCIPAL, habilitation.getDroit());
    }
}
