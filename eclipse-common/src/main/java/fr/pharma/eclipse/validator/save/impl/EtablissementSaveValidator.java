package fr.pharma.eclipse.validator.save.impl;

import java.io.Serializable;
import java.util.List;

import fr.pharma.eclipse.domain.criteria.localisation.ServiceSearchCriteria;
import fr.pharma.eclipse.domain.model.localisation.Etablissement;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.validator.save.SaveValidator;

/**
 * Classe en charge de valider la sauvegarde d'un bean Etablissement.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EtablissementSaveValidator<BEAN extends Etablissement> implements Serializable, SaveValidator<BEAN> {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -2744154617740567773L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(final BEAN bean,
                         final GenericService<BEAN> beanService) {
        final ServiceSearchCriteria crit = new ServiceSearchCriteria();
        crit.setNom(bean.getNom());

        final List<BEAN> result = beanService.getAll(crit);

        if ((result.size() > 0) && !result.get(0).getId().equals(bean.getId())) {
            throw new ValidationException("etablissement.nom", new String[]{"exist" }, bean);
        }

    }

}
