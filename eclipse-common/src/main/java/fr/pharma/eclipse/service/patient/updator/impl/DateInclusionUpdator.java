package fr.pharma.eclipse.service.patient.updator.impl;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.criteria.patient.InclusionSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.patient.InclusionService;
import fr.pharma.eclipse.service.patient.updator.InclusionBeforeSaveUpdator;

/**
 * Description de la classe.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DateInclusionUpdator implements InclusionBeforeSaveUpdator {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -5029172480191563988L;

    /**
     * Service essai.
     */
    @Resource(name = "essaiService")
    private EssaiService essaiService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Inclusion inclusion,
                       final InclusionService service) {
        final Essai essai = this.essaiService.get(inclusion.getEssai().getId());
        final InclusionSearchCriteria crit = new InclusionSearchCriteria();
        crit.setEssai(essai);
        if (service.getAll(crit).isEmpty()) {
            essai.getDetailDates().setDebutInclusion(inclusion.getDateInclusion());
            this.essaiService.save(essai);
        }
    }

    /**
     * Setter pour essaiService.
     * @param essaiService Le essaiService à écrire.
     */
    public void setEssaiService(final EssaiService essaiService) {
        this.essaiService = essaiService;
    }

}
