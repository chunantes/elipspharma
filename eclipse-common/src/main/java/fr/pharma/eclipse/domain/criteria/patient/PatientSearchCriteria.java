package fr.pharma.eclipse.domain.criteria.patient;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;
import fr.pharma.eclipse.domain.enums.TypeRechercheParEssai;
import fr.pharma.eclipse.domain.model.essai.Essai;

/**
 * Critère de recherche sur Patient.
 
 * @version $Revision$ $Date$
 */
public class PatientSearchCriteria
    extends AbstractSearchCriteria
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2243992861729878437L;

    /**
     * Nom.
     */
    private String nom;

    /**
     * Prenom.
     */
    private String prenom;

    /**
     * Numéro IPP.
     */
    private String numeroIpp;

    /**
     * Numéro IPP.
     */
    private String numeroIppExact;

    /**
     * Initiales.
     */
    private String initiales;

    /**
     * Recherche multiple sur numero IPP, nom , prenom.
     */
    private String numeroIppOrNomOrPrenom;

    /**
     * Inclu dans un Essai.
     */
    private Essai essai;

    /**
     * Inclu dans un Essai (recherche par type d'inclusion).
     */
    private Essai essaiByType;

    /**
     * Type de Recherche par essai.
     */
    private TypeRechercheParEssai byEssai;

    /**
     * Getter pour nom.
     * @return Retourne le nom.
     */
    public String getNom()
    {
        return this.nom;
    }

    /**
     * Setter pour nom.
     * @param nom le nom à écrire.
     */
    public void setNom(final String nom)
    {
        this.nom = nom;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear()
    {
        this.setNom(null);
        this.setNumeroIpp(null);
        this.setNumeroIppOrNomOrPrenom(null);
        this.setPrenom(null);
        this.setEssaiByType(null);
        this.setByEssai(null);
        this.setEssai(null);
    }

    /**
     * Getter sur numeroIpp.
     * @return Retourne le numeroIpp.
     */
    public String getNumeroIpp()
    {
        return this.numeroIpp;
    }

    /**
     * Setter pour numeroIpp.
     * @param numeroIpp le numeroIpp à écrire.
     */
    public void setNumeroIpp(final String numeroIpp)
    {
        this.numeroIpp = numeroIpp;
    }

    /**
     * Getter sur prenom.
     * @return Retourne le prenom.
     */
    public String getPrenom()
    {
        return this.prenom;
    }

    /**
     * Setter pour prenom.
     * @param prenom le prenom à écrire.
     */
    public void setPrenom(final String prenom)
    {
        this.prenom = prenom;
    }

    /**
     * Getter sur numeroIppOrNomOrPrenom.
     * @return Retourne le numeroIppOrNomOrPrenom.
     */
    public String getNumeroIppOrNomOrPrenom()
    {
        return this.numeroIppOrNomOrPrenom;
    }

    /**
     * Setter pour numeroIppOrNomOrPrenom.
     * @param numeroIppOrNomOrPrenom le numeroIppOrNomOrPrenom à écrire.
     */
    public void setNumeroIppOrNomOrPrenom(final String numeroIppOrNomOrPrenom)
    {
        this.numeroIppOrNomOrPrenom = numeroIppOrNomOrPrenom;
    }

    /**
     * Getter sur essai.
     * @return Retourne le essai.
     */
    public Essai getEssai()
    {
        return this.essai;
    }

    /**
     * Setter pour essai.
     * @param essai le essai à écrire.
     */
    public void setEssai(final Essai essai)
    {
        this.essai = essai;
    }

    /**
     * Getter sur initiales.
     * @return Retourne le initiales.
     */
    public String getInitiales()
    {
        return this.initiales;
    }

    /**
     * Setter pour initiales.
     * @param initiales le initiales à écrire.
     */
    public void setInitiales(final String initiales)
    {
        this.initiales = initiales;
    }

    /**
     * Getter pour numeroIppExact.
     * @return Le numeroIppExact
     */
    public String getNumeroIppExact()
    {
        return this.numeroIppExact;
    }

    /**
     * Setter pour numeroIppExact.
     * @param numeroIppExact Le numeroIppExact à écrire.
     */
    public void setNumeroIppExact(final String numeroIppExact)
    {
        this.numeroIppExact = numeroIppExact;
    }

    /**
     * Getter pour byEssai.
     * @return Le byEssai
     */
    public TypeRechercheParEssai getByEssai()
    {
        return this.byEssai;
    }

    /**
     * Setter pour byEssai.
     * @param byEssai Le byEssai à écrire.
     */
    public void setByEssai(final TypeRechercheParEssai byEssai)
    {
        this.byEssai = byEssai;
    }

    /**
     * Getter pour essaiByType.
     * @return Le essaiByType
     */
    public Essai getEssaiByType()
    {
        return this.essaiByType;
    }

    /**
     * Setter pour essaiByType.
     * @param essaiByType Le essaiByType à écrire.
     */
    public void setEssaiByType(final Essai essaiByType)
    {
        this.essaiByType = essaiByType;
    }

}
