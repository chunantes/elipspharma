package fr.pharma.eclipse.component.essai;

import java.text.ParseException;
import java.util.Calendar;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.comparator.suivi.SuiviComparator;
import fr.pharma.eclipse.domain.enums.TypeCommentaireEssai;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.recherche.CommentaireEssaiRecherche;
import fr.pharma.eclipse.domain.model.essai.detail.recherche.DetailRecherche;
import fr.pharma.eclipse.factory.common.BeanObjectWithParentFactory;
import fr.pharma.eclipse.utils.EssaiUtils;
import fr.pharma.eclipse.utils.Utils;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Test de la classe GenericCommentaireManager.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GenericCommentaireManagerTest {
    /**
     * Classe testée.
     */
    private GenericCommentaireManager<CommentaireEssaiRecherche, DetailRecherche> manager;

    /**
     * Fabrique mockée.
     */
    private BeanObjectWithParentFactory<CommentaireEssaiRecherche, DetailRecherche> mockedFactory;

    /**
     * Type de commentaire associé au manager.
     */
    private static final TypeCommentaireEssai TYPE_COMMENTAIRE = TypeCommentaireEssai.RECHERCHE;

    /**
     * Méthode d'initialisation.
     */
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        this.mockedFactory = Mockito.mock(BeanObjectWithParentFactory.class);
        this.manager = new GenericCommentaireManager(GenericCommentaireManagerTest.TYPE_COMMENTAIRE.name());
        this.manager.setFactory(this.mockedFactory);
    }

    /**
     * Méthode de finalisation.
     */
    @After
    public void tearDown() {
        this.manager = null;
        this.mockedFactory = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.mockedFactory);
        Assert.assertEquals(GenericCommentaireManagerTest.TYPE_COMMENTAIRE, this.manager.getTypeCommentaire());
    }

    /**
     * Test de la méthode canCreateCommentaire.
     */
    @Test
    public void testCanCreateCommentaire() {
        Assert.assertFalse(this.manager.canCreateCommentaire());

        final String libelle = "libellé du commentaire";
        this.manager.setLibelle(libelle);
        Assert.assertTrue(this.manager.canCreateCommentaire());
    }

    /**
     * Test de la méthode createCommentaire.
     */
    @Test
    public void testCreateCommentaire() {
        final String libelle = "  libellé du commentaire    ";
        this.manager.setLibelle(libelle);

        final Essai essai = EssaiUtils.makeEssaiTest(1);
        final DetailRecherche detailRecherche = Mockito.mock(DetailRecherche.class);
        essai.setDetailRecherche(detailRecherche);
        final CommentaireEssaiRecherche expectedCommentaire = new CommentaireEssaiRecherche();
        expectedCommentaire.setId(1L);
        expectedCommentaire.setDateMaj(Calendar.getInstance(EclipseConstants.LOCALE));
        expectedCommentaire.setMajPar("SRM");
        Mockito.when(this.mockedFactory.getInitializedObject(detailRecherche)).thenReturn(expectedCommentaire);
        final CommentaireEssaiRecherche commentaireResult = this.manager.createCommentaire(essai);
        Mockito.verify(this.mockedFactory).getInitializedObject(detailRecherche);
        Assert.assertEquals(expectedCommentaire.getId(), commentaireResult.getId());
        Assert.assertEquals(expectedCommentaire.getMajPar(), commentaireResult.getMajPar());
        Assert.assertEquals(expectedCommentaire.getDateMaj(), commentaireResult.getDateMaj());
        Assert.assertEquals(libelle.trim(), commentaireResult.getLibelle());
        Assert.assertEquals(StringUtils.EMPTY, this.manager.getLibelle());
    }

    /**
     * Test de la méthode initLastCommentaire - cas de liste vide.
     */
    @Test
    public void testInitLastCommentaireEmptyList() {
        long ids = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(ids++);
        final CommentaireEssaiRecherche initialLastCommentaire = Mockito.mock(CommentaireEssaiRecherche.class);
        this.manager.setLastCommentaire(initialLastCommentaire);
        Assert.assertTrue(essai.getDetailRecherche().getCommentaires().isEmpty());
        Assert.assertEquals(initialLastCommentaire, this.manager.getLastCommentaire());

        this.manager.initLastCommentaire(essai);
        Assert.assertNull(this.manager.getLastCommentaire());
    }

    /**
     * Test de la méthode initLastCommentaire - cas de liste non vide.
     * @throws ParseException En cas d'erreur de préparation des jeux de
     * données.
     */
    @Test
    public void testInitLastCommentaire() throws ParseException {
        long ids = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(ids++);
        final CommentaireEssaiRecherche initialLastCommentaire = Mockito.mock(CommentaireEssaiRecherche.class);
        this.manager.setLastCommentaire(initialLastCommentaire);
        Assert.assertTrue(essai.getDetailRecherche().getCommentaires().isEmpty());
        Assert.assertEquals(initialLastCommentaire, this.manager.getLastCommentaire());

        final SortedSet<CommentaireEssaiRecherche> commentaires = new TreeSet<CommentaireEssaiRecherche>(new SuiviComparator());
        final CommentaireEssaiRecherche expectedCommentaire = EssaiUtils.makeCommentaireTest(ids++, Utils.parseDate("01/11/2010", EclipseConstants.PATTERN_SIMPLE));
        commentaires.add(EssaiUtils.makeCommentaireTest(ids++, Utils.parseDate("01/03/2010", EclipseConstants.PATTERN_SIMPLE)));
        commentaires.add(EssaiUtils.makeCommentaireTest(ids++, Utils.parseDate("01/01/2010", EclipseConstants.PATTERN_SIMPLE)));
        commentaires.add(expectedCommentaire);
        essai.getDetailRecherche().getCommentaires().addAll(commentaires);

        this.manager.initLastCommentaire(essai);
        Assert.assertNotNull(this.manager.getLastCommentaire());
        Assert.assertEquals(expectedCommentaire, this.manager.getLastCommentaire());

    }

}
