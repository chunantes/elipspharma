package fr.pharma.eclipse.transformer.dispensation;

import java.util.List;

import org.apache.commons.collections.Transformer;

import fr.pharma.eclipse.domain.model.dispensation.Dispensation;

/**
 * Transformer en charge de transformer la dispensation en nombre de ligne de
 * dispensation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class LigneDispensationTransformer implements Transformer {

    /**
     * {@inheritDoc}
     */
    @Override
    public Object transform(final Object input) {
        final Dispensation d = (Dispensation) input;
        return d.getPrescription().getProduitsPrescrits().size();
    }

    /**
     * Retourne le total de l'addition de la liste des entiers.
     */
    public static int getTotal(final List<Integer> results) {
        int r = 0;
        for (final Integer i : results) {
            r += i;
        }
        return r;
    }

}
