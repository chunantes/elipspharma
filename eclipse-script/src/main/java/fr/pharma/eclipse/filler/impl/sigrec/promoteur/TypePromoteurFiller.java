package fr.pharma.eclipse.filler.impl.sigrec.promoteur;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.domain.enums.TypePromoteur;
import fr.pharma.eclipse.domain.model.sigrec.SponsorType;
import fr.pharma.eclipse.domain.model.sigrec.acteur.PromoteurSigrec;
import fr.pharma.eclipse.utils.converter.filler.Filler;

/**
 * Description de la classe.
 
 * @version $Revision$ $Date$
 */
public class TypePromoteurFiller
    implements Filler<SponsorType, PromoteurSigrec>
{
    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(TypePromoteurFiller.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(final SponsorType source,
                     final PromoteurSigrec destination)
    {
        TypePromoteur type = null;
        if (source.getSponsorType().getTypeId().intValue() == 1)
        {
            type = TypePromoteur.ACADEMIQUE;
        }
        else if (source.getSponsorType().getTypeId().intValue() == 2)
        {
            type = TypePromoteur.INDUSTRIEL;
        }
        else
        {
            this.log.info("Aucune information sur le type de promoteur.");
        }
        destination.setType(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(final SponsorType source)
    {
        return null != source.getSponsorType();
    }
}
