package fr.pharma.eclipse.domain.model.essai.detail.dates;

import java.io.Serializable;
import java.util.Calendar;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import fr.pharma.eclipse.comparator.suivi.SuiviComparator;
import fr.pharma.eclipse.domain.model.common.BeanObjectSuivi;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.suivi.essai.detail.DetailDatesSuivi;

/**
 * Classe métier représentant les dates d'un essai clinique.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_detail_dates")
public class DetailDates extends BeanObjectSuivi implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 8436477696491272528L;

    /**
     * Essai auquel est rattaché le détail.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_essai")
    private Essai essai;

    /**
     * Date de début de l'étude prévisionnelle.
     */
    @Column(name = "debutEtudePrev")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar debutEtudePrev;

    /**
     * Date de fin de l'étude prévisionnelle.
     */
    @Column(name = "finEtudePrev")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar finEtudePrev;

    /**
     * Date de première réception.
     */
    @Column(name = "dateReception")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar reception;

    /**
     * Date d'activation prévisionnelle.
     */
    @Column(name = "activationPrev")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar activationPrev;

    /**
     * Date de début d'inclusion prévisionnelle.
     */
    @Column(name = "debutInclusionPrev")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar debutInclusionPrev;

    /**
     * Date de fin d'inclusion prévisionnelle.
     */
    @Column(name = "finInclusionPrev")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar finInclusionPrev;

    /**
     * Date réeelle d'activation de l'essai.
     */
    @Column(name = "activation")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar activation;

    /**
     * Date réelle de début de l'essai.
     */
    @Column(name = "debutEtude")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar debutEtude;

    /**
     * Date réeelle de fin de l'essai.
     */
    @Column(name = "finEtude")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar finEtude;

    /**
     * Date réeelle de début d'inclusion.
     */
    @Column(name = "debutInclusion")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar debutInclusion;

    /**
     * Date réeelle de fin d'inclusion.
     */
    @Column(name = "finInclusion")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar finInclusion;

    /**
     * Date de fin des dispensations.
     */
    @Column(name = "finDispensations")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar finDispensations;

    /**
     * Date de pré-clôture.
     */
    @Column(name = "preCloture")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar preCloture;

    /**
     * Date de la visite de cloture.
     */
    @Column(name = "cloture")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar cloture;

    /**
     * Liste des modifications du détail.
     */
    @OneToMany(mappedBy = "detailDates", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private SortedSet<DetailDatesSuivi> modifs = new TreeSet<DetailDatesSuivi>(new SuiviComparator());

    /**
     * Getter sur essai.
     * @return Retourne le essai.
     */
    public Essai getEssai() {
        return this.essai;
    }

    /**
     * Setter pour essai.
     * @param essai le essai à écrire.
     */
    public void setEssai(final Essai essai) {
        this.essai = essai;
    }

    /**
     * Getter sur debutEtudePrev.
     * @return Retourne le debutEtudePrev.
     */
    public Calendar getDebutEtudePrev() {
        return this.debutEtudePrev;
    }

    /**
     * Setter pour debutEtudePrev.
     * @param debutEtudePrev le debutEtudePrev à écrire.
     */
    public void setDebutEtudePrev(final Calendar debutEtudePrev) {
        this.debutEtudePrev = debutEtudePrev;
    }

    /**
     * Getter sur finEtudePrev.
     * @return Retourne le finEtudePrev.
     */
    public Calendar getFinEtudePrev() {
        return this.finEtudePrev;
    }

    /**
     * Setter pour finEtudePrev.
     * @param finEtudePrev le finEtudePrev à écrire.
     */
    public void setFinEtudePrev(final Calendar finEtudePrev) {
        this.finEtudePrev = finEtudePrev;
    }

    /**
     * Getter sur activationPrev.
     * @return Retourne le activationPrev.
     */
    public Calendar getActivationPrev() {
        return this.activationPrev;
    }

    /**
     * Setter pour activationPrev.
     * @param activationPrev le activationPrev à écrire.
     */
    public void setActivationPrev(final Calendar activationPrev) {
        this.activationPrev = activationPrev;
    }

    /**
     * Getter sur debutInclusionPrev.
     * @return Retourne le debutInclusionPrev.
     */
    public Calendar getDebutInclusionPrev() {
        return this.debutInclusionPrev;
    }

    /**
     * Setter pour debutInclusionPrev.
     * @param debutInclusionPrev le debutInclusionPrev à écrire.
     */
    public void setDebutInclusionPrev(final Calendar debutInclusionPrev) {
        this.debutInclusionPrev = debutInclusionPrev;
    }

    /**
     * Getter sur finInclusionPrev.
     * @return Retourne le finInclusionPrev.
     */
    public Calendar getFinInclusionPrev() {
        return this.finInclusionPrev;
    }

    /**
     * Setter pour finInclusionPrev.
     * @param finInclusionPrev le finInclusionPrev à écrire.
     */
    public void setFinInclusionPrev(final Calendar finInclusionPrev) {
        this.finInclusionPrev = finInclusionPrev;
    }

    /**
     * Getter sur modifs.
     * @return Retourne le modifs.
     */
    @Override
    public SortedSet<DetailDatesSuivi> getModifs() {
        return this.modifs;
    }

    /**
     * Setter pour modifs.
     * @param modifs le modifs à écrire.
     */
    public void setModifs(final SortedSet<DetailDatesSuivi> modifs) {
        this.modifs = modifs;
    }

    /**
     * Getter sur debutEtude.
     * @return Retourne le debutEtude.
     */
    public Calendar getDebutEtude() {
        return this.debutEtude;
    }

    /**
     * Setter pour debutEtude.
     * @param debutEtude le debutEtude à écrire.
     */
    public void setDebutEtude(final Calendar debutEtude) {
        this.debutEtude = debutEtude;
    }

    /**
     * Getter sur finEtude.
     * @return Retourne le finEtude.
     */
    public Calendar getFinEtude() {
        return this.finEtude;
    }

    /**
     * Setter pour finEtude.
     * @param finEtude le finEtude à écrire.
     */
    public void setFinEtude(final Calendar finEtude) {
        this.finEtude = finEtude;
    }

    /**
     * Getter sur debutInclusion.
     * @return Retourne le debutInclusion.
     */
    public Calendar getDebutInclusion() {
        return this.debutInclusion;
    }

    /**
     * Setter pour debutInclusion.
     * @param debutInclusion le debutInclusion à écrire.
     */
    public void setDebutInclusion(final Calendar debutInclusion) {
        this.debutInclusion = debutInclusion;
    }

    /**
     * Getter sur finInclusion.
     * @return Retourne le finInclusion.
     */
    public Calendar getFinInclusion() {
        return this.finInclusion;
    }

    /**
     * Setter pour finInclusion.
     * @param finInclusion le finInclusion à écrire.
     */
    public void setFinInclusion(final Calendar finInclusion) {
        this.finInclusion = finInclusion;
    }

    /**
     * Getter sur finDispensations.
     * @return Retourne le finDispensations.
     */
    public Calendar getFinDispensations() {
        return this.finDispensations;
    }

    /**
     * Setter pour finDispensations.
     * @param finDispensations le finDispensations à écrire.
     */
    public void setFinDispensations(final Calendar finDispensations) {
        this.finDispensations = finDispensations;
    }

    /**
     * Getter sur preCloture.
     * @return Retourne le preCloture.
     */
    public Calendar getPreCloture() {
        return this.preCloture;
    }

    /**
     * Setter pour preCloture.
     * @param preCloture le preCloture à écrire.
     */
    public void setPreCloture(final Calendar preCloture) {
        this.preCloture = preCloture;
    }

    /**
     * Getter sur cloture.
     * @return Retourne le cloture.
     */
    public Calendar getCloture() {
        return this.cloture;
    }

    /**
     * Setter pour cloture.
     * @param cloture le cloture à écrire.
     */
    public void setCloture(final Calendar cloture) {
        this.cloture = cloture;
    }

    /**
     * Getter sur activation.
     * @return Retourne le activation.
     */
    public Calendar getActivation() {
        return this.activation;
    }

    /**
     * Setter pour activation.
     * @param activation le activation à écrire.
     */
    public void setActivation(final Calendar activation) {
        this.activation = activation;
    }

    /**
     * Getter pour reception.
     * @return Le reception
     */
    public Calendar getReception() {
        return this.reception;
    }

    /**
     * Setter pour reception.
     * @param reception Le reception à écrire.
     */
    public void setReception(final Calendar reception) {
        this.reception = reception;
    }

}
