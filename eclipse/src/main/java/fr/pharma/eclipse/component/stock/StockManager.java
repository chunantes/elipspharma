package fr.pharma.eclipse.component.stock;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.model.TreeNode;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;

import fr.pharma.eclipse.component.stockage.helper.TreeStockageHelper;
import fr.pharma.eclipse.domain.criteria.stock.StockSearchCriteria;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.EtatLigneStock;
import fr.pharma.eclipse.domain.model.stock.EtatStock;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.utils.EclipseDocumentProcessor;
import fr.pharma.eclipse.utils.Utils;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Manager de consultation de stock (Etat de stock).
 
 * @version $Revision$ $Date$
 */
public class StockManager
    implements Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 4585546119652116173L;

    /**
     * Critère de recherche.
     */
    private final StockSearchCriteria searchCriteria;

    /**
     * Liste des états de stock.
     */
    private List<EtatStock> beans;

    /**
     * Etat stock sélectionné.
     */
    private EtatStock beanSelected;

    /**
     * Etat ligne de stock.
     */
    private EtatLigneStock ligne;

    /**
     * Arbre des stockages sélectionnables.
     */
    private TreeNode stockagesSelectable;

    /**
     * Noeud sélectionné.
     */
    private TreeNode nodeSelected;

    /**
     * Processor pour les documents dataExporter.
     */
    @Resource(name = "eclipseDocProcessor")
    private EclipseDocumentProcessor processor;

    /**
     * Classe de helper de construction d'arbre de stockage.
     */
    @Resource(name = "treeStockageHelper")
    private TreeStockageHelper treeStockageHelper;

    /**
     * Constructeur.
     * @param searchCriteria Critère de recherche.
     */
    public StockManager(final StockSearchCriteria searchCriteria)
    {
        this.searchCriteria = searchCriteria;
    }

    /**
     * Getter pour stockagesSelectable.
     * @return Le stockagesSelectable
     */
    public TreeNode getStockagesSelectable()
    {
        // Récupération de la pharmacie sélectionnée
        final Pharmacie pharmacie = this.getSearchCriteria().getPharmacie();

        if (pharmacie != null)
        {
            this.stockagesSelectable = this.treeStockageHelper.buildTree(pharmacie);
            return this.stockagesSelectable;
        }
        else
        {
            return null;
        }
    }

    /**
     * Méthode en charge de mettre à jour le stockage de recherche.
     */
    public void updateStockage()
    {
        if (this.getNodeSelected() != null)
        {
            final Stockage stockage = (Stockage) this.getNodeSelected().getData();
            this.getSearchCriteria().setStockage(stockage);
        }
    }

    /**
     * Méthode en charge de supprimer le stockage de recherche.
     */
    public void delStockage()
    {
        this.getSearchCriteria().setStockage(null);
    }

    /**
     * Méthode appelée via la couche IHM lorsqu'une pharmacie est sélectionnée.
     * @param event Evénement remonté via la couche IHM.
     */
    public void handleSelectPharmacie(final AjaxBehaviorEvent event)
    {
        // Récupération de la pharmacie sélectionnée
        final HtmlSelectOneMenu select = (HtmlSelectOneMenu) event.getSource();
        final Pharmacie pharmacie = (Pharmacie) select.getLocalValue();

        this.getSearchCriteria().setPharmacie(pharmacie);

        // Effacement du lieu de stockage
        this.getSearchCriteria().setStockage(null);
    }

    /**
     * Méthode en charge de déterminer si la quantité d'une ligne d'état de stock est inférieure
     * au seuil plancher défini dans la partie Produit de l'essai.
     * @param etatStock Ligne d'état de stock à tester.
     * @return Résultat de l'atteinte du seuil plancher.
     */
    public Boolean seuilPlancherAtteint(final EtatStock etatStock)
    {
        Boolean result = Boolean.FALSE;

        // Récupération du stock seuil du produit
        final Produit produit = etatStock.getProduit();
        final Integer seuilPlancher = produit.getDetailLogistique().getStockSeuil();

        if (seuilPlancher != null
            && etatStock.getQteEnStock() < seuilPlancher)
        {
            result = Boolean.TRUE;
        }
        return result;
    }

    /**
     * Applique un pre processor avant le génération des documents dataExporter.
     * @param document Document.
     * @throws IOException en cas d'erreur.
     * @throws BadElementException en cas d'erreur .
     * @throws DocumentException en cas d'erreur.
     */
    public void preProcessPDF(final Object document)
        throws IOException,
            BadElementException,
            DocumentException
    {
        final Calendar date = Calendar.getInstance(Locale.FRANCE);
        this.processor.preProcessPDF(document,
                                     "Consultation du stock au "
                                             + Utils.formatDate(date.getTime(),
                                                                EclipseConstants.PATTERN_SIMPLE));
    }

    /**
     * Méthode en charge de formatter les lignes de stocks pour l'export pdf.
     * @param stock Stock.
     * @return La chaine à afficher dans la cellule.
     */
    public String getQuantites(final EtatStock stock)
    {
        final StringBuffer sb = new StringBuffer();

        for (final EtatLigneStock e : stock.getEtatsLignesStockAsList())
        {
            sb.append(e.getQteEnStock()).append(" - ").append(e.getNumLot());
            if (e.getNumTraitement() != null)
            {
                sb.append(" - ").append(e.getNumTraitement());
            }
            else
            {
                sb.append(" - ").append(EclipseConstants.NON_APPLICABLE);
            }
            if (e.getDatePeremption() != null)
            {
                sb.append(" - ").append(Utils.formatDate(e.getDatePeremption().getTime(),
                                                         EclipseConstants.PATTERN_SIMPLE));
            }
            sb.append(" \r");
        }

        return sb.toString();
    }
    /**
     * Getter pour beans.
     * @return Le beans
     */
    public List<EtatStock> getBeans()
    {
        return this.beans;
    }

    /**
     * Setter pour beans.
     * @param beans Le beans à écrire.
     */
    public void setBeans(final List<EtatStock> beans)
    {
        this.beans = beans;
    }

    /**
     * Getter pour searchCriteria.
     * @return Le searchCriteria
     */
    public StockSearchCriteria getSearchCriteria()
    {
        return this.searchCriteria;
    }

    /**
     * Getter pour nodeSelected.
     * @return Le nodeSelected
     */
    public TreeNode getNodeSelected()
    {
        return this.nodeSelected;
    }

    /**
     * Setter pour nodeSelected.
     * @param nodeSelected Le nodeSelected à écrire.
     */
    public void setNodeSelected(final TreeNode nodeSelected)
    {
        this.nodeSelected = nodeSelected;
    }

    /**
     * Setter pour treeStockageHelper.
     * @param treeStockageHelper Le treeStockageHelper à écrire.
     */
    public void setTreeStockageHelper(final TreeStockageHelper treeStockageHelper)
    {
        this.treeStockageHelper = treeStockageHelper;
    }

    /**
     * Getter sur beanSelected.
     * @return Retourne le beanSelected.
     */
    public EtatStock getBeanSelected()
    {
        return this.beanSelected;
    }

    /**
     * Setter pour beanSelected.
     * @param beanSelected le beanSelected à écrire.
     */
    public void setBeanSelected(final EtatStock beanSelected)
    {
        this.beanSelected = beanSelected;
    }

    /**
     * Getter sur ligne.
     * @return Retourne le ligne.
     */
    public EtatLigneStock getLigne()
    {
        return this.ligne;
    }

    /**
     * Setter pour ligne.
     * @param ligne le ligne à écrire.
     */
    public void setLigne(final EtatLigneStock ligne)
    {
        this.ligne = ligne;
    }

    /**
     * Setter pour processor.
     * @param processor Le processor à écrire.
     */
    public void setProcessor(final EclipseDocumentProcessor processor)
    {
        this.processor = processor;
    }

}
