package fr.pharma.eclipse.service.stock;

import java.util.List;

import fr.pharma.eclipse.domain.enums.stock.RaisonSortie;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.stock.ResultSortie;
import fr.pharma.eclipse.domain.model.stock.Sortie;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Interface de service des sorties de stock.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface SortieService {
    /**
     * Méthode en charge de créer les mouvements de sortie de stock relatif à
     * une sortie.
     * @param raisonSortie Raison de la sortie.
     * @param commentaireRaison COmmentaire sur la raison de la sortie.
     * @param type Type de mouvement de sortie.
     * @param nomSocieteTransport Nom de la société de transport.
     * @param referenceEnvoi Référence de l'envoi.
     * @param commentaire Commentaire à affecter sur les sorties.
     * @param sorties Sorties.
     * @param dispensation La dispensation.
     * @return Résultat de la sortie.
     */
    ResultSortie save(final RaisonSortie raisonSortie,
                      final String commentaireRaison,
                      final TypeMvtStock type,
                      final String nomSocieteTransport,
                      final String referenceEnvoi,
                      final String commentaire,
                      final Pharmacie pharmacieDest,
                      final List<Sortie> sorties,
                      final Dispensation dispensation);
}
