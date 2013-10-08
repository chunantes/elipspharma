package fr.pharma.eclipse.jasper.engine.helper;

import java.io.Serializable;

import fr.pharma.eclipse.jasper.exception.JasperReportBuildException;

/**
 * Classe de helper pour la levée d'exception JasperReportBuildException<br>
 * lors des vérifications pour valider les sources de données des
 * FicheInfosEssaiDatasBuilder.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class SourceCheckingHandler implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 7384259959861996617L;

    /**
     * Méthode qui lève une exception si une condition n'est pas vérifiée.
     * @param check Résultat de la condition à vérifier.
     * @param messageErreur Message de l'exception à lever.
     * @throws JasperReportBuildException En cas d'invalidité d'une condition.
     */
    public void handleCheck(final boolean check,
                            final String messageErreur) throws JasperReportBuildException {
        if (check) {
            return;
        }
        throw new JasperReportBuildException(messageErreur);
    }

}
