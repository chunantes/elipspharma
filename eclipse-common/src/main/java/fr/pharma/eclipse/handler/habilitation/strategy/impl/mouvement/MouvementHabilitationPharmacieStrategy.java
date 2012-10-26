package fr.pharma.eclipse.handler.habilitation.strategy.impl.mouvement;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.handler.habilitation.strategy.HabilitationHandlerStrategy;
import fr.pharma.eclipse.service.stockage.PharmacieService;

/**
 * Stratégie qui élimine les mouvements pour lesquels l'utilisateur n'a pas d'habilitation active
 * sur les pharmacies.
 
 * @version $Revision$ $Date$
 */
public class MouvementHabilitationPharmacieStrategy<BEAN extends MvtStock>
    implements HabilitationHandlerStrategy<BEAN>
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3213162716864552112L;

    /**
     * Service de gestion des pharmacies.
     */
    @Resource(name = "pharmacieService")
    private PharmacieService pharmacieService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(final Personne personne)
    {
        return personne.getType().equals(TypePersonne.PHARMACIEN);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doPurge(final List<BEAN> beans,
                        final Personne personne)
    {
        // Récupération des pharmacies gérant les droits de visibilité utilisateur
        final List<Pharmacie> pharmacies = this.pharmacieService.getAll();

        // Filtre des mouvements par rapport à la pharmacie du mouvement
        CollectionUtils.filter(beans,
                               new Predicate() {

                                   @Override
                                   public boolean evaluate(final Object object)
                                   {
                                       final MvtStock mvt = (MvtStock) object;
                                       return pharmacies.contains(mvt.getPharmacie());
                                   }
                               });
    }

    /**
     * Setter pour pharmacieService.
     * @param pharmacieService Le pharmacieService à écrire.
     */
    public void setPharmacieService(final PharmacieService pharmacieService)
    {
        this.pharmacieService = pharmacieService;
    }
}
