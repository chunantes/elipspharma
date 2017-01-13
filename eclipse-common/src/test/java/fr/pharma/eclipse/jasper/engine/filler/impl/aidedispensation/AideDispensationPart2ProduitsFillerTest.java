package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedSet;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart2;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanProduit;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.jasper.engine.factory.JRDataSourceFactory;
import fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation.helper.ProduitsFillerHelper;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;
import fr.pharma.eclipse.utils.ProduitUtils;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * Test de la classe {@link AideDispensationPart2ProduitsFiller}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AideDispensationPart2ProduitsFillerTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private AideDispensationPart2ProduitsFiller filler;

    /**
     * Fabrique mockée de JRDataSource.
     */
    private JRDataSourceFactory mockedFactory;

    /**
     * Helper mocké.
     */
    private ProduitsFillerHelper mockedHelper;

    /**
     * Nom de la propriété de la liste des produits<br>
     * à introspecter depuis l'essai.
     */
    private final static String PRODUITS_PROPERTY = "detailProduit.medicaments";

    /**
     * Propriété du JasperReportBean valorisée par ce filler.
     */
    private final static String PROPERTY_TO_SET = "medicaments";

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedFactory = Mockito.mock(JRDataSourceFactory.class);
        this.mockedHelper = Mockito.mock(ProduitsFillerHelper.class);
        this.filler = new AideDispensationPart2ProduitsFiller(AideDispensationPart2ProduitsFillerTest.PRODUITS_PROPERTY, AideDispensationPart2ProduitsFillerTest.PROPERTY_TO_SET);
        this.filler.setHelper(this.mockedHelper);
        this.filler.setJrDataSourceFactory(this.mockedFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.filler = null;
        this.mockedFactory = null;
        this.mockedHelper = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.filler);
        Assert.assertEquals(AideDispensationPart2ProduitsFillerTest.PRODUITS_PROPERTY, this.filler.getProduitsPropertyFromEssai());
        Assert.assertEquals(AideDispensationPart2ProduitsFillerTest.PROPERTY_TO_SET, this.filler.getJrPropertyToSet());
        Assert.assertEquals(this.mockedFactory, this.filler.getJrDataSourceFactory());
        Assert.assertEquals(this.mockedHelper, this.filler.getHelper());
    }

    /**
     * Test de la méthode fill(fr.pharma.eclipse.domain.model.essai.Essai,
     * fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean).
     */
    @Test
    public void testFill() {
        long id = 1;
        final JRBeanFicheAideDispensationPart2 bean = new JRBeanFicheAideDispensationPart2();
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final Produit produit = ProduitUtils.makeMedicamentTest(id++);
        final SortedSet<Produit> produits = essai.getDetailProduit().getMedicaments();
        produits.add(produit);
        final Collection<JRBeanProduit> beansProduits = new ArrayList<JRBeanProduit>();
        beansProduits.add(new JRBeanProduit());
        beansProduits.add(new JRBeanProduit());
        final JRDataSource expectedDataSource = Mockito.mock(JRDataSource.class);

        Mockito.when(this.mockedHelper.transform(produits)).thenReturn(beansProduits);
        Mockito.when(this.mockedFactory.getInitializedObject(beansProduits)).thenReturn(expectedDataSource);

        Assert.assertNull(bean.getMedicaments());
        this.filler.fill(essai, bean);
        Mockito.verify(this.mockedHelper).transform(produits);
        Mockito.verify(this.mockedFactory).getInitializedObject(beansProduits);
        Assert.assertEquals(expectedDataSource, bean.getMedicaments());
    }
}
