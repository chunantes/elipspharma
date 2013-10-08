package fr.pharma.eclipse.domain.criteria.localisation;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;
import fr.pharma.eclipse.domain.model.localisation.Etablissement;

/**
 * Critère de recherche sur Pole.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PoleSearchCriteria extends AbstractSearchCriteria {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2243992861729878437L;

    /**
     * Nom.
     */
    private String nom;

    /**
     * Etablissement.
     */
    private Etablissement etablissement;

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
     * Getter sur etablissement.
     * @return Retourne le etablissement.
     */
    public Etablissement getEtablissement() {
        return this.etablissement;
    }

    /**
     * Setter pour etablissement.
     * @param etablissement le etablissement à écrire.
     */
    public void setEtablissement(final Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.setEtablissement(null);
        this.setNom(null);
    }

}
