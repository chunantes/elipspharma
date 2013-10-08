package fr.pharma.eclipse.utils;

import fr.pharma.eclipse.domain.enums.TypePromoteur;
import fr.pharma.eclipse.domain.model.acteur.Promoteur;

/**
 * Classe utilitaire pour le traitement des promoteurs dans les tests.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public final class PromoteurUtils {
    /**
     * Constructeur privé.
     */
    private PromoteurUtils() {
        super();
    }

    /**
     * Méthode de création d'un promoteur pour les tests.
     * @param id Identifiant du promoteur.
     * @param typePromoteur Type du promoteur.
     * @return Un promoteur initialisé pour les tests.
     */
    public static Promoteur makePromoteurTest(final Long id,
                                              final TypePromoteur typePromoteur) {
        final Promoteur promoteur = new Promoteur();
        promoteur.setId(id);
        promoteur.setType(typePromoteur);
        return promoteur;
    }
}
