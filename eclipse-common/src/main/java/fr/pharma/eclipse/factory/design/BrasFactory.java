package fr.pharma.eclipse.factory.design;

import fr.pharma.eclipse.domain.enums.TypeDesignable;
import fr.pharma.eclipse.domain.model.design.Bras;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.factory.common.BeanObjectComplexFactory;

/**
 * Factory de Bean Bras.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class BrasFactory extends BeanObjectComplexFactory<Bras> {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3171922165561292902L;

    /**
     * Constructeur.
     * @param bean Classe.
     */
    public BrasFactory(final Class<Bras> bean) {
        super(bean);
    }

    /**
     * Méthode en charge de construire un bras de traitement.
     * @param essai L'essai.
     * @return Le bras.
     */
    public Bras getInitializedObject(final Essai essai) {
        final Bras bras = super.getInitializedObject();
        bras.setType(TypeDesignable.BRAS);
        bras.setDetailDesign(essai.getDetailDesign());
        essai.getDetailDesign().getBras().add(bras);
        return bras;
    }

}
