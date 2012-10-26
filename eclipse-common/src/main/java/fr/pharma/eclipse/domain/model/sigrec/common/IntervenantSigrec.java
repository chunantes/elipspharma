package fr.pharma.eclipse.domain.model.sigrec.common;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.sigrec.acteur.ContactSigrec;

/**
 * Classe commune des intervenants (ARC promoteurs, ARC Investigateur, investigateurprincipal,
 * Investigateur associé.
 
 * @version $Revision$ $Date$
 */
@MappedSuperclass
public abstract class IntervenantSigrec
    extends BeanObject
    implements Serializable
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 2529987723828008653L;

    /**
     * Identifiant SIGREC.
     */
    @Column(name = "identifiant")
    private String identifiant;

    /**
     * Id intervenant de SIGREC.
     */
    @Column(name = "intervenantId")
    private Integer intervenantId;

    /**
     * Titre.
     */
    @Column(name = "titre")
    private String titre;

    /**
     * Numéro dans le centre.
     */
    @Column(name = "numAdeli")
    private String numAdeli;

    /**
     * Contact.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_contact")
    private ContactSigrec contact;

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
     * Getter sur identifiant.
     * @return Retourne le identifiant.
     */
    public String getIdentifiant()
    {
        return this.identifiant;
    }

    /**
     * Setter pour identifiant.
     * @param identifiant le identifiant à écrire.
     */
    public void setIdentifiant(final String identifiant)
    {
        this.identifiant = identifiant;
    }

    /**
     * Getter sur intervenantId.
     * @return Retourne le intervenantId.
     */
    public Integer getIntervenantId()
    {
        return this.intervenantId;
    }

    /**
     * Setter pour intervenantId.
     * @param intervenantId le intervenantId à écrire.
     */
    public void setIntervenantId(final Integer intervenantId)
    {
        this.intervenantId = intervenantId;
    }

    /**
     * Getter sur titre.
     * @return Retourne le titre.
     */
    public String getTitre()
    {
        return this.titre;
    }

    /**
     * Setter pour titre.
     * @param titre le titre à écrire.
     */
    public void setTitre(final String titre)
    {
        this.titre = titre;
    }

    /**
     * Getter sur numAdeli.
     * @return Retourne le numAdeli.
     */
    public String getNumAdeli()
    {
        return this.numAdeli;
    }

    /**
     * Setter pour numAdeli.
     * @param numAdeli le numAdeli à écrire.
     */
    public void setNumAdeli(final String numAdeli)
    {
        this.numAdeli = numAdeli;
    }

}
