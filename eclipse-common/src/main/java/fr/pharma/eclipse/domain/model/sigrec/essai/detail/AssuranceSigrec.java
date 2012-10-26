package fr.pharma.eclipse.domain.model.sigrec.essai.detail;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.sigrec.acteur.ContactSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;

/**
 * Classe du modèle représentant une assurance importée de SIGREC.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "assurance_sigrec")
public class AssuranceSigrec
    extends BeanObject
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 7861460191608394588L;

    /**
     * Essai auquel est rattaché la prévision.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_essai")
    private EssaiSigrec essai;

    /**
     * Numéro du contrat.
     */
    @Column(name = "numeroContrat")
    private String numeroContrat;

    /**
     * Date de début de validité.
     */
    @Column(name = "dateDebutValidite")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateDebutValidite;

    /**
     * Date de fin de validité.
     */
    @Column(name = "dateFinValidite")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateFinValidite;

    /**
     * Contact. Le nom de l'assureur est stockée dans la propriété raisonSociale.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_contact")
    @Index(name = "idx_contact_assurance_sigrec")
    private ContactSigrec contact;

    /**
     * Getter sur numeroContrat.
     * @return Retourne le numeroContrat.
     */
    public String getNumeroContrat()
    {
        return this.numeroContrat;
    }

    /**
     * Setter pour numeroContrat.
     * @param numeroContrat le numeroContrat à écrire.
     */
    public void setNumeroContrat(final String numeroContrat)
    {
        this.numeroContrat = numeroContrat;
    }

    /**
     * Getter sur dateDebutValidite.
     * @return Retourne le dateDebutValidite.
     */
    public Calendar getDateDebutValidite()
    {
        return this.dateDebutValidite;
    }

    /**
     * Setter pour dateDebutValidite.
     * @param dateDebutValidite le dateDebutValidite à écrire.
     */
    public void setDateDebutValidite(final Calendar dateDebutValidite)
    {
        this.dateDebutValidite = dateDebutValidite;
    }

    /**
     * Getter sur dateFinValidite.
     * @return Retourne le dateFinValidite.
     */
    public Calendar getDateFinValidite()
    {
        return this.dateFinValidite;
    }

    /**
     * Setter pour dateFinValidite.
     * @param dateFinValidite le dateFinValidite à écrire.
     */
    public void setDateFinValidite(final Calendar dateFinValidite)
    {
        this.dateFinValidite = dateFinValidite;
    }

    /**
     * Getter sur contact.
     * @return Retourne le contact.
     */
    public ContactSigrec getContact()
    {
        return this.contact;
    }

    /**
     * Setter pour contact.
     * @param contact le contact à écrire.
     */
    public void setContact(final ContactSigrec contact)
    {
        this.contact = contact;
    }

    /**
     * Getter sur essai.
     * @return Retourne le essai.
     */
    public EssaiSigrec getEssai()
    {
        return this.essai;
    }

    /**
     * Setter pour essai.
     * @param essai le essai à écrire.
     */
    public void setEssai(final EssaiSigrec essai)
    {
        this.essai = essai;
    }

}
