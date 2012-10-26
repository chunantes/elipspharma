package fr.pharma.eclipse.component.produit;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import fr.pharma.eclipse.component.BeansManager;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.enums.produit.TypeProduit;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.DispositifMedical;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Preparation;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.produit.ProduitTherapeutique;
import fr.pharma.eclipse.predicate.GenericPredicate;
import fr.pharma.eclipse.utils.introspection.BeanTool;
import fr.pharma.eclipse.validator.remove.RemoveValidator;

/**
 * Manager sur les beans de gestion de Produit.
 
 * @version $Revision$ $Date$
 */
public class ProduitsManager
    extends BeansManager<Produit>
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 7759002977790495169L;

    /**
     * Validator de suppression.
     */
    @Resource(name = "produitRemoveValidator")
    private RemoveValidator<Produit> removeValidator;

    /**
     * Dictionnaire permettant de faire le lien entre le type de produit et la collection
     * correspondante dans le detailProduit.
     */
    private Map<TypeProduit, String> typeProduitDictionary;

    /**
     * Medicament seclectionné.
     */
    private Medicament medicamentSelected;

    /**
     * Dm seclectionné.
     */
    private DispositifMedical dmSelected;

    /**
     * Prod therapeutique seclectionné.
     */
    private ProduitTherapeutique produitTheraSelected;

    /**
     * Preparation seclectionné.
     */
    private Preparation preparationSelected;

    /**
     * Map contenant les informations de redirection en fonction du type de personne.
     */
    public static final Map<TypeProduit, String[]> INFOS_REDIRECT =
        new TreeMap<TypeProduit, String[]>();

    static
    {
        ProduitsManager.INFOS_REDIRECT.put(TypeProduit.MEDICAMENT,
                                           new String[]
                                           {"editMedicament", "idMedicament" });
        ProduitsManager.INFOS_REDIRECT.put(TypeProduit.DISPOSITIF_MEDICAL,
                                           new String[]
                                           {"editDispositifMedical", "idDm" });
        ProduitsManager.INFOS_REDIRECT.put(TypeProduit.PRODUIT_THERAPEUTIQUE,
                                           new String[]
                                           {"editProduitTherapeutique", "idProduitThera" });
    }

    /**
     * Type de personne sélectionné pour un ajout de produit.
     */
    private TypeProduit typeProduit;

    /**
     * Constructeur.
     * @param searchCriteria Critère de recherche.
     */
    public ProduitsManager(final SearchCriteria searchCriteria)
    {
        super(searchCriteria);
    }

    /**********************
     * Suppression
     **********************/

    /**
     * Méthode en charge de supprimer de l'essai les beans sélectionnés.
     * @param essai L'essai.
     */
    @SuppressWarnings("unchecked")
    public void remove(final Essai essai)
    {
        this.getBeans().clear();
        // Constitution de la liste globale.
        this.getBeans().addAll(essai.getDetailProduit().getDispositifsMedicaux());
        this.getBeans().addAll(essai.getDetailProduit().getMedicaments());
        this.getBeans().addAll(essai.getDetailProduit().getProduits());

        for (final Produit p : this.getBeansSelected())
        {

            this.removeValidator.validate(p);

            ((SortedSet) BeanTool.getPropriete(essai.getDetailProduit(),
                                               this.typeProduitDictionary.get(p.getType())))
                    .remove(p);
            essai.getDetailProduit().getProduits().remove(p);
        }

    }

    /**
     * Méthode en charge de dupliquer les produits sélectionnés de l'essai en paramètre.
     * @param essai L'essai.
     */
    @SuppressWarnings("all")
    public void dupliquer(final Essai essai)
    {
        this.getBeans().clear();
        // Constitution de la liste globale.
        this.getBeans().addAll(essai.getDetailProduit().getDispositifsMedicaux());
        this.getBeans().addAll(essai.getDetailProduit().getMedicaments());
        this.getBeans().addAll(essai.getDetailProduit().getProduitsTherapeutiques());
        Produit clone = null;
        for (final Produit p : this.getBeansSelected())
        {
            clone = p.cloneMe();
            clone.setDenomination(p.getDenomination().concat(" - copie"));
            ((SortedSet) BeanTool.getPropriete(essai.getDetailProduit(),
                                               this.typeProduitDictionary.get(p.getType())))
                    .add(clone);
            p.setSelected(false);
            essai.getDetailProduit().getProduits().add(clone);
        }
    }

    public void addToEssaiCollection(final Essai essai,
                                     final Produit produit)
    {
        final SortedSet<Produit> collection =
            (SortedSet) BeanTool.getPropriete(essai.getDetailProduit(),
                                              this.typeProduitDictionary.get(produit.getType()));

        if (produit.getId() == null)
        {
            collection.add(produit);
            ((SortedSet) BeanTool.getPropriete(essai.getDetailProduit(),
                                               "produits")).add(produit);
        }
        else
        {
            final Produit p =
                (Produit) CollectionUtils.find(collection,
                                               new GenericPredicate("id",
                                                                    produit.getId()));
            collection.remove(p);
            collection.add(produit);

            ((SortedSet) BeanTool.getPropriete(essai.getDetailProduit(),
                                               "produits")).remove(p);
            ((SortedSet) BeanTool.getPropriete(essai.getDetailProduit(),
                                               "produits")).add(produit);

        }
    }
    /**
     * Méthode en charge de supprimer le produit en création lors d'une annulation.
     * @param produit Le produit.
     */
    public void removeProduitOnCancel(final Produit produit)
    {
        if (produit.getId() == null)
        {
            ((SortedSet) BeanTool.getPropriete(produit.getDetailProduit(),
                                               this.typeProduitDictionary.get(produit.getType())))
                    .remove(produit);
        }
    }

    /*******************
     * Redirection
     *******************/

    /**
     * Méthode en charge de gérer la redirection vers la page d'édition d'un produit <br />
     * à partir de la sélection d'une ligne dans le tableau de résultats de Produit. <br />
     * La redirection est différente en fonction du type de la personne.
     */
    public void redirectEditProduit()
    {
        // Récupération de la personne sélectionnée
        final Produit produit = this.getBeanSelected();
        this.handleRedirect(produit.getType());
    }

    /**
     * Méthode en charge de gérer la redirection vers la page d'ajout d'un produit <br />
     * à partir de la sélection du profil d'ajout.
     */
    public void redirectAjoutProduit()
    {
        this.setBeanSelected(null);

        this.handleRedirect(this.typeProduit);
    }

    /**
     * Méthode en charge de gérer la redirection en fonction du type de produit.
     * @param type Type du produit.
     */
    public void handleRedirect(final TypeProduit type)
    {
        super.redirect(ProduitsManager.INFOS_REDIRECT.get(type)[0],
                       ProduitsManager.INFOS_REDIRECT.get(type)[1]);
    }

    /**
     * Getter sur typeProduit.
     * @return Retourne le typeProduit.
     */
    public TypeProduit getTypeProduit()
    {
        return this.typeProduit;
    }

    /**
     * Setter pour typeProduit.
     * @param typeProduit le typeProduit à écrire.
     */
    public void setTypeProduit(final TypeProduit typeProduit)
    {
        this.typeProduit = typeProduit;
    }

    /**
     * Setter pour typeProduitDictionary.
     * @param typeProduitDictionary le typeProduitDictionary à écrire.
     */
    public void setTypeProduitDictionary(final Map<TypeProduit, String> typeProduitDictionary)
    {
        this.typeProduitDictionary = typeProduitDictionary;
    }

    /**
     * Setter pour removeValidator.
     * @param removeValidator le removeValidator à écrire.
     */
    public void setRemoveValidator(final RemoveValidator<Produit> removeValidator)
    {
        this.removeValidator = removeValidator;
    }

    /**
     * Getter sur medicamentSelected.
     * @return Retourne le medicamentSelected.
     */
    public Medicament getMedicamentSelected()
    {
        return this.medicamentSelected;
    }

    /**
     * Getter sur dmSelected.
     * @return Retourne le dmSelected.
     */
    public DispositifMedical getDmSelected()
    {
        return this.dmSelected;
    }

    /**
     * Getter sur produitTheraSelected.
     * @return Retourne le produitTheraSelected.
     */
    public ProduitTherapeutique getProduitTheraSelected()
    {
        return this.produitTheraSelected;
    }

    /**
     * Setter pour medicamentSelected.
     * @param medicamentSelected le medicamentSelected à écrire.
     */
    public void setMedicamentSelected(final Medicament medicamentSelected)
    {
        this.medicamentSelected = medicamentSelected;
    }

    /**
     * Setter pour dmSelected.
     * @param dmSelected le dmSelected à écrire.
     */
    public void setDmSelected(final DispositifMedical dmSelected)
    {
        this.dmSelected = dmSelected;
    }

    /**
     * Setter pour produitTheraSelected.
     * @param produitTheraSelected le produitTheraSelected à écrire.
     */
    public void setProduitTheraSelected(final ProduitTherapeutique produitTheraSelected)
    {
        this.produitTheraSelected = produitTheraSelected;
    }

    /**
     * Getter pour preparationSelected.
     * @return Le preparationSelected
     */
    public Preparation getPreparationSelected()
    {
        return this.preparationSelected;
    }

    /**
     * Setter pour preparationSelected.
     * @param preparationSelected Le preparationSelected à écrire.
     */
    public void setPreparationSelected(final Preparation preparationSelected)
    {
        this.preparationSelected = preparationSelected;
    }

}
