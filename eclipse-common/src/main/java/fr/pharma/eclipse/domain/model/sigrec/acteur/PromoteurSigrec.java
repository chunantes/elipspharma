package fr.pharma.eclipse.domain.model.sigrec.acteur;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import fr.pharma.eclipse.domain.enums.TypePromoteur;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;

/**
 * Classe métier représentant un Promoteur importé de SIGREC.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "promoteur_sigrec")
public class PromoteurSigrec extends BeanObject implements Contactable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 29506428300987897L;

    /**
     * Code.
     */
    @Column(name = "identifiant")
    private String identifiant;

    /**
     * Type.
     */
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypePromoteur type;

    /**
     * Contact.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_contact")
    @Index(name = "idx_contact_promoteur_sigrec")
    private ContactSigrec contact;

    /**
     * Liste des arc promoteurs.
     */
    @OneToMany(mappedBy = "promoteur", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    private List<ARCPromoteurSigrec> arcs = new ArrayList<ARCPromoteurSigrec>();

    /**
     * Essais.
     */
    @OneToMany(mappedBy = "promoteur", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    private final List<EssaiSigrec> essais = new ArrayList<EssaiSigrec>();

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
     * Getter sur type.
     * @return Retourne le type.
     */
    public TypePromoteur getType() {
        return this.type;
    }

    /**
     * Setter pour type.
     * @param type le type à écrire.
     */
    public void setType(final TypePromoteur type) {
        this.type = type;
    }

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
     * Getter sur arcs.
     * @return Retourne le arcs.
     */
    public List<ARCPromoteurSigrec> getArcs() {
        return this.arcs;
    }

    /**
     * Setter pour arcs.
     * @param arcs le arcs à écrire.
     */
    public void setArcs(final List<ARCPromoteurSigrec> arcs) {
        this.arcs = arcs;
    }

    /**
     * Getter sur essais.
     * @return Retourne le essais.
     */
    public List<EssaiSigrec> getEssais() {
        return this.essais;
    }

}
