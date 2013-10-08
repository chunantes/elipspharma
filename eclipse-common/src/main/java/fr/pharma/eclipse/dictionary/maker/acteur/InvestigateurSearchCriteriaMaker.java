package fr.pharma.eclipse.dictionary.maker.acteur;

import java.util.List;

import org.hibernate.Criteria;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.acteur.InvestigateurSearchCriteria;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;

/**
 * Artisan de recherche pour les investigateurs.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class InvestigateurSearchCriteriaMaker extends AbstractCriteriaMaker {

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
    public InvestigateurSearchCriteriaMaker() {
        super(InvestigateurSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit) {
        this.personneCriteriaMaker.transform(criteria, searchCrit);

        final InvestigateurSearchCriteria crit = (InvestigateurSearchCriteria) searchCrit;

        // Titre
        if (crit.getTitre() != null) {
            CriteriaMakerUtils.addSqlCritere(criteria, "this_.titre", crit.getTitre());
        }

        // Services
        if ((crit.getServices() != null) && !crit.getServices().isEmpty()) {
            final List<Integer> serviceIds = CriteriaMakerUtils.prepareObjectIds(crit.getServices());
            final Criteria critServices = criteria.createCriteria("services", "services");
            CriteriaMakerUtils.addInCritere(critServices, "services.id", serviceIds.toArray());
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
