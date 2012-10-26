package fr.pharma.eclipse.dictionary.maker.acteur;

import org.hibernate.Criteria;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.acteur.ContactPromoteurSearchCriteria;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;

/**
 * Artisan de recherche pour les contacts promoteur.
 
 * @version $Revision$ $Date$
 */
public class ContactPromoteurSearchCriteriaMaker
    extends AbstractCriteriaMaker
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3360356580720981191L;

    /**
     * Maker de personne.
     */
    private PersonneSearchCriteriaMaker personneCriteriaMaker;

    /**
     * Constructeur par défaut.
     */
    public ContactPromoteurSearchCriteriaMaker()
    {
        super(ContactPromoteurSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit)
    {
        this.personneCriteriaMaker.transform(criteria,
                                             searchCrit);

        final ContactPromoteurSearchCriteria crit = (ContactPromoteurSearchCriteria) searchCrit;

        // Promoteur
        if (crit.getPromoteur() != null)
        {
            CriteriaMakerUtils.addCritere(criteria,
                                          "promoteur",
                                          crit.getPromoteur());
        }
    }

    /**
     * Getter sur personneCriteriaMaker.
     * @return Retourne le personneCriteriaMaker.
     */
    PersonneSearchCriteriaMaker getPersonneCriteriaMaker()
    {
        return this.personneCriteriaMaker;
    }

    /**
     * Setter pour personneCriteriaMaker.
     * @param personneCriteriaMaker le personneCriteriaMaker à écrire.
     */
    public void setPersonneCriteriaMaker(final PersonneSearchCriteriaMaker personneCriteriaMaker)
    {
        this.personneCriteriaMaker = personneCriteriaMaker;
    }

}
