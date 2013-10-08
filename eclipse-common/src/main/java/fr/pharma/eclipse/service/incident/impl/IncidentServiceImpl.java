package fr.pharma.eclipse.service.incident.impl;

import java.util.List;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.incident.IncidentSearchCriteria;
import fr.pharma.eclipse.domain.model.incident.Incident;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.incident.IncidentService;

/**
 * Classe d'implémentation du service de gestion des incidents.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class IncidentServiceImpl extends GenericServiceImpl<Incident> implements IncidentService {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1572333342937082499L;

    /**
     * Constructeur.
     * @param genericDao Dao.
     */
    public IncidentServiceImpl(final GenericDao<Incident> genericDao) {
        super(genericDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Incident> getAll() {
        return this.getAll(new IncidentSearchCriteria());
    }

}
