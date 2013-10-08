package fr.pharma.eclipse.service.evenement.updator.impl;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.evenement.ResultatVisite;
import fr.pharma.eclipse.domain.enums.evenement.TypeEvenement;
import fr.pharma.eclipse.domain.enums.evenement.TypeVisite;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.dates.DetailDates;
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.evenement.impl.EvenementServiceImpl;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du PreclotureUPdator.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PreclotureUpdatorTest extends AbstractEclipseJUnitTest {

    /**
     * Updator.
     */
    private PreclotureUpdator updator;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.updator = new PreclotureUpdator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.updator = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.updator);
    }

    /**
     * Test de la méthode support.
     */
    @Test
    public void testSupport() {
        final Evenement evenement = new Evenement();
        evenement.setEssai(new Essai());
        evenement.getEssai().setDetailDates(new DetailDates());
        evenement.setTypeEvenement(TypeEvenement.VISITE);
        evenement.setTypeVisite(TypeVisite.PRE_CLOTURE);
        Assert.assertFalse(this.updator.support(evenement));
    }

    /**
     * Test de la méthode support.
     */
    @Test
    public void testSupport1() {
        final Evenement evenement = new Evenement();
        evenement.setEssai(new Essai());
        evenement.getEssai().setDetailDates(new DetailDates());
        evenement.setTypeEvenement(TypeEvenement.TACHE);
        Assert.assertFalse(this.updator.support(evenement));
    }

    /**
     * Test de la méthode support.
     */
    @Test
    public void testSupport2() {
        final Evenement evenement = new Evenement();
        evenement.setEssai(new Essai());
        evenement.getEssai().setDetailDates(new DetailDates());
        evenement.setTypeEvenement(TypeEvenement.VISITE);
        evenement.setTypeVisite(TypeVisite.PRE_CLOTURE);
        evenement.setResultatVisite(ResultatVisite.EFFECTUE);
        Assert.assertTrue(this.updator.support(evenement));
    }

    /**
     * Test de la méthode update.
     */
    @Test
    public void testUpdate() {
        final Evenement evenement = new Evenement();
        evenement.setEssai(new Essai());
        evenement.getEssai().setDetailDates(new DetailDates());
        evenement.setTypeEvenement(TypeEvenement.VISITE);
        evenement.setTypeVisite(TypeVisite.PRE_CLOTURE);
        evenement.setResultatVisite(ResultatVisite.EFFECTUE);
        final EvenementServiceImpl service = Mockito.mock(EvenementServiceImpl.class);
        final EssaiService essaiService = Mockito.mock(EssaiService.class);
        Mockito.when(service.getEssaiService()).thenReturn(essaiService);
        this.updator.update(evenement, service);

        Mockito.verify(essaiService).save(Matchers.any(Essai.class));
    }
}
