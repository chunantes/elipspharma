package fr.pharma.eclipse.domain.criteria.stock;

import java.util.Calendar;
import java.util.List;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;
import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;

/**
 * Critère de recherche sur Stock.
 
 * @version $Revision$ $Date$
 */
public class StockSearchCriteria
    extends AbstractSearchCriteria
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -1369104241769990392L;

    /**
     * Essai.
     */
    private Essai essai;

    /**
     * Liste des essais.
     */
    private List<Essai> essais;

    /**
     * Pharmacie.
     */
    private Pharmacie pharmacie;

    /**
     * Liste des pharmacies.
     */
    private List<Pharmacie> pharmacies;

    /**
     * Stockage.
     */
    private Stockage stockage;

    /**
     * Numéro de lot.
     */
    private String numLot;

    /**
     * Dénomination du produit.
     */
    private String denominationProduit;

    /**
     * Date de bornage pour la récupération des mouvements.
     */
    private Calendar date;

    /**
     * String contenant les heures/minutes pour la date de bornage de récupération des mouvements.
     */
    private String heuresMinutes;

    /**
     * Conditionnement.
     */
    private Conditionnement conditionnement;

    /**
     * Mode de prescription.
     */
    private ModePrescription modePrescription;

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear()
    {
        this.setEssai(null);
        this.setPharmacie(null);
        this.setStockage(null);
        this.setNumLot(null);
        this.setDenominationProduit(null);
        this.setDate(null);
        this.setHeuresMinutes(null);
        this.setConditionnement(null);
        this.setModePrescription(null);
        this.setEssais(null);
        this.setPharmacies(null);
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
     * Getter pour stockage.
     * @return Le stockage
     */
    public Stockage getStockage()
    {
        return this.stockage;
    }

    /**
     * Setter pour stockage.
     * @param stockage Le stockage à écrire.
     */
    public void setStockage(final Stockage stockage)
    {
        this.stockage = stockage;
    }

    /**
     * Getter pour numLot.
     * @return Le numLot
     */
    public String getNumLot()
    {
        return this.numLot;
    }

    /**
     * Setter pour numLot.
     * @param numLot Le numLot à écrire.
     */
    public void setNumLot(final String numLot)
    {
        this.numLot = numLot;
    }

    /**
     * Getter pour denominationProduit.
     * @return Le denominationProduit
     */
    public String getDenominationProduit()
    {
        return this.denominationProduit;
    }

    /**
     * Setter pour denominationProduit.
     * @param denominationProduit Le denominationProduit à écrire.
     */
    public void setDenominationProduit(final String denominationProduit)
    {
        this.denominationProduit = denominationProduit;
    }

    /**
     * Getter pour date.
     * @return Le date
     */
    public Calendar getDate()
    {
        return this.date;
    }

    /**
     * Setter pour date.
     * @param date Le date à écrire.
     */
    public void setDate(final Calendar date)
    {
        this.date = date;
    }

    /**
     * Getter pour heuresMinutes.
     * @return Le heuresMinutes
     */
    public String getHeuresMinutes()
    {
        return this.heuresMinutes;
    }

    /**
     * Setter pour heuresMinutes.
     * @param heuresMinutes Le heuresMinutes à écrire.
     */
    public void setHeuresMinutes(final String heuresMinutes)
    {
        this.heuresMinutes = heuresMinutes;
    }

    /**
     * Getter sur conditionnement.
     * @return Retourne le conditionnement.
     */
    public Conditionnement getConditionnement()
    {
        return this.conditionnement;
    }

    /**
     * Setter pour conditionnement.
     * @param conditionnement le conditionnement à écrire.
     */
    public void setConditionnement(final Conditionnement conditionnement)
    {
        this.conditionnement = conditionnement;
    }

    /**
     * Getter sur modePrescription.
     * @return Retourne le modePrescription.
     */
    public ModePrescription getModePrescription()
    {
        return this.modePrescription;
    }

    /**
     * Setter pour modePrescription.
     * @param modePrescription le modePrescription à écrire.
     */
    public void setModePrescription(final ModePrescription modePrescription)
    {
        this.modePrescription = modePrescription;
    }

    /**
     * Getter pour essais.
     * @return Le essais
     */
    public List<Essai> getEssais()
    {
        return this.essais;
    }

    /**
     * Setter pour essais.
     * @param essais Le essais à écrire.
     */
    public void setEssais(final List<Essai> essais)
    {
        this.essais = essais;
    }

    /**
     * Getter pour pharmacies.
     * @return Le pharmacies
     */
    public List<Pharmacie> getPharmacies()
    {
        return this.pharmacies;
    }

    /**
     * Setter pour pharmacies.
     * @param pharmacies Le pharmacies à écrire.
     */
    public void setPharmacies(final List<Pharmacie> pharmacies)
    {
        this.pharmacies = pharmacies;
    }

}
