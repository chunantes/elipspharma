package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation.helper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanProduit;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.ProduitUtils;

/**
 * Test de la classe {@link MedicamentsFillerHelper}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class MedicamentsFillerHelperTest extends AbstractEclipseJUnitTest {
    /**
     * Helper testé.
     */
    private MedicamentsFillerHelper helper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.helper = new MedicamentsFillerHelper();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.helper = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.helper);
    }

    /**
     * Test de la classe transform(java.util.Set).
     */
    @Test
    public void testTransform() {
        long id = 1;
        final String dci = "dci";
        final String classeTherapeutique = "la classe";
        final Medicament medicament = ProduitUtils.makeMedicamentTest(id++);
        AbstractProduitsFillerHelperUtils.prepareProduitTest(medicament);
        medicament.setDci(dci);
        medicament.setClasseTherapeutique(classeTherapeutique);

        final Set<Produit> produits = new HashSet<Produit>();
        produits.add(medicament);
        final Collection<JRBeanProduit> res = this.helper.transform(produits);

        Assert.assertEquals(1, res.size());
        final JRBeanProduit dest = res.iterator().next();
        AbstractProduitsFillerHelperUtils.verify(dest);
        Assert.assertEquals(dci, dest.getDci());
        Assert.assertEquals(classeTherapeutique, dest.getClasseTherapeuthique());
    }
}
