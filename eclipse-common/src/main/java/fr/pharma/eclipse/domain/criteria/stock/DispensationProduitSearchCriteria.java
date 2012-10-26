package fr.pharma.eclipse.domain.criteria.stock;

import java.util.Calendar;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;
import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.produit.detail.DetailStockage;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Critère de recherche sur Mouvement Stock de type "dispensation".
 
 * @version $Revision$ $Date$
 */
public class DispensationProduitSearchCriteria
    extends AbstractSearchCriteria
{
    /**
     * Serial ID.
     */

    private static final long serialVersionUID = -8021360472011183298L;

    /**
     * Type de mouvement.
     */
    private TypeMvtStock typeMouvement;

    /**
     * Types de mouvements.
     */
    private TypeMvtStock[] typesMouvement;

    /**
     * Essai.
     */
    private Essai essai;

    /**
     * Stérile.
     */
    private Boolean sterile;

    /**
     * Pharmacie.
     */
    private Pharmacie pharmacie;

    /**
     * Produit.
     */
    private Produit produit;

    /**
     * Conditionnement.
     */
    private Conditionnement conditionnement;

    /**
     * Date de début.
     */
    private Calendar dateDebut;

    /**
     * Date de fin.
     */
    private Calendar dateFin;

    /**
     * Numéro de lot.
     */
    private String numLot;

    /**
     * Dénomination du produit.
     */
    private String denominationProduit;

    /**
     * Mode de prescription.
     */
    private ModePrescription modePrescription;

    /**
     * Service.
     */
    private Service service;

    /**
     * Utilisé pour les mouvements de type DispensationGlobale pour savoir si la quantité totale a
     * été dispensée nominativement.
     */
    private Boolean dispenseNominativement;

    /**
     * Numéro de traitement.
     */
    private String numTraitement;

    /**
     * Stockage.
     */
    private DetailStockage stockage;

    /**
     * Le numéro d'ordonnancier doit être présent.
     */
    private Boolean notNullNumOrdonnancier;

    /**
     * Indique si on fait une jointure avec les mouvements de type Preparation entree
     */
    private Boolean jointureAvecMvtPreparationEntree = false;

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear()
    {
        this.stockage = null;
        this.setEssai(null);
        this.setTypeMouvement(null);
        this.setPharmacie(null);
        this.setProduit(null);
        this.setConditionnement(null);
        this.setTypesMouvement(null);
        this.setDateDebut(null);
        this.setNotNullNumOrdonnancier(null);
        this.setDateFin(null);
        this.setNumLot(null);
        this.setDenominationProduit(null);
        this.setModePrescription(null);
        this.setService(null);
        this.setDispenseNominativement(null);
    }

    /**
     * Getter pour typeMouvement.
     * @return Le typeMouvement
     */
    public TypeMvtStock getTypeMouvement()
    {
        return this.typeMouvement;
    }

    /**
     * Setter pour typeMouvement.
     * @param typeMouvement Le typeMouvement à écrire.
     */
    public void setTypeMouvement(final TypeMvtStock typeMouvement)
    {
        this.typeMouvement = typeMouvement;
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
     * Getter pour typesMouvement.
     * @return Le typesMouvement
     */
    public TypeMvtStock[] getTypesMouvement()
    {
        return this.typesMouvement;
    }

    /**
     * Setter pour typesMouvement.
     * @param typesMouvement Le typesMouvement à écrire.
     */
    public void setTypesMouvement(final TypeMvtStock[] typesMouvement)
    {
        this.typesMouvement = typesMouvement;
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
     * Getter pour service.
     * @return Le service
     */
    public Service getService()
    {
        return this.service;
    }

    /**
     * Setter pour service.
     * @param service Le service à écrire.
     */
    public void setService(final Service service)
    {
        this.service = service;
    }

    /**
     * Getter pour dispenseNominativement.
     * @return Le dispenseNominativement
     */
    public Boolean getDispenseNominativement()
    {
        return this.dispenseNominativement;
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

    /**
     * Setter pour dispenseNominativement.
     * @param dispenseNominativement Le dispenseNominativement à écrire.
     */
    public void setDispenseNominativement(final Boolean dispenseNominativement)
    {
        this.dispenseNominativement = dispenseNominativement;
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
     * Getter pour notNullNumOrdonnancier.
     * @return Le notNullNumOrdonnancier
     */
    public Boolean getNotNullNumOrdonnancier()
    {
        return this.notNullNumOrdonnancier;
    }

    /**
     * Setter pour notNullNumOrdonnancier.
     * @param notNullNumOrdonnancier Le notNullNumOrdonnancier à écrire.
     */
    public void setNotNullNumOrdonnancier(final Boolean notNullNumOrdonnancier)
    {
        this.notNullNumOrdonnancier = notNullNumOrdonnancier;
    }

    /**
     * Getter pour sterile.
     * @return Le sterile
     */
    public Boolean getSterile()
    {
        return this.sterile;
    }

    /**
     * Setter pour sterile.
     * @param sterile Le sterile à écrire.
     */
    public void setSterile(final Boolean sterile)
    {
        this.sterile = sterile;
    }

    /**
     * Getter pour jointureEntreMvtDispensationEtPreparationEntree.
     * @return Le jointureEntreMvtDispensationEtPreparationEntree
     */
    public Boolean getJointureEntreMvtDispensationEtPreparationEntree()
    {
        return this.jointureAvecMvtPreparationEntree;
    }

    /**
     * Setter pour jointureEntreMvtDispensationEtPreparationEntree.
     * @param jointureEntreMvtDispensationEtPreparationEntree Le
     * jointureEntreMvtDispensationEtPreparationEntree à écrire.
     */
    public void setJointureEntreMvtDispensationEtPreparationEntree(final Boolean jointureEntreMvtDispensationEtPreparationEntree)
    {
        this.jointureAvecMvtPreparationEntree = jointureEntreMvtDispensationEtPreparationEntree;
    }

}
