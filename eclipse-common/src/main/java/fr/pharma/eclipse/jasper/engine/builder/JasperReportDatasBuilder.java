package fr.pharma.eclipse.jasper.engine.builder;

import java.io.Serializable;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import fr.pharma.eclipse.domain.enums.jasper.TypeRapportJasper;
import fr.pharma.eclipse.jasper.exception.JasperReportBuildException;

/**
 * Interface des classe en charge de construire les données d'entrée d'un
 * rapport Jasper.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface JasperReportDatasBuilder extends Serializable {
    /**
     * Méthode à appeler pour valider la source que l'on va passer au builder.
     * @param source Source de données servant de base au rapport à valider.
     * @throws JasperReportBuildException En cas de source non valide.
     */
    void checkSource(Object source) throws JasperReportBuildException;

    /**
     * Méthode en charge de construire le nom du rapport à exporter.<br>
     * La source doit avoir été validée.
     * @param source Source de données servant de base au rapport.
     * @param typeRapport Type du rapport Jasper.
     * @return Le nom du rapport à exporter.
     */
    String buildReportName(Object source,
                           TypeRapportJasper typeRapport);

    /**
     * Méthode en charge de construire la source de données du rapport.<br>
     * La source doit avoir été validée.
     * @param source Source de données servant de base au rapport.
     * @return La source de données Jasper Reports.
     */
    JRDataSource buildDataSource(Object source);

    /**
     * Méthode en charge de construire la map des paramètres du rapport.<br>
     * La source doit avoir été validée.
     * @param source Source de données servant de base au rapport.
     * @return Map des paramètres du rapport.
     */
    Map<String, Object> buildParameters(Object source);
}
