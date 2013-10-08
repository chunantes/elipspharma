package fr.pharma.eclipse.domain.criteria.sigrec.acteur;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;
import fr.pharma.eclipse.domain.enums.TypePromoteur;

/**
 * Critère de recherche sur PromoteurSgirec.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PromoteurSigrecSearchCriteria extends AbstractSearchCriteria {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -2559127498480337753L;

    /**
     * Raison sociale.
     */
    private String raisonSociale;

    /**
     * Type de promoteur.
     */
    private TypePromoteur type;

    /**
     * Identifiant national.
     */
    private String identifiant;

    /**
     * Getter sur raisonSociale.
     * @return Retourne le raisonSociale.
     */
    public String getRaisonSociale() {
        return this.raisonSociale;
    }

    /**
     * Setter pour raisonSociale.
     * @param raisonSociale le raisonSociale à écrire.
     */
    public void setRaisonSociale(final String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    /**
     * Getter sur type.
     * @return Retourne le type.
     */
    public TypePromoteur getType() {
        return this.type;
    }

    /**
     * Setter pour type.
     * @param type le type à écrire.
     */
    public void setType(final TypePromoteur type) {
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.setRaisonSociale(null);
        this.setType(null);
        this.setIdentifiant(null);
    }
    /**
     * Getter sur identifiant.
     * @return Retourne le identifiant.
     */
    public String getIdentifiant() {
        return this.identifiant;
    }

    /**
     * Setter pour identifiant.
     * @param identifiant le identifiant à écrire.
     */
    public void setIdentifiant(final String identifiant) {
        this.identifiant = identifiant;
    }

}
