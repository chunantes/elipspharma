package fr.pharma.eclipse.jasper.engine.factory.bean.impl;

import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart2;
import fr.pharma.eclipse.jasper.engine.factory.bean.JasperReportBeanFactory;

/**
 * Fabrique d'objets {@link JRBeanFicheAideDispensationPart2}.
 
 * @version $Revision$ $Date$
 */
public class JRBeanAideDispensationPart2Factory
    implements JasperReportBeanFactory
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3470500619240666391L;

    /**
     * {@inheritDoc}
     */
    @Override
    public JasperReportBean getInitializedObject()
    {
        return new JRBeanFicheAideDispensationPart2();
    }

}
