package fr.pharma.eclipse.jasper.engine.helper;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.jasper.TypeRapportJasper;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;
import fr.pharma.eclipse.utils.Utils;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Test de la classe {@link ReportNameBuildHelper}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ReportNameBuildHelperTest extends AbstractEclipseJUnitTest {
    /**
     * Helper testé.
     */
    private ReportNameBuildHelper helper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.helper = new ReportNameBuildHelper();
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
     * Test de la méthode addIdEssaiPart(java.lang.StringBuilder,
     * fr.pharma.eclipse.domain.model.essai.Essai).
     */
    @Test
    public void testAddIdEssaiPart() {
        final StringBuilder strBuilder = new StringBuilder("début");
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        essai.setNumInterne("00 1234  ");
        this.helper.addIdEssaiPart(strBuilder, essai);
        Assert.assertEquals("début_00-1234", strBuilder.toString());
    }

    /**
     * Test de la méthode addDatePart(java.lang.StringBuilder). .
     */
    @Test
    public void testAddDatePart() {
        final String expectedDate = Utils.formatDate(Calendar.getInstance(EclipseConstants.LOCALE).getTime(), ReportNameBuildHelper.getPattern());
        final StringBuilder strBuilder = new StringBuilder("début");
        this.helper.addDatePart(strBuilder);
        Assert.assertEquals("début_" + expectedDate, strBuilder.toString());
    }
    /**
     * Test de la méthode addCommonNamePart(java.lang.StringBuilder,
     * fr.pharma.eclipse.domain.enums.jasper.TypeRapportJasper).
     */
    @Test
    public void testAddCommonNamePart() {
        final StringBuilder strBuilder = new StringBuilder();
        final TypeRapportJasper typeRapport = TypeRapportJasper.FICHE_INFO_ESSAI;
        this.helper.addCommonNamePart(strBuilder, typeRapport);
        Assert.assertEquals(typeRapport.getReportName(), strBuilder.toString());
    }

    /**
     * Test de la méthode addCommonExtensionPart(java.lang.StringBuilder,
     * fr.pharma.eclipse.domain.enums.jasper.TypeRapportJasper). .
     */
    @Test
    public void testAddCommonExtensionPart() {
        final StringBuilder strBuilder = new StringBuilder("début");
        final TypeRapportJasper typeRapport = TypeRapportJasper.FICHE_INFO_ESSAI;
        this.helper.addCommonExtensionPart(strBuilder, typeRapport);
        Assert.assertEquals("début" + typeRapport.getTypeExport().getExtension(), strBuilder.toString());
    }
}
