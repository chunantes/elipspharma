package fr.pharma.eclipse.domain.model.stock;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.stockage.Stockage;

/**
 * Bean représentant une réception de stock concernant un numéro de lot.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ReceptionLot implements Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3809107423683121854L;

    /**
     * Approvisionnement.
     */
    private Approvisionnement appro;

    /**
     * Nombre de numéros de traitement à saisir.
     */
    private Integer nbNumerosTraitement;

    /**
     * Liste de numéros de traitements de saisie.
     */
    private List<NumTraitement> numsTraitements;

    /**
     * Quantité cumulée des numéros de traitements.
     */
    private Integer qteCumulNumsTraitements;

    /**
     * Liste des conditionnements.
     */
    private List<Conditionnement> conditionnements;

    /**
     * Lieu de stockage.
     */
    private Stockage stockage;

    /**
     * Méthode appelée via la couche IHM lorsqu'un nombre de numéros de
     * traitements est saisis.
     */
    public void handleSaisieNbNumsTraitements() {
        final List<NumTraitement> list = new ArrayList<NumTraitement>();
        if (this.getNbNumerosTraitement() != null) {
            for (int i = 0; i < this.getNbNumerosTraitement(); i++) {
                list.add(this.buildNumTraitement());
            }
        }
        this.setNumsTraitements(list);
    }

    /**
     * Méthode appelée via la couche IHM pour ajouter un numéro de traitement
     * dans la liste.
     */
    public void addNumTraitement() {
        this.getNumsTraitements().add(this.buildNumTraitement());
        this.setNbNumerosTraitement(this.getNbNumerosTraitement() + 1);
    }

    /**
     * Méthode appelée via la couche IHM pour supprimer un numéro de traitement
     * dans la liste.
     * @param event Evénement.
     */
    public void delNumTraitement(final ActionEvent event) {
        final NumTraitement numTraitement = (NumTraitement) event.getComponent().getAttributes().get("numTraitementToDelete");
        this.getNumsTraitements().remove(numTraitement);
        this.setNbNumerosTraitement(this.getNbNumerosTraitement() - 1);
    }

    /**
     * Méthode appelée via la couche IHM lorsqu'un conditionnement est
     * sélectionné.
     * @param event Event.
     */
    public void handleSelectConditionnement(final AjaxBehaviorEvent event) {
        final HtmlSelectOneMenu select = (HtmlSelectOneMenu) event.getSource();
        final Conditionnement conditionnement = (Conditionnement) select.getLocalValue();
        if (conditionnement == null) {
            this.appro.setConditionnement(null);
        }
        this.setNbNumerosTraitement(null);
        this.handleSaisieNbNumsTraitements();
    }

    /**
     * Méthode appelée via la couche IHM lorsque l'état de la réception est
     * coché.
     */
    public void handleSelectEtatReception() {
        // Si l'approvisionnement est approuvé
        if (this.getAppro().getApproApprouve()) {
            this.getAppro().setCommentaireRefus(null);
            this.getAppro().setMotifRefus(null);
        }
    }

    /**
     * Méthode en charge de construire un numéro de traitement.
     * @return Numéro de traitement.
     */
    private NumTraitement buildNumTraitement() {
        // Par défaut : positionnement de la quantité à 1
        final NumTraitement numTraitement = new NumTraitement();
        numTraitement.setQuantite(1);
        return numTraitement;
    }

    /**
     * Getter pour nbNumerosTraitement.
     * @return Le nbNumerosTraitement
     */
    public Integer getNbNumerosTraitement() {
        return this.nbNumerosTraitement;
    }

    /**
     * Setter pour nbNumerosTraitement.
     * @param nbNumerosTraitement Le nbNumerosTraitement à écrire.
     */
    public void setNbNumerosTraitement(final Integer nbNumerosTraitement) {
        this.nbNumerosTraitement = nbNumerosTraitement;
    }

    /**
     * Getter pour numsTraitements.
     * @return Le numsTraitements
     */
    public List<NumTraitement> getNumsTraitements() {
        return this.numsTraitements;
    }

    /**
     * Setter pour numsTraitements.
     * @param numsTraitements Le numsTraitements à écrire.
     */
    public void setNumsTraitements(final List<NumTraitement> numsTraitements) {
        this.numsTraitements = numsTraitements;
    }

    /**
     * Getter pour conditionnements.
     * @return Le conditionnements
     */
    public List<Conditionnement> getConditionnements() {
        return this.conditionnements;
    }

    /**
     * Setter pour conditionnements.
     * @param conditionnements Le conditionnements à écrire.
     */
    public void setConditionnements(final List<Conditionnement> conditionnements) {
        this.conditionnements = conditionnements;
    }

    /**
     * Getter pour appro.
     * @return Le appro
     */
    public Approvisionnement getAppro() {
        return this.appro;
    }

    /**
     * Setter pour appro.
     * @param appro Le appro à écrire.
     */
    public void setAppro(final Approvisionnement appro) {
        this.appro = appro;
    }

    /**
     * Getter pour qteCumulNumsTraitements.
     * @return Le qteCumulNumsTraitements
     */
    public Integer getQteCumulNumsTraitements() {
        this.qteCumulNumsTraitements = Integer.valueOf(0);

        for (final NumTraitement numTraitement : this.getNumsTraitements()) {
            if ((numTraitement.getQuantite() != null) && (StringUtils.isNotEmpty(numTraitement.getNumTraitement()))) {
                this.qteCumulNumsTraitements += numTraitement.getQuantite();
            }
        }

        return this.qteCumulNumsTraitements;
    }

    /**
     * Getter pour stockage.
     * @return Le stockage
     */
    public Stockage getStockage() {
        return this.stockage;
    }

    /**
     * Setter pour stockage.
     * @param stockage Le stockage à écrire.
     */
    public void setStockage(final Stockage stockage) {
        this.stockage = stockage;
    }

}
