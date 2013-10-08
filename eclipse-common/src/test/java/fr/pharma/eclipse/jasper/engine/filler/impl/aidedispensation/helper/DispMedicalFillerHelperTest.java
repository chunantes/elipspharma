package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation.helper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanProduit;
import fr.pharma.eclipse.domain.model.produit.DispositifMedical;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.jasper.constants.JasperConstants;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.ProduitUtils;

/**
 * Test de la classe {@link DispMedicalFillerHelper}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DispMedicalFillerHelperTest extends AbstractEclipseJUnitTest {
    /**
     * Helper testé.
     */
    private DispMedicalFillerHelper helper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.helper = new DispMedicalFillerHelper();
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
        final String classeTherapeutique = "la classe";
        final DispositifMedical dispMedical = ProduitUtils.makeDispMedicalTest(id++);
        AbstractProduitsFillerHelperUtils.prepareProduitTest(dispMedical);
        dispMedical.setClasseTherapeutique(classeTherapeutique);

        final Set<Produit> produits = new HashSet<Produit>();
        produits.add(dispMedical);
        final Collection<JRBeanProduit> res = this.helper.transform(produits);

        Assert.assertEquals(1, res.size());
        final JRBeanProduit dest = res.iterator().next();
        AbstractProduitsFillerHelperUtils.verify(dest);
        Assert.assertEquals(JasperConstants.DEFAULT_FIELD_VALUE, dest.getDci());
        Assert.assertEquals(classeTherapeutique, dest.getClasseTherapeuthique());
    }

}
