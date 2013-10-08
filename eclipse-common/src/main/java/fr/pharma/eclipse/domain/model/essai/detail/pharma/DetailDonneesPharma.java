package fr.pharma.eclipse.domain.model.essai.detail.pharma;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.comparator.suivi.SuiviComparator;
import fr.pharma.eclipse.domain.model.common.BeanObjectSuivi;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.embedded.InfosComplementaires;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.embedded.InfosDispensations;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.embedded.InfosGenerales;
import fr.pharma.eclipse.domain.model.localisation.Etablissement;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.suivi.essai.detail.DetailDonneesPharmaSuivi;

/**
 * Classe mÃ©tier reprÃ©sentant les donnees pharma d'un essai clinique.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_detail_pharma")
public class DetailDonneesPharma extends BeanObjectSuivi implements Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -3445795792052149996L;

    /**
     * Essai auquel est rattachÃ© le dÃ©tail.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_essai")
    private Essai essai;

    /**
     * Informations gÃ©nÃ©rales sur les donnÃ©es pharma.
     */
    @Embedded
    private final InfosGenerales infosGenerales = new InfosGenerales();

    /**
     * Informations complementaires sur les donnees pharma.
     */
    @Embedded
    private final InfosComplementaires infosComplementaires = new InfosComplementaires();

    /**
     * Informations sur les dispensations sur les donnees pharma.
     */
    @Embedded
    private final InfosDispensations infosDispensations = new InfosDispensations();

    /**
     * Formation spÃ©cifique Ã  l'Ã©tude.
     */
    @Column(name = "formationSpecifique")
    private Boolean formationSpecifique;

    /**
     * Pharmacies.
     */
    @ManyToMany(targetEntity = Pharmacie.class)
    @JoinTable(name = "essai_detail_pharma_pharmacie", joinColumns = @JoinColumn(name = "id_detail_pharma"), inverseJoinColumns = @JoinColumn(name = "id_pharmacie"))
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = BeanWithNomComparator.class)
    private SortedSet<Pharmacie> pharmacies = new TreeSet<Pharmacie>(new BeanWithNomComparator());

    /**
     * Etablissements.
     */
    @ManyToMany(targetEntity = Etablissement.class)
    @JoinTable(name = "essai_detail_pharma_etablissement", joinColumns = @JoinColumn(name = "id_detail_pharma"), inverseJoinColumns = @JoinColumn(name = "id_etablissement"))
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = BeanWithNomComparator.class)
    private final SortedSet<Etablissement> etablissements = new TreeSet<Etablissement>(new BeanWithNomComparator());

    /**
     * Liste des modifications du dÃ©tail.
     */
    @OneToMany(mappedBy = "detailDonneesPharma", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private final SortedSet<DetailDonneesPharmaSuivi> modifs = new TreeSet<DetailDonneesPharmaSuivi>(new SuiviComparator());

    /**
     * Getter sur modifs.
     * @return Retourne le modifs.
     */
    @Override
    public SortedSet<DetailDonneesPharmaSuivi> getModifs() {
        return this.modifs;
    }

    /**
     * Getter sur essai.
     * @return Retourne le essai.
     */
    public Essai getEssai() {
        return this.essai;
    }

    /**
     * Setter pour essai.
     * @param essai le essai Ã  Ã©crire.
     */
    public void setEssai(final Essai essai) {
        this.essai = essai;
    }

    /**
     * Getter sur infosGenerales.
     * @return Retourne le infosGenerales.
     */
    public InfosGenerales getInfosGenerales() {
        return this.infosGenerales;
    }

    /**
     * Getter sur formationSpecifique.
     * @return Retourne le formationSpecifique.
     */
    public Boolean getFormationSpecifique() {
        return this.formationSpecifique;
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
     * @param pharmacies Le pharmacies Ã  Ã©crire.
     */
    public void setPharmacies(final SortedSet<Pharmacie> pharmacies) {
        this.pharmacies = pharmacies;
    }

    /**
     * Setter pour formationSpecifique.
     * @param formationSpecifique le formationSpecifique Ã  Ã©crire.
     */
    public void setFormationSpecifique(final Boolean formationSpecifique) {
        this.formationSpecifique = formationSpecifique;
    }

    /**
     * Getter sur infosComplementaires.
     * @return Retourne le infosComplementaires.
     */
    public InfosComplementaires getInfosComplementaires() {
        return this.infosComplementaires;
    }

    /**
     * Getter sur infosDispensations.
     * @return Retourne le infosDispensations.
     */
    public InfosDispensations getInfosDispensations() {
        return this.infosDispensations;
    }

    /**
     * Getter pour etablissements.
     * @return Le etablissements
     */
    public SortedSet<Etablissement> getEtablissements() {
        return this.etablissements;
    }

}
