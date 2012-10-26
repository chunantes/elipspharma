package fr.pharma.eclipse.dictionary.maker.sigrec.essai;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.sigrec.essai.EssaiSigrecSearchCriteria;

/**
 * Artisan de recherche pour les essais sigrec.
 
 * @version $Revision$ $Date$
 */
public class EssaiSigrecSearchCriteriaMaker
    extends AbstractCriteriaMaker
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1073970765339009251L;

    /**
     * Constructeur par défaut.
     */
    public EssaiSigrecSearchCriteriaMaker()
    {
        super(EssaiSigrecSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit)
    {
        final EssaiSigrecSearchCriteria crit = (EssaiSigrecSearchCriteria) searchCrit;

        // Nom
        if (StringUtils.isNotEmpty(crit.getNom()))
        {
            CriteriaMakerUtils.addSqlCritere(criteria,
                                             "this_.nom",
                                             crit.getNom());
        }
        // Num identifiant AC
        if (StringUtils.isNotEmpty(crit.getNumSigrec()))
        {
            final Criteria detail = criteria.createCriteria("detailRecherche",
                                                            "detailRecherche");
            CriteriaMakerUtils.addSqlCritere(detail,
                                             "numEnregistrement",
                                             crit.getNumSigrec());
        }
        // Promoteur
        if (crit.getPromoteur() != null)
        {
            CriteriaMakerUtils.addCritere(criteria,
                                          "promoteur",
                                          crit.getPromoteur());
        }
    }

}
