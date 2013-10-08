package fr.pharma.eclipse.domain.model.actualite;

import java.io.Serializable;

/**
 * Bean contenant les informations d'actualité.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class Actualite implements Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 8348379551797250377L;

    /**
     * Identifiant de l'essai.
     */
    private Long id;

    /**
     * Nom usuel.
     */
    private String nom;

    /**
     * Numéro d'identification interne.
     */
    private String numInterne;

    /**
     * Promoteur.
     */
    private String promoteur;

    /**
     * Code promoteur.
     */
    private String codePromoteur;

    /**
     * DCI.
     */
    private String dci;

    /**
     * Raison sociale.
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
     * Getter pour promoteur.
     * @return Le promoteur
     */
    public String getPromoteur() {
        return this.promoteur;
    }

    /**
     * Setter pour promoteur.
     * @param promoteur Le promoteur à écrire.
     */
    public void setPromoteur(final String promoteur) {
        this.promoteur = promoteur;
    }

    /**
     * Getter pour codePromoteur.
     * @return Le codePromoteur
     */
    public String getCodePromoteur() {
        return this.codePromoteur;
    }

    /**
     * Setter pour codePromoteur.
     * @param codePromoteur Le codePromoteur à écrire.
     */
    public void setCodePromoteur(final String codePromoteur) {
        this.codePromoteur = codePromoteur;
    }

    /**
     * Getter pour dci.
     * @return Le dci
     */
    public String getDci() {
        return this.dci;
    }

    /**
     * Setter pour dci.
     * @param dci Le dci à écrire.
     */
    public void setDci(final String dci) {
        this.dci = dci;
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
