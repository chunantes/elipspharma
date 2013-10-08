package fr.pharma.eclipse.domain.criteria.acteur;

import java.util.List;

import fr.pharma.eclipse.domain.model.localisation.Service;

/**
 * Critère de recherche d'ARC investigateurs.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ArcInvestigateurSearchCriteria extends PersonneSearchCriteria {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3189216877185824133L;

    /**
     * Services.
     */
    private List<Service> services;

    /**
     * Getter sur services.
     * @return Retourne le services.
     */
    public List<Service> getServices() {
        return this.services;
    }

    /**
     * Setter pour services.
     * @param services le services à écrire.
     */
    public void setServices(final List<Service> services) {
        this.services = services;
    }
}
