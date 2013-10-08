package fr.pharma.eclipse.service.indicateur.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.SortedSet;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.indicateur.Indicateur;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.indicateur.builder.IndicateurBuilder;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du service IndicateurServiceImpl.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class IndicateurServiceImplTest extends AbstractEclipseJUnitTest {

    /**
     * Service.
     */
    private IndicateurServiceImpl service;

    /**
     * Builder.
     */
    private IndicateurBuilder builder;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.builder = Mockito.mock(IndicateurBuilder.class);
        this.service = new IndicateurServiceImpl();
        final List<IndicateurBuilder> builders = new ArrayList<IndicateurBuilder>();
        builders.add(this.builder);
        this.service.setBuilders(builders);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.builder = null;
        this.service = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.builder);
        Assert.assertNotNull(this.service);
    }

    /**
     * Test de la méthode generateIndicateur.
     */
    @Test
    public void testGenerateIndicateur() {
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        final Calendar dateDebut = Calendar.getInstance();
        final Calendar dateFin = Calendar.getInstance();
        Mockito.when(this.builder.build(pharmacie, dateDebut, dateFin)).thenReturn(new Indicateur("", new BigDecimal(1)));
        final SortedSet<Indicateur> results = this.service.generateIndicateur(pharmacie, dateDebut, dateFin);
        Assert.assertEquals(1, results.size());
        Mockito.verify(this.builder).build(pharmacie, dateDebut, dateFin);
    }
}
