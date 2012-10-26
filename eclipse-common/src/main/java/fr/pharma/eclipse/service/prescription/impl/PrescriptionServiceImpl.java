package fr.pharma.eclipse.service.prescription.impl;

import java.util.List;

import javax.annotation.Resource;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.prescription.PrescriptionSearchCriteria;
import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.handler.habilitation.HabilitationHandler;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.prescription.PrescriptionService;
import fr.pharma.eclipse.validator.save.impl.PrescriptionSaveValidator;

/**
 * Implémentation des services liés à la prescription.
 
 * @version $Revision$ $Date$
 */
public class PrescriptionServiceImpl
    extends GenericServiceImpl<Prescription>
    implements PrescriptionService
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 7562214176714485455L;

    /**
     * Validator de sauvegarde de Prescription.
     */
    @Resource(name = "prescriptionSaveValidator")
    private PrescriptionSaveValidator prscriptionSaveValidator;

    /**
     * Gestionnaire d'habilitations sur les prescriptions.
     */
    @Resource(name = "essaiElementHabilitationHandler")
    private HabilitationHandler<Prescription> habilitationHandler;

    /**
     * Constructeur.
     * @param genericDao Dao.
     */
    public PrescriptionServiceImpl(final GenericDao<Prescription> genericDao)
    {
        super(genericDao);
    }

    /**
     * Retourne <true> si la sequence en paramètre est utilisée dans une prescription.
     * @param sequence La séquence.
     * @return <true> si la sequence en paramètre est utilisée dans une prescription.
     */
    public boolean isSequenceUsedInPrescriptions(final Sequence sequence)
    {
        final PrescriptionSearchCriteria criteria = new PrescriptionSearchCriteria();
        criteria.setSequence(sequence);
        return !this.getAll(criteria).isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Prescription save(final Prescription object)
    {
        this.prscriptionSaveValidator.validate(object,
                                               this);
        return this.getGenericDao().save(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Prescription> getAll()
    {
        final List<Prescription> essais = super.getAll();
        // Purge des essais par rapport aux habilitations
        this.habilitationHandler.purge(essais);
        return essais;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Prescription> getAll(final SearchCriteria criteria)
    {
        final List<Prescription> essais = super.getAll(criteria);
        // Purge des essais par rapport aux habilitations
        this.habilitationHandler.purge(essais);
        return essais;
    }

    /**
     * Setter pour prscriptionSaveValidator.
     * @param prscriptionSaveValidator le prscriptionSaveValidator à écrire.
     */
    public void setPrscriptionSaveValidator(final PrescriptionSaveValidator prscriptionSaveValidator)
    {
        this.prscriptionSaveValidator = prscriptionSaveValidator;
    }

    /**
     * Setter pour habilitationHandler.
     * @param habilitationHandler le habilitationHandler à écrire.
     */
    public void setHabilitationHandler(final HabilitationHandler<Prescription> habilitationHandler)
    {
        this.habilitationHandler = habilitationHandler;
    }
}
