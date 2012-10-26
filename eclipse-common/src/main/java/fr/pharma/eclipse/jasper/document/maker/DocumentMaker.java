package fr.pharma.eclipse.jasper.document.maker;

import fr.pharma.eclipse.domain.enums.jasper.TypeRapportJasper;
import fr.pharma.eclipse.jasper.exception.JasperReportBuildException;

/**
 * Maker en charge de générer les document en fonction du type de rapport.
 
 * @version $Revision$ $Date$
 */
public interface DocumentMaker
{

    /**
     * Méthode en charge de générer un document en fonction du type de rapport.
     * @param type Type de rapport.
     * @param source Source de données.
     * @throws JasperReportBuildException en cas d'erreur.
     */
    void make(TypeRapportJasper type,
              Object source)
        throws JasperReportBuildException;
}
