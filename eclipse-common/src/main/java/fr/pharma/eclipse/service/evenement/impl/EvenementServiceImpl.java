package fr.pharma.eclipse.service.evenement.impl;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.dao.search.AclSearchDao;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.evenement.EvenementSearchCriteria;
import fr.pharma.eclipse.domain.enums.evenement.TypeEvenement;
import fr.pharma.eclipse.domain.enums.evenement.TypeVisite;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.domain.model.suivi.evenement.EvenementSuivi;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.evenement.EvenementService;
import fr.pharma.eclipse.service.evenement.updator.EvenementBeforeSaveUpdator;
import fr.pharma.eclipse.utils.constants.EclipseConstants;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateMidnight;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Classe d'implémentation du service de gestion des événements.
 *
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EvenementServiceImpl extends GenericServiceImpl<Evenement> implements EvenementService {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1572333342937082499L;

    /**
     * Constante contenant le nb de jours pour la récupération des événements à
     * venir.
     */
    protected static final int NB_JOURS_RECUP_EVT = 5;

    /**
     * Factory de suivi de evenement.
     */
    @Resource(name = "evenementSuiviFactory")
    private SuiviFactory<EvenementSuivi> evtSuiviFactory;

    /**
     * Service de gestion des essais.
     */
    @Resource(name = "essaiService")
    private EssaiService essaiService;

    /**
     * DAO de recherche des ACLs.
     */
    @Autowired
    private AclSearchDao aclSearchDao;

    /**
     * Updators.
     */
    private List<EvenementBeforeSaveUpdator> updators;

    /**
     * Requête de récupération des événements.
     */
    protected static final String SELECT_EVENEMENTS = "select evt.* from essai e, evenement evt" + " where e.id=evt.id_essai" + " and evt.dateDebut>=? and evt.dateDebut<=? and e.id in {0}"
            + " order by dateDebut";

    /**
     * Constructeur.
     *
     * @param evenementDao Dao de gestion des événements.
     */
    public EvenementServiceImpl(final GenericDao<Evenement> evenementDao) {
        super(evenementDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Evenement save(final Evenement evenement) {
        final String heureDebut = evenement.getHeureDebut();
        final Calendar dateDebut = evenement.getDateDebut();
        final String heureFin = evenement.getHeureFin();
        final Calendar dateFin = (Calendar) dateDebut.clone();
        evenement.setDateFin(dateFin);
        if (StringUtils.isNotEmpty(heureDebut)) {
            try {
                final String[] tokens = heureDebut.split(EclipseConstants.COLON);
                dateDebut.set(Calendar.HOUR_OF_DAY, Integer.valueOf(tokens[0]));
                dateDebut.set(Calendar.MINUTE, Integer.valueOf(tokens[1]));
            } catch (final ArrayIndexOutOfBoundsException e) {
                evenement.setHeureDebut(null);
                this.handleExceptionHeures(dateDebut);
            } catch (final NumberFormatException e) {
                evenement.setHeureDebut(null);
                this.handleExceptionHeures(dateDebut);
            }
        }
        if (StringUtils.isNotEmpty(heureFin)) {
            try {
                final String[] tokens = heureFin.split(EclipseConstants.COLON);
                dateFin.set(Calendar.HOUR_OF_DAY, Integer.valueOf(tokens[0]));
                dateFin.set(Calendar.MINUTE, Integer.valueOf(tokens[1]));
            } catch (final ArrayIndexOutOfBoundsException e) {
                evenement.setHeureFin(null);
                this.handleExceptionHeures(dateFin);
            } catch (final NumberFormatException e) {
                evenement.setHeureFin(null);
                this.handleExceptionHeures(dateFin);
            }
        }

        final Evenement evenementToSave = this.reattach(evenement);

        // on applique les updators.
        for (final EvenementBeforeSaveUpdator updator : this.updators) {
            if (updator.support(evenementToSave)) {
                updator.update(evenementToSave, this);
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
    @Override
    public Evenement getVisiteMonitoring(final Essai essai) {
        final EvenementSearchCriteria criteria = new EvenementSearchCriteria();
        criteria.setEssai(essai);
        criteria.setTypeEvenement(TypeEvenement.VISITE);
        criteria.setTypeVisite(TypeVisite.MISE_EN_PLACE);
        final List<Evenement> results = this.getAll(criteria);
        if (!results.isEmpty()) {
            return results.get(0);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Evenement> getNextEvenements() {
        final Date dateDebut = new DateMidnight().toDate();
        final Date dateFin = new DateMidnight().plusDays(EvenementServiceImpl.NB_JOURS_RECUP_EVT + 1).toDate();

        final List<Long> idsEssais = this.aclSearchDao.findIdsEssais();
        final String paramIdsEssais = Arrays.toString(idsEssais.toArray(new Object[idsEssais.size()])).replace("[", "(").replace("]", ")");

        final List<Evenement> result = this.executeSQLQuery(MessageFormat.format(EvenementServiceImpl.SELECT_EVENEMENTS, paramIdsEssais), new Object[]{dateDebut, dateFin});

        // Chargement des essais pour eviter un LazyLoadingException dans
        // evenements.xhtml
        for (final Evenement evenement : result) {
            evenement.getEssai().getNom();
        }

        return result;
    }

    /**
     * Méthode en charge de gérer une exception sur le traitement des heures
     * minutes.
     *
     * @param calendar Calendar ayant provoqué une exception.
     */
    private void handleExceptionHeures(final Calendar calendar) {
        // On remet HEURE + MINUTE à 0
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);

        throw new ValidationException("evenement.heure", new String[]{"incorrect"});
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Evenement> getAll() {
        return this.getAll(new EvenementSearchCriteria());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Evenement> getAllWithoutPurge(final EvenementSearchCriteria criteria) {
        final List<Evenement> essais = super.getAll(criteria);
        return essais;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Evenement> getAll(final SearchCriteria criteria) {
        final EvenementSearchCriteria evenementSearchCriteria = (EvenementSearchCriteria) criteria;

        if (evenementSearchCriteria.getIdsEssais()==null || evenementSearchCriteria.getIdsEssais().isEmpty()) {
            evenementSearchCriteria.setIdsEssais(this.aclSearchDao.findIdsEssais());
        } else {
            List<Long> idACL = this.aclSearchDao.findIdsEssais();
            Iterator<Long> idsEssaisIT = evenementSearchCriteria.getIdsEssais().iterator();
            while (idsEssaisIT.hasNext()) {
                Long id = idsEssaisIT.next();
                if (!idACL.contains(id)) {
                    evenementSearchCriteria.getIdsEssais().remove(id);
                }
            }
        }

        return super.getAll(evenementSearchCriteria);
    }

    /**
     * Setter pour evtSuiviFactory.
     *
     * @param evtSuiviFactory Le evtSuiviFactory à écrire.
     */
    public void setEvtSuiviFactory(final SuiviFactory<EvenementSuivi> evtSuiviFactory) {
        this.evtSuiviFactory = evtSuiviFactory;
    }

    /**
     * Setter pour essaiService.
     *
     * @param essaiService Le essaiService à écrire.
     */
    public void setEssaiService(final EssaiService essaiService) {
        this.essaiService = essaiService;
    }

    /**
     * Getter sur updators.
     *
     * @return Retourne le updators.
     */
    public List<EvenementBeforeSaveUpdator> getUpdators() {
        return this.updators;
    }

    /**
     * Setter pour updators.
     *
     * @param updators le updators à écrire.
     */
    public void setUpdators(final List<EvenementBeforeSaveUpdator> updators) {
        this.updators = updators;
    }

    /**
     * Getter sur essaiService.
     *
     * @return Retourne le essaiService.
     */
    public EssaiService getEssaiService() {
        return this.essaiService;
    }

    /**
     * Setter pour aclSearchDao.
     *
     * @param aclSearchDao Le aclSearchDao à écrire.
     */
    public void setAclSearchDao(final AclSearchDao aclSearchDao) {
        this.aclSearchDao = aclSearchDao;
    }

}
