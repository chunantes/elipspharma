package fr.pharma.eclipse.domain.model.builder;

import fr.pharma.eclipse.domain.model.localisation.Pole;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.domain.model.localisation.Site;

/**
 * Classe de builder de Service.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ServiceBuilder {

    private final Service service;

    public ServiceBuilder() {
        this.service = new Service();
    }

    public ServiceBuilder withId(final Long id) {
        this.service.setId(id);
        return this;
    }

    public ServiceBuilder withNom(final String value) {
        this.service.setNom(value);
        return this;
    }

    public ServiceBuilder withPole(final Pole value) {
        this.service.setPole(value);
        return this;
    }

    public ServiceBuilder withSite(final Site value) {
        this.service.setSite(value);
        return this;
    }

    public Service build() {
        return this.service;
    }

    public Service buildHematologie() {
        this.withId(1L);
        this.withNom("HEMATOLOGIE");
        return this.service;
    }
}
