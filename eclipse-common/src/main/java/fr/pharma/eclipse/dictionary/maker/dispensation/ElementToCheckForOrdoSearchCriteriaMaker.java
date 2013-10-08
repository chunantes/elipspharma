package fr.pharma.eclipse.dictionary.maker.dispensation;

import java.util.Calendar;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.dispensation.ElementToCheckForOrdoSearchCriteria;
import fr.pharma.eclipse.domain.enums.TypeElementToCheck;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Artisan de recherche pour les ElementsToCheck dans la gestion des
 * ordonnanciers de fabrication/reconstitution.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ElementToCheckForOrdoSearchCriteriaMaker extends AbstractCriteriaMaker {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 7117088843299154005L;

    /**
     * Constructeur par défaut.
     */
    public ElementToCheckForOrdoSearchCriteriaMaker() {
        super(ElementToCheckForOrdoSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit) {
        final ElementToCheckForOrdoSearchCriteria crit = (ElementToCheckForOrdoSearchCriteria) searchCrit;

        // Validé / Vérifié
        CriteriaMakerUtils.addCritere(criteria, "checked", Boolean.TRUE);

        // Infos Ordonnancier null
        criteria.add(Restrictions.isNull("numOrdonnancier"));
        criteria.add(Restrictions.isNull("ordonnancier"));

        // Type (fabrication ou reconstitution)
        criteria.add(Restrictions.in("type", new Object[]{TypeElementToCheck.FABRICATION, TypeElementToCheck.RECONSTITUTION_PSM, TypeElementToCheck.RECONSTITUTION_SIMPLE, }));

        // Pharmacie
        if (crit.getPharmacie() != null) {
            final Criteria critDispensation = criteria.createCriteria("dispensation");
            CriteriaMakerUtils.addCritere(critDispensation, "pharmacie", crit.getPharmacie());
        }

        // Date de début
        if (crit.getDateDebut() != null) {
            criteria.add(Restrictions.ge("dateChecked", crit.getDateDebut()));
        }

        // Date de fin
        if (crit.getDateFin() != null) {
            final Calendar fin = Calendar.getInstance(EclipseConstants.LOCALE);
            fin.setTime(crit.getDateFin().getTime());
            // Ajout d'un jour
            fin.add(Calendar.DAY_OF_MONTH, 1);
            criteria.add(Restrictions.le("dateChecked", fin));
        }
    }

}
