/**
 * 
 */
package fr.pharma.eclipse.service.reprisePassif;

import java.util.List;

import fr.pharma.eclipse.domain.criteria.reprisePassif.ReprisePassifSearchCriteria;
import fr.pharma.eclipse.domain.model.reprisePassif.ReprisePassif;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * @author Netapsys
 * Interface de service de gestion des reprises passif
 *
 */
public interface ReprisePassifService <BEAN extends ReprisePassif> extends GenericService<ReprisePassif> {

	/**
	 * Calcule les reprises passifs à retourner en fonction des critères de recherche indiqués 
	 * et triés en fonction du numéro d'ordonnancier
	 * @param criteria critères de recherche
	 * @return une liste de ReprisePassif
	 */
	public List<ReprisePassif> calculerOrdonnancierReprisePassif(ReprisePassifSearchCriteria criteria);
			
}
