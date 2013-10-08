package fr.pharma.eclipse.domain.model.sigrec.acteur;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;

/**
 * Classe du modèle d'import SIGREC représentant un CRO.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "cro_sigrec")
public class CROSigrec extends BeanObject implements Contactable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -8346341002237934459L;

    /**
     * Identifiant national.
     */
    @Column(name = "identifiant")
    private String identifiant;

    /**
     * Promoteur.
     */
    @ManyToOne
    @JoinColumn(name = "id_essai")
    @Index(name = "idx_essai_cro_sigrec")
    private EssaiSigrec essai;

    /**
     * Contact Le nom de la CRO est dans le contact.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_contact")
    @Index(name = "idx_contact_cro_sigrec")
    private ContactSigrec contact;

    /**
     * Getter sur contact.
     * @return Retourne le contact.
     */
    @Override
    public ContactSigrec getContact() {
        return this.contact;
    }

    /**
     * Setter pour contact.
     * @param contact le contact à écrire.
     */
    @Override
    public void setContact(final ContactSigrec contact) {
        this.contact = contact;
    }

    /**
     * Getter sur identifiant.
     * @return Retourne le identifiant.
     */
    public String getIdentifiant() {
        return this.identifiant;
    }

    /**
     * Setter pour identifiant.
     * @param identifiant le identifiant à écrire.
     */
    public void setIdentifiant(final String identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Getter sur essai.
     * @return Retourne le essai.
     */
    public EssaiSigrec getEssai() {
        return this.essai;
    }

    /**
     * Setter pour essai.
     * @param essai le essai à écrire.
     */
    public void setEssai(final EssaiSigrec essai) {
        this.essai = essai;
    }

}
