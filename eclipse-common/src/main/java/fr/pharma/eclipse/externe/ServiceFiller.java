package fr.pharma.eclipse.externe;

import java.util.List;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.criteria.localisation.ServiceSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.converter.filler.Filler;

/**
 * Filler en charge de populer un Essai à partir du promoteur d'un EssaiSigec.
 *
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ServiceFiller implements Filler<EssaiExterne, Essai> {

    /**
     * Service de promoteurs.
     */
    @Resource(name = "serviceService")
    private GenericService<Service> serviceService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(final EssaiExterne source,
            final Essai destination) {
        // recherche des tous les promoteurs correspondants à l'identifiant du
        // promoteur courant.
        final ServiceSearchCriteria crit = new ServiceSearchCriteria();
        if (source.getService()!= null) {
            crit.setNom(source.getService());
            final List<Service> services = this.serviceService.getAll(crit);

            // SI un promoteur est trouvé alors on l'affecte.
            if (services.size() > 0) {
                destination.getServices().add(services.get(0));
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(final EssaiExterne source) {
        return source.getService() != null;
    }

    /**
     * Setter pour serviceService.
     *
     * @param serviceService le serviceService à écrire.
     */
    public void setSerivceService(final GenericService<Service> serviceService) {
        this.serviceService = serviceService;
    }

}
