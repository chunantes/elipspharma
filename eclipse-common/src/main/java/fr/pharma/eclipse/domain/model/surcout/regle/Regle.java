package fr.pharma.eclipse.domain.model.surcout.regle;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.domain.enums.surcout.ModeCalcul;
import fr.pharma.eclipse.domain.enums.surcout.PerimetreCout;
import fr.pharma.eclipse.domain.enums.surcout.TypeCout;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.common.Clonable;
import fr.pharma.eclipse.domain.model.surcout.Categorie;
import fr.pharma.eclipse.domain.model.surcout.Item;
import fr.pharma.eclipse.domain.model.surcout.Theme;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Interface décrivant le comportement des règles de calculs pour les surcouts.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "regle_surcout")
public class Regle extends BeanObject implements Serializable, Clonable<Regle> {
    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 5569345150917705621L;

    /**
     * Thème.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_theme")
    @Index(name = "idx_theme_regle")
    private Theme theme;

    /**
     * Catégorie.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categorie")
    @Index(name = "idx_categorie_regle")
    private Categorie categorie;

    /**
     * Item auquel la règle est rattachée.
     */
    @ManyToMany(targetEntity = Item.class, mappedBy = "regles")
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = BeanWithNomComparator.class)
    private SortedSet<Item> items = new TreeSet<Item>(new BeanWithNomComparator());

    /**
     * Type de règles.
     */
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeCout type;

    /**
     * Mode de calcul (cout variable).
     */
    @Column(name = "mode")
    @Enumerated(EnumType.STRING)
    private ModeCalcul mode;

    /**
     * Perimetre.
     */
    @Column(name = "perimetre")
    @Enumerated(EnumType.STRING)
    private PerimetreCout perimetre;

    /**
     * La valeur lors de la première année (cout fixe).
     */
    @Column(name = "premiereAnnee")
    private BigDecimal premiereAnnee;

    /**
     * La valeur lors des années suivantes (cout fixe).
     */
    @Column(name = "anneesSuivantes")
    private BigDecimal anneesSuivantes;

    /**
     * Limite basse.
     */
    @Column(name = "min")
    private Integer min;

    /**
     * limite haute.
     */
    @Column(name = "max")
    private Integer max;

    /**
     * Montant.
     */
    @Column(name = "montant")
    private BigDecimal montant;

    /**
     * Getter sur type.
     * @return Retourne le type.
     */
    public TypeCout getType() {
        return this.type;
    }

    /**
     * Setter pour type.
     * @param type le type à écrire.
     */
    public void setType(final TypeCout type) {
        this.type = type;
    }

    /**
     * Getter sur items.
     * @return Retourne le items.
     */
    public SortedSet<Item> getItems() {
        return this.items;
    }

    /**
     * Setter pour items.
     * @param items le items à écrire.
     */
    public void setItems(final SortedSet<Item> items) {
        this.items = items;
    }

    /**
     * Getter sur perimetre.
     * @return Retourne le perimetre.
     */
    public PerimetreCout getPerimetre() {
        return this.perimetre;
    }

    /**
     * Setter pour perimetre.
     * @param perimetre le perimetre à écrire.
     */
    public void setPerimetre(final PerimetreCout perimetre) {
        this.perimetre = perimetre;
    }

    /**
     * Getter sur premiereAnnee.
     * @return Retourne le premiereAnnee.
     */
    public BigDecimal getPremiereAnnee() {
        return this.premiereAnnee;
    }

    /**
     * Getter sur anneesSuivantes.
     * @return Retourne le anneesSuivantes.
     */
    public BigDecimal getAnneesSuivantes() {
        return this.anneesSuivantes;
    }

    /**
     * Setter pour premiereAnnee.
     * @param premiereAnnee le premiereAnnee à écrire.
     */
    public void setPremiereAnnee(final BigDecimal premiereAnnee) {
        this.premiereAnnee = premiereAnnee;
    }

    /**
     * Setter pour anneesSuivantes.
     * @param anneesSuivantes le anneesSuivantes à écrire.
     */
    public void setAnneesSuivantes(final BigDecimal anneesSuivantes) {
        this.anneesSuivantes = anneesSuivantes;
    }

    /**
     * Getter sur min.
     * @return Retourne le min.
     */
    public Integer getMin() {
        return this.min;
    }

    /**
     * Getter sur max.
     * @return Retourne le max.
     */
    public Integer getMax() {
        return this.max;
    }

    /**
     * Setter pour min.
     * @param min le min à écrire.
     */
    public void setMin(final Integer min) {
        this.min = min;
    }

    /**
     * Setter pour max.
     * @param max le max à écrire.
     */
    public void setMax(final Integer max) {
        this.max = max;
    }

    /**
     * Getter sur montant.
     * @return Retourne le montant.
     */
    public BigDecimal getMontant() {
        return this.montant;
    }

    /**
     * Setter pour montant.
     * @param montant le montant à écrire.
     */
    public void setMontant(final BigDecimal montant) {
        this.montant = montant;
    }

    /**
     * Getter sur mode.
     * @return Retourne le mode.
     */
    public ModeCalcul getMode() {
        return this.mode;
    }

    /**
     * Setter pour mode.
     * @param mode le mode à écrire.
     */
    public void setMode(final ModeCalcul mode) {
        this.mode = mode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuffer buff = new StringBuffer();
        buff.append("Cout ").append(this.getType().getLibelle()).append(EclipseConstants.SPACE).append(EclipseConstants.DASH).append(EclipseConstants.SPACE)
                .append(this.getPerimetre().getLibelle()).append(EclipseConstants.SPACE).append(EclipseConstants.DASH).append(EclipseConstants.SPACE);

        if (this.type.equals(TypeCout.FIXE)) {
            buff.append("1ere année : ").append(this.getPremiereAnnee()).append(EclipseConstants.EURO).append("/").append(" Autres : ").append(this.getAnneesSuivantes())
                    .append(EclipseConstants.EURO);
        } else if (this.type.equals(TypeCout.VARIABLE)) {
            buff.append(this.getMode()).append(EclipseConstants.SPACE).append(EclipseConstants.DASH).append(EclipseConstants.SPACE);

            if (this.getMin() == null) {
                buff.append("0");
            } else {
                buff.append(this.getMin());
            }
            buff.append("/");

            if (this.getMax() == null) {
                buff.append("-");
            } else {
                buff.append(this.getMax());
            }
            buff.append(EclipseConstants.DASH).append(this.getMontant()).append(EclipseConstants.EURO);

        }

        return buff.toString();
    }

    /**
     * Getter sur theme.
     * @return Retourne le theme.
     */
    public Theme getTheme() {
        return this.theme;
    }

    /**
     * Getter sur categorie.
     * @return Retourne le categorie.
     */
    public Categorie getCategorie() {
        return this.categorie;
    }

    /**
     * Setter pour theme.
     * @param theme le theme à écrire.
     */
    public void setTheme(final Theme theme) {
        this.theme = theme;
    }

    /**
     * Setter pour categorie.
     * @param categorie le categorie à écrire.
     */
    public void setCategorie(final Categorie categorie) {
        this.categorie = categorie;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Regle cloneMe() {
        final Regle regle = new Regle();
        regle.setAnneesSuivantes(this.getAnneesSuivantes());
        regle.setPerimetre(this.getPerimetre());
        regle.setPremiereAnnee(this.getPremiereAnnee());
        regle.setMax(this.getMax());
        regle.setMin(this.getMin());
        regle.setMontant(this.getMontant());
        regle.setMode(this.getMode());
        regle.setType(this.getType());
        return regle;
    }
}
