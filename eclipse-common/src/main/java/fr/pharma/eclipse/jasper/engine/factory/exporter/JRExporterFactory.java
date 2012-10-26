package fr.pharma.eclipse.jasper.engine.factory.exporter;

import java.io.Serializable;

import net.sf.jasperreports.engine.JRExporter;

/**
 * Interface des fabriques d'exporters de rapports Jasper.
 
 * @version $Revision$ $Date$
 */
public interface JRExporterFactory
    extends Serializable
{
    /**
     * Méthode de création d'un exporter de Jasper Report.
     * @return Un nouvel objet JRAbstractExporter.
     */
    JRExporter getInitializedObject();
}
