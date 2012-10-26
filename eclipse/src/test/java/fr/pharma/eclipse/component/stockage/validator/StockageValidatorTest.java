package fr.pharma.eclipse.component.stockage.validator;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.faces.application.FacesMessage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.comparator.stockage.StockageComparator;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.service.stockage.StockageService;
import fr.pharma.eclipse.utils.FacesUtils;

/**
 * Classe en charge de tester le validator de Stockage.
 
 * @version $Revision$ $Date$
 */
public class StockageValidatorTest
{
    /**
     * StockageValidator à tester.
     */
    private StockageValidator validator;

    /**
     * Service de stockage mocké.
     */
    private StockageService mockStockageService;

    /**
     * FacesUtils mocké.
     */
    private FacesUtils mockFacesUtils;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init()
    {
        this.validator = new StockageValidator();
        this.mockStockageService = Mockito.mock(StockageService.class);
        this.validator.setStockageService(this.mockStockageService);
        this.mockFacesUtils = Mockito.mock(FacesUtils.class);
        this.validator.setFacesUtils(this.mockFacesUtils);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end()
    {
        this.validator = null;
        this.mockStockageService = null;
        this.mockFacesUtils = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.validator);
        Assert.assertNotNull(this.mockStockageService);
        Assert.assertNotNull(this.mockFacesUtils);
    }

    /**
     * Méthode en charge de tester la validation OK.
     */
    @Test
    public void testValidateStockageOK()
    {
        final Stockage stockage = new Stockage();
        stockage.setNom("nom");
        final SortedSet<Stockage> stockages = new TreeSet<Stockage>(new StockageComparator());

        Mockito.when(this.mockStockageService.isStockageAlreadyPresent(stockage,
                                                                       stockages))
                .thenReturn(Boolean.FALSE);

        Assert.assertTrue(this.validator.validateStockage(stockage));

        Mockito.verify(this.mockFacesUtils).putCallbackValidityParam(true);
    }

    /**
     * Méthode en charge de tester la validation KO.
     */
    @Test
    public void testValidateStockageKONom()
    {
        final Stockage stockage = new Stockage();
        final SortedSet<Stockage> stockages = new TreeSet<Stockage>(new StockageComparator());

        Assert.assertFalse(this.validator.validateStockage(stockage));
        Mockito.verify(this.mockFacesUtils).addMessage(FacesMessage.SEVERITY_ERROR,
                                                       "stockage.nom.notEmpty");
        Mockito.verify(this.mockFacesUtils).putCallbackValidityParam(false);
    }

    /**
     * Méthode en charge de tester la validation KO.
     */
    @Test
    public void testValidateStockageKOUnicite()
    {
        final Stockage stockage = Mockito.mock(Stockage.class);
        final SortedSet<Stockage> stockages = Mockito.mock(TreeSet.class);
        Mockito.when(stockage.getNom()).thenReturn("nom");
        Mockito.when(this.mockStockageService.isNomStockageUtiliseParAutreStockageDeMemeNiveau(stockage,
                                                                                               stockage
                                                                                                       .getPharmacie()))
                .thenReturn(Boolean.TRUE);
        Assert.assertFalse(this.validator.validateStockage(stockage));
        Mockito.verify(this.mockFacesUtils).addMessage(FacesMessage.SEVERITY_ERROR,
                                                       "stockage.nom.notUnique");
        Mockito.verify(this.mockFacesUtils).putCallbackValidityParam(false);
    }
}
