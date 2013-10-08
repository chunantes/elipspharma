package fr.pharma.eclipse.domain.model.stock;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.acteur.Promoteur;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Bean métier représentant le résultat d'une réception de stock
 * (approvisionnement).
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ResultApprovisionnement implements Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2120231414185654264L;

    /**
     * Essai.
     */
    private Essai essai;

    /**
     * Promoteur.
     */
    private Promoteur promoteur;

    /**
     * Pharmacie.
     */
    private Pharmacie pharmacie;

    /**
     * Date de l'approvisionnement.
     */
    private Calendar dateAppro;

    /**
     * Utilisateur à l'origine de la réception.
     */
    private Personne personne;

    /**
     * Liste des réceptions de lot pris en compte pour créer les mouvements.
     */
    private final List<ReceptionLot> receptionLots = new ArrayList<ReceptionLot>();

    /**
     * Getter pour essai.
     * @return Le essai
     */
    public Essai getEssai() {
        return this.essai;
    }

    /**
     * Setter pour essai.
     * @param essai Le essai à écrire.
     */
    public void setEssai(final Essai essai) {
        this.essai = essai;
    }

    /**
     * Getter pour pharmacie.
     * @return Le pharmacie
     */
    public Pharmacie getPharmacie() {
        return this.pharmacie;
    }

    /**
     * Setter pour pharmacie.
     * @param pharmacie Le pharmacie à écrire.
     */
    public void setPharmacie(final Pharmacie pharmacie) {
        this.pharmacie = pharmacie;
    }

    /**
     * Getter pour dateAppro.
     * @return Le dateAppro
     */
    public Calendar getDateAppro() {
        return this.dateAppro;
    }

    /**
     * Setter pour dateAppro.
     * @param dateAppro Le dateAppro à écrire.
     */
    public void setDateAppro(final Calendar dateAppro) {
        this.dateAppro = dateAppro;
    }

    /**
     * Getter pour personne.
     * @return Le personne
     */
    public Personne getPersonne() {
        return this.personne;
    }

    /**
     * Setter pour personne.
     * @param personne Le personne à écrire.
     */
    public void setPersonne(final Personne personne) {
        this.personne = personne;
    }

    /**
     * Getter pour promoteur.
     * @return Le promoteur
     */
    public Promoteur getPromoteur() {
        return this.promoteur;
    }

    /**
     * Setter pour promoteur.
     * @param promoteur Le promoteur à écrire.
     */
    public void setPromoteur(final Promoteur promoteur) {
        this.promoteur = promoteur;
    }

    /**
     * Getter pour receptionLots.
     * @return Le receptionLots
     */
    public List<ReceptionLot> getReceptionLots() {
        return this.receptionLots;
    }

}
