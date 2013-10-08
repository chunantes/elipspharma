package fr.pharma.eclipse.dictionary.maker.patient;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.patient.InclusionSearchCriteria;

/**
 * Artisan de recherche pour les inclusions.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class InclusionSearchCriteriaMaker extends AbstractCriteriaMaker {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -4441012063893341303L;

    /**
     * Constructeur par défaut.
     */
    public InclusionSearchCriteriaMaker() {
        super(InclusionSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit) {
        final InclusionSearchCriteria crit = (InclusionSearchCriteria) searchCrit;

        Criteria critEssai = null;

        // Essai
        if (crit.getEssai() != null) {
            critEssai = criteria.createCriteria("essai");
            critEssai.add(Restrictions.idEq(crit.getEssai().getId()));
        }

        // PAtient
        if (crit.getPatient() != null) {
            CriteriaMakerUtils.addCritere(criteria, "patient", crit.getPatient());
        }

        // Actif
        if (crit.getActif() != null) {
            CriteriaMakerUtils.addCritere(criteria, "actif", crit.getActif());
        }

        // Restriction par rapport aux acls des essais
        final List<Long> idsEssais = this.getAclSearchDao().findIdsEssais();
        if (critEssai == null) {
            critEssai = criteria.createCriteria("essai");
        }
        CriteriaMakerUtils.addInCritere(criteria, "essai.id", idsEssais.toArray(new Object[idsEssais.size()]));

        // // Date de début
        // if (crit.getDateDebut() != null)
        // {
        // criteria.add(Restrictions.ge("dateDispensation",
        // crit.getDateDebut()));
        // }
        //
        // // Date de fin
        // if (crit.getDateFin() != null)
        // {
        // final Calendar fin = Calendar.getInstance(EclipseConstants.LOCALE);
        // fin.setTime(crit.getDateFin().getTime());
        // // Ajout d'un jour
        // fin.add(Calendar.DAY_OF_MONTH,
        // 1);
        // criteria.add(Restrictions.le("datePrescription",
        // fin));
        // }

    }
}
