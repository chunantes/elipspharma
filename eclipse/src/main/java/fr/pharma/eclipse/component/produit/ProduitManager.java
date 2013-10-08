package fr.pharma.eclipse.component.produit;

import java.util.SortedMap;

import javax.faces.model.DataModel;

import org.primefaces.event.TabChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.component.BeanManager;
import fr.pharma.eclipse.domain.enums.TypeHistoriqueProduit;
import fr.pharma.eclipse.domain.enums.document.TypeDocumentProduit;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.DocumentProduit;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.document.DocumentService;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Manager de Produit.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ProduitManager extends BeanManager<Produit> {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 6698150218110904174L;

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(ProduitManager.class);

    /**
     * Index de l'onglet courant.
     */
    private int indexOngletCourant;

    /**
     * Service document.
     */
    private DocumentService documentService;

    /**
     * Le produit est-il en enregistré?.
     */
    private Boolean enregistre;

    /**
     * Dictionnaire des managers des documents du produit selon leur type.
     */
    @SuppressWarnings("unchecked")
    private SortedMap<String, GenericDocumentManager> documentsManagers;

    /**
     * Constructeur.
     * @param service Service des essais.
     */
    public ProduitManager(final GenericService<Produit> service) {
        super(service);
    }

    /**
     * Méthode d'initialisation du manager.
     */
    public void init() {
        this.setBean(null);
        this.setEnregistre(false);
    }

    /** ******************************* **/
    /** GESTION DE L'ONGLET COURANT **/
    /** ******************************* **/

    /**
     * Listener appelé lorsque l'utilisateur change d'onglet.
     * @param event Evénement remonté par le composant primeFaces.
     */
    public void onOngletChange(final TabChangeEvent event) {
        final String tabId = event.getTab().getId();
        this.selectOngletCourant(tabId);
    }

    /**
     * Méthode de sélection de l'onglet courant.
     * @param tabId Identifiant de l'onglet à sélectionner.
     */
    public void selectOngletCourant(final String tabId) {
        try {
            this.setIndexOngletCourant(TypeHistoriqueProduit.valueOf(tabId).getIndexIHM());
        } catch (final IllegalArgumentException illArgExc) {
            if (this.log.isDebugEnabled()) {
                this.log.debug(new StringBuilder("[onOngletChange] ").append("Erreur de récupération de l'onglet sélectionné : ")
                        .append("pas de correspondance avec TypeHistoriqueProduit pour l'id ").append(tabId).append(".").toString());
            }
        }
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
    @SuppressWarnings("unchecked")
    public GenericDocumentManager getDocumentManager(final TypeDocumentProduit typeDocument) {
        final GenericDocumentManager manager = this.documentsManagers.get(typeDocument.name());
        if ((manager == null) && this.log.isDebugEnabled()) {
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
    public void ajouterDocument(final TypeDocumentProduit typeDocument) {
        final GenericDocumentManager docManager = this.getDocumentManager(typeDocument);
        if (docManager == null) {
            return;
        }
        if (!docManager.canCreateDocument()) {
            docManager.resetFormDatas();
            return;
        }
        final DocumentProduit doc = docManager.createDocument(this.getBean());
        BeanTool.setPropriete(this.getBean(), typeDocument.getPropriete(), doc);
    }

    /**
     * Méthode en charge de supprimer un document d'un produit.
     * @param typeDocument Le type de document.
     */
    public void supprimerDocument(final TypeDocumentProduit typeDocument) {
        final DocumentProduit doc = (DocumentProduit) BeanTool.getPropriete(this.getBean(), typeDocument.getPropriete());

        BeanTool.setPropriete(this.getBean(), typeDocument.getPropriete(), null);

        // Pour supprimer le document en BDD car le orphan removal de fonctionne
        // pas...
        if (doc.getId() != null) {
            this.documentService.remove(doc);
        }
    }

    /**
     * Méthode en charge de supprimer un conditionnement du produit courant.
     * @param conditionnement Conditionnement.
     */
    public void removeConditionnement(final Conditionnement conditionnement) {
        this.getBean().getConditionnements().remove(conditionnement);
    }

    /** ************************ **/
    /** GET / SET **/
    /** ************************ **/

    /**
     * Getter sur indexOngletCourant.
     * @return Retourne le indexOngletCourant.
     */
    public int getIndexOngletCourant() {
        return this.indexOngletCourant;
    }

    /**
     * Setter pour indexOngletCourant.
     * @param indexOngletCourant le indexOngletCourant à écrire.
     */
    public void setIndexOngletCourant(final int indexOngletCourant) {
        this.indexOngletCourant = indexOngletCourant;
    }

    /**
     * Getter sur documentsManagers.
     * @return Retourne le documentsManagers.
     */
    public SortedMap<String, GenericDocumentManager> getDocumentsManagers() {
        return this.documentsManagers;
    }

    /**
     * Setter pour documentsManagers.
     * @param documentsManagers le documentsManagers à écrire.
     */
    public void setDocumentsManagers(final SortedMap<String, GenericDocumentManager> documentsManagers) {
        this.documentsManagers = documentsManagers;
    }

    /**
     * Setter pour documentService.
     * @param documentService le documentService à écrire.
     */
    public void setDocumentService(final DocumentService documentService) {
        this.documentService = documentService;
    }

    public Boolean getEnregistre() {
        return this.enregistre;
    }

    public void setEnregistre(final Boolean enregistre) {
        this.enregistre = enregistre;
    }

}
