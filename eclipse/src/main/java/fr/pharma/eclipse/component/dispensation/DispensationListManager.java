package fr.pharma.eclipse.component.dispensation;

import fr.pharma.eclipse.component.BeanListManager;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;

/**
 * Manager de liste de dispensations.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DispensationListManager extends BeanListManager<Dispensation> {

    /**
     * SserialVersionUID.
     */
    private static final long serialVersionUID = 197691295028833783L;

    /**
     * @param searchCriteria
     */
    public DispensationListManager(final SearchCriteria searchCriteria) {
        super(searchCriteria);
    }

}
