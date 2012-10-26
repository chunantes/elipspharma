package fr.pharma.eclipse.service.evenement.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.evenement.EvenementSearchCriteria;
import fr.pharma.eclipse.domain.enums.evenement.TypeEvenement;
import fr.pharma.eclipse.domain.enums.evenement.TypeVisite;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.domain.model.suivi.evenement.EvenementSuivi;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;
import fr.pharma.eclipse.handler.habilitation.HabilitationHandler;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.evenement.EvenementService;
import fr.pharma.eclipse.service.evenement.updator.EvenementBeforeSaveUpdator;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe d'implémentation du service de gestion des événements.
 
 * @version $Revision$ $Date$
 */
public class EvenementServiceImpl
    extends GenericServiceImpl<Evenement>
    implements EvenementService
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1572333342937082499L;

    /**
     * Constante contenant le nb de jours pour la récupération des événements à venir.
     */
    private static final int NB_JOURS_RECUP_EVT = 5;

    /**
     * Factory de suivi de evenement.
     */
    @Resource(name = "evenementSuiviFactory")
    private SuiviFactory<EvenementSuivi> evtSuiviFactory;

    /**
     * Gestionnaire d'habilitations sur les evenements.
     */
    @Resource(name = "essaiElementHabilitationHandler")
    private HabilitationHandler<Evenement> habilitationHandler;

    /**
     * Service de gestion des essais.
     */
    @Resource(name = "essaiService")
    private EssaiService essaiService;

    /**
     * Updators.
     */
    private List<EvenementBeforeSaveUpdator> updators;

    /**
     * Constructeur.
     * @param evenementDao Dao de gestion des événements.
     */
    public EvenementServiceImpl(final GenericDao<Evenement> evenementDao)
    {
        super(evenementDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Evenement save(final Evenement evenement)
    {
        final String heureDebut = evenement.getHeureDebut();
        final Calendar dateDebut = evenement.getDateDebut();
        final String heureFin = evenement.getHeureFin();
        final Calendar dateFin = (Calendar) dateDebut.clone();
        evenement.setDateFin(dateFin);
        if (StringUtils.isNotEmpty(heureDebut))
        {
            try
            {
                final String[] tokens = heureDebut.split(EclipseConstants.COLON);
                dateDebut.set(Calendar.HOUR_OF_DAY,
                              Integer.valueOf(tokens[0]));
                dateDebut.set(Calendar.MINUTE,
                              Integer.valueOf(tokens[1]));
            }
            catch (final ArrayIndexOutOfBoundsException e)
            {
                evenement.setHeureDebut(null);
                this.handleExceptionHeures(dateDebut);
            }
            catch (final NumberFormatException e)
            {
                evenement.setHeureDebut(null);
                this.handleExceptionHeures(dateDebut);
            }
        }
        if (StringUtils.isNotEmpty(heureFin))
        {
            try
            {
                final String[] tokens = heureFin.split(EclipseConstants.COLON);
                dateFin.set(Calendar.HOUR_OF_DAY,
                            Integer.valueOf(tokens[0]));
                dateFin.set(Calendar.MINUTE,
                            Integer.valueOf(tokens[1]));
            }
            catch (final ArrayIndexOutOfBoundsException e)
            {
                evenement.setHeureFin(null);
                this.handleExceptionHeures(dateFin);
            }
            catch (final NumberFormatException e)
            {
                evenement.setHeureFin(null);
                this.handleExceptionHeures(dateFin);
            }
        }

        final Evenement evenementToSave = this.reattach(evenement);

        // on applique les updators.
        for (final EvenementBeforeSaveUpdator updator : this.updators)
        {
            if (updator.support(evenementToSave))
            {
                updator.update(evenementToSave,
                               this);
            }
        }

        final EvenementSuivi evenementSuivi = this.evtSuiviFactory.getInitializedObject();
        evenementSuivi.setEvenement(evenementToSave);
        evenementToSave.getModifs().add(evenementSuivi);
        return super.save(evenementToSave);
    }

    /**
     * {@inheritDoc}
     */
    public Evenement getVisiteMonitoring(final Essai essai)
    {
        final EvenementSearchCriteria criteria = new EvenementSearchCriteria();
        criteria.setEssai(essai);
        criteria.setTypeEvenement(TypeEvenement.VISITE);
        criteria.setTypeVisite(TypeVisite.MISE_EN_PLACE);
        final List<Evenement> results = this.getAll(criteria);
        if (!results.isEmpty())
        {
            return results.get(0);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Evenement> getNextEvenements()
    {
        List<Evenement> result = new ArrayList<Evenement>();

        // Récupération des essais associés à l'utilisateur
        final List<Essai> essais = this.essaiService.getAll();

        if (!essais.isEmpty())
        {
            // Récupération des événements qui concernent ces essais
            final EvenementSearchCriteria criteria = new EvenementSearchCriteria();
            criteria.setActiveOrder("dateDebut");
            criteria.setEssais(essais);

            final Calendar dateDebut = Calendar.getInstance();
            // on enleve les heures
            dateDebut.add(Calendar.HOUR_OF_DAY,
                          -dateDebut.get(Calendar.HOUR_OF_DAY + 1));
            criteria.setDateDebut(dateDebut);

            final Calendar dateFin = Calendar.getInstance();
            dateFin.add(Calendar.DAY_OF_MONTH,
                        EvenementServiceImpl.NB_JOURS_RECUP_EVT);
            criteria.setDateFin(dateFin);

            result = this.getAll(criteria);
        }

        return result;
    }

    /**
     * Méthode en charge de gérer une exception sur le traitement des heures minutes.
     * @param calendar Calendar ayant provoqué une exception.
     */
    private void handleExceptionHeures(final Calendar calendar)
    {
        // On remet HEURE + MINUTE à 0
        calendar.set(Calendar.HOUR_OF_DAY,
                     0);
        calendar.set(Calendar.MINUTE,
                     0);

        throw new ValidationException("evenement.heure",
                                      new String[]
                                      {"incorrect" });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Evenement> getAll()
    {
        final List<Evenement> essais = super.getAll();
        // Purge des essais par rapport aux habilitations
        this.habilitationHandler.purge(essais);
        return essais;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Evenement> getAllWithoutPurge(final EvenementSearchCriteria criteria)
    {
        final List<Evenement> essais = super.getAll(criteria);
        return essais;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Evenement> getAll(final SearchCriteria criteria)
    {
        final List<Evenement> essais = super.getAll(criteria);
        // Purge des essais par rapport aux habilitations
        this.habilitationHandler.purge(essais);
        return essais;
    }

    /**
     * Setter pour evtSuiviFactory.
     * @param evtSuiviFactory Le evtSuiviFactory à écrire.
     */
    public void setEvtSuiviFactory(final SuiviFactory<EvenementSuivi> evtSuiviFactory)
    {
        this.evtSuiviFactory = evtSuiviFactory;
    }

    /**
     * Setter pour essaiService.
     * @param essaiService Le essaiService à écrire.
     */
    public void setEssaiService(final EssaiService essaiService)
    {
        this.essaiService = essaiService;
    }

    /**
     * Getter sur updators.
     * @return Retourne le updators.
     */
    public List<EvenementBeforeSaveUpdator> getUpdators()
    {
        return this.updators;
    }

    /**
     * Setter pour updators.
     * @param updators le updators à écrire.
     */
    public void setUpdators(final List<EvenementBeforeSaveUpdator> updators)
    {
        this.updators = updators;
    }

    /**
     * Getter sur essaiService.
     * @return Retourne le essaiService.
     */
    public EssaiService getEssaiService()
    {
        return this.essaiService;
    }

    /**
     * Setter pour habilitationHandler.
     * @param habilitationHandler le habilitationHandler à écrire.
     */
    public void setHabilitationHandler(final HabilitationHandler<Evenement> habilitationHandler)
    {
        this.habilitationHandler = habilitationHandler;
    };

}
