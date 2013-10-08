package fr.pharma.eclipse.jasper.engine.factory.bean.impl;

import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart6;
import fr.pharma.eclipse.jasper.engine.factory.bean.JasperReportBeanFactory;

/**
 * Fabrique d'objets {@link JRBeanFicheAideDispensationPart6}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JRBeanAideDispensationPart6Factory implements JasperReportBeanFactory {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 8122334904642493445L;

    /**
     * {@inheritDoc}
     */
    @Override
    public JasperReportBean getInitializedObject() {
        return new JRBeanFicheAideDispensationPart6();
    }

}
