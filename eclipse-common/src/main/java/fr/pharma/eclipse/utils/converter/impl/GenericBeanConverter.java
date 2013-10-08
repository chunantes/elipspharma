package fr.pharma.eclipse.utils.converter.impl;

import java.util.ArrayList;
import java.util.List;

import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.factory.common.BeanObjectFactory;
import fr.pharma.eclipse.utils.converter.BeanConverter;
import fr.pharma.eclipse.utils.converter.filler.Filler;
import fr.pharma.eclipse.utils.introspection.GenericFetcher;

/**
 * Classe en charge de convertir un bean en un bean du modèle. Il procède en 3
 * étapes : 1 - Il appelle la factory du bean métier, 2 - il appliquer un
 * fetcher en charge de remplir les propriétés simples, 3- Il applique des
 * fillers pour les propriétés plus complexes.
 * @author Netapsys
 * @version $Revision$ $Date$
 * @param <BEANTECH> Type du bean source.
 * @param <BEAN> Type du bean métier destination.
 */
public class GenericBeanConverter<BEANTECH extends Object, BEAN extends BeanObject> implements BeanConverter<BEANTECH, BEAN> {

    /**
     * Factory.
     */
    private final BeanObjectFactory<BEAN> factory;

    /**
     * Fetcher en charge de copier les propriétés simples.
     */
    private final GenericFetcher<BEANTECH, BEAN> fetcher;

    /**
     * Liste de converter à appliquer.
     */
    private List<Filler<BEANTECH, BEAN>> fillers = new ArrayList<Filler<BEANTECH, BEAN>>();

    /**
     * Constructeur.
     * @param fetcher GenericFetcher.
     * @param factory Factory du bean métier.
     */
    public GenericBeanConverter(final GenericFetcher<BEANTECH, BEAN> fetcher, final BeanObjectFactory<BEAN> factory) {
        this.fetcher = fetcher;
        this.factory = factory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BEAN convert(final BEANTECH source) {
        final BEAN destination = this.factory.getInitializedObject();

        // fetch des propriétés simple.
        this.fetcher.fetch(source, destination);

        // On applique les converter spécifique.
        for (final Filler<BEANTECH, BEAN> filler : this.fillers) {
            if (filler.support(source)) {
                filler.fill(source, destination);
            }
        }
        return destination;
    }

    /**
     * Getter sur fillers.
     * @return Retourne le fillers.
     */
    public List<Filler<BEANTECH, BEAN>> getFillers() {
        return this.fillers;
    }

    /**
     * Setter pour fillers.
     * @param fillers le fillers à écrire.
     */
    public void setFillers(final List<Filler<BEANTECH, BEAN>> fillers) {
        this.fillers = fillers;
    }

}
