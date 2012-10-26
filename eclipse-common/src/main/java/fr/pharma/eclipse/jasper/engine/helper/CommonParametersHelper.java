package fr.pharma.eclipse.jasper.engine.helper;

import java.io.Serializable;
import java.util.Map;

import fr.pharma.eclipse.jasper.constants.JasperConstants;

/**
 * Helper pour la gestion des paramètres communs aux rapports Jasper.
 
 * @version $Revision$ $Date$
 */
public class CommonParametersHelper
    implements Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3695787250754627005L;

    /**
     * Répertoire des contenant les rapports Jasper.
     */
    private final String reportsDirectory;

    /**
     * CheckboxDirector.
     */
    private final String checkboxDirectory;

    /**
     * Constructeur.
     * @param reportsDirectory Répertoire des contenant les rapports Jasper.
     */
    public CommonParametersHelper(final String reportsDirectory, final String checkboxDirectory)
    {
        this.reportsDirectory = reportsDirectory;
        this.checkboxDirectory = checkboxDirectory;
    }

    /**
     * Méthode en charge d'ajouter les paramètres communs de tous les rapports.
     * @param mapParameters Map des paramètres pour le rapport.
     */
    public void addCommonParameters(final Map<String, Object> mapParameters)
    {
        // Répertoire des rapports.
        this.addIfNotExist(mapParameters,
                           JasperConstants.SUBREPORT_DIRECTORY,
                           this.reportsDirectory);
        // Répertoire des rapports.
        this.addIfNotExist(mapParameters,
                           JasperConstants.CHECKBOX_DIRECTORY,
                           this.checkboxDirectory);

    }

    /**
     * Méthode qui ajoute une paire clé/valeur dans la map, seulement si la clé n'est pas
     * présente.
     * @param mapParameters Map dans laquelle ajouter la paire.
     * @param key Clé.
     * @param value Valeur.
     */
    private void addIfNotExist(final Map<String, Object> mapParameters,
                               final String key,
                               final Object value)
    {
        if (!mapParameters.containsKey(key))
        {
            mapParameters.put(key,
                              value);
        }
    }

    /**
     * Getter sur reportsDirectory.
     * @return Retourne le reportsDirectory.
     */
    String getReportsDirectory()
    {
        return this.reportsDirectory;
    }

    /**
     * Getter pour checkboxDirectory.
     * @return Le checkboxDirectory
     */
    public String getCheckboxDirectory()
    {
        return this.checkboxDirectory;
    }

}
