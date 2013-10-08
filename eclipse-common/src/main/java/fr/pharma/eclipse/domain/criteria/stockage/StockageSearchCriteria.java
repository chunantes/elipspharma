package fr.pharma.eclipse.domain.criteria.stockage;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;

/**
 * Critère de recherche sur Stockage.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class StockageSearchCriteria extends AbstractSearchCriteria {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 5695521314967092551L;

    /**
     * Nom.
     */
    private String nom;

    /**
     * stockage parent
     */
    private Stockage parent;

    /**
     * indique si le stockage a un parent
     */
    private Boolean hasParent = Boolean.FALSE;

    /**
     * pharmacie du stockage
     */
    private Pharmacie pharmacie;

    /**
     * Getter pour nom.
     * @return Retourne le nom.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Setter pour nom.
     * @param nom le nom à écrire.
     */
    public void setNom(final String nom) {
        this.nom = nom;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.setNom(null);
    }

    /**
     * Getter pour parent.
     * @return Le parent
     */
    public Stockage getParent() {
        return this.parent;
    }

    /**
     * Setter pour parent.
     * @param parent Le parent à écrire.
     */
    public void setParent(final Stockage parent) {
        if (parent != null) {
            this.setHasParent(Boolean.TRUE);
        } else {
            this.setHasParent(Boolean.FALSE);
        }
        this.parent = parent;
    }
    /**
     * Getter pour pharmacie.
     * @return Le pharmacie
     */
    public Pharmacie getPharmacie() {
        return this.pharmacie;
    }

    /**
     * Setter pour pharmacie.
     * @param pharmacie Le pharmacie à écrire.
     */
    public void setPharmacie(final Pharmacie pharmacie) {
        this.pharmacie = pharmacie;
    }

    /**
     * Getter pour hasParent.
     * @return Le hasParent
     */
    public Boolean getHasParent() {
        return this.hasParent;
    }

    /**
     * Setter pour hasParent.
     * @param hasParent Le hasParent à écrire.
     */
    public void setHasParent(final Boolean hasParent) {
        this.hasParent = hasParent;
    }

}
