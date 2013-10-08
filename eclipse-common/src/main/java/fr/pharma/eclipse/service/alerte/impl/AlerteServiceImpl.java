package fr.pharma.eclipse.service.alerte.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Hibernate;

import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.enums.RolePersonne;
import fr.pharma.eclipse.domain.enums.alerte.TypeAlerte;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.alerte.Alerte;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.EssaiAlerte;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.alerte.AlerteService;
import fr.pharma.eclipse.service.alerte.builder.AlerteBuilder;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.stockage.PharmacieService;
import fr.pharma.eclipse.service.user.UserService;

/**
 * Classe d'implémentation du service de gestion des alertes.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AlerteServiceImpl implements AlerteService, Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -2692226232982789962L;

    /**
     * Service de gestion des essais.
     */
    @Resource(name = "essaiService")
    private EssaiService essaiService;

    /**
     * Service de gestion des pharmacies.
     */
    @Resource(name = "pharmacieService")
    private PharmacieService pharmacieService;

    /**
     * Map des builders d'alertes.
     */
    @Resource(name = "mapAlerteBuilders")
    private Map<TypeAlerte, AlerteBuilder> builders;

    /**
     * Service de gestion des utilisateurs.
     */
    @Resource(name = "userService")
    private UserService userService;

    /**
     * Tableau des colonnes d'essais à récupérer.
     */
    protected static final String[] ESSAI_COLS = new String[]{"id", "numInterne", "nom", "finEtude", "cloture" };

    protected static final Map<String, Object> scalars = new HashMap<String, Object>();
    static {
        AlerteServiceImpl.scalars.put("finEtude", Hibernate.CALENDAR);
        AlerteServiceImpl.scalars.put("cloture", Hibernate.CALENDAR);
    }

    /**
     * Requête SQL récupérant les essais entrant dans le calcul des alertes pour
     * un admin.
     */
    protected static final String SELECT_ESSAIS_FOR_ADMIN = "select e.id, e.numInterne, e.nom, d.finEtude, d.cloture from essai e, essai_detail_dates d" + " where e.id=d.id_essai"
                                                            + " and e.alerteActive is true and etat<>'" + EtatEssai.ARCHIVE.name() + "'";

    /**
     * Requête SQL récupérant les essais entrant dans le calcul des alertes pour
     * un pharmacien.
     */
    private static final String SELECT_ESSAIS_FOR_PHARMACIEN = "select distinct(e.id), e.numInterne, e.nom, d.finEtude, d.cloture, e.id_pharma "
                                                               + "from essai e, essai_detail_dates d, pharmacien_pharmacie p, essai_detail_pharma_pharmacie ep "
                                                               + "where e.id=d.id_essai and e.alerteActive is true and etat<>'" + EtatEssai.ARCHIVE.name() + "'"
                                                               + "and (e.id_pharma = p.id_pharmacie or (e.id=ep.id_detail_pharma and p.id_pharmacie=ep.id_pharmacie)) "
                                                               + "and (p.id_pharmacien = ?)";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Alerte> getAlertes() {
        final List<Alerte> alertes = new ArrayList<Alerte>();

        final Personne personne = this.userService.getPersonne();
        List<EssaiAlerte> essaisAlerte = new ArrayList<EssaiAlerte>();

        if (personne.getIsAdmin()) {

            @SuppressWarnings("unchecked")
            final List<EssaiAlerte> eaTemp =
                (List<EssaiAlerte>) this.essaiService.executeSQLQuery(AlerteServiceImpl.SELECT_ESSAIS_FOR_ADMIN, null, AlerteServiceImpl.ESSAI_COLS, EssaiAlerte.class,
                                                                      AlerteServiceImpl.scalars);
            essaisAlerte = eaTemp;

        } else {
            @SuppressWarnings("unchecked")
            final List<EssaiAlerte> eaTemp =
                (List<EssaiAlerte>) this.essaiService.executeSQLQuery(AlerteServiceImpl.SELECT_ESSAIS_FOR_PHARMACIEN, new Object[]{personne.getId() },
                                                                      AlerteServiceImpl.ESSAI_COLS, EssaiAlerte.class, AlerteServiceImpl.scalars);
            essaisAlerte = eaTemp;
        }

        this.calculAlertes(essaisAlerte, alertes);

        return alertes;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Alerte> getAlertes(final Essai essai) {
        final List<Alerte> alertes = new ArrayList<Alerte>();

        // Calcul des alertes de l'essai s'il a été enregistré
        if (essai.getId() != null) {
            final List<EssaiAlerte> essais = new ArrayList<EssaiAlerte>();
            final EssaiAlerte essaiAlerte = new EssaiAlerte();
            essaiAlerte.setId(essai.getId());
            essaiAlerte.setFinEtude(essai.getDetailDates().getFinEtude());
            essaiAlerte.setCloture(essai.getDetailDates().getCloture());
            essaiAlerte.setNom(essai.getNom());
            essaiAlerte.setNumInterne(essai.getNumInterne());
            essais.add(essaiAlerte);
            this.calculAlertes(essais, alertes);
        }

        return alertes;
    }

    /**
     * Méthode en charge de calculer les alertes des essais.
     * @param essais Essais à traiter dans la recherche des alertes.
     * @param alertes Liste des alertes à compléter.
     */
    public void calculAlertes(final List<EssaiAlerte> essais,
                              final List<Alerte> alertes) {
        if (this.isAdminOrPharmacien()) {
            // Si la liste des essais est non vide
            if (!essais.isEmpty()) {
                // Récupération de la liste des pharmacies visibles de
                // l'utilisateur
                final List<Pharmacie> pharmacies = this.pharmacieService.getAll();

                for (final Map.Entry<TypeAlerte, AlerteBuilder> builder : this.builders.entrySet()) {
                    builder.getValue().build(essais, pharmacies, alertes);
                }
            }
        }
    }

    /**
     * @return true si user est admin ou pharmacian.
     */
    protected Boolean isAdminOrPharmacien() {
        // Lancement du calcul des alertes uniquement pour les profils ADMIN +
        // pharmaciens
        final RolePersonne role = this.userService.getUser().getRole();
        return role.equals(RolePersonne.ADMIN) || role.name().contains("PHARMACIEN");
    }

    /**
     * Setter pour essaiService.
     * @param essaiService Le essaiService à écrire.
     */
    public void setEssaiService(final EssaiService essaiService) {
        this.essaiService = essaiService;
    }

    /**
     * Setter pour pharmacieService.
     * @param pharmacieService Le pharmacieService à écrire.
     */
    public void setPharmacieService(final PharmacieService pharmacieService) {
        this.pharmacieService = pharmacieService;
    }

    /**
     * Setter pour builders.
     * @param builders Le builders à écrire.
     */
    public void setBuilders(final Map<TypeAlerte, AlerteBuilder> builders) {
        this.builders = builders;
    }

    /**
     * Setter pour userService.
     * @param userService Le userService à écrire.
     */
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }

}
