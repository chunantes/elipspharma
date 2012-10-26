package fr.pharma.eclipse.helper;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.service.helper.DroitAccesHelper;
import fr.pharma.eclipse.service.user.UserService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe QualiteInsuHelper.
 
 * @version $Revision$ $Date$
 */
public class QualiteInsuHelperTest
    extends AbstractEclipseJUnitTest
{
    /**
     * Helper.
     */
    private DroitAccesHelper helper;

    /**
     * Service.
     */
    private UserService service;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp()
    {
        this.service = Mockito.mock(UserService.class);
        this.helper = new DroitAccesHelper();
        this.helper.setUserService(this.service);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown()
    {
        this.service = null;
        this.helper = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.service);
        Assert.assertNotNull(this.helper);
    }

}
