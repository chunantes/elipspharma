package fr.pharma.eclipse.filler.impl.sigrec.essai;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.domain.model.sigrec.OptionalType;
import fr.pharma.eclipse.domain.model.sigrec.TrialType;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.detail.PrevisionSigrec;
import fr.pharma.eclipse.utils.Utils;
import fr.pharma.eclipse.utils.converter.filler.Filler;

/**
 * Filler en charge de remplir les informations liées à la prévision.
 
 * @version $Revision$ $Date$
 */
public class PrevisionFiller
    implements Filler<TrialType, EssaiSigrec>
{

    /**
     * Format des dates.
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(PrevisionFiller.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(final TrialType source,
                     final EssaiSigrec destination)
    {

        final PrevisionSigrec prevision = destination.getPrevision();

        // Durée totale
        final OptionalType option =
            source.getGeneralInformation().getTotalTrialDurationMsMonths();
        if (null != option)
        {
            try
            {
                prevision.setDureeTotale(Integer.parseInt(option.getValue()));
            }
            catch (final NumberFormatException e)
            {
                this.log.info("La durée totale de l'essai n'a pu être récupérée.");
            }
        }
        // Nombre de sites
        if (null != source.getGeneralInformation().getTrialDesign())
        {
            try
            {

                prevision.setNbCentres(Integer.parseInt(source
                        .getGeneralInformation()
                        .getTrialDesign()
                        .getNumberOfSites()));
            }
            catch (final NumberFormatException e)
            {
                this.log.info("Le nombre de centre n'a pu être récupéré.");
            }
        }

        // Dates de début et de fin.
        if (source.getGeneralInformation().getPlannedEndDate() != null)
        {
            try
            {

                prevision.setDateDebut(Utils.parseDate(source
                                                               .getGeneralInformation()
                                                               .getPlannedStartDate()
                                                               .getValue(),
                                                       PrevisionFiller.DATE_FORMAT));
            }
            catch (final ParseException e)
            {
                this.log.info("La date de début n'a pue être récupérée.");
            }
        }

        if (source.getGeneralInformation().getPlannedEndDate() != null)
        {
            try
            {

                prevision.setDateFin(Utils.parseDate(source
                                                             .getGeneralInformation()
                                                             .getPlannedEndDate()
                                                             .getValue(),
                                                     PrevisionFiller.DATE_FORMAT));
            }
            catch (final ParseException e)
            {
                this.log.info("La date de début n'a pue être récupérée.");
            }
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(final TrialType source)
    {
        return null != source.getGeneralInformation();
    }

}
