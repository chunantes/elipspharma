package fr.pharma.eclipse.utils;

import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;

/**
 * Classe utilitaire pour les données de tests relatives aux beans Personne.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public final class PersonneUtils {
    /**
     * Constructeur privé.
     */
    private PersonneUtils() {
        super();
    }

    /**
     * Méthode de création d'un bean Pharmacien.
     * @param id Identifiant technique.
     * @return Un bean Pharmacien.
     */
    public static Pharmacien makePharmacien(final long id) {
        final Pharmacien personne = new Pharmacien();
        personne.setId(1L);
        personne.setType(TypePersonne.PHARMACIEN);
        return personne;
    }

    /**
     * Méthode de création d'un bean Investigateur.
     * @param id Identifiant technique.
     * @return Un bean Investigateur.
     */
    public static Investigateur makeInvestigateur(final long id) {
        return PersonneUtils.makeInvestigateur(id, null, null, null);
    }

    /**
     * Méthode de création d'un bean Investigateur.
     * @param id Identifiant technique.
     * @param titre Titre.
     * @param prenom Prénom.
     * @param nom Nom.
     * @return Un bean Investigateur.
     */
    public static Investigateur makeInvestigateur(final long id,
                                                  final String titre,
                                                  final String prenom,
                                                  final String nom) {
        final Investigateur personne = new Investigateur();
        personne.setId(1L);
        personne.setType(TypePersonne.INVESTIGATEUR);
        personne.setNom(nom);
        personne.setPrenom(prenom);
        personne.setTitre(titre);
        return personne;
    }
}
