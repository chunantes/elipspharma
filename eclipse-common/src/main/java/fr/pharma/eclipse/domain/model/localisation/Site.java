package fr.pharma.eclipse.domain.model.localisation;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;
import org.hibernate.validator.constraints.NotEmpty;

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.comparator.suivi.SuiviComparator;
import fr.pharma.eclipse.domain.model.common.BeanObjectSuivi;
import fr.pharma.eclipse.domain.model.common.BeanWithNom;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.suivi.localisation.SiteSuivi;

/**
 * Classe métier représentant un site.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "site")
public class Site extends BeanObjectSuivi implements BeanWithNom {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -7668890632843705973L;

    /**
     * Code.
     */
    @Column(name = "code")
    @NotNull
    @NotEmpty
    private String code;

    /**
     * Nom.
     */
    @Column(name = "nom")
    @NotNull
    @NotEmpty
    private String nom;

    /**
     * Etablissement.
     */
    @ManyToOne
    @JoinColumn(name = "id_etablissement", nullable = false)
    @Index(name = "idx_etab_site")
    @NotNull
    private Etablissement etablissement;

    /**
     * Adresse.
     */
    @Column(name = "adresse", columnDefinition = "TEXT")
    private String adresse;

    /**
     * Code postal.
     */
    @Column(name = "codePostal")
    private String codePostal;

    /**
     * Ville.
     */
    @Column(name = "ville")
    private String ville;

    /**
     * Pharmacies.
     */
    @ManyToMany(targetEntity = Pharmacie.class, mappedBy = "sites", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = BeanWithNomComparator.class)
    private SortedSet<Pharmacie> pharmacies = new TreeSet<Pharmacie>(new BeanWithNomComparator());

    /**
     * Liste des modifications du site.
     */
    @OneToMany(mappedBy = "site", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private final SortedSet<SiteSuivi> modifs = new TreeSet<SiteSuivi>(new SuiviComparator());

    /**
     * Getter sur nom.
     * @return Retourne le nom.
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
     * Getter sur etablissement.
     * @return Retourne le etablissement.
     */
    public Etablissement getEtablissement() {
        return this.etablissement;
    }

    /**
     * Setter pour etablissement.
     * @param etablissement le etablissement à écrire.
     */
    public void setEtablissement(final Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    /**
     * Getter sur modifs.
     * @return Retourne le modifs.
     */
    @Override
    public SortedSet<SiteSuivi> getModifs() {
        return this.modifs;
    }

    /**
     * Getter sur pharmacies.
     * @return Retourne le pharmacies.
     */
    public SortedSet<Pharmacie> getPharmacies() {
        return this.pharmacies;
    }

    /**
     * Setter pour pharmacies.
     * @param pharmacies le pharmacies à écrire.
     */
    public void setPharmacies(final SortedSet<Pharmacie> pharmacies) {
        this.pharmacies = pharmacies;
    }

    /**
     * Getter sur code.
     * @return Retourne le code.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Setter pour code.
     * @param code le code à écrire.
     */
    public void setCode(final String code) {
        this.code = code;
    }

    /**
     * Getter pour adresse.
     * @return Le adresse
     */
    public String getAdresse() {
        return this.adresse;
    }

    /**
     * Setter pour adresse.
     * @param adresse Le adresse à écrire.
     */
    public void setAdresse(final String adresse) {
        this.adresse = adresse;
    }

    /**
     * Getter pour codePostal.
     * @return Le codePostal
     */
    public String getCodePostal() {
        return this.codePostal;
    }

    /**
     * Setter pour codePostal.
     * @param codePostal Le codePostal à écrire.
     */
    public void setCodePostal(final String codePostal) {
        this.codePostal = codePostal;
    }

    /**
     * Getter pour ville.
     * @return Le ville
     */
    public String getVille() {
        return this.ville;
    }

    /**
     * Setter pour ville.
     * @param ville Le ville à écrire.
     */
    public void setVille(final String ville) {
        this.ville = ville;
    }

}
