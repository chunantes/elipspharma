package fr.pharma.eclipse.service.ordonnancier.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.dispensation.DispensationForOrdoSearchCriteria;
import fr.pharma.eclipse.domain.criteria.ordonnancier.OrdonnancierSearchCriteria;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.ordonnancier.OrdonnancierDisp;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;
import fr.pharma.eclipse.service.dispensation.DispensationService;

/**
 * Classe d'implémentation du service de gestion des ordonnanciers de dispensation.
 
 * @version $Revision$ $Date$
 */
public class OrdonnancierDispServiceImpl
    extends OrdonnancierServiceImpl<OrdonnancierDisp>
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -81577467680769954L;

    /**
     * Service de gestion des dispensations.
     */
    @Resource(name = "dispensationService")
    private DispensationService dispensationService;

    /**
     * Factory d'ordonnancier de dispensation.
     */
    @Resource(name = "ordonnancierDispFactory")
    private SuiviFactory<OrdonnancierDisp> ordonnancierFactory;

    /**
     * Constructeur.
     * @param ordonnancierDispDao Dao de gestion des ordonnanciers de dispensation.
     */
    public OrdonnancierDispServiceImpl(final GenericDao<OrdonnancierDisp> ordonnancierDispDao)
    {
        super(ordonnancierDispDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OrdonnancierDisp calculerOrdonnancier(final OrdonnancierSearchCriteria criteria)
    {
        final Pharmacie pharmacie = criteria.getPharmacie();

        // Récupération des dispensations éligibles pour l'ordonnancier
        final DispensationForOrdoSearchCriteria crit = new DispensationForOrdoSearchCriteria();
        crit.setPharmacie(pharmacie);
        crit.setDateDebut(criteria.getDateDebut());
        crit.setDateFin(criteria.getDateFin());
        crit.setActiveOrder("dateDispensation");
        crit.setAscending(true);

        final List<Dispensation> dispensations = this.dispensationService.getAll(crit);

        if (dispensations.isEmpty())
        {
            throw new ValidationException("ordonnancier.dispensations",
                                          new String[]
                                          {"empty" });
        }
        else
        {
            // Création de l'ordonnancier
            final OrdonnancierDisp ordonnancier = this.ordonnancierFactory.getInitializedObject();
            ordonnancier.setDateDebut(criteria.getDateDebut());
            ordonnancier.setDateFin(criteria.getDateFin());
            ordonnancier.setPharmacie(pharmacie);

            for (final Dispensation dispensation : dispensations)
            {
                // Le numéro d'ordonnancier est créé à l'enregistrement de la dispensation
                dispensation.setOrdonnancier(ordonnancier);
                ordonnancier.getDispensations().add(dispensation);
            }

            // Sauvegarde de l'ordonnancier
            return this.save(ordonnancier);
        }
    }

    /**
     * Setter pour dispensationService.
     * @param dispensationService Le dispensationService à écrire.
     */
    public void setDispensationService(final DispensationService dispensationService)
    {
        this.dispensationService = dispensationService;
    }

    /**
     * Setter pour ordonnancierFactory.
     * @param ordonnancierFactory Le ordonnancierFactory à écrire.
     */
    public void setOrdonnancierFactory(final SuiviFactory<OrdonnancierDisp> ordonnancierFactory)
    {
        this.ordonnancierFactory = ordonnancierFactory;
    }

}
