package fr.pharma.eclipse.dictionary.maker.dotation;

import java.util.Calendar;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.dotation.DotationSearchCriteria;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Artisan de recherche pour les dotations.
 
 * @version $Revision$ $Date$
 */
public class DotationSearchCriteriaMaker
    extends AbstractCriteriaMaker
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -2250846651261257044L;

    /**
     * Constructeur par défaut.
     */
    public DotationSearchCriteriaMaker()
    {
        super(DotationSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit)
    {
        final DotationSearchCriteria crit = (DotationSearchCriteria) searchCrit;

        // Essai
        if (crit.getEssai() != null)
        {
            CriteriaMakerUtils.addCritere(criteria,
                                          "essai",
                                          crit.getEssai());
        }

        // Pharmacie
        if (crit.getPharmacie() != null)
        {
            CriteriaMakerUtils.addCritere(criteria,
                                          "pharmacie",
                                          crit.getPharmacie());
        }

        // Service
        if (crit.getService() != null)
        {
            CriteriaMakerUtils.addCritere(criteria,
                                          "service",
                                          crit.getService());
        }

        // Produit
        if (crit.getProduit() != null)
        {
            CriteriaMakerUtils.addCritere(criteria,
                                          "produit",
                                          crit.getProduit());
        }

        // Traitee
        if (crit.getTraitee() != null)
        {
            CriteriaMakerUtils.addCritere(criteria,
                                          "traitee",
                                          crit.getTraitee());
        }

        // Date de début
        if (crit.getDateDebut() != null)
        {
            criteria.add(Restrictions.ge("dateDemande",
                                         crit.getDateDebut()));
        }

        // Date de fin
        if (crit.getDateFin() != null)
        {
            final Calendar fin = Calendar.getInstance(EclipseConstants.LOCALE);
            fin.setTime(crit.getDateFin().getTime());
            // Ajout d'un jour
            fin.add(Calendar.DAY_OF_MONTH,
                    1);
            criteria.add(Restrictions.le("dateDemande",
                                         fin));
        }
    }

}
