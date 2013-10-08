package fr.pharma.eclipse.service.essai.updator.impl;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.essai.updator.EssaiBeforeSaveUpdator;

/**
 * Updator en charge de mettre à jour l'état d'un essai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EtatBeforeSaveUpdator implements EssaiBeforeSaveUpdator {
    private static final long serialVersionUID = 6698150218110L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Essai essai,
                       final EssaiService service) {
        service.updateEtat(essai);
    }

}
