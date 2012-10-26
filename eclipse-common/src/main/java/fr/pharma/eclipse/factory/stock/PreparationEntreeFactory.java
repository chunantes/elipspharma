package fr.pharma.eclipse.factory.stock;

import java.util.Calendar;

import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.stock.PreparationEntree;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Factory de Bean Approvisionnement.
 
 * @version $Revision$ $Date$
 */
public class PreparationEntreeFactory
    extends MvtStockFactory<PreparationEntree>
    implements ApproFactory<PreparationEntree>
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -1206837232672178736L;

    /**
     * Constructeur.
     * @param bean Classe.
     */
    public PreparationEntreeFactory(final Class<PreparationEntree> bean)
    {
        super(bean,
              TypeMvtStock.PREPARATION_ENTREE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PreparationEntree getInitializedObject()
    {
        final PreparationEntree appro = super.getInitializedObject();
        appro.setApproApprouve(Boolean.TRUE);
        appro.setExtensionPeremption(Boolean.FALSE);
        return appro;
    }

    /**
     * Méthode en charge d'initialiser un Approvisionnement avec un essai, une pharmacie, une date
     * de réception et une date d'arrivée de colis.
     * @param essai Essai.
     * @param pharmacie Pharmacie.
     * @param dateReception Date de réception.
     * @param dateArriveeColis Date d'arrivée du colis.
     * @return Approvisionnement.
     */
    public PreparationEntree getInitializedObject(final Essai essai,
                                                  final Pharmacie pharmacie,
                                                  final Calendar dateReception,
                                                  final Calendar dateArriveeColis)
    {
        final PreparationEntree appro = this.getInitializedObject();
        appro.setEssai(essai);
        appro.setPharmacie(pharmacie);
        appro.setDateReception(dateReception);
        appro.setDateArriveeColis(dateArriveeColis);
        return appro;
    }

}
