package fr.pharma.eclipse.filler.impl.sigrec.essai;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.domain.enums.QualiteInsu;
import fr.pharma.eclipse.domain.model.sigrec.TrialDesignType;
import fr.pharma.eclipse.domain.model.sigrec.TrialType;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.filler.helper.BooleanFillerHelper;
import fr.pharma.eclipse.utils.converter.filler.Filler;

/**
 * Filler en charge de déterminer la qualité de l'insu et de l'insérer dans le bean métier de
 * l'essai.
 
 * @version $Revision$ $Date$
 */
public class QualiteInsuFiller
    implements Filler<TrialType, EssaiSigrec>
{
    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(QualiteInsuFiller.class);

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
        if (this.helper.checkTrue(design.getOpen()))
        {
            destination.getDetailRecherche().setQualiteInsu(QualiteInsu.ESSAI_OUVERT);
        }
        else if (this.helper.checkTrue(design.getSingleBlind()))
        {
            destination.getDetailRecherche().setQualiteInsu(QualiteInsu.ESSAI_SIMPLE_AVEUGLE);
        }
        else if (this.helper.checkTrue(design.getDoubleBlind()))
        {
            destination.getDetailRecherche().setQualiteInsu(QualiteInsu.ESSAI_DOUBLE_AVEUGLE);
        }
        else
        {
            this.log.info("Aucne information sur la qualité de l'insu.");
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(final TrialType source)
    {
        return null != source.getGeneralInformation()
               && null != source.getGeneralInformation().getTrialDesign();

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
