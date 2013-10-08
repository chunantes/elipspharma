package fr.pharma.eclipse.service.stockage;

import java.util.List;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Interface de service de gestion de pharmacie.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface PharmacieService extends GenericService<Pharmacie> {
    /**
     * Méthode en charge de supprimer un stockage dans la liste des stockages de
     * la pharmacie.
     * @param pharmacie Pharmacie.
     * @param stockage Stockage à supprimer.
     */
    void removeStockage(final Pharmacie pharmacie,
                        final Stockage stockage);

    /**
     * Méthode en charge de récupérer les pharmacies sans tenir compte des ACLs.
     * @param searchCriteria Critère de recherche.
     * @return Liste des pharmacies.
     */
    List<Pharmacie> getAllSansAcl(final SearchCriteria searchCriteria);

}
