package fr.pharma.eclipse.dictionary.maker.incident;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.incident.IncidentSearchCriteria;

/**
 * Artisan de recherche pour les incidents.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class IncidentSearchCriteriaMaker extends AbstractCriteriaMaker {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 6424292792443550242L;

    /**
     * Constructeur par défaut.
     */
    public IncidentSearchCriteriaMaker() {
        super(IncidentSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit) {
        final IncidentSearchCriteria crit = (IncidentSearchCriteria) searchCrit;

        // Critères sur Essai
        Criteria critEssai = null;
        if (crit.getEssai() != null) {
            critEssai = criteria.createCriteria("essai");
            critEssai.add(Restrictions.idEq(crit.getEssai().getId()));
        }

        // Essai DTO
        if (crit.getEssaiDTO() != null) {
            critEssai = criteria.createCriteria("essai");
            critEssai.add(Restrictions.idEq(crit.getEssaiDTO().getId()));
        }

        // Date de début
        if (crit.getDateDebut() != null) {
            criteria.add(Restrictions.ge("date", crit.getDateDebut()));
        }

        // Date de fin
        if (crit.getDateFin() != null) {
            final Calendar cal = Calendar.getInstance();
            cal.setTime(crit.getDateFin().getTime());
            // Ajout d'un jour pour gérer les heures
            cal.add(Calendar.DAY_OF_MONTH, 1);
            criteria.add(Restrictions.le("date", cal));
        }

        // Restriction par rapport aux acls des essais
        final List<Long> idsEssais = this.getAclSearchDao().findIdsEssais();
        if (critEssai == null) {
            critEssai = criteria.createCriteria("essai");
        }
        CriteriaMakerUtils.addInCritere(criteria, "essai.id", idsEssais.toArray(new Object[idsEssais.size()]));
    }
}
