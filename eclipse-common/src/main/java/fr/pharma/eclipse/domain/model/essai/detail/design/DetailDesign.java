package fr.pharma.eclipse.domain.model.essai.detail.design;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import fr.pharma.eclipse.comparator.design.DesignableComparator;
import fr.pharma.eclipse.comparator.suivi.SuiviComparator;
import fr.pharma.eclipse.domain.enums.design.TypeDesign;
import fr.pharma.eclipse.domain.model.common.BeanObjectSuivi;
import fr.pharma.eclipse.domain.model.design.Bras;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.suivi.essai.detail.DetailDesignSuivi;

/**
 * Description de la classe.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_detail_design")
public class DetailDesign
    extends BeanObjectSuivi
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 5295769119898839652L;

    /**
     * Essai auquel est rattaché le détail.
     */
    @OneToOne()
    @JoinColumn(name = "id_essai")
    private Essai essai;

    /**
     * Type de design.
     */
    @Column(name = "typeDesign")
    @Enumerated(EnumType.STRING)
    private TypeDesign type;

    /**
     * Liste des bras du design.
     */
    @OneToMany(mappedBy = "detailDesign", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = DesignableComparator.class)
    private SortedSet<Bras> bras = new TreeSet<Bras>(new DesignableComparator());

    /**
     * Liste des modifications du détail.
     */
    @OneToMany(mappedBy = "detailDesign", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private SortedSet<DetailDesignSuivi> modifs =
        new TreeSet<DetailDesignSuivi>(new SuiviComparator());

    /**
     * Getter sur bras.
     * @return Retourne le bras.
     */
    public SortedSet<Bras> getBras()
    {
        return this.bras;
    }

    /**
     * Setter pour bras.
     * @param bras le bras à écrire.
     */
    public void setBras(final SortedSet<Bras> bras)
    {
        this.bras = bras;
    }

    /**
     * Getter sur modifs.
     * @return Retourne le modifs.
     */
    @Override
    public SortedSet<DetailDesignSuivi> getModifs()
    {
        return this.modifs;
    }

    /**
     * Setter pour modifs.
     * @param modifs le modifs à écrire.
     */
    public void setModifs(final SortedSet<DetailDesignSuivi> modifs)
    {
        this.modifs = modifs;
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

    /**
     * Getter pour type.
     * @return Le type
     */
    public TypeDesign getType()
    {
        return this.type;
    }

    /**
     * Setter pour type.
     * @param type Le type à écrire.
     */
    public void setType(final TypeDesign type)
    {
        this.type = type;
    }
}
