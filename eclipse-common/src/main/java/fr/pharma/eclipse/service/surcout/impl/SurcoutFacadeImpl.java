package fr.pharma.eclipse.service.surcout.impl;

import java.util.Calendar;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.criteria.patient.InclusionSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.patient.InclusionService;
import fr.pharma.eclipse.service.surcout.SurcoutFacade;

/**
 * Classe d'implémentation de la facade de gestion des surcouts.s
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class SurcoutFacadeImpl implements SurcoutFacade {

    /**
     * Service des inclusions.
     */
    @Resource(name = "inclusionService")
    private InclusionService inclusionService;

    /**
     * {@inheritDoc}
     */
    @Override
    public int countNbPatients(final Essai essai,
                               final Calendar dateDebut,
                               final Calendar dateFin,
                               final boolean inclu) {
        final InclusionSearchCriteria criteria = new InclusionSearchCriteria();
        criteria.setEssai(essai);
        criteria.setActif(inclu);
        criteria.setDateDebut(dateDebut);
        criteria.setDateFin(dateFin);
        return this.inclusionService.count(criteria).intValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int countNbAnnees(final Essai essai,
                             final Calendar dateDebut,
                             final Calendar dateFin) {
        int nbAnnees = 0;
        final Calendar debut = essai.getDetailDates().getDebutEtude();
        if (debut == null) {

            throw new ValidationException("surcout.essai.dateDebut", new String[]{"notNull" });
        }

        final Calendar date = (Calendar) debut.clone();

        date.add(Calendar.YEAR, 1);
        final Calendar fin = (Calendar) dateFin.clone();
        fin.add(Calendar.DAY_OF_MONTH, 1);

        while (date.before(Calendar.getInstance())) {
            if (date.after(dateDebut) && date.before(fin)) {
                nbAnnees++;
            }
            date.add(Calendar.YEAR, 1);
        }

        return nbAnnees;

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean inPremiereAnnee(final Essai essai,
                                   final Calendar dateDebut,
                                   final Calendar dateFin) {
        final Calendar debut = essai.getDetailDates().getDebutEtude();
        if (debut == null) {

            throw new ValidationException("surcout.essai.dateDebut", new String[]{"notNull" });
        } else {
            final Calendar fin = (Calendar) dateFin.clone();
            fin.add(Calendar.DAY_OF_MONTH, 1);
            return debut.after(dateDebut) && debut.before(fin);
        }

    }

    /**
     * Setter pour inclusionService.
     * @param inclusionService le inclusionService à écrire.
     */
    public void setInclusionService(final InclusionService inclusionService) {
        this.inclusionService = inclusionService;
    }
}
