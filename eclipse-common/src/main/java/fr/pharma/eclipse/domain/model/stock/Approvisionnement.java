package fr.pharma.eclipse.domain.model.stock;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Where;

import fr.pharma.eclipse.domain.enums.stock.MotifRefus;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.stock.document.DocumentAppro;

/**
 * Bean métier représentant un approvisionnement de stock.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "mvt_approvisionnement")
public class Approvisionnement extends MvtStock {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -5654270804575540133L;

    /**
     * Date de péremption.
     */
    @Column(name = "datePeremption")
    @Temporal(TemporalType.DATE)
    private Calendar datePeremption;

    /**
     * Boolean indiquant qu'une extension de péremtion a eu lieu.
     */
    @Column(name = "extensionPeremption")
    private Boolean extensionPeremption;

    /**
     * Commentaire extension date de péremption.
     */
    @Column(name = "commentaireExtensionPeremption", columnDefinition = "TEXT")
    private String commentaireExtensionPeremption;

    /**
     * Historique extension date de péremption.
     */
    @Column(name = "historiqueExtensionPeremption", columnDefinition = "TEXT")
    private String historiqueExtensionPeremption;

    /**
     * Date de réception.
     */
    @Column(name = "dateReception")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateReception;

    /**
     * Date d'arrivée du colis.
     */
    @Column(name = "dateArriveeColis")
    @Temporal(TemporalType.DATE)
    private Calendar dateArriveeColis;

    /**
     * Motif du refus.
     */
    @Column(name = "motifRefus")
    @Enumerated(EnumType.STRING)
    private MotifRefus motifRefus;

    /**
     * Commentaire refus.
     */
    @Column(name = "commentaireRefus", columnDefinition = "TEXT")
    private String commentaireRefus;

    /**
     * Document d'approvisionnement (certificat d'analyse).
     */
    @OneToOne(mappedBy = "mvtStock", cascade = CascadeType.ALL)
    @Where(clause = "type='APPRO'")
    @LazyCollection(LazyCollectionOption.TRUE)
    private DocumentAppro documentAppro;

    /**
     * Constructeur
     */
    public Approvisionnement() {
        this.setType(TypeMvtStock.APPROVISIONNEMENT);
    }

    /**
     * Getter pour datePeremption.
     * @return Le datePeremption
     */
    @Override
    public Calendar getDatePeremption() {
        return this.datePeremption;
    }

    /**
     * Setter pour datePeremption.
     * @param datePeremption Le datePeremption à écrire.
     */
    @Override
    public void setDatePeremption(final Calendar datePeremption) {
        this.datePeremption = datePeremption;
    }

    /**
     * Getter pour motifRefus.
     * @return Le motifRefus
     */
    public MotifRefus getMotifRefus() {
        return this.motifRefus;
    }

    /**
     * Setter pour motifRefus.
     * @param motifRefus Le motifRefus à écrire.
     */
    public void setMotifRefus(final MotifRefus motifRefus) {
        this.motifRefus = motifRefus;
    }

    /**
     * Getter pour commentaireRefus.
     * @return Le commentaireRefus
     */
    public String getCommentaireRefus() {
        return this.commentaireRefus;
    }

    /**
     * Setter pour commentaireRefus.
     * @param commentaireRefus Le commentaireRefus à écrire.
     */
    public void setCommentaireRefus(final String commentaireRefus) {
        this.commentaireRefus = commentaireRefus;
    }

    /**
     * Getter pour dateReception.
     * @return Le dateReception
     */
    public Calendar getDateReception() {
        return this.dateReception;
    }

    /**
     * Setter pour dateReception.
     * @param dateReception Le dateReception à écrire.
     */
    public void setDateReception(final Calendar dateReception) {
        this.dateReception = dateReception;
    }

    /**
     * Getter pour dateArriveeColis.
     * @return Le dateArriveeColis
     */
    public Calendar getDateArriveeColis() {
        return this.dateArriveeColis;
    }

    /**
     * Setter pour dateArriveeColis.
     * @param dateArriveeColis Le dateArriveeColis à écrire.
     */
    public void setDateArriveeColis(final Calendar dateArriveeColis) {
        this.dateArriveeColis = dateArriveeColis;
    }

    /**
     * Getter pour commentaireExtensionPeremption.
     * @return Le commentaireExtensionPeremption
     */
    public String getCommentaireExtensionPeremption() {
        return this.commentaireExtensionPeremption;
    }

    /**
     * Setter pour commentaireExtensionPeremption.
     * @param commentaireExtensionPeremption Le commentaireExtensionPeremption à
     * écrire.
     */
    public void setCommentaireExtensionPeremption(final String commentaireExtensionPeremption) {
        this.commentaireExtensionPeremption = commentaireExtensionPeremption;
    }

    /**
     * Getter pour extensionPeremption.
     * @return Le extensionPeremption
     */
    public Boolean getExtensionPeremption() {
        return this.extensionPeremption;
    }

    /**
     * Setter pour extensionPeremption.
     * @param extensionPeremption Le extensionPeremption à écrire.
     */
    public void setExtensionPeremption(final Boolean extensionPeremption) {
        this.extensionPeremption = extensionPeremption;
    }

    /**
     * Getter pour documentAppro.
     * @return Le documentAppro
     */
    public DocumentAppro getDocumentAppro() {
        return this.documentAppro;
    }

    /**
     * Setter pour documentAppro.
     * @param documentAppro Le documentAppro à écrire.
     */
    public void setDocumentAppro(final DocumentAppro documentAppro) {
        this.documentAppro = documentAppro;
    }

    /**
     * Getter pour historiqueExtensionPeremption.
     * @return Le historiqueExtensionPeremption
     */
    public String getHistoriqueExtensionPeremption() {
        return this.historiqueExtensionPeremption;
    }

    /**
     * Setter pour historiqueExtensionPeremption.
     * @param historiqueExtensionPeremption Le historiqueExtensionPeremption à
     * écrire.
     */
    public void setHistoriqueExtensionPeremption(final String historiqueExtensionPeremption) {
        this.historiqueExtensionPeremption = historiqueExtensionPeremption;
    }

}
