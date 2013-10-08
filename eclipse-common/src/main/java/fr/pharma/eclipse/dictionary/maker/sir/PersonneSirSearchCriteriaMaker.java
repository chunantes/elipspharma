package fr.pharma.eclipse.dictionary.maker.sir;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.sir.PersonneSirSearchCriteria;

/**
 * Artisan de recherche pour les personnes SIR.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PersonneSirSearchCriteriaMaker extends AbstractCriteriaMaker {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1826959346218952987L;

    /**
     * Constructeur par défaut.
     */
    public PersonneSirSearchCriteriaMaker() {
        super(PersonneSirSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit) {
        final PersonneSirSearchCriteria crit = (PersonneSirSearchCriteria) searchCrit;
        // Login
        if (StringUtils.isNotEmpty(crit.getLogin())) {
            // Recherche stricte sur le login (cas de l'authentification)
            if ((crit.getStrictSearchLogin() != null) && (crit.getStrictSearchLogin())) {
                CriteriaMakerUtils.addCritere(criteria, "login", crit.getLogin());
            } else {
                CriteriaMakerUtils.addSqlCritere(criteria, "this_.per_login", crit.getLogin());
            }
        }
        // Nom
        if (StringUtils.isNotEmpty(crit.getNom())) {
            CriteriaMakerUtils.addSqlCritere(criteria, "this_.per_nom", crit.getNom());
        }
        // Prénom
        if (StringUtils.isNotEmpty(crit.getPrenom())) {
            CriteriaMakerUtils.addSqlCritere(criteria, "this_.per_prenom", crit.getPrenom());
        }
    }
}
