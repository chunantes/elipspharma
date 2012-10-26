package fr.pharma.eclipse.service.patient.impl;

import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.patient.InclusionService;
import fr.pharma.eclipse.service.patient.updator.InclusionBeforeSaveUpdator;

/**
 * Implémentation du service des inclusions.
 
 * @version $Revision$ $Date$
 */
public class InclusionServiceImpl
    extends GenericServiceImpl<Inclusion>
    implements InclusionService
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -7632249830616005519L;

    /**
     * Updator.
     */
    private InclusionBeforeSaveUpdator updator;

    /**
     * @param genericDao
     */
    public InclusionServiceImpl(final GenericDao<Inclusion> genericDao)
    {
        super(genericDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Inclusion save(final Inclusion inclusion)
    {
        if (inclusion.getEssai() == null)
        {
            throw new ValidationException("inclusion.essai",
                                          new String[]
                                          {"notEmpty" },
                                          inclusion);
        }
        if (inclusion.getPatient() == null)
        {
            throw new ValidationException("inclusion.patient",
                                          new String[]
                                          {"notEmpty" },
                                          inclusion);
        }
        if (StringUtils.isBlank(inclusion.getNumInclusion()))
        {
            throw new ValidationException("inclusion.num",
                                          new String[]
                                          {"notEmpty" },
                                          inclusion);
        }

        this.updator.update(inclusion,
                            this);
        return super.save(inclusion);
    }

    /**
     * Setter pour updator.
     * @param updator Le updator à écrire.
     */
    public void setUpdator(final InclusionBeforeSaveUpdator updator)
    {
        this.updator = updator;
    }

}
