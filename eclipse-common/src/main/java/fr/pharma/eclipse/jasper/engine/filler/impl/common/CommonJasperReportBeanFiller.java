package fr.pharma.eclipse.jasper.engine.filler.impl.common;

import java.util.List;

import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.jasper.engine.factory.JRDataSourceFactory;
import fr.pharma.eclipse.jasper.engine.factory.bean.JasperReportBeanFactory;
import fr.pharma.eclipse.jasper.engine.filler.JasperReportBeanFiller;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Implémentation commune de classe en charge de valoriser un bean JasperReport.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class CommonJasperReportBeanFiller implements JasperReportBeanFiller {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1783691045567287978L;

    /**
     * Fabrique de JRDataSource.
     */
    private JRDataSourceFactory jrDataSourceFactory;

    /**
     * Fabrique de bean JasperReportBean.
     */
    private JasperReportBeanFactory jrBeanFactory;

    /**
     * Fillers secondaires.
     */
    private List<JasperReportBeanFiller> subFillers;

    /**
     * Nom de la propriété que doit valoriser ce filler<br>
     * dans le bean principal.
     */
    private final String propertyToSet;

    /**
     * Constructeur.
     * @param propertyToSet Nom de la propriété que doit valoriser ce filler<br>
     * dans le bean principal.
     */
    public CommonJasperReportBeanFiller(final String propertyToSet) {
        this.propertyToSet = propertyToSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(final Essai essai,
                     final JasperReportBean bean) {
        // Création du bean.
        final JasperReportBean subBean = this.jrBeanFactory.getInitializedObject();

        // Appel des sous-fillers.
        for (final JasperReportBeanFiller subFiller : this.subFillers) {
            subFiller.fill(essai, subBean);
        }

        // Valorisation de la propriété dans le bean principal.
        BeanTool.setPropriete(bean, this.propertyToSet, this.jrDataSourceFactory.getInitializedObject(subBean));
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
     * Getter sur jrBeanFactory.
     * @return Retourne le jrBeanFactory.
     */
    JasperReportBeanFactory getJrBeanFactory() {
        return this.jrBeanFactory;
    }

    /**
     * Setter pour jrBeanFactory.
     * @param jrBeanFactory le jrBeanFactory à écrire.
     */
    public void setJrBeanFactory(final JasperReportBeanFactory jrBeanFactory) {
        this.jrBeanFactory = jrBeanFactory;
    }

    /**
     * Getter sur subFillers.
     * @return Retourne le subFillers.
     */
    List<JasperReportBeanFiller> getSubFillers() {
        return this.subFillers;
    }

    /**
     * Setter pour subFillers.
     * @param subFillers le subFillers à écrire.
     */
    public void setSubFillers(final List<JasperReportBeanFiller> subFillers) {
        this.subFillers = subFillers;
    }

    /**
     * Getter sur propertyToSet.
     * @return Retourne le propertyToSet.
     */
    String getPropertyToSet() {
        return this.propertyToSet;
    }

}
