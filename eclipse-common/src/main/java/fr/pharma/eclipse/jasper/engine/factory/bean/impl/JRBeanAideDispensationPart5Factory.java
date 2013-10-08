package fr.pharma.eclipse.jasper.engine.factory.bean.impl;

import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart5;
import fr.pharma.eclipse.jasper.engine.factory.bean.JasperReportBeanFactory;

/**
 * Fabrique d'objets {@link JRBeanFicheAideDispensationPart5}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JRBeanAideDispensationPart5Factory implements JasperReportBeanFactory {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 8122334904642493445L;

    /**
     * {@inheritDoc}
     */
    @Override
    public JasperReportBean getInitializedObject() {
        return new JRBeanFicheAideDispensationPart5();
    }

}
