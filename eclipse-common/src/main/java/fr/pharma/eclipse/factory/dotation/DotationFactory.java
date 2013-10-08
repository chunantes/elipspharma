package fr.pharma.eclipse.factory.dotation;

import fr.pharma.eclipse.domain.model.dotation.Dotation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.factory.common.BeanObjectFactory;

/**
 * Factory de Bean Dotation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DotationFactory extends BeanObjectFactory<Dotation> {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4646816131364363603L;

    /**
     * Constructeur.
     * @param bean Classe.
     */
    public DotationFactory(final Class<Dotation> bean) {
        super(bean);
    }

    /**
     * Méthode en charge d'initialiser une Dotation avec un service.
     * @param essai Essai.
     * @param service Service.
     * @return Dotation.
     */
    public Dotation getInitializedObject(final Essai essai,
                                         final Service service) {
        final Dotation dotation = this.getInitializedObject();
        dotation.setEssai(essai);
        dotation.setPharmacie(essai.getPharmaciePrincipale());
        dotation.setTraitee(Boolean.FALSE);
        dotation.setService(service);
        return dotation;
    }

}
