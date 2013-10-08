package fr.pharma.eclipse.domain.model.suivi.localisation;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;

/**
 * Bean métier représentant les suivis de modifications sur le bean Service.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "service_suivi")
public class ServiceSuivi extends Suivi {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1378238576703743440L;

    /**
     * Objet Service.
     */
    @ManyToOne
    @JoinColumn(name = "id_service")
    @Index(name = "idx_suivi_service")
    private Service service;

    /**
     * Getter sur service.
     * @return Retourne le service.
     */
    public Service getService() {
        return this.service;
    }

    /**
     * Setter pour service.
     * @param service le service à écrire.
     */
    public void setService(final Service service) {
        this.service = service;
    }

}
