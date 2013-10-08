package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.util.StringUtils;

import fr.pharma.eclipse.comparator.common.EclipseListComparator;
import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart4;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.service.habilitation.helper.HabilitationsHelper;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;
import fr.pharma.eclipse.utils.PersonneUtils;

/**
 * Test de la classe {@link AideDispensationPart4PrescripteursFiller}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AideDispensationPart4PrescripteursFillerTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private AideDispensationPart4PrescripteursFiller filler;

    /**
     * Helper mocké pour la gestion des habilitations.
     */
    private HabilitationsHelper mockedHelper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedHelper = Mockito.mock(HabilitationsHelper.class);
        this.filler = new AideDispensationPart4PrescripteursFiller();
        this.filler.setHabilitationsHelper(this.mockedHelper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.filler = null;
        this.mockedHelper = null;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.filler);
        Assert.assertEquals(this.mockedHelper, this.filler.getHabilitationsHelper());
    }

    /**
     * Test de la méthode fill(fr.pharma.eclipse.domain.model.essai.Essai,
     * fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean).
     */
    @Test
    public void testFill() {
        long id = 1;
        final Investigateur investigateurPrincipal = PersonneUtils.makeInvestigateur(id++, "professeur", "jean", "valjean");
        final Investigateur investigateurCo1 = PersonneUtils.makeInvestigateur(id++, "monsieur", "andré", "titi");
        final Investigateur investigateurCo2 = PersonneUtils.makeInvestigateur(id++, "madame", "jeanne", "valjean");
        final SortedSet<Habilitation> expectedHabilitations = new TreeSet<Habilitation>(new EclipseListComparator());

        expectedHabilitations.add(EssaiUtils.makeHabilitationTest(id++, true, Droit.INVESTIGATEUR_CO, investigateurCo1));
        expectedHabilitations.add(EssaiUtils.makeHabilitationTest(id++, true, Droit.INVESTIGATEUR_CO, investigateurCo2));

        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        Mockito.when(this.mockedHelper.getInvestigateurPrincipal(essai)).thenReturn(investigateurPrincipal);
        Mockito.when(this.mockedHelper.getHabilitations(Matchers.any(Essai.class), Matchers.anyListOf(Droit.class)))
                .thenAnswer(this.prepareHabilitationsCoAnswer(essai, expectedHabilitations));

        final JRBeanFicheAideDispensationPart4 partie4 = new JRBeanFicheAideDispensationPart4();
        Assert.assertFalse(StringUtils.hasText(partie4.getInvestigateurPrincipal()));
        Assert.assertFalse(StringUtils.hasText(partie4.getCoInvestigateurs()));

        this.filler.fill(essai, partie4);

        Mockito.verify(this.mockedHelper).getInvestigateurPrincipal(essai);
        Mockito.verify(this.mockedHelper).getHabilitations(Matchers.any(Essai.class), Matchers.anyListOf(Droit.class));
        Assert.assertEquals("professeur jean valjean", partie4.getInvestigateurPrincipal());
        Assert.assertEquals("monsieur andré titi; madame jeanne valjean", partie4.getCoInvestigateurs());
    }
    /**
     * Méthode en charge de préparer la réponse lors de l'appel à la méthode<br>
     * mockedHelper.getHabilitations(Matchers.any(Essai.class),
     * Matchers.anyListOf(Droit.class)).
     * @param expectedEssai Essai attendu dans les paramètres.
     * @param expectedHabilitations Habilitations de co-investigateurs
     * attendues.
     * @return La réponse à l'appel.
     */
    private Answer<SortedSet<Habilitation>> prepareHabilitationsCoAnswer(final Essai expectedEssai,
                                                                         final SortedSet<Habilitation> expectedHabilitations) {
        return new Answer<SortedSet<Habilitation>>() {

            @SuppressWarnings("unchecked")
            @Override
            public SortedSet<Habilitation> answer(final InvocationOnMock invocation) throws Throwable {
                int i = 0;
                final Essai argEssai = (Essai) invocation.getArguments()[i++];
                final Collection<Droit> argDroits = (Collection<Droit>) invocation.getArguments()[i++];
                Assert.assertEquals(expectedEssai, argEssai);
                Assert.assertEquals(1, argDroits.size());
                Assert.assertEquals(Droit.INVESTIGATEUR_CO, argDroits.iterator().next());
                return expectedHabilitations;
            }
        };
    }
}
