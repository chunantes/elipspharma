package fr.pharma.eclipse.dictionary.maker.dispensation;

import java.util.Calendar;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.dispensation.DispensationForOrdoSearchCriteria;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Artisan de recherche pour les Dispensations dans la gestion des ordonnanciers.
 
 * @version $Revision$ $Date$
 */
public class DispensationForOrdoSearchCriteriaMaker
    extends AbstractCriteriaMaker
{
    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -3961170707483632810L;

    /**
     * Constructeur par défaut.
     */
    public DispensationForOrdoSearchCriteriaMaker()
    {
        super(DispensationForOrdoSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit)
    {
        final DispensationForOrdoSearchCriteria crit =
            (DispensationForOrdoSearchCriteria) searchCrit;

        // Dispensé
        CriteriaMakerUtils.addCritere(criteria,
                                      "dispense",
                                      Boolean.TRUE);

        // Infos Ordonnancier null
        criteria.add(Restrictions.isNull("ordonnancier"));

        // Infos Ordonnancier null
        criteria.add(Restrictions.isNotNull("numOrdonnancier"));

        final Criteria critDispensationProduit = criteria.createCriteria("dispensationsProduit");

        // Pharmacie
        if (crit.getPharmacie() != null)
        {
            CriteriaMakerUtils.addCritere(critDispensationProduit,
                                          "pharmacie",
                                          crit.getPharmacie());
        }

        // Date de début
        if (crit.getDateDebut() != null)
        {
            critDispensationProduit.add(Restrictions.ge("dateCreation",
                                                        crit.getDateDebut()));
        }

        // Date de fin
        if (crit.getDateFin() != null)
        {
            final Calendar fin = Calendar.getInstance(EclipseConstants.LOCALE);
            fin.setTime(crit.getDateFin().getTime());
            // Ajout d'un jour
            fin.add(Calendar.DAY_OF_MONTH,
                    1);
            critDispensationProduit.add(Restrictions.le("dateCreation",
                                                        fin));
        }
    }

}
