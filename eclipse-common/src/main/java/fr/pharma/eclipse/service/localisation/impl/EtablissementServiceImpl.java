package fr.pharma.eclipse.service.localisation.impl;

import javax.annotation.Resource;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.model.localisation.Etablissement;
import fr.pharma.eclipse.domain.model.suivi.localisation.EtablissementSuivi;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.localisation.EtablissementService;

/**
 * Classe d'implémentation du service de gestion des établissements.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EtablissementServiceImpl extends GenericServiceImpl<Etablissement> implements EtablissementService {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -1699717276089241566L;

    /**
     * Factory de suivi d'établissement.
     */
    @Resource(name = "etablissementSuiviFactory")
    private SuiviFactory<EtablissementSuivi> etabSuiviFactory;

    /**
     * Constructeur.
     * @param etablissementDao Dao de gestion des établissements.
     */
    public EtablissementServiceImpl(final GenericDao<Etablissement> etablissementDao) {
        super(etablissementDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Etablissement save(final Etablissement etablissement) {
        final Etablissement etablissementToSave = this.reattach(etablissement);
        final EtablissementSuivi etabSuivi = this.etabSuiviFactory.getInitializedObject();
        etabSuivi.setEtablissement(etablissementToSave);
        etablissementToSave.getModifs().add(etabSuivi);
        return super.save(etablissementToSave);
    }

    /**
     * Setter pour etabSuiviFactory.
     * @param etabSuiviFactory le etabSuiviFactory à écrire.
     */
    public void setEtabSuiviFactory(final SuiviFactory<EtablissementSuivi> etabSuiviFactory) {
        this.etabSuiviFactory = etabSuiviFactory;
    }

}
