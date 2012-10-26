package fr.pharma.eclipse.processor.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.domain.model.sigrec.SigrecMultiTrialsTransfert;
import fr.pharma.eclipse.domain.model.sigrec.SponsorType;
import fr.pharma.eclipse.domain.model.sigrec.acteur.PromoteurSigrec;
import fr.pharma.eclipse.processor.SigrecProcessor;
import fr.pharma.eclipse.processor.helper.SponsorExtractorHelper;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.converter.BeanConverter;

/**
 * Processor en charge de récupérer l'ensemble des Sponsors, de les convertir et de les
 * enregistrer.
 
 * @version $Revision$ $Date$
 */
@Transactional
public class PromoteurProcessor
    implements SigrecProcessor<SigrecMultiTrialsTransfert>
{

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(PromoteurProcessor.class);

    /**
     * Extracteur de promoteur.
     */
    @Resource(name = "sponsorExtractorHelper")
    private SponsorExtractorHelper extractor;

    /**
     * Converter.
     */
    @Resource(name = "promoteurSigrecConverter")
    private BeanConverter<SponsorType, PromoteurSigrec> converter;

    /**
     * Service Promoteur.
     */
    @Resource(name = "promoteurSigrecService")
    private GenericService<PromoteurSigrec> service;

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(final SigrecMultiTrialsTransfert bean)
    {

        this.log.info("Synchronisation des promoteurs.");
        // Extraction des promoteurs de l'ensemble de la grappe.
        final List<SponsorType> sponsors = this.extractor.extract(bean);

        final List<PromoteurSigrec> promoteurs = new ArrayList<PromoteurSigrec>();

        // Conversion des bean techniques en bean métier.
        final PromoteurSigrec promoteur;
        this.log.info("Conversion des promoteurs.");
        for (final SponsorType sponsor : sponsors)
        {
            promoteurs.add(this.converter.convert(sponsor));
        }

        // persistance des informations.
        this.log.info("Sauvegarde des promoteurs.");
        this.service.saveAll(promoteurs);
        this.log.info("Synchronisation des promoteurs terminée.");
    }
    /**
     * Getter sur extractor.
     * @return Retourne le extractor.
     */
    public SponsorExtractorHelper getExtractor()
    {
        return this.extractor;
    }

    /**
     * Setter pour extractor.
     * @param extractor le extractor à écrire.
     */
    public void setExtractor(final SponsorExtractorHelper extractor)
    {
        this.extractor = extractor;
    }

    /**
     * Getter sur converter.
     * @return Retourne le converter.
     */
    public BeanConverter<SponsorType, PromoteurSigrec> getConverter()
    {
        return this.converter;
    }

    /**
     * Setter pour converter.
     * @param converter le converter à écrire.
     */
    public void setConverter(final BeanConverter<SponsorType, PromoteurSigrec> converter)
    {
        this.converter = converter;
    }

    /**
     * Getter sur service.
     * @return Retourne le service.
     */
    public GenericService<PromoteurSigrec> getService()
    {
        return this.service;
    }

    /**
     * Setter pour service.
     * @param service le service à écrire.
     */
    public void setService(final GenericService<PromoteurSigrec> service)
    {
        this.service = service;
    }
}
