package fr.pharma.eclipse.factory.stock;

import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.factory.common.BeanObjectFactory;

/**
 * Factory de bean Pharmacie.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PharmacieFactory extends BeanObjectFactory<Pharmacie> {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -1339058981863889440L;

    /**
     * Constructeur.
     * @param bean Classe.
     */
    public PharmacieFactory(final Class<Pharmacie> bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pharmacie getInitializedObject() {
        final Pharmacie pharmacie = super.getInitializedObject();
        pharmacie.setNumOrdonnancierDisp(0);
        pharmacie.setNumOrdonnancierFab(0);
        return pharmacie;
    }

}
