package fr.pharma.eclipse.filler.impl.sigrec.essai;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.domain.model.sigrec.InsuranceType;
import fr.pharma.eclipse.domain.model.sigrec.TrialType;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.detail.AssuranceSigrec;
import fr.pharma.eclipse.utils.converter.BeanConverter;
import fr.pharma.eclipse.utils.converter.filler.Filler;

/**
 * Filler en charge de remplir les informations liées à l'assurance dans l'essai.
 
 * @version $Revision$ $Date$
 */
public class AssuranceFiller
    implements Filler<TrialType, EssaiSigrec>
{
    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(AssuranceFiller.class);

    /**
     * Converter.
     */
    @Resource(name = "assuranceSigrecConverter")
    private BeanConverter<InsuranceType, AssuranceSigrec> converter;

    /**
     * {@inheritDoc}
     */
    @Override
    public void fill(final TrialType source,
                     final EssaiSigrec destination)
    {

        AssuranceSigrec assurance = null;
        // conversion et affectation des assurances.
        for (final InsuranceType insurance : source
                .getRegulatoryInformation()
                .getInsuranceInformation()
                .getInsurance())
        {
            assurance = this.converter.convert(insurance);

            // date de début
            assurance.setDateDebutValidite(insurance
                    .getBeginningValidityDate()
                    .getValue()
                    .toGregorianCalendar());

            // date de fin.
            assurance.setDateFinValidite(insurance
                    .getEndValidityDate()
                    .getValue()
                    .toGregorianCalendar());

            assurance.setEssai(destination);
            destination.getAssurances().add(assurance);

        }

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(final TrialType source)
    {
        return null != source.getRegulatoryInformation()
               && null != source.getRegulatoryInformation().getInsuranceInformation()
               && null != source
                       .getRegulatoryInformation()
                       .getInsuranceInformation()
                       .getInsurance();
    }

    /**
     * Getter sur converter.
     * @return Retourne le converter.
     */
    public BeanConverter<InsuranceType, AssuranceSigrec> getConverter()
    {
        return this.converter;
    }
    /**
     * Setter pour converter.
     * @param converter le converter à écrire.
     */
    public void setConverter(final BeanConverter<InsuranceType, AssuranceSigrec> converter)
    {
        this.converter = converter;
    }

}
