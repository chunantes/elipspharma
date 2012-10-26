package fr.pharma.eclipse.service.surcout.counter.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.criteria.evenement.EvenementSearchCriteria;
import fr.pharma.eclipse.domain.enums.evenement.TypeEvenement;
import fr.pharma.eclipse.domain.enums.evenement.TypeVisite;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.evenement.EvenementService;
import fr.pharma.eclipse.service.surcout.counter.ActeCounter;

/**
 * Classe en charge de compter le nombre d'évènements pour un essai ou un patient dans un essai.
 
 * @version $Revision$ $Date$
 */
public abstract class EvenementCounter
    implements ActeCounter, Serializable
{

    /**
     * Serial UID.
     */
    private static final long serialVersionUID = -5454986233941697685L;

    /**
     * Service des évènements
     */
    @Resource(name = "evenementService")
    private EvenementService evenementService;

    /**
     * {@inheritDoc}
     */
    protected List<Evenement> process(final TypeEvenement type,
                                      final TypeVisite typeVisite,
                                      final Essai essai,
                                      final Patient patient,
                                      final Calendar dateDebut,
                                      final Calendar dateFin)
    {
        this.checkPatient(patient);
        final EvenementSearchCriteria crit = new EvenementSearchCriteria();
        crit.setTypeEvenement(type);
        crit.setTypeVisite(typeVisite);
        crit.setDateDebut(dateDebut);
        crit.setEssai(essai);
        crit.setDateFin(dateFin);
        return this.evenementService.getAll(crit);
    }
    /**
     * Setter pour evenementService.
     * @param evenementService Le evenementService à écrire.
     */
    public void setEvenementService(final EvenementService evenementService)
    {
        this.evenementService = evenementService;
    }

    /**
     * Méthode en charge de vérifier que la règle est bien de perimètre "par essai".
     * @param patient Patient.
     */
    protected void checkPatient(final Patient patient)
    {
        if (patient != null)
        {
            throw new ValidationException("surcout.support.patient",
                                          new String[]
                                          {"error" });
        }
    }
}
