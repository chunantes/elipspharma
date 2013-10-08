package fr.pharma.eclipse.domain.model.suivi.common;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import fr.pharma.eclipse.domain.model.common.BeanObject;

/**
 * Classe abstraite représentant un suivi de modification.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@MappedSuperclass
public abstract class Suivi extends BeanObject implements Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -6983697791778322850L;

    /**
     * Date de mise à jour de l'objet.
     */
    @Column(name = "dateMaj")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateMaj;

    /**
     * Identification de l'utilisateur ayant fait la mise à jour.
     */
    @Column(name = "majPar")
    @NotNull
    @NotEmpty
    private String majPar;

    /**
     * Getter sur dateMaj.
     * @return Retourne le dateMaj.
     */
    public Calendar getDateMaj() {
        return this.dateMaj;
    }

    /**
     * Setter pour dateMaj.
     * @param dateMaj le dateMaj à écrire.
     */
    public void setDateMaj(final Calendar dateMaj) {
        this.dateMaj = dateMaj;
    }

    /**
     * Getter sur majPar.
     * @return Retourne le majPar.
     */
    public String getMajPar() {
        return this.majPar;
    }

    /**
     * Setter pour majPar.
     * @param majPar le majPar à écrire.
     */
    public void setMajPar(final String majPar) {
        this.majPar = majPar;
    }

}
