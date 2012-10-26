package fr.pharma.eclipse.component.essai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.SortedMap;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.model.DataModel;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import fr.pharma.eclipse.component.BeanManager;
import fr.pharma.eclipse.component.SelectableBeansManager;
import fr.pharma.eclipse.component.essai.helper.DicoSuivisEssai;
import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.enums.GroupeContacts;
import fr.pharma.eclipse.domain.enums.TypeCommentaireEssai;
import fr.pharma.eclipse.domain.enums.TypeHistoriqueEssai;
import fr.pharma.eclipse.domain.enums.document.TypeDocumentEssai;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.acteur.Promoteur;
import fr.pharma.eclipse.domain.model.alerte.Alerte;
import fr.pharma.eclipse.domain.model.common.DocumentEclipse;
import fr.pharma.eclipse.domain.model.essai.CommentaireEssai;
import fr.pharma.eclipse.domain.model.essai.DocumentEssai;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.predicate.GenericPredicate;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.document.DocumentService;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.utils.FacesUtils;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Manager de Essai.
 
 * @version $Revision$ $Date$
 */
public class EssaiManager
    extends BeanManager<Essai>
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 6698150218110904174L;

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(EssaiManager.class);

    /**
     * Gestionnaire des contacts.
     */
    private ContactsManager contactsManager;

    /**
     * Service document.
     */
    private DocumentService documentService;

    /**
     * Id des onglets visités.
     */
    private String idOngletVisites;

    /**
     * Utils Faces.
     */
    @Resource(name = "facesUtils")
    private FacesUtils facesUtils;

    /**
     * Dictionnaire des managers de commentaires de l'essai selon leur type.
     */
    @SuppressWarnings("rawtypes")
    private SortedMap<String, GenericCommentaireManager> commentairesManagers;

    /**
     * Dictionnaire des managers des collections de documents de l'essai selon leur type.
     */
    @SuppressWarnings("rawtypes")
    private SortedMap<String, GenericDocumentEssaiManager> documentsCollectionManagers;

    /**
     * Dictionnaire des managers de documents de l'essai selon leur type.
     */
    @SuppressWarnings("rawtypes")
    private SortedMap<String, GenericDocumentEssaiManager> documentsManagers;

    /**
     * Dictionnaire des dernières modifications de l'essai.
     */
    private DicoSuivisEssai dicoDernieresModifs;

    /**
     * Index de l'onglet courant.
     */
    private int indexOngletCourant;

    /**
     * Evénement sélectionné.
     */
    private Evenement evenementSelected;

    /**
     * Commentaire changement etat.
     */
    private String commentaireChgtEtat;

    /**
     * Etat du changement de l'état.
     */
    private EtatEssai etatChgtEtat;

    /**
     * Liste des alertes de l'essai.
     */
    private List<Alerte> alertes;

    /**
     * Onglet visites.
     */
    private final List<String> ongletVisites = new ArrayList<String>();

    /**
     * Constructeur.
     * @param service Service des essais.
     */
    public EssaiManager(final GenericService<Essai> service)
    {
        super(service);
        this.log.debug("Construction de l'objet EssaiManager : "
                       + this);
    }
    /** ************************ **/
    /** GESTION DU PROMOTEUR **/
    /** ************************ **/

    /**
     * Méthode appelée via la couche IHM lorsqu'un promoteur est sélectionné.
     * @param event Evénement remonté via la couche IHM.
     */
    public void handleSelectPromoteur(final SelectEvent event)
    {
        this.updateTypePromoteur((Promoteur) event.getObject());
    }

    /**
     * Méthode en charge de mettre à jour le type de promoteur<br>
     * à partir des données du promoteur.
     * @param promoteur Promoteur sélectionné.
     */
    void updateTypePromoteur(final Promoteur promoteur)
    {
        if (promoteur == null
            || promoteur.getType() == null)
        {
            return;
        }
        this.getBean().setTypePromoteur(promoteur.getType());
    }

    /** ************************ **/
    /** GESTION DES CONTACTS **/
    /** ************************ **/

    /**
     * Méthode de récupération des habilitations du groupe Promoteurs.
     * @return Objet DataModel.
     */
    public DataModel<Habilitation> getGroupePromoteurs()
    {
        this.reattach();
        return this
                .getHelper()
                .returnAsDataModel(this.contactsManager.getGroupeHabilitations(this.getBean(),
                                                                               GroupeContacts.PROMOTEURS));
    }

    /**
     * Méthode de récupération des habilitations du groupe Investigateurs.
     * @return Objet DataModel.
     */
    public DataModel<Habilitation> getGroupeInvestigateurs()
    {
        return this
                .getHelper()
                .returnAsDataModel(this.contactsManager.getGroupeHabilitations(this.getBean(),
                                                                               GroupeContacts.INVESTIGATEURS));
    }

    /**
     * Méthode de récupération des habilitations du groupe DRC.
     * @return Objet DataModel.
     */
    public DataModel<Habilitation> getGroupeDRC()
    {
        return this
                .getHelper()
                .returnAsDataModel(this.contactsManager.getGroupeHabilitations(this.getBean(),
                                                                               GroupeContacts.DRC));
    }

    /**
     * Méthode de récupération des habilitations du groupe PHARMACIENS.
     * @return Objet DataModel.
     */
    public DataModel<Habilitation> getGroupePharmaciens()
    {
        return this
                .getHelper()
                .returnAsDataModel(this.contactsManager.getGroupeHabilitations(this.getBean(),
                                                                               GroupeContacts.PHARMACIENS));
    }

    /**
     * Méthode qui indique si l'essai possède un investigateur principal.
     * @return true ssi l'essai possède une habilitation avec un droit Investigateur principal.
     */
    public boolean hasInvestigateurPrincipal()
    {
        return this.contactsManager.hasInvestigateurPrincipal(this.getBean());
    }

    /**
     * Méthode en charge de désactiver les contacts sélectionnés.
     */
    public void disableContacts()
    {
        this.contactsManager.disableContacts(this.getBean());
    }

    /**
     * Getter sur le type de contact à ajouter.
     * @return Retourne le TypeContact.
     */
    public TypeContact getTypeContactToAdd()
    {
        return this.contactsManager.getTypeContactToAdd();
    }

    /**
     * Setter sur le type de contact à ajouter.
     * @param typeContactToAdd type de contact à ajouter.
     */
    public void setTypeContactToAdd(final TypeContact typeContactToAdd)
    {
        this.contactsManager.setTypeContactToAdd(typeContactToAdd);
    }

    /**
     * Méthode en charge de lancer le calcul des contacts sélectionnables pour l'ajout.
     * @param typeContactToAdd Type de contact à ajouter.
     */
    public void calculatePersons(final TypeContact typeContactToAdd)
    {
        this.contactsManager.setTypeContactToAdd(typeContactToAdd);
        this.contactsManager.initSelectableContacts(this.getBean());
    }

    /**
     * Méthode en charge de récupérer le manager des contacts sélectionnables.
     * @return Le manager des contacts sélectionnables.
     */
    public SelectableBeansManager<Personne> getContactsBeansManager()
    {
        return this.contactsManager.getSelectableContactsManager();
    }

    /**
     * Méthode en charge de valider l'ajout des contacts.
     */
    public void validerAjoutContacts()
    {
        this.contactsManager.ajouterContacts(this.getBean());
    }

    /**
     * Méthode de récupération de l'habilitation sélectionnée.
     * @return L'habilitation sélectionnée.
     */
    public Habilitation getSelectedHabilitation()
    {
        return this.contactsManager.getSelectedHabilitation();
    }

    /**
     * Méthode de valorisation de l'habilitation sélectionnée.
     * @param selectedHabilitation L'habilitation sélectionnée.
     */
    public void setSelectedHabilitation(final Habilitation selectedHabilitation)
    {
        this.contactsManager.setSelectedHabilitation(selectedHabilitation);
    }

    /**
     * Méthode en charge de réinitialiser les données du manager des contacts.
     */
    public void resetContactsManager()
    {
        this.contactsManager.reset();
    }

    /** ************************ **/
    /** GESTION DES COMMENTAIRES **/
    /** ************************ **/

    /**
     * Méthode d'initialisation des derniers commentaires où les types à initialiser sont
     * spécifiés.
     * @param types Liste des noms des types de commentaires à initialiser.<br>
     * S'il n'y a pas de paramètres, appelle la méthode initLastCommentaires sans paramètre.
     */
    @SuppressWarnings("rawtypes")
    public void initLastCommentaires(final String... types)
    {
        if (types == null
            || types.length == 0)
        {
            this.initLastCommentaires(this.commentairesManagers.values());
            return;
        }
        final List<String> typesList = Arrays.asList(types);
        final Predicate predicate = new Predicate() {

            @Override
            public boolean evaluate(final Object object)
            {
                final GenericCommentaireManager manager = (GenericCommentaireManager) object;
                return typesList.contains(manager.getTypeCommentaire().name());
            }
        };
        final List<GenericCommentaireManager> managers =
            (List<GenericCommentaireManager>) CollectionUtils.select(this.commentairesManagers
                                                                             .values(),
                                                                     predicate);
        this.initLastCommentaires(managers);
    }

    /**
     * Méthode d'initialisation des derniers commentaires d'une liste de managers.
     * @param managers Managers à initialiser.
     */
    @SuppressWarnings("rawtypes")
    private void initLastCommentaires(final Collection<GenericCommentaireManager> managers)
    {
        for (final GenericCommentaireManager manager : managers)
        {
            manager.initLastCommentaire(this.getBean());
        }
    }

    /**
     * Méthode en charge d'ajouter un nouveau commentaire d'un certain type.
     * @param typeCommentaire Type du commentaire à ajouter.
     */
    @SuppressWarnings("rawtypes")
    public void ajouterCommentaire(final TypeCommentaireEssai typeCommentaire)
    {
        final GenericCommentaireManager commentManager =
            this.getCommentaireManager(typeCommentaire);
        if (commentManager == null)
        {
            return;
        }
        if (!commentManager.canCreateCommentaire())
        {
            commentManager.resetLibelle();
            return;
        }
        final CommentaireEssai commentaire = commentManager.createCommentaire(this.getBean());
        this.getHelper().addToCollection(this.getBean(),
                                         typeCommentaire.getCommentairesPropertyFromEssai(),
                                         commentaire);
        commentManager.setLastCommentaire(commentaire);
    }

    /**
     * Méthode en charge de récupérer le commentaire le premier commentaire de l'ensemble.
     * @param typeCommentaire Type du commentaire.
     * @return Le bean CommentaireEssaiRecherche le plus récent (null si aucun commentaire).
     */
    @SuppressWarnings("rawtypes")
    public CommentaireEssai getLastCommentaire(final TypeCommentaireEssai typeCommentaire)
    {
        final GenericCommentaireManager commentManager =
            this.getCommentaireManager(typeCommentaire);
        if (commentManager == null)
        {
            return null;
        }
        return commentManager.getLastCommentaire();
    }

    /**
     * Méthode de récupération d'un manager de commentaires d'un certain type dans la map.
     * @param typeCommentaireName Nom du type de commentaire {@link TypeCommentaireEssai}.
     * @return Le GenericCommentaireManager correspondant au type de commentaire dans la map. Null
     * si inexistant.
     */
    @SuppressWarnings("rawtypes")
    public GenericCommentaireManager getCommentaireManager(final String typeCommentaireName)
    {
        return this.getCommentaireManager(TypeCommentaireEssai.valueOf(typeCommentaireName));
    }

    /**
     * Méthode de récupération d'un manager de commentaires d'un certain type dans la map.
     * @param typeCommentaire Type de commentaire.
     * @return Le GenericCommentaireManager correspondant au type de commentaire dans la map. Null
     * si inexistant.
     */
    @SuppressWarnings("rawtypes")
    private GenericCommentaireManager getCommentaireManager(final TypeCommentaireEssai typeCommentaire)
    {
        final GenericCommentaireManager commentManager =
            this.commentairesManagers.get(typeCommentaire.name());
        if (commentManager == null
            && this.log.isDebugEnabled())
        {
            this.log.error(new StringBuilder("[getManager] ")
                    .append("Aucun manager de commentaires ")
                    .append("n'est défini dans la map pour le type ")
                    .append(typeCommentaire.name())
                    .append(" : le commentaire n'est pas traité.")
                    .toString());
        }
        return commentManager;
    }

    /** ******************************* **/
    /** GESTION DES DOCUMENTS **/
    /** ******************************* **/

    /**
     * Méthode de récupération du manager des documents d'un certain type dans la map.
     * @param typeDocument Type de document.
     * @param collection Manager d'une collection ou non.
     * @return Le GenericDocumentManager correspondant au type de document dans la map. Null si
     * inexistant.
     */
    @SuppressWarnings("rawtypes")
    public GenericDocumentEssaiManager getDocumentManager(final TypeDocumentEssai typeDocument,
                                                          final boolean collection)
    {
        GenericDocumentEssaiManager manager = null;
        if (collection)
        {
            manager = this.documentsCollectionManagers.get(typeDocument.name());
        }
        else
        {

            manager = this.documentsManagers.get(typeDocument.name());
        }

        if (manager == null
            && this.log.isDebugEnabled())
        {
            this.log.error(new StringBuilder("[getDocumentManager] ")
                    .append("Aucun manager de documents n'est défini dans la map pour le type ")
                    .append(typeDocument.name())
                    .append(" : le document n'est pas traité.")
                    .toString());
        }
        return manager;
    }

    /**
     * Méthode d'initialisation des derniers documents de chacun des managers de documents.
     */
    @SuppressWarnings("rawtypes")
    public void initLastDocuments()
    {
        for (final GenericDocumentEssaiManager manager : this.documentsCollectionManagers
                .values())
        {
            manager.initLastDocument(this.getBean());
        }
    }

    /**
     * Méthode de récupération du dernier document d'un certain type.
     * @param typeDocument Type du document.
     * @return Le dernier document du type spécifié, null si pas de manager.
     */
    public DocumentEssai getLastDocument(final TypeDocumentEssai typeDocument)
    {
        @SuppressWarnings("rawtypes")
        final GenericDocumentEssaiManager manager = this.getDocumentManager(typeDocument,
                                                                            true);
        if (manager == null)
        {
            return null;
        }
        return manager.getLastDocument();
    }

    /**
     * Méthode en charge d'ajouter un nouveau document d'un certain type.
     * @param typeDocument Type du document d'essai à ajouter.
     * @param collection Document d'une collection ou non
     */
    public void ajouterDocument(final TypeDocumentEssai typeDocument,
                                final boolean collection)
    {
        @SuppressWarnings("rawtypes")
        final GenericDocumentEssaiManager docManager = this.getDocumentManager(typeDocument,
                                                                               collection);
        if (docManager == null)
        {
            return;
        }
        if (!docManager.canCreateDocument())
        {
            docManager.resetFormDatas();
            return;
        }
        final DocumentEssai doc = docManager.createDocument(this.getBean());

        if (collection)
        {
            this.getHelper().addToCollection(this.getBean(),
                                             typeDocument.getDocumentsPropertyFromEssai(),
                                             doc);

            docManager.setLastDocument(doc);
        }
        else
        {
            // parsing de la propriété représentant le document.
            final String propertyDocument =
                StringUtils.substringAfterLast(typeDocument.getDocumentsPropertyFromEssai(),
                                               ".");
            // parsing de la propriété du parent du document à partir de l'essai.
            final String parentProperty =
                StringUtils.substringBeforeLast(typeDocument.getDocumentsPropertyFromEssai(),
                                                ".");
            BeanTool.setPropriete(BeanTool.getPropriete(this.getBean(),
                                                        parentProperty),
                                  propertyDocument,
                                  doc);
        }
    }
    /**
     * Méthode en charge de supprimer un document d'un produit.
     * @param typeDocument Le type de document.
     */
    public void supprimerDocument(final TypeDocumentEssai typeDocument)
    {
        final DocumentEclipse doc =
            (DocumentEssai) BeanTool.getPropriete(this.getBean(),
                                                  typeDocument.getDocumentsPropertyFromEssai());

        // parsing de la propriété représentant le document.
        final String propertyDocument =
            StringUtils.substringAfterLast(typeDocument.getDocumentsPropertyFromEssai(),
                                           ".");
        // parsing de la propriété du parent du document à partir de l'essai.
        final String parentProperty =
            StringUtils.substringBeforeLast(typeDocument.getDocumentsPropertyFromEssai(),
                                            ".");

        BeanTool.setPropriete(BeanTool.getPropriete(this.getBean(),
                                                    parentProperty),
                              propertyDocument,
                              null);

        // Pour supprimer le document en BDD car le orphan removal de fonctionne pas...
        if (doc.getId() != null)
        {
            this.documentService.remove(doc);
        }
    }
    /** ************************ **/
    /** GESTION DES HISTORIQUES **/
    /** ************************ **/
    /**
     * Méthode d'initialisation des dernières modifications de l'essai.<br>
     * Le bean du manager est non nul.
     */
    public void initLastModifs()
    {
        Assert.notNull(this.getBean(),
                       "Le bean du manager doit être non nul.");
        this.dicoDernieresModifs.init(this.getBean());
    }

    /**
     * Méthode pour indiquer si l'essai possède une dernière modification d'un certain type.
     * @param ongletEssaiName Type de la modification, correspondant au nom à la valeur de
     * l'énumération TypeHistoriqueEssai correspondante.
     * @return true ssi l'essai possède une dernière modification pour le type donné.
     */
    public boolean hasLastModif(final String ongletEssaiName)
    {
        return this.dicoDernieresModifs.hasDerniereModif(TypeHistoriqueEssai
                .valueOf(ongletEssaiName));
    }

    /**
     * Méthode en charge de récupérer la dernière modification d'un certain type de l'essai.
     * @param ongletEssaiName Type de la modification, correspondant au nom à la valeur de
     * l'énumération TypeHistoriqueEssai correspondante.
     * @return La dernière modification.
     */
    public Suivi getLastModif(final String ongletEssaiName)
    {
        return this.dicoDernieresModifs.getDerniereModif(TypeHistoriqueEssai
                .valueOf(ongletEssaiName));
    }

    /** ******************************* **/
    /** GESTION DE L'ONGLET COURANT **/
    /** ******************************* **/

    /**
     * Listener appelé lorsque l'utilisateur change d'onglet.
     * @param event Evénement remonté par le composant primeFaces.
     */
    public void onOngletChange(final TabChangeEvent event)
    {
        final String tabId = event.getTab().getId();
        if (!StringUtils.contains(this.idOngletVisites,
                                  tabId)
            && StringUtils.isNotEmpty(TypeHistoriqueEssai
                    .valueOf(tabId)
                    .getModifsPropertyFromEssai()))

        {
            this.idOngletVisites += tabId
                                    + ",";
            this.ongletVisites.add(tabId);

        }
        this.selectOngletCourant(tabId);
    }

    /**
     * Méthode de sélection de l'onglet courant.
     * @param tabId Identifiant de l'onglet à sélectionner.
     */
    public void selectOngletCourant(final String tabId)
    {
        try
        {
            this.setIndexOngletCourant(TypeHistoriqueEssai.valueOf(tabId).getIndexIHM());
        }
        catch (final IllegalArgumentException illArgExc)
        {
            if (this.log.isDebugEnabled())
            {
                this.log.debug(new StringBuilder("[onOngletChange] ")
                        .append("Erreur de récupération de l'onglet sélectionné : ")
                        .append("pas de correspondance avec TypeHistoriqueEssai pour l'id ")
                        .append(tabId)
                        .append(".")
                        .toString());
            }
        }
    }

    /**
     * Méthode en charge de gérer le reset du commentaire de changement d'état.
     */
    public void resetChangementEtat()
    {
        this.setEtatChgtEtat(this.getBean().getEtat());
        this.setCommentaireChgtEtat(null);
    }

    /**
     * Méthode en charge de gérer le changement d'état d'un essai.
     */
    public void ajouterDetailEtatEssai()
    {
        // Mise à jour de l'état actuel de l'essai
        this.getBean().setEtat(this.getEtatChgtEtat());

        // Ajout d'un nouveau DetailEtatEssai à l'essai
        ((EssaiService) this.getService()).addDetailEtatEssai(this.getBean(),
                                                              this.getEtatChgtEtat(),
                                                              this.getCommentaireChgtEtat());
    }

    /**
     * Méthode en charge de définir si les champs de surcouts previsionnel sont éditables.
     * @return true si les champs sont editables.
     */
    public boolean isSurcoutPrevisionnelEditable()
    {
        return this.getBean().getEtat().equals(EtatEssai.EN_EVALUATION)
               || this.getBean().getEtat().equals(EtatEssai.EN_ATTENTE_MISE_EN_PLACE)
               || this.getBean().getEtat().equals(EtatEssai.MISE_EN_PLACE);
    }

    /**
     * Méthode de sauvegarde en ajax d'un essai.
     */
    public void save()
    {
        try
        {
            this.setBean(((EssaiService) this.getService()).save(this.getBean(),
                                                                 this.idOngletVisites));
            this.facesUtils.addMessage(FacesMessage.SEVERITY_INFO,
                                       "essai.enregistrement.ok");
        }
        catch (final ValidationException e)
        {
            final StringBuilder strToResolve = new StringBuilder();
            strToResolve.append(e.getErrorCode());
            strToResolve.append(".");
            strToResolve.append(e.getValues()[0]);
            this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR,
                                       strToResolve.toString());
        }
        this.idOngletVisites =
            StringUtils.substringAfterLast(StringUtils.substringBeforeLast(this.idOngletVisites,
                                                                           ","),
                                           ",")
                    + ",";
    }

    public Produit getProduitByName(final Produit produit)
    {
        @SuppressWarnings("unchecked")
        final Produit result =
            (Produit) CollectionUtils.find((Collection<Produit>) BeanTool
                                                   .getPropriete(this.getBean()
                                                                         .getDetailProduit(),
                                                                 produit.getType()
                                                                         .getCollection()),
                                           new GenericPredicate("denomination",
                                                                produit.getDenomination()));
        return result;
    }

    public boolean isVisited(final String idOnglet)
    {
        return this.ongletVisites.contains(idOnglet);
    }
    /** ************************ **/
    /** GET / SET **/
    /** ************************ **/

    /**
     * Setter pour dicoDernieresModifs.
     * @param dicoDernieresModifs le dicoDernieresModifs à écrire.
     */
    public void setDicoDernieresModifs(final DicoSuivisEssai dicoDernieresModifs)
    {
        this.dicoDernieresModifs = dicoDernieresModifs;
    }

    /**
     * Setter pour commentairesManagers.
     * @param commentairesManagers le commentairesManagers à écrire.
     */
    @SuppressWarnings("rawtypes")
    public void setCommentairesManagers(final SortedMap<String, GenericCommentaireManager> commentairesManagers)
    {
        this.commentairesManagers = commentairesManagers;
    }

    /**
     * Getter sur indexOngletCourant.
     * @return Retourne le indexOngletCourant.
     */
    public int getIndexOngletCourant()
    {
        return this.indexOngletCourant;
    }

    /**
     * Setter pour indexOngletCourant.
     * @param indexOngletCourant le indexOngletCourant à écrire.
     */
    public void setIndexOngletCourant(final int indexOngletCourant)
    {
        this.indexOngletCourant = indexOngletCourant;
    }

    /**
     * Getter sur documentsCollectionManagers.
     * @return Retourne le documentsCollectionManagers.
     */
    @SuppressWarnings("rawtypes")
    public SortedMap<String, GenericDocumentEssaiManager> getDocumentsCollectionManagers()
    {
        return this.documentsCollectionManagers;
    }

    /**
     * Setter pour documentsCollectionManagers.
     * @param documentsCollectionManagers le documentsCollectionManagers à écrire.
     */
    @SuppressWarnings("rawtypes")
    public void setDocumentsCollectionManagers(final SortedMap<String, GenericDocumentEssaiManager> documentsCollectionManagers)
    {
        this.documentsCollectionManagers = documentsCollectionManagers;
    }

    /**
     * Getter sur contactsManager.
     * @return Retourne le contactsManager.
     */
    public ContactsManager getContactsManager()
    {
        return this.contactsManager;
    }

    /**
     * Setter pour contactsManager.
     * @param contactsManager le contactsManager à écrire.
     */
    public void setContactsManager(final ContactsManager contactsManager)
    {
        this.contactsManager = contactsManager;
    }

    /**
     * Getter sur documentManagers.
     * @return Retourne le documentManagers.
     */
    @SuppressWarnings("rawtypes")
    public SortedMap<String, GenericDocumentEssaiManager> getDocumentsManagers()
    {
        return this.documentsManagers;
    }

    /**
     * Setter pour documentManagers.
     * @param documentManagers le documentManagers à écrire.
     */
    @SuppressWarnings("rawtypes")
    public void setDocumentsManagers(final SortedMap<String, GenericDocumentEssaiManager> documentManagers)
    {
        this.documentsManagers = documentManagers;
    }

    /**
     * Setter pour documentService.
     * @param documentService le documentService à écrire.
     */
    public void setDocumentService(final DocumentService documentService)
    {
        this.documentService = documentService;
    }

    /**
     * Getter pour evenementSelected.
     * @return Le evenementSelected
     */
    public Evenement getEvenementSelected()
    {
        return this.evenementSelected;
    }

    /**
     * Setter pour evenementSelected.
     * @param evenementSelected Le evenementSelected à écrire.
     */
    public void setEvenementSelected(final Evenement evenementSelected)
    {
        this.evenementSelected = evenementSelected;
    }

    /**
     * Getter pour commentaireChgtEtat.
     * @return Le commentaireChgtEtat
     */
    public String getCommentaireChgtEtat()
    {
        return this.commentaireChgtEtat;
    }

    /**
     * Setter pour commentaireChgtEtat.
     * @param commentaireChgtEtat Le commentaireChgtEtat à écrire.
     */
    public void setCommentaireChgtEtat(final String commentaireChgtEtat)
    {
        this.commentaireChgtEtat = commentaireChgtEtat;
    }

    /**
     * Getter pour etatChgtEtat.
     * @return Le etatChgtEtat
     */
    public EtatEssai getEtatChgtEtat()
    {
        return this.etatChgtEtat;
    }

    /**
     * Setter pour etatChgtEtat.
     * @param etatChgtEtat Le etatChgtEtat à écrire.
     */
    public void setEtatChgtEtat(final EtatEssai etatChgtEtat)
    {
        this.etatChgtEtat = etatChgtEtat;
    }

    /**
     * Getter pour alertes.
     * @return Le alertes
     */
    public List<Alerte> getAlertes()
    {
        return this.alertes;
    }

    /**
     * Setter pour alertes.
     * @param alertes Le alertes à écrire.
     */
    public void setAlertes(final List<Alerte> alertes)
    {
        this.alertes = alertes;
    }

    /**
     * Getter pour idOngletVisites.
     * @return Le idOngletVisites
     */
    public String getIdOngletVisites()
    {
        return this.idOngletVisites;
    }

    /**
     * Setter pour idOngletVisites.
     * @param idOngletVisites Le idOngletVisites à écrire.
     */
    public void setIdOngletVisites(final String idOngletVisites)
    {
        this.idOngletVisites = idOngletVisites;
    }

    /**
     * Getter pour ongletVisites.
     * @return Le ongletVisites
     */
    public List<String> getOngletVisites()
    {
        return this.ongletVisites;
    }

    @Override
    public void setBean(final Essai essai)
    {
        this.log.debug("[setBean] EssaiManager : "
                       + this
                       + ", Essai : "
                       + essai);
        super.setBean(essai);
    }

}
