package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.jasperreports.engine.JRDataSource;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart1;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanGroupeTraitement;
import fr.pharma.eclipse.domain.model.design.Bras;
import fr.pharma.eclipse.domain.model.design.Designable;
import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.jasper.engine.factory.JRDataSourceFactory;
import fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation.helper.GroupesTraitFillerHelper;
import fr.pharma.eclipse.service.helper.design.DesignHelper;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;

/**
 * Test de la classe {@link AideDispensationPart1GroupesTraitFiller}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AideDispensationPart1GroupesTraitFillerTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private AideDispensationPart1GroupesTraitFiller filler;

    /**
     * Fabrique mockée.
     */
    private JRDataSourceFactory mockedFactory;

    /**
     * Helper de design mocké.
     */
    private DesignHelper mockedDesignHelper;

    /**
     * Helper mocké.
     */
    private GroupesTraitFillerHelper mockedHelper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedFactory = Mockito.mock(JRDataSourceFactory.class);
        this.mockedDesignHelper = Mockito.mock(DesignHelper.class);
        this.mockedHelper = Mockito.mock(GroupesTraitFillerHelper.class);
        this.filler = new AideDispensationPart1GroupesTraitFiller();
        this.filler.setDesignHelper(this.mockedDesignHelper);
        this.filler.setHelper(this.mockedHelper);
        this.filler.setJrDataSourceFactory(this.mockedFactory);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.filler = null;
        this.mockedFactory = null;
        this.mockedDesignHelper = null;
        this.mockedHelper = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.filler);
        Assert.assertEquals(this.mockedDesignHelper, this.filler.getDesignHelper());
        Assert.assertEquals(this.mockedFactory, this.filler.getJrDataSourceFactory());
        Assert.assertEquals(this.mockedHelper, this.filler.getHelper());
    }

    /**
     * Test de la classe fill(fr.pharma.eclipse.domain.model.essai.Essai,
     * fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean).
     */
    @Test
    public void testFill() {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final JRBeanFicheAideDispensationPart1 source = new JRBeanFicheAideDispensationPart1();
        final JRDataSource expectedDataSource = Mockito.mock(JRDataSource.class);

        final Bras brasRoot1 = new Bras();
        final Bras brasRoot2 = new Bras();
        final Sequence sequence = new Sequence();
        brasRoot1.setId(id++);
        brasRoot2.setId(id++);
        sequence.setId(id++);
        brasRoot1.setNom("brasRoot1");
        brasRoot2.setNom("brasRoot2");
        sequence.setNom("sequence");

        brasRoot1.getSequences().add(sequence);
        sequence.setParent(brasRoot1);

        final Set<Designable> roots = new HashSet<Designable>();
        roots.add(brasRoot1);
        roots.add(brasRoot2);
        Mockito.when(this.mockedDesignHelper.getDesignRoots(essai)).thenReturn(roots);
        Mockito.when(this.mockedHelper.transform(Matchers.any(Designable.class), Matchers.anyInt())).thenReturn(new JRBeanGroupeTraitement());
        Mockito.when(this.mockedFactory.getInitializedObject(Matchers.anyCollectionOf(JRBeanGroupeTraitement.class))).thenAnswer(new Answer<JRDataSource>() {

            @SuppressWarnings("unchecked")
            @Override
            public JRDataSource answer(final InvocationOnMock invocation) throws Throwable {
                final Collection<JRBeanGroupeTraitement> argColl = (Collection<JRBeanGroupeTraitement>) invocation.getArguments()[0];
                Assert.assertNotNull(argColl);
                Assert.assertEquals(3, argColl.size());
                return expectedDataSource;
            }
        });

        Assert.assertNull(source.getGroupesTraitement());
        this.filler.fill(essai, source);

        Mockito.verify(this.mockedDesignHelper).getDesignRoots(essai);
        Mockito.verify(this.mockedHelper).transform(brasRoot1, 0);
        Mockito.verify(this.mockedHelper).transform(brasRoot2, 0);
        Mockito.verify(this.mockedHelper).transform(sequence, 1);
        Assert.assertEquals(expectedDataSource, source.getGroupesTraitement());

    }
}
