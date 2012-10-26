package fr.pharma.eclipse.factory.surcout;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.apache.commons.lang.BooleanUtils;

import fr.pharma.eclipse.comparator.surcout.ItemComparator;
import fr.pharma.eclipse.domain.model.surcout.Categorie;
import fr.pharma.eclipse.domain.model.surcout.Grille;
import fr.pharma.eclipse.domain.model.surcout.GrilleModele;
import fr.pharma.eclipse.domain.model.surcout.Item;
import fr.pharma.eclipse.domain.model.surcout.Theme;
import fr.pharma.eclipse.domain.model.surcout.regle.Regle;
import fr.pharma.eclipse.factory.common.BeanObjectFactory;

/**
 * Factory de grille de surcout.
 
 * @version $Revision$ $Date$
 */
public class GrilleFactory
    extends BeanObjectFactory<Grille>
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 3012332990810060411L;

    /**
     * Factory d'item.
     */
    @Resource(name = "itemFactory")
    private BeanObjectFactory<Item> itemFactory;

    /**
     * Constructeur.
     * @param bean Classe.
     */
    public GrilleFactory(final Class<Grille> bean)
    {
        super(bean);
    }

    /**
     * Méthode en charge de construire une grille de surcout et d'intialiser ses items à partir
     * d'une grille modèle.
     * @param modele La grille modèle.
     * @return La grille de surcout.
     */
    public Grille getInitializedObject(final GrilleModele modele)
    {
        final Grille grille = super.getInitializedObject();
        grille.setGrilleModele(modele);
        for (final Theme theme : modele.getThemes())
        {
            grille.getItems().addAll(this.buildItems(grille,
                                                     theme));
        }
        return grille;
    }

    /**
     * Méthode en charge de construire la liste des items pour un thème du modèle. Il s'agit de
     * construire un item par catégorie et par thème/catégorie.
     * @param grille La grille.
     * @param theme Le thème.
     * @return La liste des items correspondants aux catégories.
     */
    private Collection<Item> buildItems(final Grille grille,
                                        final Theme theme)
    {
        final SortedSet<Item> items = new TreeSet<Item>(new ItemComparator());

        // Gestion des thèmes qui sont aussi des catégories
        if (BooleanUtils.isTrue(theme.isCategorie()))
        {
            items.add(this.buildItem(grille,
                                     theme,
                                     null));
        }

        // gestion des catégories.
        for (final Categorie categorie : theme.getCategories())
        {
            items.add(this.buildItem(grille,
                                     theme,
                                     categorie));
        }
        return items;
    }

    /**
     * Méthode en charge de constuire un item à partir d'un thème, d'une grille et d'une
     * catégorie.
     * @param theme Le thème
     * @param grille La grille
     * @param La catégorie
     * @return L'item.
     */
    private Item buildItem(final Grille grille,
                           final Theme theme,
                           final Categorie categorie)
    {
        final Item item = this.itemFactory.getInitializedObject();
        item.setTheme(theme.getNom());
        item.setGrille(grille);
        if (categorie != null)
        {
            item.setCategorie(categorie.getLibelle());
            item.setActe(categorie.getActe());
            item.getRegles().addAll(categorie.getRegles());
            for (final Regle regle : item.getRegles())
            {
                regle.getItems().add(item);
            }

        }
        // Cas d'un thème qui est aussi une catégorie.
        else if (theme.isCategorie())
        {
            item.getRegles().addAll(theme.getRegles());
            for (final Regle regle : item.getRegles())
            {
                regle.getItems().add(item);
            }
        }
        return item;
    }

    /**
     * Setter pour itemFactory.
     * @param itemFactory le itemFactory à écrire.
     */
    public void setItemFactory(final BeanObjectFactory<Item> itemFactory)
    {
        this.itemFactory = itemFactory;
    }

}
