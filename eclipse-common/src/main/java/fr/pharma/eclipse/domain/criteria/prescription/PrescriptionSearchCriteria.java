package fr.pharma.eclipse.domain.criteria.prescription;

import java.util.Calendar;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;
import fr.pharma.eclipse.domain.dto.EssaiDTO;
import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.domain.model.patient.Patient;

/**
 * Critère de recherche sur Prescription.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PrescriptionSearchCriteria extends AbstractSearchCriteria {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 1718231433541560688L;

    /**
     * Inclusion.
     */
    private Inclusion inclusion;

    /**
     * Essai.
     */
    private Essai essai;

    /**
     * Essai DTO.
     */
    private EssaiDTO essaiDTO;

    /**
     * Patient.
     */
    private Patient patient;

    /**
     * Dispensé.
     */
    private Boolean dispense;

    /**
     * Dispensé.
     */
    private boolean withDispensationsNonDispenseesOnly = false;

    public boolean isWithDispensationsNonDispenseesOnly() {
        return this.withDispensationsNonDispenseesOnly;
    }

    public void setWithDispensationsNonDispenseesOnly(final boolean allDispensationsDispensees) {
        this.withDispensationsNonDispenseesOnly = allDispensationsDispensees;
    }

    /**
     * Date de début.
     */
    private Calendar dateDebut;

    /**
     * Date de fin.
     */
    private Calendar dateFin;

    /**
     * Sequence.
     */
    private Sequence sequence;

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.inclusion = null;
        this.patient = null;
        this.essai = null;
        this.dispense = null;
        this.dateDebut = null;
        this.sequence = null;
        this.dateFin = null;
    }

    /**
     * Getter sur inclusion.
     * @return Retourne le inclusion.
     */
    public Inclusion getInclusion() {
        return this.inclusion;
    }

    /**
     * Setter pour inclusion.
     * @param inclusion le inclusion à écrire.
     */
    public void setInclusion(final Inclusion inclusion) {
        this.inclusion = inclusion;
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
     * Getter sur dispense.
     * @return Retourne le dispense.
     */
    public Boolean getDispense() {
        return this.dispense;
    }

    /**
     * Setter pour dispense.
     * @param dispense le dispense à écrire.
     */
    public void setDispense(final Boolean dispense) {
        this.dispense = dispense;
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
     * Getter sur sequence.
     * @return Retourne le sequence.
     */
    public Sequence getSequence() {
        return this.sequence;
    }

    /**
     * Setter pour sequence.
     * @param sequence le sequence à écrire.
     */
    public void setSequence(final Sequence sequence) {
        this.sequence = sequence;
    }

    /**
     * Getter pour essaiDTO.
     * @return Le essaiDTO
     */
    public EssaiDTO getEssaiDTO() {
        return this.essaiDTO;
    }

    /**
     * Setter pour essaiDTO.
     * @param essaiDTO Le essaiDTO à écrire.
     */
    public void setEssaiDTO(final EssaiDTO essaiDTO) {
        this.essaiDTO = essaiDTO;
    }

}
