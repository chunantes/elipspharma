package fr.pharma.eclipse.domain.enums.stock;

import java.util.ArrayList;
import java.util.List;

/**
 * Enumération représentant le type de mouvement de stock.
 
 * @version $Revision$ $Date$
 */
public enum TypeMvtStock
{
    /**
     * Approvisionnement.
     */
    APPROVISIONNEMENT("Approvisionnement", CategorieMouvement.ENTREE),

    /**
     * Entrée corrective : Mouvement d'entrée permettant de corriger des erreurs de stocks. Il n'y
     * a pas de réception de produits, par conséquent la facturation et le calcul d'indicateurs ne
     * sont pas impactés.
     */
    ENTREE_CORRECTIVE("Entrée Corrective", CategorieMouvement.ENTREE),

    /**
     * Cession PUI.
     */
    CESSION_PUI("Cession PUI", CategorieMouvement.SORTIE),

    /**
     * Dispensation.
     */
    DISPENSATION("Dispensation", CategorieMouvement.DISPENSATION),

    /**
     * Destruction.
     */
    DESTRUCTION("Destruction", CategorieMouvement.SORTIE),

    /**
     * Dotation.
     */
    DOTATION("Dotation", CategorieMouvement.DOTATION),

    /**
     * Retour promoteur.
     */
    RETOUR_PROMOTEUR("Retour Promoteur", CategorieMouvement.SORTIE),

    /**
     * Retour promoteur.
     */
    PREPARATION_SORTIE("Sortie pour préparation", CategorieMouvement.SORTIE),

    /**
     * Retour promoteur.
     */
    PREPARATION_ENTREE("Entrée de préparation", CategorieMouvement.ENTREE),

    /**
     * Autre sortie.
     */
    AUTRE_SORTIE("Autre Sortie", CategorieMouvement.SORTIE);

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Catégorie de mouvement.
     */
    private CategorieMouvement categorie;

    /**
     * Constructeur.
     * @param libelle Le libellé du type de mouvement.
     * @param categorie La catégorie du type de mouvement.
     */
    TypeMvtStock(final String libelle, final CategorieMouvement categorie)
    {
        this.libelle = libelle;
        this.categorie = categorie;
    }

    /**
     * Getter pour libelle.
     * @return Retourne le libelle.
     */
    public String getLibelle()
    {
        return this.libelle;
    }

    /**
     * Getter pour categorie.
     * @return Le categorie
     */
    public CategorieMouvement getCategorie()
    {
        return this.categorie;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return this.getLibelle();
    }

    /**
     * Méthode en charge de retourner les types de mouvements de stock de type sortie.
     * @return Type de mouvements de catégorie sortie.
     */
    public static TypeMvtStock[] valuesSortie()
    {
        final List<TypeMvtStock> result = new ArrayList<TypeMvtStock>();
        final TypeMvtStock[] all = TypeMvtStock.values();
        for (final TypeMvtStock typeMvtStock : all)
        {
            if (CategorieMouvement.SORTIE.equals(typeMvtStock.getCategorie()))
            {
                result.add(typeMvtStock);
            }
        }
        return result.toArray(new TypeMvtStock[result.size()]);
    }

    /**
     * Méthode en charge de retourner les types de mouvements de stock de type sortie sauf le type
     * Préparation.
     * @return Type de mouvements de catégorie sortie.
     */
    public static TypeMvtStock[] valuesSortieSaufPreparation()
    {
        final List<TypeMvtStock> result = new ArrayList<TypeMvtStock>();
        final TypeMvtStock[] all = TypeMvtStock.values();
        for (final TypeMvtStock typeMvtStock : all)
        {
            if (CategorieMouvement.SORTIE.equals(typeMvtStock.getCategorie())
                && !TypeMvtStock.PREPARATION_SORTIE.equals(typeMvtStock))
            {
                result.add(typeMvtStock);
            }
        }
        return result.toArray(new TypeMvtStock[result.size()]);
    }

    /**
     * Méthode en charge de retourner les types de mouvements de stock de type sortie (tout ce qui
     * n'est pas entrée : sortie + dispensation).
     * @return Type de mouvements de catégorie sortie.
     */
    public static TypeMvtStock[] valuesAllSortie()
    {
        final List<TypeMvtStock> result = new ArrayList<TypeMvtStock>();
        final TypeMvtStock[] all = TypeMvtStock.values();
        for (final TypeMvtStock typeMvtStock : all)
        {
            if (!CategorieMouvement.ENTREE.equals(typeMvtStock.getCategorie()))
            {
                result.add(typeMvtStock);
            }
        }
        return result.toArray(new TypeMvtStock[result.size()]);
    }

    /**
     * Méthode en charge de retourner les types de mouvements de stock de type entrée.
     * @return Type de mouvements de catégorie entree.
     */
    public static TypeMvtStock[] valuesEntree()
    {
        final List<TypeMvtStock> result = new ArrayList<TypeMvtStock>();
        final TypeMvtStock[] all = TypeMvtStock.values();
        for (final TypeMvtStock typeMvtStock : all)
        {
            if (CategorieMouvement.ENTREE.equals(typeMvtStock.getCategorie()))
            {
                result.add(typeMvtStock);
            }
        }
        return result.toArray(new TypeMvtStock[result.size()]);
    }

}
