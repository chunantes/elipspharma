package fr.pharma.eclipse.domain.criteria.patient;

import java.util.Calendar;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Patient;

/**
 * Critère de recherche sur Inclusion.
 
 * @version $Revision$ $Date$
 */
public class InclusionSearchCriteria
    extends AbstractSearchCriteria
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 409404267176407213L;

    /**
     * Essai.
     */
    private Essai essai;

    /**
     * Date de début.
     */
    private Calendar dateDebut;

    /**
     * Date de fin.
     */
    private Calendar dateFin;

    /**
     * Actif.
     */
    private Boolean actif;

    /**
     * Patient.
     */
    private Patient patient;

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear()
    {
        this.setEssai(null);
        this.actif = null;
        this.dateDebut = null;
        this.dateFin = null;
        this.patient = null;
    }
    /**
     * Getter sur essai.
     * @return Retourne le essai.
     */
    public Essai getEssai()
    {
        return this.essai;
    }

    /**
     * Setter pour essai.
     * @param essai le essai à écrire.
     */
    public void setEssai(final Essai essai)
    {
        this.essai = essai;
    }

    /**
     * Getter sur dateDebut.
     * @return Retourne le dateDebut.
     */
    public Calendar getDateDebut()
    {
        return this.dateDebut;
    }

    /**
     * Setter pour dateDebut.
     * @param dateDebut le dateDebut à écrire.
     */
    public void setDateDebut(final Calendar dateDebut)
    {
        this.dateDebut = dateDebut;
    }

    /**
     * Getter sur dateFin.
     * @return Retourne le dateFin.
     */
    public Calendar getDateFin()
    {
        return this.dateFin;
    }

    /**
     * Setter pour dateFin.
     * @param dateFin le dateFin à écrire.
     */
    public void setDateFin(final Calendar dateFin)
    {
        this.dateFin = dateFin;
    }

    /**
     * Getter sur actif.
     * @return Retourne le actif.
     */
    public Boolean getActif()
    {
        return this.actif;
    }

    /**
     * Setter pour actif.
     * @param actif le actif à écrire.
     */
    public void setActif(final Boolean actif)
    {
        this.actif = actif;
    }
    /**
     * Getter pour patient.
     * @return Le patient
     */
    public Patient getPatient()
    {
        return this.patient;
    }
    /**
     * Setter pour patient.
     * @param patient Le patient à écrire.
     */
    public void setPatient(final Patient patient)
    {
        this.patient = patient;
    }

}
