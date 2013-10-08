package fr.pharma.eclipse.domain.enums.design;

import java.util.Calendar;

/**
 * Enumération représentant le type de temps (utilisé dans les prescription).
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum UniteTemps {

    /**
     * Minute.
     */
    MINUTE("Minute", "m", "minute(s)", Calendar.MINUTE),

    /**
     * Heure.
     */
    HEURE("Heure", "h", "heure(s)", Calendar.HOUR),

    /**
     * Jour.
     */
    JOUR("Jour", "j", "jour(s)", Calendar.DAY_OF_YEAR),

    /**
     * Semaine.
     */
    SEMAINE("Semaine", "s", "semaine(s)", Calendar.WEEK_OF_YEAR),

    /**
     * Mois.
     */
    MOIS("Mois", "m", "mois", Calendar.MONTH);

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Libelle court.
     */
    private String libelleCourt;

    /**
     * Libellé au pluriel.
     */
    private String libPluriel;

    /**
     * Propriété liée au calendar.
     */
    private Integer calendarProperty;

    /**
     * Constructeur.
     * @param libelle Le libellé.
     * @param libelleCourt Le libelle court.
     */
    UniteTemps(final String libelle, final String libelleCourt, final String libPluriel, final Integer calendarPropertie) {
        this.libelle = libelle;
        this.libelleCourt = libelleCourt;
        this.libPluriel = libPluriel;
        this.calendarProperty = calendarPropertie;
    }

    /**
     * Getter pour libelle.
     * @return Retourne le libelle.
     */
    public String getLibelle() {
        return this.libelle;
    }

    /**
     * Getter sur libelleCourt.
     * @return Retourne le libelleCourt.
     */
    public String getLibelleCourt() {
        return this.libelleCourt;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.getLibelle();
    }

    /**
     * Getter sur libPluriel.
     * @return Retourne le libPluriel.
     */
    public String getLibPluriel() {
        return this.libPluriel;
    }

    /**
     * Setter pour libPluriel.
     * @param libPluriel le libPluriel à écrire.
     */
    public void setLibPluriel(final String libPluriel) {
        this.libPluriel = libPluriel;
    }

    /**
     * Getter pour calendarProperty.
     * @return Le calendarProperty
     */
    public Integer getCalendarProperty() {
        return this.calendarProperty;
    }

    /**
     * Setter pour calendarProperty.
     * @param calendarProperty Le calendarProperty à écrire.
     */
    public void setCalendarProperty(final Integer calendarProperty) {
        this.calendarProperty = calendarProperty;
    }

}
