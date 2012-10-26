package fr.pharma.eclipse.filler.impl.sigrec.essai;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.domain.criteria.sigrec.acteur.PromoteurSigrecSearchCriteria;
import fr.pharma.eclipse.domain.model.sigrec.SponsorType;
import fr.pharma.eclipse.domain.model.sigrec.TrialType;
import fr.pharma.eclipse.domain.model.sigrec.acteur.PromoteurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.converter.filler.Filler;

/**
 * Filler en charge de remplir les informations liées au promoteur dans l'essai.
 
 * @version $Revision$ $Date$
 */
public class PromoteurFiller
    implements Filler<TrialType, EssaiSigrec>
{
    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(PromoteurFiller.class);

    /**
     * Service Promoteur Sigrec.
     */
    @Resource(name = "promoteurSigrecService")
    private GenericService<PromoteurSigrec> promoteurService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(final TrialType source,
                     final EssaiSigrec destination)
    {
        if (source.getSponsorInformation().getSponsor().size() > 0)
        {
            final SponsorType sponsor = source.getSponsorInformation().getSponsor().get(0);

            final PromoteurSigrecSearchCriteria crit = new PromoteurSigrecSearchCriteria();
            crit.setIdentifiant(sponsor.getEvSponsorSenderId());
            final List<PromoteurSigrec> result = this.promoteurService.getAll(crit);
            if (result.size() > 0)
            {
                destination.setPromoteur(result.get(0));
                destination.setTypePromoteur(result.get(0).getType());
            }
            else
            {
                this.log.info("Aucun promoteur reconnu pour l'essai.");
            }
        }

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(final TrialType source)
    {
        return null != source.getSponsorInformation();
    }
    /**
     * Getter sur promoteurService.
     * @return Retourne le promoteurService.
     */
    public GenericService<PromoteurSigrec> getPromoteurService()
    {
        return this.promoteurService;
    }
    /**
     * Setter pour promoteurService.
     * @param promoteurService le promoteurService à écrire.
     */
    public void setPromoteurService(final GenericService<PromoteurSigrec> promoteurService)
    {
        this.promoteurService = promoteurService;
    }
}
