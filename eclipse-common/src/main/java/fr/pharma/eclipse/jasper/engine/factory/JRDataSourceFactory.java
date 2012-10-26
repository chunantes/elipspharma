package fr.pharma.eclipse.jasper.engine.factory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Fabrique d'objets JRDataSource pour Jasper.
 
 * @version $Revision$ $Date$
 */
public class JRDataSourceFactory
    implements Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 862928015073140614L;

    /**
     * Crée un JRDataSource à partir d'un objet source.<br>
     * Type réel: JRBeanCollectionDataSource.
     * @param source Bean source.
     * @return Un objet JRDataSource.
     */
    public JRDataSource getInitializedObject(final Object source)
    {
        final List<Object> sources = new ArrayList<Object>();
        sources.add(source);
        return this.getInitializedObject(sources);
    }

    /**
     * Crée un JRDataSource à partir d'une collection d'objets.<br>
     * Type réel: JRBeanCollectionDataSource.
     * @param sources Liste des sources.
     * @return Un objet JRDataSource.
     */
    public JRDataSource getInitializedObject(final Collection<? extends Object> sources)
    {
        return new JRBeanCollectionDataSource(sources);
    }

}
