package fr.pharma.eclipse.component.stockage.helper;

import java.io.Serializable;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import fr.pharma.eclipse.comparator.stockage.StockageComparator;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe de helper concernant la représentation sous forme d'arbre des
 * stockages d'une pharmacie.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class TreeStockageHelper implements Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 6928086394166230269L;

    /**
     * Nom de l'arbre de stockage.
     */
    private static final String NAME_TREE_STOCKAGES = "treeStockages_";

    /**
     * Méthode en charge de construire les données de l'arbre de stockages d'une
     * pharmacie.
     * @param pharmacie Pharmacie.
     * @return Arbre.
     */
    public TreeNode buildTree(final Pharmacie pharmacie) {
        final TreeNode root = new DefaultTreeNode("root", null);

        final SortedSet<Stockage> stockagesRoot = this.getStockagesRoot(pharmacie);

        for (final Stockage stockageRoot : stockagesRoot) {
            final TreeNode nodeParent = new DefaultTreeNode(stockageRoot, root);
            this.handleNoeuds(nodeParent, stockageRoot);
        }
        return root;
    }

    /**
     * Méthode en charge de calculer les identifiants de noeuds à ouvrir pour
     * l'affichage.
     * @param tree Arbre contenant les stockages.
     * @param stockage Bean stockage de référence dans l'arbre pour gérer
     * l'ouverture des noeuds.
     * @return Identifiants de noeuds à ouvrir séparés par une virgule.
     */
    public String calculateNodesToExpand(final TreeNode tree,
                                         final Stockage stockage) {
        String result = StringUtils.EMPTY;
        int index = 0;
        for (final Iterator<TreeNode> iterator = tree.getChildren().iterator(); iterator.hasNext();) {
            result = this.calculateIdTreeNode(iterator.next(), stockage, String.valueOf(index));
            if (StringUtils.isNotEmpty(result)) {
                result = this.decomposeNodes(result);
                break;
            }
            index++;
        }
        return result;
    }

    /**
     * Méthode en charge de calculer l'identifiant du noeud correspondant au
     * stockage.
     * @param node Noeud.
     * @param stockage Stockage.
     * @param index Index courant de recherche.
     * @return Identifiant du noeud correspondant au stockage.
     */
    private String calculateIdTreeNode(final TreeNode node,
                                       final Stockage stockage,
                                       final String index) {
        if (node.getData() == stockage) {
            // Si le noeud est une feuille
            if (node.isLeaf()) {
                String newIndex = index;
                if (newIndex.lastIndexOf(EclipseConstants.UNDERSCORE) != -1) {
                    newIndex = newIndex.substring(0, newIndex.lastIndexOf(EclipseConstants.UNDERSCORE));
                }
                return newIndex;
            } else {
                return index;
            }
        } else {
            int rowIndex = 0;
            String result = null;
            for (final Iterator<TreeNode> iterator = node.getChildren().iterator(); iterator.hasNext();) {
                final String nodeRowKey = index + "_" + rowIndex;
                rowIndex++;
                result = this.calculateIdTreeNode(iterator.next(), stockage, nodeRowKey);
                if (StringUtils.isNotEmpty(result)) {
                    break;
                }
            }
            return result;
        }
    }

    /**
     * Méthode en charge de retourner tous les stockages de la pharmacie qui
     * n'ont pas de parent.
     * @param pharmacie Pharmacie.
     * @return Liste des stockages root (ceux qui n'ont pas de parent - niveau
     * 1.
     */
    protected SortedSet<Stockage> getStockagesRoot(final Pharmacie pharmacie) {
        final SortedSet<Stockage> stockages = pharmacie.getStockages();

        // Récupération de tous les stockages qui n'ont pas de parent
        final SortedSet<Stockage> stockagesRoot = new TreeSet<Stockage>(new StockageComparator());
        stockagesRoot.addAll(stockages);

        CollectionUtils.filter(stockagesRoot, new Predicate() {
            @Override
            public boolean evaluate(final Object object) {
                final Stockage stockage = (Stockage) object;
                return stockage.getParent() == null;
            }
        });
        return stockagesRoot;
    }

    /**
     * Méthode en charge de traiter les noeuds de façon récursive.
     * @param nodeParent noeud Parent.
     * @param parent Stockage Parent.
     */
    protected void handleNoeuds(final TreeNode nodeParent,
                                final Stockage parent) {
        // Traitement des enfants
        for (final Stockage enfant : parent.getEnfants()) {
            // Stockage enfant est lui même parent
            if (!enfant.getEnfants().isEmpty()) {
                final TreeNode node = new DefaultTreeNode(enfant, nodeParent);
                this.handleNoeuds(node, enfant);
            }
            // Dernier niveau
            else {
                new DefaultTreeNode(enfant, nodeParent);
            }
        }
    }

    /**
     * Méthode en charge de retourner dans une chaîne le nom des identifiants de
     * noeuds séparés par une virgule qui correspondent à la hiérarchie des
     * noeuds présents dans une chaîne. Exemple : 0_1_2 doit retourner
     * TreeStockages_0,TreeStockages_1,TreeStockages_2.
     * @param input Chaîne à traiter.
     * @return Résultat de la décomposition.
     */
    protected String decomposeNodes(final String input) {
        String result = StringUtils.EMPTY;
        String tmp = input;
        if (StringUtils.isNotEmpty(tmp)) {
            result += TreeStockageHelper.NAME_TREE_STOCKAGES + tmp + EclipseConstants.COMMA;
            while (tmp.lastIndexOf(EclipseConstants.UNDERSCORE) != -1) {
                tmp = tmp.substring(0, tmp.lastIndexOf(EclipseConstants.UNDERSCORE));
                result += TreeStockageHelper.NAME_TREE_STOCKAGES + tmp + EclipseConstants.COMMA;
            }
            // Suppression de la virgule finale
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

}
