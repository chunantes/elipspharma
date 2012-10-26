package fr.pharma.eclipse.factory.stock;

import java.util.Calendar;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Description de la classe.
 
 * @version $Revision$ $Date$
 */
public interface ApproFactory<MVT extends Approvisionnement>
{
    MVT getInitializedObject(final Essai essai,
                             final Pharmacie pharmacie,
                             final Calendar dateReception,
                             final Calendar dateArriveeColis);

    MVT getInitializedObject();
}
