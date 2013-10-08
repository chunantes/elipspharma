package fr.pharma.eclipse.utils;

import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Classe utilitaire pour les jeux de tests de type Pharmacie.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public final class PharmacieUtils {
    /**
     * Constructeur.
     */
    private PharmacieUtils() {
        super();
    }

    /**
     * Méthode de création d'un objet Pharmacie.
     * @param id Identifiant technique.
     * @return Un objet Pharmacie.
     */
    public static Pharmacie makePharmacieTest(final long id) {
        return PharmacieUtils.makePharmacieTest(id, "nom_" + id);
    }

    /**
     * Méthode de création d'un objet Pharmacie.
     * @param id Identifiant technique.
     * @param nom Nom.
     * @return Un objet Pharmacie.
     */
    public static Pharmacie makePharmacieTest(final long id,
                                              final String nom) {
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(id);
        pharmacie.setNom(nom);
        return pharmacie;
    }
}
