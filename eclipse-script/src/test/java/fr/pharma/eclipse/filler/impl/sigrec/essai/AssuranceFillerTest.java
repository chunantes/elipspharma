package fr.pharma.eclipse.filler.impl.sigrec.essai;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.sigrec.InsuranceType;
import fr.pharma.eclipse.domain.model.sigrec.RegulatoryInformationType;
import fr.pharma.eclipse.domain.model.sigrec.TrialType;
import fr.pharma.eclipse.domain.model.sigrec.RegulatoryInformationType.InsuranceInformation;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.detail.AssuranceSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.detail.PrevisionSigrec;
import fr.pharma.eclipse.utils.converter.BeanConverter;

/**
 * Test du filler : AssuranceFiller.
 
 * @version $Revision$ $Date$
 */
public class AssuranceFillerTest
{
    /**
     * Filler à tester.
     */
    private AssuranceFiller filler;

    /**
     * Converter mocké.
     */
    private BeanConverter<InsuranceType, AssuranceSigrec> converter;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void setUp()
    {
        this.converter = Mockito.mock(BeanConverter.class);
        this.filler = new AssuranceFiller();
        this.filler.setConverter(this.converter);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void tearDown()
    {
        this.filler = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.filler);
        Assert.assertEquals(this.converter,
                            this.filler.getConverter());
    }
    /**
     * Test de la méthode fill().
     */
    @Test
    public void testFill()
    {
        final TrialType source = this.initTrial();
        final EssaiSigrec destination = this.initEssai();
        final Calendar result = new GregorianCalendar();
        result.set(Calendar.YEAR,
                   2008);
        result.set(Calendar.MONTH,
                   9);
        result.set(Calendar.DAY_OF_MONTH,
                   10);
        Mockito
                .when(this.converter.convert(Matchers.any(InsuranceType.class)))
                .thenReturn(new AssuranceSigrec());

        this.filler.fill(source,
                         destination);

        Assert.assertEquals(2,
                            destination.getAssurances().size());
    }

    /**
     * Test de la méthode support().
     */
    @Test
    public void testSuppor()
    {
        final TrialType essai = new TrialType();
        Assert.assertFalse(this.filler.support(essai));
        essai.setRegulatoryInformation(new RegulatoryInformationType());
        Assert.assertFalse(this.filler.support(essai));
        essai.getRegulatoryInformation().setInsuranceInformation(new InsuranceInformation());
        Assert.assertTrue(this.filler.support(essai));
    }

    /**
     * Initialisation du TrialType.
     * @return le trialtype.
     */
    private TrialType initTrial()
    {
        final RegulatoryInformationType reg = new RegulatoryInformationType();
        final InsuranceType a1 = new InsuranceType();
        final InsuranceType a2 = new InsuranceType();
        final QName na = new QName("end-validity-date");
        final JAXBElement<XMLGregorianCalendar> date =
            new JAXBElement<XMLGregorianCalendar>(na,
                                                  XMLGregorianCalendar.class,
                                                  Mockito.mock(XMLGregorianCalendar.class));
        a1.setBeginningValidityDate(date);
        a2.setBeginningValidityDate(date);
        a1.setEndValidityDate(date);
        a2.setEndValidityDate(date);
        reg.setInsuranceInformation(new InsuranceInformation());
        reg.getInsuranceInformation().getInsurance().add(a1);
        reg.getInsuranceInformation().getInsurance().add(a2);
        final TrialType trial = new TrialType();
        trial.setRegulatoryInformation(reg);
        return trial;
    }

    private EssaiSigrec initEssai()
    {
        final EssaiSigrec essai = new EssaiSigrec();
        essai.setPrevision(new PrevisionSigrec());
        return essai;
    }

}
