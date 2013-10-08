package fr.pharma.eclipse.jasper.engine.factory.bean;

import java.io.Serializable;

import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;

/**
 * Classe de fabrique d'objets {@link JasperReportBean}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface JasperReportBeanFactory extends Serializable {

    /**
     * Méthode qui crée un nouvel objet JasperReportBean.
     * @return Nouvel objet JasperReportBean.
     */
    JasperReportBean getInitializedObject();

}
