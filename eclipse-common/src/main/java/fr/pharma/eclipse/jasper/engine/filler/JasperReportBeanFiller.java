package fr.pharma.eclipse.jasper.engine.filler;

import java.io.Serializable;

import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.domain.model.essai.Essai;

/**
 * Interface des helpers pour la construction d'une source de données de rapport Jasper.
 
 * @version $Revision$ $Date$
 */
public interface JasperReportBeanFiller
    extends Serializable
{

    /**
     * Méthode en charge de compléter la source de données à partir de l'essai.
     * @param essai Essai contenant les données de départ.
     * @param bean Source de données à compléter.
     */
    void fill(Essai essai,
              JasperReportBean bean);

}
