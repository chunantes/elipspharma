package fr.pharma.eclipse.jasper.engine.factory;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.springframework.core.io.Resource;

import fr.pharma.eclipse.jasper.engine.helper.JasperPrintHelper;
import fr.pharma.eclipse.jasper.exception.JasperReportBuildException;

/**
 * Fabrique d'objets JasperPrint pour la génération de rapports Jasper.
 
 * @version $Revision$ $Date$
 */
public class JasperPrintFactory
    implements Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3659402297177361198L;

    /**
     * Helper.
     */
    private JasperPrintHelper helper;

    /**
     * Template du rapport Jasper.
     */
    private final Resource template;

    /**
     * Constructeur.
     * @param template Template du rapport Jasper.
     */
    public JasperPrintFactory(final Resource template)
    {
        this.template = template;
    }

    /**
     * Méthode de création d'un objet JasperPrint à partir d'une map de paramètres.
     * @param mapParameters Map des paramètres du rapport.
     * @return Un nouvel objet JasperPrint.
     * @throws JasperReportBuildException En cas d'erreur de lecture du template.
     */
    public JasperPrint getInitializedObject(final Map<String, ? extends Object> mapParameters)
        throws JasperReportBuildException
    {
        return this.getInitializedObject(null,
                                         mapParameters);
    }

    /**
     * Méthode de création d'un objet JasperPrint à partir<br>
     * d'une map de paramètres et d'une source de données.
     * @param datasource Source de données du rapport.
     * @param mapParameters Map des paramètres du rapport.
     * @return Un nouvel objet JasperPrint.
     * @throws JasperReportBuildException En cas d'erreur de lecture du template.
     */
    public JasperPrint getInitializedObject(final JRDataSource datasource,
                                            final Map<String, ? extends Object> mapParameters)
        throws JasperReportBuildException
    {
        // Chargement du report
        final JasperPrint jspPrint;
        JasperReport jasperReport;
        try
        {
            jasperReport = this.helper.loadObject(this.getTemplate().getInputStream());
            if (datasource != null)
            {
                // Cas où on passe une datasource
                jspPrint = this.helper.fillReport(jasperReport,
                                                  mapParameters,
                                                  datasource);
            }
            else
            {
                // Pas de source de données
                jspPrint = this.helper.fillReport(jasperReport,
                                                  mapParameters);
            }

        }
        catch (final JRException e)
        {
            throw new JasperReportBuildException("Erreur lors du chargement du rapport : ",
                                                 e);
        }
        catch (final IOException e)
        {
            final StringBuilder message =
                new StringBuilder("Erreur de fichier. Le rapport ").append(this
                        .getTemplate()
                        .getFilename()).append(" n'a pas été trouvé.");
            throw new JasperReportBuildException(message.toString(),
                                                 e);
        }
        return jspPrint;
    }
    /**
     * Récupération de template.
     * @return Retourne le template.
     */
    Resource getTemplate()
    {
        return this.template;
    }

    /**
     * Getter sur helper.
     * @return Retourne le helper.
     */
    JasperPrintHelper getHelper()
    {
        return this.helper;
    }

    /**
     * Setter pour helper.
     * @param helper le helper à écrire.
     */
    public void setHelper(final JasperPrintHelper helper)
    {
        this.helper = helper;
    }
}
