package fr.pharma.eclipse.service.produit.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.enums.produit.TypeDetailStockage;
import fr.pharma.eclipse.domain.enums.produit.TypeProduit;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.produit.detail.DetailLogistique;
import fr.pharma.eclipse.domain.model.produit.detail.DetailStockage;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.domain.model.suivi.produit.ProduitSuivi;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.produit.ProduitService;

/**
 * Classe d'implémentation du service de gestion de personne.
 
 * @version $Revision$ $Date$
 * @param <PRODUIT> Bean Objet Personne.
 */
public class ProduitServiceImpl<PRODUIT extends Produit>
    extends GenericServiceImpl<PRODUIT>
    implements ProduitService<PRODUIT>
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 4658588704286166626L;

    /**
     * Factory de suivi de produit.
     */
    @Resource(name = "produitSuiviFactory")
    private SuiviFactory<ProduitSuivi> produitSuiviFactory;

    /**
     * Service de gestion des essais.
     */
    @Resource(name = "essaiService")
    private EssaiService essaiService;

    /**
     * Constructeur.
     * @param produitDao Dao de gestion des personnes.
     */
    public ProduitServiceImpl(final GenericDao<PRODUIT> produitDao)
    {
        super(produitDao);
    }

    /**
     * Méthode en charge d'ajouter un élément d'historique de maj sur un produit.
     * @param p Le produit.
     */
    public void addMaj(final Produit p)
    {
        final ProduitSuivi suivi = this.produitSuiviFactory.getInitializedObject();
        suivi.setProduit(p);
        p.getModifs().add(suivi);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PRODUIT save(final PRODUIT produit)
    {
        return this.saveCommon(produit);
    }

    /**
     * Méthode contenant le traitement commun aux deux méthodes save.
     * @param produit Produit à sauvegarde.
     * @return Le produit sauvegardée.
     */
    private PRODUIT saveCommon(final PRODUIT produit)
    {
        final PRODUIT prod = this.reattach(produit);
        final ProduitSuivi personneSuivi = this.produitSuiviFactory.getInitializedObject();
        personneSuivi.setProduit(prod);
        prod.getModifs().add(personneSuivi);
        return super.save(prod);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Produit> getProduits(final Essai essai,
                                     final Pharmacie pharmacie)
    {
        final List<Produit> result = new ArrayList<Produit>();

        final Essai ess = this.essaiService.reattach(essai);

        // Récupération des produits de l'essai
        final SortedSet<Produit> produits = ess.getDetailProduit().getProduits();

        // On ne garde que les produits ayant un lieu de stockage pour la pharmacie
        for (final Produit produit : produits)
        {
            if (produit.getType() != TypeProduit.PREPARATION
                && this.getStockageProduitPharma(produit,
                                                 pharmacie) != null)
            {
                result.add(produit);
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Produit> getProduitsWithPreparations(final Essai essai,
                                                     final Pharmacie pharmacie)
    {
        final List<Produit> result = new ArrayList<Produit>();

        final Essai ess = this.essaiService.reattach(essai);

        // Récupération des produits de l'essai
        final SortedSet<Produit> produits = ess.getDetailProduit().getProduits();

        // On ne garde que les produits ayant un lieu de stockage pour la pharmacie
        for (final Produit produit : produits)
        {
            if (this.getStockageProduitPharma(produit,
                                              pharmacie) != null)
            {
                result.add(produit);
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Produit> getPreparations(final Essai essai,
                                         final Pharmacie pharmacie)
    {
        final List<Produit> result = new ArrayList<Produit>();

        final Essai ess = this.essaiService.reattach(essai);

        // Récupération des produits de l'essai
        final SortedSet<Produit> produits = ess.getDetailProduit().getPreparations();

        // On ne garde que les produits ayant un lieu de stockage pour la pharmacie
        for (final Produit produit : produits)
        {
            if (this.getStockageProduitPharma(produit,
                                              pharmacie) != null)
            {
                result.add(produit);
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stockage getStockageProduitPharma(final Produit produit,
                                             final Pharmacie pharmacie)
    {

        final DetailLogistique detailLogistique = produit.getDetailLogistique();

        final SortedSet<DetailStockage> stockages = detailLogistique.getDetailsStockages();

        final DetailStockage resultDetailStockage =
            (DetailStockage) CollectionUtils.find(stockages,
                                                  new Predicate() {
                                                      @Override
                                                      public boolean evaluate(final Object object)
                                                      {
                                                          final DetailStockage d =
                                                              (DetailStockage) object;
                                                          return TypeDetailStockage.STOCK
                                                                  .equals(d.getType())
                                                                 && d.getPharmacie()
                                                                         .equals(pharmacie);
                                                      }
                                                  });
        if (resultDetailStockage != null)
        {
            return resultDetailStockage.getStockage();
        }
        else
        {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<Produit> getProduits(final Essai essai)
    {
        return new ArrayList<Produit>(this.essaiService
                .reattach(essai)
                .getDetailProduit()
                .getProduits());
    }

    /**
     * Setter pour produitSuiviFactory.
     * @param produitSuiviFactory le produitSuiviFactory à écrire.
     */
    public void setProduitSuiviFactory(final SuiviFactory<ProduitSuivi> produitSuiviFactory)
    {
        this.produitSuiviFactory = produitSuiviFactory;
    }

    /**
     * Setter pour essaiService.
     * @param essaiService Le essaiService à écrire.
     */
    public void setEssaiService(final EssaiService essaiService)
    {
        this.essaiService = essaiService;
    }

}
