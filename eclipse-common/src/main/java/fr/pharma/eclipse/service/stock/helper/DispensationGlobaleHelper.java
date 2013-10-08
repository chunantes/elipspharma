package fr.pharma.eclipse.service.stock.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import fr.pharma.eclipse.domain.criteria.stock.MvtStockSearchCriteria;
import fr.pharma.eclipse.domain.enums.TypeDispensation;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.stock.DispensationGlobale;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stock.Sortie;
import fr.pharma.eclipse.service.stock.MvtStockService;

/**
 * Helper de gestion des dispensations globales lors de dispensations
 * nominatives.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DispensationGlobaleHelper implements Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 7909934885638435631L;

    /**
     * Service des mouvements de stocks.
     */
    @Resource(name = "dispensationGlobaleService")
    private MvtStockService<DispensationGlobale> mvtService;

    /**
     * Méthode en charge de mettre à jour les dispensations globales avec les
     * sorties en paramètre.
     * @param sorties
     */
    public void updateDispensationsGlobales(final List<Sortie> sorties) {
        // on traite les sorties liés à un essai en dispensation globale.
        final List<Sortie> liste = new ArrayList<Sortie>(sorties);
        CollectionUtils.filter(liste, new Predicate() {

            @Override
            public boolean evaluate(final Object objet) {
                final Sortie sortie = (Sortie) objet;
                return DispensationGlobaleHelper.this.support(sortie.getMvtSortie().getEssai());
            }
        });
        final List<DispensationGlobale> dispensationsGlobales = new ArrayList<DispensationGlobale>();

        for (final Sortie s : liste) {
            for (final LigneStock l : s.getLignesStockCompletees()) {
                dispensationsGlobales.addAll(this.process(l));
            }
        }
        this.mvtService.saveAll(dispensationsGlobales);
    }

    /**
     * Méthode en charge de répartir sur les dispensations globales
     * correspontantes à la ligne de stock les sorties.
     * @param ligne La ligne de stock.
     * @return Les dispensations globales à mettre à jour.
     */
    private List<DispensationGlobale> process(final LigneStock ligne) {
        final List<DispensationGlobale> toUpdate = new ArrayList<DispensationGlobale>();

        // On recherche toutes les dispensations globales correspondantes aux
        // critères.
        final MvtStockSearchCriteria crit = new MvtStockSearchCriteria();
        crit.setConditionnement(ligne.getConditionnement());
        crit.setDispenseNominativement(false);
        crit.setEssai(ligne.getEssai());
        crit.setNumLot(ligne.getNumLot());
        crit.setProduit(ligne.getProduit());
        crit.setTypeMouvement(TypeMvtStock.DOTATION);
        crit.setNumTraitement(ligne.getNumTraitement());
        crit.setConditionnement(ligne.getConditionnement());

        final List<DispensationGlobale> disps = this.mvtService.getAll(crit);

        // Et on débite dans l'ordre d'apparition des dispensations globales.
        int quantiteLigne = ligne.getQteASortir();
        int i = 0;
        while (quantiteLigne > 0) {
            final DispensationGlobale d = disps.get(i);
            if (d.getQuantiteDispensee() < d.getQuantite()) {

                // si la dispensation contient suffisamment d'unités, on affecte
                // tout sur elle
                if ((d.getQuantiteDispensee() + quantiteLigne) < d.getQuantite()) {
                    d.setQuantiteDispensee(d.getQuantiteDispensee() + quantiteLigne);
                    quantiteLigne = quantiteLigne - ligne.getQteASortir();
                }
                // sinon on affecte le maximum et et on passe à la suivante.
                else {
                    final int nb = d.getQuantite() - d.getQuantiteDispensee();
                    d.setQuantiteDispensee(d.getQuantiteDispensee() + nb);

                    quantiteLigne = quantiteLigne - nb;
                    i++;
                }
                toUpdate.add(d);
            }
        }
        return toUpdate;

    }
    /**
     * Méthode en charge de déterminer si l'essai en paramètre supporte le
     * traitement du helper.
     * @param essai L'essai.
     * @return <true> si l'essai supporte le traitement.
     */
    private boolean support(final Essai essai) {
        final TypeDispensation type = essai.getDetailDonneesPharma().getInfosDispensations().getTypeDispensation();
        return (type != null) && type.equals(TypeDispensation.GLOBALE);
    }
    /**
     * Setter pour mvtService.
     * @param mvtService Le mvtService à écrire.
     */
    public void setMvtService(final MvtStockService<DispensationGlobale> mvtService) {
        this.mvtService = mvtService;
    }

}
