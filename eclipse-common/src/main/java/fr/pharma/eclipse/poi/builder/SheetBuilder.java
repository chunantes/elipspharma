package fr.pharma.eclipse.poi.builder;

import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.surcout.Item;
import fr.pharma.eclipse.domain.model.surcout.Resultat;

/**
 * Interface définissant le comportement des builders en charge de construire la
 * feuille excel.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface SheetBuilder {
    /**
     * Méthode en charge de construire le feuille excel.
     * @param essai L'essai.
     * @param datas Les données.
     * @param sheet La feuille.
     * @param workbook WOrkbook.
     */
    void build(Essai essai,
               final Map<Item, Resultat> datas,
               final HSSFSheet sheet,
               HSSFWorkbook workbook);
}
