package fr.pharma.eclipse.domain.model.produit.detail;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.enums.produit.TypeDetailStockage;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.common.BeanWithNom;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;

/**
 * Classe métier représentant le lien entre un produit et un stockage.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "produit_detail_stockage")
public class DetailStockage
    extends BeanObject
    implements BeanWithNom
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -8298096167732097967L;

    /**
     * Pharmacie.
     */
    @ManyToOne
    @JoinColumn(name = "id_pharmacie")
    @Index(name = "idx_produit_pharmacie")
    private Pharmacie pharmacie;

    /**
     * Stockage.
     */
    @ManyToOne
    @JoinColumn(name = "id_stockage")
    @Index(name = "idx_produit_stockage")
    private Stockage stockage;

    /**
     * Identifiant de stockage.
     */
    @Column(name = "identifiantStockage")
    private String identifiantStockage;

    /**
     * Objet DetailLogistique.
     */
    @ManyToOne()
    @JoinColumn(name = "id_detail_logistique")
    @Index(name = "idx_detail_stockage_detail_logistique")
    private DetailLogistique detailLogistique;

    /**
     * Type de detail stockage (stock ou retour).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TypeDetailStockage type;

    /**
     * Getter sur pharmacie.
     * @return Retourne le pharmacie.
     */
    public Pharmacie getPharmacie()
    {
        return this.pharmacie;
    }

    /**
     * Setter pour pharmacie.
     * @param pharmacie le pharmacie à écrire.
     */
    public void setPharmacie(final Pharmacie pharmacie)
    {
        this.pharmacie = pharmacie;
    }

    /**
     * Getter sur stockage.
     * @return Retourne le stockage.
     */
    public Stockage getStockage()
    {
        return this.stockage;
    }

    /**
     * Setter pour stockage.
     * @param stockage le stockage à écrire.
     */
    public void setStockage(final Stockage stockage)
    {
        this.stockage = stockage;
    }

    /**
     * Getter sur identifiantStockage.
     * @return Retourne le identifiantStockage.
     */
    public String getIdentifiantStockage()
    {
        return this.identifiantStockage;
    }

    /**
     * Setter pour identifiantStockage.
     * @param identifiantStockage le identifiantStockage à écrire.
     */
    public void setIdentifiantStockage(final String identifiantStockage)
    {
        this.identifiantStockage = identifiantStockage;
    }

    /**
     * Getter sur detailLogistique.
     * @return Retourne le detailLogistique.
     */
    public DetailLogistique getDetailLogistique()
    {
        return this.detailLogistique;
    }

    /**
     * Setter pour detailLogistique.
     * @param detailLogistique le detailLogistique à écrire.
     */
    public void setDetailLogistique(final DetailLogistique detailLogistique)
    {
        this.detailLogistique = detailLogistique;
    }

    /**
     * Getter sur type.
     * @return Retourne le type.
     */
    public TypeDetailStockage getType()
    {
        return this.type;
    }

    /**
     * Setter pour type.
     * @param type le type à écrire.
     */
    public void setType(final TypeDetailStockage type)
    {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNom()
    {
        return this.getStockage().getNomComplet()
               + " - "
               + this.getIdentifiantStockage();
    }
}
