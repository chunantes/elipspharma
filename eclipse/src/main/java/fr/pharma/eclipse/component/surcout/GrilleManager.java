package fr.pharma.eclipse.component.surcout;

import java.util.Calendar;
import java.util.Collection;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.collections.CollectionUtils;

import fr.pharma.eclipse.component.BeanManager;
import fr.pharma.eclipse.component.essai.EssaiManager;
import fr.pharma.eclipse.domain.model.essai.detail.surcout.DetailSurcout;
import fr.pharma.eclipse.domain.model.surcout.Grille;
import fr.pharma.eclipse.domain.model.surcout.GrilleModele;
import fr.pharma.eclipse.domain.model.surcout.Item;
import fr.pharma.eclipse.domain.model.surcout.Resultat;
import fr.pharma.eclipse.factory.surcout.GrilleFactory;
import fr.pharma.eclipse.predicate.GenericPredicate;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.surcout.GrilleModeleService;

/**
 * Manager de l'onglet surcout.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GrilleManager extends BeanManager<Grille> {
    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -5213425305576114590L;

    /**
     * Factory de grille.
     */
    @Resource(name = "grilleFactory")
    private GrilleFactory grilleFactory;

    /**
     * Manager d'essai.
     */
    @Resource(name = "essaiManager")
    private EssaiManager essaiManager;

    /**
     * Service grilleModel.
     */
    @Resource(name = "grilleModeleService")
    private GrilleModeleService grilleModeleService;

    /**
     * Grille modèle sélectionné.
     */
    private GrilleModele grilleModeleSelected;

    /**
     * Date de début.
     */
    private Calendar dateDebut;

    /**
     * Date de fin.
     */
    private Calendar dateFin;

    /**
     * Resultat de la generation de la grille.
     */
    private Map<Item, Resultat> resultPrevisionnel;

    /**
     * Resultat de la generation de la grille.
     */
    private Map<Item, Resultat> resultReel;

    /**
     * Constructeur.
     * @param service Service.
     */
    public GrilleManager(final GenericService<Grille> service) {
        super(service);
    }

    /**
     * Méthode en charge d'initialiser les valeurs du manager.
     */
    public void init() {
        final Grille grille = this.getBean();
        if ((grille != null) && (grille.getId() != null)) {
            this.setBean(grille);
            this.setGrilleModeleSelected(grille.getGrilleModele());
        } else {
            this.setBean(null);
            this.setGrilleModeleSelected(null);
        }
    }

    /**
     * Méthode en charge de charger la grille en fonction du model sélectionné.
     * @param event Evenement.
     */
    public void handleSelectGrilleModele(final AjaxBehaviorEvent event) {
        final HtmlSelectOneMenu select = (HtmlSelectOneMenu) event.getSource();
        final GrilleModele model = (GrilleModele) select.getLocalValue();
        if (model != null) {
            this.setBean(this.grilleFactory.getInitializedObject(model));
            final DetailSurcout detailSurcout = this.essaiManager.getBean().getDetailSurcout();
            this.getBean().setDetailSurcout(detailSurcout);
            this.getService().save(this.getBean());
        } else {
            this.setBean(null);
        }
    }

    /**
     * Méthode en charge de supprimer la grille courante.
     */
    public void removeGrille() {
        final Grille grille = this.getBean();
        this.setBean(null);
        this.getService().remove(grille);
        this.init();
    }

    /**
     * Retourne les items pour le theme en paramètre.
     * @param theme Le thème .
     * @return La collection des items du theme en paramètre.
     */
    @SuppressWarnings("unchecked")
    public Collection<Item> findItemForTheme(final String theme) {
        return CollectionUtils.select(this.getBean().getItems(), new GenericPredicate("theme", theme));
    }

    /**
     * Méthode en charge de retourner la liste des thèmes présents dans la
     * grille.
     * @return la liste des thèmes présents dans la grille.
     */
    public SortedSet<String> findThemes() {
        if (this.getBean() == null) {
            return null;
        }
        final SortedSet<String> themes = new TreeSet<String>();
        for (final Item item : this.getBean().getItems()) {
            themes.add(item.getTheme());
        }
        return themes;
    }

    /**
     * Retourne la liste des grilles modèles.
     * @return La liste des grilles modèles.
     */
    public Collection<GrilleModele> getGrillesModeles() {
        return this.grilleModeleService.getAll();
    }

    /**
     * Setter pour grilleFactory.
     * @param grilleFactory le grilleFactory à écrire.
     */
    public void setGrilleFactory(final GrilleFactory grilleFactory) {
        this.grilleFactory = grilleFactory;
    }

    /**
     * Setter pour grilleModeleService.
     * @param grilleModeleService le grilleModeleService à écrire.
     */
    public void setGrilleModeleService(final GrilleModeleService grilleModeleService) {
        this.grilleModeleService = grilleModeleService;
    }

    /**
     * Getter sur grilleModeleSelected.
     * @return Retourne le grilleModeleSelected.
     */
    public GrilleModele getGrilleModeleSelected() {
        return this.grilleModeleSelected;
    }

    /**
     * Setter pour grilleModeleSelected.
     * @param grilleModeleSelected le grilleModeleSelected à écrire.
     */
    public void setGrilleModeleSelected(final GrilleModele grilleModeleSelected) {
        this.grilleModeleSelected = grilleModeleSelected;
    }

    /**
     * Setter pour essaiManager.
     * @param essaiManager le essaiManager à écrire.
     */
    public void setEssaiManager(final EssaiManager essaiManager) {
        this.essaiManager = essaiManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Grille getBean() {
        return this.essaiManager.getBean().getDetailSurcout().getGrille();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBean(final Grille grille) {
        this.essaiManager.getBean().getDetailSurcout().setGrille(grille);
    }

    /**
     * Getter sur dateDebut.
     * @return Retourne le dateDebut.
     */
    public Calendar getDateDebut() {
        return this.dateDebut;
    }

    /**
     * Getter sur dateFin.
     * @return Retourne le dateFin.
     */
    public Calendar getDateFin() {
        return this.dateFin;
    }

    /**
     * Setter pour dateDebut.
     * @param dateDebut le dateDebut à écrire.
     */
    public void setDateDebut(final Calendar dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Setter pour dateFin.
     * @param dateFin le dateFin à écrire.
     */
    public void setDateFin(final Calendar dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Getter sur resultPrevisionnel.
     * @return Retourne le resultPrevisionnel.
     */
    public Map<Item, Resultat> getResultPrevisionnel() {
        return this.resultPrevisionnel;
    }

    /**
     * Getter sur resultReel.
     * @return Retourne le resultReel.
     */
    public Map<Item, Resultat> getResultReel() {
        return this.resultReel;
    }

    /**
     * Setter pour resultPrevisionnel.
     * @param resultPrevisionnel le resultPrevisionnel à écrire.
     */
    public void setResultPrevisionnel(final Map<Item, Resultat> resultPrevisionnel) {
        this.resultPrevisionnel = resultPrevisionnel;
    }

    /**
     * Setter pour resultReel.
     * @param resultReel le resultReel à écrire.
     */
    public void setResultReel(final Map<Item, Resultat> resultReel) {
        this.resultReel = resultReel;
    }

}
