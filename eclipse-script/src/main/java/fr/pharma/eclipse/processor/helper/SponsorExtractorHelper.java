package fr.pharma.eclipse.processor.helper;

import java.util.ArrayList;
import java.util.List;

import fr.pharma.eclipse.domain.model.sigrec.SigrecMultiTrialsTransfert;
import fr.pharma.eclipse.domain.model.sigrec.SponsorType;
import fr.pharma.eclipse.domain.model.sigrec.TrialType;

/**
 * Helper en charge d'extraire la liste des sponsor liée à un essai (TrialType).
 
 * @version $Revision$ $Date$
 */
public class SponsorExtractorHelper
{
    /**
     * Méthode en charge d'extraire l'ensemble des SponsorType de la grappe.
     * @param grappe Grappe des éléments XML.
     * @return La liste de sponsorType extraits.
     */
    public List<SponsorType> extract(final SigrecMultiTrialsTransfert grappe)
    {
        final List<SponsorType> sponsors = new ArrayList<SponsorType>();
        for (final TrialType trial : grappe.getMultiTrial().getTrial())
        {
            sponsors.addAll(trial.getSponsorInformation().getSponsor());
        }
        return sponsors;
    }
}
