/**
 * 
 */
package fr.pharma.eclipse.dictionary.maker.reprisePassif;

import java.util.Calendar;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.reprisePassif.ReprisePassifSearchCriteria;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * @author Netapsys
 *
 */
public class ReprisePassifSearchCriteriaMaker extends AbstractCriteriaMaker {

	private static final long serialVersionUID = 5053321609673860198L;
	
	public ReprisePassifSearchCriteriaMaker() {
		super(ReprisePassifSearchCriteria.class);
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public void transform(Criteria criteria, SearchCriteria searchCrit) {
		final ReprisePassifSearchCriteria crit = (ReprisePassifSearchCriteria) searchCrit;

		//Date de dispensation: recherche à partir d'un intervalle :		
		 // Date de début (comprise dans l'intervalle de recherche)
        if (crit.getDateDebut() != null) {
            criteria.add(Restrictions.ge("dateDispensation", crit.getDateDebut()));
        }
         // Date de fin (comprise dans l'intervalle de recherche)
        if (crit.getDateFin() != null) {
            final Calendar fin = Calendar.getInstance(EclipseConstants.LOCALE);
            fin.setTime(crit.getDateFin().getTime());
            // Ajout d'un jour
            fin.add(Calendar.DAY_OF_MONTH, 1);
            criteria.add(Restrictions.le("dateDispensation", fin));
        }
        
        
        // Initiales du patient 
        if (crit.getInitialesPatient() != null && !(crit.getInitialesPatient().isEmpty())) {
        	criteria.add(Restrictions.ilike("initialesPatient", crit.getInitialesPatient(),MatchMode.ANYWHERE ));
        	}
        
        // Essai promoteur : l'essai promoteur peut être recherché en n'indiquant qu'une partie de la valeur
        if (crit.getEssaiPromoteur() != null && !(crit.getEssaiPromoteur().isEmpty())) {
            criteria.add(Restrictions.ilike("essaiPromoteur", crit.getEssaiPromoteur(),MatchMode.ANYWHERE ));

        }
	}
	
	

}
