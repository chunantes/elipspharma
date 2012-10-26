package fr.pharma.eclipse.service.alerte.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import fr.pharma.eclipse.domain.enums.RolePersonne;
import fr.pharma.eclipse.domain.enums.alerte.TypeAlerte;
import fr.pharma.eclipse.domain.model.alerte.Alerte;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.predicate.alerte.AlerteEssaiVisiblePredicate;
import fr.pharma.eclipse.service.alerte.AlerteService;
import fr.pharma.eclipse.service.alerte.builder.AlerteBuilder;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.stockage.PharmacieService;
import fr.pharma.eclipse.service.user.UserService;

/**
 * Classe d'implémentation du service de gestion des alertes.
 
 * @version $Revision$ $Date$
 */
public class AlerteServiceImpl
    implements AlerteService, Serializable
{
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
     * {@inheritDoc}
     */
    @Override
    public List<Alerte> getAlertes()
    {
        final List<Alerte> alertes = new ArrayList<Alerte>();

        // Récupération de la liste des essais visibles de l'utilisateur
        final List<Essai> essais = this.essaiService.getAll();
        this.calculAlertes(essais,
                           alertes);

        return alertes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Alerte> getAlertes(final Essai essai)
    {
        final List<Alerte> alertes = new ArrayList<Alerte>();

        // Calcul des alertes de l'essai s'il a été enregistré
        if (essai.getId() != null)
        {
            final List<Essai> essais = new ArrayList<Essai>();
            essais.add(essai);
            this.calculAlertes(essais,
                               alertes);
        }

        return alertes;
    }

    /**
     * Méthode en charge de calculer les alertes des essais.
     * @param essais Essais à traiter dans la recherche des alertes.
     * @param alertes Liste des alertes à compléter.
     */
    public void calculAlertes(final List<Essai> essais,
                              final List<Alerte> alertes)
    {
        if (this.launchCalculAlertes())
        {
            // Récupération des essais visibles pour les alertes
            CollectionUtils.filter(essais,
                                   new AlerteEssaiVisiblePredicate());

            // Si la liste des essais est non vide
            if (!essais.isEmpty())
            {
                // Récupération de la liste des pharmacies visibles de l'utilisateur
                final List<Pharmacie> pharmacies = this.pharmacieService.getAll();

                for (final Map.Entry<TypeAlerte, AlerteBuilder> builder : this.builders
                        .entrySet())
                {
                    builder.getValue().build(essais,
                                             pharmacies,
                                             alertes);
                }
            }
        }
    }

    /**
     * Méthode en charge de déterminer le lancement du calcul des alertes.
     * @return Résultat du lancement du calcul des alertes.
     */
    public Boolean launchCalculAlertes()
    {
        // Lancement du calcul des alertes uniquement pour les profils ADMIN + pharmaciens
        final RolePersonne role = this.userService.getUser().getRole();
        return role.equals(RolePersonne.ADMIN)
               || role.name().contains("PHARMACIEN");
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
     * Setter pour pharmacieService.
     * @param pharmacieService Le pharmacieService à écrire.
     */
    public void setPharmacieService(final PharmacieService pharmacieService)
    {
        this.pharmacieService = pharmacieService;
    }

    /**
     * Setter pour builders.
     * @param builders Le builders à écrire.
     */
    public void setBuilders(final Map<TypeAlerte, AlerteBuilder> builders)
    {
        this.builders = builders;
    }

    /**
     * Setter pour userService.
     * @param userService Le userService à écrire.
     */
    public void setUserService(final UserService userService)
    {
        this.userService = userService;
    }

}
