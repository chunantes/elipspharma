package fr.pharma.eclipse.service.indicateur.builder.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.indicateur.Indicateur;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.dispensation.DispensationService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du builder DispensationBuilder.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DispensationBuilderTest extends AbstractEclipseJUnitTest {

    /**
     * Builder.
     */
    private DispensationBuilder builder;

    /**
     * Service Dispensation.
     */
    private DispensationService dispensationService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.dispensationService = Mockito.mock(DispensationService.class);
        this.builder = new DispensationBuilder();
        this.builder.setDispensationService(this.dispensationService);
        this.builder.setLibelle("Indicateur");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.dispensationService = null;
        this.builder = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.dispensationService);
        Assert.assertNotNull(this.builder);
    }

    /**
     * Test de la méthode build.
     */
    @Test
    public void testBuild() {
        final List<Dispensation> liste = new ArrayList<Dispensation>();
        liste.add(new Dispensation());
        Mockito.when(this.dispensationService.getAll(Matchers.any(SearchCriteria.class))).thenReturn(liste);
        final Indicateur indicateur = this.builder.build(new Pharmacie(), Calendar.getInstance(), Calendar.getInstance());
        Assert.assertEquals("Indicateur", indicateur.getLibelle());
        Assert.assertEquals(new BigDecimal(1), indicateur.getValeur());
    }
}
