package fr.pharma.eclipse.validator.save.impl;

import java.util.List;

import fr.pharma.eclipse.domain.criteria.patient.PatientSearchCriteria;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.validator.save.SaveValidator;

/**
 * Validateur du patient avant sauvegarde.
 
 * @version $Revision$ $Date$
 */
public class PatientSaveValidator<BEAN extends Patient>
    implements SaveValidator<BEAN>
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 927150658224190354L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(final BEAN bean,
                         final GenericService<BEAN> beanService)
    {
        final PatientSearchCriteria crit = new PatientSearchCriteria();
        crit.setNumeroIppExact(bean.getNumeroIpp());
        final List<BEAN> result = beanService.getAll(crit);

        if (!result.isEmpty()
            && !result.get(0).getId().equals(bean.getId()))
        {
            throw new ValidationException("patient.ipp",
                                          new String[]
                                          {"exist" },
                                          bean);
        }
    }

}
