package fr.pharma.eclipse.factory.produit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.enums.produit.TypeProduit;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.suivi.produit.ProduitSuivi;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;

/**
 * Test de la fabrique de Medicament.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class MedicamentFactoryTest {
    /**
     * Fabrique testée.
     */
    private ProduitFactory<Medicament> factory;

    /**
     * Factory Spring mockée.
     */
    private BeanFactory mockedBeanFactory;

    /**
     * Factory Spring mockée.
     */
    private SuiviFactory<ProduitSuivi> mockedSuiviFactory;

    /**
     * Méthode d'initialisation.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.mockedBeanFactory = Mockito.mock(BeanFactory.class);
        this.mockedSuiviFactory = Mockito.mock(SuiviFactory.class);
        this.factory = new ProduitFactory<Medicament>(Medicament.class, TypeProduit.MEDICAMENT);
        this.factory.setBeanFactory(this.mockedBeanFactory);
        this.factory.setProduitSuiviFactory(this.mockedSuiviFactory);
    }

    /**
     * Méthode de finalisation.
     */
    @After
    public void end() {
        this.factory = null;
        this.mockedSuiviFactory = null;
        this.mockedBeanFactory = null;
    }

    /**
     * Test de la méthode getInitializedObject.
     */
    @Test
    public void testGetInitializedObject() {
        final Medicament expectedMedicament = Mockito.mock(Medicament.class);
        final String expectedClassName = Medicament.class.getSimpleName();
        Mockito.when(this.mockedBeanFactory.getBean(expectedClassName)).thenReturn(expectedMedicament);
        Mockito.when(this.mockedSuiviFactory.getInitializedObject()).thenReturn(new ProduitSuivi());

        final Medicament created = this.factory.getInitializedObject();
        Mockito.verify(this.mockedBeanFactory).getBean(expectedClassName);
        Assert.assertEquals(expectedMedicament, created);
        Mockito.verify(expectedMedicament).setType(TypeProduit.MEDICAMENT);
        Mockito.verify(expectedMedicament).setAlerteActive(Boolean.TRUE);
    }

}
