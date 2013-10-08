package fr.pharma.eclipse.domain.criteria.stockage;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.localisation.Etablissement;

/**
 * Critère de recherche sur Pharmacie.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PharmacieSearchCriteria extends AbstractSearchCriteria {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -1432850647627079196L;

    /**
     * Nom.
     */
    private String nom;

    /**
     * Etablissement.
     */
    private Etablissement etablissement;

    /**
     * Essai.
     */
    private Essai essai;

    /**
     * Boolean indiquant la prise en compte des acls.
     */
    private Boolean withAcl = true;

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
     * Getter sur essai.
     * @return Retourne le essai.
     */
    public Essai getEssai() {
        return this.essai;
    }

    /**
     * Setter pour essai.
     * @param essai le essai à écrire.
     */
    public void setEssai(final Essai essai) {
        this.essai = essai;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.setEtablissement(null);
        this.setEssai(null);
        this.setNom(null);
    }

    /**
     * Getter pour withAcl.
     * @return Le withAcl
     */
    public Boolean getWithAcl() {
        return this.withAcl;
    }

    /**
     * Setter pour withAcl.
     * @param withAcl Le withAcl à écrire.
     */
    public void setWithAcl(final Boolean withAcl) {
        this.withAcl = withAcl;
    }

}
