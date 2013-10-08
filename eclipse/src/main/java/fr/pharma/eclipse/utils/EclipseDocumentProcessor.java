package fr.pharma.eclipse.utils;

import java.io.IOException;
import java.io.Serializable;

import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;

/**
 * Processor utilisé pour les document Primefaces.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EclipseDocumentProcessor implements Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 4805142821263461145L;

    private static Font catFont = new Font(Font.TIMES_ROMAN, 18, Font.BOLD);

    /**
     * Méthode appelée avant la génération du document.
     * @param document Document.
     * @throws IOException
     * @throws BadElementException
     * @throws DocumentException
     */
    public void preProcessPDF(final Object document,
                              final String titre) throws IOException, BadElementException, DocumentException {
        final Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);
        final Paragraph preface = new Paragraph(titre, EclipseDocumentProcessor.catFont);
        preface.setAlignment(Element.ALIGN_CENTER);
        // We add one empty line
        EclipseDocumentProcessor.addEmptyLine(preface, 3);
        pdf.add(preface);
    }

    private static void addEmptyLine(final Paragraph paragraph,
                                     final int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    public void preProcessXLS(final Object document,
                              final String titre) {
        final HSSFWorkbook wb = (HSSFWorkbook) document;
        final HSSFSheet sheet = wb.getSheetAt(0);
        final HSSFHeader header = sheet.getHeader();
        sheet.createRow(0);

        header.setCenter(titre);
    }
}
