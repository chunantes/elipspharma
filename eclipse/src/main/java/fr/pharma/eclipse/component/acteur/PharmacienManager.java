package fr.pharma.eclipse.component.acteur;

import java.util.SortedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.component.BeanManager;
import fr.pharma.eclipse.domain.enums.document.TypeDocumentPharmacien;
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.domain.model.acteur.document.DocumentPharmacien;
import fr.pharma.eclipse.service.acteur.PersonneService;
import fr.pharma.eclipse.service.document.DocumentService;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Manager de Pharmacien.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PharmacienManager extends BeanManager<Pharmacien> {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -7800239520935716715L;

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(PharmacienManager.class);

    /**
     * Service document.
     */
    private DocumentService documentService;

    /**
     * Dictionnaire des managers des documents du produit selon leur type.
     */
    private SortedMap<String, GenericDocumentManager<DocumentPharmacien>> documentsManagers;

    /**
     * Constructeur.
     * @param pharmacienService Service de gestion des pharmaciens.
     */
    public PharmacienManager(final PersonneService<Pharmacien> pharmacienService) {
        super(pharmacienService);
    }

    /** ************************ **/
    /**
     * DOCUMENTS /** ************************
     **/
    /**
     * Méthode de récupération du manager des documents d'un certain type dans
     * la map.
     * @param typeDocument Type de document.
     * @return Le GenericDocumentManager correspondant au type de document dans
     * la map. Null si inexistant.
     */
    public GenericDocumentManager getDocumentManager(final TypeDocumentPharmacien typeDocument) {
        final GenericDocumentManager manager = this.documentsManagers.get(typeDocument.name());
        if (manager == null && this.log.isDebugEnabled()) {
            this.log.error(new StringBuilder("[getManager] ").append("Aucun manager de documents n'est défini dans la map pour le type ").append(typeDocument.name())
                    .append(" : le document n'est pas traité.").toString());
        }
        return manager;
    }

    /**
     * Méthode en charge d'ajouter un nouveau document d'un certain type.
     * @param typeDocument Type du document d'essai à ajouter.
     */
    @SuppressWarnings("unchecked")
    public void ajouterDocument(final TypeDocumentPharmacien typeDocument) {
        final GenericDocumentManager<DocumentPharmacien> docManager = this.getDocumentManager(typeDocument);
        if (docManager == null) {
            return;
        }
        if (!docManager.canCreateDocument()) {
            docManager.resetFormDatas();
            return;
        }
        final DocumentPharmacien doc = docManager.createDocument(this.getBean());
        BeanTool.setPropriete(this.getBean(), typeDocument.getPropriete(), doc);
    }

    /**
     * Méthode en charge de supprimer un document d'un produit.
     * @param typeDocument Le type de document.
     */
    public void supprimerDocument(final TypeDocumentPharmacien typeDocument) {
        final DocumentPharmacien doc = (DocumentPharmacien) BeanTool.getPropriete(this.getBean(), typeDocument.getPropriete());

        BeanTool.setPropriete(this.getBean(), typeDocument.getPropriete(), null);

        // Pour supprimer le document en BDD car le orphan removal de fonctionne
        // pas...
        if (doc.getId() != null) {
            this.documentService.remove(doc);
        }
    }

    /**
     * Getter pour documentService.
     * @return Le documentService
     */
    public DocumentService getDocumentService() {
        return this.documentService;
    }

    /**
     * Setter pour documentService.
     * @param documentService Le documentService à écrire.
     */
    public void setDocumentService(final DocumentService documentService) {
        this.documentService = documentService;
    }

    /**
     * Getter pour documentsManagers.
     * @return Le documentsManagers
     */
    public SortedMap<String, GenericDocumentManager<DocumentPharmacien>> getDocumentsManagers() {
        return this.documentsManagers;
    }

    /**
     * Setter pour documentsManagers.
     * @param documentsManagers Le documentsManagers à écrire.
     */
    public void setDocumentsManagers(final SortedMap<String, GenericDocumentManager<DocumentPharmacien>> documentsManagers) {
        this.documentsManagers = documentsManagers;
    }

}
