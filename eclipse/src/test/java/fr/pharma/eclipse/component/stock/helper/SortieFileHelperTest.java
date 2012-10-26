package fr.pharma.eclipse.component.stock.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.component.stock.GenericStockManager;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.stock.Destruction;
import fr.pharma.eclipse.domain.model.stock.ResultSortie;
import fr.pharma.eclipse.domain.model.stock.RetourPromoteur;
import fr.pharma.eclipse.domain.model.stock.document.DocumentDestruction;
import fr.pharma.eclipse.domain.model.stock.document.DocumentRetourPromoteur;
import fr.pharma.eclipse.service.stock.MvtStockService;

/**
 * Classe en charge de tester la classe de helper pour le traitement des fichiers de sortie de
 * stock.
 
 * @version $Revision$ $Date$
 */
public class SortieFileHelperTest
{

    /**
     * SortieFileHelper à tester.
     */
    private SortieFileHelper helper;

    /**
     * Service de gestion des destructions mocké.
     */
    private MvtStockService<Destruction> mockDestructionService;

    /**
     * Service de gestion des retours promoteurs mocké.
     */
    private MvtStockService<RetourPromoteur> mockRetourPromoService;

    /**
     * Manager des documents de destruction mocké.
     */
    private GenericStockManager<DocumentDestruction> mockManagerDocDest;

    /**
     * Manager des documents de retour promoteur mocké.
     */
    private GenericStockManager<DocumentRetourPromoteur> mockManagerDocRetPromo;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init()
    {
        this.helper = new SortieFileHelper();
        this.mockDestructionService = Mockito.mock(MvtStockService.class);
        this.mockManagerDocDest = Mockito.mock(GenericStockManager.class);
        this.mockRetourPromoService = Mockito.mock(MvtStockService.class);
        this.mockManagerDocRetPromo = Mockito.mock(GenericStockManager.class);
        this.helper.setDestructionService(this.mockDestructionService);
        this.helper.setManagerDocDest(this.mockManagerDocDest);
        this.helper.setRetourPromoService(this.mockRetourPromoService);
        this.helper.setManagerDocRetourPromoteur(this.mockManagerDocRetPromo);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end()
    {
        this.helper = null;
        this.mockDestructionService = null;
        this.mockManagerDocDest = null;
        this.mockRetourPromoService = null;
        this.mockManagerDocRetPromo = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInitData()
    {
        Assert.assertNotNull(this.helper);
        Assert.assertNotNull(this.mockDestructionService);
        Assert.assertNotNull(this.mockManagerDocDest);
        Assert.assertNotNull(this.mockRetourPromoService);
        Assert.assertNotNull(this.mockManagerDocRetPromo);
    }

    /**
     * Méthode en charge de tester l'ajout de document.
     * @throws IOException Exception Input / Output.
     */
    @Test
    public void testAddDocumentsSortieDestructionOK()
        throws IOException
    {
        final List<Destruction> mvts = new ArrayList<Destruction>();
        final Destruction destruction = Mockito.mock(Destruction.class);
        mvts.add(destruction);

        final ResultSortie resultSortie = new ResultSortie();
        resultSortie.setMvts(mvts);

        final UploadedFile fileDestruction = Mockito.mock(UploadedFile.class);
        Mockito.when(fileDestruction.getBytes()).thenReturn(new byte[]
        {0, 1, 0 });

        this.helper.addDocumentsSortie(TypeMvtStock.DESTRUCTION,
                                       resultSortie,
                                       fileDestruction,
                                       null);

        Mockito.verify(this.mockManagerDocDest).setFile(fileDestruction);
        Mockito.verify(this.mockManagerDocDest).createDocument(destruction);
        Mockito.verify(this.mockDestructionService).saveAll(mvts);
    }

    /**
     * Méthode en charge de tester l'ajout de document.
     * @throws IOException Exception Input / Output.
     */
    @Test
    public void testAddDocumentsSortieDestructionKO()
        throws IOException
    {
        final List<Destruction> mvts = new ArrayList<Destruction>();
        final Destruction destruction = Mockito.mock(Destruction.class);
        mvts.add(destruction);

        final ResultSortie resultSortie = new ResultSortie();
        resultSortie.setMvts(mvts);

        final UploadedFile fileDestruction = Mockito.mock(UploadedFile.class);
        Mockito.when(fileDestruction.getBytes()).thenReturn(new byte[]
        {});

        this.helper.addDocumentsSortie(TypeMvtStock.DESTRUCTION,
                                       resultSortie,
                                       fileDestruction,
                                       null);
    }

    /**
     * Méthode en charge de tester l'ajout de document.
     * @throws IOException Exception Input / Output.
     */
    @Test
    public void testAddDocumentsSortieSansFichier()
        throws IOException
    {
        final List<Destruction> mvts = new ArrayList<Destruction>();
        final Destruction destruction = Mockito.mock(Destruction.class);
        mvts.add(destruction);

        final ResultSortie resultSortie = new ResultSortie();
        resultSortie.setMvts(mvts);

        final UploadedFile fileDestruction = Mockito.mock(UploadedFile.class);
        Mockito.when(fileDestruction.getBytes()).thenReturn(new byte[]
        {});

        this.helper.addDocumentsSortie(TypeMvtStock.APPROVISIONNEMENT,
                                       resultSortie,
                                       fileDestruction,
                                       null);
    }

    /**
     * Méthode en charge de tester l'ajout de document.
     * @throws IOException Exception Input / Output.
     */
    @Test
    public void testAddDocumentsSortieRetourPromoteurOK()
        throws IOException
    {
        final List<RetourPromoteur> mvts = new ArrayList<RetourPromoteur>();
        final RetourPromoteur retourPromoteur = Mockito.mock(RetourPromoteur.class);
        mvts.add(retourPromoteur);

        final ResultSortie resultSortie = new ResultSortie();
        resultSortie.setMvts(mvts);

        final UploadedFile fileRetourPromoteur = Mockito.mock(UploadedFile.class);
        Mockito.when(fileRetourPromoteur.getBytes()).thenReturn(new byte[]
        {0, 1, 0 });

        this.helper.addDocumentsSortie(TypeMvtStock.RETOUR_PROMOTEUR,
                                       resultSortie,
                                       null,
                                       fileRetourPromoteur);

        Mockito.verify(this.mockManagerDocRetPromo).setFile(fileRetourPromoteur);
        Mockito.verify(this.mockManagerDocRetPromo).createDocument(retourPromoteur);
        Mockito.verify(this.mockRetourPromoService).saveAll(mvts);
    }

    /**
     * Méthode en charge de tester l'ajout de document.
     * @throws IOException Exception Input / Output.
     */
    @Test
    public void testAddDocumentsSortieRetourPromoteurKO()
        throws IOException
    {
        final List<RetourPromoteur> mvts = new ArrayList<RetourPromoteur>();
        final RetourPromoteur retourPromoteur = Mockito.mock(RetourPromoteur.class);
        mvts.add(retourPromoteur);

        final ResultSortie resultSortie = new ResultSortie();
        resultSortie.setMvts(mvts);

        final UploadedFile fileRetourPromoteur = Mockito.mock(UploadedFile.class);
        Mockito.when(fileRetourPromoteur.getBytes()).thenReturn(new byte[]
        {});

        this.helper.addDocumentsSortie(TypeMvtStock.RETOUR_PROMOTEUR,
                                       resultSortie,
                                       null,
                                       fileRetourPromoteur);
    }

}
