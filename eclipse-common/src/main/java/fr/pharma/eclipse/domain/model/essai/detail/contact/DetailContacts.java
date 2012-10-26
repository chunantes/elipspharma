package fr.pharma.eclipse.domain.model.essai.detail.contact;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import fr.pharma.eclipse.comparator.habilitation.HabilitationComparator;
import fr.pharma.eclipse.comparator.suivi.SuiviComparator;
import fr.pharma.eclipse.domain.model.common.BeanObjectSuivi;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.domain.model.suivi.essai.detail.DetailContactsSuivi;

/**
 * Classe métier représentant les informations des contacts d'un essai clinique.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_detail_contacts")
public class DetailContacts
    extends BeanObjectSuivi
    implements Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -8434709478245638882L;

    /**
     * Essai auquel est rattaché le détail.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_essai")
    private Essai essai;

    /**
     * Liste globale des habilitations.
     */
    @OneToMany(mappedBy = "detailContacts", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = HabilitationComparator.class)
    private SortedSet<Habilitation> habilitations =
        new TreeSet<Habilitation>(new HabilitationComparator());

    /**
     * Liste des modifications du détail.
     */
    @OneToMany(mappedBy = "detailContacts", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private SortedSet<DetailContactsSuivi> modifs =
        new TreeSet<DetailContactsSuivi>(new SuiviComparator());

    /**
     * {@inheritDoc}
     */
    @Override
    public SortedSet<DetailContactsSuivi> getModifs()
    {
        return this.modifs;
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
     * Setter pour modifs.
     * @param modifs le modifs à écrire.
     */
    public void setModifs(final SortedSet<DetailContactsSuivi> modifs)
    {
        this.modifs = modifs;
    }

    /**
     * Getter sur habilitations.
     * @return Retourne le habilitations.
     */
    public SortedSet<Habilitation> getHabilitations()
    {
        return this.habilitations;
    }

    /**
     * Setter pour habilitations.
     * @param habilitations le habilitations à écrire.
     */
    public void setHabilitations(final SortedSet<Habilitation> habilitations)
    {
        this.habilitations = habilitations;
    }

}
