package fr.pharma.eclipse.poi;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.surcout.TypeCalcul;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.surcout.Item;
import fr.pharma.eclipse.domain.model.surcout.Resultat;
import fr.pharma.eclipse.poi.builder.SheetBuilder;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe FileCreator.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class FileCreatorTest extends AbstractEclipseJUnitTest {
    /**
     * Creator.
     */
    private FileCreator creator;

    /**
     * Builder.
     */
    private SheetBuilder builder;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.builder = Mockito.mock(SheetBuilder.class);
        this.creator = new FileCreator();
        final Map<TypeCalcul, SheetBuilder> map = new HashMap<TypeCalcul, SheetBuilder>();
        map.put(TypeCalcul.PREVISIONNEL, this.builder);
        this.creator.setBuilders(map);
        this.creator.setDocumentsDirectory(new File("doc"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.builder = null;
        this.creator = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.builder);
        Assert.assertNotNull(this.creator);
    }

    /**
     * Test de la méthode createFile.
     * @throws IOException
     */
    @Test
    public void testCreateFile() throws IOException {
        final Map<Item, Resultat> datas = new HashMap<Item, Resultat>();
        final Essai essai = new Essai();
        essai.setId(1L);
        this.creator.createFile(essai, datas, TypeCalcul.PREVISIONNEL);
        Mockito.verify(this.builder).build(Matchers.any(Essai.class), Matchers.anyMapOf(Item.class, Resultat.class), Matchers.any(HSSFSheet.class),
                                           Matchers.any(HSSFWorkbook.class));
    }
}
