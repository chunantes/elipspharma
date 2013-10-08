package fr.pharma.eclipse.domain.model.sigrec.essai.detail;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;

/**
 * Classe métier représentant une Prevision importé de SIGREC.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "prevision_sigrec")
public class PrevisionSigrec extends BeanObject {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 496673274826405169L;

    /**
     * Essai auquel est rattaché la prévision.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_essai")
    private EssaiSigrec essai;

    /**
     * Nombre de centres prévus.
     */
    @Column(name = "nbCentres")
    private Integer nbCentres;

    /**
     * Durée totale.
     */
    @Column(name = "dureeTotale")
    private Integer dureeTotale;

    /**
     * Date de début.
     */
    @Column(name = "dateDebut")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateDebut;

    /**
     * Date de fin.
     */
    @Column(name = "dateFin")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateFin;

    /**
     * Getter sur nbCentres.
     * @return Retourne le nbCentres.
     */
    public Integer getNbCentres() {
        return this.nbCentres;
    }

    /**
     * Setter pour nbCentres.
     * @param nbCentres le nbCentres à écrire.
     */
    public void setNbCentres(final Integer nbCentres) {
        this.nbCentres = nbCentres;
    }

    /**
     * Getter sur dureeTotale.
     * @return Retourne le dureeTotale.
     */
    public Integer getDureeTotale() {
        return this.dureeTotale;
    }

    /**
     * Setter pour dureeTotale.
     * @param dureeTotale le dureeTotale à écrire.
     */
    public void setDureeTotale(final Integer dureeTotale) {
        this.dureeTotale = dureeTotale;
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
    public EssaiSigrec getEssai() {
        return this.essai;
    }

    /**
     * Setter pour essai.
     * @param essai le essai à écrire.
     */
    public void setEssai(final EssaiSigrec essai) {
        this.essai = essai;
    }

}
