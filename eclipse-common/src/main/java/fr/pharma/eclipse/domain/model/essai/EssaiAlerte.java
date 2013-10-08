package fr.pharma.eclipse.domain.model.essai;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Bean contenant une extraction des informations d'essai nécessaires aux
 * alertes.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EssaiAlerte implements Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -41829791910231272L;

    /**
     * Identifiant de l'essai.
     */
    private Long id;

    /**
     * Numéro interne de l'essai.
     */
    private String numInterne;

    /**
     * Nom de l'essai.
     */
    private String nom;

    /**
     * Date réelle de fin de l'essai.
     */
    private Calendar finEtude;

    /**
     * Date de la visite de cloture.
     */
    private Calendar cloture;

    /**
     * Getter pour finEtude.
     * @return Le finEtude
     */
    public Calendar getFinEtude() {
        return this.finEtude;
    }

    /**
     * Setter pour finEtude.
     * @param finEtude Le finEtude à écrire.
     */
    public void setFinEtude(final Calendar finEtude) {
        this.finEtude = finEtude;
    }

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
     * Getter pour cloture.
     * @return Le cloture
     */
    public Calendar getCloture() {
        return this.cloture;
    }

    /**
     * Setter pour cloture.
     * @param cloture Le cloture à écrire.
     */
    public void setCloture(final Calendar cloture) {
        this.cloture = cloture;
    }

}
