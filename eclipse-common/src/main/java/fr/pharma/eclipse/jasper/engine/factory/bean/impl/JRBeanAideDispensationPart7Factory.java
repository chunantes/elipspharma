package fr.pharma.eclipse.jasper.engine.factory.bean.impl;

import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart7;
import fr.pharma.eclipse.jasper.engine.factory.bean.JasperReportBeanFactory;

/**
 * Fabrique d'objets {@link JRBeanFicheAideDispensationPart7}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JRBeanAideDispensationPart7Factory implements JasperReportBeanFactory {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3766145895026457699L;

    /**
     * {@inheritDoc}
     */
    @Override
    public JasperReportBean getInitializedObject() {
        return new JRBeanFicheAideDispensationPart7();
    }

}
