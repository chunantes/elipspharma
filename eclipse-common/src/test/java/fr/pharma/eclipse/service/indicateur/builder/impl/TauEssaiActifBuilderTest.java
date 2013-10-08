package fr.pharma.eclipse.service.indicateur.builder.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.TypeDispensation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.DetailDonneesPharma;
import fr.pharma.eclipse.domain.model.indicateur.Indicateur;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du builder TauEssaiActifBuilder.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class TauEssaiActifBuilderTest extends AbstractEclipseJUnitTest {

    /**
     * Builder.
     */
    private TauEssaiActifBuilder builder;

    /**
     * Service essai.
     */
    private EssaiService essaiService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.essaiService = Mockito.mock(EssaiService.class);
        this.builder = new TauEssaiActifBuilder();
        this.builder.setEssaiService(this.essaiService);
        this.builder.setLibelle("Libelle");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.builder = null;
        this.essaiService = null;
    }

    /**
     * {@inheritDoc}
     */
    @Test
    @Override
    public void testInit() {
        Assert.assertNotNull(this.builder);
        Assert.assertNotNull(this.essaiService);
    }

    /**
     * Test de la méthode build.
     */
    @Test
    public void testBuild() {
        final List<Essai> essais = new ArrayList<Essai>();
        final Essai e1 = new Essai();
        final Essai e2 = new Essai();
        final Essai e3 = new Essai();
        final DetailDonneesPharma detail = new DetailDonneesPharma();
        e1.setDetailDonneesPharma(detail);
        e2.setDetailDonneesPharma(detail);

        final DetailDonneesPharma detail2 = new DetailDonneesPharma();
        detail2.getInfosDispensations().setTypeDispensation(TypeDispensation.NOMINATIVE);
        essais.add(e1);
        essais.add(e2);
        essais.add(e3);

        final Indicateur indicateur = new Indicateur("Libelle", new BigDecimal(1 / 3));

        Mockito.when(this.essaiService.getEssaisActifs(Matchers.any(Calendar.class), Matchers.any(Pharmacie.class))).thenReturn(essais);

        final Indicateur result = this.builder.build(new Pharmacie(), Calendar.getInstance(), Calendar.getInstance());

        Assert.assertEquals(indicateur.getLibelle(), result.getLibelle());
        Assert.assertEquals(indicateur.getValeur().doubleValue(), result.getValeur().doubleValue(), 1L);
    }
}
