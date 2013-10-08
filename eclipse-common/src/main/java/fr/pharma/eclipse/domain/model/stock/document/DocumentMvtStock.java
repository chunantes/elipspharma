package fr.pharma.eclipse.domain.model.stock.document;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentStock;
import fr.pharma.eclipse.domain.model.stock.DocumentStock;
import fr.pharma.eclipse.domain.model.stock.MvtStock;

/**
 * Bean métier représentant un document lié aux mouvements de stock.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "mvtstock_document")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class DocumentMvtStock extends DocumentStock {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1998118716231611921L;

    /**
     * Objet auquel est attaché le document.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mvtstock", nullable = false)
    private MvtStock mvtStock;

    /**
     * Type de document.
     */
    @Column(name = "type", insertable = false, updatable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeDocumentStock type;

    /**
     * Getter pour mvtStock.
     * @return Le mvtStock
     */
    public MvtStock getMvtStock() {
        return this.mvtStock;
    }

    /**
     * Setter pour mvtStock.
     * @param mvtStock Le mvtStock à écrire.
     */
    public void setMvtStock(final MvtStock mvtStock) {
        this.mvtStock = mvtStock;
    }

    /**
     * Getter pour type.
     * @return Le type
     */
    @Override
    public TypeDocumentStock getType() {
        return this.type;
    }

    /**
     * Setter pour type.
     * @param type Le type à écrire.
     */
    @Override
    public void setType(final TypeDocumentStock type) {
        this.type = type;
    }

}
