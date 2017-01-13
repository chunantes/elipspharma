package fr.pharma.eclipse.utils.converter.filler.impl.essai;

import java.util.List;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.criteria.acteur.PromoteurSearchCriteria;
import fr.pharma.eclipse.domain.model.acteur.Promoteur;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.converter.filler.Filler;

/**
 * Filler en charge de populer un Essai à partir du promoteur d'un EssaiSigec.
 *
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PromoteurFiller implements Filler<EssaiSigrec, Essai> {

    /**
     * Service de promoteurs.
     */
    @Resource(name = "promoteurService")
    private GenericService<Promoteur> promoteurService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(final EssaiSigrec source,
            final Essai destination) {
        // recherche des tous les promoteurs correspondants à l'identifiant du
        // promoteur courant.
        final PromoteurSearchCriteria crit = new PromoteurSearchCriteria();
        if (source.getPromoteur().getIdentifiant() != null || source.getPromoteur().getContact().getRaisonSociale() != null) {
            crit.setIdentifiant(source.getPromoteur().getIdentifiant());
            if (source.getPromoteur().getContact()!=null) {
                crit.setRaisonSociale(source.getPromoteur().getContact().getRaisonSociale());
            }
            final List<Promoteur> promoteurs = this.promoteurService.getAll(crit);

            // SI un promoteur est trouvé alors on l'affecte.
            if (promoteurs.size() > 0) {
                destination.setPromoteur(promoteurs.get(0));
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(final EssaiSigrec source) {
        return source.getPromoteur() != null;
    }

    /**
     * Setter pour promoteurService.
     *
     * @param promoteurService le promoteurService à écrire.
     */
    public void setPromoteurService(final GenericService<Promoteur> promoteurService) {
        this.promoteurService = promoteurService;
    }

}
