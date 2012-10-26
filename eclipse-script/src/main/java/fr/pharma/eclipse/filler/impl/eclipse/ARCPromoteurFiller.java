package fr.pharma.eclipse.filler.impl.eclipse;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.ArcPromoteur;
import fr.pharma.eclipse.domain.model.acteur.Promoteur;
import fr.pharma.eclipse.domain.model.sigrec.acteur.ARCPromoteurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.PromoteurSigrec;
import fr.pharma.eclipse.utils.converter.BeanConverter;
import fr.pharma.eclipse.utils.converter.filler.Filler;

/**
 * Filler en charge de populer les arc promoteurs d'un Promoteur à partir d'un PromoteurSigrec.
 
 * @version $Revision$ $Date$
 */
public class ARCPromoteurFiller
    implements Filler<PromoteurSigrec, Promoteur>
{

    /**
     * Converter ARCPromoteurSigrec => ARCPromoteur.
     */
    @Resource(name = "arcPromoteurEclipseConverter")
    private BeanConverter<ARCPromoteurSigrec, ArcPromoteur> converter;

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(final PromoteurSigrec source,
                     final Promoteur destination)
    {
        for (final ARCPromoteurSigrec arc : source.getArcs())
        {
            final ArcPromoteur arcPro = this.converter.convert(arc);
            arcPro.setType(TypePersonne.ARC_PROMOTEUR);
            arcPro.setPromoteur(destination);
            destination.getArcPromoteurs().add(arcPro);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(final PromoteurSigrec source)
    {
        return source != null
               && source.getArcs() != null;
    }

    /**
     * Getter sur converter.
     * @return Retourne le converter.
     */
    public BeanConverter<ARCPromoteurSigrec, ArcPromoteur> getConverter()
    {
        return this.converter;
    }

    /**
     * Setter pour converter.
     * @param converter le converter à écrire.
     */
    public void setConverter(final BeanConverter<ARCPromoteurSigrec, ArcPromoteur> converter)
    {
        this.converter = converter;
    }

}
