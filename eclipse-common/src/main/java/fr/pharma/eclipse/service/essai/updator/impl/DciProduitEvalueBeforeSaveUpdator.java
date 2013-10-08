package fr.pharma.eclipse.service.essai.updator.impl;

import org.hibernate.proxy.HibernateProxy;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.essai.updator.EssaiBeforeSaveUpdator;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Implémentation de l'interface {@link EssaiBeforeSaveUpdator} afin de mettre à
 * jour le dci de l'essai.
 * @author Netapsys
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DciProduitEvalueBeforeSaveUpdator implements EssaiBeforeSaveUpdator {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -2070599168852545395L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Essai essai,
                       final EssaiService service) {

        // initialisation.
        essai.setDci(EclipseConstants.NON_APPLICABLE);
        essai.setLibelleProduitEvalue(EclipseConstants.NON_APPLICABLE);

        for (Produit p : essai.getDetailProduit().getMedicaments()) {

            // getMedicaments est une liste de produits et Hibernate
            // les donne des fois en tant que proxy. On converit le proxy en
            // implementation avant le cast vers Medicament
            // (qui plante si p reste un proxy)
            if (p instanceof HibernateProxy) {
                p = (Produit) ((HibernateProxy) p).getHibernateLazyInitializer().getImplementation();
            }

            final Medicament m = (Medicament) p;
            if (m.isProduitEvalue()) {
                if (!essai.getDci().contains(m.getDci())) {
                    if (essai.getDci().contains(EclipseConstants.NON_APPLICABLE)) {
                        essai.setDci(m.getDci());
                    } else {
                        essai.setDci(essai.getDci() + EclipseConstants.COMMA + EclipseConstants.SPACE + m.getDci());
                    }
                }
                if (!essai.getLibelleProduitEvalue().contains(m.getDenomination())) {
                    if (essai.getLibelleProduitEvalue().contains(EclipseConstants.NON_APPLICABLE)) {
                        essai.setLibelleProduitEvalue(m.getDenomination());
                    } else {
                        essai.setLibelleProduitEvalue(essai.getLibelleProduitEvalue() + EclipseConstants.COMMA + EclipseConstants.SPACE + m.getDenomination());
                    }
                }
            }
        }
    }
}
