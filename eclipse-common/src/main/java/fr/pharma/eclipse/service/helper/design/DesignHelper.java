package fr.pharma.eclipse.service.helper.design;

import java.io.Serializable;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.comparator.design.DesignableComparator;
import fr.pharma.eclipse.domain.model.design.Bras;
import fr.pharma.eclipse.domain.model.design.Designable;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.service.essai.EssaiService;

/**
 * Classe de helper pour la gestion des bras du design d'un essai.
 
 * @version $Revision$ $Date$
 */
public class DesignHelper
    implements Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2691829716664401665L;

    /**
     * Service Essai.
     */
    @Resource(name = "essaiService")
    private EssaiService essaiService;

    /**
     * Méthode en charge de retourner tous les bras de l'essai qui n'ont pas de parent.
     * @param essai Essai.
     * @return Ensemble (trié) des bras root (ceux qui n'ont pas de parent - niveau 1.
     */
    public Set<Designable> getDesignRoots(final Essai essai)
    {
        final SortedSet<Bras> bras = essai.getDetailDesign().getBras();

        // Récupération de tous les sigs qui n'ont pas de parent
        final SortedSet<Designable> designRoot =
            new TreeSet<Designable>(new DesignableComparator());
        designRoot.addAll(bras);

        CollectionUtils.filter(designRoot,
                               new Predicate() {
                                   @Override
                                   public boolean evaluate(final Object object)
                                   {
                                       final Designable design = (Designable) object;
                                       return design.getParent() == null;
                                   }
                               });
        return designRoot;
    }

    /**
     * Retourne une chaine de caractères contenant la liste des noms des bras du design.
     * @param essai L'essai.
     * @return La chaine de caractères.
     */
    public String getBrasAsString(final Essai essai)
    {
        final StringBuffer sb = new StringBuffer();
        for (final Bras bras : essai.getDetailDesign().getBras())
        {
            sb.append(bras.getNom()).append(" / ");
        }
        return StringUtils.substringBeforeLast(sb.toString(),
                                               "/");
    }

    /**
     * Setter pour essaiService.
     * @param essaiService le essaiService à écrire.
     */
    public void setEssaiService(final EssaiService essaiService)
    {
        this.essaiService = essaiService;
    }
}
