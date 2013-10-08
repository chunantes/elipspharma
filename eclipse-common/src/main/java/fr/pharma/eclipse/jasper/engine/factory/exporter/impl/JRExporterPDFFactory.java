package fr.pharma.eclipse.jasper.engine.factory.exporter.impl;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import fr.pharma.eclipse.jasper.engine.factory.exporter.JRExporterFactory;

/**
 * Fabrique d'exporteur RTF de rapport Jasper.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JRExporterPDFFactory implements JRExporterFactory {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -1170424370207874824L;

    /**
     * {@inheritDoc}
     */
    @Override
    public JRExporter getInitializedObject() {
        return new JRPdfExporter();
    }

}
