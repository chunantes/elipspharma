package fr.pharma.eclipse.service.indicateur.builder.impl;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.evenement.EvenementSearchCriteria;
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.evenement.EvenementService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du builder GenericEvenementBuilder.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GenericEvenementBuilderTest extends AbstractEclipseJUnitTest {

    /**
     * Builder à tester.
     */
    private GenericEvenementBuilder builder;

    /**
     * Service evenement.
     */
    private EvenementService evenementService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.evenementService = Mockito.mock(EvenementService.class);
        this.builder = new GenericEvenementBuilder();
        this.builder.setService(this.evenementService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.builder = null;
        this.evenementService = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.builder);
        Assert.assertNotNull(this.evenementService);
    }

    /**
     * Test de la méthode build.
     */
    @Test
    public void testBuild() {
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        final Calendar dateDebut = Calendar.getInstance();
        final Calendar dateFin = Calendar.getInstance();
        Mockito.when(this.evenementService.getAllWithoutPurge(Matchers.any(EvenementSearchCriteria.class))).thenReturn(new ArrayList<Evenement>());
        this.builder.build(pharmacie, dateDebut, dateFin);
        Mockito.verify(this.evenementService).getAllWithoutPurge(Matchers.any(EvenementSearchCriteria.class));
    }

}
