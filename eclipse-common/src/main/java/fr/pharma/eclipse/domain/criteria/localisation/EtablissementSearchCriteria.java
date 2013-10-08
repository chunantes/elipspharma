package fr.pharma.eclipse.domain.criteria.localisation;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;

/**
 * Critère de recherche sur Etablissement.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EtablissementSearchCriteria extends AbstractSearchCriteria {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 8773802258642846199L;

    /**
     * Nom.
     */
    private String nom;

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

}
