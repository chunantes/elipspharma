package fr.pharma.eclipse.service.ordonnancier.impl;

import java.util.Calendar;
import java.util.List;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.ordonnancier.OrdonnancierSearchCriteria;
import fr.pharma.eclipse.domain.model.ordonnancier.Ordonnancier;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.ordonnancier.OrdonnancierService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe d'implémentation commune du service de gestion des ordonnanciers.
 
 * @version $Revision$ $Date$
 */
public abstract class OrdonnancierServiceImpl<BEAN extends Ordonnancier>
    extends GenericServiceImpl<BEAN>
    implements OrdonnancierService<BEAN>
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 276441287653303554L;

    /**
     * Année d'initialisation de début de gestion d'ordonnancier.
     */
    protected static final int AN_2011 = 2011;

    /**
     * Constructeur.
     * @param genericDao DAO.
     */
    public OrdonnancierServiceImpl(final GenericDao<BEAN> genericDao)
    {
        super(genericDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calendar getDateDebut(final Pharmacie pharmacie)
    {
        // Récupération du dernier ordonnancier de la pharmacie
        final OrdonnancierSearchCriteria criteria = new OrdonnancierSearchCriteria();
        criteria.setPharmacie(pharmacie);
        criteria.setActiveOrder("id");
        criteria.setAscending(false);

        final Calendar dateFin = Calendar.getInstance(EclipseConstants.LOCALE);

        final List<BEAN> results = this.getAll(criteria);
        // Il existe au moins un ordonnancier pour cette pharmacie
        if (results.size() > 0)
        {
            final BEAN lastOrdonnancier = results.get(0);
            dateFin.setTime(lastOrdonnancier.getDateFin().getTime());
            dateFin.add(Calendar.DAY_OF_MONTH,
                        1);
        }
        // Aucun ordonnancier pour cette pharmacie => init par défaut au 01/01/2011
        else
        {
            dateFin.set(OrdonnancierServiceImpl.AN_2011,
                        Calendar.JANUARY,
                        1,
                        0,
                        0,
                        0);
            dateFin.set(Calendar.MILLISECOND,
                        0);
        }

        return dateFin;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Calendar getDateFin()
    {
        final Calendar dateFin = Calendar.getInstance(EclipseConstants.LOCALE);
        dateFin.set(Calendar.HOUR_OF_DAY,
                    0);
        dateFin.set(Calendar.MINUTE,
                    0);
        dateFin.set(Calendar.SECOND,
                    0);
        dateFin.set(Calendar.MILLISECOND,
                    0);
        dateFin.add(Calendar.DAY_OF_MONTH,
                    -1);
        return dateFin;
    }

}
