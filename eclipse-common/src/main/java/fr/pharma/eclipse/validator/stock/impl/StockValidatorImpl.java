package fr.pharma.eclipse.validator.stock.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stock.PreparationEntree;
import fr.pharma.eclipse.domain.model.stock.ReceptionLot;
import fr.pharma.eclipse.domain.model.stock.Sortie;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.predicate.stock.SortiePredicate;
import fr.pharma.eclipse.validator.stock.StockValidator;

/**
 * Classe d'implémentation du validator de gestion de stock.
 
 * @version $Revision$ $Date$
 */
public class StockValidatorImpl
    implements StockValidator, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3992017483426893853L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateLignesStockSortie(final List<Sortie> sorties,
                                          final Sortie currentSortie)
    {
        // Vérification que la sortie ne soit pas vide (aucune ligne).
        if (currentSortie.getLignesStock().isEmpty()
            || currentSortie.getLignesStockCompletees().isEmpty())
        {
            throw new ValidationException("sortie.enregistrement",
                                          new String[]
                                          {"empty" });
        }

        // Vérification que la sortie n'interfère pas avec la liste des sorties existantes
        // L'interférence se situe au niveau produit + conditionnement

        final Sortie result = (Sortie) CollectionUtils.find(sorties,
                                                            new SortiePredicate(currentSortie));

        if (result != null
            && result != currentSortie)
        {
            throw new ValidationException("sortie.enregistrement",
                                          new String[]
                                          {"alreadyExists" });
        }

        // Vérification de la saisie des quantités à sortir
        final List<LigneStock> lignesStock = currentSortie.getLignesStock();
        this.validateQteLignesStock(lignesStock);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateQteLignesStock(final List<LigneStock> lignesStock)
    {
        for (final LigneStock ligneStock : lignesStock)
        {
            if (ligneStock.getQteASortir() != null
                && ligneStock.getQteASortir() > ligneStock.getQteEnStock())
            {
                throw new ValidationException("sortie.enregistrement",
                                              new String[]
                                              {"impossible" });
            }

            if (ligneStock.getQteASortir() != null
                && ligneStock.getQteASortir() < 0)
            {
                throw new ValidationException("sortie.enregistrement.negatif",
                                              new String[]
                                              {"impossible" });
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validateReceptionLot(final ReceptionLot receptionLot)
    {
        final Approvisionnement appro = receptionLot.getAppro();
        final Conditionnement conditionnement = appro.getConditionnement();

        if (appro instanceof PreparationEntree)
        {
            final PreparationEntree preparationEntree = (PreparationEntree) appro;
            if (preparationEntree.getSterile() == null)
            {
                throw new ValidationException("approvisionnement.preparation.sterile",
                                              new String[]
                                              {"notEmpty" });
            }
        }

        if (conditionnement != null)
        {
            final ModePrescription modePrescription = conditionnement.getModePrescription();
            // La réception de lot n'est pas en numéro de traitement
            if (!ModePrescription.NUM_TRAITEMENT.equals(modePrescription))
            {
                final Integer qte = appro.getQuantite();
                if (qte == null
                    || qte <= 0)
                {
                    throw new ValidationException("stock.quantite",
                                                  new String[]
                                                  {"notValid" });
                }
            }
            // La réception de lot est en numéro de traitement
            else
            {
                final Integer nbNumTraitements = receptionLot.getNbNumerosTraitement();
                if (nbNumTraitements == null
                    || nbNumTraitements <= 0)
                {
                    throw new ValidationException("approvisionnement.nbNumerosTraitement",
                                                  new String[]
                                                  {"notEmpty" });
                }

                // Récupération de la saisie cumulée des quantités des numéros de traitements
                final Integer qteCumulee = receptionLot.getQteCumulNumsTraitements();
                if (qteCumulee == null
                    || qteCumulee <= 0)
                {
                    throw new ValidationException("stock.quantite",
                                                  new String[]
                                                  {"notValid" });
                }
            }
        }
    }

}
