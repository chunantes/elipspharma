package fr.pharma.eclipse.factory.produit;

import java.util.Map;
import java.util.SortedSet;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import fr.pharma.eclipse.domain.enums.produit.TypeProduit;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.suivi.produit.ProduitSuivi;
import fr.pharma.eclipse.factory.common.BeanObjectComplexFactory;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;
import fr.pharma.eclipse.predicate.GenericPredicate;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Factory de Bean Produit.
 
 * @version $Revision$ $Date$
 * @param <PRODUIT> Bean Objet Produit.
 */
public class ProduitFactory<PRODUIT extends Produit>
    extends BeanObjectComplexFactory<PRODUIT>
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3171922165561292902L;

    /**
     * Type du produit.
     */
    private final TypeProduit typeProduit;

    /**
     * Dictionnaire permettant de faire le lien entre le type de produit et la collection de
     * DetailProduit concernée.
     */
    private Map<TypeProduit, String> typeDictionary;

    /**
     * Factory de suivi pour produit.
     */
    @Resource(name = "produitSuiviFactory")
    private SuiviFactory<ProduitSuivi> produitSuiviFactory;

    /**
     * Constructeur.
     * @param bean Classe.
     * @param typeProduit Type du produit.
     */
    public ProduitFactory(final Class<PRODUIT> bean, final TypeProduit typeProduit)
    {
        super(bean);
        this.typeProduit = typeProduit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PRODUIT getInitializedObject()
    {
        final PRODUIT produit = super.getInitializedObject();
        final ProduitSuivi suivi = this.produitSuiviFactory.getInitializedObject();
        suivi.setProduit(produit);
        produit.getModifs().add(suivi);
        produit.setType(this.typeProduit);
        produit.setAlerteActive(Boolean.TRUE);
        produit.setDenomination("");
        return produit;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("all")
    public PRODUIT getInitializedObject(final Essai essai)
    {
        final PRODUIT p = this.getInitializedObject();
        p.setDetailProduit(essai.getDetailProduit());

        return p;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("all")
    public PRODUIT getInitializedObjectFromEssai(final Essai essai,
                                                 final Long idProduit)
    {
        final PRODUIT result =
            (PRODUIT) CollectionUtils.select(this.getCollectionByType(essai),
                                             new GenericPredicate("id",
                                                                  idProduit)).iterator().next();
        return result;
    }

    /**
     * Méthode en charge de retourner la collection correspondante au type de produit.
     * @param essai L'essai
     * @return la collection correspondante au type de produit.
     */
    @SuppressWarnings("all")
    private SortedSet<Produit> getCollectionByType(final Essai essai)
    {
        return (SortedSet<Produit>) BeanTool.getPropriete(essai.getDetailProduit(),
                                                          this.typeDictionary
                                                                  .get(this.typeProduit));
    }

    /**
     * Setter pour typeDictionary.
     * @param typeDictionary le typeDictionary à écrire.
     */
    public void setTypeDictionary(final Map<TypeProduit, String> typeDictionary)
    {
        this.typeDictionary = typeDictionary;
    }

    /**
     * Setter pour produitSuiviFactory.
     * @param produitSuiviFactory le produitSuiviFactory à écrire.
     */
    public void setProduitSuiviFactory(final SuiviFactory<ProduitSuivi> produitSuiviFactory)
    {
        this.produitSuiviFactory = produitSuiviFactory;
    }

}
