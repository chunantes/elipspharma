package fr.pharma.eclipse.domain.criteria.stock;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.enums.EtatRetour;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.produit.detail.DetailStockage;

/**
 * Description de la classe.
 
 * @version $Revision$ $Date$
 */
public class RetourPatientSearchCriteria
    extends AbstractSearchCriteria
    implements SearchCriteria
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -8577995392468347459L;

    /**
     * Patient.
     */
    private Patient patient;

    /**
     * Essai.
     */
    private Essai essai;

    /**
     * Produit.
     */
    private Produit produit;

    /**
     * Etat.
     */
    private EtatRetour etat;

    /**
     * Stockage.
     */
    private DetailStockage stockage;

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear()
    {
        this.produit = null;
        this.etat = null;
        this.stockage = null;
        this.essai = null;
        this.patient = null;
    }

    /**
     * Getter sur patient.
     * @return Retourne le patient.
     */
    public Patient getPatient()
    {
        return this.patient;
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
     * Getter sur produit.
     * @return Retourne le produit.
     */
    public Produit getProduit()
    {
        return this.produit;
    }

    /**
     * Setter pour patient.
     * @param patient le patient à écrire.
     */
    public void setPatient(final Patient patient)
    {
        this.patient = patient;
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
     * Setter pour produit.
     * @param produit le produit à écrire.
     */
    public void setProduit(final Produit produit)
    {
        this.produit = produit;
    }

    /**
     * Getter pour etat.
     * @return Le etat
     */
    public EtatRetour getEtat()
    {
        return this.etat;
    }

    /**
     * Setter pour etat.
     * @param etat Le etat à écrire.
     */
    public void setEtat(final EtatRetour etat)
    {
        this.etat = etat;
    }

    /**
     * Getter pour stockage.
     * @return Le stockage
     */
    public DetailStockage getStockage()
    {
        return this.stockage;
    }

    /**
     * Setter pour stockage.
     * @param stockage Le stockage à écrire.
     */
    public void setStockage(final DetailStockage stockage)
    {
        this.stockage = stockage;
    }

}
