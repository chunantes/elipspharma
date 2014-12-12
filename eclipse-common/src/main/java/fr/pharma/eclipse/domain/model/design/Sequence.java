package fr.pharma.eclipse.domain.model.design;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import fr.pharma.eclipse.comparator.design.DesignableComparator;
import fr.pharma.eclipse.comparator.design.PrescriptionTypeComparator;
import fr.pharma.eclipse.domain.enums.TypeDesignable;
import fr.pharma.eclipse.domain.enums.design.UniteTemps;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Bean métier représentant une sequence.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "sequence")
public class Sequence extends BeanObject implements Designable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 8938270836977564334L;

    /**
     * Nom de la séquence.
     */
    @Column(name = "nom")
    private String nom;

    /**
     * Début de la séquence.
     */
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "nb", column = @Column(name = "nb_debut")), @AttributeOverride(name = "unite", column = @Column(name = "unite_debut")) })
    private TempsPrescription debut = new TempsPrescription();

    /**
     * Fin de la séquence.
     */
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "nb", column = @Column(name = "nb_fin")), @AttributeOverride(name = "unite", column = @Column(name = "unite_fin")) })
    private TempsPrescription fin = new TempsPrescription();

    /**
     * Nb duree.
     */
    @Column(name = "nb_duree")
    private Integer nbDuree;

    /**
     * Unité duree.
     */
    @Column(name = "unite_duree")
    @Enumerated(EnumType.STRING)
    private UniteTemps uniteDuree;

    /**
     * Type designable.
     */
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeDesignable type;

    /**
     * Description.
     */
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    /**
     * Bras parent.
     */
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE })
    @JoinColumn(name = "id_bras_sequence")
    @Index(name = "idx_bras_sequence")
    private Bras parent;

    /**
     * Prescriptions.
     */
    @OneToMany(mappedBy = "sequence", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = PrescriptionTypeComparator.class)
    private SortedSet<PrescriptionType> prescriptions = new TreeSet<PrescriptionType>(new PrescriptionTypeComparator());

    /**
     * Getter sur description.
     * @return Retourne le description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter pour description.
     * @param description le description à écrire.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Getter sur parent.
     * @return Retourne le parent.
     */
    @Override
    public Bras getParent() {
        return this.parent;
    }

    /**
     * Setter pour parent.
     * @param parent le parent à écrire.
     */
    public void setParent(final Bras parent) {
        this.parent = parent;
    }

    /**
     * Getter sur prescriptions.
     * @return Retourne le prescriptions.
     */
    public SortedSet<PrescriptionType> getPrescriptions() {
        return this.prescriptions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
     * {@inheritDoc}
     */
    @Override
    public String getLibelleProduit() {
        final StringBuffer buff = new StringBuffer();
        for (final PrescriptionType p : this.prescriptions) {
            buff.append(p.getProduit().getDenomination());
            buff.append(" ");
        }
        return buff.toString();
    }

    /**
     * Getter sur type.
     * @return Retourne le type.
     */
    @Override
    public TypeDesignable getType() {
        return this.type;
    }

    /**
     * Setter pour type.
     * @param type le type à écrire.
     */
    public void setType(final TypeDesignable type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SortedSet<Designable> getEnfants() {
        return new TreeSet<Designable>(new DesignableComparator());
    }

    /**
     * Setter pour debut.
     * @param debut le debut à écrire.
     */
    public void setDebut(final TempsPrescription debut) {
        this.debut = debut;
    }

    /**
     * Setter pour fin.
     * @param fin le fin à écrire.
     */
    public void setFin(final TempsPrescription fin) {
        this.fin = fin;
    }

    /**
     * Setter pour prescriptions.
     * @param prescriptions le prescriptions à écrire.
     */
    public void setPrescriptions(final SortedSet<PrescriptionType> prescriptions) {
        this.prescriptions = prescriptions;
    }

    /**
     * Getter sur debut.
     * @return Retourne le debut.
     */
    @Override
    public TempsPrescription getDebut() {
        return this.debut;
    }

    /**
     * Getter sur fin.
     * @return Retourne le fin.
     */
    @Override
    public TempsPrescription getFin() {
        return this.fin;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNomComplet() {
        final StringBuilder buff = new StringBuilder((parent == null) ? "" : this.getParent().getNomComplet());
        buff.append(EclipseConstants.DASH).append(this.getNom());
        return buff.toString();
    }

    /**
     * Getter pour duree.
     * @return Le duree
     */
    public TempsPrescription getDuree() {
        final TempsPrescription t = new TempsPrescription();
        t.setNb(this.nbDuree);
        t.setUnite(this.uniteDuree);
        return t;
    }

    public void setDuree(final TempsPrescription t) {
        this.nbDuree = t.getNb();
        this.uniteDuree = t.getUnite();
    }

    /**
     * Getter pour nbDuree.
     * @return Le nbDuree
     */
    public Integer getNbDuree() {
        return this.nbDuree;
    }

    /**
     * Setter pour nbDuree.
     * @param nbDuree Le nbDuree à écrire.
     */
    public void setNbDuree(final Integer nbDuree) {
        this.nbDuree = nbDuree;
    }

    /**
     * Getter pour uniteDuree.
     * @return Le uniteDuree
     */
    public UniteTemps getUniteDuree() {
        return this.uniteDuree;
    }

    /**
     * Setter pour uniteDuree.
     * @param uniteDuree Le uniteDuree à écrire.
     */
    public void setUniteDuree(final UniteTemps uniteDuree) {
        this.uniteDuree = uniteDuree;
    }
}
