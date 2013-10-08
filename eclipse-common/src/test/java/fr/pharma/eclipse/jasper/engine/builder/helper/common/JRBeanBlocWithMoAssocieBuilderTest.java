package fr.pharma.eclipse.jasper.engine.builder.helper.common;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.StringUtils;

import fr.pharma.eclipse.domain.jasper.model.common.JRBeanBlocWithMoAssocie;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.document.DocumentInformationConditionnement;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Test de la classe {@link JRBeanBlocWithMoAssocieBuilder}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JRBeanBlocWithMoAssocieBuilderTest extends AbstractEclipseJUnitTest {
    /**
     * Helper testé.
     */
    private JRBeanBlocWithMoAssocieBuilder helper;

    /**
     * Titre du bloc de test.
     */
    private static final String TITRE_BLOC = "Conditionnement du produit fini";

    /**
     * Propriété pour le champ presence.
     */
    private static final String PROP_PRESENCE = "detailFaisabilite.infosEtude.conditionnementPduits";

    /**
     * Propriété pour le champ commentaire.
     */
    private static final String PROP_COMMENTAIRE = "detailDonneesPharma.infosDispensations.informationConditionnement";

    /**
     * Propriété pour le champ moAssocie.
     */
    private static final String PROP_MOASSOCIE = "detailDonneesPharma.infosDispensations.documentInformationConditionnement";

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.helper =
            new JRBeanBlocWithMoAssocieBuilder(JRBeanBlocWithMoAssocieBuilderTest.TITRE_BLOC, JRBeanBlocWithMoAssocieBuilderTest.PROP_PRESENCE,
                                               JRBeanBlocWithMoAssocieBuilderTest.PROP_COMMENTAIRE, JRBeanBlocWithMoAssocieBuilderTest.PROP_MOASSOCIE);
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
        Assert.assertEquals(JRBeanBlocWithMoAssocieBuilderTest.TITRE_BLOC, this.helper.getTitreBloc());
        Assert.assertEquals(JRBeanBlocWithMoAssocieBuilderTest.PROP_PRESENCE, this.helper.getEssaiPropertyForPresence());
        Assert.assertEquals(JRBeanBlocWithMoAssocieBuilderTest.PROP_COMMENTAIRE, this.helper.getEssaiPropertyForCommentaire());
        Assert.assertEquals(JRBeanBlocWithMoAssocieBuilderTest.PROP_MOASSOCIE, this.helper.getEssaiPropertyForMoAssocie());
    }

    /**
     * Test de la méthode build(fr.pharma.eclipse.domain.model.essai.Essai) -
     * présence à false.
     */
    @Test
    public void testBuildFalse() {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);

        final JRBeanBlocWithMoAssocie jrBean = this.helper.build(essai);
        Assert.assertNotNull(jrBean);
        Assert.assertEquals(JRBeanBlocWithMoAssocieBuilderTest.TITRE_BLOC, jrBean.getTitre());
        Assert.assertEquals(Boolean.TRUE, jrBean.getShowPresence());
        Assert.assertEquals(Boolean.FALSE, jrBean.getPresence());
        Assert.assertEquals(Boolean.FALSE, jrBean.getHasCommentaire());
        Assert.assertEquals(Boolean.FALSE, jrBean.getHasMoAssocie());
    }

    /**
     * Test de la méthode build(fr.pharma.eclipse.domain.model.essai.Essai) -
     * présence à true 1.
     */
    @Test
    public void testBuildTrue1() {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        BeanTool.setPropriete(essai, JRBeanBlocWithMoAssocieBuilderTest.PROP_PRESENCE, true);
        BeanTool.setPropriete(essai, JRBeanBlocWithMoAssocieBuilderTest.PROP_COMMENTAIRE, "        ");
        BeanTool.setPropriete(essai, JRBeanBlocWithMoAssocieBuilderTest.PROP_MOASSOCIE, null);

        final JRBeanBlocWithMoAssocie jrBean = this.helper.build(essai);
        Assert.assertNotNull(jrBean);
        Assert.assertEquals(JRBeanBlocWithMoAssocieBuilderTest.TITRE_BLOC, jrBean.getTitre());
        Assert.assertEquals(Boolean.TRUE, jrBean.getShowPresence());
        Assert.assertEquals(Boolean.TRUE, jrBean.getPresence());
        Assert.assertEquals(Boolean.FALSE, jrBean.getHasCommentaire());
        Assert.assertEquals(Boolean.FALSE, jrBean.getHasMoAssocie());
    }

    /**
     * Test de la méthode build(fr.pharma.eclipse.domain.model.essai.Essai) -
     * présence à true 2.
     */
    @Test
    public void testBuildTrue2() {
        long id = 1;
        final String commentaire = "Commentaire Commentaire Commentaire Commentaire ";
        final String nomUtilisateur = "monDoc.xls";
        final DocumentInformationConditionnement docAssocie = new DocumentInformationConditionnement();
        docAssocie.setId(id++);
        docAssocie.setNomUtilisateur(nomUtilisateur);
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        BeanTool.setPropriete(essai, JRBeanBlocWithMoAssocieBuilderTest.PROP_PRESENCE, true);
        BeanTool.setPropriete(essai, JRBeanBlocWithMoAssocieBuilderTest.PROP_COMMENTAIRE, commentaire);
        BeanTool.setPropriete(essai, JRBeanBlocWithMoAssocieBuilderTest.PROP_MOASSOCIE, docAssocie);

        final JRBeanBlocWithMoAssocie jrBean = this.helper.build(essai);
        Assert.assertNotNull(jrBean);
        Assert.assertEquals(JRBeanBlocWithMoAssocieBuilderTest.TITRE_BLOC, jrBean.getTitre());
        Assert.assertEquals(Boolean.TRUE, jrBean.getShowPresence());
        Assert.assertEquals(Boolean.TRUE, jrBean.getPresence());
        Assert.assertEquals(Boolean.TRUE, jrBean.getHasCommentaire());
        Assert.assertEquals(Boolean.TRUE, jrBean.getHasMoAssocie());
        Assert.assertEquals(commentaire, jrBean.getCommentaire());
        Assert.assertEquals(nomUtilisateur, jrBean.getMoAssocie());
    }

    /**
     * Test de la méthode build(fr.pharma.eclipse.domain.model.essai.Essai) -
     * présence à true mais constructeur simple.
     */
    @Test
    public void testBuildTrue3() {
        this.helper = new JRBeanBlocWithMoAssocieBuilder(JRBeanBlocWithMoAssocieBuilderTest.TITRE_BLOC, JRBeanBlocWithMoAssocieBuilderTest.PROP_PRESENCE);

        long id = 1;
        final String commentaire = "Commentaire Commentaire Commentaire Commentaire ";
        final String nomUtilisateur = "monDoc.xls";
        final DocumentInformationConditionnement docAssocie = new DocumentInformationConditionnement();
        docAssocie.setId(id++);
        docAssocie.setNomUtilisateur(nomUtilisateur);
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        BeanTool.setPropriete(essai, JRBeanBlocWithMoAssocieBuilderTest.PROP_PRESENCE, true);
        BeanTool.setPropriete(essai, JRBeanBlocWithMoAssocieBuilderTest.PROP_COMMENTAIRE, commentaire);
        BeanTool.setPropriete(essai, JRBeanBlocWithMoAssocieBuilderTest.PROP_MOASSOCIE, docAssocie);

        final JRBeanBlocWithMoAssocie jrBean = this.helper.build(essai);
        Assert.assertNotNull(jrBean);
        Assert.assertEquals(JRBeanBlocWithMoAssocieBuilderTest.TITRE_BLOC, jrBean.getTitre());
        Assert.assertEquals(Boolean.TRUE, jrBean.getShowPresence());
        Assert.assertEquals(Boolean.TRUE, jrBean.getPresence());
        Assert.assertEquals(Boolean.FALSE, jrBean.getHasCommentaire());
        Assert.assertEquals(Boolean.FALSE, jrBean.getHasMoAssocie());
        Assert.assertFalse(StringUtils.hasText(jrBean.getCommentaire()));
        Assert.assertFalse(StringUtils.hasText(jrBean.getMoAssocie()));
    }

    /**
     * Test de la méthode build(fr.pharma.eclipse.domain.model.essai.Essai) -
     * présence à true car propriété non renseignée.
     */
    @Test
    public void testBuildTrue4() {
        // propriété pour présence non renseignée
        this.helper.setEssaiPropertyForPresence("");

        long id = 1;
        final String commentaire = "Commentaire Commentaire Commentaire Commentaire ";
        final String nomUtilisateur = "monDoc.xls";
        final DocumentInformationConditionnement docAssocie = new DocumentInformationConditionnement();
        docAssocie.setId(id++);
        docAssocie.setNomUtilisateur(nomUtilisateur);
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        BeanTool.setPropriete(essai, JRBeanBlocWithMoAssocieBuilderTest.PROP_PRESENCE, true);
        BeanTool.setPropriete(essai, JRBeanBlocWithMoAssocieBuilderTest.PROP_COMMENTAIRE, commentaire);
        BeanTool.setPropriete(essai, JRBeanBlocWithMoAssocieBuilderTest.PROP_MOASSOCIE, docAssocie);

        final JRBeanBlocWithMoAssocie jrBean = this.helper.build(essai);
        Assert.assertNotNull(jrBean);
        Assert.assertEquals(JRBeanBlocWithMoAssocieBuilderTest.TITRE_BLOC, jrBean.getTitre());
        Assert.assertEquals(Boolean.FALSE, jrBean.getShowPresence());
        Assert.assertEquals(Boolean.TRUE, jrBean.getPresence());
        Assert.assertEquals(Boolean.TRUE, jrBean.getHasCommentaire());
        Assert.assertEquals(Boolean.TRUE, jrBean.getHasMoAssocie());
        Assert.assertEquals(commentaire, jrBean.getCommentaire());
        Assert.assertEquals(nomUtilisateur, jrBean.getMoAssocie());
    }
}
