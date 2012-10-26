package fr.pharma.eclipse.poi.builder.impl;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;

import fr.pharma.eclipse.domain.model.surcout.Item;
import fr.pharma.eclipse.domain.model.surcout.Resultat;
import fr.pharma.eclipse.poi.builder.SheetBuilder;
import fr.pharma.eclipse.poi.formatter.ItemLineFormatter;

/**
 * Builder de feuille de calcul pour le calcul prévisionnel.
 
 * @version $Revision$ $Date$
 */
public class PrevisionnelBuilder
    extends AbstractSheetBuilder
    implements SheetBuilder, Serializable
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -2930047001415003337L;

    /**
     * Formatter item.
     */
    @Resource(name = "previsionnelLineFormatter")
    private ItemLineFormatter lineFormatter;

    /**
     * Champs dans l'en-têtes.
     */
    private final String[][] headers =
    {
     {null, null, "Frais fixes", null, "Frais variables", null, null, null, "Total" },
     {null, null, "Première année", "année suivantes", "Par patient", null, "Par esssai", null,
      null },
     {null, null, null, null, "Nombre", "Cout", "Nombre", "Cout", null }

    };

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createHeaders(final Map<Item, Resultat> datas,
                                 final HSSFSheet sheet,
                                 final HSSFWorkbook workbook)
    {

        final Map<String, HSSFCellStyle> styles = super.createStyles(workbook);
        HSSFRow row;
        HSSFCell cell;
        for (int i = 0; i < this.headers.length; i++)
        {
            {
                row = sheet.createRow(i
                                      + this.getStartRow());
                for (int j = 0; j < this.headers[i].length; j++)
                {
                    cell = row.createCell(j);
                    cell.setCellValue(this.headers[i][j]);
                    cell.setCellStyle(styles.get("header"));
                }
            }
        }

        // merge des colonnes de la ligne1
        // merge de la colonne frais fixes
        sheet.addMergedRegion(new CellRangeAddress(0 + this.getStartRow(),
                                                   0 + this.getStartRow(),
                                                   2,
                                                   3));
        // merge de la colonne frais variables
        sheet.addMergedRegion(new CellRangeAddress(0 + this.getStartRow(),
                                                   0 + this.getStartRow(),
                                                   4,
                                                   7));

        // merge des colonnes de la ligne 2
        // merge de la colonne par patient
        sheet.addMergedRegion(new CellRangeAddress(1 + this.getStartRow(),
                                                   1 + this.getStartRow(),
                                                   4,
                                                   5));
        // merge de la colonne par essai
        sheet.addMergedRegion(new CellRangeAddress(1 + this.getStartRow(),
                                                   1 + this.getStartRow(),
                                                   6,
                                                   7));

        // merge des lignes
        sheet.addMergedRegion(new CellRangeAddress(1 + this.getStartRow(),
                                                   2 + this.getStartRow(),
                                                   2,
                                                   2));
        // merge des lignes
        sheet.addMergedRegion(new CellRangeAddress(1 + this.getStartRow(),
                                                   2 + this.getStartRow(),
                                                   3,
                                                   3));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createLine(final Item item,
                              final Resultat resultat,
                              final HSSFSheet sheet,
                              final HSSFWorkbook workbook)
    {
        final HSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);
        final String[] valeurs = this.lineFormatter.format(item,
                                                           resultat);

        HSSFCell cell;
        for (int i = 0; i < valeurs.length; i++)
        {
            cell = row.createCell(i);
            cell.setCellValue(valeurs[i]);
            cell.setCellStyle(this.createStyles(workbook).get("donnees"));
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void resize(final HSSFSheet sheet)
    {
        sheet.setColumnWidth(0,
                             20 * 256);
        sheet.setColumnWidth(1,
                             20 * 256);
        sheet.setColumnWidth(2,
                             15 * 256);
        sheet.setColumnWidth(3,
                             15 * 256);
        sheet.setColumnWidth(4,
                             8 * 256);
        sheet.setColumnWidth(5,
                             24 * 256);
        sheet.setColumnWidth(6,
                             8 * 256);
        sheet.setColumnWidth(7,
                             24 * 256);
        sheet.setColumnWidth(8,
                             10 * 256);
    }

    /**
     * Setter pour lineFormatter.
     * @param lineFormatter le lineFormatter à écrire.
     */
    public void setLineFormatter(final ItemLineFormatter lineFormatter)
    {
        this.lineFormatter = lineFormatter;
    }
}
