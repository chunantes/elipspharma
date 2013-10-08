package fr.pharma.eclipse.dictionary.maker.acteur;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.acteur.PersonneSearchCriteria;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;

/**
 * Artisan de recherche pour les personnes.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PersonneSearchCriteriaMaker extends AbstractCriteriaMaker {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -5396246636387414477L;

    /**
     * Constructeur par défaut.
     */
    public PersonneSearchCriteriaMaker() {
        super(PersonneSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit) {
        final PersonneSearchCriteria crit = (PersonneSearchCriteria) searchCrit;

        // Nom
        if (StringUtils.isNotEmpty(crit.getNom())) {
            CriteriaMakerUtils.addSqlCritere(criteria, "this_.nom", crit.getNom());
        }

        // Type de personne
        if (crit.getTypePersonne() != null) {
            CriteriaMakerUtils.addCritere(criteria, "type", crit.getTypePersonne());
        }

        // Nom de la société.
        if (crit.getNomSociete() != null) {
            CriteriaMakerUtils.addCritere(criteria, "nomSociete", crit.getNomSociete());
        }

        // Login
        if (StringUtils.isNotEmpty(crit.getLogin())) {
            CriteriaMakerUtils.addCritere(criteria, "login", crit.getLogin());
        }

        // Si essai sélectionné => Habilitation
        if ((crit.getEssai() != null) && ((crit.getDateDebut() != null) & (crit.getDateFin() != null))) {
            final Criteria critHabilitations = criteria.createCriteria("habilitations", "habilitations");
            this.setMaxDate(crit.getDateFin());
            final Disjunction dis = Restrictions.disjunction();
            dis.add(Restrictions.ge("dateDesactivation", crit.getDateDebut()));
            dis.add(Restrictions.isNull("dateDesactivation"));
            critHabilitations.add(Restrictions.le("dateCreation", crit.getDateFin()));
            CriteriaMakerUtils.addCritere(critHabilitations, "detailContacts", crit.getEssai().getDetailContacts());
            critHabilitations.add(dis);
        }
    }

    /**
     * @param crit
     */
    private void setMaxDate(final Calendar date) {
        date.set(Calendar.HOUR_OF_DAY, 23);
        date.set(Calendar.MINUTE, 59);
        date.set(Calendar.SECOND, 59);
    }
}
