package fr.pharma.eclipse.component.stock;

import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.stock.DispensationProduit;
import fr.pharma.eclipse.factory.stock.DispensationProduitFactory;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe DispensationProduitManager.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DispensationProduitManagerTest extends AbstractEclipseJUnitTest {

    /**
     * Manager à tester.
     */
    private DispensationProduitManager manager;

    /**
     * Service.
     */
    private GenericService<DispensationProduit> service;

    /**
     * Factory.
     */
    private DispensationProduitFactory factory;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.service = Mockito.mock(GenericService.class);
        this.factory = Mockito.mock(DispensationProduitFactory.class);
        this.manager = new DispensationProduitManager(this.service);
        this.manager.setFactory(this.factory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.service = null;
        this.manager = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.service);
        Assert.assertNotNull(this.manager);
    }

    /**
     * Test de la méthode initDispensation.
     */
    @Test
    public void testInitDispensation() {
        final ProduitPrescrit produitPrescrit = Mockito.mock(ProduitPrescrit.class);
        final Dispensation dispensation = Mockito.mock(Dispensation.class);
        final ActionEvent event = Mockito.mock(ActionEvent.class);
        final UIComponent component = Mockito.mock(UIComponent.class);
        Mockito.when(event.getComponent()).thenReturn(component);
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("produitCurrent", produitPrescrit);
        map.put("dispensation", dispensation);
        Mockito.when(component.getAttributes()).thenReturn(map);
        this.manager.initDispensation(event);
        Mockito.verify(this.factory).getInitializedObject(produitPrescrit, dispensation);
    }

}
