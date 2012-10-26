package fr.pharma.eclipse.processor.helper;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.sigrec.MultiTrialType;
import fr.pharma.eclipse.domain.model.sigrec.SigrecMultiTrialsTransfert;
import fr.pharma.eclipse.domain.model.sigrec.SponsorInformationType;
import fr.pharma.eclipse.domain.model.sigrec.SponsorType;
import fr.pharma.eclipse.domain.model.sigrec.TrialType;

/**
 * Classe de test du helper : SponsorExtractorHelper.
 
 * @version $Revision$ $Date$
 */
public class SponsorExtractorHelperTest
{
    /**
     * Helper à tester.
     */
    private SponsorExtractorHelper helper;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void setUp()
    {
        this.helper = new SponsorExtractorHelper();
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void tearDown()
    {
        this.helper = null;
    }

    /**
     * Test de la méthode extract pour aucun essais.
     */
    @Test
    public void testExtractZeroElement()
    {
        final SigrecMultiTrialsTransfert grappe = new SigrecMultiTrialsTransfert();
        grappe.setMultiTrial(new MultiTrialType());
        final List<SponsorType> sponsors = this.helper.extract(grappe);
        Assert.assertEquals(0,
                            sponsors.size());
    }

    /**
     * Test de la méthode extract.
     */
    @Test
    public void testExtract()
    {
        final SigrecMultiTrialsTransfert grappe = new SigrecMultiTrialsTransfert();
        grappe.setMultiTrial(new MultiTrialType());
        final TrialType t1 = new TrialType();
        t1.setSponsorInformation(new SponsorInformationType());
        t1.getSponsorInformation().getSponsor().add(new SponsorType());
        final TrialType t2 = new TrialType();
        t2.setSponsorInformation(new SponsorInformationType());
        t2.getSponsorInformation().getSponsor().add(new SponsorType());
        t2.getSponsorInformation().getSponsor().add(new SponsorType());
        grappe.getMultiTrial().getTrial().add(t1);
        grappe.getMultiTrial().getTrial().add(t2);
        final List<SponsorType> sponsors = this.helper.extract(grappe);
        Assert.assertEquals(3,
                            sponsors.size());
    }
}
