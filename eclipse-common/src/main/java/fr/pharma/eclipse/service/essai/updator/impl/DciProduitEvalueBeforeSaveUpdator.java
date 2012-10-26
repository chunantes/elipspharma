package fr.pharma.eclipse.service.essai.updator.impl;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.domain.enums.produit.NatureProduit;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.essai.updator.EssaiBeforeSaveUpdator;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Implémentation de l'interface {@link EssaiBeforeSaveUpdator} afin de mettre à jour le dci de
 * l'essai.
 
 
 * @version $Revision$ $Date$
 */
public class DciProduitEvalueBeforeSaveUpdator
    implements EssaiBeforeSaveUpdator
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -2070599168852545395L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Essai essai,
                       final EssaiService service)
    {
        // on recherche le premier médicament évalué.
        final Collection<Medicament> results =
            CollectionUtils.select(essai.getDetailProduit().getMedicaments(),
                                   new Predicate() {

                                       @Override
                                       public boolean evaluate(final Object object)
                                       {
                                           final Medicament m = (Medicament) object;
                                           return m.getNature() != null
                                                  && m
                                                          .getNature()
                                                          .equals(NatureProduit.PRODUIT_EVALUE)
                                                  && StringUtils.isNotBlank(m.getDci());
                                       }
                                   });
        // initialisation.
        essai.setDci(EclipseConstants.NON_APPLICABLE);
        essai.setLibelleProduitEvalue(EclipseConstants.NON_APPLICABLE);

        // on regarde si des produits evalues sont presents.
        for (final Medicament result : results)
        {

            if (!essai.getDci().contains(result.getDci()))
            {
                if (essai.getDci().contains(EclipseConstants.NON_APPLICABLE))
                {
                    essai.setDci(result.getDci());
                }
                else
                {
                    essai.setDci(essai.getDci()
                                 + EclipseConstants.COMMA
                                 + EclipseConstants.SPACE
                                 + result.getDci());
                }
            }
            if (!essai.getLibelleProduitEvalue().contains(result.getDenomination()))
            {
                if (essai.getLibelleProduitEvalue().contains(EclipseConstants.NON_APPLICABLE))
                {
                    essai.setLibelleProduitEvalue(result.getDenomination());
                }
                else
                {
                    essai.setLibelleProduitEvalue(essai.getLibelleProduitEvalue()
                                                  + EclipseConstants.COMMA
                                                  + EclipseConstants.SPACE
                                                  + result.getDenomination());
                }
            }
        }
    }
}
