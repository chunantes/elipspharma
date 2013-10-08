package fr.pharma.eclipse.domain.model.stock;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Where;

import fr.pharma.eclipse.domain.enums.stock.RaisonSortie;
import fr.pharma.eclipse.domain.model.stock.document.DocumentRetourPromoteur;

/**
 * Bean métier représentant un mouvement de stock de retour promoteur.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "mvt_retour_promoteur")
public class RetourPromoteur extends MvtStock {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 7709103954040090185L;

    /**
     * Nom de la société de transport.
     */
    @Column(name = "nomSocieteTransport")
    private String nomSocieteTransport;

    /**
     * Référence de l'envoi.
     */
    @Column(name = "referenceEnvoi")
    private String referenceEnvoi;

    /**
     * Commentaire.
     */
    @Column(name = "commentaire", columnDefinition = "TEXT")
    private String commentaire;

    /**
     * Document de certificat de retour promoteur.
     */
    @OneToOne(mappedBy = "mvtStock", cascade = CascadeType.ALL)
    @Where(clause = "type='RETOUR_PROMOTEUR'")
    @LazyCollection(LazyCollectionOption.TRUE)
    private DocumentRetourPromoteur documentRetourPromoteur;

    /**
     * Raison sortie. Setté si le type demouvement est une sortie.
     */
    @Column(name = "raisonSortie")
    @Enumerated(EnumType.STRING)
    private RaisonSortie raisonSortie;

    /**
     * Commentaire sur la raison de la sortie.
     */
    @Column(name = "commentaireRaison", columnDefinition = "TEXT")
    private String commentaireRaison;

    /**
     * Getter pour commentaire.
     * @return Le commentaire
     */
    public String getCommentaire() {
        return this.commentaire;
    }

    /**
     * Setter pour commentaire.
     * @param commentaire Le commentaire à écrire.
     */
    public void setCommentaire(final String commentaire) {
        this.commentaire = commentaire;
    }

    /**
     * Getter pour nomSocieteTransport.
     * @return Le nomSocieteTransport
     */
    public String getNomSocieteTransport() {
        return this.nomSocieteTransport;
    }

    /**
     * Setter pour nomSocieteTransport.
     * @param nomSocieteTransport Le nomSocieteTransport à écrire.
     */
    public void setNomSocieteTransport(final String nomSocieteTransport) {
        this.nomSocieteTransport = nomSocieteTransport;
    }

    /**
     * Getter pour referenceEnvoi.
     * @return Le referenceEnvoi
     */
    public String getReferenceEnvoi() {
        return this.referenceEnvoi;
    }

    /**
     * Setter pour referenceEnvoi.
     * @param referenceEnvoi Le referenceEnvoi à écrire.
     */
    public void setReferenceEnvoi(final String referenceEnvoi) {
        this.referenceEnvoi = referenceEnvoi;
    }

    /**
     * Getter pour documentRetourPromoteur.
     * @return Le documentRetourPromoteur
     */
    public DocumentRetourPromoteur getDocumentRetourPromoteur() {
        return this.documentRetourPromoteur;
    }

    /**
     * Setter pour documentRetourPromoteur.
     * @param documentRetourPromoteur Le documentRetourPromoteur à écrire.
     */
    public void setDocumentRetourPromoteur(final DocumentRetourPromoteur documentRetourPromoteur) {
        this.documentRetourPromoteur = documentRetourPromoteur;
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

    /**
     * Getter pour commentaireRaison.
     * @return Le commentaireRaison
     */
    public String getCommentaireRaison() {
        return this.commentaireRaison;
    }

    /**
     * Setter pour commentaireRaison.
     * @param commentaireRaison Le commentaireRaison à écrire.
     */
    public void setCommentaireRaison(final String commentaireRaison) {
        this.commentaireRaison = commentaireRaison;
    }

}
