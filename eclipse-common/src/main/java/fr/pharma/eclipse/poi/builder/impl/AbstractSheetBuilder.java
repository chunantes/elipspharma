package fr.pharma.eclipse.poi.builder.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

import fr.pharma.eclipse.comparator.surcout.ItemComparator;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.domain.model.surcout.Item;
import fr.pharma.eclipse.domain.model.surcout.Resultat;
import fr.pharma.eclipse.jasper.utils.JasperUtils;
import fr.pharma.eclipse.poi.builder.SheetBuilder;
import fr.pharma.eclipse.service.habilitation.helper.HabilitationsHelper;
import fr.pharma.eclipse.utils.Utils;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Description de la classe.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public abstract class AbstractSheetBuilder implements SheetBuilder, Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -5869069565987783949L;

    /**
     * Numéro de la ligne de départ après le header global.
     */
    private int startRow;

    /**
     * Helper pour les habilitations.
     */
    @Resource(name = "habilitationsHelper")
    private HabilitationsHelper habilitationsHelper;

    /**
     * Styles.
     */
    private Map<String, HSSFCellStyle> styles;

    /**
     * {@inheritDoc}
     */
    @Override
    public void build(final Essai essai,
                      final Map<Item, Resultat> datas,
                      final HSSFSheet sheet,
                      final HSSFWorkbook workbook) {
        this.styles = this.createStyles(workbook);
        this.createSheetHeader(essai, sheet);

        this.startRow = sheet.getLastRowNum() + 1;

        this.createHeaders(datas, sheet, workbook);
        for (final Item item : this.buildSortedItems(datas)) {
            this.createLine(item, datas.get(item), sheet, workbook);
        }
        this.resize(sheet);

        this.createSheetFooter(essai, sheet);
        this.cleanup(sheet);

    }

    /**
     * Ajout du footer.
     * @param essai Essai.
     * @param sheet Feuille excel.
     */
    private void createSheetFooter(final Essai essai,
                                   final HSSFSheet sheet) {
        final int row = sheet.getLastRowNum() + 1;
        sheet.createRow(row);
        final HSSFRow r = sheet.createRow(row + 1);
        final HSSFCell cell = r.createCell(0);
        cell.setCellValue(new HSSFRichTextString("Le " + Utils.formatDate(Calendar.getInstance(EclipseConstants.LOCALE).getTime(), EclipseConstants.PATTERN_SIMPLE)));
        cell.setCellStyle(this.styles.get("header"));
    }
    /**
     * Méthode en charge de construire le header du document.
     * @param essai L'eesai.
     * @param sheet La feuille Excel.
     */
    protected void createSheetHeader(final Essai essai,
                                     final HSSFSheet sheet) {

        // Ligne 1 du header
        HSSFRow row = sheet.createRow(0);

        HSSFCell cell = row.createCell(0);
        cell.setCellValue(new HSSFRichTextString("PROMOTEUR"));
        cell.setCellStyle(this.styles.get("header"));
        row.createCell(1).setCellStyle(this.styles.get("header"));
        row.createCell(2).setCellValue(new HSSFRichTextString(essai.getPromoteur().getRaisonSociale()));
        cell = row.createCell(5);
        cell.setCellStyle(this.styles.get("header"));
        cell.setCellValue(new HSSFRichTextString("REFERENCE PROTOCOLE"));
        row.createCell(6).setCellStyle(this.styles.get("header"));
        row.createCell(7).setCellValue(new HSSFRichTextString(essai.getNom()));
        row.createCell(8);

        // Ligne 2 du header
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue(new HSSFRichTextString("SERVICES"));
        cell.setCellStyle(this.styles.get("header"));

        row.createCell(1).setCellStyle(this.styles.get("header"));
        row.createCell(2).setCellValue(new HSSFRichTextString(JasperUtils.formatterListeStrings(this.prepareNomsServices(essai.getServices()))));
        cell = row.createCell(5);
        cell.setCellValue(new HSSFRichTextString("INVESTIGATEUR PRINCIPAL"));
        cell.setCellStyle(this.styles.get("header"));
        row.createCell(6).setCellStyle(this.styles.get("header"));
        final Investigateur invPrincipal = this.habilitationsHelper.getInvestigateurPrincipal(essai);
        if (invPrincipal != null) {
            row.createCell(7).setCellValue(new HSSFRichTextString(invPrincipal.getPrenom() + " " + invPrincipal.getNom()));
        }
        row.createCell(8);

        // Ligne 3 du header
        row = sheet.createRow(2);
        cell = row.createCell(0);
        cell.setCellStyle(this.styles.get("header"));
        cell.setCellValue(new HSSFRichTextString("DUREE APROXIMATIVE DE L'ESSAI"));
        row.createCell(1).setCellStyle(this.styles.get("header"));
        if (essai.getDetailSurcout().getDonneesPrevision().getNbAnnees() != null) {
            row.createCell(2).setCellValue(new HSSFRichTextString(essai.getDetailSurcout().getDonneesPrevision().getNbAnnees().toString()));
        }
        cell = row.createCell(5);
        cell.setCellValue(new HSSFRichTextString("NOMBRE DE PATIENTS PREVUS"));
        cell.setCellStyle(this.styles.get("header"));;
        row.createCell(6).setCellStyle(this.styles.get("header"));

        if (essai.getDetailDonneesPharma().getInfosGenerales().getNbPatientsPrevus() != null) {
            row.createCell(7).setCellValue(new HSSFRichTextString(essai.getDetailDonneesPharma().getInfosGenerales().getNbPatientsPrevus().toString()));
        }
        row.createCell(8);

        sheet.createRow(3);
        sheet.createRow(4);

        // MERGE des régions.
        for (int i = 0; i < 3; i++) {

            sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 1));

            sheet.addMergedRegion(new CellRangeAddress(i, i, 2, 3));

            sheet.addMergedRegion(new CellRangeAddress(i, i, 5, 6));
            sheet.addMergedRegion(new CellRangeAddress(i, i, 7, 8));
        }

    }
    /**
     * Méthode en charge de préparer la liste des noms des services) donés.
     * @param services Services à inspecter.
     * @return Liste des noms des services de l'investigateur
     */
    @SuppressWarnings("unchecked")
    private Collection<String> prepareNomsServices(final Collection<Service> services) {
        if ((services == null) || services.isEmpty()) {
            return new ArrayList<String>();
        }
        final Collection collection = new ArrayList(services);
        CollectionUtils.transform(collection, new Transformer() {

            @Override
            public Object transform(final Object input) {
                final Service service = (Service) input;
                return service.getNom();
            }
        });
        return collection;
    }

    /**
     * Méthode en charge de créer les headers de la feuille excel.
     * @param datas Les données.
     * @param sheet La feuille.
     * @param workbook workbook.
     */
    protected abstract void createHeaders(Map<Item, Resultat> datas,
                                          HSSFSheet sheet,
                                          HSSFWorkbook workbook);

    /**
     * Méthode en charge de créer une ligne dans le tableau pour l'item en
     * paramètre.
     * @param item L'item.
     * @param sheet La feuille.
     * @param workbook WorkBook.
     */
    protected abstract void createLine(Item item,
                                       Resultat resultat,
                                       HSSFSheet sheet,
                                       HSSFWorkbook workbook);

    /**
     * Méthode en charge de redimensionner la grille.
     * @param sheet La feuille.
     */
    protected abstract void resize(HSSFSheet sheet);

    /**
     * Méthode en charge de nettoyer la grille.
     * @param sheet La grille.
     */
    private void cleanup(final HSSFSheet sheet) {

        // merge des lignes
        sheet.addMergedRegion(new org.apache.poi.hssf.util.CellRangeAddress(0 + this.getStartRow(), 2 + this.getStartRow(), 0, 1));

    }

    /**
     * Méthode en charge de créer une liste d'item à partir de la map et de la
     * trier par thème et catégorie.
     * @param datas Les données.
     * @return La liste triée d'items.
     */
    protected List<Item> buildSortedItems(final Map<Item, Resultat> datas) {
        final List<Item> items = new ArrayList<Item>(datas.keySet());
        Collections.sort(items, new ItemComparator());
        return items;
    }

    /**
     * create a library of cell styles
     */
    protected Map<String, HSSFCellStyle> createStyles(final HSSFWorkbook wb) {
        final Map<String, HSSFCellStyle> styles = new HashMap<String, HSSFCellStyle>();

        // Header
        HSSFCellStyle header;
        final HSSFFont headerFont = wb.createFont();
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        header = AbstractSheetBuilder.createBorderedStyle(wb);
        header.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        header.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
        header.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        header.setFont(headerFont);
        styles.put("header", header);

        // Header
        final HSSFCellStyle donnees = AbstractSheetBuilder.createBorderedStyle(wb);
        donnees.setWrapText(true);
        styles.put("donnees", donnees);

        return styles;
    }

    private static HSSFCellStyle createBorderedStyle(final HSSFWorkbook wb) {
        final HSSFCellStyle style = wb.createCellStyle();
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setRightBorderColor(HSSFColor.BLACK.index);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setTopBorderColor(HSSFColor.BLACK.index);
        return style;
    }
    /**
     * Getter pour startRow.
     * @return Le startRow
     */
    public int getStartRow() {
        return this.startRow;
    }
    /**
     * Setter pour startRow.
     * @param startRow Le startRow à écrire.
     */
    public void setStartRow(final int startRow) {
        this.startRow = startRow;
    }
    /**
     * Setter pour habilitationsHelper.
     * @param habilitationsHelper Le habilitationsHelper à écrire.
     */
    public void setHabilitationsHelper(final HabilitationsHelper habilitationsHelper) {
        this.habilitationsHelper = habilitationsHelper;
    }

}
