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
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ReelBuilder extends AbstractSheetBuilder implements SheetBuilder, Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -2930047001415003337L;

    /**
     * Formatter item.
     */
    @Resource(name = "reelLineFormatter")
    private ItemLineFormatter lineFormatter;

    /**
     * Champs dans l'en-têtes.
     */
    private final String[][] headers = {{null, null, "Frais fixes", null, "Frais variables", null, null, "Total" },
                                        {null, null, "Première année", "années suivantes", "Par patient", "Par essai", null, null },
                                        {null, null, null, null, "Cout", "Nombre", "Cout", null }

    };

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createHeaders(final Map<Item, Resultat> datas,
                                 final HSSFSheet sheet,
                                 final HSSFWorkbook workbook) {

        final Map<String, HSSFCellStyle> styles = super.createStyles(workbook);

        HSSFRow row;
        HSSFCell cell;
        for (int i = 0; i < this.headers.length; i++) {
            {
                row = sheet.createRow(i + this.getStartRow());
                for (int j = 0; j < this.headers[i].length; j++) {
                    cell = row.createCell(j);
                    cell.setCellValue(this.headers[i][j]);
                    cell.setCellStyle(styles.get("header"));
                }
            }
        }

        // merge des colonnes de la ligne1
        // merge de la colonne frais fixes
        sheet.addMergedRegion(new CellRangeAddress(0 + this.getStartRow(), 0 + this.getStartRow(), 2, 3));
        // merge de la colonne frais variables
        sheet.addMergedRegion(new CellRangeAddress(0 + this.getStartRow(), 0 + this.getStartRow(), 4, 6));

        // merge de la colonne par essai
        sheet.addMergedRegion(new CellRangeAddress(1 + this.getStartRow(), 1 + this.getStartRow(), 5, 6));

        // merge des lignes
        sheet.addMergedRegion(new CellRangeAddress(1 + this.getStartRow(), 2 + this.getStartRow(), 2, 2));
        // merge des lignes
        sheet.addMergedRegion(new CellRangeAddress(1 + this.getStartRow(), 2 + this.getStartRow(), 3, 3));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void createLine(final Item item,
                              final Resultat resultat,
                              final HSSFSheet sheet,
                              final HSSFWorkbook workbook) {
        final HSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);
        final String[] valeurs = this.lineFormatter.format(item, resultat);

        HSSFCell cell;
        for (int i = 0; i < valeurs.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(valeurs[i]);
            cell.setCellStyle(this.createStyles(workbook).get("donnees"));
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void resize(final HSSFSheet sheet) {
        sheet.setColumnWidth(0, 20 * 256);
        sheet.setColumnWidth(1, 20 * 256);
        sheet.setColumnWidth(2, 15 * 256);
        sheet.setColumnWidth(3, 15 * 256);
        sheet.setColumnWidth(4, 24 * 256);
        sheet.setColumnWidth(5, 8 * 256);
        sheet.setColumnWidth(6, 24 * 256);
        sheet.setColumnWidth(7, 10 * 256);
    }

    /**
     * Setter pour lineFormatter.
     * @param lineFormatter le lineFormatter à écrire.
     */
    public void setLineFormatter(final ItemLineFormatter lineFormatter) {
        this.lineFormatter = lineFormatter;
    }
}
