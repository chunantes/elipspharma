package fr.pharma.eclipse.filler.impl.sigrec.promoteur;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import fr.pharma.eclipse.domain.model.sigrec.SponsorType;
import fr.pharma.eclipse.domain.model.sigrec.IntervenantsType.Intervenant;
import fr.pharma.eclipse.domain.model.sigrec.acteur.ARCPromoteurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.PromoteurSigrec;
import fr.pharma.eclipse.predicate.GenericPredicate;
import fr.pharma.eclipse.utils.converter.filler.Filler;
import fr.pharma.eclipse.utils.converter.impl.GenericBeanConverter;

/**
 * Filler en charge de déterminer les ARC promoteurs (intervenants SIGREC du sponsor) et de les
 * ajouter au promoteur.
 
 * @version $Revision$ $Date$
 */
public class ARCFiller
    implements Filler<SponsorType, PromoteurSigrec>
{
    /**
     * Code sigrec de la fonction ARCPromoteur.
     */
    public final static String FONCTION_ARC = "201";

    /**
     * Converter en charge de convertir un intervenantType en ARCSigrec.
     */
    @Resource(name = "arcPromoteurSigrecConverter")
    private GenericBeanConverter<Intervenant, ARCPromoteurSigrec> converter;

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("all")
    public void fill(final SponsorType source,
                     final PromoteurSigrec destination)
    {
        // on filtre les intervenants ayant la fonction ARC.
        final Collection<Intervenant> intervenants =
            CollectionUtils.select(source.getSponsorIntervenants().getIntervenant(),
                                   new GenericPredicate("fonctions.id",
                                                        ARCFiller.FONCTION_ARC));

        ARCPromoteurSigrec arc = null;
        // Conversion et ajout à la liste d'arc promoteurs.
        for (final Intervenant intervenant : intervenants)
        {
            arc = this.converter.convert(intervenant);
            arc.setPromoteur(destination);
            destination.getArcs().add(arc);

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(final SponsorType source)
    {
        return null != source
               && null != source.getSponsorIntervenants();
    }

    /**
     * Getter sur converter.
     * @return Retourne le converter.
     */
    public GenericBeanConverter<Intervenant, ARCPromoteurSigrec> getConverter()
    {
        return this.converter;
    }

    /**
     * Setter pour converter.
     * @param converter le converter à écrire.
     */
    public void setConverter(final GenericBeanConverter<Intervenant, ARCPromoteurSigrec> converter)
    {
        this.converter = converter;
    }

}
