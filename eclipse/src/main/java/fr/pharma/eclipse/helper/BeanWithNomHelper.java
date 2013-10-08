package fr.pharma.eclipse.helper;

import java.util.HashMap;
import java.util.Map;

import fr.pharma.eclipse.domain.model.common.BeanWithNom;

/**
 * Helper pour les interfaces avec des beans de type BeanWithNom.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class BeanWithNomHelper {
    /**
     * Utilisée pour les selectItems afin d'afficher en label le nom du bean.
     * @param collection La liste de BeanWithNom.
     * @return La map.
     */
    public Map<String, BeanWithNom> getListByName(final Iterable<BeanWithNom> collection) {
        final Map<String, BeanWithNom> map = new HashMap<String, BeanWithNom>();
        for (final BeanWithNom b : collection) {
            map.put(b.getNom(), b);
        }
        return map;
    }
}
