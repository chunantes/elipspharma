package fr.pharma.eclipse.filler.impl.sigrec.essai;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.model.sigrec.InvestigatorType;
import fr.pharma.eclipse.domain.model.sigrec.TrialType;
import fr.pharma.eclipse.domain.model.sigrec.acteur.ARCInvestigateurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.CentreSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.CoInvestigateurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.converter.filler.Filler;
import fr.pharma.eclipse.utils.converter.impl.GenericBeanConverter;

/**
 * Filler en charge de déterminer les investigateurs (principal, co et arc) et de les insérer dans
 * l'essai.
 
 * @version $Revision$ $Date$
 */
public class CentreFiller
    implements Filler<TrialType, EssaiSigrec>
{

    /**
     * Converter en charge de convertir un InvestigatorType en CentreSigrec.
     */
    @Resource(name = "centreSigrecConverter")
    private GenericBeanConverter<InvestigatorType, CentreSigrec> converter;

    /**
     * Service centre.
     */
    @Resource(name = "centreSigrecService")
    private GenericService<CentreSigrec> centreService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(final TrialType source,
                     final EssaiSigrec destination)
    {
        CentreSigrec centre = null;

        // Pour chaque investigatorType de TrialType.
        for (final InvestigatorType invest : source
                .getProposedClinicalTrialSites()
                .getInvestigator())
        {
            // Conversion en centre.
            centre = this.converter.convert(invest);

            this.centreService.save(centre);

            // on copie les investigateurs correspondants
            destination.getArcInvestigateurs().addAll(centre.getArcInvestigateurs());
            if (centre.getInvestigateurs().size() > 0)
            {
                destination.setInvestigateurPrincipal(centre.getInvestigateurs().get(0));
            }
            destination.getCoInvestigateurs().addAll(centre.getCoInvestigateurs());

            // affectation de l'essai aux investigateurs.
            for (final CoInvestigateurSigrec co : destination.getCoInvestigateurs())
            {
                co.setEssai(destination);
            }
            for (final ARCInvestigateurSigrec co : destination.getArcInvestigateurs())
            {
                co.setEssai(destination);
            }

        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(final TrialType source)
    {
        return null != source.getProposedClinicalTrialSites();

    }

    /**
     * Getter sur converter.
     * @return Retourne le converter.
     */
    public GenericBeanConverter<InvestigatorType, CentreSigrec> getConverter()
    {
        return this.converter;
    }

    /**
     * Setter pour converter.
     * @param converter le converter à écrire.
     */
    public void setConverter(final GenericBeanConverter<InvestigatorType, CentreSigrec> converter)
    {
        this.converter = converter;
    }
    /**
     * Getter sur centreService.
     * @return Retourne le centreService.
     */
    public GenericService<CentreSigrec> getCentreService()
    {
        return this.centreService;
    }
    /**
     * Setter pour centreService.
     * @param centreService le centreService à écrire.
     */
    public void setCentreService(final GenericService<CentreSigrec> centreService)
    {
        this.centreService = centreService;
    }

}
