package fr.pharma.eclipse.dictionary.maker.stock;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.stock.StockSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Artisan de recherche pour le stock.
 
 * @version $Revision$ $Date$
 */
public class StockSearchCriteriaMaker
    extends AbstractCriteriaMaker
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3729802035499523042L;

    /**
     * Constructeur par défaut.
     */
    public StockSearchCriteriaMaker()
    {
        super(StockSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit)
    {
        final StockSearchCriteria crit = (StockSearchCriteria) searchCrit;

        // Essai
        if (crit.getEssai() != null)
        {
            CriteriaMakerUtils.addCritere(criteria,
                                          "essai",
                                          crit.getEssai());
        }

        // Essais
        if (crit.getEssais() != null)
        {
            CriteriaMakerUtils.addInCritere(criteria,
                                            "essai",
                                            crit.getEssais().toArray(new Essai[crit
                                                    .getEssais()
                                                    .size()]));
        }

        // Pharmacie
        if (crit.getPharmacie() != null)
        {
            CriteriaMakerUtils.addCritere(criteria,
                                          "pharmacie",
                                          crit.getPharmacie());
        }

        // Pharmacies
        if (crit.getPharmacies() != null)
        {
            CriteriaMakerUtils.addInCritere(criteria,
                                            "pharmacie",
                                            crit.getPharmacies().toArray(new Pharmacie[crit
                                                    .getPharmacies()
                                                    .size()]));
        }

        // Numéro de lot
        if (StringUtils.isNotEmpty(crit.getNumLot()))
        {
            CriteriaMakerUtils.addSqlCritere(criteria,
                                             "numLot",
                                             crit.getNumLot());
        }

        // Critères sur produit
        this.handleCriteriaProduit(criteria,
                                   crit);

        // Critère sur date de bornage de récupération des mouvements
        this.handleCriteriaDate(criteria,
                                crit);
    }

    /**
     * Méthode en charge de gérer les critères par rapport à la date.
     * @param criteria Criteria Hibernate.
     * @param crit Critère de recherche sur Stock.
     */
    private void handleCriteriaDate(final Criteria criteria,
                                    final StockSearchCriteria crit)
    {
        final Calendar date = crit.getDate();

        // Si une date est définie
        if (date != null)
        {
            final String heuresMinutes = crit.getHeuresMinutes();

            final Calendar calendar = Calendar.getInstance(EclipseConstants.LOCALE);
            calendar.setTime(date.getTime());

            // Cas 1 : des heures minutes sont précisées
            if (StringUtils.isNotEmpty(heuresMinutes))
            {
                try
                {
                    final String[] tokens = heuresMinutes.split(EclipseConstants.COLON);
                    calendar.set(Calendar.HOUR_OF_DAY,
                                 Integer.valueOf(tokens[0]));
                    calendar.set(Calendar.MINUTE,
                                 Integer.valueOf(tokens[1]) + 1);
                    calendar.set(Calendar.SECOND,
                                 0);
                    calendar.set(Calendar.MILLISECOND,
                                 0);
                }
                catch (final ArrayIndexOutOfBoundsException e)
                {
                    this.handleExceptionHeuresMinutes(calendar);
                }
                catch (final NumberFormatException e)
                {
                    this.handleExceptionHeuresMinutes(calendar);
                }
            }
            // Cas 2 : il n'y a pas d'heures minutes de renseignées
            else
            {
                // Ajout d'un jour
                calendar.add(Calendar.DAY_OF_MONTH,
                             1);
            }
            criteria.add(Restrictions.le("dateCreation",
                                         calendar));
        }
    }

    /**
     * Méthode en charge de gérer une exception sur le traitement des heures minutes.
     * @param calendar Calendar ayant provoqué une exception.
     */
    private void handleExceptionHeuresMinutes(final Calendar calendar)
    {
        // On remet HEURE + MINUTE à 0
        calendar.set(Calendar.HOUR_OF_DAY,
                     0);
        calendar.set(Calendar.MINUTE,
                     0);
        // Ajout d'un jour (traitement classique)
        // On gère comme si heures/minutes non saisi par l'utilisateur
        calendar.add(Calendar.DAY_OF_MONTH,
                     1);
    }

    /**
     * Méthode en charge de gérer les critères par rapport au produit.
     * @param criteria Criteria Hibernate.
     * @param crit Critère de recherche sur Stock.
     */
    private void handleCriteriaProduit(final Criteria criteria,
                                       final StockSearchCriteria crit)
    {
        // Produit
        if (StringUtils.isNotEmpty(crit.getDenominationProduit())
            || crit.getStockage() != null)
        {
            final Criteria critProduit = criteria.createCriteria("produit",
                                                                 "produit");
            if (StringUtils.isNotEmpty(crit.getDenominationProduit()))
            {
                CriteriaMakerUtils.addSqlCritere(critProduit,
                                                 "{alias}.denomination",
                                                 crit.getDenominationProduit());
            }
            if (crit.getStockage() != null)
            {
                // La création des trois Criteria évite un sqlgrammarexception qu'on voit si
                // le path detailLogistique.detailsStockages.stockage est
                // dans un seul Criteria
                final Criteria critDetailLogistique =
                    critProduit.createCriteria("detailLogistique",
                                               "detailLogistique");
                final Criteria critDetailsStockages =
                    critDetailLogistique.createCriteria("detailsStockages",
                                                        "detailsStockages");
                final Criteria critStockage = critDetailsStockages.createCriteria("stockage",
                                                                                  "stockage");
                CriteriaMakerUtils.addInCritere(critStockage,
                                                "stockage.id",
                                                new Long[]
                                                {crit.getStockage().getId() });
            }
        }
    }

}
