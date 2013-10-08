package fr.pharma.eclipse.domain.model.alerte;

import java.io.Serializable;

import fr.pharma.eclipse.domain.enums.alerte.TypeAlerte;

/**
 * Bean métier représentant une alerte.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class Alerte implements Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3179029462762872036L;

    /**
     * Numéro interne de l'essai.
     */
    private String numInterne;

    /**
     * Nom de l'essai.
     */
    private String nom;

    /**
     * Nom de la mharmacie concernant l'alerte.
     */
    private String nomPharmacie;

    /**
     * Type de l'alerte.
     */
    private TypeAlerte typeAlerte;

    /**
     * Libellé de l'alerte.
     */
    private String libelle;

    /**
     * Constructeur de Alerte.
     * @param typeAlerte Type d'alerte.
     * @param numInterne Numéro interne de l'essai.
     * @param nom Nom de l'essai.
     */
    public Alerte(final TypeAlerte typeAlerte, final String numInterne, final String nom) {
        this.setTypeAlerte(typeAlerte);
        this.setNumInterne(numInterne);
        this.setNom(nom);
    }

    /**
     * Getter pour typeAlerte.
     * @return Le typeAlerte
     */
    public TypeAlerte getTypeAlerte() {
        return this.typeAlerte;
    }

    /**
     * Setter pour typeAlerte.
     * @param typeAlerte Le typeAlerte à écrire.
     */
    public void setTypeAlerte(final TypeAlerte typeAlerte) {
        this.typeAlerte = typeAlerte;
    }

    /**
     * Getter pour libelle.
     * @return Le libelle
     */
    public String getLibelle() {
        return this.libelle;
    }

    /**
     * Setter pour libelle.
     * @param libelle Le libelle à écrire.
     */
    public void setLibelle(final String libelle) {
        this.libelle = libelle;
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
     * Getter pour nomPharmacie.
     * @return Le nomPharmacie
     */
    public String getNomPharmacie() {
        return this.nomPharmacie;
    }

    /**
     * Setter pour nomPharmacie.
     * @param nomPharmacie Le nomPharmacie à écrire.
     */
    public void setNomPharmacie(final String nomPharmacie) {
        this.nomPharmacie = nomPharmacie;
    }
}
