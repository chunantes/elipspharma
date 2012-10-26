package fr.pharma.eclipse.utils.converter.impl;

import java.util.List;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.criteria.essai.EssaiSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.factory.common.BeanObjectFactory;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.introspection.GenericFetcher;

/**
 * Converter en charge de convertir un essai Sigrec en Essai Eclipse.
 
 * @version $Revision$ $Date$
 */
public class EssaiBeanConverter
    extends GenericBeanConverter<EssaiSigrec, Essai>
{

    /**
     * Service essai.
     */
    @Resource(name = "essaiService")
    private GenericService<Essai> essaiService;

    /**
     * Constructeur.
     * @param fetcher GenericFetcher.
     * @param factory Factory du bean métier.
     */
    public EssaiBeanConverter(
                              final GenericFetcher<EssaiSigrec, Essai> fetcher,
                              final BeanObjectFactory<Essai> factory)
    {
        super(fetcher,
              factory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Essai convert(final EssaiSigrec essai)
    {
        // On regarde si un essai avec l'identifiant sigrec existe, si oui on le retourne.
        final EssaiSearchCriteria criteria = new EssaiSearchCriteria();
        criteria.setNumSigrec(essai.getDetailRecherche().getNumEnregistrement());
        final List<Essai> results = this.essaiService.getAll(criteria);
        if (results.size() > 0)
        {
            return results.get(0);
        }
        else
        {
            return super.convert(essai);
        }

    }

    /**
     * Setter pour essaiService.
     * @param essaiService le essaiService à écrire.
     */
    public void setEssaiService(final GenericService<Essai> essaiService)
    {
        this.essaiService = essaiService;
    }

}
