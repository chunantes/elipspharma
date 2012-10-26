package fr.pharma.eclipse.filler.impl.sigrec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.domain.enums.Civilite;
import fr.pharma.eclipse.domain.model.sigrec.IntervenantsType.Intervenant;
import fr.pharma.eclipse.domain.model.sigrec.common.IntervenantSigrec;
import fr.pharma.eclipse.utils.converter.filler.Filler;

/**
 * Filler en charge de déterminer l'a valeur de l'enum Civilite et de l'insérer dans le bean
 * métier IntervenantSigrec en fonction de la civilité de l'intervenant SIGREC.
 
 * @version $Revision$ $Date$
 */
public class CiviliteFiller
    implements Filler<Intervenant, IntervenantSigrec>
{

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(CiviliteFiller.class);

    /**
     * Monsieur.
     */
    public static final String MR = "Mr";

    /**
     * Madame.
     */
    public static final String MME = "Mme";

    /**
     * Mademoiselle.
     */
    public static final String MLLE = "Mlle";

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(final Intervenant source,
                     final IntervenantSigrec destination)
    {
        if (source.getCivilite().equals(CiviliteFiller.MR))
        {
            destination.getContact().setCivilite(Civilite.MR);
        }
        else if (source.getCivilite().equals(CiviliteFiller.MME))
        {

            destination.getContact().setCivilite(Civilite.MME);
        }
        else if (source.getCivilite().equals(CiviliteFiller.MLLE))
        {

            destination.getContact().setCivilite(Civilite.MLLE);
        }
        else
        {
            this.log.info("Aucune information sur le type de la recherche.");
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(final Intervenant source)
    {
        return null != source
               && null != source.getCivilite();
    }
}
