/**
 * 
 */
package fr.pharma.eclipse.service.reprisePassif.impl;

import java.util.List;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.reprisePassif.ReprisePassifSearchCriteria;
import fr.pharma.eclipse.domain.model.reprisePassif.ReprisePassif;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.reprisePassif.ReprisePassifService;

/**
 * @author dev
 *
 */
public class ReprisePassifServiceImpl<BEAN extends ReprisePassif> extends GenericServiceImpl<ReprisePassif> implements ReprisePassifService<BEAN> {

	public ReprisePassifServiceImpl(GenericDao<ReprisePassif> genericDao) {
		super(genericDao);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5968593472608454005L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ReprisePassif> calculerOrdonnancierReprisePassif(
			ReprisePassifSearchCriteria criteria) {
		
		criteria.setActiveOrder("dateDispensation");
        criteria.setAscending(true);

        final List<ReprisePassif> reprises = this.getAll(criteria);
		return reprises;
	}

}

