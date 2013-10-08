package fr.pharma.eclipse.factory.design;

import fr.pharma.eclipse.domain.model.design.PrescriptionType;
import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.factory.common.BeanObjectFactory;

/**
 * Factory de Bean PrescriptionType.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PrescriptionTypeFactory extends BeanObjectFactory<PrescriptionType> {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3171922165561292902L;

    /**
     * Constructeur.
     * @param bean Classe.
     */
    public PrescriptionTypeFactory(final Class<PrescriptionType> bean) {
        super(bean);
    }

    /**
     * Méthode en charge de construire une prescription..
     * @param sequence La sequence.
     * @return La PrescriptionType
     */
    public PrescriptionType getInitializedObject(final Sequence sequence) {
        final PrescriptionType prescription = super.getInitializedObject();
        prescription.setSequence(sequence);
        return prescription;
    }
}
