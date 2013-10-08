package fr.pharma.eclipse.service.localisation.impl;

import javax.annotation.Resource;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.domain.model.suivi.localisation.ServiceSuivi;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.localisation.ServiceService;

/**
 * Classe d'implémentation du service de gestion de service.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ServiceServiceImpl extends GenericServiceImpl<Service> implements ServiceService {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4850529702186016867L;

    /**
     * Factory de suivi de service.
     */
    @Resource(name = "serviceSuiviFactory")
    private SuiviFactory<ServiceSuivi> serviceSuiviFactory;

    /**
     * Constructeur.
     * @param serviceDao Dao de gestion des services.
     */
    public ServiceServiceImpl(final GenericDao<Service> serviceDao) {
        super(serviceDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Service save(final Service service) {
        final Service serviceToSave = this.reattach(service);
        final ServiceSuivi serviceSuivi = this.serviceSuiviFactory.getInitializedObject();
        serviceSuivi.setService(serviceToSave);
        serviceToSave.getModifs().add(serviceSuivi);
        return super.save(serviceToSave);
    }

    /**
     * Setter pour serviceSuiviFactory.
     * @param serviceSuiviFactory le serviceSuiviFactory à écrire.
     */
    public void setServiceSuiviFactory(final SuiviFactory<ServiceSuivi> serviceSuiviFactory) {
        this.serviceSuiviFactory = serviceSuiviFactory;
    }

}
