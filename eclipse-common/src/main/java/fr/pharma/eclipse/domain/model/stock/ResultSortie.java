package fr.pharma.eclipse.domain.model.stock;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fr.pharma.eclipse.domain.enums.stock.RaisonSortie;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.acteur.Promoteur;
import fr.pharma.eclipse.domain.model.common.DocumentEclipse;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Bean métier représentant le résultat d'une sortie de stock.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ResultSortie implements Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 212972940873555224L;

    /**
     * Essai.
     */
    private Essai essai;

    /**
     * Promoteur.
     */
    private Promoteur promoteur;

    /**
     * Document.
     */
    private DocumentEclipse document;

    /**
     * Pharmacie.
     */
    private Pharmacie pharmacie;

    /**
     * Date de la sortie.
     */
    private Calendar dateSortie;

    /**
     * Utilisateur à l'origine de la sortie.
     */
    private Personne personne;

    /**
     * Raison de la sortie.
     */
    private RaisonSortie raisonSortie;

    /**
     * Liste des sorties.
     */
    private List<Sortie> sorties = new ArrayList<Sortie>();

    /**
     * Liste des mouvements créés en BD suite à la sortie.
     */
    private List<? extends MvtStock> mvts;

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
     * Getter pour dateSortie.
     * @return Le dateSortie
     */
    public Calendar getDateSortie() {
        return this.dateSortie;
    }

    /**
     * Setter pour dateSortie.
     * @param dateSortie Le dateSortie à écrire.
     */
    public void setDateSortie(final Calendar dateSortie) {
        this.dateSortie = dateSortie;
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
     * Getter pour sorties.
     * @return Le sorties
     */
    public List<Sortie> getSorties() {
        return this.sorties;
    }

    /**
     * Setter pour sorties.
     * @param sorties Le sorties à écrire.
     */
    public void setSorties(final List<Sortie> sorties) {
        this.sorties = sorties;
    }

    /**
     * Getter pour mvts.
     * @return Le mvts
     */
    public List<? extends MvtStock> getMvts() {
        return this.mvts;
    }

    /**
     * Setter pour mvts.
     * @param mvts Le mvts à écrire.
     */
    public void setMvts(final List<? extends MvtStock> mvts) {
        this.mvts = mvts;
    }

    /**
     * Getter pour document.
     * @return Le document
     */
    public DocumentEclipse getDocument() {
        return this.document;
    }

    /**
     * Setter pour document.
     * @param document Le document à écrire.
     */
    public void setDocument(final DocumentEclipse document) {
        this.document = document;
    }

    /**
     * Getter pour raisonSortie.
     * @return Le raisonSortie
     */
    public RaisonSortie getRaisonSortie() {
        return this.raisonSortie;
    }

    /**
     * Setter pour raisonSortie.
     * @param raisonSortie Le raisonSortie à écrire.
     */
    public void setRaisonSortie(final RaisonSortie raisonSortie) {
        this.raisonSortie = raisonSortie;
    }

}
