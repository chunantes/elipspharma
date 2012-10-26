package fr.pharma.eclipse.service.surcout.counter.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import fr.pharma.eclipse.domain.enums.RealisePar;
import fr.pharma.eclipse.domain.enums.evenement.TypeEvenement;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.surcout.DonneesPrevision;
import fr.pharma.eclipse.predicate.GenericPredicate;
import fr.pharma.eclipse.service.surcout.counter.ActeCounter;

/**
 * Processor en charge de compter le nombre de ré-étiquetages.
 
 * @version $Revision$ $Date$
 */
public class ReetiquetageCounter
    extends EvenementCounter
    implements ActeCounter, Serializable
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -4396280638450549577L;

    /**
     * {@inheritDoc}
     */
    public int process(final Essai essai,
                       final Patient patient,
                       final Calendar dateDebut,
                       final Calendar dateFin)
    {
        int size = 0;

        final List<Evenement> reetiquetages = super.process(TypeEvenement.REETIQUETAGE,
                                                            null,
                                                            essai,
                                                            patient,
                                                            dateDebut,
                                                            dateFin);
        CollectionUtils.filter(reetiquetages,
                               new GenericPredicate("realisePar",
                                                    RealisePar.PHARMACIE));

        for (final Evenement e : reetiquetages)
        {
            size += e.getNombre();
        }
        return size;
    }

    /**
     * {@inheritDoc}
     */
    public int process(final Essai essai,
                       final Patient patient,
                       final DonneesPrevision prevision)
    {
        this.checkPatient(patient);
        return prevision.getNbReetiquetages();
    }

}
