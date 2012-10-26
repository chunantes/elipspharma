package fr.pharma.eclipse.filler.impl.sigrec.essai;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.model.sigrec.OrganizationType;
import fr.pharma.eclipse.domain.model.sigrec.TrialType;
import fr.pharma.eclipse.domain.model.sigrec.acteur.CROSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.utils.converter.filler.Filler;
import fr.pharma.eclipse.utils.converter.impl.GenericBeanConverter;

/**
 * Filler en charge de déterminer les CRO SIGERC et de les ajouter à l'essai.
 
 * @version $Revision$ $Date$
 */
public class CROFiller
    implements Filler<TrialType, EssaiSigrec>
{

    /**
     * Converter en charge de convertir un OrganisationType en CROSigrec.
     */
    @Resource(name = "croSigrecConverter")
    private GenericBeanConverter<OrganizationType, CROSigrec> converter;

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(final TrialType source,
                     final EssaiSigrec destination)
    {

        CROSigrec cro = null;
        // Conversion et ajout à la liste de CROS.
        for (final OrganizationType org : source
                .getRegulatoryInformation()
                .getCro()
                .getOrganization())
        {
            cro = this.converter.convert(org);
            cro.setEssai(destination);
            destination.getCros().add(cro);

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(final TrialType source)
    {
        return null != source.getRegulatoryInformation()
               && null != source.getRegulatoryInformation().getCro();

    }
    /**
     * Getter sur converter.
     * @return Retourne le converter.
     */
    public GenericBeanConverter<OrganizationType, CROSigrec> getConverter()
    {
        return this.converter;
    }

    /**
     * Setter pour converter.
     * @param converter le converter à écrire.
     */
    public void setConverter(final GenericBeanConverter<OrganizationType, CROSigrec> converter)
    {
        this.converter = converter;
    }

}
