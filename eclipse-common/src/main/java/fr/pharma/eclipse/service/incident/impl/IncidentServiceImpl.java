package fr.pharma.eclipse.service.incident.impl;

import java.util.List;

import javax.annotation.Resource;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.incident.Incident;
import fr.pharma.eclipse.handler.habilitation.HabilitationHandler;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.incident.IncidentService;

/**
 * Classe d'implémentation du service de gestion des incidents.
 
 * @version $Revision$ $Date$
 */
public class IncidentServiceImpl
    extends GenericServiceImpl<Incident>
    implements IncidentService
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1572333342937082499L;

    /**
     * Gestionnaire d'habilitations sur les incidents.
     */
    @Resource(name = "essaiElementHabilitationHandler")
    private HabilitationHandler<Incident> habilitationHandler;

    /**
     * Constructeur.
     * @param genericDao Dao.
     */
    public IncidentServiceImpl(final GenericDao<Incident> genericDao)
    {
        super(genericDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Incident> getAll()
    {
        final List<Incident> essais = super.getAll();
        // Purge des essais par rapport aux habilitations
        this.habilitationHandler.purge(essais);
        return essais;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Incident> getAll(final SearchCriteria criteria)
    {
        final List<Incident> essais = super.getAll(criteria);
        // Purge des essais par rapport aux habilitations
        this.habilitationHandler.purge(essais);
        return essais;
    }

    /**
     * Setter pour habilitationHandler.
     * @param habilitationHandler le habilitationHandler à écrire.
     */
    public void setHabilitationHandler(final HabilitationHandler<Incident> habilitationHandler)
    {
        this.habilitationHandler = habilitationHandler;
    }

}
