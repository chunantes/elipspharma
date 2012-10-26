package fr.pharma.eclipse.dictionary.maker.stockage;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.stockage.PharmacieSearchCriteria;

/**
 * Artisan de recherche pour les pharmacies.
 
 * @version $Revision$ $Date$
 */
public class PharmacieSearchCriteriaMaker
    extends AbstractCriteriaMaker
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3058248000220241410L;

    /**
     * Constructeur par défaut.
     */
    public PharmacieSearchCriteriaMaker()
    {
        super(PharmacieSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit)
    {
        final PharmacieSearchCriteria crit = (PharmacieSearchCriteria) searchCrit;

        // Nom
        if (StringUtils.isNotEmpty(crit.getNom()))
        {
            CriteriaMakerUtils.addSqlCritere(criteria,
                                             "this_.nom",
                                             crit.getNom());
        }

        // Essai.
        if (crit.getEssai() != null)
        {
            final Criteria critDetails = criteria.createCriteria("detailsDonneesPharma",
                                                                 "detailsDonneesPharma");

            CriteriaMakerUtils.addInCritere(critDetails,
                                            "essai.id",
                                            new Long[]
                                            {crit.getEssai().getId() });
        }

        // Etablissement
        if (crit.getEtablissement() != null)
        {
            CriteriaMakerUtils.addCritere(criteria,
                                          "etablissement",
                                          crit.getEtablissement());
        }
    }
}
