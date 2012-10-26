package fr.pharma.eclipse.service.surcout.impl;

import java.util.List;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.model.surcout.GrilleModele;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.surcout.GrilleModeleService;
import fr.pharma.eclipse.validator.save.SaveValidator;

/**
 * Implémentaion des services de gestion d'un GrilleModele.
 
 * @version $Revision$ $Date$
 */
public class GrilleModeleServiceImpl
    extends GenericServiceImpl<GrilleModele>
    implements GrilleModeleService
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -7079403464777950502L;

    /**
     * Validator d'enregistrement.
     */
    private List<SaveValidator<GrilleModele>> saveValidators;

    /**
     * @param genericDao
     */
    public GrilleModeleServiceImpl(final GenericDao<GrilleModele> genericDao)
    {
        super(genericDao);
    }

    @Override
    public GrilleModele save(final GrilleModele grille)
    {

        // Validation.
        for (final SaveValidator<GrilleModele> validator : this.saveValidators)
        {
            validator.validate(grille,
                               this);
        }
        return super.save(grille);
    }

    /**
     * Setter pour saveValidators.
     * @param saveValidators le saveValidators à écrire.
     */
    public void setSaveValidators(final List<SaveValidator<GrilleModele>> saveValidators)
    {
        this.saveValidators = saveValidators;
    }

}
