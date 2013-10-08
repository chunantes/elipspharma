package fr.pharma.eclipse.utils.introspection;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Fetcher générique permettant de populer des beans métier à partir d'autres
 * beans.
 * @author Netapsys
 * @version $Revision$ $Date$
 * @param <BEANTECH> Le bean d'origine
 * @param <BEAN> le bean destination
 */
public class GenericFetcher<BEANTECH, BEAN> {
    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(GenericFetcher.class);

    /**
     * Mapping propriété destination / propriété source.
     */
    private Map<String, String> mapping;

    /**
     * Méthodes permettant d'insérer les données du bean technique dans le bean
     * métier selon le mapping.
     * @param source le bean technique.
     * @param destination Le bean métier.
     */
    public void fetch(final BEANTECH source,
                      final BEAN destination) {
        for (final Entry<String, String> entry : this.mapping.entrySet()) {

            BeanTool.setPropriete(destination, entry.getKey(), BeanTool.getPropriete(source, entry.getValue()));

        }
    }
    /**
     * Set mapping avec mapping.
     * @param mapping Le mapping à écrire.
     */
    public void setMapping(final Map<String, String> mapping) {
        this.mapping = mapping;
    }

}
