package fr.pharma.eclipse.jasper.engine.filler.impl.common;

import java.util.ArrayList;
import java.util.List;

import fr.pharma.eclipse.domain.jasper.model.common.JRBeanBlocWithMoAssocie;
import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.jasper.engine.builder.helper.common.JRBeanBlocWithMoAssocieBuilder;
import fr.pharma.eclipse.jasper.engine.factory.JRDataSourceFactory;
import fr.pharma.eclipse.jasper.engine.filler.JasperReportBeanFiller;
import fr.pharma.eclipse.utils.introspection.BeanTool;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * Filler en charge de construire une source de données<br>
 * pour un bloc décrit par le bean métier {@link JRBeanBlocWithMoAssocie}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class BlocWithMoAssocieFiller implements JasperReportBeanFiller {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -116327890428216020L;

    /**
     * Fabrique de JRDataSource.
     */
    private JRDataSourceFactory jrDataSourceFactory;

    /**
     * Liste des helpers à appeler.
     */
    private List<JRBeanBlocWithMoAssocieBuilder> helpers;

    /**
     * Propriété du bean Jasper que valorise ce filler.
     */
    private final String propertyToSet;

    /**
     * Constructeur.
     * @param propertyToSet Propriété du bean Jasper que valorise ce filler.
     */
    public BlocWithMoAssocieFiller(final String propertyToSet) {
        this.propertyToSet = propertyToSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(final Essai essai,
                     final JasperReportBean bean) {
        // Collection de beans servant de source de données.
        final List<JRBeanBlocWithMoAssocie> sources = new ArrayList<JRBeanBlocWithMoAssocie>();
        for (final JRBeanBlocWithMoAssocieBuilder helper : this.helpers) {
            sources.add(helper.build(essai));
        }

        // Création de la source de données Jasper.
        final JRDataSource dataSource = this.jrDataSourceFactory.getInitializedObject(sources);
        BeanTool.setPropriete(bean, this.propertyToSet, dataSource);
    }

    /**
     * Getter sur jrDataSourceFactory.
     * @return Retourne le jrDataSourceFactory.
     */
    JRDataSourceFactory getJrDataSourceFactory() {
        return this.jrDataSourceFactory;
    }

    /**
     * Setter pour jrDataSourceFactory.
     * @param jrDataSourceFactory le jrDataSourceFactory à écrire.
     */
    public void setJrDataSourceFactory(final JRDataSourceFactory jrDataSourceFactory) {
        this.jrDataSourceFactory = jrDataSourceFactory;
    }

    /**
     * Getter sur helpers.
     * @return Retourne le helpers.
     */
    List<JRBeanBlocWithMoAssocieBuilder> getHelpers() {
        return this.helpers;
    }

    /**
     * Setter pour helpers.
     * @param helpers le helpers à écrire.
     */
    public void setHelpers(final List<JRBeanBlocWithMoAssocieBuilder> helpers) {
        this.helpers = helpers;
    }

    /**
     * Getter sur propertyToSet.
     * @return Retourne le propertyToSet.
     */
    String getPropertyToSet() {
        return this.propertyToSet;
    }

}
