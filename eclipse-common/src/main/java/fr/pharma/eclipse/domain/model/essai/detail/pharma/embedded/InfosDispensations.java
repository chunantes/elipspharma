package fr.pharma.eclipse.domain.model.essai.detail.pharma.embedded;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Where;

import fr.pharma.eclipse.domain.enums.TypeDispensation;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.document.DocumentAideDispensation;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.document.DocumentConseilPatient;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.document.DocumentContreEtiquette;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.document.DocumentInformationConditionnement;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.document.DocumentNumerotationConditionnement;

/**
 * Informations dispensations relatives aux donnees pharma d'un essai clinique.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class InfosDispensations implements Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -8465917535458953768L;

    /**
     * Type de dispensation.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "typeDispensation")
    private TypeDispensation typeDispensation;

    /**
     * Tracabilité patient obligatoire avant réassort.
     */
    @Column(name = "tracabilitePatient")
    private Boolean tracabilitePatient = false;

    /**
     * Patient destinataires de la dispensation.
     */
    @Column(name = "destinatairePatient")
    private Boolean destinatairePatient = false;

    /**
     * Service destinataires de la dispensation.
     */
    @Column(name = "destinataireService")
    private Boolean destinataireService = false;

    /**
     * Investigateur destinataires de la dispensation.
     */
    @Column(name = "destinataireInvestigateur")
    private Boolean destinataireInvestigateur = false;

    /**
     * Numérotation des conditionnements.
     */
    @Column(name = "numerotationConditionnement", columnDefinition = "TEXT")
    private String numerotationConditionnement = "";

    /**
     * Informations sur les conditionnements.
     */
    @Column(name = "informationConditionnement", columnDefinition = "TEXT")
    private String informationConditionnement = "";

    /**
     * Contre-etiquette a détacher et destinations de cette etiquette.
     */
    @Column(name = "contreEtiquette", columnDefinition = "TEXT")
    private String contreEtiquette = "";

    /**
     * Conseil patient.
     */
    @Column(name = "conseilPatient", columnDefinition = "TEXT")
    private String conseilPatient = "";

    /**
     * Aide à la dispensation.
     */
    @Column(name = "aideDispensation", columnDefinition = "TEXT")
    private String aideDispensation = "";

    /**
     * Document Numérotation des conditionnements.
     */
    @OneToOne(mappedBy = "detailDonneesPharma", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Where(clause = "type='NUMEROTATION_CONDITIONNEMENT'")
    private DocumentNumerotationConditionnement documentNumerotationConditionnement;

    /**
     * Document Information sur les conditionnements.
     */
    @OneToOne(mappedBy = "detailDonneesPharma", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Where(clause = "type='INFORMATION_CONDITIONNEMENT'")
    private DocumentInformationConditionnement documentInformationConditionnement;

    /**
     * Document Contre etiquette.
     */
    @OneToOne(mappedBy = "detailDonneesPharma", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Where(clause = "type='CONTRE_ETIQUETTE'")
    private DocumentContreEtiquette documentContreEtiquette;

    /**
     * Document Conseil patient.
     */
    @OneToOne(mappedBy = "detailDonneesPharma", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Where(clause = "type='CONSEIL_PATIENT'")
    private DocumentConseilPatient documentConseilPatient;

    /**
     * Document Aide à la dispensation.
     */
    @OneToOne(mappedBy = "detailDonneesPharma", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Where(clause = "type='AIDE_DISPENSATION'")
    private DocumentAideDispensation documentAideDispensation;

    /**
     * Getter sur typeDispensation.
     * @return Retourne le typeDispensation.
     */
    public TypeDispensation getTypeDispensation() {
        return this.typeDispensation;
    }

    /**
     * Setter pour typeDispensation.
     * @param typeDispensation le typeDispensation à écrire.
     */
    public void setTypeDispensation(final TypeDispensation typeDispensation) {
        this.typeDispensation = typeDispensation;
    }

    /**
     * Getter sur documentNumerotationConditionnement.
     * @return Retourne le documentNumerotationConditionnement.
     */
    public DocumentNumerotationConditionnement getDocumentNumerotationConditionnement() {
        return this.documentNumerotationConditionnement;
    }

    /**
     * Setter pour documentNumerotationConditionnement.
     * @param documentNumerotationConditionnement le
     * documentNumerotationConditionnement à écrire.
     */
    public void setDocumentNumerotationConditionnement(final DocumentNumerotationConditionnement documentNumerotationConditionnement) {
        this.documentNumerotationConditionnement = documentNumerotationConditionnement;
    }

    /**
     * Getter sur documentInformationConditionnement.
     * @return Retourne le documentInformationConditionnement.
     */
    public DocumentInformationConditionnement getDocumentInformationConditionnement() {
        return this.documentInformationConditionnement;
    }

    /**
     * Setter pour documentInformationConditionnement.
     * @param documentInformationConditionnement le
     * documentInformationConditionnement à écrire.
     */
    public void setDocumentInformationConditionnement(final DocumentInformationConditionnement documentInformationConditionnement) {
        this.documentInformationConditionnement = documentInformationConditionnement;
    }

    /**
     * Getter sur documentContreEtiquette.
     * @return Retourne le documentContreEtiquette.
     */
    public DocumentContreEtiquette getDocumentContreEtiquette() {
        return this.documentContreEtiquette;
    }

    /**
     * Setter pour documentContreEtiquette.
     * @param documentContreEtiquette le documentContreEtiquette à écrire.
     */
    public void setDocumentContreEtiquette(final DocumentContreEtiquette documentContreEtiquette) {
        this.documentContreEtiquette = documentContreEtiquette;
    }

    /**
     * Getter sur documentConseilPatient.
     * @return Retourne le documentConseilPatient.
     */
    public DocumentConseilPatient getDocumentConseilPatient() {
        return this.documentConseilPatient;
    }

    /**
     * Setter pour documentConseilPatient.
     * @param documentConseilPatient le documentConseilPatient à écrire.
     */
    public void setDocumentConseilPatient(final DocumentConseilPatient documentConseilPatient) {
        this.documentConseilPatient = documentConseilPatient;
    }

    /**
     * Getter sur documentAideDispensation.
     * @return Retourne le documentAideDispensation.
     */
    public DocumentAideDispensation getDocumentAideDispensation() {
        return this.documentAideDispensation;
    }

    /**
     * Setter pour documentAideDispensation.
     * @param documentAideDispensation le documentAideDispensation à écrire.
     */
    public void setDocumentAideDispensation(final DocumentAideDispensation documentAideDispensation) {
        this.documentAideDispensation = documentAideDispensation;
    }

    /**
     * Getter sur tracabilitePatient.
     * @return Retourne le tracabilitePatient.
     */
    public Boolean getTracabilitePatient() {
        return this.tracabilitePatient;
    }

    /**
     * Getter sur destinatairePatient.
     * @return Retourne le destinatairePatient.
     */
    public Boolean getDestinatairePatient() {
        return this.destinatairePatient;
    }

    /**
     * Getter sur destinataireService.
     * @return Retourne le destinataireService.
     */
    public Boolean getDestinataireService() {
        return this.destinataireService;
    }

    /**
     * Getter sur destinataireInvestigateur.
     * @return Retourne le destinataireInvestigateur.
     */
    public Boolean getDestinataireInvestigateur() {
        return this.destinataireInvestigateur;
    }

    /**
     * Getter sur numerotationConditionnement.
     * @return Retourne le numerotationConditionnement.
     */
    public String getNumerotationConditionnement() {
        return this.numerotationConditionnement;
    }

    /**
     * Getter sur informationConditionnement.
     * @return Retourne le informationConditionnement.
     */
    public String getInformationConditionnement() {
        return this.informationConditionnement;
    }

    /**
     * Getter sur contreEtiquette.
     * @return Retourne le contreEtiquette.
     */
    public String getContreEtiquette() {
        return this.contreEtiquette;
    }

    /**
     * Getter sur conseilPatient.
     * @return Retourne le conseilPatient.
     */
    public String getConseilPatient() {
        return this.conseilPatient;
    }

    /**
     * Getter sur aideDispensation.
     * @return Retourne le aideDispensation.
     */
    public String getAideDispensation() {
        return this.aideDispensation;
    }

    /**
     * Setter pour tracabilitePatient.
     * @param tracabilitePatient le tracabilitePatient à écrire.
     */
    public void setTracabilitePatient(final Boolean tracabilitePatient) {
        this.tracabilitePatient = tracabilitePatient;
    }

    /**
     * Setter pour destinatairePatient.
     * @param destinatairePatient le destinatairePatient à écrire.
     */
    public void setDestinatairePatient(final Boolean destinatairePatient) {
        this.destinatairePatient = destinatairePatient;
    }

    /**
     * Setter pour destinataireService.
     * @param destinataireService le destinataireService à écrire.
     */
    public void setDestinataireService(final Boolean destinataireService) {
        this.destinataireService = destinataireService;
    }

    /**
     * Setter pour destinataireInvestigateur.
     * @param destinataireInvestigateur le destinataireInvestigateur à écrire.
     */
    public void setDestinataireInvestigateur(final Boolean destinataireInvestigateur) {
        this.destinataireInvestigateur = destinataireInvestigateur;
    }

    /**
     * Setter pour numerotationConditionnement.
     * @param numerotationConditionnement le numerotationConditionnement à
     * écrire.
     */
    public void setNumerotationConditionnement(final String numerotationConditionnement) {
        this.numerotationConditionnement = numerotationConditionnement;
    }

    /**
     * Setter pour informationConditionnement.
     * @param informationConditionnement le informationConditionnement à écrire.
     */
    public void setInformationConditionnement(final String informationConditionnement) {
        this.informationConditionnement = informationConditionnement;
    }

    /**
     * Setter pour contreEtiquette.
     * @param contreEtiquette le contreEtiquette à écrire.
     */
    public void setContreEtiquette(final String contreEtiquette) {
        this.contreEtiquette = contreEtiquette;
    }

    /**
     * Setter pour conseilPatient.
     * @param conseilPatient le conseilPatient à écrire.
     */
    public void setConseilPatient(final String conseilPatient) {
        this.conseilPatient = conseilPatient;
    }

    /**
     * Setter pour aideDispensation.
     * @param aideDispensation le aideDispensation à écrire.
     */
    public void setAideDispensation(final String aideDispensation) {
        this.aideDispensation = aideDispensation;
    }

}
