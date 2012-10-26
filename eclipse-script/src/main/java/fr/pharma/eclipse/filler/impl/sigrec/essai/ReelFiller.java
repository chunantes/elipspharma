package fr.pharma.eclipse.filler.impl.sigrec.essai;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.domain.model.sigrec.TrialDesignType;
import fr.pharma.eclipse.domain.model.sigrec.TrialType;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.filler.helper.BooleanFillerHelper;
import fr.pharma.eclipse.utils.converter.filler.Filler;

/**
 * Filler en charge de remplir les informations liées aux réel (par oposition aux prévisions).
 
 * @version $Revision$ $Date$
 */
public class ReelFiller
    implements Filler<TrialType, EssaiSigrec>
{

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(ReelFiller.class);

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
        final TrialDesignType design = source.getGeneralInformation().getTrialDesign();

        // Multi centrique.
        if (this.helper.checkTrue(design.getMultipleSite()))
        {
            destination.setMulticentrique(true);
        }
        else
        {
            destination.setMulticentrique(false);
        }

        // Nombre de sites
        if (null != design.getNumberOfSites())
        {
            try
            {

                destination.setNbCentres(Integer.parseInt(design.getNumberOfSites()));
            }
            catch (final NumberFormatException e)
            {
                this.log.info("Le nombre de centre n'a pu être récupéré.");
            }
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(final TrialType source)
    {
        return source != null
               && source.getGeneralInformation() != null
               && source.getGeneralInformation().getTrialDesign() != null;
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
