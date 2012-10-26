package fr.pharma.eclipse.processor.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.domain.model.sigrec.SigrecMultiTrialsTransfert;
import fr.pharma.eclipse.domain.model.sigrec.TrialType;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.processor.SigrecProcessor;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.converter.BeanConverter;

/**
 * Implémentation d'un processor permettant en charge de récupérer l'ensemble des TrialType, de
 * les convertir et de les enregistrer.
 
 * @version $Revision$ $Date$
 */
@Transactional
public class EssaiProcessor
    implements SigrecProcessor<SigrecMultiTrialsTransfert>
{
    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(EssaiProcessor.class);

    /**
     * Converter.
     */
    @Resource(name = "essaiSigrecConverter")
    private BeanConverter<TrialType, EssaiSigrec> converter;

    /**
     * Service essaiSigrec.
     */
    @Resource(name = "essaiSigrecService")
    private GenericService<EssaiSigrec> essaiService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void process(final SigrecMultiTrialsTransfert bean)
    {
        this.log.info("Synchronisation des essais.");
        final List<EssaiSigrec> essais = new ArrayList<EssaiSigrec>();

        final List<TrialType> trials = bean.getMultiTrial().getTrial();

        this.log.info("Conversion des essais.");
        // Conversion des bean techniques en bean métier.
        for (final TrialType trial : trials)
        {
            essais.add(this.converter.convert(trial));
        }

        // persistance des informations.
        this.log.info("Sauvegarde des essais.");
        this.essaiService.saveAll(essais);
    }
    /**
     * Getter sur converter.
     * @return Retourne le converter.
     */
    public BeanConverter<TrialType, EssaiSigrec> getConverter()
    {
        return this.converter;
    }

    /**
     * Setter pour converter.
     * @param converter le converter à écrire.
     */
    public void setConverter(final BeanConverter<TrialType, EssaiSigrec> converter)
    {
        this.converter = converter;
    }

    /**
     * Getter sur essaiService.
     * @return Retourne le essaiService.
     */
    public GenericService<EssaiSigrec> getEssaiService()
    {
        return this.essaiService;
    }

    /**
     * Setter pour essaiService.
     * @param essaiService le essaiService à écrire.
     */
    public void setEssaiService(final GenericService<EssaiSigrec> essaiService)
    {
        this.essaiService = essaiService;
    }

}
