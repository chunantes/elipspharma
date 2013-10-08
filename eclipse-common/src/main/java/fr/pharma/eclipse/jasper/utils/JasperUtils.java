package fr.pharma.eclipse.jasper.utils;

import java.util.Collection;
import java.util.Iterator;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.jasper.constants.JasperConstants;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe outil pour la génération des rapports Jasper.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public final class JasperUtils {

    /**
     * Constructeur privé pour classe utilitaire.
     */
    private JasperUtils() {
        super();
    }

    /**
     * Méthode utilitaire pour la formation du nom d'un investigateur.
     * @param investigateur Investigateur.
     * @return titre + prénom + nom de l'investigateur (valeur par défaut si
     * investigateur nul).
     */
    public static String makeLibelleInvestigateur(final Investigateur investigateur) {
        if (investigateur == null) {
            return JasperConstants.DEFAULT_FIELD_VALUE;
        }
        return new StringBuilder().append(investigateur.getTitre()).append(" ").append(investigateur.getPrenom()).append(" ").append(investigateur.getNom()).toString();
    }

    /**
     * Donne la clé Jasper pour le paramètre donné.
     * @param jasperParamName Nom du paramètre jasper.
     * @return La clé du paramètre jasper qui transite par la requête.
     */
    public static String getKey(final String jasperParamName) {
        final StringBuffer buff = new StringBuffer(JasperConstants.JASPER_PREFIX);
        buff.append(jasperParamName);
        return buff.toString();
    }

    /**
     * Donne le nom du paramètre jasper contenu dans la clé.
     * @param jasperKey La clé (peut être obtenue en utilisant la fonction
     * getKey).
     * @return Le nom du paramètre contenu dans la clé.
     */
    public static String getParameterName(final String jasperKey) {
        String paramName = null;
        if (jasperKey.startsWith(JasperConstants.JASPER_PREFIX)) {
            paramName = jasperKey.substring(JasperConstants.JASPER_PREFIX.length());
        }
        return paramName;
    }

    /**
     * Crée une chaine avec les libellés contenus dans la liste séparés par le
     * séparateur JasperConstants.SEPARATEUR_LIBELLES.
     * @param listeLibelles Liste des libellés.
     * @return Les libellés formattés (chaine vide si la liste est nulle ou
     * vide).
     */
    public static String formatterListeStrings(final Collection<String> listeLibelles) {
        final StringBuffer res = new StringBuffer();
        if ((listeLibelles != null) && !listeLibelles.isEmpty()) {
            final Iterator<String> it = listeLibelles.iterator();
            String courant;
            while (it.hasNext()) {
                courant = it.next();
                res.append(courant);
                if (it.hasNext()) {
                    res.append(JasperConstants.SEPARATEUR_LIBELLES);
                }
            }
        } else {
            res.append(JasperConstants.DEFAULT_FIELD_VALUE);
        }
        return res.toString();
    }

    /**
     * Méthode en charge de transformer un booléen en chaîne de caractères
     * intelligible.
     * @param bool Booléen à transformer.
     * @return Chaîne correspondant au booléen :<br>
     * - null: ""<br>
     * - true: Oui<br>
     * - false: Non.
     */
    public static String transformToOuiNon(final Boolean bool) {
        if (bool == null) {
            return JasperConstants.DEFAULT_FIELD_VALUE;
        } else if (bool) {
            return EclipseConstants.OUI;
        } else {
            return EclipseConstants.NON;
        }
    }

    /**
     * Donne la source de données Jasper relative à la collection donnée en
     * paramètre.
     * @param listData Collection à transformer en source de données pour Jasper
     * @return La source de données Japser (nulle si la liste est nulle)
     */
    public static JRBeanCollectionDataSource createJRDataSource(final Collection<?> listData) {
        JRBeanCollectionDataSource dataSource = null;
        if (listData != null) {
            dataSource = new JRBeanCollectionDataSource(listData);
        }
        return dataSource;
    }
}
