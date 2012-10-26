package fr.pharma.eclipse.component.surcout;

import java.util.SortedSet;

import javax.annotation.Resource;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.collections.CollectionUtils;

import fr.pharma.eclipse.component.BeanManager;
import fr.pharma.eclipse.component.surcout.validator.RegleValidator;
import fr.pharma.eclipse.domain.model.surcout.Categorie;
import fr.pharma.eclipse.domain.model.surcout.GrilleModele;
import fr.pharma.eclipse.domain.model.surcout.Theme;
import fr.pharma.eclipse.domain.model.surcout.regle.Regle;
import fr.pharma.eclipse.factory.common.BeanObjectFactory;
import fr.pharma.eclipse.predicate.GenericPredicate;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Description de la classe.
 
 * @version $Revision$ $Date$
 */
public class GrilleModeleManager
    extends BeanManager<GrilleModele>
{

    /**
     * .SerialVersionUID.
     */
    private static final long serialVersionUID = -7920649122442884366L;

    /**
     * Theme.
     */
    private Theme theme;

    /**
     * Catégorie.
     */
    private Categorie categorie;

    /**
     * Factory de thème.
     */
    @Resource(name = "themeFactory")
    private BeanObjectFactory<Theme> themeFactory;

    /**
     * Factory de catégorie.
     */
    @Resource(name = "categorieFactory")
    private BeanObjectFactory<Categorie> categorieFactory;

    /**
     * Validateur de regle.
     */
    @Resource(name = "regleValidator")
    private RegleValidator regleValidator;

    /**
     * Regle.
     */
    private Regle regle;

    /**
     * Editable.
     */
    private boolean editable;

    /**
     * Constructeur.
     * @param service Service.
     */
    public GrilleModeleManager(final GenericService<GrilleModele> service)
    {
        super(service);
    }

    /**
     * Méthode en charge d'initialiser un thème.
     * @param event Envenement.
     */
    public void initTheme(final ActionEvent event)
    {
        final Theme t = (Theme) event.getComponent().getAttributes().get("theme");
        if (t == null)
        {
            this.theme = this.themeFactory.getInitializedObject();
            this.theme.setGrilleModele(this.getBean());
        }
        else
        {
            this.setTheme(t);
        }
    }

    /**
     * Calcul si la grille est éditable.
     */
    public void processEditable()
    {
        this.reattach();
        this.editable = this.getBean().getId() == null
                        || this.getBean().getGrilles().isEmpty();
    }

    /**
     * Méthode en charge de mettre à jour et d'ajouter le thème en cours.
     */
    public void addTheme()
    {
        final SortedSet<Theme> themes = this.getBean().getThemes();
        if (!themes.contains(this.theme))
        {
            themes.add(this.theme);
        }
        this.theme = null;
    }

    /**
     * Méthode en charge d'initialiser une catégorie.
     * @param event Envenement.
     */
    public void initCategorie(final ActionEvent event)
    {
        final Categorie c = (Categorie) event.getComponent().getAttributes().get("categorie");
        final Theme theme = (Theme) event.getComponent().getAttributes().get("theme");
        if (c == null)
        {
            this.categorie = this.categorieFactory.getInitializedObject();
            this.categorie.setTheme(theme);
            this.setTheme(theme);
        }
        else
        {
            this.setCategorie(c);
            this.setTheme(theme);
        }
    }

    /**
     * Méthode en charge de mettre à jour ou ajouter la catégorie en cours.
     */
    public void addCategorie()
    {
        final SortedSet<Categorie> categories = this.theme.getCategories();
        if (!categories.contains(this.categorie))
        {
            categories.add(this.categorie);
        }
        this.theme = null;
        this.categorie = null;
    }

    /**
     * Méthode en charge de supprimer une catégorie.
     * @param event L'évènement.
     */
    public void removeCategorie(final ActionEvent event)
    {
        final Categorie c = (Categorie) event.getComponent().getAttributes().get("categorie");
        c.getTheme().getCategories().remove(c);
    }

    /**
     * Méthode en charge de supprimer un thème.
     * @param event L'évènement.
     */
    public void removeTheme(final ActionEvent event)
    {
        final Theme t = (Theme) event.getComponent().getAttributes().get("theme");
        this.getBean().getThemes().remove(t);
    }

    /**
     * Méthode en charge d'initialiser une règle.
     * @param categorie La catégorie.
     */
    public void initRegle(final Categorie categorie)
    {
        this.regle = new Regle();
        this.regle.setCategorie(categorie);
    }

    /**
     * Listener vide (obligatoire car sinon le tag <f:ajax ..> ne fonctionne pas.
     * @param event Evenement.
     */
    public void nothing(final AjaxBehaviorEvent event)
    {
        event.getComponent();
    }

    /**
     * Méthode en charge d'ajouter une regle.
     */
    public void addRegle()
    {
        // Validation du stockage
        final boolean valid = this.regleValidator.validate(this.regle);
        if (valid)
        {
            final SortedSet<Categorie> categories =
                ((Theme) CollectionUtils.find(this.getBean().getThemes(),
                                              new GenericPredicate("libelle",
                                                                   this.getRegle()
                                                                           .getCategorie()
                                                                           .getTheme()
                                                                           .getLibelle())))
                        .getCategories();
            final Categorie c =
                (Categorie) CollectionUtils.find(categories,
                                                 new GenericPredicate("libelle",
                                                                      this.getRegle()
                                                                              .getCategorie()
                                                                              .getLibelle()));
            this.getRegle().setCategorie(c);
            c.getRegles().add(this.getRegle());
            this.getService().save(this.getBean());
        }
    }
    /**
     * Retourne <true> si la grille courante est éditable.
     * @return <true> si la grille courante est éditable.
     */
    public boolean isEditable()
    {
        return this.editable;
    }

    /**
     * Méthode en charge de supprimer une règle.
     * @param r La règle à supprimer.
     */
    public void removeRegle(final Regle r)
    {
        r.getCategorie().getRegles().remove(r);
    }

    /**
     * Setter pour themeFactory.
     * @param themeFactory le themeFactory à écrire.
     */
    public void setThemeFactory(final BeanObjectFactory<Theme> themeFactory)
    {
        this.themeFactory = themeFactory;
    }

    /**
     * Setter pour categorieFactory.
     * @param categorieFactory le categorieFactory à écrire.
     */
    public void setCategorieFactory(final BeanObjectFactory<Categorie> categorieFactory)
    {
        this.categorieFactory = categorieFactory;
    }

    /**
     * Getter sur theme.
     * @return Retourne le theme.
     */
    public Theme getTheme()
    {
        return this.theme;
    }

    /**
     * Setter pour theme.
     * @param theme le theme à écrire.
     */
    public void setTheme(final Theme theme)
    {
        this.theme = theme;
    }

    /**
     * Getter sur categorie.
     * @return Retourne le categorie.
     */
    public Categorie getCategorie()
    {
        return this.categorie;
    }

    /**
     * Setter pour categorie.
     * @param categorie le categorie à écrire.
     */
    public void setCategorie(final Categorie categorie)
    {
        this.categorie = categorie;
    }

    /**
     * Getter sur regle.
     * @return Retourne le regle.
     */
    public Regle getRegle()
    {
        return this.regle;
    }

    /**
     * Setter pour regle.
     * @param regle le regle à écrire.
     */
    public void setRegle(final Regle regle)
    {
        this.regle = regle;
    }

    /**
     * Setter pour regleValidator.
     * @param regleValidator le regleValidator à écrire.
     */
    public void setRegleValidator(final RegleValidator regleValidator)
    {
        this.regleValidator = regleValidator;
    }

    /**
     * Setter pour editable.
     * @param editable le editable à écrire.
     */
    public void setEditable(final boolean editable)
    {
        this.editable = editable;
    }
}
