package fr.pharma.eclipse.service.ordonnancier.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.ordonnancier.OrdonnancierSearchCriteria;
import fr.pharma.eclipse.domain.criteria.stock.MvtStockSearchCriteria;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.ordonnancier.OrdonnancierFabReconst;
import fr.pharma.eclipse.domain.model.stock.PreparationEntree;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;
import fr.pharma.eclipse.service.stock.ApprovisionnementService;

/**
 * Classe d'implémentation du service de gestion des ordonnanciers de fabrication /
 * reconstitution.
 
 * @version $Revision$ $Date$
 */
public class OrdonnancierFabReconstServiceImpl
    extends OrdonnancierServiceImpl<OrdonnancierFabReconst>
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -8558593821640475370L;

    /**
     * Factory d'ordonnancier de fabrication/reconstitution.
     */
    @Resource(name = "ordonnancierFabReconstFactory")
    private SuiviFactory<OrdonnancierFabReconst> ordoFactory;

    /**
     * Service de gestion des PreparationEntree.
     */
    @Resource(name = "approvisionnementService")
    private ApprovisionnementService<PreparationEntree> approvisionnementService;

    /**
     * Constructeur.
     * @param ordoDao Dao de gestion des ordonnanciers de fabrication/reconstitution.
     */
    public OrdonnancierFabReconstServiceImpl(final GenericDao<OrdonnancierFabReconst> ordoDao)
    {
        super(ordoDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OrdonnancierFabReconst calculerOrdonnancier(final OrdonnancierSearchCriteria criteria)
    {
        final Pharmacie pharmacie = criteria.getPharmacie();

        // Récupération des elementsToCheck éligibles pour l'ordonnancier
        final MvtStockSearchCriteria crit = new MvtStockSearchCriteria();
        crit.setDateDebut(criteria.getDateDebut());
        crit.setDateFin(criteria.getDateFin());
        crit.setPharmacie(pharmacie);
        crit.setActiveOrder("dateCreation");
        crit.setTypeMouvement(TypeMvtStock.PREPARATION_ENTREE);
        crit.setNotNullNumOrdonnancier(true);
        crit.setAscending(true);

        final List<PreparationEntree> preparations = this.approvisionnementService.getAll(crit);

        if (preparations.isEmpty())
        {
            throw new ValidationException("ordonnancier.eltsToCheck",
                                          new String[]
                                          {"empty" });
        }
        else
        {
            // Création de l'ordonnancier
            final OrdonnancierFabReconst ordonnancier = this.ordoFactory.getInitializedObject();
            ordonnancier.setDateDebut(criteria.getDateDebut());
            ordonnancier.setDateFin(criteria.getDateFin());
            ordonnancier.setPharmacie(pharmacie);

            // Ajout des elementsToCheck avec les informations de l'ordonnancier
            for (final PreparationEntree p : preparations)
            {
                p.setOrdonnancier(ordonnancier);
                ordonnancier.getElementsToCheck().add(p);
            }

            // Sauvegarde de l'ordonnancier
            return this.save(ordonnancier);
        }
    }

    /**
     * Setter pour ordoFactory.
     * @param ordoFactory Le ordoFactory à écrire.
     */
    public void setOrdoFactory(final SuiviFactory<OrdonnancierFabReconst> ordoFactory)
    {
        this.ordoFactory = ordoFactory;
    }

    /**
     * Getter pour approvisionnementService.
     * @return Le approvisionnementService
     */
    public ApprovisionnementService<PreparationEntree> getApprovisionnementService()
    {
        return this.approvisionnementService;
    }

    /**
     * Setter pour approvisionnementService.
     * @param approvisionnementService Le approvisionnementService à écrire.
     */
    public void setApprovisionnementService(final ApprovisionnementService<PreparationEntree> approvisionnementService)
    {
        this.approvisionnementService = approvisionnementService;
    }

    /**
     * Getter pour ordoFactory.
     * @return Le ordoFactory
     */
    public SuiviFactory<OrdonnancierFabReconst> getOrdoFactory()
    {
        return this.ordoFactory;
    }

}
