package fr.pharma.eclipse.service.stockage.impl;

import java.util.List;
import java.util.SortedSet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.stockage.StockageSearchCriteria;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.predicate.GenericPredicate;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.stockage.StockageService;

/**
 * Classe d'implémentation du service de gestion de stockage.
 
 * @version $Revision$ $Date$
 */
public class StockageServiceImpl
    extends GenericServiceImpl<Stockage>
    implements StockageService
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 6396408517433710460L;

    /**
     * Constructeur.
     * @param stockageDao Dao de gestion des stockages.
     */
    public StockageServiceImpl(final GenericDao<Stockage> stockageDao)
    {
        super(stockageDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNomComplet(final Stockage stockage)
    {
        return this.addNomStockageParents(stockage,
                                          StringUtils.EMPTY)
               + stockage.getNom();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isStockageAlreadyPresent(final Stockage stockage,
                                            final SortedSet<Stockage> stockages)
    {
        Boolean result = Boolean.FALSE;

        for (final Stockage stock : stockages)
        {
            // Même nom
            if (this.getNomComplet(stock).compareToIgnoreCase(this.getNomComplet(stockage)) == 0)
            {
                if (stock != stockage)
                {
                    result = Boolean.TRUE;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isNomStockageUtiliseParAutreStockageDeMemeNiveau(final Stockage stockage,
                                                                    final Pharmacie pharmacie)
    {
        Boolean result = Boolean.FALSE;

        final StockageSearchCriteria criteria = new StockageSearchCriteria();
        criteria.setPharmacie(pharmacie);
        criteria.setNom(stockage.getNom());
        if (stockage.getParent() != null)
        {
            criteria.setParent(stockage.getParent());
            criteria.setHasParent(Boolean.TRUE);
        }
        else
        {
            criteria.setHasParent(Boolean.FALSE);
        }

        // on recherche les stockages de même niveau (cad ayant le même parent) que le stockage.
        final List<Stockage> stockagesDeMemeNiveau = this.getAll(criteria);

        // on filtre sur le nom
        CollectionUtils.filter(stockagesDeMemeNiveau,
                               new GenericPredicate("nom",
                                                    stockage.getNom()));

        // on verifie maintenant que le nom du stockage n'est pas déja porté par un stockage de
        // même niveau.
        if (stockagesDeMemeNiveau.size() > 0)
        {
            // il y a un stockage de même niveau portant le nom. Il faut vérifier si c'est le meme
            // objet.

            // Si notre stockage n'a pas d'id, c'est qu'il n'est pas en base, donc c'est un
            // nouveau stockage. Donc le stockage récupéré n'est pas le même.
            if (stockage.getId() == null)
            {
                result = Boolean.TRUE;
            }
            else
            {
                // le stockage en base n'a pas le même id, donc ce n'est pas le même.
                if (stockagesDeMemeNiveau.get(0).getId().longValue() != stockage
                        .getId()
                        .longValue())
                {
                    result = Boolean.TRUE;
                }
            }
        }

        return result;
    }

    /**
     * Méthode en charge de concaténer le nom des parents du stockage.
     * @param stockage Stockage à analyser.
     * @param str String contenant la concaténation des noms de stockages.
     * @return Nom des parents.
     */
    private String addNomStockageParents(final Stockage stockage,
                                         final String str)
    {
        final Stockage parent = stockage.getParent();
        while (parent != null)
        {
            return this.addNomStockageParents(parent,
                                              parent.getNom().concat(str));
        }
        return str;
    }

}
