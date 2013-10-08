package fr.pharma.eclipse.domain.model.acl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;

/**
 * Classe représentant les droits d'accès des personnes sur les pharmacies.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "acl_pharmacie")
public class AclPharmacie implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -1008349430286101381L;

    /**
     * Constructeur d'un AclPharmacie.
     * @param idPersonne Identifiant de la personne.
     * @param idPharmacie Identifiant de la pharmacie.
     */
    public AclPharmacie(final Long idPersonne, final Long idPharmacie) {
        this.setIdPersonne(idPersonne);
        this.setIdPharmacie(idPharmacie);
    }

    /**
     * Identifiant technique de la pharmacie.
     */
    @Id
    @Column(name = "id_pharmacie")
    @NotNull
    @Index(name = "acl_pharmacie_id_pharmacie")
    private Long idPharmacie;

    /**
     * Identifiant technique de la personne.
     */
    @Id
    @Column(name = "id_personne")
    @NotNull
    @Index(name = "acl_pharmacie_id_personne")
    private Long idPersonne;

    /**
     * Getter pour idPharmacie.
     * @return Le idPharmacie
     */
    public Long getIdPharmacie() {
        return this.idPharmacie;
    }

    /**
     * Setter pour idPharmacie.
     * @param idPharmacie Le idPharmacie à écrire.
     */
    public void setIdPharmacie(final Long idPharmacie) {
        this.idPharmacie = idPharmacie;
    }

    /**
     * Getter pour idPersonne.
     * @return Le idPersonne
     */
    public Long getIdPersonne() {
        return this.idPersonne;
    }

    /**
     * Setter pour idPersonne.
     * @param idPersonne Le idPersonne à écrire.
     */
    public void setIdPersonne(final Long idPersonne) {
        this.idPersonne = idPersonne;
    }

}
