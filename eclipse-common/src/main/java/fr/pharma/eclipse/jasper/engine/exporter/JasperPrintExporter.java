package fr.pharma.eclipse.jasper.engine.exporter;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;

import org.springframework.util.Assert;

import fr.pharma.eclipse.domain.enums.jasper.TypeRapportJasper;
import fr.pharma.eclipse.factory.utils.IOStreamsFactory;
import fr.pharma.eclipse.jasper.engine.factory.exporter.JRExporterFactory;
import fr.pharma.eclipse.jasper.exception.JasperReportBuildException;

/**
 * Classes en charge d'exporter un objet JasperPrint en InputStream.
 
 * @version $Revision$ $Date$
 */
public class JasperPrintExporter
    implements Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -1670465941040125417L;

    /**
     * Fabrique de flux I/O.
     */
    private IOStreamsFactory ioFactory;

    /**
     * Map des fabriques d'exporteurs Jasper selon le type de rapport à créer.<br>
     * - clé : valeur de l'énum TypeExportJasper.<br>
     * - valeur: fabrique associée.
     */
    private SortedMap<String, JRExporterFactory> jrExporterFactories;

    /**
     * Méthode en charge d'exporter l'objet JasperPrint représentant le rapport Jasper, en un
     * objet InputStream.
     * @param typeRapport Type du rapport à exporter.
     * @param jasperPrint Rapport Jasper à exporter.
     * @return Le flux de données correspondant au rapport.
     * @throws JasperReportBuildException En cas d'erreur.
     */
    public ByteArrayOutputStream export(final TypeRapportJasper typeRapport,
                                        final JasperPrint jasperPrint)
        throws JasperReportBuildException
    {
        // Récupération de l'exporteur.
        final JRExporterFactory factory =
            this.jrExporterFactories.get(typeRapport.getTypeExport().name());
        this.checkFactory(typeRapport,
                          factory);
        final JRExporter exporter = factory.getInitializedObject();

        // Liste des JasperPrints.
        final List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
        jasperPrints.add(jasperPrint);

        // Formation de la sortie.
        final ByteArrayOutputStream stream = this.ioFactory.getInitializedByteArrayOutStream();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST,
                              jasperPrints);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
                              stream);
        try
        {
            exporter.exportReport();
        }
        catch (final JRException exc)
        {
            throw new JasperReportBuildException(exc);
        }

        return stream;
    }

    /**
     * Méthode de vérification de la fabrique associée au type de rapport.
     * @param typeRapport Type du rapport à exporter.
     * @param factory Fabrique récupérée.
     */
    private void checkFactory(final TypeRapportJasper typeRapport,
                              final JRExporterFactory factory)
    {
        Assert.notNull(factory,
                       new StringBuilder("[checkFactory] ")
                               .append("Aucune fabrique n'est associée ")
                               .append("au type de rapport Jasper '")
                               .append(typeRapport)
                               .append("'!")
                               .toString());
    }

    /**
     * Getter sur jrExporterFactories.
     * @return Retourne le jrExporterFactories.
     */
    SortedMap<String, JRExporterFactory> getJrExporterFactories()
    {
        return this.jrExporterFactories;
    }

    /**
     * Setter pour jrExporterFactories.
     * @param jrExporterFactories le jrExporterFactories à écrire.
     */
    public void setJrExporterFactories(final SortedMap<String, JRExporterFactory> jrExporterFactories)
    {
        this.jrExporterFactories = jrExporterFactories;
    }
    /**
     * Getter sur ioFactory.
     * @return Retourne le ioFactory.
     */
    IOStreamsFactory getIoFactory()
    {
        return this.ioFactory;
    }
    /**
     * Setter pour ioFactory.
     * @param ioFactory le ioFactory à écrire.
     */
    public void setIoFactory(final IOStreamsFactory ioFactory)
    {
        this.ioFactory = ioFactory;
    }
}
