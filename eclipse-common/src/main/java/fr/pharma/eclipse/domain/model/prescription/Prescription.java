package fr.pharma.eclipse.domain.model.prescription;

import java.util.Calendar;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import fr.pharma.eclipse.comparator.common.EclipseListComparator;
import fr.pharma.eclipse.comparator.prescription.ProduitPrescritComparator;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.EssaiElement;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.domain.model.patient.Inclusion;

/**
 * Classe métier représentant une Prescription réelle.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "prescription")
public class Prescription
    extends BeanObject
    implements EssaiElement
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -8275087629089159466L;

    /**
     * Inclusion.
     */
    @ManyToOne
    @JoinColumn(name = "id_inclusion", nullable = false)
    @Index(name = "idx_inclusion_prescription")
    @NotNull
    private Inclusion inclusion;

    /**
     * Sequence.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sequence")
    @Index(name = "idx_sequence_prescriptin")
    private Sequence sequence;

    /**
     * Service.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_service", nullable = false)
    @Index(name = "idx_service_prescription")
    @NotNull
    private Service service;

    /**
     * Investigateur.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_investigateur", nullable = false)
    @Index(name = "idx_investigateur_prescription")
    @NotNull
    private Investigateur investigateur;

    /**
     * Date de prescription.
     */
    @Column(name = "datePrescription")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar datePrescription;

    /**
     * Date de debut de traitement.
     */
    @Column(name = "dateDebutTraitement")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateDebutTraitement;

    /**
     * Numéro de visite.
     */
    @Column(name = "numPrescription")
    private Integer numPrescription;

    /**
     * Numéro de visite.
     */
    @Column(name = "numVisite")
    private String numVisite;

    /**
     * Dispensée ?.
     */
    @Column(name = "dispense")
    private Boolean dispense;

    /**
     * Commentaire.
     */
    @Column(name = "commentaire", columnDefinition = "TEXT")
    private String commentaire;

    /**
     * Liste de dispensations.
     */
    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = EclipseListComparator.class)
    private final SortedSet<Dispensation> dispensations =
        new TreeSet<Dispensation>(new EclipseListComparator());

    /**
     * Liste de produits prescrits.
     */
    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = ProduitPrescritComparator.class)
    private SortedSet<ProduitPrescrit> produitsPrescrits =
        new TreeSet<ProduitPrescrit>(new ProduitPrescritComparator());

    /**
     * Retourne une chaine de caractère contenant les libelles des produits prescrits.
     * @return une chaine de caractère contenant les libelles des produits prescrits.
     */
    public String getLibelleProduitsPrescrits()
    {
        final StringBuffer buff = new StringBuffer();
        for (final ProduitPrescrit p : this.produitsPrescrits)
        {
            buff.append(p.getProduit().getDenomination());
            buff.append(" ");
        }
        return buff.toString();
    }

    /**
     * Getter sur investigateur.
     * @return Retourne le investigateur.
     */
    public Investigateur getInvestigateur()
    {
        return this.investigateur;
    }

    /**
     * Setter pour investigateur.
     * @param investigateur le investigateur à écrire.
     */
    public void setInvestigateur(final Investigateur investigateur)
    {
        this.investigateur = investigateur;
    }

    /**
     * Getter sur datePrescription.
     * @return Retourne le datePrescription.
     */
    public Calendar getDatePrescription()
    {
        return this.datePrescription;
    }

    /**
     * Setter pour datePrescription.
     * @param datePrescription le datePrescription à écrire.
     */
    public void setDatePrescription(final Calendar datePrescription)
    {
        this.datePrescription = datePrescription;
    }

    /**
     * Getter sur numPrescription.
     * @return Retourne le numPrescription.
     */
    public Integer getNumPrescription()
    {
        return this.numPrescription;
    }

    /**
     * Setter pour numPrescription.
     * @param numPrescription le numPrescription à écrire.
     */
    public void setNumPrescription(final Integer numPrescription)
    {
        this.numPrescription = numPrescription;
    }

    /**
     * Getter sur service.
     * @return Retourne le service.
     */
    public Service getService()
    {
        return this.service;
    }

    /**
     * Setter pour service.
     * @param service le service à écrire.
     */
    public void setService(final Service service)
    {
        this.service = service;
    }

    /**
     * Getter sur produitsPrescrits.
     * @return Retourne le produitsPrescrits.
     */
    public SortedSet<ProduitPrescrit> getProduitsPrescrits()
    {
        return this.produitsPrescrits;
    }

    /**
     * Setter pour produitsPrescrits.
     * @param produitsPrescrits le produitsPrescrits à écrire.
     */
    public void setProduitsPrescrits(final SortedSet<ProduitPrescrit> produitsPrescrits)
    {
        this.produitsPrescrits = produitsPrescrits;
    }

    /**
     * Getter sur inclusion.
     * @return Retourne le inclusion.
     */
    public Inclusion getInclusion()
    {
        return this.inclusion;
    }

    /**
     * Setter pour inclusion.
     * @param inclusion le inclusion à écrire.
     */
    public void setInclusion(final Inclusion inclusion)
    {
        this.inclusion = inclusion;
    }

    /**
     * Getter sur dateDebutTraitement.
     * @return Retourne le dateDebutTraitement.
     */
    public Calendar getDateDebutTraitement()
    {
        return this.dateDebutTraitement;
    }

    /**
     * Setter pour dateDebutTraitement.
     * @param dateDebutTraitement le dateDebutTraitement à écrire.
     */
    public void setDateDebutTraitement(final Calendar dateDebutTraitement)
    {
        this.dateDebutTraitement = dateDebutTraitement;
    }

    /**
     * Getter sur sequence.
     * @return Retourne le sequence.
     */
    public Sequence getSequence()
    {
        return this.sequence;
    }

    /**
     * Setter pour sequence.
     * @param sequence le sequence à écrire.
     */
    public void setSequence(final Sequence sequence)
    {
        this.sequence = sequence;
    }

    /**
     * Getter sur dispense.
     * @return Retourne le dispense.
     */
    public Boolean getDispense()
    {
        return this.dispense;
    }

    /**
     * Getter sur dispense.
     * @return Retourne le dispense.
     */
    public Boolean getDispenseEmpty()
    {
        return this.dispense
               || !this.getDispensations().isEmpty();
    }

    /**
     * Setter pour dispense.
     * @param dispense le dispense à écrire.
     */
    public void setDispense(final Boolean dispense)
    {
        this.dispense = dispense;
    }

    /**
     * {@inheritDoc}
     */
    public Essai getEssai()
    {
        return this.getInclusion().getEssai();
    }

    /**
     * Getter pour commentaire.
     * @return Le commentaire
     */
    public String getCommentaire()
    {
        return this.commentaire;
    }

    /**
     * Setter pour commentaire.
     * @param commentaire Le commentaire à écrire.
     */
    public void setCommentaire(final String commentaire)
    {
        this.commentaire = commentaire;
    }

    /**
     * Getter pour dispensations.
     * @return Le dispensations
     */
    public SortedSet<Dispensation> getDispensations()
    {
        return this.dispensations;
    }

    /**
     * Getter pour numVisite.
     * @return Le numVisite
     */
    public String getNumVisite()
    {
        return this.numVisite;
    }

    /**
     * Setter pour numVisite.
     * @param numVisite Le numVisite à écrire.
     */
    public void setNumVisite(final String numVisite)
    {
        this.numVisite = numVisite;
    }

    /**
     * Marquer produit dispensé
     * @param clé la clé du produit
     */
    public void setProduitDispense(final Long cle)
    {
        for (final ProduitPrescrit p : this.produitsPrescrits)
        {
            if (p.getId() == cle)
            {
                p.setDispense(true);
            }
        }
    }

}
