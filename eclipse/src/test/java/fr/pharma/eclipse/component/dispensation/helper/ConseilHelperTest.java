package fr.pharma.eclipse.component.dispensation.helper;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.dispensation.ConseilDispensation;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.service.dispensation.builder.ConseilDispensationBuilder;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Description de la classe.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ConseilHelperTest extends AbstractEclipseJUnitTest {

    /**
     * Helper.
     */
    private ConseilHelper helper;

    /**
     * Builder.
     */
    private ConseilDispensationBuilder builder;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.builder = Mockito.mock(ConseilDispensationBuilder.class);
        this.helper = new ConseilHelper();
        this.helper.setBuilder(this.builder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.builder = null;
        this.helper = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.builder);
        Assert.assertNotNull(this.helper);
    }

    /**
     * Test de la méthode buildConseil.
     */
    @Test
    public void testBuildConseilSupportKo() {
        final ProduitPrescrit produitPrescrit = Mockito.mock(ProduitPrescrit.class);
        Mockito.when(this.builder.support(produitPrescrit)).thenReturn(false);

        Assert.assertEquals("", this.helper.buildConseil(produitPrescrit));
    }

    /**
     * Test de la méthode buildConseil.
     */
    @Test
    public void testBuildConseilSupportOk() {
        final ProduitPrescrit produitPrescrit = Mockito.mock(ProduitPrescrit.class);
        Mockito.when(this.builder.support(produitPrescrit)).thenReturn(true);
        Mockito.when(this.builder.build(produitPrescrit)).thenReturn(new ConseilDispensation());
        Mockito.when(this.builder.format(Matchers.any(ConseilDispensation.class))).thenReturn("ok");

        Assert.assertEquals("ok", this.helper.buildConseil(produitPrescrit));
    }

}
