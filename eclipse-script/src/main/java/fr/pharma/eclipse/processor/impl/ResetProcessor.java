package fr.pharma.eclipse.processor.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.domain.model.sigrec.SigrecMultiTrialsTransfert;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.processor.SigrecProcessor;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * processor en charge de réinitialiser les tables de synchronisations Sigrec en supprimant les
 * essais et les promoteurs.
 
 * @version $Revision$ $Date$
 */
@Transactional
public class ResetProcessor
    implements SigrecProcessor<SigrecMultiTrialsTransfert>
{

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(ResetProcessor.class);

    /**
     * Service essaiSigrec.
     */
    @Resource(name = "essaiSigrecService")
    private GenericService<EssaiSigrec> essaiSigrecService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(final SigrecMultiTrialsTransfert bean)
    {
        this.log
                .info("Suppression des essais et des promoteurs présents dans les tables de synchronisation.");
        this.essaiSigrecService.remove(this.essaiSigrecService.getAll());
        this.log.info("Suppression effectuée.");
    }

    /**
     * Setter pour essaiSigrecService.
     * @param essaiSigrecService le essaiSigrecService à écrire.
     */
    public void setEssaiSigrecService(final GenericService<EssaiSigrec> essaiSigrecService)
    {
        this.essaiSigrecService = essaiSigrecService;
    }

}
