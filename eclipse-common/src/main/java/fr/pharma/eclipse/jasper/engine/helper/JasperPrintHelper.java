package fr.pharma.eclipse.jasper.engine.helper;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.fill.JRFiller;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Helper pour la génération des objets de type JasperPrint.
 
 * @version $Revision$ $Date$
 */
public class JasperPrintHelper
    implements Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 7076765564289907694L;

    /**
     * Méthode en charge de créer un objet JasperReport à partir d'un flux d'entrée.
     * @param is Flux d'entrée.
     * @return Un objet JasperReport.
     * @throws JRException En cas d'erreur.
     */
    public JasperReport loadObject(final InputStream is)
        throws JRException
    {
        return (JasperReport) JRLoader.loadObject(is);
    }

    /**
     * Fills the compiled report design loaded from the supplied input stream and returns the
     * generated report object.
     * @param jasperReport input stream to read the compiled report design object from
     * @param mapParameters report parameters map
     * @param dataSource data source object
     * @return generated report object
     * @throws JRException JRException.
     * @see JasperFillManager#fillReport(JasperReport, Map, JRDataSource)
     */
    public JasperPrint fillReport(final JasperReport jasperReport,
                                  final Map<String, ? extends Object> mapParameters,
                                  final JRDataSource dataSource)
        throws JRException
    {
        return JasperFillManager.fillReport(jasperReport,
                                            mapParameters,
                                            dataSource);
    }

    /**
     * Fills the compiled report design loaded from the supplied input stream and returns the
     * generated report object.
     * @param jasperReport input stream to read the compiled report design object from
     * @param mapParameters report parameters map
     * @return generated report object
     * @throws JRException JRException.
     * @see JRFiller#fillReport(JasperReport, Map)
     */
    public JasperPrint fillReport(final JasperReport jasperReport,
                                  final Map<String, ? extends Object> mapParameters)
        throws JRException
    {
        return JasperFillManager.fillReport(jasperReport,
                                            mapParameters);
    }
}
