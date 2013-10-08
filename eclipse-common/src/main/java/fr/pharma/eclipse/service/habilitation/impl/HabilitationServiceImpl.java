package fr.pharma.eclipse.service.habilitation.impl;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.habilitation.HabilitationService;

/**
 * Classe d'implémentation du service de gestion des habilitations.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class HabilitationServiceImpl extends GenericServiceImpl<Habilitation> implements HabilitationService {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -1699717276089241566L;

    /**
     * Constructeur.
     * @param habilitationDao Dao de gestion des habilitations.
     */
    public HabilitationServiceImpl(final GenericDao<Habilitation> habilitationDao) {
        super(habilitationDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Habilitation save(final Habilitation habilitation) {
        final Habilitation habilitationToSave = this.reattach(habilitation);
        return super.save(habilitationToSave);
    }

}
