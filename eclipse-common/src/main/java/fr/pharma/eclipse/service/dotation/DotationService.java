package fr.pharma.eclipse.service.dotation;

import java.util.List;

import fr.pharma.eclipse.domain.model.dotation.Dotation;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Interface de service de gestion de dotation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface DotationService extends GenericService<Dotation> {
    /**
     * Méthode en charge d'enregistrer le traitement d'une demande de dotation.
     * @param dotation Dotation.
     * @param lignesStock Lignes de stock complétées par l'utilisateur pour
     * traiter la demande.
     */
    void saveTraitementDotation(final Dotation dotation,
                                final List<LigneStock> lignesStock);
}
