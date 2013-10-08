package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation.helper;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.TypeDesignable;
import fr.pharma.eclipse.domain.enums.design.UniteTemps;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanGroupeTraitement;
import fr.pharma.eclipse.domain.model.design.Bras;
import fr.pharma.eclipse.domain.model.design.PrescriptionType;
import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.jasper.constants.JasperConstants;
import fr.pharma.eclipse.service.helper.design.TimeHelper;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe {@link GroupesTraitFillerHelper}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GroupesTraitFillerHelperTest extends AbstractEclipseJUnitTest

{
    /**
     * Helper testé.
     */
    private GroupesTraitFillerHelper helper;

    /**
     * TimeHelper mocké.
     */
    private TimeHelper mockedTimeHelper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedTimeHelper = Mockito.mock(TimeHelper.class);
        this.helper = new GroupesTraitFillerHelper();
        this.helper.setTimeHelper(this.mockedTimeHelper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.helper = null;
        this.mockedTimeHelper = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.helper);
        Assert.assertEquals(this.mockedTimeHelper, this.helper.getTimeHelper());
    }

    /**
     * Test de la méthode transform(fr.pharma.eclipse.domain.model.design.Bras).
     */
    @Test
    public void testTransformBras() {
        final String nom = "nom du bras";
        final Bras bras = new Bras();
        bras.setType(TypeDesignable.BRAS);
        bras.setId(1L);
        bras.setNom(nom);

        final JRBeanGroupeTraitement jrBean = this.helper.transform(bras, 0);
        Assert.assertNotNull(jrBean);
        Assert.assertEquals(nom, jrBean.getDesign());
        Assert.assertEquals(JasperConstants.DEFAULT_FIELD_VALUE, jrBean.getDebut());
        Assert.assertEquals(JasperConstants.DEFAULT_FIELD_VALUE, jrBean.getFin());
        Assert.assertEquals(JasperConstants.DEFAULT_FIELD_VALUE, jrBean.getProduits());
    }

    /**
     * Test de la méthode
     * transform(fr.pharma.eclipse.domain.model.design.Sequence).
     */
    @Test
    public void testTransformSequence() {
        final Conditionnement c = new Conditionnement();
        final String nom = "nom du bras";
        final int nbAlineas = 3;
        long id = 1;
        final Sequence sequence = new Sequence();
        sequence.setType(TypeDesignable.SEQUENCE);
        sequence.setId(id++);
        sequence.setNom(nom);

        final Medicament medic1 = new Medicament();
        final Medicament medic2 = new Medicament();
        medic1.setId(id++);
        medic2.setId(id++);
        medic1.setDenomination("medic1");
        medic2.setDenomination("medic2");
        final PrescriptionType presc1 = new PrescriptionType();
        final PrescriptionType presc2 = new PrescriptionType();
        presc1.setId(id++);
        presc2.setId(id++);
        presc1.setProduit(medic1);
        presc2.setProduit(medic2);
        presc1.setConditionnement(c);
        presc2.setConditionnement(c);

        sequence.getPrescriptions().add(presc1);
        sequence.getPrescriptions().add(presc2);

        final TempsPrescription tpDebut = new TempsPrescription();
        tpDebut.setNb(3);
        tpDebut.setUnite(UniteTemps.MOIS);
        final TempsPrescription tpFin = new TempsPrescription();
        tpFin.setNb(2);
        tpFin.setUnite(UniteTemps.JOUR);

        Mockito.when(this.mockedTimeHelper.getDebut(sequence.getPrescriptions())).thenReturn(tpDebut);
        Mockito.when(this.mockedTimeHelper.getFin(sequence.getPrescriptions())).thenReturn(tpFin);

        final JRBeanGroupeTraitement jrBean = this.helper.transform(sequence, nbAlineas);

        Mockito.verify(this.mockedTimeHelper).getDebut(sequence.getPrescriptions());
        Mockito.verify(this.mockedTimeHelper).getFin(sequence.getPrescriptions());
        Assert.assertNotNull(jrBean);
        Assert.assertEquals(StringUtils.repeat(GroupesTraitFillerHelper.getAlinea(), nbAlineas) + nom, jrBean.getDesign());
        Assert.assertEquals("3m", jrBean.getDebut());
        Assert.assertEquals("2j", jrBean.getFin());
        Assert.assertEquals("medic1; medic2", jrBean.getProduits());
    }
}
