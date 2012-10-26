package fr.pharma.eclipse.component.stock.helper;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.apache.myfaces.custom.fileupload.UploadedFile;

import fr.pharma.eclipse.component.stock.GenericStockManager;
import fr.pharma.eclipse.domain.enums.document.TypeDocumentStock;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.stock.Destruction;
import fr.pharma.eclipse.domain.model.stock.DocumentStock;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.stock.ResultSortie;
import fr.pharma.eclipse.domain.model.stock.RetourPromoteur;
import fr.pharma.eclipse.domain.model.stock.document.DocumentDestruction;
import fr.pharma.eclipse.domain.model.stock.document.DocumentRetourPromoteur;
import fr.pharma.eclipse.service.stock.MvtStockService;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Classe de Helper pour la gestion des fichiers sur la sortie de stock.
 
 * @version $Revision$ $Date$
 */
public class SortieFileHelper
    implements Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 6615714618532642593L;

    /**
     * Service de gestion des destructions.
     */
    @Resource(name = "destructionService")
    private MvtStockService<Destruction> destructionService;

    /**
     * Service de gestion des retours promoteurs.
     */
    @Resource(name = "retourPromoteurService")
    private MvtStockService<RetourPromoteur> retourPromoService;

    /**
     * Manager des documents de destruction.
     */
    @Resource(name = "documentDestructionStockManager")
    private GenericStockManager<DocumentDestruction> managerDocDest;

    /**
     * Manager des documents de retour promoteur.
     */
    @Resource(name = "documentRetourPromoteurStockManager")
    private GenericStockManager<DocumentRetourPromoteur> managerDocRetourPromoteur;

    /**
     * Méthode en charge d'ajouter les documents enregistrés sur sortie sur la liste des
     * mouvements.
     * @param typeSortie Type de la sortie.
     * @param resultSortie Résultat de l'enregistrement de la sortie.
     * @param fileDestruction Fichier de certificat de detsruction.
     * @param fileRetourPromoteur Fichier de certificat de retour promoteur.
     * @throws IOException Exception Input/Output
     */
    @SuppressWarnings("unchecked")
    public void addDocumentsSortie(final TypeMvtStock typeSortie,
                                   final ResultSortie resultSortie,
                                   final UploadedFile fileDestruction,
                                   final UploadedFile fileRetourPromoteur)
        throws IOException
    {
        final List<? extends MvtStock> mvts = resultSortie.getMvts();

        // Type de sortie Destruction et Fichier de destruction saisi
        if ((TypeMvtStock.DESTRUCTION.equals(typeSortie))
            && (fileDestruction != null)
            && (fileDestruction.getBytes().length > 0))
        {
            for (final MvtStock mvt : mvts)
            {
                this.managerDocDest.setFile(fileDestruction);
                final DocumentStock doc = this.managerDocDest.createDocument(mvt);

                BeanTool.setPropriete(mvt,
                                      TypeDocumentStock.DESTRUCTION.getPropriete(),
                                      doc);
            }
            // Sauvegarde des destructions
            this.destructionService.saveAll((List<Destruction>) mvts);
        }
        // Type de sortie Retour Promoteur et Fichier de retour promoteur saisi
        else if ((TypeMvtStock.RETOUR_PROMOTEUR.equals(typeSortie))
                 && (fileRetourPromoteur != null)
                 && (fileRetourPromoteur.getBytes().length > 0))
        {
            for (final MvtStock mvt : mvts)
            {
                this.managerDocRetourPromoteur.setFile(fileRetourPromoteur);
                final DocumentStock doc = this.managerDocRetourPromoteur.createDocument(mvt);

                BeanTool.setPropriete(mvt,
                                      TypeDocumentStock.RETOUR_PROMOTEUR.getPropriete(),
                                      doc);
            }
            // Sauvegarde des destructions
            this.retourPromoService.saveAll((List<RetourPromoteur>) mvts);
        }
    }

    /**
     * Setter pour managerDocDest.
     * @param managerDocDest Le managerDocDest à écrire.
     */
    public void setManagerDocDest(final GenericStockManager<DocumentDestruction> managerDocDest)
    {
        this.managerDocDest = managerDocDest;
    }

    /**
     * Setter pour destructionService.
     * @param destructionService Le destructionService à écrire.
     */
    public void setDestructionService(final MvtStockService<Destruction> destructionService)
    {
        this.destructionService = destructionService;
    }

    /**
     * Setter pour managerDocRetourPromoteur.
     * @param managerDocRetourPromoteur Le managerDocRetourPromoteur à écrire.
     */
    public void setManagerDocRetourPromoteur(final GenericStockManager<DocumentRetourPromoteur> managerDocRetourPromoteur)
    {
        this.managerDocRetourPromoteur = managerDocRetourPromoteur;
    }

    /**
     * Setter pour retourPromoService.
     * @param retourPromoService Le retourPromoService à écrire.
     */
    public void setRetourPromoService(final MvtStockService<RetourPromoteur> retourPromoService)
    {
        this.retourPromoService = retourPromoService;
    }

}
