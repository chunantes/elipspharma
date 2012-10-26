package fr.pharma.eclipse.component.produit;

import javax.annotation.Resource;

import org.primefaces.model.TreeNode;

import fr.pharma.eclipse.component.BeanManager;
import fr.pharma.eclipse.component.stockage.helper.TreeStockageHelper;
import fr.pharma.eclipse.domain.model.produit.detail.DetailStockage;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Manager de DetailStockage.
 
 * @version $Revision$ $Date$
 */
public class DetailStockageManager
    extends BeanManager<DetailStockage>
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -426419965117851251L;

    /**
     * Arbre des stockages sélectionnables pour le stockage.
     */
    private TreeNode stockagesSelectable;

    /**
     * Noeud sélectionné.
     */
    private TreeNode nodeSelected;

    /**
     * Classe de helper de construction d'arbre de stockage.
     */
    @Resource(name = "treeStockageHelper")
    private TreeStockageHelper treeStockageHelper;

    /**
     * Constructeur.
     * @param service Service DetailStockage.
     */
    public DetailStockageManager(final GenericService<DetailStockage> service)
    {
        super(service);
    }

    /**
     * Méthode en charge de purger le stockage.
     */
    public void majPharmacie()
    {
        // Changement de pharmacie => purge du stockage associé
        this.getBean().setStockage(null);
        this.setNodeSelected(null);
    }

    /**
     * Méthode en charge de mettre à jour le stockage.
     */
    public void updateStockage()
    {
        if (this.getNodeSelected() != null)
        {
            final Stockage stockage = (Stockage) this.getNodeSelected().getData();
            this.getBean().setStockage(stockage);
        }
    }

    /**
     * Getter pour stockagesSelectable.
     * @return Le stockagesSelectable
     */
    public TreeNode getStockagesSelectable()
    {
        // Récupération de la pharmacie sélectionnée
        if (this.getBean() != null
            && this.getBean().getPharmacie() != null)
        {
            this.stockagesSelectable =
                this.treeStockageHelper.buildTree(this.getBean().getPharmacie());
            return this.stockagesSelectable;
        }
        else
        {
            return null;
        }
    }

    /**
     * Getter sur nodeSelected.
     * @return Retourne le nodeSelected.
     */
    public TreeNode getNodeSelected()
    {
        return this.nodeSelected;
    }

    /**
     * Setter pour nodeSelected.
     * @param nodeSelected le nodeSelected à écrire.
     */
    public void setNodeSelected(final TreeNode nodeSelected)
    {
        this.nodeSelected = nodeSelected;
    }

    /**
     * Getter sur treeStockageHelper.
     * @return Retourne le treeStockageHelper.
     */
    public TreeStockageHelper getTreeStockageHelper()
    {
        return this.treeStockageHelper;
    }

    /**
     * Setter pour treeStockageHelper.
     * @param treeStockageHelper le treeStockageHelper à écrire.
     */
    public void setTreeStockageHelper(final TreeStockageHelper treeStockageHelper)
    {
        this.treeStockageHelper = treeStockageHelper;
    }

    /**
     * Setter pour stockagesSelectable.
     * @param stockagesSelectable le stockagesSelectable à écrire.
     */
    public void setStockagesSelectable(final TreeNode stockagesSelectable)
    {
        this.stockagesSelectable = stockagesSelectable;
    }

}
