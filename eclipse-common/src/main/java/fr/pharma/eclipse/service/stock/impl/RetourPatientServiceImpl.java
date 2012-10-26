package fr.pharma.eclipse.service.stock.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.model.stock.RetourPatient;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.stock.RetourPatientService;
import fr.pharma.eclipse.service.user.UserService;

/**
 * Implémentation du service de gestion des retours patients.
 
 * @version $Revision$ $Date$
 */
public class RetourPatientServiceImpl
    extends GenericServiceImpl<RetourPatient>
    implements RetourPatientService
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -4928265056187357950L;

    /**
     * Service de gestion des utilisateurs.
     */
    @Resource(name = "userService")
    private UserService userService;

    /**
     * Constructeur.
     * @param genericDao Dao.
     */
    public RetourPatientServiceImpl(final GenericDao<RetourPatient> genericDao)
    {
        super(genericDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RetourPatient save(final RetourPatient retourPatient)
    {
        retourPatient.setPersonne(this.userService.getPersonne());
        return super.save(retourPatient);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RetourPatient> save(final List<RetourPatient> retours)
    {
        final List<RetourPatient> result = new ArrayList<RetourPatient>();
        for (final RetourPatient r : retours)
        {
            result.add(this.save(r));
        }
        return result;
    }

    /**
     * Setter pour userService.
     * @param userService le userService à écrire.
     */
    public void setUserService(final UserService userService)
    {
        this.userService = userService;
    }

}
