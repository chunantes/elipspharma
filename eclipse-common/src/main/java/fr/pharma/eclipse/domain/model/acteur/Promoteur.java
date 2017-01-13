package fr.pharma.eclipse.domain.model.acteur;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotEmpty;

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.comparator.suivi.SuiviComparator;
import fr.pharma.eclipse.domain.enums.TypePromoteur;
import fr.pharma.eclipse.domain.model.common.BeanObjectSuivi;
import fr.pharma.eclipse.domain.model.suivi.acteur.PromoteurSuivi;

/**
 * Classe métier représentant un Promoteur.
 * 
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "promoteur")
public class Promoteur extends BeanObjectSuivi {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -1696197874188542328L;

    /**
     * Raison sociale.
     */
    @Column(name = "raisonSociale")
    @NotNull
    @NotEmpty
    private String raisonSociale;

    /**
     * Identifiant.
     */
    @Column(name = "identifiant")
    private String identifiant;

    /**
     * Type.
     */
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private TypePromoteur type;

    /**
     * Liste des arc promoteurs.
     */
    @OneToMany(mappedBy = "promoteur", cascade = CascadeType.ALL)
    @Where(clause = "type='ARC_PROMOTEUR'")
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = BeanWithNomComparator.class)
    private SortedSet<ArcPromoteur> arcPromoteurs = new TreeSet<ArcPromoteur>(
            new BeanWithNomComparator());

    /**
     * Liste des contacts promoteurs.
     */
    @OneToMany(mappedBy = "promoteur", cascade = CascadeType.ALL)
    @Where(clause = "type='PROMOTEUR'")
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = BeanWithNomComparator.class)
    private SortedSet<ContactPromoteur> contactPromoteurs = new TreeSet<ContactPromoteur>(
            new BeanWithNomComparator());

    /**
     * Liste des modifications du promoteur.
     */
    @OneToMany(mappedBy = "promoteur", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private final SortedSet<PromoteurSuivi> modifs = new TreeSet<PromoteurSuivi>(
            new SuiviComparator());

    /**
     * Getter sur raisonSociale.
     * 
     * @return Retourne le raisonSociale.
     */
    public String getRaisonSociale() {
        return this.raisonSociale;
    }

    /**
     * Setter pour raisonSociale.
     * 
     * @param raisonSociale
     *            le raisonSociale à écrire.
     */
    public void setRaisonSociale(final String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    /**
     * Getter sur type.
     * 
     * @return Retourne le type.
     */
    public TypePromoteur getType() {
        return this.type;
    }

    /**
     * Setter pour type.
     * 
     * @param type
     *            le type à écrire.
     */
    public void setType(final TypePromoteur type) {
        this.type = type;
    }

    /**
     * Getter sur identifiant.
     * 
     * @return Retourne le identifiant.
     */
    public String getIdentifiant() {
        return this.identifiant;
    }

    /**
     * Setter pour identifiant.
     * 
     * @param identifiant
     *            le identifiant à écrire.
     */
    public void setIdentifiant(final String identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Getter sur modifs.
     * 
     * @return Retourne le modifs.
     */
    @Override
    public SortedSet<PromoteurSuivi> getModifs() {
        return this.modifs;
    }

    /**
     * Getter pour arcPromoteurs.
     * 
     * @return Le arcPromoteurs
     */
    public SortedSet<ArcPromoteur> getArcPromoteurs() {
        return this.arcPromoteurs;
    }

    /**
     * Setter pour arcPromoteurs.
     * 
     * @param arcPromoteurs
     *            Le arcPromoteurs à écrire.
     */
    public void setArcPromoteurs(final SortedSet<ArcPromoteur> arcPromoteurs) {
        this.arcPromoteurs = arcPromoteurs;
    }

    /**
     * Getter pour contactPromoteurs.
     * 
     * @return Le contactPromoteurs
     */
    public SortedSet<ContactPromoteur> getContactPromoteurs() {
        return this.contactPromoteurs;
    }

    /**
     * Setter pour contactPromoteurs.
     * 
     * @param contactPromoteurs
     *            Le contactPromoteurs à écrire.
     */
    public void setContactPromoteurs(
            final SortedSet<ContactPromoteur> contactPromoteurs) {
        this.contactPromoteurs = contactPromoteurs;
    }

}
