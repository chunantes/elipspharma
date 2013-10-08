package fr.pharma.eclipse.component.helper;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.service.helper.DroitAccesHelper;
import fr.pharma.eclipse.service.user.UserService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du helper DroitAccesHelper.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DroitAccesHelperTest extends AbstractEclipseJUnitTest {

    /**
     * Helper.
     */
    private DroitAccesHelper helper;

    /**
     * Service user.
     */
    private UserService userService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.userService = Mockito.mock(UserService.class);
        this.helper = new DroitAccesHelper();
        this.helper.setUserService(this.userService);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.userService = null;
        this.helper = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.userService);
        Assert.assertNotNull(this.helper);
    }

}
