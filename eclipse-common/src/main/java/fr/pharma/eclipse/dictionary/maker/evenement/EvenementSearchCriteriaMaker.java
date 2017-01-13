package fr.pharma.eclipse.dictionary.maker.evenement;

import java.util.Calendar;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.evenement.EvenementSearchCriteria;

/**
 * Artisan de recherche pour les événements.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EvenementSearchCriteriaMaker extends AbstractCriteriaMaker {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 6424292792443550242L;

    /**
     * Constructeur par défaut.
     */
    public EvenementSearchCriteriaMaker() {
        super(EvenementSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit) {
        final EvenementSearchCriteria crit = (EvenementSearchCriteria) searchCrit;

        // Critères sur Essai
        this.handleCriteriaEssai(criteria, crit);

        // Type d'événement
        if (crit.getTypeEvenement() != null) {
            CriteriaMakerUtils.addCritere(criteria, "typeEvenement", crit.getTypeEvenement());
        }

        // Type de visite
        if (crit.getTypeVisite() != null) {
            CriteriaMakerUtils.addCritere(criteria, "typeVisite", crit.getTypeVisite());
        }

        // Résultat de la visite
        if (crit.getResultatVisite() != null) {
            CriteriaMakerUtils.addCritere(criteria, "resultatVisite", crit.getResultatVisite());
        }

        // Recherche des visites dont le résultat est vide
        if (crit.getResultVisiteVide() != null && crit.getResultVisiteVide()) {
            criteria.add(Restrictions.isNull("resultatVisite"));
        }

        // Recherche des cessions PUI dont la date de réception est vide.
        if (crit.getDateReceptionVide() != null && crit.getDateReceptionVide()) {
            criteria.add(Restrictions.isNull("dateReception"));
        }
        
        // Date de début
        if (crit.getDateDebut() != null) {
            criteria.add(Restrictions.ge("dateDebut", crit.getDateDebut()));
        }

        // Date de fin
        if (crit.getDateFin() != null) {
            final Calendar cal = Calendar.getInstance();
            cal.setTime(crit.getDateFin().getTime());
            // Ajout d'un jour pour gérer les heures
            cal.add(Calendar.DAY_OF_MONTH, 1);
            criteria.add(Restrictions.le("dateDebut", cal));
        }
    }

    /**
     * Méthode en charge de gérer les critères posés sur les essais.
     * Les critères sur essais ne peuvent être combiné. 
     * La méthode prend également en charge le cas des évènements sans essais.
     * @param criteria Criteria Hibernate.
     * @param crit Critère de recherche sur Evenement.
     */
    private void handleCriteriaEssai(final Criteria criteria,
                                     final EvenementSearchCriteria crit) {
        final Criteria critEssai;

        if ((crit.getEssaiVide() == null || crit.getEssaiVide())) {
            critEssai = criteria.createCriteria("essai", Criteria.LEFT_JOIN);
        } else {
            critEssai = criteria.createCriteria("essai",Criteria.INNER_JOIN);   
        }
        
        // Essai
        if (crit.getEssai() != null) {
            critEssai.add(Restrictions.idEq(crit.getEssai().getId()));
        } else

        // Essai
        if (crit.getEssaiDTO() != null) {
            critEssai.add(Restrictions.idEq(crit.getEssaiDTO().getId()));
        } else
        
        // Liste d'identifiants d'essais
        if (crit.getIdsEssais() != null && crit.getIdsEssais().size() > 0) {
            if ((crit.getEssaiVide() == null || crit.getEssaiVide())) { 
                criteria.add(Restrictions.or(Restrictions.isNull("essai"),  Restrictions.in("essai.id", crit.getIdsEssais().toArray(new Long[crit.getIdsEssais().size()]))));
            } else {
                CriteriaMakerUtils.addInCritere(critEssai, "id", crit.getIdsEssais().toArray(new Long[crit.getIdsEssais().size()]));
            }
        }
    }
}
