package fr.pharma.eclipse.utils.converter.filler.impl.essai;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.embedded.InfosAssurance;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.detail.AssuranceSigrec;
import fr.pharma.eclipse.utils.converter.filler.Filler;
import fr.pharma.eclipse.utils.introspection.GenericFetcher;

/**
 * Filler en charge de populer les assurance d'un essai à partir d'un essai
 * sigrec.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AssuranceFiller implements Filler<EssaiSigrec, Essai> {

    /**
     * Fetcher en charge de populer les informations d'assurance.
     */
    @Resource(name = "assuranceFetcher")
    private GenericFetcher<AssuranceSigrec, InfosAssurance> assuranceFetcher;

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(final EssaiSigrec source,
                     final Essai destination) {
        if (source.getAssurances().size() > 0) {
            this.assuranceFetcher.fetch(source.getAssurances().get(0), destination.getDetailAdministratif().getInfosAssurance());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(final EssaiSigrec source) {
        return source.getAssurances() != null;
    }

    /**
     * Setter pour assuranceFetcher.
     * @param assuranceFetcher le assuranceFetcher à écrire.
     */
    public void setAssuranceFetcher(final GenericFetcher<AssuranceSigrec, InfosAssurance> assuranceFetcher) {
        this.assuranceFetcher = assuranceFetcher;
    }

}
