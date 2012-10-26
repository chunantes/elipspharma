package fr.pharma.eclipse.component.accueil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.domain.model.alerte.Alerte;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.service.actualite.ActualiteService;
import fr.pharma.eclipse.service.alerte.AlerteService;
import fr.pharma.eclipse.service.evenement.EvenementService;
import fr.pharma.eclipse.utils.FacesUtils;

/**
 * Manager de gestion de la page d'accueil (actualités / alertes / événements).
 
 * @version $Revision$ $Date$
 */
public class AccueilManager
    implements Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -8600795584842955670L;

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(AccueilManager.class);

    /**
     * Liste des essais des actualités.
     */
    private List<Essai> essaisActualites = new ArrayList<Essai>();

    /**
     * Liste des prochains événements.
     */
    private List<Evenement> evenements = new ArrayList<Evenement>();

    /**
     * Liste des alertes.
     */
    private List<Alerte> alertes = new ArrayList<Alerte>();

    /**
     * Essai sélectionné.
     */
    private Essai essaiSelected;

    /**
     * Utils Faces.
     */
    @Resource(name = "facesUtils")
    private FacesUtils facesUtils;

    /**
     * Evenement sélectionné.
     */
    private Evenement evenementSelected;

    /**
     * Service de gestion des actualités.
     */
    @Resource(name = "actualiteService")
    private ActualiteService actualiteService;

    /**
     * Service de gestion des événements.
     */
    @Resource(name = "evenementService")
    private EvenementService evenementService;

    /**
     * Service de gestion des alertes.
     */
    @Resource(name = "alerteService")
    private AlerteService alerteService;

    /**
     * Méthode en charge de construire les informations de la page d'accueil.
     */
    public void buildInfosAccueil()
    {
        try
        {
            // Construction des alertes uniquement pour le profil Pharmacien et Admin
            this.buildAlertes();

            // Construction des actualités
            this.buildActualites();

            // Construction des prochains événements
            this.buildNextEvenements();
        }
        catch (final HibernateException e)
        {
            this.log.error("Erreur lors du chargement des données de l'accueil",
                           e);
            this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR,
                                       "accueil.chargement.ko");
        }
    }

    /**
     * Méthode en charge de construire les informations des actualités.
     */
    private void buildActualites()
    {
        this.setEssaisActualites(this.actualiteService.getLastEssais());
    }

    /**
     * Méthode en charge de construire les prochains événements.
     */
    private void buildNextEvenements()
    {
        this.setEvenements(this.evenementService.getNextEvenements());
    }

    /**
     * Méthode en charge de construire les alertes.
     */
    private void buildAlertes()
    {
        this.setAlertes(this.alerteService.getAlertes());
    }

    /**
     * Setter pour actualiteService.
     * @param actualiteService Le actualiteService à écrire.
     */
    public void setActualiteService(final ActualiteService actualiteService)
    {
        this.actualiteService = actualiteService;
    }

    /**
     * Getter pour essaisActualites.
     * @return Le essaisActualites
     */
    public List<Essai> getEssaisActualites()
    {
        return this.essaisActualites;
    }

    /**
     * Setter pour essaisActualites.
     * @param essaisActualites Le essaisActualites à écrire.
     */
    public void setEssaisActualites(final List<Essai> essaisActualites)
    {
        this.essaisActualites = essaisActualites;
    }

    /**
     * Getter pour essaiSelected.
     * @return Le essaiSelected
     */
    public Essai getEssaiSelected()
    {
        return this.essaiSelected;
    }

    /**
     * Setter pour essaiSelected.
     * @param essaiSelected Le essaiSelected à écrire.
     */
    public void setEssaiSelected(final Essai essaiSelected)
    {
        this.essaiSelected = essaiSelected;
    }

    /**
     * Getter pour evenements.
     * @return Le evenements
     */
    public List<Evenement> getEvenements()
    {
        return this.evenements;
    }

    /**
     * Setter pour evenements.
     * @param evenements Le evenements à écrire.
     */
    public void setEvenements(final List<Evenement> evenements)
    {
        this.evenements = evenements;
    }

    /**
     * Setter pour evenementService.
     * @param evenementService Le evenementService à écrire.
     */
    public void setEvenementService(final EvenementService evenementService)
    {
        this.evenementService = evenementService;
    }

    /**
     * Getter pour evenementSelected.
     * @return Le evenementSelected
     */
    public Evenement getEvenementSelected()
    {
        return this.evenementSelected;
    }

    /**
     * Setter pour evenementSelected.
     * @param evenementSelected Le evenementSelected à écrire.
     */
    public void setEvenementSelected(final Evenement evenementSelected)
    {
        this.evenementSelected = evenementSelected;
    }

    /**
     * Setter pour alerteService.
     * @param alerteService Le alerteService à écrire.
     */
    public void setAlerteService(final AlerteService alerteService)
    {
        this.alerteService = alerteService;
    }

    /**
     * Getter pour alertes.
     * @return Le alertes
     */
    public List<Alerte> getAlertes()
    {
        return this.alertes;
    }

    /**
     * Setter pour alertes.
     * @param alertes Le alertes à écrire.
     */
    public void setAlertes(final List<Alerte> alertes)
    {
        this.alertes = alertes;
    }

    /**
     * Setter pour facesUtils.
     * @param facesUtils Le facesUtils à écrire.
     */
    public void setFacesUtils(final FacesUtils facesUtils)
    {
        this.facesUtils = facesUtils;
    }

}
