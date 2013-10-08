package fr.pharma.eclipse.service.indicateur.builder.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.indicateur.Indicateur;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du builder EssaiActifsBuilder.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EssaiActifsBuilderTest extends AbstractEclipseJUnitTest {
    /**
     * Builder.
     */
    private EssaiActifsBuilder builder;

    /**
     * Service essai.
     */
    private EssaiService service;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.service = Mockito.mock(EssaiService.class);
        this.builder = new EssaiActifsBuilder();
        this.builder.setEssaiService(this.service);
        this.builder.setLibelle("Libelle");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.service = null;
        this.builder = null;
    }

    /**
     * {@inheritDoc}
     */
    @Test
    @Override
    public void testInit() {
        Assert.assertNotNull(this.service);
        Assert.assertNotNull(this.builder);
    }

    /**
     * Test de la méthode build.
     */
    @Test
    public void testBuild() {
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        final Calendar dateDebut = Calendar.getInstance();
        final Calendar dateFin = Calendar.getInstance();

        final List<Essai> liste = new ArrayList<Essai>();
        liste.add(new Essai());

        Mockito.when(this.service.getEssaisActifs(dateFin, pharmacie)).thenReturn(liste);

        final Indicateur indicateur = this.builder.build(pharmacie, dateDebut, dateFin);

        Assert.assertEquals("Libelle", indicateur.getLibelle());
        Assert.assertEquals(new BigDecimal(1), indicateur.getValeur());
    }
}
