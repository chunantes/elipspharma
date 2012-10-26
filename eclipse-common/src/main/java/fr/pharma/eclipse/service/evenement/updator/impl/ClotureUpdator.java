package fr.pharma.eclipse.service.evenement.updator.impl;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.enums.evenement.ResultatVisite;
import fr.pharma.eclipse.domain.enums.evenement.TypeEvenement;
import fr.pharma.eclipse.domain.enums.evenement.TypeVisite;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.evenement.impl.EvenementServiceImpl;
import fr.pharma.eclipse.service.evenement.updator.EvenementBeforeSaveUpdator;

/**
 * Description de la classe.
 
 * @version $Revision$ $Date$
 */
public class ClotureUpdator
    implements EvenementBeforeSaveUpdator
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 1814124974370000341L;

    /**
     * Service essai.
     */
    @Resource(name = "essaiService")
    private EssaiService serviceEssai;

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Evenement evenement,
                       final EvenementServiceImpl service)
    {
        final Essai essai = this.serviceEssai.get(evenement.getEssai().getId());
        essai.getDetailDates().setCloture(evenement.getDateDebut());
        essai.setEtat(EtatEssai.CLOTURE);
        this.serviceEssai.addDetailEtatEssai(essai,
                                             EtatEssai.CLOTURE,
                                             "Visite de cloture effectuée.");
        this.serviceEssai.save(essai);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(final Evenement evenement)
    {
        return evenement.getTypeEvenement().equals(TypeEvenement.VISITE)
               && evenement.getTypeVisite().equals(TypeVisite.CLOTURE)
               && evenement.getResultatVisite() != null
               && evenement.getResultatVisite().equals(ResultatVisite.EFFECTUE)
               && evenement.getEssai() != null;
    }

    /**
     * Setter pour serviceEssai.
     * @param serviceEssai Le serviceEssai à écrire.
     */
    public void setServiceEssai(final EssaiService serviceEssai)
    {
        this.serviceEssai = serviceEssai;
    }
}
