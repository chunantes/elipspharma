package fr.pharma.eclipse.service.habilitation.helper;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;

/**
 * Test de la classe HabilitationsHelper.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class HabilitationsHelperTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private HabilitationsHelper helper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.helper = new HabilitationsHelper();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.helper = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.helper);
    }

    /**
     * Test de la méthode getHabilitations - pas de filtre sur le caractère
     * actif.
     */
    @Test
    public void testGetHabilitations() {
        long id = 1;
        final Habilitation habilitation1 = EssaiUtils.makeHabilitationTest(id++, true, Droit.ARC_INVESTIGATEUR, "pers1", TypePersonne.INVESTIGATEUR);
        final Habilitation habilitation2 = EssaiUtils.makeHabilitationTest(id++, true, Droit.CRO, "pers1", TypePersonne.CRO);
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        essai.getDetailContacts().getHabilitations().add(habilitation1);
        essai.getDetailContacts().getHabilitations().add(habilitation2);

        final Collection<Habilitation> res1 = this.helper.getHabilitations(essai, Arrays.asList(habilitation1.getDroit()));
        final Collection<Habilitation> res2 = this.helper.getHabilitations(essai, Arrays.asList(habilitation2.getDroit()));
        Assert.assertEquals(1, res1.size());
        Assert.assertEquals(habilitation1, res1.iterator().next());
        Assert.assertEquals(1, res2.size());
        Assert.assertEquals(habilitation2, res2.iterator().next());

        Assert.assertTrue(this.helper.getHabilitations(essai, Arrays.asList(Droit.PHARMACIEN_EXTERNE)).isEmpty());
    }

    /**
     * Test de la méthode getHabilitations - filtre sur le caractère actif.
     */
    @Test
    public void testGetHabilitationsFiltreSurActif() {
        long id = 1;
        final Habilitation habilitationActive = EssaiUtils.makeHabilitationTest(id++, true, Droit.ARC_INVESTIGATEUR, "pers1", TypePersonne.INVESTIGATEUR);
        final Habilitation habilitationInactive = EssaiUtils.makeHabilitationTest(id++, false, Droit.ARC_INVESTIGATEUR, "pers2", TypePersonne.INVESTIGATEUR);
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        essai.getDetailContacts().getHabilitations().add(habilitationActive);
        essai.getDetailContacts().getHabilitations().add(habilitationInactive);

        final Collection<Habilitation> res1 = this.helper.getHabilitations(essai, Arrays.asList(habilitationActive.getDroit()), true);
        final Collection<Habilitation> res2 = this.helper.getHabilitations(essai, Arrays.asList(habilitationInactive.getDroit()), false);
        Assert.assertEquals(1, res1.size());
        Assert.assertEquals(habilitationActive, res1.iterator().next());
        Assert.assertEquals(1, res2.size());
        Assert.assertEquals(habilitationInactive, res2.iterator().next());
    }

    /**
     * Test de la méthode getInvestigateurPrincipal.
     */
    @Test
    public void testGetInvestigateurPrincipal() {
        long id = 1;
        final Habilitation habilitation1 = EssaiUtils.makeHabilitationTest(id++, true, Droit.INVESTIGATEUR_CO, "pers1", TypePersonne.INVESTIGATEUR);
        final Habilitation habilitation2 = EssaiUtils.makeHabilitationInvPrincipalTest(id++, true, "pers2");
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        essai.getDetailContacts().getHabilitations().add(habilitation1);
        Assert.assertNull(this.helper.getInvestigateurPrincipal(essai));

        essai.getDetailContacts().getHabilitations().add(habilitation2);
        Assert.assertEquals(habilitation2.getPersonne(), this.helper.getInvestigateurPrincipal(essai));
    }

    /**
     * Test de la méthode getInvestigateurPrincipal - inactif.
     */
    @Test
    public void testGetInvestigateurPrincipalInactif() {
        long id = 1;
        final Habilitation habilitation1 = EssaiUtils.makeHabilitationTest(id++, true, Droit.INVESTIGATEUR_CO, "pers1", TypePersonne.INVESTIGATEUR);
        final Habilitation habilitation2 = EssaiUtils.makeHabilitationTest(id++, false, Droit.INVESTIGATEUR_PRINCIPAL, "pers2", TypePersonne.INVESTIGATEUR);
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        essai.getDetailContacts().getHabilitations().add(habilitation1);
        Assert.assertNull(this.helper.getInvestigateurPrincipal(essai));

        essai.getDetailContacts().getHabilitations().add(habilitation2);
        Assert.assertNull(this.helper.getInvestigateurPrincipal(essai));
    }
}
