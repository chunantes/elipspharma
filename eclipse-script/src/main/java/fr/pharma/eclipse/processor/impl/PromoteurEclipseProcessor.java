package fr.pharma.eclipse.processor.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.domain.model.acteur.Promoteur;
import fr.pharma.eclipse.domain.model.sigrec.acteur.PromoteurSigrec;
import fr.pharma.eclipse.predicate.GenericPredicate;
import fr.pharma.eclipse.processor.Processor;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.converter.BeanConverter;

/**
 * Processor en charge de copier les promoteurs sigrec dans les tables eclipse.
 
 * @version $Revision$ $Date$
 */
@Transactional
public class PromoteurEclipseProcessor
    implements Processor
{
    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(PromoteurEclipseProcessor.class);

    /**
     * Service de promoteurs sigrec.
     */
    @Resource(name = "promoteurSigrecService")
    private GenericService<PromoteurSigrec> promoteurSigrecService;

    /**
     * Service de promoteurs.
     */
    @Resource(name = "promoteurService")
    private GenericService<Promoteur> promoteurService;

    /**
     * Converter PromoteurSigrec => Promoteur.
     */
    @Resource(name = "promoteurEclipseConverter")
    private BeanConverter<PromoteurSigrec, Promoteur> converter;

    /**
     * {@inheritDoc}
     */
    @Override
    public void process()
    {
        this.log.info("Récupération des promoteurs Sigrec.");
        final List<PromoteurSigrec> promoteursSigrecs = this.promoteurSigrecService.getAll();
        this.log.info("Récupération des promoteurs Eclipse.");
        final List<Promoteur> promoteurs = this.promoteurService.getAll();

        this.log.info("Enregistrement des nouveaux promoteurs Eclipse.");
        for (final PromoteurSigrec p : promoteursSigrecs)
        {
            if (!CollectionUtils.exists(promoteurs,
                                        new GenericPredicate("identifiant",
                                                             p.getIdentifiant())))
            {
                this.promoteurService.save(this.converter.convert(p));
            }
        }

    }

    /**
     * Setter pour promoteurSigrecService.
     * @param promoteurSigrecService le promoteurSigrecService à écrire.
     */
    public void setPromoteurSigrecService(final GenericService<PromoteurSigrec> promoteurSigrecService)
    {
        this.promoteurSigrecService = promoteurSigrecService;
    }

    /**
     * Setter pour promoteurService.
     * @param promoteurService le promoteurService à écrire.
     */
    public void setPromoteurService(final GenericService<Promoteur> promoteurService)
    {
        this.promoteurService = promoteurService;
    }

    /**
     * Setter pour converter.
     * @param converter le converter à écrire.
     */
    public void setConverter(final BeanConverter<PromoteurSigrec, Promoteur> converter)
    {
        this.converter = converter;
    }

}
