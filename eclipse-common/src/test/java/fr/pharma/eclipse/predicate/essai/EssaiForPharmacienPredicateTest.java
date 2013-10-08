package fr.pharma.eclipse.predicate.essai;

import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.DetailDonneesPharma;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du prédicat EssaiForPharmacienPredicate.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EssaiForPharmacienPredicateTest extends AbstractEclipseJUnitTest {

    /**
     * Predicate.
     */
    private EssaiForPharmacienPredicate predicate;

    /**
     * Pharmacien.
     */
    private Pharmacien pharmacien;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.pharmacien = Mockito.mock(Pharmacien.class);
        this.predicate = new EssaiForPharmacienPredicate(this.pharmacien);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.pharmacien = null;
        this.predicate = null;
    }

    /**
     * {@inheritDoc}
     */
    @Test
    @Override
    public void testInit() {
        Assert.assertNotNull(this.pharmacien);
        Assert.assertNotNull(this.predicate);
    }

    /**
     * Test de la méthode evaluate.
     */
    @Test
    public void testPredicate() {
        final Essai essai = new Essai();
        final SortedSet<Pharmacie> pharmacies = Mockito.mock(SortedSet.class);
        Mockito.when(this.pharmacien.getPharmacies()).thenReturn(pharmacies);
        Mockito.when(pharmacies.contains(Matchers.any(Pharmacie.class))).thenReturn(true);
        essai.setPharmaciePrincipale(new Pharmacie());
        Assert.assertTrue(this.predicate.evaluate(essai));
    }

    /**
     * Test de la méthode evaluate.
     */
    @Test
    public void testPredicateFalse() {
        // pharmacies pharmacien.
        final SortedSet<Pharmacie> pharmacies = new TreeSet<Pharmacie>(new BeanWithNomComparator());
        final Pharmacie p1 = new Pharmacie();
        p1.setNom("nom1");
        final Pharmacie p2 = new Pharmacie();
        p2.setNom("nom2");
        final Pharmacie p3 = new Pharmacie();
        p3.setNom("nom3");
        pharmacies.add(p1);
        pharmacies.add(p2);
        pharmacies.add(p3);
        Mockito.when(this.pharmacien.getPharmacies()).thenReturn(pharmacies);

        // pharmacies essai
        final Essai essai = new Essai();
        final Pharmacie principale = new Pharmacie();
        principale.setNom("principale");
        final Pharmacie pe1 = new Pharmacie();
        pe1.setNom("essai 1");
        essai.setDetailDonneesPharma(new DetailDonneesPharma());
        essai.getDetailDonneesPharma().getPharmacies().add(pe1);
        essai.setPharmaciePrincipale(principale);

        Assert.assertFalse(this.predicate.evaluate(essai));
    }

    /**
     * Test de la méthode evaluate.
     */
    @Test
    public void testPredicateTrue() {
        // pharmacies pharmacien.
        final SortedSet<Pharmacie> pharmacies = new TreeSet<Pharmacie>(new BeanWithNomComparator());
        final Pharmacie p1 = new Pharmacie();
        p1.setNom("nom1");
        final Pharmacie p2 = new Pharmacie();
        p2.setNom("nom2");
        final Pharmacie p3 = new Pharmacie();
        p3.setNom("nom3");
        pharmacies.add(p1);
        pharmacies.add(p2);
        pharmacies.add(p3);
        Mockito.when(this.pharmacien.getPharmacies()).thenReturn(pharmacies);

        // pharmacies essai
        final Essai essai = new Essai();
        final Pharmacie principale = new Pharmacie();
        principale.setNom("principale");
        final Pharmacie pe1 = new Pharmacie();
        pe1.setNom("essai 1");
        essai.setDetailDonneesPharma(new DetailDonneesPharma());
        essai.getDetailDonneesPharma().getPharmacies().add(pe1);
        essai.getDetailDonneesPharma().getPharmacies().add(p1);
        essai.setPharmaciePrincipale(principale);

        Assert.assertTrue(this.predicate.evaluate(essai));
    }

}
