package fr.pharma.eclipse.service.localisation.impl;

import javax.annotation.Resource;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.model.localisation.Pole;
import fr.pharma.eclipse.domain.model.suivi.localisation.PoleSuivi;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.localisation.PoleService;

/**
 * Classe d'implémentation du service de gestion de pole.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PoleServiceImpl extends GenericServiceImpl<Pole> implements PoleService {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2723923953954098505L;

    /**
     * Factory de suivi de pole.
     */
    @Resource(name = "poleSuiviFactory")
    private SuiviFactory<PoleSuivi> poleSuiviFactory;

    /**
     * Constructeur.
     * @param poleDao Dao de gestion des poles.
     */
    public PoleServiceImpl(final GenericDao<Pole> poleDao) {
        super(poleDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pole save(final Pole pole) {
        final Pole poleToSave = this.reattach(pole);
        final PoleSuivi poleSuivi = this.poleSuiviFactory.getInitializedObject();
        poleSuivi.setPole(poleToSave);
        poleToSave.getModifs().add(poleSuivi);
        return super.save(poleToSave);
    }

    /**
     * Setter pour poleSuiviFactory.
     * @param poleSuiviFactory le poleSuiviFactory à écrire.
     */
    public void setPoleSuiviFactory(final SuiviFactory<PoleSuivi> poleSuiviFactory) {
        this.poleSuiviFactory = poleSuiviFactory;
    }

}
