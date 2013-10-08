package fr.pharma.eclipse.validator.remove.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.model.design.Bras;
import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.validator.remove.RemoveValidator;

/**
 * Validateur de suppression d'un bras.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class BrasRemoveValidator implements RemoveValidator<Bras>, Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 7844049178351896226L;
    /**
     * Service prescription.
     */
    @Resource(name = "sequenceRemoveValidator")
    private RemoveValidator<Sequence> sequenceRemoveValidator;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(final Bras bean) {
        for (final Bras b : bean.getSousBras()) {
            this.validate(b);
        }

        for (final Sequence sequence : bean.getSequences()) {
            this.sequenceRemoveValidator.validate(sequence);
        }
    }
    /**
     * Setter pour sequenceRemoveValidator.
     * @param sequenceRemoveValidator le sequenceRemoveValidator à écrire.
     */
    public void setSequenceRemoveValidator(final RemoveValidator<Sequence> sequenceRemoveValidator) {
        this.sequenceRemoveValidator = sequenceRemoveValidator;
    }

}
