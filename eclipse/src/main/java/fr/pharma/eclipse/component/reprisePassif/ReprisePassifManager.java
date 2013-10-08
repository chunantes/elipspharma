/**
 * 
 */
package fr.pharma.eclipse.component.reprisePassif;

import java.util.List;

import fr.pharma.eclipse.component.BeanListManager;
import fr.pharma.eclipse.domain.criteria.reprisePassif.ReprisePassifSearchCriteria;
import fr.pharma.eclipse.domain.model.reprisePassif.ReprisePassif;

/**
 * @author Netapsys
 * Manager de gestion des reprises passif
 *
 */
public class ReprisePassifManager extends BeanListManager<ReprisePassif> {
	
	private static final long serialVersionUID = -8588252744984985644L;
	
	/**
	 * Critères de recherche des reprises passif
	 */
	private ReprisePassifSearchCriteria criteria;

	/**
	 * liste des reprises passif obtenus en résultat de recherche
	 */
	private List<ReprisePassif> ordonnancier;
	
	public ReprisePassifManager(final ReprisePassifSearchCriteria searchCriteria) {
		super(searchCriteria);
		this.setCriteria(searchCriteria);
	}
	
	/**
	 * reinitialisation du manager
	 */
	public void init(){
		this.setOrdonnancier(null);
	}

	/**
	 * @return the ordonnancier
	 */
	public List<ReprisePassif> getOrdonnancier() {
		return ordonnancier;
	}

	/**
	 * @param ordonnancier the ordonnancier to set
	 */
	public void setOrdonnancier(List<ReprisePassif> ordonnancier) {
		this.ordonnancier = ordonnancier;
	}

	/**
	 * @return the criteria
	 */
	public ReprisePassifSearchCriteria getCriteria() {
		return criteria;
	}

	/**
	 * @param criteria the criteria to set
	 */
	public void setCriteria(ReprisePassifSearchCriteria criteria) {
		this.criteria = criteria;
	}
	

}
