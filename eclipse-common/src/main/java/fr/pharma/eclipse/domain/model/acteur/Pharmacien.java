package fr.pharma.eclipse.domain.model.acteur;

import java.util.Calendar;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;
import org.hibernate.annotations.Where;

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.domain.enums.TypePharmacien;
import fr.pharma.eclipse.domain.model.acteur.document.DocumentCV;
import fr.pharma.eclipse.domain.model.acteur.document.DocumentValidationFormation;
import fr.pharma.eclipse.domain.model.common.BeanParentDocument;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Classe métier représentant un Pharmacien.
 
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("PHARMACIEN")
public class Pharmacien
    extends Personne
    implements BeanParentDocument
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -6723907984751874179L;

    /**
     * Type de pharmacien.
     */
    @Column(name = "typePharmacien")
    @Enumerated(EnumType.STRING)
    @NotNull
    private TypePharmacien typePharmacien;

    /**
     * Pharmacies.
     */
    @ManyToMany(targetEntity = Pharmacie.class)
    @JoinTable(name = "pharmacien_pharmacie", joinColumns = @JoinColumn(name = "id_pharmacien"), inverseJoinColumns = @JoinColumn(name = "id_pharmacie"))
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = BeanWithNomComparator.class)
    private SortedSet<Pharmacie> pharmacies = new TreeSet<Pharmacie>(new BeanWithNomComparator());

    /**
     * CV.
     */
    @OneToOne(mappedBy = "pharmacien", cascade = CascadeType.ALL)
    @Where(clause = "type='CV'")
    @LazyCollection(LazyCollectionOption.TRUE)
    private DocumentCV documentCV;

    /**
     * Validation de la formation.
     */
    @OneToOne(mappedBy = "pharmacien", cascade = CascadeType.ALL)
    @Where(clause = "type='VALIDATION_FORMATION'")
    @LazyCollection(LazyCollectionOption.TRUE)
    private DocumentValidationFormation documentValidationFormation;

    /**
     * Date d'arrivee dans le service.
     */
    @Column(name = "dateArriveeService")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateArriveeService;

    /**
     * Date de dépaert du service.
     */
    @Column(name = "dateDepartService")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateDepartService;

    /**
     * Date de validation de formation.
     */
    @Column(name = "dateValidationFormation")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateValidationFormation;

    /**
     * Getter pour pharmacies.
     * @return Le pharmacies
     */
    public SortedSet<Pharmacie> getPharmacies()
    {
        return this.pharmacies;
    }

    /**
     * Setter pour pharmacies.
     * @param pharmacies Le pharmacies à écrire.
     */
    public void setPharmacies(final SortedSet<Pharmacie> pharmacies)
    {
        this.pharmacies = pharmacies;
    }

    /**
     * Getter pour typePharmacien.
     * @return Le typePharmacien
     */
    public TypePharmacien getTypePharmacien()
    {
        return this.typePharmacien;
    }

    /**
     * Setter pour typePharmacien.
     * @param typePharmacien Le typePharmacien à écrire.
     */
    public void setTypePharmacien(final TypePharmacien typePharmacien)
    {
        this.typePharmacien = typePharmacien;
    }

    /**
     * Getter pour documentCV.
     * @return Le documentCV
     */
    public DocumentCV getDocumentCV()
    {
        return this.documentCV;
    }

    /**
     * Setter pour documentCV.
     * @param documentCV Le documentCV à écrire.
     */
    public void setDocumentCV(final DocumentCV documentCV)
    {
        this.documentCV = documentCV;
    }

    /**
     * Getter pour documentValidationFormation.
     * @return Le documentValidationFormation
     */
    public DocumentValidationFormation getDocumentValidationFormation()
    {
        return this.documentValidationFormation;
    }

    /**
     * Setter pour documentValidationFormation.
     * @param documentValidationFormation Le documentValidationFormation à écrire.
     */
    public void setDocumentValidationFormation(final DocumentValidationFormation documentValidationFormation)
    {
        this.documentValidationFormation = documentValidationFormation;
    }

    /**
     * Getter pour dateArriveeService.
     * @return Le dateArriveeService
     */
    public Calendar getDateArriveeService()
    {
        return this.dateArriveeService;
    }

    /**
     * Setter pour dateArriveeService.
     * @param dateArriveeService Le dateArriveeService à écrire.
     */
    public void setDateArriveeService(final Calendar dateArriveeService)
    {
        this.dateArriveeService = dateArriveeService;
    }

    /**
     * Getter pour dateDepartService.
     * @return Le dateDepartService
     */
    public Calendar getDateDepartService()
    {
        return this.dateDepartService;
    }

    /**
     * Setter pour dateDepartService.
     * @param dateDepartService Le dateDepartService à écrire.
     */
    public void setDateDepartService(final Calendar dateDepartService)
    {
        this.dateDepartService = dateDepartService;
    }

    /**
     * Getter pour dateValidationFormation.
     * @return Le dateValidationFormation
     */
    public Calendar getDateValidationFormation()
    {
        return this.dateValidationFormation;
    }

    /**
     * Setter pour dateValidationFormation.
     * @param dateValidationFormation Le dateValidationFormation à écrire.
     */
    public void setDateValidationFormation(final Calendar dateValidationFormation)
    {
        this.dateValidationFormation = dateValidationFormation;
    }

}
