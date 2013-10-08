package fr.pharma.eclipse.domain.criteria.habilitation;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;
import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.essai.Essai;

/**
 * Critère de recherche sur Habilitation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class HabilitationSearchCriteria extends AbstractSearchCriteria {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -6752934673861997077L;

    /**
     * Essai.
     */
    private Essai essai;

    /**
     * Personne.
     */
    private Personne personne;

    /**
     * Droit.
     */
    private Droit droit;

    /**
     * Active.
     */
    private Boolean active;

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.setEssai(null);
        this.setDroit(null);
        this.setPersonne(null);
        this.setActive(null);
    }

    /**
     * Getter pour essai.
     * @return Le essai
     */
    public Essai getEssai() {
        return this.essai;
    }

    /**
     * Setter pour essai.
     * @param essai Le essai à écrire.
     */
    public void setEssai(final Essai essai) {
        this.essai = essai;
    }

    /**
     * Getter pour personne.
     * @return Le personne
     */
    public Personne getPersonne() {
        return this.personne;
    }

    /**
     * Setter pour personne.
     * @param personne Le personne à écrire.
     */
    public void setPersonne(final Personne personne) {
        this.personne = personne;
    }

    /**
     * Getter pour droit.
     * @return Le droit
     */
    public Droit getDroit() {
        return this.droit;
    }

    /**
     * Setter pour droit.
     * @param droit Le droit à écrire.
     */
    public void setDroit(final Droit droit) {
        this.droit = droit;
    }

    /**
     * Getter sur active.
     * @return Retourne le active.
     */
    public Boolean getActive() {
        return this.active;
    }

    /**
     * Setter pour active.
     * @param active le active à écrire.
     */
    public void setActive(final Boolean active) {
        this.active = active;
    }

}
