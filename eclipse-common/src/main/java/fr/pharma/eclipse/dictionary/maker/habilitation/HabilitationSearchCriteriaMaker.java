package fr.pharma.eclipse.dictionary.maker.habilitation;

import org.hibernate.Criteria;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.habilitation.HabilitationSearchCriteria;

/**
 * Artisan de recherche pour les habilitations.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class HabilitationSearchCriteriaMaker extends AbstractCriteriaMaker {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 61875340866448317L;

    /**
     * Constructeur par défaut.
     */
    public HabilitationSearchCriteriaMaker() {
        super(HabilitationSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit) {
        final HabilitationSearchCriteria crit = (HabilitationSearchCriteria) searchCrit;

        // Droit
        if (crit.getDroit() != null) {
            CriteriaMakerUtils.addCritere(criteria, "droit", crit.getDroit());
        }
        // Essai
        if (crit.getEssai() != null) {
            CriteriaMakerUtils.addCritere(criteria, "detailContacts", crit.getEssai().getDetailContacts());
        }
        // Personne
        if (crit.getPersonne() != null) {
            CriteriaMakerUtils.addCritere(criteria, "personne", crit.getPersonne());
        }
    }

}
