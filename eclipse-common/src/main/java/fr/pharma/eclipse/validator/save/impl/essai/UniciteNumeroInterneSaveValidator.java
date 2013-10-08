package fr.pharma.eclipse.validator.save.impl.essai;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import fr.pharma.eclipse.domain.criteria.essai.EssaiSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.validator.save.SaveValidator;

/**
 * Validateur appelé, lors de la sauvegarde d'un essai, pour vérifier l'unicité
 * de son numéro interne dans le système.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class UniciteNumeroInterneSaveValidator implements SaveValidator<Essai>, BeanFactoryAware {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3481523003975779877L;

    /**
     * Fabrique Spring.
     */
    private BeanFactory beanFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(final Essai essai,
                         final GenericService<Essai> essaiService) {
        // Récupération du critère de recherche sur les essais.
        final EssaiSearchCriteria searchCrit = (EssaiSearchCriteria) this.beanFactory.getBean("essaiCriteria");
        searchCrit.setNumInterneStrict(essai.getNumInterne());

        // Aucun autre essai en base, autre que moi, ne doit avoir ce numéro
        // interne.
        final List<Essai> essaisWithNumInterne = essaiService.getAll(searchCrit);
        if (!essaisWithNumInterne.isEmpty() && essaisWithNumInterne.size() == 1 && !essaisWithNumInterne.get(0).getId().equals(essai.getId())) {
            throw new ValidationException("essai.numInterne", new String[]{"unique", }, essai);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBeanFactory(final BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

}
