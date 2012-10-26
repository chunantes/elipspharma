package fr.pharma.eclipse.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe utilitaire.
 
 * @version $Revision$ $Date$
 */
public final class Utils
{
    /**
     * Constructeur privé pour éviter toute instanciation.
     */
    private Utils()
    {
        super();
    }

    /**
     * DAY_31.
     */
    private static final int DAY_31 = 31;

    /**
     * Méthode en charge de formater une date en utilisant le formatage passé en paramètre.
     * @param date Date à formater.
     * @param pattern Pattern à utiliser pour le formattage de la date.
     * @return La chaîne de caractères correspondant à la date.
     */
    public static String formatDate(final Date date,
                                    final String pattern)
    {
        return Utils.formatDate(date,
                                pattern,
                                null);
    }
    /**
     * Méthode en charge de formater une date en utilisant le formatage passé en paramètre.
     * @param calendar Calendar à formater.
     * @param pattern Pattern à utiliser pour le formattage de la date.
     * @param defaultValue Valeur retournée par défaut.
     * @return La chaîne de caractères correspondant à la date.
     */
    public static String formatDate(final Calendar calendar,
                                    final String pattern,
                                    final String defaultValue)
    {
        if (calendar == null)
        {
            return defaultValue;
        }
        return Utils.formatDate(calendar.getTime(),
                                pattern,
                                defaultValue);
    }

    /**
     * Méthode en charge de formater une date en utilisant le formatage passé en paramètre.
     * @param date Date à formater.
     * @param pattern Pattern à utiliser pour le formattage de la date.
     * @param defaultValue Valeur retournée par défaut.
     * @return La chaîne de caractères correspondant à la date.
     */
    public static String formatDate(final Date date,
                                    final String pattern,
                                    final String defaultValue)
    {
        final DateFormat df = new SimpleDateFormat(pattern);
        if (date != null)
        {
            return df.format(date);
        }
        else
        {
            return defaultValue;
        }
    }

    /**
     * Fonction en charge de parser une date et de renvoyer le Calendar correspondant.
     * @param strDate Date, sous forme de chaîne de caractères, à parser.
     * @param pattern Pattern à utiliser pour le parsing.
     * @return L'objet Calendar initialisé à la date correspondante.
     * @throws ParseException Si erreur de parsing.
     */
    public static Calendar parseDate(final String strDate,
                                     final String pattern)
        throws ParseException
    {
        final DateFormat df = new SimpleDateFormat(pattern);
        final Date date = df.parse(strDate);
        final Calendar cal = Calendar.getInstance(EclipseConstants.LOCALE);
        cal.setTime(date);
        return cal;
    }

    /**
     * Fonction en charge de créer un calendrier positionné au premier jour de l'année donnée.
     * @param year Année sur laquelle positionner le calendrier.
     * @return Le calendar positionné.
     */
    public static Calendar getCalendarForFirstDayOfYear(final int year)
    {
        return Utils.getCalendarForYear(year,
                                        -1);
    }

    /**
     * Fonction en charge de créer un calendrier positionné au dernier jour de l'année donnée.
     * @param year Année sur laquelle positionner le calendrier.
     * @return Le calendar positionné.
     */
    public static Calendar getCalendarForLastDayOfYear(final int year)
    {
        return Utils.getCalendarForYear(year,
                                        1);
    }

    /**
     * Fonction en charge de créer un calendrier positionné à la première ou la dernière date
     * d'une année.
     * @param year Année sur laquelle positionner le calendrier.
     * @param mode si supérieur ou égal à 0, positionne au 31/12/year; si inférieur à 0,
     * positionne au 01/01/year.
     * @return Le calendar positionné.
     */
    private static Calendar getCalendarForYear(final int year,
                                               final int mode)
    {
        final Calendar cal = Calendar.getInstance(EclipseConstants.LOCALE);
        int day;
        int month;
        if (mode >= 0)
        {
            day = Utils.DAY_31;
            month = Calendar.DECEMBER;
        }
        else
        {
            day = 1;
            month = Calendar.JANUARY;
        }
        cal.set(Calendar.DAY_OF_MONTH,
                day);
        cal.set(Calendar.MONTH,
                month);
        cal.set(Calendar.YEAR,
                year);
        return cal;
    }

    /**
     * Méthode en charge de transformer la stackTrace d'une exception en chaîne de caractères
     * (pour la mettre dans les logs par exemple).
     * @param throwable Exception à traiter.
     * @return La chaîne de caractères correspondant à la stackTrace de l'exception.
     */
    public static String getStringStack(final Throwable throwable)
    {
        final Writer writer = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(writer);
        throwable.printStackTrace(printWriter);
        final String message = writer.toString();
        return message;
    }
}
