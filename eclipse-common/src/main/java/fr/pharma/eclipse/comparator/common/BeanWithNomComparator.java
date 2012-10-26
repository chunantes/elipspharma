package fr.pharma.eclipse.comparator.common;

import java.io.Serializable;
import java.util.Comparator;

import fr.pharma.eclipse.domain.model.common.BeanWithNom;

/**
 * Classe de comparator pour les objets ayant un nom.
 
 * @version $Revision$ $Date$
 */
public class BeanWithNomComparator
    implements Comparator<BeanWithNom>, Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 7462968439226919349L;

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final BeanWithNom bean1,
                       final BeanWithNom bean2)
    {
        return bean1.getNom().compareTo(bean2.getNom());
    }

}
