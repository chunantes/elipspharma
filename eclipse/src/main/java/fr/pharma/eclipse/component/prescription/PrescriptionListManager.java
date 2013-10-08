package fr.pharma.eclipse.component.prescription;

import fr.pharma.eclipse.component.BeanListManager;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.prescription.Prescription;

/**
 * Description de la classe.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PrescriptionListManager extends BeanListManager<Prescription> {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 7618116404545225666L;

    /**
     * Constructeur.
     * @param searchCriteria Critère de recherche.
     */
    public PrescriptionListManager(final SearchCriteria searchCriteria) {
        super(searchCriteria);
    }

}
