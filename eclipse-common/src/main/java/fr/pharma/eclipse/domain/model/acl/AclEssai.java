package fr.pharma.eclipse.domain.model.acl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;

/**
 * Classe représentant les droits d'accès des personnes sur les essais.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "acl_essai")
public class AclEssai implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 5904712208674993870L;

    /**
     * Identifiant technique de l'essai.
     */
    @Id
    @Column(name = "id_essai")
    @NotNull
    @Index(name = "acl_essai_id_essai")
    private Long idEssai;

    /**
     * Identifiant technique de la personne.
     */
    @Id
    @Column(name = "id_personne")
    @NotNull
    @Index(name = "acl_essai_id_personne")
    private Long idPersonne;

    /**
     * Getter pour idEssai.
     * @return Le idEssai
     */
    public Long getIdEssai() {
        return this.idEssai;
    }

    /**
     * Setter pour idEssai.
     * @param idEssai Le idEssai à écrire.
     */
    public void setIdEssai(final Long idEssai) {
        this.idEssai = idEssai;
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
