package fr.pharma.eclipse.jasper.engine.factory.bean.impl;

import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart3;
import fr.pharma.eclipse.jasper.engine.factory.bean.JasperReportBeanFactory;

/**
 * Fabrique d'objets {@link JRBeanFicheAideDispensationPart3}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JRBeanAideDispensationPart3Factory implements JasperReportBeanFactory {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3470500619240666391L;

    /**
     * {@inheritDoc}
     */
    @Override
    public JasperReportBean getInitializedObject() {
        return new JRBeanFicheAideDispensationPart3();
    }

}
