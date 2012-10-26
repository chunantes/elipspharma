package fr.pharma.eclipse.domain.criteria.acteur;

import fr.pharma.eclipse.domain.model.acteur.Promoteur;

/**
 * Critère de recherche de contact promoteur.
 
 * @version $Revision$ $Date$
 */
public class ContactPromoteurSearchCriteria
    extends PersonneSearchCriteria
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 7865783390550784270L;

    /**
     * Promoteur.
     */
    private Promoteur promoteur;

    /**
     * Getter sur promoteur.
     * @return Retourne le promoteur.
     */
    public Promoteur getPromoteur()
    {
        return this.promoteur;
    }

    /**
     * Setter pour promoteur.
     * @param promoteur le promoteur à écrire.
     */
    public void setPromoteur(final Promoteur promoteur)
    {
        this.promoteur = promoteur;
    }
}
