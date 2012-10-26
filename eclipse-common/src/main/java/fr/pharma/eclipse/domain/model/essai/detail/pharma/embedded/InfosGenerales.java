package fr.pharma.eclipse.domain.model.essai.detail.pharma.embedded;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import fr.pharma.eclipse.domain.enums.QualiteInsu;
import fr.pharma.eclipse.domain.enums.UniteTempsPrevision;
import fr.pharma.eclipse.domain.enums.produit.TypeProduit;

/**
 * Informations generales relatives aux donnees pharma d'un essai clinique.
 
 * @version $Revision$ $Date$
 */
public class InfosGenerales
    implements Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -2432275351486052947L;

    /**
     * Type de produit evalué.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "typeProduitEvalue")
    private TypeProduit typeProduitEvalue;

    /**
     * Qualité de l'insu.
     */
    @Column(name = "qualiteInsu")
    private QualiteInsu qualiteInsu;

    /**
     * Durée totale prévue .
     */
    @Column(name = "dureeTotalePrevue")
    private BigDecimal dureeTotalePrevue;

    /**
     * Unité de la durée totale.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "uniteDureeTotalePrevue")
    private UniteTempsPrevision uniteDureeTotalePrevue;

    /**
     * Durée totale prévue par patient(jours).
     */
    @Column(name = "dureeTotalePatientPrevue")
    private BigDecimal dureeTotalePatientPrevue;

    /**
     * Unité de la durée totale par patient.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "uniteDureeTotalePatientPrevue")
    private UniteTempsPrevision uniteDureeTotalePatientPrevue;

    /**
     * Nombre de centres prévus.
     */
    @Column(name = "nbCentresPrevus")
    private Integer nbCentresPrevus = 0;

    /**
     * Nombre de patients prévus.
     */
    @Column(name = "nbPatientsPrevus")
    private Integer nbPatientsPrevus = 0;

    /**
     * Numéro de centre.
     */
    @Column(name = "numeroCentre")
    private String numeroCentre;

    /**
     * Nombre de patients prévus.
     */
    @Column(name = "nbPatientsPrevusTotal")
    private Integer nbPatientsPrevusTotal = 0;

    /**
     * Getter sur typeProduitEvalue.
     * @return Retourne le typeProduitEvalue.
     */
    public TypeProduit getTypeProduitEvalue()
    {
        return this.typeProduitEvalue;
    }

    /**
     * Setter pour typeProduitEvalue.
     * @param typeProduitEvalue le typeProduitEvalue à écrire.
     */
    public void setTypeProduitEvalue(final TypeProduit typeProduitEvalue)
    {
        this.typeProduitEvalue = typeProduitEvalue;
    }

    /**
     * Getter sur qualiteInsu.
     * @return Retourne le qualiteInsu.
     */
    public QualiteInsu getQualiteInsu()
    {
        return this.qualiteInsu;
    }

    /**
     * Setter pour qualiteInsu.
     * @param qualiteInsu le qualiteInsu à écrire.
     */
    public void setQualiteInsu(final QualiteInsu qualiteInsu)
    {
        this.qualiteInsu = qualiteInsu;
    }

    /**
     * Getter sur nbCentresPrevus.
     * @return Retourne le nbCentresPrevus.
     */
    public Integer getNbCentresPrevus()
    {
        return this.nbCentresPrevus;
    }

    /**
     * Setter pour nbCentresPrevus.
     * @param nbCentresPrevus le nbCentresPrevus à écrire.
     */
    public void setNbCentresPrevus(final Integer nbCentresPrevus)
    {
        this.nbCentresPrevus = nbCentresPrevus;
    }

    /**
     * Getter pour dureeTotalePrevue.
     * @return Le dureeTotalePrevue
     */
    public BigDecimal getDureeTotalePrevue()
    {
        return this.dureeTotalePrevue;
    }

    /**
     * Setter pour dureeTotalePrevue.
     * @param dureeTotalePrevue Le dureeTotalePrevue à écrire.
     */
    public void setDureeTotalePrevue(final BigDecimal dureeTotalePrevue)
    {
        this.dureeTotalePrevue = dureeTotalePrevue;
    }

    /**
     * Getter pour dureeTotalePatientPrevue.
     * @return Le dureeTotalePatientPrevue
     */
    public BigDecimal getDureeTotalePatientPrevue()
    {
        return this.dureeTotalePatientPrevue;
    }

    /**
     * Setter pour dureeTotalePatientPrevue.
     * @param dureeTotalePatientPrevue Le dureeTotalePatientPrevue à écrire.
     */
    public void setDureeTotalePatientPrevue(final BigDecimal dureeTotalePatientPrevue)
    {
        this.dureeTotalePatientPrevue = dureeTotalePatientPrevue;
    }

    /**
     * Getter sur nbPatientsPrevus.
     * @return Retourne le nbPatientsPrevus.
     */
    public Integer getNbPatientsPrevus()
    {
        return this.nbPatientsPrevus;
    }

    /**
     * Setter pour nbPatientsPrevus.
     * @param nbPatientsPrevus le nbPatientsPrevus à écrire.
     */
    public void setNbPatientsPrevus(final Integer nbPatientsPrevus)
    {
        this.nbPatientsPrevus = nbPatientsPrevus;
    }

    /**
     * Getter sur uniteDureeTotalePrevue.
     * @return Retourne le uniteDureeTotalePrevue.
     */
    public UniteTempsPrevision getUniteDureeTotalePrevue()
    {
        return this.uniteDureeTotalePrevue;
    }

    /**
     * Getter sur uniteDureeTotalePatientPrevue.
     * @return Retourne le uniteDureeTotalePatientPrevue.
     */
    public UniteTempsPrevision getUniteDureeTotalePatientPrevue()
    {
        return this.uniteDureeTotalePatientPrevue;
    }

    /**
     * Setter pour uniteDureeTotalePrevue.
     * @param uniteDureeTotalePrevue le uniteDureeTotalePrevue à écrire.
     */
    public void setUniteDureeTotalePrevue(final UniteTempsPrevision uniteDureeTotalePrevue)
    {
        this.uniteDureeTotalePrevue = uniteDureeTotalePrevue;
    }

    /**
     * Setter pour uniteDureeTotalePatientPrevue.
     * @param uniteDureeTotalePatientPrevue le uniteDureeTotalePatientPrevue à écrire.
     */
    public void setUniteDureeTotalePatientPrevue(final UniteTempsPrevision uniteDureeTotalePatientPrevue)
    {
        this.uniteDureeTotalePatientPrevue = uniteDureeTotalePatientPrevue;
    }

    /**
     * Getter sur nbPatientsPrevusTotal.
     * @return Retourne le nbPatientsPrevusTotal.
     */
    public Integer getNbPatientsPrevusTotal()
    {
        return this.nbPatientsPrevusTotal;
    }

    /**
     * Setter pour nbPatientsPrevusTotal.
     * @param nbPatientsPrevusTotal le nbPatientsPrevusTotal à écrire.
     */
    public void setNbPatientsPrevusTotal(final Integer nbPatientsPrevusTotal)
    {
        this.nbPatientsPrevusTotal = nbPatientsPrevusTotal;
    }

    /**
     * Getter pour numeroCentre.
     * @return Le numeroCentre
     */
    public String getNumeroCentre()
    {
        return this.numeroCentre;
    }

    /**
     * Setter pour numeroCentre.
     * @param numeroCentre Le numeroCentre à écrire.
     */
    public void setNumeroCentre(final String numeroCentre)
    {
        this.numeroCentre = numeroCentre;
    }

}
