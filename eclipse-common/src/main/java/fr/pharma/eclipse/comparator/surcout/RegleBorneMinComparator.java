package fr.pharma.eclipse.comparator.surcout;
import java.util.Comparator;

import fr.pharma.eclipse.domain.model.surcout.regle.Regle;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Comparator utilisée pour trier les regles de type Variable selon leur borne inférieure.
 
 * @version $Revision$ $Date$
 */
public class RegleBorneMinComparator
    implements Comparator<Regle>
{

    /**
     * Propriété représentant la borne basse.
     */
    private final String propertyMin;

    /**
     * Propriété représentant la borne haute.
     */
    private final String propertyMax;

    /**
     * Constructeur.
     * @param propertyMin Borne haute.
     * @param propertyMax Borne basse.
     */
    public RegleBorneMinComparator(final String propertyMin, final String propertyMax)
    {
        this.propertyMax = propertyMax;
        this.propertyMin = propertyMin;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final Regle o1,
                       final Regle o2)
    {
        final Integer o1Min = (Integer) BeanTool.getPropriete(o1,
                                                              this.propertyMin);
        final Integer o1Max = (Integer) BeanTool.getPropriete(o1,
                                                              this.propertyMax);
        final Integer o2Min = (Integer) BeanTool.getPropriete(o2,
                                                              this.propertyMin);
        final Integer o2Max = (Integer) BeanTool.getPropriete(o2,
                                                              this.propertyMax);

        if (o1Min == null
            && o2Min == null)
        {
            return o1Max.compareTo(o2Max);
        }
        else if (o1Min == null)
        {
            return -1;
        }
        else if (o2Min == null)
        {
            return 1;
        }
        else
        {
            return o1Min.compareTo(o2Min);
        }

    }
}
