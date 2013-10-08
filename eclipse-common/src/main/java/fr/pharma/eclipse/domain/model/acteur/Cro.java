package fr.pharma.eclipse.domain.model.acteur;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Classe métier représentant un CRO.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("CRO")
public class Cro extends Personne {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -5686639756295128019L;

    /**
     * Nom de la société.
     */
    @Column(name = "nomSociete")
    private String nomSociete;

    /**
     * Getter pour nomSociete.
     * @return Le nomSociete
     */
    public String getNomSociete() {
        return this.nomSociete;
    }

    /**
     * Setter pour nomSociete.
     * @param nomSociete Le nomSociete à écrire.
     */
    public void setNomSociete(final String nomSociete) {
        this.nomSociete = nomSociete;
    }

}
