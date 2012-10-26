package fr.pharma.eclipse.service.stockage;

import java.util.List;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Interface de service de gestion de pharmacie.
 
 * @version $Revision$ $Date$
 */
public interface PharmacieService
    extends GenericService<Pharmacie>
{
    /**
     * Méthode en charge de supprimer un stockage dans la liste des stockages de la pharmacie.
     * @param pharmacie Pharmacie.
     * @param stockage Stockage à supprimer.
     */
    void removeStockage(final Pharmacie pharmacie,
                        final Stockage stockage);

    /**
     * Méthode générique de listing de toutes les pharmacies en proposant de les filtrer selon les
     * habilitations.
     * @param filtre Filtre à appliquer.
     * @param criteria Critère de recherche.
     * @return La liste de pharmacie.
     */
    List<Pharmacie> getAll(final SearchCriteria criteria,
                           final boolean filtre);

}
