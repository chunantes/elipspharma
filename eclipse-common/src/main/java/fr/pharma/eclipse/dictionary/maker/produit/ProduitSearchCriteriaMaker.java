package fr.pharma.eclipse.dictionary.maker.produit;

import org.hibernate.Criteria;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.produit.ProduitSearchCriteria;

/**
 * Artisan de recherche pour les produits.
 
 * @version $Revision$ $Date$
 */
public class ProduitSearchCriteriaMaker
    extends AbstractCriteriaMaker
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -5396246636387414477L;

    /**
     * Constructeur par défaut.
     */
    public ProduitSearchCriteriaMaker()
    {
        super(ProduitSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit)
    {
        final ProduitSearchCriteria crit = (ProduitSearchCriteria) searchCrit;

        // Type de produit
        if (crit.getTypeProduit() != null)
        {
            CriteriaMakerUtils.addCritere(criteria,
                                          "type",
                                          crit.getTypeProduit());
        }

        // Essai
        if (null != crit.getEssai())
        {
            CriteriaMakerUtils.addCritere(criteria,
                                          "essai",
                                          crit.getEssai());
        }

        if (null != crit.getStockage())
        {
            // Création d'un critère sur les habilitations du du détail des contacts de l'essai
            final Criteria critDetailLogistique = criteria.createCriteria("detailLogistique",
                                                                          "detailLogistique");
            final Criteria critStockages = critDetailLogistique.createCriteria("stockages",
                                                                               "stockages");

            CriteriaMakerUtils.addInCritere(critStockages,
                                            "stockage.id",
                                            new Long[]
                                            {crit.getStockage().getId() });

        }

        if (null != crit.getStockageRetour())
        {
            // Création d'un critère sur les habilitations du du détail des contacts de l'essai
            final Criteria critDetailLogistique = criteria.createCriteria("detailLogistique",
                                                                          "detailLogistique");
            final Criteria critStockages =
                critDetailLogistique.createCriteria("stockagesRetours",
                                                    "stockagesRetours");

            CriteriaMakerUtils.addInCritere(critStockages,
                                            "stockage.id",
                                            new Long[]
                                            {crit.getStockageRetour().getId() });

        }
    }
}
