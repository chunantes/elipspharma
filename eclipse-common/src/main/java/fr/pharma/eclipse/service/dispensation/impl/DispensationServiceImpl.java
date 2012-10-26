package fr.pharma.eclipse.service.dispensation.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.handler.habilitation.HabilitationHandler;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.dispensation.DispensationService;
import fr.pharma.eclipse.service.stockage.PharmacieService;
import fr.pharma.eclipse.validator.save.impl.DispensationSaveValidator;

/**
 * Implémentation des services liés à la Dispensation.
 
 * @version $Revision$ $Date$
 */
public class DispensationServiceImpl
    extends GenericServiceImpl<Dispensation>
    implements DispensationService
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 7562214176714485455L;

    /**
     * Validator de sauvegarde de Prescription.
     */
    @Resource(name = "dispensationSaveValidator")
    private DispensationSaveValidator dispensationSaveValidator;

    /**
     * Gestionnaire d'habilitations sur les dispensations.
     */
    @Resource(name = "essaiElementHabilitationHandler")
    private HabilitationHandler<Dispensation> habilitationHandler;

    /**
     * Service pharmacie.
     */
    @Resource(name = "pharmacieService")
    private PharmacieService pharmacieService;

    /**
     * Constructeur.
     * @param genericDao Dao.
     */
    public DispensationServiceImpl(final GenericDao<Dispensation> genericDao)
    {
        super(genericDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dispensation save(final Dispensation object)
    {
        // si c'est une action de dispensation et pas de sauvegarde simple, on valide la
        // dispensation.
        if (object.getDispense())
        {
            this.dispensationSaveValidator.validate(object,
                                                    this);
        }
        object.setDateDispensation(Calendar.getInstance(Locale.FRANCE));
        return this.getGenericDao().save(object);
    }

    public void validRemoveProduitPrescrit(final ProduitPrescrit p)
    {
        if (!p.getDispensationProduits().isEmpty())
        {
            throw new ValidationException("prescriptions.produits",
                                          new String[]
                                          {"notEmpty" },
                                          null);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Dispensation genererNumOrdonnancier(final Dispensation object)
    {
        final Pharmacie pharmacie = object.getPharmacie();

        Integer numOrdonnancier = pharmacie.getNumOrdonnancierDisp();
        object.setNumOrdonnancier(++numOrdonnancier);
        pharmacie.setNumOrdonnancierDisp(numOrdonnancier);

        this.pharmacieService.save(pharmacie);
        return this.save(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Dispensation> getAll()
    {
        final List<Dispensation> essais = super.getAll();
        // Purge des essais par rapport aux habilitations
        this.habilitationHandler.purge(essais);
        return essais;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Dispensation> getAll(final SearchCriteria criteria)
    {
        final List<Dispensation> essais = super.getAll(criteria);
        // Purge des essais par rapport aux habilitations
        this.habilitationHandler.purge(essais);
        return essais;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Dispensation> getAllWithoutPurge(final SearchCriteria criteria)
    {
        final List<Dispensation> essais = super.getAll(criteria);
        return essais;
    }

    /**
     * Setter pour dispensationSaveValidator.
     * @param dispensationSaveValidator le dispensationSaveValidator à écrire.
     */
    public void setDispensationSaveValidator(final DispensationSaveValidator dispensationSaveValidator)
    {
        this.dispensationSaveValidator = dispensationSaveValidator;
    }

    /**
     * Setter pour habilitationHandler.
     * @param habilitationHandler le habilitationHandler à écrire.
     */
    public void setHabilitationHandler(final HabilitationHandler<Dispensation> habilitationHandler)
    {
        this.habilitationHandler = habilitationHandler;
    }

    /**
     * Setter pour pharmacieService.
     * @param pharmacieService Le pharmacieService à écrire.
     */
    public void setPharmacieService(final PharmacieService pharmacieService)
    {
        this.pharmacieService = pharmacieService;
    }

}
