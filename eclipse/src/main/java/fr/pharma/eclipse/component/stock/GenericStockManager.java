package fr.pharma.eclipse.component.stock;

import java.io.Serializable;

import org.apache.myfaces.custom.fileupload.UploadedFile;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentStock;
import fr.pharma.eclipse.domain.model.common.Fichier;
import fr.pharma.eclipse.domain.model.stock.DocumentStock;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.factory.document.stock.DocumentStockFactory;
import fr.pharma.eclipse.file.FichierFiller;
import fr.pharma.eclipse.service.document.DocumentService;

/**
 * Classe générique en charge de gérer un type de documents sur un mouvement de stock de sortie.
 
 * @version $Revision$ $Date$
 */
public class GenericStockManager<DOC extends DocumentStock>
    implements Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 5354102238805886876L;

    /**
     * Type des documents managés.
     */
    private final TypeDocumentStock typeDocuments;

    /**
     * Service de gestion des documents.
     */
    private DocumentService docService;

    /**
     * Fabrique d'objets DocumentStock.
     */
    private DocumentStockFactory<DocumentStock> factory;

    /**
     * Helper pour la transformation du fichier remonté de l'IHM<br>
     * en fichier métier.
     */
    private FichierFiller fichierFiller;

    /**
     * Fichier à importer.
     */
    private Fichier fichier = new Fichier();

    /**
     * Constructeur.
     * @param typeDocumentsName Nom du type des documents managés.
     * @throws IllegalArgumentException Si typeDocumentsName ne correspond pas à une valeur de
     * l'énumération {@link TypeDocumentStock}.
     */
    public GenericStockManager(final String typeDocumentsName)
    {
        this.typeDocuments = TypeDocumentStock.valueOf(typeDocumentsName);
    }

    /**
     * Méthode qui indique si un document peut être créé à partir des informations (fichier)
     * mémorisés.
     * @return true ssi la méthode createDocument peut être appelée.
     */
    public boolean canCreateDocument()
    {
        return this.fichier.getContenu().length > 0;
    }

    /**
     * Crée un objet document à partir du libellé et du fichier renseignés <br>
     * et remet à blanc ces informations. La méthode canCreateDocument <br>
     * doit avoir été appelée et son retour doit être true.
     * @param mvtStock Mouvement de stock sur lequel on souhaite créer le document.
     * @return Un nouveau DocumentStock. Le fichier physique est créé sur le disque.
     */
    public DocumentStock createDocument(final MvtStock mvtStock)
    {
        // Document BDD.
        final DocumentStock doc = this.factory.getInitializedObject(this.fichier,
                                                                    mvtStock);

        // Document Physique.
        this.docService.saveOnDisk(mvtStock,
                                   doc,
                                   this.fichier);

        this.resetFormDatas();
        return doc;
    }

    /**
     * Méthode de réinitialisation des données du formulaire (fichier).
     */
    public void resetFormDatas()
    {
        this.resetFichier();
    }

    /**
     * Méthode de réinitialisation du fichier.
     */
    private void resetFichier()
    {
        this.fichier = new Fichier();
    }

    /**
     * Getter sur fichier.
     * @return Retourne le fichier.
     */
    public Fichier getFichier()
    {
        return this.fichier;
    }

    /**
     * Setter pour fichier.
     * @param fichier le fichier à écrire.
     */
    public void setFichier(final Fichier fichier)
    {
        this.fichier = fichier;
    }

    /**
     * Getter sur docService.
     * @return Retourne le docService.
     */
    DocumentService getDocService()
    {
        return this.docService;
    }

    /**
     * Setter pour docService.
     * @param docService le docService à écrire.
     */
    public void setDocService(final DocumentService docService)
    {
        this.docService = docService;
    }

    /**
     * Getter sur file.
     * @return Retourne le file.
     */
    public UploadedFile getFile()
    {
        return null;
    }

    /**
     * Setter pour file.
     * @param file le file à écrire.
     */
    public void setFile(final UploadedFile file)
    {
        this.resetFichier();
        if (file == null)
        {
            return;
        }
        this.fichierFiller.fill(file,
                                this.fichier);
    }

    /**
     * Getter sur fichierFiller.
     * @return Retourne le fichierFiller.
     */
    FichierFiller getFichierFiller()
    {
        return this.fichierFiller;
    }

    /**
     * Setter pour fichierFiller.
     * @param fichierFiller le fichierFiller à écrire.
     */
    public void setFichierFiller(final FichierFiller fichierFiller)
    {
        this.fichierFiller = fichierFiller;
    }

    /**
     * Getter sur typeDocuments.
     * @return Retourne le typeDocuments.
     */
    public TypeDocumentStock getTypeDocuments()
    {
        return this.typeDocuments;
    }

    /**
     * Setter pour factory.
     * @param factory Le factory à écrire.
     */
    public void setFactory(final DocumentStockFactory<DocumentStock> factory)
    {
        this.factory = factory;
    };

}
