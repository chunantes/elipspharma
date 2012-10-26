package fr.pharma.eclipse.factory.common;

import java.io.Serializable;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import fr.pharma.eclipse.domain.model.common.BeanObject;

/**
 * Classe de Factory de BeanObject.
 
 * @version $Revision$ $Date$
 * @param <BEAN> Bean Objet Métier.
 */
public class BeanObjectFactory<BEAN extends BeanObject>
    implements BeanFactoryAware, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2989972403978568257L;

    /**
     * Factory Spring.
     */
    private BeanFactory beanFactory;

    /**
     * Classe.
     */
    private final Class<BEAN> bean;

    /**
     * Constructeur.
     * @param bean Classe.
     */
    public BeanObjectFactory(final Class<BEAN> bean)
    {
        this.bean = bean;
    }

    /**
     * Méthode en charge de retourner un BeanObject initialisé.
     * @return Retourne un BeanObject initialisé.
     */
    @SuppressWarnings("unchecked")
    public BEAN getInitializedObject()
    {
        return (BEAN) this.beanFactory.getBean(this.bean.getSimpleName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBeanFactory(final BeanFactory beanFactory)
        throws BeansException
    {
        this.beanFactory = beanFactory;
    }

}
