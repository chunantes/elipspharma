package fr.pharma.eclipse.component.essai;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.comparator.habilitation.HabilitationComparator;
import fr.pharma.eclipse.domain.criteria.essai.EssaiSearchCriteria;
import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.contact.DetailContacts;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de tester le manager des beans Essais.
 
 * @version $Revision$ $Date$
 */
public class EssaisManagerTest
{

    /**
     * EssaisManager à tester.
     */
    private EssaisManager essaisManager;

    /**
     * Service de gestion des essais mocké.
     */
    @Resource(name = "mockEssaiService")
    private EssaiService mockEssaiService;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init()
    {
        this.essaisManager = new EssaisManager(new EssaiSearchCriteria());
        this.mockEssaiService = Mockito.mock(EssaiService.class);
        this.essaisManager.setEssaiService(this.mockEssaiService);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end()
    {
        this.essaisManager = null;
        this.mockEssaiService = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.essaisManager);
        Assert.assertNotNull(this.mockEssaiService);
    }

    /**
     * Méthode en charge de tester la récupération de l'investigateur principal KO.
     */
    @Test
    public void testGetInvestigateurPrincipalKO()
    {
        final Essai essai = Mockito.mock(Essai.class);
        final DetailContacts detailContacts = Mockito.mock(DetailContacts.class);
        final SortedSet<Habilitation> habilitations =
            new TreeSet<Habilitation>(new HabilitationComparator());
        final Habilitation habilitation = new Habilitation();
        habilitation.setDroit(Droit.DIRECTION_RECHERCHE_ADMINISTRATIF);
        habilitations.add(habilitation);

        Mockito.when(this.mockEssaiService.reattach(essai)).thenReturn(essai);
        Mockito.when(essai.getDetailContacts()).thenReturn(detailContacts);
        Mockito.when(detailContacts.getHabilitations()).thenReturn(habilitations);
        Assert.assertEquals(StringUtils.EMPTY,
                            this.essaisManager.getInvestigateurPrincipal(essai));
    }

    /**
     * Méthode en charge de tester la récupération de l'investigateur principal OK.
     */
    @Test
    public void testGetInvestigateurPrincipalOK()
    {
        final Essai essai = Mockito.mock(Essai.class);
        final DetailContacts detailContacts = Mockito.mock(DetailContacts.class);
        final SortedSet<Habilitation> habilitations =
            new TreeSet<Habilitation>(new HabilitationComparator());
        final Habilitation habilitation = new Habilitation();
        habilitation.setDroit(Droit.INVESTIGATEUR_PRINCIPAL);
        final Investigateur investigateur = new Investigateur();
        investigateur.setNom("nom");
        investigateur.setPrenom("prenom");
        habilitation.setPersonne(investigateur);
        habilitations.add(habilitation);

        Mockito.when(this.mockEssaiService.reattach(essai)).thenReturn(essai);
        Mockito.when(essai.getDetailContacts()).thenReturn(detailContacts);
        Mockito.when(detailContacts.getHabilitations()).thenReturn(habilitations);
        Assert.assertEquals("nom"
                                    + EclipseConstants.SPACE
                                    + "prenom",
                            this.essaisManager.getInvestigateurPrincipal(essai));
    }

}
