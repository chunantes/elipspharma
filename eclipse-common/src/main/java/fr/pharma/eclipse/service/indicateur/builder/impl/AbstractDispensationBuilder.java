package fr.pharma.eclipse.service.indicateur.builder.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.criteria.dispensation.DispensationSearchCriteria;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.indicateur.Indicateur;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.dispensation.DispensationService;
import fr.pharma.eclipse.service.indicateur.builder.IndicateurBuilder;

/**
 * Description de la classe.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public abstract class AbstractDispensationBuilder implements IndicateurBuilder, Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 4631559491454538033L;

    /**
     * Service dispensations.
     */
    @Resource(name = "dispensationService")
    private DispensationService dispensationService;

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract Indicateur build(Pharmacie pharmacie,
                                     Calendar dateDebut,
                                     Calendar dateFin);

    /**
     * Méthode en charge de retourner les dispensations.
     * @param pharmacie La pharmacie.
     * @param dateDebut La date de début.
     * @param dateFin La date de fin.
     * @param dispense Booleen dispensé.
     * @return Les dispensations.
     */
    protected List<Dispensation> loadDispensations(final Pharmacie pharmacie,
                                                   final Calendar dateDebut,
                                                   final Calendar dateFin,
                                                   final Boolean dispense) {
        final DispensationSearchCriteria criteria = new DispensationSearchCriteria();
        criteria.setDateDebut(dateDebut);
        criteria.setDateFin(dateFin);
        criteria.setPharmacie(pharmacie);
        criteria.setDispense(dispense);

        return this.dispensationService.getAll(criteria);

    }

    /**
     * Setter pour dispensationService.
     * @param dispensationService Le dispensationService à écrire.
     */
    public void setDispensationService(final DispensationService dispensationService) {
        this.dispensationService = dispensationService;
    }
}
