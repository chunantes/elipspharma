package fr.pharma.eclipse.factory.stock;

import java.util.Calendar;

import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Factory de Bean Approvisionnement.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ApprovisionnementFactory extends MvtStockFactory<Approvisionnement> implements ApproFactory<Approvisionnement> {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -1206837232672178736L;

    /**
     * Constructeur.
     * @param bean Classe.
     */
    public ApprovisionnementFactory(final Class<Approvisionnement> bean) {
        super(bean, TypeMvtStock.APPROVISIONNEMENT);
    }

    /**
     * Constructeur.
     * @param bean Classe.
     * @param typeMvtStock type de mouvement.
     */
    public ApprovisionnementFactory(final Class<Approvisionnement> bean, final TypeMvtStock typeMvtStock) {
        super(bean, typeMvtStock);

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Approvisionnement getInitializedObject() {
        final Approvisionnement appro = super.getInitializedObject();
        appro.setApproApprouve(Boolean.TRUE);
        appro.setExtensionPeremption(Boolean.FALSE);
        return appro;
    }

    /**
     * Méthode en charge d'initialiser un Approvisionnement avec un essai, une
     * pharmacie, une date de réception et une date d'arrivée de colis.
     * @param essai Essai.
     * @param pharmacie Pharmacie.
     * @param dateReception Date de réception.
     * @param dateArriveeColis Date d'arrivée du colis.
     * @return Approvisionnement.
     */
    @Override
    public Approvisionnement getInitializedObject(final Essai essai,
                                                  final Pharmacie pharmacie,
                                                  final Calendar dateReception,
                                                  final Calendar dateArriveeColis) {
        final Approvisionnement appro = this.getInitializedObject();
        appro.setEssai(essai);
        appro.setPharmacie(pharmacie);
        appro.setDateReception(dateReception);
        appro.setDateArriveeColis(dateArriveeColis);
        return appro;
    }

}
