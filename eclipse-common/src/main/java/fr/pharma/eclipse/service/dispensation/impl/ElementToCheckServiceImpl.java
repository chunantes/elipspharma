package fr.pharma.eclipse.service.dispensation.impl;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.model.dispensation.ElementToCheck;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.dispensation.ElementToCheckService;

/**
 * Implémentation des services liés aux actes pharma à vérifier.
 
 * @version $Revision$ $Date$
 */
public class ElementToCheckServiceImpl
    extends GenericServiceImpl<ElementToCheck>
    implements ElementToCheckService
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 8945616196187968286L;

    /**
     * Constructeur.
     * @param genericDao Dao.
     */
    public ElementToCheckServiceImpl(final GenericDao<ElementToCheck> genericDao)
    {
        super(genericDao);
    }

}
