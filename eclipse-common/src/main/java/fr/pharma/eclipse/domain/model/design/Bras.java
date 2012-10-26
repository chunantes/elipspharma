package fr.pharma.eclipse.domain.model.design;

import java.util.Collection;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.NullPredicate;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import fr.pharma.eclipse.comparator.design.DesignableComparator;
import fr.pharma.eclipse.comparator.design.TempsPrescriptionComparator;
import fr.pharma.eclipse.domain.enums.TypeDesignable;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;
import fr.pharma.eclipse.domain.model.essai.detail.design.DetailDesign;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe métier représentant un bras de traitement.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "bras")
public class Bras
    extends BeanObject
    implements Designable
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 1266713226254678139L;

    /**
     * Nom du bras.
     */
    @Column(name = "nom")
    private String nom;

    /**
     * Description.
     */
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    /**
     * Type designable.
     */
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeDesignable type;

    /**
     * Detail Design.
     */
    @ManyToOne
    @JoinColumn(name = "id_detail_design", nullable = false)
    @Index(name = "idx_detail_design_bras")
    private DetailDesign detailDesign;

    /**
     * Bras parent.
     */
    @ManyToOne(cascade =
    {CascadeType.REFRESH, CascadeType.MERGE })
    @JoinColumn(name = "id_bras_parent")
    @Index(name = "idx_brase_parent")
    private Bras parent;

    /**
     * Stockage enfants.
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = DesignableComparator.class)
    private SortedSet<Bras> sousBras = new TreeSet<Bras>(new DesignableComparator());

    /**
     * Sequences.
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = DesignableComparator.class)
    private SortedSet<Sequence> sequences = new TreeSet<Sequence>(new DesignableComparator());

    /**
     * Getter sur description.
     * @return Retourne le description.
     */
    public String getDescription()
    {
        return this.description;
    }

    /**
     * Setter pour description.
     * @param description le description à écrire.
     */
    public void setDescription(final String description)
    {
        this.description = description;
    }

    /**
     * Getter sur parent.
     * @return Retourne le parent.
     */
    public Bras getParent()
    {
        return this.parent;
    }

    /**
     * Setter pour parent.
     * @param parent le parent à écrire.
     */
    public void setParent(final Bras parent)
    {
        this.parent = parent;
    }

    /**
     * Getter sur sousBras.
     * @return Retourne le sousBras.
     */
    public SortedSet<Bras> getSousBras()
    {
        return this.sousBras;
    }

    /**
     * Setter pour sousBras.
     * @param sousBras le sousBras à écrire.
     */
    public void setSousBras(final SortedSet<Bras> sousBras)
    {
        this.sousBras = sousBras;
    }

    /**
     * Getter sur sequences.
     * @return Retourne le sequences.
     */
    public SortedSet<Sequence> getSequences()
    {
        return this.sequences;
    }

    /**
     * Setter pour sequences.
     * @param sequences le sequences à écrire.
     */
    public void setSequences(final SortedSet<Sequence> sequences)
    {
        this.sequences = sequences;
    }

    /**
     * Getter sur detailDesign.
     * @return Retourne le detailDesign.
     */
    public DetailDesign getDetailDesign()
    {
        return this.detailDesign;
    }

    /**
     * Setter pour detailDesign.
     * @param detailDesign le detailDesign à écrire.
     */
    public void setDetailDesign(final DetailDesign detailDesign)
    {
        this.detailDesign = detailDesign;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLibelleProduit()
    {
        return "";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNom()
    {
        return this.nom;
    }

    /**
     * Setter pour nom.
     * @param nom le nom à écrire.
     */
    public void setNom(final String nom)
    {
        this.nom = nom;
    }

    /**
     * Getter sur type.
     * @return Retourne le type.
     */
    public TypeDesignable getType()
    {
        return this.type;
    }

    /**
     * Setter pour type.
     * @param type le type à écrire.
     */
    public void setType(final TypeDesignable type)
    {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SortedSet<Designable> getEnfants()
    {
        final SortedSet<Designable> enfants = new TreeSet<Designable>(new DesignableComparator());
        enfants.addAll(this.getSequences());
        enfants.addAll(this.getSousBras());
        return enfants;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public TempsPrescription getDebut()
    {
        final Collection coll = CollectionUtils.selectRejected(this.getEnfants(),
                                                               NullPredicate.getInstance());
        CollectionUtils.transform(coll,
                                  new Transformer() {

                                      @Override
                                      public Object transform(final Object input)
                                      {
                                          return ((Designable) input).getDebut();
                                      }
                                  });
        if (!coll.isEmpty())
        {
            final Object r = Collections.min(coll,
                                             new TempsPrescriptionComparator());
            return (TempsPrescription) r;
        }
        return null;
    }
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public TempsPrescription getFin()
    {
        final Collection coll = CollectionUtils.selectRejected(this.getEnfants(),
                                                               NullPredicate.getInstance());
        CollectionUtils.transform(coll,
                                  new Transformer() {

                                      @Override
                                      public Object transform(final Object input)
                                      {
                                          return ((Designable) input).getFin();
                                      }
                                  });
        if (!coll.isEmpty())
        {
            final Object r = Collections.max(coll,
                                             new TempsPrescriptionComparator());
            return (TempsPrescription) r;
        }
        return null;
    }

    /**
     * Méthode en charge de retouner le nom complet d'un stockage.
     * @return Nom complet du stockage (inclusion du nom du parent).
     */
    public String getNomComplet()
    {
        String result = this.getNom();

        Bras brasParent = this.getParent();
        while (brasParent != null)
        {
            result = brasParent.getNom()
                     + EclipseConstants.DASH
                     + result;
            brasParent = brasParent.getParent();
        }
        return result;
    }

    @Override
    public String toString()
    {
        return "[nomComplet: "
               + this.getNomComplet()
               + "]";
    }
}
