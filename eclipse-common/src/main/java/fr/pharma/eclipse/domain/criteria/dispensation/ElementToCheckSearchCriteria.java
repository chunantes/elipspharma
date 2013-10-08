package fr.pharma.eclipse.domain.criteria.dispensation;

import java.util.Calendar;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;
import fr.pharma.eclipse.domain.enums.TypeElementToCheck;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Critère de recherche pour le bean ElementToCheck.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ElementToCheckSearchCriteria extends AbstractSearchCriteria {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -3723646290271230307L;

    /**
     * Type d'ElementToCheck.
     */
    private TypeElementToCheck type;

    /**
     * Date début.
     */
    private Calendar dateDebut;

    /**
     * Date de fin.
     */
    private Calendar dateFin;

    /**
     * Pharmacie.
     */
    private Pharmacie pharmacie;

    /**
     * Patient.
     */
    private Patient patient;

    /**
     * Essai.
     */
    private Essai essai;

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.dateDebut = null;
        this.pharmacie = null;
        this.dateFin = null;
        this.type = null;
        this.essai = null;
    }

    /**
     * Getter sur type.
     * @return Retourne le type.
     */
    public TypeElementToCheck getType() {
        return this.type;
    }

    /**
     * Setter pour type.
     * @param type le type à écrire.
     */
    public void setType(final TypeElementToCheck type) {
        this.type = type;
    }

    /**
     * Getter sur dateDebut.
     * @return Retourne le dateDebut.
     */
    public Calendar getDateDebut() {
        return this.dateDebut;
    }

    /**
     * Setter pour dateDebut.
     * @param dateDebut le dateDebut à écrire.
     */
    public void setDateDebut(final Calendar dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Getter sur dateFin.
     * @return Retourne le dateFin.
     */
    public Calendar getDateFin() {
        return this.dateFin;
    }

    /**
     * Setter pour dateFin.
     * @param dateFin le dateFin à écrire.
     */
    public void setDateFin(final Calendar dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Getter sur essai.
     * @return Retourne le essai.
     */
    public Essai getEssai() {
        return this.essai;
    }

    /**
     * Setter pour essai.
     * @param essai le essai à écrire.
     */
    public void setEssai(final Essai essai) {
        this.essai = essai;
    }

    /**
     * Getter sur patient.
     * @return Retourne le patient.
     */
    public Patient getPatient() {
        return this.patient;
    }

    /**
     * Setter pour patient.
     * @param patient le patient à écrire.
     */
    public void setPatient(final Patient patient) {
        this.patient = patient;
    }

    /**
     * Getter pour pharmacie.
     * @return Le pharmacie
     */
    public Pharmacie getPharmacie() {
        return this.pharmacie;
    }

    /**
     * Setter pour pharmacie.
     * @param pharmacie Le pharmacie à écrire.
     */
    public void setPharmacie(final Pharmacie pharmacie) {
        this.pharmacie = pharmacie;
    }

}
