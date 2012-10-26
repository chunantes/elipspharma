package fr.pharma.eclipse.component.essai;

import java.io.File;
import java.io.Serializable;
import java.util.SortedSet;

import org.apache.commons.lang.StringUtils;
import org.apache.myfaces.custom.fileupload.UploadedFile;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentEssai;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.common.Fichier;
import fr.pharma.eclipse.domain.model.essai.DocumentEssai;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.factory.document.essai.DocumentEssaiFactory;
import fr.pharma.eclipse.file.FichierFiller;
import fr.pharma.eclipse.service.document.DocumentService;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Classe générique en charge de gérer un type de documents sur un essai clinique.
 * @param <DOC> Type générique de document d'essai.
 * @param <PARENT> Type dérivé de {@link BeanObject} représentant le parent du document.
 
 * @version $Revision$ $Date$
 */
public class GenericDocumentEssaiManager<DOC extends DocumentEssai, PARENT extends BeanObject>
    implements Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3601548264868774784L;

    /**
     * Type des documents managés.
     */
    private final TypeDocumentEssai typeDocuments;

    /**
     * Service de gestion des documents.
     */
    private DocumentService docService;

    /**
     * Fabrique d'objets DocumentEssai.
     */
    private DocumentEssaiFactory<DOC, PARENT> factory;

    /**
     * Helper pour la transformation du fichier remonté de l'IHM<br>
     * en fichier métier.
     */
    private FichierFiller fichierFiller;

    /**
     * Dernier document ajouté.
     */
    private DOC lastDocument;

    /**
     * Commentaire pour un nouveau document.
     */
    private String commentaire = StringUtils.EMPTY;

    /**
     * Fichier à importer.
     */
    private Fichier fichier = new Fichier();

    /**
     * Constructeur.
     * @param typeDocumentsName Nom du type des documents managés.
     * @throws IllegalArgumentException Si typeDocumentsName ne correspond pas à une valeur de
     * l'énumération {@link TypeDocumentEssai}.
     */
    public GenericDocumentEssaiManager(final String typeDocumentsName)
    {
        this.typeDocuments = TypeDocumentEssai.valueOf(typeDocumentsName);
    }

    /**
     * Méthode qui indique si un document peut être créé à partir des informations<br>
     * (commentaire + fichier) mémorisées.
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
     * @param essai Essai sur lequel on souhaite créer le document.
     * @return Un nouveau DocumentEssai. Le fichier physique est créé sur le disque.
     */
    @SuppressWarnings("unchecked")
    public DOC createDocument(final Essai essai)
    {
        // Document BDD.
        final PARENT parent =
            (PARENT) BeanTool.getPropriete(essai,
                                           this.typeDocuments
                                                   .getDocumentsParentPropertyFromEssai());
        final DOC doc = this.factory.getInitializedObject(parent,
                                                          this.fichier,
                                                          this.commentaire.trim());

        // Document Physique.
        this.docService.saveOnDisk(essai,
                                   doc,
                                   this.fichier);

        this.resetFormDatas();
        return doc;
    }

    /**
     * Méthode de réinitialisation des données du formulaire (fichier + commentaire).
     */
    public void resetFormDatas()
    {
        this.commentaire = StringUtils.EMPTY;
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
     * Méthode de récupération du dernier document du type managé d'un essai.
     * @param essai Essai.
     */
    @SuppressWarnings("unchecked")
    public void initLastDocument(final Essai essai)
    {
        this.resetLastDocument();
        final SortedSet<DOC> documents =
            (SortedSet<DOC>) BeanTool.getPropriete(essai,
                                                   this.typeDocuments
                                                           .getDocumentsPropertyFromEssai());
        if (documents.isEmpty())
        {
            return;
        }
        this.setLastDocument(documents.first());
    }

    /**
     * Méthode de réinitialisation du dernier document mémorisé.
     */
    private void resetLastDocument()
    {
        this.lastDocument = null;
    }

    /**
     * Getter sur typeDocuments.
     * @return Retourne le typeDocuments.
     */
    TypeDocumentEssai getTypeDocuments()
    {
        return this.typeDocuments;
    }

    /**
     * Getter sur lastDocument.
     * @return Retourne le lastDocument.
     */
    public DOC getLastDocument()
    {
        return this.lastDocument;
    }

    /**
     * Setter pour lastDocument.
     * @param lastDocument le lastDocument à écrire.
     */
    void setLastDocument(final DOC lastDocument)
    {
        this.lastDocument = lastDocument;
    }

    /**
     * Getter sur commentaire.
     * @return Retourne le commentaire.
     */
    public String getCommentaire()
    {
        return this.commentaire;
    }

    /**
     * Setter pour commentaire.
     * @param commentaire le commentaire à écrire.
     */
    public void setCommentaire(final String commentaire)
    {
        this.commentaire = commentaire;
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
     * Setter pour factory.
     * @param factory le factory à écrire.
     */
    public void setFactory(final DocumentEssaiFactory<DOC, PARENT> factory)
    {
        this.factory = factory;
    }

    /**
     * Getter sur factory.
     * @return Retourne le factory.
     */
    DocumentEssaiFactory<DOC, PARENT> getFactory()
    {
        return this.factory;
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
     * Setter pour file.
     * @param file le file à écrire.
     */
    public void setFileType(final File file)
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

}
