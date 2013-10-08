package fr.pharma.eclipse.domain.dto;

import java.io.Serializable;

/**
 * Classe DTO d'essai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EssaiDTO implements Serializable {

    /**
     * serial UID.
     */
    private static final long serialVersionUID = -5064514232361006294L;

    /**
     * Identifiant d'essai.
     */
    private Long id;

    /**
     * Nom de l'essai.
     */
    private String nom;

    /**
     * Numéro d'identification interne.
     */
    private String numInterne;

    /**
     * Raison sociale du promoteur.
     */
    private String raisonSociale;

    /**
     * Getter pour id.
     * @return Le id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Setter pour id.
     * @param id Le id à écrire.
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Getter pour nom.
     * @return Le nom
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Setter pour nom.
     * @param nom Le nom à écrire.
     */
    public void setNom(final String nom) {
        this.nom = nom;
    }

    /**
     * Getter pour numInterne.
     * @return Le numInterne
     */
    public String getNumInterne() {
        return this.numInterne;
    }

    /**
     * Setter pour numInterne.
     * @param numInterne Le numInterne à écrire.
     */
    public void setNumInterne(final String numInterne) {
        this.numInterne = numInterne;
    }

    /**
     * Getter pour raisonSociale.
     * @return Le raisonSociale
     */
    public String getRaisonSociale() {
        return this.raisonSociale;
    }

    /**
     * Setter pour raisonSociale.
     * @param raisonSociale Le raisonSociale à écrire.
     */
    public void setRaisonSociale(final String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

}
