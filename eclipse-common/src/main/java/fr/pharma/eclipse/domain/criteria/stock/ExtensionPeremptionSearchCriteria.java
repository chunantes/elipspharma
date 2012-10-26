package fr.pharma.eclipse.domain.criteria.stock;

import java.util.Calendar;
import java.util.List;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Critère de recherche utilisé dans le cadre de l'extension de date de péremption.
 
 * @version $Revision$ $Date$
 */
public class ExtensionPeremptionSearchCriteria
    extends AbstractSearchCriteria
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -5819686557860549266L;

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
     * Produit.
     */
    private Produit produit;

    /**
     * Conditionnement.
     */
    private Conditionnement conditionnement;

    /**
     * Numéro de lot.
     */
    private String numLot;

    /**
     * Numéro de traitement.
     */
    private String numTraitement;

    /**
     * Date de début de péremption.
     */
    private Calendar dateDebut;

    /**
     * Date de fin de péremption.
     */
    private Calendar dateFin;

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear()
    {
        this.setEssai(null);
        this.setPharmacie(null);
        this.setProduit(null);
        this.setConditionnement(null);
        this.setNumLot(null);
        this.setNumTraitement(null);
        this.setDateDebut(null);
        this.setDateFin(null);
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
     * Getter pour produit.
     * @return Le produit
     */
    public Produit getProduit()
    {
        return this.produit;
    }

    /**
     * Setter pour produit.
     * @param produit Le produit à écrire.
     */
    public void setProduit(final Produit produit)
    {
        this.produit = produit;
    }

    /**
     * Getter pour conditionnement.
     * @return Le conditionnement
     */
    public Conditionnement getConditionnement()
    {
        return this.conditionnement;
    }

    /**
     * Setter pour conditionnement.
     * @param conditionnement Le conditionnement à écrire.
     */
    public void setConditionnement(final Conditionnement conditionnement)
    {
        this.conditionnement = conditionnement;
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
     * Getter pour numTraitement.
     * @return Le numTraitement
     */
    public String getNumTraitement()
    {
        return this.numTraitement;
    }

    /**
     * Setter pour numTraitement.
     * @param numTraitement Le numTraitement à écrire.
     */
    public void setNumTraitement(final String numTraitement)
    {
        this.numTraitement = numTraitement;
    }

    /**
     * Getter pour dateDebut.
     * @return Le dateDebut
     */
    public Calendar getDateDebut()
    {
        return this.dateDebut;
    }

    /**
     * Setter pour dateDebut.
     * @param dateDebut Le dateDebut à écrire.
     */
    public void setDateDebut(final Calendar dateDebut)
    {
        this.dateDebut = dateDebut;
    }

    /**
     * Getter pour dateFin.
     * @return Le dateFin
     */
    public Calendar getDateFin()
    {
        return this.dateFin;
    }

    /**
     * Setter pour dateFin.
     * @param dateFin Le dateFin à écrire.
     */
    public void setDateFin(final Calendar dateFin)
    {
        this.dateFin = dateFin;
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
