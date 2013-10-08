package fr.pharma.eclipse.component.design;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import org.primefaces.model.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.comparator.produit.detail.ProduitComparator;
import fr.pharma.eclipse.component.design.helper.TreeDesignHelper;
import fr.pharma.eclipse.component.essai.EssaiManager;
import fr.pharma.eclipse.domain.enums.TypeDesignable;
import fr.pharma.eclipse.domain.model.design.Bras;
import fr.pharma.eclipse.domain.model.design.Designable;
import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.design.DetailDesign;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.factory.common.BeanObjectFactory;
import fr.pharma.eclipse.json.DesignConverter;
import fr.pharma.eclipse.predicate.GenericPredicate;
import fr.pharma.eclipse.service.helper.design.TimeHelper;
import fr.pharma.eclipse.utils.FacesUtils;
import fr.pharma.eclipse.validator.remove.RemoveValidator;

/**
 * Manager des designs de l'essai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DesignsManager implements Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 2079751893882378708L;

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(DesignsManager.class);

    /**
     * EssaiManager.
     */
    private EssaiManager essaiManager;

    /**
     * Helper de l'arbre de design.
     */
    @Resource(name = "treeDesignHelper")
    private TreeDesignHelper treeDesignHelper;

    /**
     * Courant.
     */
    private Designable current;

    /**
     * Designable servant de base pour la gestion de l'ouverture des noeuds.
     */
    private Designable designableDisplay;

    /**
     * Object TreeNode pour les stockages.
     */
    private TreeNode root;

    /**
     * Identifiant des nodes à expanded.
     */
    private String idsNodesToExpand;

    /**
     * Date de début.
     */
    private Calendar dateDebut = Calendar.getInstance();

    /**
     * Date de fin.
     */
    private Calendar dateFin;

    /**
     * Bras.
     */
    private Bras bras;

    /**
     * Type de designable.
     */
    private TypeDesignable type;

    /**
     * Parent.
     */
    private Bras parent;

    /**
     * Bras factory.
     */
    @Resource(name = "brasFactory")
    private BeanObjectFactory<Bras> brasFactory;

    /**
     * Action.
     */
    private String actionCurrent;

    /**
     * Validateur de suppression d'un bras.
     */
    @Resource(name = "brasRemoveValidator")
    private RemoveValidator<Bras> brasRemoveValidator;

    /**
     * Validateur de suppression d'une sequence.
     */
    @Resource(name = "sequenceRemoveValidator")
    private RemoveValidator<Sequence> sequenceRemoveValidator;

    /**
     * Sequence à editer.
     */
    private String nomCompletSequence;

    /**
     * Converter de design.
     */
    @Resource(name = "designConverter")
    private DesignConverter designConverter;

    /**
     * Helper relatifs aux temps de prescriptions.
     */
    @Resource(name = "timeHelper")
    private TimeHelper timeHelper;

    /**
     * JSON généré.
     */
    private JSONArray json;

    /**
     * Date en JSON.
     */
    private JSONObject jsonDate;

    /**
     * Faces utils.
     */
    @Resource(name = "facesUtils")
    private FacesUtils facesUtils;

    /**
     * Liste de produits.
     */
    private SortedSet<Produit> produits = new TreeSet<Produit>(new ProduitComparator());

    public DesignsManager() {
        this.log.debug("Construction de l'objet DesignsManager : " + this);
    }

    /**
     * Intialiser les valeurs pour le diagramme de design.
     */
    public void initDesignChrono() {

        if (this.validateInitDesignChrono()) {
            this.json = this.designConverter.convert(this.essaiManager.getBean().getDetailDesign(), this.dateDebut);
            this.processDateFin(this.dateDebut);
            if (this.dateFin == null) {
                this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "designs.data.invalid");
            } else {
                try {
                    this.jsonDate = new JSONObject();
                    final JSONObject debut = new JSONObject();
                    debut.put("jours", this.getDateDebut().get(Calendar.DAY_OF_MONTH));
                    debut.put("mois", this.getDateDebut().get(Calendar.MONTH));
                    debut.put("annee", this.getDateDebut().get(Calendar.YEAR));
                    this.jsonDate.put("debut", debut);
                    final JSONObject fin = new JSONObject();
                    fin.put("jours", this.getDateFin().get(Calendar.DAY_OF_MONTH));
                    fin.put("mois", this.getDateFin().get(Calendar.MONTH));
                    fin.put("annee", this.getDateFin().get(Calendar.YEAR));
                    this.jsonDate.put("fin", fin);
                } catch (final JSONException e) {
                    this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "designs.data.invalid");
                }
            }
        }
    }

    /**
     * @return true si données nécessaire pour l'init existent
     */
    private boolean validateInitDesignChrono() {
        if (this.essaiManager.getBean().getDetailDesign().getBras().isEmpty()) {
            this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "designs.data.empty");
            return false;
        } else if (this.dateDebut == null) {
            this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "designs.data.invalid");
            return false;
        }

        return true;
    }

    /**
     * Méthoe en charge de calculer la date de fin de la chronologie des designs
     * en fonction de la date de début saisie par l'utilisateur.<br>
     * dateFin sera null si le calcul ne marche pas
     * @param date Date de début.
     */
    protected void processDateFin(final Calendar date) {
        final TempsPrescription fin = this.timeHelper.getDateFinForDesign(this.essaiManager.getBean().getDetailDesign());
        if (fin == null) {
            this.dateFin = null;
        } else {
            this.dateFin = this.timeHelper.convertTime(this.dateDebut, fin);
        }
    }
    /**
     * Méthode en charge d'initialiser un bras.
     * @param event Envenement.
     */
    public void initBras(final ActionEvent event) {
        this.parent = (Bras) event.getComponent().getAttributes().get("designableParent");
        this.bras = this.brasFactory.getInitializedObject();
        this.bras.setParent(this.parent);
        this.actionCurrent = "ADD";
    }

    /**
     * Méthode appelée afin d'intiialiser la séquence courante à éditer.
     * @param nomComplet de la sequence.
     * @param nomCompletParent Nom complet du parent.
     * @return la séquence.
     */
    public Sequence initSequence(final String nomComplet,
                                 final String nomCompletParent) {
        this.actionCurrent = "EDIT";
        this.nomCompletSequence = nomComplet;
        final Bras bras = this.findBras(nomCompletParent);
        final Sequence sequence = (Sequence) CollectionUtils.find(bras.getSequences(), new GenericPredicate("nomComplet", nomComplet));
        return sequence;

    }

    /**
     * Méthode appelée pour récupérer l'objet bras dans le detaildesign de
     * l'essai à partir de son nom complet.
     * @param nomComplet Nom complet.
     * @return Le bras.
     */
    public Bras findBras(final String nomComplet) {
        return (Bras) CollectionUtils.find(this.essaiManager.getBean().getDetailDesign().getBras(), new GenericPredicate("nomComplet", nomComplet));
    }
    /**
     * Méthode en charged e réinitialiser les informations du manager.
     */
    public void init() {
        this.log.debug("[init] " + this);
        this.root = this.buildRoot();
        this.initProduits();
        this.setBras(null);
        this.setType(null);
        this.actionCurrent = "";
        this.nomCompletSequence = null;
        this.dateFin = null;
        this.json = null;
        this.jsonDate = null;
    }

    /**
     * Méthode en charge d'initialiser la liste des produits.
     */
    public void initProduits() {
        this.produits.clear();
        final Essai essai = this.essaiManager.getBean();
        this.produits.addAll(essai.getDetailProduit().getProduits());
    }
    /**
     * Méthode en charge d'éditer un bras.
     * @param event Evénement.
     */
    public void editBras(final ActionEvent event) {
        final Bras b = (Bras) event.getComponent().getAttributes().get("brasCurrent");
        final DetailDesign detail = this.essaiManager.getBean().getDetailDesign();
        final Bras p = (Bras) CollectionUtils.find(detail.getBras(), new GenericPredicate("nomComplet", b.getNomComplet()));
        this.setBras(p);
        this.actionCurrent = "EDIT";
        this.setType(TypeDesignable.BRAS);
    }

    /**
     * Méthode en charge de mettre à jour la liste des designs.
     */
    public void updateDesigns() {
        this.log.debug("[updateDesigns] " + this);

        final DetailDesign detail = this.essaiManager.getBean().getDetailDesign();
        if ("ADD".equals(this.actionCurrent)) {
            this.bras.setType(TypeDesignable.BRAS);
            if (this.bras.getParent() == null) {
                detail.getBras().add(this.bras);
                this.bras.setDetailDesign(detail);
            } else {
                this.designableDisplay = this.bras;
                final Bras designableParent = this.getParent();
                final Bras p = (Bras) CollectionUtils.find(detail.getBras(), new GenericPredicate("nomComplet", designableParent.getNomComplet()));
                new ArrayList<Bras>(detail.getBras());
                p.getSousBras().add(this.bras);
                new ArrayList<Bras>(p.getSousBras());
                detail.getBras().add(this.bras);
                this.bras.setParent(p);
                this.bras.setDetailDesign(detail);

            }

        }
        this.buildRoot();
        this.setType(null);
    }
    /**
     * Méthode en charge de supprimer une séquence.
     */
    public void removeSequence() {
        this.log.debug("[removeSequence] " + this);

        final Sequence sequence =
            (Sequence) CollectionUtils.find(this.findBras(this.getCurrent().getParent().getNomComplet()).getSequences(), new GenericPredicate("nomComplet", this.getCurrent()
                    .getNomComplet()));
        this.sequenceRemoveValidator.validate(sequence);
        final Bras p = this.findBras(sequence.getParent().getNomComplet());
        p.getSequences().remove(sequence);
        this.buildRoot();
    }
    /**
     * Méthode en charge de supprimer un bras.
     */
    public void removeBras() {
        this.log.debug("[removeBras] " + this);

        final Bras b = this.findBras(this.getCurrent().getNomComplet());

        if (b != null) {
            this.brasRemoveValidator.validate(b);
            this.essaiManager.getBean().getDetailDesign().getBras().remove(b);
            this.removeBras(b);
            if (b.getParent() != null) {
                this.findBras(b.getParent().getNomComplet()).getSousBras().remove(b);

            }
        }

        this.buildRoot();
    }
    /**
     * Méthode en charge d'ajouter la sequence à son parent.
     * @param sequence La séquence.
     */
    public void addSequence(final Sequence sequence) {
        this.log.debug("[addSequence] " + this);

        final Bras bras = this.findBras(sequence.getParent().getNomComplet());
        if (this.actionCurrent.equals("EDIT")) {
            bras.getSequences().remove(CollectionUtils.find(bras.getSequences(), new GenericPredicate("nomComplet", this.nomCompletSequence)));
        }

        bras.getSequences().add(sequence);
        this.setDesignableDisplay(sequence);
        this.buildRoot();
    }

    /**
     * Méthode récursive supprimant le bras l'intégralité de ses décendant
     * (bras) de la liste passée en paramètre.
     * @param b Le bras.
     */
    private void removeBras(final Bras b) {
        for (final Bras sb : b.getSousBras()) {
            this.removeBras(sb);
            this.essaiManager.getBean().getDetailDesign().getBras().remove(sb);
        }
    }

    /**
     * Getter pour root.
     * @return Le root
     */
    public TreeNode buildRoot() {
        this.log.debug("[buildRoot] " + this + ", EssaiManager : " + this.essaiManager + ", Essai : " + this.essaiManager.getBean());

        // Construction des données de l'arbre
        this.root = this.treeDesignHelper.buildTree(this.essaiManager.getBean());

        // Construction de la liste des noeuds à expanded pour l'affichage
        if (this.designableDisplay != null) {
            this.idsNodesToExpand = this.treeDesignHelper.calculateNodesToExpand(this.root, this.designableDisplay);
        } else {
            this.idsNodesToExpand = null;
        }

        return this.root;
    }
    /**
     * Méthode appelée par l'IHM lors de la sélection d'une date sur le
     * calendar.
     * @param event Evenemtn JSF.
     */
    public void selectDateListener(final SelectEvent event) {
        // Récupération de la date
        final Calendar cal = (Calendar) event.getObject();
        this.setDateDebut(cal);
    }

    /**
     * Méthode appelée via la couche IHM lorsqu'un type de design est
     * sélectionné.
     * @param event Event.
     */
    public void handleSelectType(final AjaxBehaviorEvent event) {
        final HtmlSelectOneMenu select = (HtmlSelectOneMenu) event.getSource();
        final TypeDesignable d = (TypeDesignable) select.getLocalValue();
        this.setType(d);
        this.setBras(this.getBrasFactory().getInitializedObject());
    }

    /**
     * Getter sur root.
     * @return Retourne le root.
     */
    public TreeNode getRoot() {
        return this.root;
    }

    /**
     * Setter pour root.
     * @param root le root à écrire.
     */
    public void setRoot(final TreeNode root) {
        this.root = root;
    }

    /**
     * Getter sur idsNodesToExpand.
     * @return Retourne le idsNodesToExpand.
     */
    public String getIdsNodesToExpand() {
        return this.idsNodesToExpand;
    }

    /**
     * Setter pour idsNodesToExpand.
     * @param idsNodesToExpand le idsNodesToExpand à écrire.
     */
    public void setIdsNodesToExpand(final String idsNodesToExpand) {
        //
    }

    /**
     * Setter pour treeDesignHelper.
     * @param treeDesignHelper le treeDesignHelper à écrire.
     */
    public void setTreeDesignHelper(final TreeDesignHelper treeDesignHelper) {
        this.treeDesignHelper = treeDesignHelper;
    }

    /**
     * Setter pour essaiManager.
     * @param essaiManager le essaiManager à écrire.
     */
    public void setEssaiManager(final EssaiManager essaiManager) {
        this.log.debug("[setEssaiManager] " + essaiManager);
        this.essaiManager = essaiManager;
    }

    /**
     * Getter sur designableDisplay.
     * @return Retourne le designableDisplay.
     */
    public Designable getDesignableDisplay() {
        return this.designableDisplay;
    }

    /**
     * Setter pour designableDisplay.
     * @param designableDisplay le designableDisplay à écrire.
     */
    public void setDesignableDisplay(final Designable designableDisplay) {
        this.designableDisplay = designableDisplay;
    }

    /**
     * Getter sur brasFactory.
     * @return Retourne le brasFactory.
     */
    public BeanObjectFactory<Bras> getBrasFactory() {
        return this.brasFactory;
    }

    /**
     * Setter pour brasFactory.
     * @param brasFactory le brasFactory à écrire.
     */
    public void setBrasFactory(final BeanObjectFactory<Bras> brasFactory) {
        this.brasFactory = brasFactory;
    }

    /**
     * Getter sur bras.
     * @return Retourne le bras.
     */
    public Bras getBras() {
        return this.bras;
    }

    /**
     * Setter pour bras.
     * @param bras le bras à écrire.
     */
    public void setBras(final Bras bras) {
        this.bras = bras;
    }

    /**
     * Getter sur type.
     * @return Retourne le type.
     */
    public TypeDesignable getType() {
        return this.type;
    }

    /**
     * Setter pour type.
     * @param type le type à écrire.
     */
    public void setType(final TypeDesignable type) {
        this.type = type;
    }

    /**
     * Getter sur actionCurrent.
     * @return Retourne le actionCurrent.
     */
    public String getActionCurrent() {
        return this.actionCurrent;
    }

    /**
     * Setter pour actionCurrent.
     * @param actionCurrent le actionCurrent à écrire.
     */
    public void setActionCurrent(final String actionCurrent) {
        this.actionCurrent = actionCurrent;
    }

    /**
     * Getter sur parent.
     * @return Retourne le parent.
     */
    public Bras getParent() {
        return this.parent;
    }

    /**
     * Setter pour parent.
     * @param parent le parent à écrire.
     */
    public void setParent(final Bras parent) {
        this.parent = parent;
    }

    /**
     * Setter pour brasRemoveValidator.
     * @param brasRemoveValidator le brasRemoveValidator à écrire.
     */
    public void setBrasRemoveValidator(final RemoveValidator<Bras> brasRemoveValidator) {
        this.brasRemoveValidator = brasRemoveValidator;
    }

    /**
     * Getter sur current.
     * @return Retourne le current.
     */
    public Designable getCurrent() {
        return this.current;
    }

    /**
     * Setter pour current.
     * @param current le current à écrire.
     */
    public void setCurrent(final Designable current) {
        this.actionCurrent = "EDIT";
        this.current = current;
    }

    /**
     * Setter pour sequenceRemoveValidator.
     * @param sequenceRemoveValidator le sequenceRemoveValidator à écrire.
     */
    public void setSequenceRemoveValidator(final RemoveValidator<Sequence> sequenceRemoveValidator) {
        this.sequenceRemoveValidator = sequenceRemoveValidator;
    }

    /**
     * Getter sur produits.
     * @return Retourne le produits.
     */
    public SortedSet<Produit> getProduits() {
        return this.produits;
    }

    /**
     * Setter pour produits.
     * @param produits le produits à écrire.
     */
    public void setProduits(final SortedSet<Produit> produits) {
        this.produits = produits;
    }

    /**
     * Getter sur dateDebut.
     * @return Retourne le dateDebut.
     */
    public Calendar getDateDebut() {
        return this.dateDebut;
    }

    /**
     * Setter pour dateDebut.
     * @param dateDebut le dateDebut à écrire.
     */
    public void setDateDebut(final Calendar dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Setter pour designConverter.
     * @param designConverter le designConverter à écrire.
     */
    public void setDesignConverter(final DesignConverter designConverter) {
        this.designConverter = designConverter;
    }

    /**
     * Getter sur json.
     * @return Retourne le json.
     */
    public JSONArray getJson() {
        return this.json;
    }

    /**
     * Setter pour json.
     * @param json le json à écrire.
     */
    public void setJson(final JSONArray json) {
        this.json = json;
    }

    /**
     * Setter pour timeHelper.
     * @param timeHelper le timeHelper à écrire.
     */
    public void setTimeHelper(final TimeHelper timeHelper) {
        this.timeHelper = timeHelper;
    }

    /**
     * Getter sur dateFin.
     * @return Retourne le dateFin.
     */
    public Calendar getDateFin() {
        return this.dateFin;
    }

    /**
     * Setter pour dateFin.
     * @param dateFin le dateFin à écrire.
     */
    public void setDateFin(final Calendar dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Getter sur jsonDate.
     * @return Retourne le jsonDate.
     */
    public JSONObject getJsonDate() {
        return this.jsonDate;
    }

    /**
     * Setter pour jsonDate.
     * @param jsonDate le jsonDate à écrire.
     */
    public void setJsonDate(final JSONObject jsonDate) {
        this.jsonDate = jsonDate;
    }

    public void setFacesUtils(final FacesUtils facesUtils) {
        this.facesUtils = facesUtils;
    }
    public String getNomCompletSequence() {
        return this.nomCompletSequence;
    }
    public void setNomCompletSequence(final String nomCompletSequence) {
        this.nomCompletSequence = nomCompletSequence;
    }

}
