package fr.pharma.eclipse.component.design.helper;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.domain.model.design.Designable;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.service.helper.design.DesignHelper;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe de helper concernant la représentation sous forme d'arbre des s d'une elements du design
 * (bras, sous-bras, sequence).
 
 * @version $Revision$ $Date$
 */
public class TreeDesignHelper
    implements Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 6928086394166230269L;

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(TreeDesignHelper.class);

    /**
     * Nom de l'arbre de design.
     */
    private static final String NAME_TREE_DESIGN = "treeDesign_";

    /**
     * Helper.
     */
    private DesignHelper helper;

    /**
     * Méthode en charge de construire les données de l'arbre des éléments du design d'un essai.
     * @param essai Essai.
     * @return Arbre.
     */
    public TreeNode buildTree(final Essai essai)
    {
        final TreeNode root = new DefaultTreeNode("root",
                                                  null);

        final Set<Designable> designRoot = this.helper.getDesignRoots(essai);

        this.log.debug("[buildTree] noeuds parents : "
                       + designRoot);

        for (final Designable designable : designRoot)
        {
            final TreeNode nodeParent = new DefaultTreeNode(designable,
                                                            root);
            this.handleNoeuds(nodeParent,
                              designable);
        }
        return root;
    }

    /**
     * Méthode en charge de calculer les identifiants de noeuds à ouvrir pour l'affichage.
     * @param tree Arbre contenant les designables.
     * @param designable Bean Designable de référence dans l'arbre pour gérer l'ouverture des
     * noeuds.
     * @return Identifiants de noeuds à ouvrir séparés par une virgule.
     */
    public String calculateNodesToExpand(final TreeNode tree,
                                         final Designable designable)
    {
        String result = StringUtils.EMPTY;
        int index = 0;
        for (final Iterator<TreeNode> iterator = tree.getChildren().iterator(); iterator
                .hasNext();)
        {
            result = this.calculateIdTreeNode(iterator.next(),
                                              designable,
                                              String.valueOf(index));
            if (StringUtils.isNotEmpty(result))
            {
                result = this.decomposeNodes(result);
                break;
            }
            index++;
        }
        return result;
    }

    /**
     * Méthode en charge de calculer l'identifiant du noeud correspondant au designable.
     * @param node Noeud.
     * @param designable Designable.
     * @param index Index courant de recherche.
     * @return Identifiant du noeud correspondant au designable.
     */
    private String calculateIdTreeNode(final TreeNode node,
                                       final Designable designable,
                                       final String index)
    {
        if (((Designable) node.getData()).getNomComplet().equals(designable.getNomComplet()))
        {
            // Si le noeud est une feuille
            if (node.isLeaf())
            {
                String newIndex = index;
                if (newIndex.lastIndexOf(EclipseConstants.UNDERSCORE) != -1)
                {
                    newIndex =
                        newIndex.substring(0,
                                           newIndex.lastIndexOf(EclipseConstants.UNDERSCORE));
                }
                return newIndex;
            }
            else
            {
                return index;
            }
        }
        else
        {
            int rowIndex = 0;
            String result = null;
            for (final Iterator<TreeNode> iterator = node.getChildren().iterator(); iterator
                    .hasNext();)
            {
                final String nodeRowKey = index
                                          + "_"
                                          + rowIndex;
                rowIndex++;
                result = this.calculateIdTreeNode(iterator.next(),
                                                  designable,
                                                  nodeRowKey);
                if (StringUtils.isNotEmpty(result))
                {
                    break;
                }
            }
            return result;
        }
    }
    /**
     * Méthode en charge de traiter les noeuds de façon récursive.
     * @param nodeParent noeud Parent.
     * @param parent Designable Parent.
     */
    protected void handleNoeuds(final TreeNode nodeParent,
                                final Designable parent)
    {
        // Traitement des enfants
        for (final Designable enfant : parent.getEnfants())
        {
            // designable enfant est lui même parent
            if (!enfant.getEnfants().isEmpty())
            {
                final TreeNode node = new DefaultTreeNode(enfant,
                                                          nodeParent);
                this.handleNoeuds(node,
                                  enfant);
            }
            // Dernier niveau
            else
            {
                new DefaultTreeNode(enfant,
                                    nodeParent);
            }
        }
    }

    /**
     * Méthode en charge de retourner dans une chaîne le nom des identifiants de noeuds séparés
     * par une virgule qui correspondent à la hiérarchie des noeuds présents dans une chaîne.
     * Exemple : 0_1_2 doit retourner TreeStockages_0,TreeStockages_1,TreeStockages_2.
     * @param input Chaîne à traiter.
     * @return Résultat de la décomposition.
     */
    protected String decomposeNodes(final String input)
    {
        String result = StringUtils.EMPTY;
        String tmp = input;
        if (StringUtils.isNotEmpty(tmp))
        {
            result += TreeDesignHelper.NAME_TREE_DESIGN
                      + tmp
                      + EclipseConstants.COMMA;
            while (tmp.lastIndexOf(EclipseConstants.UNDERSCORE) != -1)
            {
                tmp = tmp.substring(0,
                                    tmp.lastIndexOf(EclipseConstants.UNDERSCORE));
                result += TreeDesignHelper.NAME_TREE_DESIGN
                          + tmp
                          + EclipseConstants.COMMA;
            }
            // Suppression de la virgule finale
            result = result.substring(0,
                                      result.length() - 1);
        }
        return result;
    }

    /**
     * Getter sur helper.
     * @return Retourne le helper.
     */
    DesignHelper getHelper()
    {
        return this.helper;
    }

    /**
     * Setter pour helper.
     * @param helper le helper à écrire.
     */
    public void setHelper(final DesignHelper helper)
    {
        this.helper = helper;
    }

}
