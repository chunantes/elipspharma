package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.util.StringUtils;

import fr.pharma.eclipse.domain.enums.ConditionConservation;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanProduit;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.produit.detail.DetailStockage;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.jasper.utils.JasperUtils;

/**
 * Helper (abstrait) pour la création des beans de produits<br>
 * commun à tous les types de produits.
 
 * @version $Revision$ $Date$
 */
public abstract class AbstractProduitsFillerHelper
    implements ProduitsFillerHelper
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -933772808615577892L;

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<JRBeanProduit> transform(final Set<Produit> produits)
    {
        final Collection<JRBeanProduit> beanProduits = new ArrayList<JRBeanProduit>();
        for (final Produit produit : produits)
        {
            final JRBeanProduit jrProduit = new JRBeanProduit();
            jrProduit.setDenomination(produit.getDenomination());
            jrProduit.setCode(produit.getCode());
            this.fillConservation(produit,
                                  jrProduit);
            this.fillLieuStockage(produit,
                                  jrProduit);
            this.fillConditionnement(produit,
                                     jrProduit);

            this.fill(produit,
                      jrProduit);
            beanProduits.add(jrProduit);
        }

        return beanProduits;
    }

    /**
     * Méthode en charge de valoriser l'attribut conditionnement.
     * @param produit Produit.
     * @param jrProduit Bean Jasper.
     */
    private void fillConditionnement(final Produit produit,
                                     final JRBeanProduit jrProduit)
    {
        final List<String> libConditionnements = new ArrayList<String>();
        for (final Conditionnement conditionnement : produit.getConditionnements())
        {
            final String currentLib = this.buildLibConditionnement(conditionnement);
            if (StringUtils.hasText(currentLib))
            {
                libConditionnements.add(currentLib);
            }
        }
        jrProduit.setConditionnement(JasperUtils.formatterListeStrings(libConditionnements));
    }

    /**
     * Méthode en charge de valoriser l'attribut conservation.
     * @param produit Produit.
     * @param jrProduit Bean Jasper.
     */
    private void fillConservation(final Produit produit,
                                  final JRBeanProduit jrProduit)
    {
        final ConditionConservation conservation =
            produit.getDetailLogistique().getConditionConservation();
        if (conservation != null)
        {
            jrProduit.setConservation(conservation.getLibelle());
        }
    }

    /**
     * Méthode en charge de valoriser l'attribut lieuStockage.
     * @param produit Produit.
     * @param jrProduit Bean Jasper.
     */
    private void fillLieuStockage(final Produit produit,
                                  final JRBeanProduit jrProduit)
    {
        final List<String> libellesStockages = new ArrayList<String>();
        for (final DetailStockage detailStockage : produit.getDetailLogistique().getDetailsStockages())
        {
            // Récupération des informations sur le lieu de stockage.
            final Pharmacie pharmacie = detailStockage.getPharmacie();
            final Stockage stockage = detailStockage.getStockage();
            final String idStockage = detailStockage.getIdentifiantStockage();

            // Formation de la chaîne de caractères.
            final StringBuilder builder = new StringBuilder();

            final boolean needSeparateur = this.addLibPharmacie(builder,
                                                                pharmacie);
            this.addLibSiteSiteStockage(builder,
                                        stockage,
                                        needSeparateur);
            this.addLibIdStockage(builder,
                                  idStockage);

            // Ajout dans la liste.
            libellesStockages.add(builder.toString());
        }
        jrProduit.setLieuStockage(JasperUtils.formatterListeStrings(libellesStockages));
    }

    /**
     * Ajoute le du site de stockage au message.
     * @param builder Builder du message.
     * @param stockage Site de stockage.
     * @param needSeparateur Indique s'il faut un séparateur avant le nom.
     */
    private void addLibSiteSiteStockage(final StringBuilder builder,
                                        final Stockage stockage,
                                        final boolean needSeparateur)
    {
        if (stockage == null)
        {
            return;
        }

        if (needSeparateur)
        {
            builder.append(" : ");
        }
        builder.append(stockage.getNomComplet());
    }

    /**
     * Ajoute le nom de la pharmacie au message.
     * @param builder Builder du message.
     * @param pharmacie Pharmacie.
     * @return Flag qui indique si un nom de pharmacie a été ajouté.
     */
    private boolean addLibPharmacie(final StringBuilder builder,
                                    final Pharmacie pharmacie)
    {
        if (pharmacie == null)
        {
            return false;
        }
        builder.append(pharmacie.getNom());
        return true;
    }

    /**
     * Ajoute l'identifiant de stockage au message.
     * @param builder Builder du message.
     * @param idStockage Identifiant de stockage.
     */
    private void addLibIdStockage(final StringBuilder builder,
                                  final String idStockage)
    {
        if (StringUtils.hasText(idStockage))
        {
            builder.append(" (").append(idStockage).append(")");
        }
    }

    /**
     * Méthode en charge de former le libellé d'un conditionnement.
     * @param conditionnement Conditionnement.
     * @return Libellé du conditionnement.
     */
    private String buildLibConditionnement(final Conditionnement conditionnement)
    {
        final StringBuilder builder = new StringBuilder();
        if (conditionnement.getUniteGestion() != null)
        {
            builder.append(conditionnement.getUniteGestion().getLibelle()).append(" : ");
        }
        if (conditionnement.getDosage() != null
            && conditionnement.getUniteDosage() != null)
        {
            builder.append(conditionnement.getDosage()).append(" ").append(conditionnement
                    .getUniteDosage());
        }
        return builder.toString();
    }

    /**
     * Méthode pour la valorisation des données de la source<br>
     * spécifiques à un type de produit.
     * @param source Produit source à inspecter.
     * @param dest Bean jasper destination à valoriser.
     */
    protected abstract void fill(Produit source,
                                 JRBeanProduit dest);

}
