package fr.pharma.eclipse.dictionary.maker.acteur;

import java.util.List;

import org.hibernate.Criteria;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.acteur.PharmacienSearchCriteria;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;

/**
 * Artisan de recherche pour les pharmaciens.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PharmacienSearchCriteriaMaker extends AbstractCriteriaMaker {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3360356580720981191L;

    /**
     * Maker de personne.
     */
    private PersonneSearchCriteriaMaker personneCriteriaMaker;

    /**
     * Constructeur par défaut.
     */
    public PharmacienSearchCriteriaMaker() {
        super(PharmacienSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit) {
        this.personneCriteriaMaker.transform(criteria, searchCrit);

        final PharmacienSearchCriteria crit = (PharmacienSearchCriteria) searchCrit;

        // Type de pharmacien
        if (crit.getTypePharmacien() != null) {
            CriteriaMakerUtils.addCritere(criteria, "typePharmacien", crit.getTypePharmacien());
        }

        // Pharmacie
        if ((crit.getPharmacies() != null) && !crit.getPharmacies().isEmpty()) {
            final List<Integer> pharmacieIds = CriteriaMakerUtils.prepareObjectIds(crit.getPharmacies());
            final Criteria critPharmacies = criteria.createCriteria("pharmacies", "pharmacies");
            CriteriaMakerUtils.addInCritere(critPharmacies, "pharmacies.id", pharmacieIds.toArray());
        }
    }

    /**
     * Getter sur personneCriteriaMaker.
     * @return Retourne le personneCriteriaMaker.
     */
    PersonneSearchCriteriaMaker getPersonneCriteriaMaker() {
        return this.personneCriteriaMaker;
    }

    /**
     * Setter pour personneCriteriaMaker.
     * @param personneCriteriaMaker le personneCriteriaMaker à écrire.
     */
    public void setPersonneCriteriaMaker(final PersonneSearchCriteriaMaker personneCriteriaMaker) {
        this.personneCriteriaMaker = personneCriteriaMaker;
    }

}
