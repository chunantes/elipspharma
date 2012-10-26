package fr.pharma.eclipse.domain.model.alerte;

import java.io.Serializable;

import fr.pharma.eclipse.domain.enums.alerte.TypeAlerte;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Bean métier représentant une alerte.
 
 * @version $Revision$ $Date$
 */
public class Alerte
    implements Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3179029462762872036L;

    /**
     * Essai concernant l'alerte.
     */
    private Essai essai;

    /**
     * Pharmacie concernant l'alerte.
     */
    private Pharmacie pharmacie;

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
     */
    public Alerte(final TypeAlerte typeAlerte)
    {
        this.setTypeAlerte(typeAlerte);
    }

    /**
     * Getter pour essai.
     * @return Le essai
     */
    public Essai getEssai()
    {
        return this.essai;
    }

    /**
     * Setter pour essai.
     * @param essai Le essai à écrire.
     */
    public void setEssai(final Essai essai)
    {
        this.essai = essai;
    }

    /**
     * Getter pour pharmacie.
     * @return Le pharmacie
     */
    public Pharmacie getPharmacie()
    {
        return this.pharmacie;
    }

    /**
     * Setter pour pharmacie.
     * @param pharmacie Le pharmacie à écrire.
     */
    public void setPharmacie(final Pharmacie pharmacie)
    {
        this.pharmacie = pharmacie;
    }

    /**
     * Getter pour typeAlerte.
     * @return Le typeAlerte
     */
    public TypeAlerte getTypeAlerte()
    {
        return this.typeAlerte;
    }

    /**
     * Setter pour typeAlerte.
     * @param typeAlerte Le typeAlerte à écrire.
     */
    public void setTypeAlerte(final TypeAlerte typeAlerte)
    {
        this.typeAlerte = typeAlerte;
    }

    /**
     * Getter pour libelle.
     * @return Le libelle
     */
    public String getLibelle()
    {
        return this.libelle;
    }

    /**
     * Setter pour libelle.
     * @param libelle Le libelle à écrire.
     */
    public void setLibelle(final String libelle)
    {
        this.libelle = libelle;
    }
}
