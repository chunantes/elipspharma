package fr.pharma.eclipse.domain.criteria.localisation;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;
import fr.pharma.eclipse.domain.model.localisation.Pole;

/**
 * Critère de recherche sur Service.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ServiceSearchCriteria extends AbstractSearchCriteria {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4914558665659269297L;

    /**
     * Nom.
     */
    private String nom;

    /**
     * Pole.
     */
    private Pole pole;

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
     * Getter sur pole.
     * @return Retourne le pole.
     */
    public Pole getPole() {
        return this.pole;
    }

    /**
     * Setter pour pole.
     * @param pole le pole à écrire.
     */
    public void setPole(final Pole pole) {
        this.pole = pole;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.setNom(null);
        this.setPole(null);
    }

}
