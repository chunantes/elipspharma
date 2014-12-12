package fr.pharma.eclipse.domain.model.essai.detail.produit;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;
import org.hibernate.annotations.Where;

import fr.pharma.eclipse.comparator.produit.detail.ProduitComparator;
import fr.pharma.eclipse.comparator.suivi.SuiviComparator;
import fr.pharma.eclipse.domain.model.common.BeanObjectSuivi;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.suivi.essai.detail.DetailProduitSuivi;

/**
 * Classe métier représentant les informations de détail des produits d'un essai
 * clinique.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_detail_produit")
public class DetailProduit extends BeanObjectSuivi {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -4555820200467933539L;

    /**
     * Essai auquel est rattaché le détail.
     */
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "id_essai")
    private Essai essai;

    /**
     * Produits de type MEDICAMENT.
     */
    @OneToMany(mappedBy = "detailProduit", cascade = CascadeType.ALL, orphanRemoval = true)
    @Where(clause = "type='MEDICAMENT'")
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = ProduitComparator.class)
    private SortedSet<Produit> medicaments = new TreeSet<Produit>(new ProduitComparator());

    /**
     * Produits de type DISPOSITIFS MEDICAUX.
     */
    @OneToMany(mappedBy = "detailProduit", cascade = CascadeType.ALL, orphanRemoval = true)
    @Where(clause = "type='DISPOSITIF_MEDICAL'")
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = ProduitComparator.class)
    private SortedSet<Produit> dispositifsMedicaux = new TreeSet<Produit>(new ProduitComparator());

    /**
     * Produits de type PRODUITS THERAPEUTIQUES.
     */
    @OneToMany(mappedBy = "detailProduit", cascade = CascadeType.ALL, orphanRemoval = true)
    @Where(clause = "type='PRODUIT_THERAPEUTIQUE'")
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = ProduitComparator.class)
    private SortedSet<Produit> produitsTherapeutiques = new TreeSet<Produit>(new ProduitComparator());

    /**
     * Produits de type PREPARATION.
     */
    @OneToMany(mappedBy = "detailProduit", cascade = CascadeType.ALL, orphanRemoval = true)
    @Where(clause = "type='PREPARATION'")
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = ProduitComparator.class)
    private SortedSet<Produit> preparations = new TreeSet<Produit>(new ProduitComparator());

    /**
     * Produits de tous les types.
     */
    @OneToMany(mappedBy = "detailProduit", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = ProduitComparator.class)
    private SortedSet<Produit> produits = new TreeSet<Produit>(new ProduitComparator());

    /**
     * Liste des modifications du détail.
     */
    @OneToMany(mappedBy = "detailProduit", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private SortedSet<DetailProduitSuivi> modifs = new TreeSet<DetailProduitSuivi>(new SuiviComparator());

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
     * Getter sur modifs.
     * @return Retourne le modifs.
     */
    @Override
    public SortedSet<DetailProduitSuivi> getModifs() {
        return this.modifs;
    }

    /**
     * Getter sur medicaments.
     * @return Retourne le medicaments.
     */
    public SortedSet<Produit> getMedicaments() {
        return this.medicaments;
    }

    /**
     * Getter sur dispositifsMedicaux.
     * @return Retourne le dispositifsMedicaux.
     */
    public SortedSet<Produit> getDispositifsMedicaux() {
        return this.dispositifsMedicaux;
    }

    /**
     * Getter sur produitsTherapeutiques.
     * @return Retourne le produitsTherapeutiques.
     */
    public SortedSet<Produit> getProduitsTherapeutiques() {
        return this.produitsTherapeutiques;
    }

    /**
     * Setter pour medicaments.
     * @param medicaments le medicaments à écrire.
     */
    public void setMedicaments(final SortedSet<Produit> medicaments) {
        this.medicaments = medicaments;
    }

    /**
     * Setter pour dispositifsMedicaux.
     * @param dispositifsMedicaux le dispositifsMedicaux à écrire.
     */
    public void setDispositifsMedicaux(final SortedSet<Produit> dispositifsMedicaux) {
        this.dispositifsMedicaux = dispositifsMedicaux;
    }

    /**
     * Setter pour produitsTherapeutiques.
     * @param produitsTherapeutiques le produitsTherapeutiques à écrire.
     */
    public void setProduitsTherapeutiques(final SortedSet<Produit> produitsTherapeutiques) {
        this.produitsTherapeutiques = produitsTherapeutiques;
    }

    /**
     * Setter pour modifs.
     * @param modifs le modifs à écrire.
     */
    public void setModifs(final SortedSet<DetailProduitSuivi> modifs) {
        this.modifs = modifs;
    }

    /***/
    public SortedSet<Produit> getProduits() {
        return this.produits;
    }
    /***/
    public void setProduits(final SortedSet<Produit> p) {
        this.produits = p;
    }

    /***/
    public SortedSet<Produit> getPreparations() {
        return this.preparations;
    }
    /***/
    public void setPreparations(final SortedSet<Produit> preparations) {
        this.preparations = preparations;
    }

}
