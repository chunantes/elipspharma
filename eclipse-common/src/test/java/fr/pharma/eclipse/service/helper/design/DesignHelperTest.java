package fr.pharma.eclipse.service.helper.design;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.design.Bras;
import fr.pharma.eclipse.domain.model.design.Designable;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;

/**
 * Test de la classe DesignHelper.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DesignHelperTest extends AbstractEclipseJUnitTest {
    /**
     * Helper testé.
     */
    private DesignHelper helper;

    /**
     * Service essai.
     */
    private EssaiService essaiService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.essaiService = Mockito.mock(EssaiService.class);
        this.helper = new DesignHelper();
        this.helper.setEssaiService(this.essaiService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.helper = null;
        this.essaiService = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.essaiService);
        Assert.assertNotNull(this.helper);
    }

    /**
     * Test de la méthode
     * getDesignRoots(fr.pharma.eclipse.domain.model.essai.Essai).
     */
    @Test
    public void testGetDesignRoots() {
        long id = 1;
        final Bras brasRoot1 = new Bras();
        final Bras brasRoot2 = new Bras();
        brasRoot1.setNom("brasRoot1");
        final Bras bras2 = new Bras();
        brasRoot2.setNom("brasRoot2");
        final Bras bras3 = new Bras();
        bras3.setNom("bras3");

        brasRoot1.setId(id++);
        brasRoot2.setId(id++);
        bras2.setId(id++);
        bras3.setId(id++);

        brasRoot1.getSousBras().add(bras2);
        bras2.setParent(brasRoot1);
        bras2.getSousBras().add(bras3);
        bras3.setParent(bras2);
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        essai.getDetailDesign().getBras().add(brasRoot1);
        essai.getDetailDesign().getBras().add(brasRoot2);
        essai.getDetailDesign().getBras().add(bras2);
        essai.getDetailDesign().getBras().add(bras3);

        Mockito.when(this.essaiService.reattach(Matchers.any(Essai.class))).thenReturn(essai);

        final Set<Designable> brasRoots = this.helper.getDesignRoots(essai.getDetailDesign());
        Assert.assertNotNull(brasRoots);
        Assert.assertEquals(2, brasRoots.size());

        final Collection<? extends Object> rootIds = new ArrayList<Designable>(brasRoots);
        CollectionUtils.transform(rootIds, new Transformer() {

            @Override
            public Object transform(final Object input) {
                return ((Bras) input).getId();
            }
        });
        Assert.assertTrue(rootIds.contains(brasRoot1.getId()));
        Assert.assertTrue(rootIds.contains(brasRoot2.getId()));
    }
}
