package fr.pharma.eclipse.service.evenement.updator.impl;

import java.io.Serializable;
import java.util.Calendar;

import fr.pharma.eclipse.domain.enums.evenement.ResultatVisite;
import fr.pharma.eclipse.domain.enums.evenement.TypeEvenement;
import fr.pharma.eclipse.domain.enums.evenement.TypeVisite;
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.service.evenement.impl.EvenementServiceImpl;
import fr.pharma.eclipse.service.evenement.updator.EvenementBeforeSaveUpdator;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de mettre à jour la date de pré cloture de l'essai lié à l'evenement.
 
 * @version $Revision$ $Date$
 */
public class PreclotureUpdator
    implements EvenementBeforeSaveUpdator, Serializable
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -2216619399447998783L;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(final Evenement evenement)
    {
        return evenement.getTypeEvenement().equals(TypeEvenement.VISITE)
               && evenement.getTypeVisite().equals(TypeVisite.PRE_CLOTURE)
               && evenement.getResultatVisite() != null
               && evenement.getResultatVisite().equals(ResultatVisite.EFFECTUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Evenement evenement,
                       final EvenementServiceImpl service)
    {
        evenement.getEssai().getDetailDates().setPreCloture(Calendar
                .getInstance(EclipseConstants.LOCALE));
        service.getEssaiService().save(evenement.getEssai());
    }

}
