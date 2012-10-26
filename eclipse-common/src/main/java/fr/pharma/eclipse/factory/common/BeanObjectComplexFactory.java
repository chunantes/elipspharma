package fr.pharma.eclipse.factory.common;

import java.util.SortedMap;
import java.util.Map.Entry;

import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Fabrique d'objets BeanObject devant elle-même créer des sous-objets de l'objet créé.
 
 * @version $Revision$ $Date$
 * @param <BEAN> Bean Objet Métier.
 */
public class BeanObjectComplexFactory<BEAN extends BeanObject>
    extends BeanObjectFactory<BEAN>
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 8351903408969780553L;

    /**
     * Map à parcourir pour instancier les sous-objets <br>
     * du bean à créer (éventuellement nulle).<br>
     * - clé: la propriété de l'objet créé à valoriser.<br>
     * - valeur: fabriques avec parent.
     */
    private SortedMap<String, BeanObjectWithParentFactory<? extends BeanObject, BEAN>> mapFactories;

    /**
     * Map à parcourir pour instancier les sous-objets <br>
     * du bean à créer (éventuellement nulle).<br>
     * - clé: la propriété de l'objet créé à valoriser.<br>
     * - valeur: fabriques sans parent.
     */
    private SortedMap<String, BeanObjectFactory<? extends BeanObject>> mapFactoriesWithoutParent;

    /**
     * Constructeur.
     * @param bean Classe à instancier.
     */
    public BeanObjectComplexFactory(final Class<BEAN> bean)
    {
        super(bean);
    }

    /**
     * Méthode en charge de retourner un BeanObject initialisé.
     * @return Retourne un BeanObject initialisé.
     */
    @Override
    public BEAN getInitializedObject()
    {
        // Instanciation du bean par la classe mère.
        final BEAN bean = super.getInitializedObject();

        // Initialisation.
        this.initBean(bean);

        return bean;
    }

    /**
     * Méthode d'initialisation du bean créé.
     * @param bean Bean instancié.
     */
    private void initBean(final BEAN bean)
    {
        if (this.mapFactories == null)
        {
            return;
        }

        // Appel des fabriques de la map.
        for (final Entry<String, BeanObjectWithParentFactory<? extends BeanObject, BEAN>> mapEntry : this.mapFactories
                .entrySet())
        {
            this.initializeProperty(mapEntry.getKey(),
                                    mapEntry.getValue(),
                                    bean);
        }

        // Appel des fabriques de la map.
        for (final Entry<String, BeanObjectFactory<? extends BeanObject>> mapEntry : this.mapFactoriesWithoutParent
                .entrySet())
        {
            this.initializeProperty(mapEntry.getKey(),
                                    mapEntry.getValue(),
                                    bean);
        }
    }

    /**
     * Méthode en charge d'initialiser une propriété dans le bean créé par la super-classe.
     * @param beanProperty Propriété du bean à créer.
     * @param beanObjectWithParentFactory Fabrique à appeler.
     * @param bean Le bean créé à initialiser.
     */
    private void initializeProperty(final String beanProperty,
                                    final BeanObjectWithParentFactory<? extends BeanObject, BEAN> beanObjectWithParentFactory,
                                    final BEAN bean)
    {
        final BeanObject subBean = beanObjectWithParentFactory.getInitializedObject(bean);
        BeanTool.setPropriete(bean,
                              beanProperty,
                              subBean);
    }

    /**
     * Méthode en charge d'initialiser une propriété dans le bean créé par la super-classe.
     * @param beanProperty Propriété du bean à créer.
     * @param beanObjectFactory Fabrique à appeler.
     * @param bean Le bean créé à initialiser.
     */
    private void initializeProperty(final String beanProperty,
                                    final BeanObjectFactory<? extends BeanObject> beanObjectFactory,
                                    final BEAN bean)
    {
        final BeanObject subBean = beanObjectFactory.getInitializedObject();
        BeanTool.setPropriete(bean,
                              beanProperty,
                              subBean);
    }

    /**
     * Setter pour mapFactories.
     * @param mapFactories le mapFactories à écrire.
     */
    public void setMapFactories(final SortedMap<String, BeanObjectWithParentFactory<? extends BeanObject, BEAN>> mapFactories)
    {
        this.mapFactories = mapFactories;
    }

    /**
     * Setter pour mapFactoriesWithoutParent.
     * @param mapFactoriesWithoutParent le mapFactoriesWithoutParent à écrire.
     */
    public void setMapFactoriesWithoutParent(final SortedMap<String, BeanObjectFactory<? extends BeanObject>> mapFactoriesWithoutParent)
    {
        this.mapFactoriesWithoutParent = mapFactoriesWithoutParent;
    }
}
