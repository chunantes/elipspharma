package fr.pharma.eclipse.component.incident;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.faces.model.DataModel;

import fr.pharma.eclipse.comparator.incident.IncidentComparator;
import fr.pharma.eclipse.component.BeanManager;
import fr.pharma.eclipse.domain.criteria.incident.IncidentSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.incident.Incident;
import fr.pharma.eclipse.factory.incident.IncidentFactory;
import fr.pharma.eclipse.service.incident.IncidentService;

/**
 * Manager de gestion d'un incident.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class IncidentManager extends BeanManager<Incident> {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -8467396778907104660L;

    /**
     * Factory incident.
     */
    @Resource(name = "incidentFactory")
    private IncidentFactory incidentFactory;

    /**
     * Constructeur.
     * @param incidentService Service de gestion des incidents.
     */
    public IncidentManager(final IncidentService incidentService) {
        super(incidentService);
    }

    /**
     * Méthode en charge de récupérer les incidents pour l'essai en paramètre.
     * @param essai L'essai.
     * @return Le dataModel des incidents.
     */
    public DataModel<Incident> findIncidentsForEssai(final Essai essai) {
        final SortedSet<Incident> result = new TreeSet<Incident>(new IncidentComparator());

        // on ne charge les incidents que si l'essai est présent en base.
        if (essai.getId() != null) {
            final IncidentSearchCriteria criteria = new IncidentSearchCriteria();
            criteria.setEssai(essai);
            criteria.setActiveOrder("date");
            criteria.setAscending(false);
            result.addAll(this.getService().getAll(criteria));
        }
        return this.getHelper().returnAsDataModel(result);
    }

    /**
     * Méthode en charge d'initialiser un incident avec un essai.
     * @param essai Essai.
     */
    public void init(final Essai essai) {
        if (essai != null) {
            this.setBean(this.incidentFactory.getInitializedObject(essai));
        } else {
            this.setBean(this.incidentFactory.getInitializedObject());
        }
    }
    public void ajouterIncident() {
        this.getService().save(this.getBean());
    }

    /**
     * Réinitialise le manager.
     */
    public void reinit() {
        this.setBean(null);
    }

    /**
     * Setter pour incidentFactory.
     * @param incidentFactory le incidentFactory à écrire.
     */
    public void setIncidentFactory(final IncidentFactory incidentFactory) {
        this.incidentFactory = incidentFactory;
    }
}
