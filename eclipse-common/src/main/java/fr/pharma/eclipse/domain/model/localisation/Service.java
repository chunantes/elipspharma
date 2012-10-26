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
import fr.pharma.eclipse.domain.model.acteur.ArcInvestigateur;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.common.BeanObjectSuivi;
import fr.pharma.eclipse.domain.model.common.BeanWithNom;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.suivi.localisation.ServiceSuivi;

/**
 * Classe métier représentant un service.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "service")
public class Service
    extends BeanObjectSuivi
    implements BeanWithNom
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3906675949088575992L;

    /**
     * Nom.
     */
    @Column(name = "nom")
    @NotNull
    @NotEmpty
    private String nom;

    /**
     * Pole.
     */
    @ManyToOne
    @JoinColumn(name = "id_pole")
    @Index(name = "idx_pole_service")
    private Pole pole;

    /**
     * Site.
     */
    @ManyToOne
    @JoinColumn(name = "id_site")
    @Index(name = "idx_site_service")
    private Site site;

    /**
     * Essais.
     */
    @ManyToMany(targetEntity = Essai.class, mappedBy = "services", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = BeanWithNomComparator.class)
    private SortedSet<Essai> essais = new TreeSet<Essai>(new BeanWithNomComparator());

    /**
     * Investigateurs.
     */
    @ManyToMany(targetEntity = Investigateur.class, mappedBy = "services", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = BeanWithNomComparator.class)
    private SortedSet<Investigateur> investigateurs =
        new TreeSet<Investigateur>(new BeanWithNomComparator());

    /**
     * ARC Investigateurs.
     */
    @ManyToMany(targetEntity = ArcInvestigateur.class, mappedBy = "services", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = BeanWithNomComparator.class)
    private SortedSet<ArcInvestigateur> arcInvestigateurs =
        new TreeSet<ArcInvestigateur>(new BeanWithNomComparator());

    /**
     * Liste des modifications du service.
     */
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private final SortedSet<ServiceSuivi> modifs =
        new TreeSet<ServiceSuivi>(new SuiviComparator());

    /**
     * Getter sur nom.
     * @return Retourne le nom.
     */
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
     * Getter sur pole.
     * @return Retourne le pole.
     */
    public Pole getPole()
    {
        return this.pole;
    }

    /**
     * Setter pour pole.
     * @param pole le pole à écrire.
     */
    public void setPole(final Pole pole)
    {
        this.pole = pole;
    }

    /**
     * Getter sur modifs.
     * @return Retourne le modifs.
     */
    @Override
    public SortedSet<ServiceSuivi> getModifs()
    {
        return this.modifs;
    }

    /**
     * Getter sur essais.
     * @return Retourne le essais.
     */
    public SortedSet<Essai> getEssais()
    {
        return this.essais;
    }

    /**
     * Setter pour essais.
     * @param essais le essais à écrire.
     */
    public void setEssais(final SortedSet<Essai> essais)
    {
        this.essais = essais;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder("[");
        builder.append("id: ").append(this.getId());
        builder.append(", nom: ").append(this.nom);
        builder.append(", selected: ").append(this.getSelected());
        return builder.append("]").toString();
    }

    /**
     * Getter sur investigateurs.
     * @return Retourne le investigateurs.
     */
    public SortedSet<Investigateur> getInvestigateurs()
    {
        return this.investigateurs;
    }

    /**
     * Setter pour investigateurs.
     * @param investigateurs le investigateurs à écrire.
     */
    public void setInvestigateurs(final SortedSet<Investigateur> investigateurs)
    {
        this.investigateurs = investigateurs;
    }

    /**
     * Getter pour arcInvestigateurs.
     * @return Le arcInvestigateurs
     */
    public SortedSet<ArcInvestigateur> getArcInvestigateurs()
    {
        return this.arcInvestigateurs;
    }

    /**
     * Setter pour arcInvestigateurs.
     * @param arcInvestigateurs Le arcInvestigateurs à écrire.
     */
    public void setArcInvestigateurs(final SortedSet<ArcInvestigateur> arcInvestigateurs)
    {
        this.arcInvestigateurs = arcInvestigateurs;
    }

    /**
     * Getter pour site.
     * @return Le site
     */
    public Site getSite()
    {
        return this.site;
    }

    /**
     * Setter pour site.
     * @param site Le site à écrire.
     */
    public void setSite(final Site site)
    {
        this.site = site;
    }

}
