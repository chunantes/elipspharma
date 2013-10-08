package fr.pharma.eclipse.domain.criteria.sigrec.essai;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;
import fr.pharma.eclipse.domain.model.sigrec.acteur.PromoteurSigrec;

/**
 * Critère de recherche sur Essai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EssaiSigrecSearchCriteria extends AbstractSearchCriteria {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3409282531875035453L;

    /**
     * Nom.
     */
    private String nom;

    /**
     * Numéro SIGREC.
     */
    private String numSigrec;

    /**
     * Promoteur.
     */
    private PromoteurSigrec promoteur;

    /**
     * Getter sur nom.
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
        this.setNumSigrec(null);
        this.setPromoteur(null);
    }

    /**
     * Getter sur promoteur.
     * @return Retourne le promoteur.
     */
    public PromoteurSigrec getPromoteur() {
        return this.promoteur;
    }

    /**
     * Setter pour promoteur.
     * @param promoteur le promoteur à écrire.
     */
    public void setPromoteur(final PromoteurSigrec promoteur) {
        this.promoteur = promoteur;
    }

    /**
     * Getter sur numSigrec.
     * @return Retourne le numSigrec.
     */
    public String getNumSigrec() {
        return this.numSigrec;
    }

    /**
     * Setter pour numSigrec.
     * @param numSigrec le numSigrec à écrire.
     */
    public void setNumSigrec(final String numSigrec) {
        this.numSigrec = numSigrec;
    }

}
