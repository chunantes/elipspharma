package fr.pharma.eclipse.utils.introspection;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;

import fr.pharma.eclipse.exception.common.CommonException;

/**
 * Classe en charge de fournir des méthodes simplifiées d'introspection.
 * @author Sébastien ROUL
 * @version $Revision$ $Date$
 */
public final class BeanTool {

    /** Afficher null dans le message de log si le bean est null. */
    protected static final String NULL = "null";

    /**
     * Constructeur privé pour classe d'utilitaire.
     */
    private BeanTool() {
        // RAF.
    }

    /**
     * Retourne le nom de la classe de l'objet.
     * @param objet Objet.
     * @return Retourne le nom de la classe de l'objet.
     */
    protected static String getClassName(final Object objet) {
        if (objet == null) {
            return BeanTool.NULL;
        } else {
            return objet.getClass().getName();
        }
    }

    /**
     * Méthode indiquant si la propriété est définie et accessible pour le bean.
     * @param bean Objet à évaluer.
     * @param property Propriété de l'objet à évaluer.
     * @return : Indique que l'on peut accéder à la propriété.
     */
    public static boolean hasProperty(final Object bean,
                                      final String property) {
        if (bean == null) {
            return false;
        }
        try {
            PropertyUtils.getNestedProperty(bean, property);
        } catch (final NestedNullException e) {
            // Le bean ou sa propriété est null.
            return false;
        } catch (final NoSuchMethodException e) {
            // Aucun getter pour la propriété dans la classe
            return false;
        } catch (final IllegalAccessException e) {
            // Getter pour la propriété est non publique dans la classe
            return false;
        } catch (final InvocationTargetException e) {
            return false;
        }
        return true;
    }

    /**
     * Méthode retournant la valeur de la propriété de l'objet en cours.
     * @param bean Objet à évaluer.
     * @param propriete Propriété de l'objet à évaluer.
     * @return : Valeur de la propriété
     * @throws CommonException Erreur technique.
     */
    public static Object getPropriete(final Object bean,
                                      final String propriete) throws CommonException {

        try {
            return PropertyUtils.getNestedProperty(bean, propriete);
        } catch (final NestedNullException e) {
            return null;
        } catch (final NoSuchMethodException e) {
            final String error = "Aucun getter pour la propriété " + propriete + " dans la classe   " + BeanTool.getClassName(bean);
            throw new CommonException(error, e);

        } catch (final IllegalAccessException e) {
            final String error = "Le getter pour la propriété " + propriete + " dans la classe " + BeanTool.getClassName(bean) + " doit être publique";
            throw new CommonException(error, e);
        } catch (final InvocationTargetException e) {
            final Throwable cause = e.getCause();
            throw new CommonException(cause.getMessage(), e);
        }
    }
    /**
     * Définit la valeur de la propriété de l'objet en cours.
     * @param bean Objet à évaluer.
     * @param propertyName Propriété de l'objet à définir.
     * @param propertyValue Valeur à définir
     * @throws CommonException Erreur technique.
     */
    public static void setPropriete(final Object bean,
                                    final String propertyName,
                                    final Object propertyValue) throws CommonException {
        try {
            PropertyUtils.setNestedProperty(bean, propertyName, propertyValue);
        } catch (final NestedNullException e) {
            return;
        } catch (final IllegalArgumentException e) {
            final String error = "Le bean ou la propriété n'est pas défini : " + e.getMessage();
            throw new CommonException(error, e);
        } catch (final NoSuchMethodException e) {
            final String error = "Aucun getter pour la propriété " + propertyName + " dans la classe   " + BeanTool.getClassName(bean);
            throw new CommonException(error, e);
        } catch (final IllegalAccessException e) {
            final String error = "Le getter pour la propriété " + propertyName + " dans la classe " + BeanTool.getClassName(bean) + " doit être publique";
            throw new CommonException(error, e);
        } catch (final InvocationTargetException e) {
            final Throwable cause = e.getCause();
            throw new CommonException(cause.getMessage(), e);
        }
    }

    /**
     * Copie les propriétés de l'objet source vers l'objet cible; NOTE : Si l'un
     * des deux objets est null, aucune copie n'est effectuée.
     * @param destination Objet cible
     * @param source Objet source
     * @throws CommonException Erreur technique.
     */
    public static void copyProperties(final Object destination,
                                      final Object source) throws CommonException {
        if (source == null) {
            return;
        }
        if (destination == null) {
            return;
        }
        try {
            BeanUtils.copyProperties(destination, source);
        } catch (final IllegalAccessException e) {
            final String error = "Une des propriétés de l'objet source n'est pas accessible.";
            throw new CommonException(error, e);
        } catch (final InvocationTargetException e) {
            final String error = "Erreur lors de la recopie des propriétés de l'objet source.";
            throw new CommonException(error, e);
        }
    }

}
