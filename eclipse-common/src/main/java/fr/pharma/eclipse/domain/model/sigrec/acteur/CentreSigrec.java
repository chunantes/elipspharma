package fr.pharma.eclipse.domain.model.sigrec.acteur;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import fr.pharma.eclipse.domain.model.common.BeanObject;

/**
 * Classe du modèle d'import SIGREC représentant un centre investigateur.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "centre_sigrec")
public class CentreSigrec extends BeanObject {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -8346341002237934459L;

    /**
     * Numéro attribué par le promoteur.
     */
    @Column(name = "numero")
    private String numero;

    /**
     * Numéro Finess.
     */
    @Column(name = "numeroFiness")
    private String numeroFiness;

    /**
     * Nom.
     */
    @Column(name = "nom")
    private String nom;

    /**
     * Identifiant du centre dans la base SIGREC.
     */
    @Column(name = "idCentre")
    private String idCentre;

    /**
     * Investigateurs.
     */
    @OneToMany(mappedBy = "centre", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    private final List<InvestigateurSigrec> investigateurs = new ArrayList<InvestigateurSigrec>();

    /**
     * ARCInvestigateurs.
     */
    @OneToMany(mappedBy = "centre", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    private final List<ARCInvestigateurSigrec> arcInvestigateurs = new ArrayList<ARCInvestigateurSigrec>();

    /**
     * CoInvestigateurs.
     */
    @OneToMany(mappedBy = "centre", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    private final List<CoInvestigateurSigrec> coInvestigateurs = new ArrayList<CoInvestigateurSigrec>();

    /**
     * Contact.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_contact")
    @Index(name = "idx_contact_centre_sigrec")
    private ContactSigrec contact;

    /**
     * Getter sur numero.
     * @return Retourne le numero.
     */
    public String getNumero() {
        return this.numero;
    }

    /**
     * Setter pour numero.
     * @param numero le numero à écrire.
     */
    public void setNumero(final String numero) {
        this.numero = numero;
    }

    /**
     * Getter sur numeroFiness.
     * @return Retourne le numeroFiness.
     */
    public String getNumeroFiness() {
        return this.numeroFiness;
    }

    /**
     * Setter pour numeroFiness.
     * @param numeroFiness le numeroFiness à écrire.
     */
    public void setNumeroFiness(final String numeroFiness) {
        this.numeroFiness = numeroFiness;
    }

    /**
     * Getter sur nom.
     * @return Retourne le nom.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Setter pour nom.
     * @param nom le nom à écrire.
     */
    public void setNom(final String nom) {
        this.nom = nom;
    }

    /**
     * Getter sur contact.
     * @return Retourne le contact.
     */
    public ContactSigrec getContact() {
        return this.contact;
    }

    /**
     * Setter pour contact.
     * @param contact le contact à écrire.
     */
    public void setContact(final ContactSigrec contact) {
        this.contact = contact;
    }

    /**
     * Getter sur idCentre.
     * @return Retourne le idCentre.
     */
    public String getIdCentre() {
        return this.idCentre;
    }

    /**
     * Setter pour idCentre.
     * @param idCentre le idCentre à écrire.
     */
    public void setIdCentre(final String idCentre) {
        this.idCentre = idCentre;
    }

    /**
     * Getter sur investigateurs.
     * @return Retourne le investigateurs.
     */
    public List<InvestigateurSigrec> getInvestigateurs() {
        return this.investigateurs;
    }

    /**
     * Getter sur arcInvestigateurs.
     * @return Retourne le arcInvestigateurs.
     */
    public List<ARCInvestigateurSigrec> getArcInvestigateurs() {
        return this.arcInvestigateurs;
    }

    /**
     * Getter sur coInvestigateurs.
     * @return Retourne le coInvestigateurs.
     */
    public List<CoInvestigateurSigrec> getCoInvestigateurs() {
        return this.coInvestigateurs;
    }

}
