package fr.pharma.eclipse.validator.save.impl;

import java.io.Serializable;
import java.util.List;

import fr.pharma.eclipse.domain.criteria.localisation.ServiceSearchCriteria;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.validator.save.SaveValidator;

/**
 * Classe en charge de valider la sauvegarde d'un bean Service.
 
 * @version $Revision$ $Date$
 */
public class ServiceSaveValidator<BEAN extends Service>
    implements Serializable, SaveValidator<BEAN>
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -2744154617740567773L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(final BEAN bean,
                         final GenericService<BEAN> beanService)
    {
        final ServiceSearchCriteria crit = new ServiceSearchCriteria();
        crit.setNom(bean.getNom());
        final List<BEAN> result = beanService.getAll(crit);

        if (result.size() > 0
            && !result.get(0).getId().equals(bean.getId()))
        {
            throw new ValidationException("service.nom",
                                          new String[]
                                          {"exist" },
                                          bean);
        }

        if (bean.getSite() == null
            && bean.getPole() == null)
        {
            throw new ValidationException("service.pole.site",
                                          new String[]
                                          {"obligatoire" },
                                          bean);
        }
    }

}
