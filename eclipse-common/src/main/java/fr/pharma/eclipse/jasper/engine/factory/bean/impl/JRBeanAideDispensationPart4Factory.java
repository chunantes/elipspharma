package fr.pharma.eclipse.jasper.engine.factory.bean.impl;

import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart4;
import fr.pharma.eclipse.jasper.engine.factory.bean.JasperReportBeanFactory;

/**
 * Fabrique d'objets {@link JRBeanFicheAideDispensationPart4}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JRBeanAideDispensationPart4Factory implements JasperReportBeanFactory {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -5211113025238183561L;

    /**
     * {@inheritDoc}
     */
    @Override
    public JasperReportBean getInitializedObject() {
        return new JRBeanFicheAideDispensationPart4();
    }

}
