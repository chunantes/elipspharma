package fr.pharma.eclipse.domain.model.essai.detail.faisabilite;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import fr.pharma.eclipse.comparator.suivi.SuiviComparator;
import fr.pharma.eclipse.domain.model.common.BeanObjectSuivi;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.faisabilite.embedded.InfosConclusionFaisabilite;
import fr.pharma.eclipse.domain.model.essai.detail.faisabilite.embedded.InfosEtudeFaisabilite;
import fr.pharma.eclipse.domain.model.suivi.essai.detail.DetailFaisabiliteSuivi;

/**
 * Classe métier représentant les informations de détail de faisabilité d'un essai clinique.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_detail_faisabilite")
public class DetailFaisabilite
    extends BeanObjectSuivi
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4782708134827644212L;

    /**
     * Essai auquel est rattaché le détail.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_essai")
    private Essai essai;

    /**
     * Informations de l'étude de faisabilité.
     */
    @Embedded
    private InfosEtudeFaisabilite infosEtude = new InfosEtudeFaisabilite();

    /**
     * Informations de la conclusion de l'étude de faisabilité.
     */
    @Embedded
    private InfosConclusionFaisabilite infosConclusion = new InfosConclusionFaisabilite();

    /**
     * Liste des modifications du détail.
     */
    @OneToMany(mappedBy = "detailFaisabilite", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private SortedSet<DetailFaisabiliteSuivi> modifs =
        new TreeSet<DetailFaisabiliteSuivi>(new SuiviComparator());

    /**
     * Getter sur modifs.
     * @return Retourne le modifs.
     */
    @Override
    public SortedSet<DetailFaisabiliteSuivi> getModifs()
    {
        return this.modifs;
    }

    /**
     * Setter pour modifs.
     * @param modifs le modifs à écrire.
     */
    public void setModifs(final SortedSet<DetailFaisabiliteSuivi> modifs)
    {
        this.modifs = modifs;
    }

    /**
     * Getter sur infosEtude.
     * @return Retourne le infosEtude.
     */
    public InfosEtudeFaisabilite getInfosEtude()
    {
        return this.infosEtude;
    }

    /**
     * Setter pour infosEtude.
     * @param infosEtude le infosEtude à écrire.
     */
    public void setInfosEtude(final InfosEtudeFaisabilite infosEtude)
    {
        this.infosEtude = infosEtude;
    }

    /**
     * Getter sur infosConclusion.
     * @return Retourne le infosConclusion.
     */
    public InfosConclusionFaisabilite getInfosConclusion()
    {
        return this.infosConclusion;
    }

    /**
     * Setter pour infosConclusion.
     * @param infosConclusion le infosConclusion à écrire.
     */
    public void setInfosConclusion(final InfosConclusionFaisabilite infosConclusion)
    {
        this.infosConclusion = infosConclusion;
    }

    /**
     * Getter sur essai.
     * @return Retourne le essai.
     */
    public Essai getEssai()
    {
        return this.essai;
    }

    /**
     * Setter pour essai.
     * @param essai le essai à écrire.
     */
    public void setEssai(final Essai essai)
    {
        this.essai = essai;
    }

}
