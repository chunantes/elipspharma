package fr.pharma.eclipse.filler.impl.sigrec.essai;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.domain.enums.PhaseRecherche;
import fr.pharma.eclipse.domain.model.sigrec.TrialType;
import fr.pharma.eclipse.domain.model.sigrec.TrialTypeAndPhaseType;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.filler.helper.BooleanFillerHelper;
import fr.pharma.eclipse.utils.converter.filler.Filler;

/**
 * Filler en charge de déterminer la phase de recherche de l'essai.
 
 * @version $Revision$ $Date$
 */
public class PhaseRechercheFiller
    implements Filler<TrialType, EssaiSigrec>
{
    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(PhaseRechercheFiller.class);

    /**
     * Helper pour gérer les booleens.
     */
    @Resource(name = "booleanFillerHelper")
    private BooleanFillerHelper helper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(final TrialType source,
                     final EssaiSigrec destination)
    {
        final TrialTypeAndPhaseType phase = source.getGeneralInformation().getTrialTypeAndPhase();
        if (this.helper.checkTrue(phase.getPhaseOne()))
        {
            destination.getDetailRecherche().setPhaseRecherche(PhaseRecherche.I);
        }
        else if (this.helper.checkTrue(phase.getPhaseTwoA()))
        {
            destination.getDetailRecherche().setPhaseRecherche(PhaseRecherche.IIa);
        }
        else if (this.helper.checkTrue(phase.getPhaseTwoB()))
        {
            destination.getDetailRecherche().setPhaseRecherche(PhaseRecherche.IIb);
        }
        else if (this.helper.checkTrue(phase.getPhaseThree()))
        {
            destination.getDetailRecherche().setPhaseRecherche(PhaseRecherche.III);
        }
        else if (this.helper.checkTrue(phase.getPhaseFour()))
        {
            destination.getDetailRecherche().setPhaseRecherche(PhaseRecherche.IV);
        }
        else
        {
            this.log.info("Aucune information sur la nature de la recherche.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(final TrialType source)
    {
        return null != source.getGeneralInformation()
               && null != source.getGeneralInformation().getTrialTypeAndPhase();
    }

    /**
     * Setter pour helper.
     * @param helper le helper à écrire.
     */
    public void setHelper(final BooleanFillerHelper helper)
    {
        this.helper = helper;
    }
}
