package fr.pharma.eclipse.domain.model.essai.detail.pharma.embedded;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Where;

import fr.pharma.eclipse.domain.enums.Responsabilite;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.document.DocumentModaliteDestruction;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.document.DocumentModaliteReception;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.document.DocumentRespCommande;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.document.DocumentRespInsu;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.document.DocumentRespRandomisation;

/**
 * Informations complementaires relatives aux donnees pharma d'un essai
 * clinique.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class InfosComplementaires implements Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 5649632949929402819L;

    /**
     * Responsabilite de la randomisation.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "responsabiliteRandomisation")
    private Responsabilite responsabiliteRandomisation;

    /**
     * Responsabilite de la levée d'insu.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "responsabiliteInsu")
    private Responsabilite responsabiliteInsu;

    /**
     * Modalité de réception des traitements.
     */
    @Column(name = "modaliteReception", columnDefinition = "TEXT")
    private String modaliteReception = "";

    /**
     * Modalité de destructions des traitements.
     */
    @Column(name = "modaliteDestruction", columnDefinition = "TEXT")
    private String modaliteDestruction = "";

    /**
     * Gestion des retours.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "gestionRetour")
    private Responsabilite gestionRetour;

    /**
     * Type de retour.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "typeRetour")
    private TypeMvtStock typeRetour;

    /**
     * Envois de traitements / distribution à d'autres pui.
     */
    @Column(name = "envoisTraitement")
    private Boolean envoisTraitement;

    /**
     * Responsabilité de la commande de produit.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "responsabiliteCommande")
    private Responsabilite responsabiliteCommande;

    /**
     * Document randomisation.
     */
    @OneToOne(mappedBy = "detailDonneesPharma", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Where(clause = "type='RESPONSABILITE_RANDOMISATION'")
    private DocumentRespRandomisation documentResponsabiliteRandomisation;

    /**
     * Document Responsabilité Insu.
     */
    @OneToOne(mappedBy = "detailDonneesPharma", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Where(clause = "type='RESPONSABILITE_INSU'")
    private DocumentRespInsu documentResponsabiliteInsu;

    /**
     * Document Responsabilité Commande.
     */
    @OneToOne(mappedBy = "detailDonneesPharma", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Where(clause = "type='RESPONSABILITE_COMMANDE'")
    private DocumentRespCommande documentResponsabiliteCommande;

    /**
     * Document Modalité Reception.
     */
    @OneToOne(mappedBy = "detailDonneesPharma", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Where(clause = "type='MODALITE_RECEPTION'")
    private DocumentModaliteReception documentModaliteReception;

    /**
     * Document Modalité Reception.
     */
    @OneToOne(mappedBy = "detailDonneesPharma", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Where(clause = "type='MODALITE_DESTRUCTION'")
    private DocumentModaliteDestruction documentModaliteDestruction;

    /**
     * Getter sur responsabiliteRandomisation.
     * @return Retourne le responsabiliteRandomisation.
     */
    public Responsabilite getResponsabiliteRandomisation() {
        return this.responsabiliteRandomisation;
    }

    /**
     * Setter pour responsabiliteRandomisation.
     * @param responsabiliteRandomisation le responsabiliteRandomisation à
     * écrire.
     */
    public void setResponsabiliteRandomisation(final Responsabilite responsabiliteRandomisation) {
        this.responsabiliteRandomisation = responsabiliteRandomisation;
    }

    /**
     * Getter sur responsabiliteInsu.
     * @return Retourne le responsabiliteInsu.
     */
    public Responsabilite getResponsabiliteInsu() {
        return this.responsabiliteInsu;
    }

    /**
     * Setter pour responsabiliteInsu.
     * @param responsabiliteInsu le responsabiliteInsu à écrire.
     */
    public void setResponsabiliteInsu(final Responsabilite responsabiliteInsu) {
        this.responsabiliteInsu = responsabiliteInsu;
    }

    /**
     * Getter sur modaliteReception.
     * @return Retourne le modaliteReception.
     */
    public String getModaliteReception() {
        return this.modaliteReception;
    }

    /**
     * Setter pour modaliteReception.
     * @param modaliteReception le modaliteReception à écrire.
     */
    public void setModaliteReception(final String modaliteReception) {
        this.modaliteReception = modaliteReception;
    }

    /**
     * Getter sur gestionRetour.
     * @return Retourne le gestionRetour.
     */
    public Responsabilite getGestionRetour() {
        return this.gestionRetour;
    }

    /**
     * Setter pour gestionRetour.
     * @param gestionRetour le gestionRetour à écrire.
     */
    public void setGestionRetour(final Responsabilite gestionRetour) {
        this.gestionRetour = gestionRetour;
    }

    /**
     * Getter sur typeRetour.
     * @return Retourne le typeRetour.
     */
    public TypeMvtStock getTypeRetour() {
        return this.typeRetour;
    }

    /**
     * Setter pour typeRetour.
     * @param typeRetour le typeRetour à écrire.
     */
    public void setTypeRetour(final TypeMvtStock typeRetour) {
        this.typeRetour = typeRetour;
    }

    /**
     * Getter sur envoisTraitement.
     * @return Retourne le envoisTraitement.
     */
    public Boolean getEnvoisTraitement() {
        return this.envoisTraitement;
    }

    /**
     * Setter pour envoisTraitement.
     * @param envoisTraitement le envoisTraitement à écrire.
     */
    public void setEnvoisTraitement(final Boolean envoisTraitement) {
        this.envoisTraitement = envoisTraitement;
    }

    /**
     * Getter sur responsabiliteCommande.
     * @return Retourne le responsabiliteCommande.
     */
    public Responsabilite getResponsabiliteCommande() {
        return this.responsabiliteCommande;
    }

    /**
     * Setter pour responsabiliteCommande.
     * @param responsabiliteCommande le responsabiliteCommande à écrire.
     */
    public void setResponsabiliteCommande(final Responsabilite responsabiliteCommande) {
        this.responsabiliteCommande = responsabiliteCommande;
    }

    /**
     * Getter sur documentResponsabiliteRandomisation.
     * @return Retourne le documentResponsabiliteRandomisation.
     */
    public DocumentRespRandomisation getDocumentResponsabiliteRandomisation() {
        return this.documentResponsabiliteRandomisation;
    }

    /**
     * Setter pour documentResponsabiliteRandomisation.
     * @param documentResponsabiliteRandomisation le
     * documentResponsabiliteRandomisation à écrire.
     */
    public void setDocumentResponsabiliteRandomisation(final DocumentRespRandomisation documentResponsabiliteRandomisation) {
        this.documentResponsabiliteRandomisation = documentResponsabiliteRandomisation;
    }

    /**
     * Getter sur documentResponsabiliteInsu.
     * @return Retourne le documentResponsabiliteInsu.
     */
    public DocumentRespInsu getDocumentResponsabiliteInsu() {
        return this.documentResponsabiliteInsu;
    }

    /**
     * Setter pour documentResponsabiliteInsu.
     * @param documentResponsabiliteInsu le documentResponsabiliteInsu à écrire.
     */
    public void setDocumentResponsabiliteInsu(final DocumentRespInsu documentResponsabiliteInsu) {
        this.documentResponsabiliteInsu = documentResponsabiliteInsu;
    }

    /**
     * Getter sur documentResponsabiliteCommande.
     * @return Retourne le documentResponsabiliteCommande.
     */
    public DocumentRespCommande getDocumentResponsabiliteCommande() {
        return this.documentResponsabiliteCommande;
    }

    /**
     * Setter pour documentResponsabiliteCommande.
     * @param documentResponsabiliteCommande le documentResponsabiliteCommande à
     * écrire.
     */
    public void setDocumentResponsabiliteCommande(final DocumentRespCommande documentResponsabiliteCommande) {
        this.documentResponsabiliteCommande = documentResponsabiliteCommande;
    }

    /**
     * Getter sur documentModaliteReception.
     * @return Retourne le documentModaliteReception.
     */
    public DocumentModaliteReception getDocumentModaliteReception() {
        return this.documentModaliteReception;
    }

    /**
     * Setter pour documentModaliteReception.
     * @param documentModaliteReception le documentModaliteReception à écrire.
     */
    public void setDocumentModaliteReception(final DocumentModaliteReception documentModaliteReception) {
        this.documentModaliteReception = documentModaliteReception;
    }

    /**
     * Getter sur modaliteDestruction.
     * @return Retourne le modaliteDestruction.
     */
    public String getModaliteDestruction() {
        return this.modaliteDestruction;
    }

    /**
     * Setter pour modaliteDestruction.
     * @param modaliteDestruction le modaliteDestruction à écrire.
     */
    public void setModaliteDestruction(final String modaliteDestruction) {
        this.modaliteDestruction = modaliteDestruction;
    }

    /**
     * Getter sur documentModaliteDestruction.
     * @return Retourne le documentModaliteDestruction.
     */
    public DocumentModaliteDestruction getDocumentModaliteDestruction() {
        return this.documentModaliteDestruction;
    }

    /**
     * Setter pour documentModaliteDestruction.
     * @param documentModaliteDestruction le documentModaliteDestruction à
     * écrire.
     */
    public void setDocumentModaliteDestruction(final DocumentModaliteDestruction documentModaliteDestruction) {
        this.documentModaliteDestruction = documentModaliteDestruction;
    }

}
