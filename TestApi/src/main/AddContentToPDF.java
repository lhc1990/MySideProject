package main;

/* requires itextpdf-5.1.2.jar or similar */
import java.io.*;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;

public class AddContentToPDF {

    public static void main(String[] args) throws IOException, DocumentException {

        /* example inspired from "iText in action" (2006), chapter 2 */

        PdfReader reader = new PdfReader("C:/temp/Bubi.pdf"); // input PDF
        PdfStamper stamper = new PdfStamper(reader,
          new FileOutputStream("C:/temp/Bubi_modified.pdf")); // output PDF
        BaseFont bf = BaseFont.createFont(
                BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED); // set font

        //loop on pages (1-based)
        for (int i=1; i<=reader.getNumberOfPages(); i++){

            // get object for writing over the existing content;
            // you can also use getUnderContent for writing in the bottom layer
            PdfContentByte over = stamper.getOverContent(i);
            over.getPdfDocument();
            

            // write text
            over.beginText();
            over.setFontAndSize(bf, 10);    // set font and size
            over.setTextMatrix(107, 740);   // set x,y position (0,0 is at the bottom left)
            over.showText("I can write at page " + i);  // set text
            over.endText();

            // draw a red circle
            over.setRGBColorStroke(0xFF, 0x00, 0x00);
            over.setLineWidth(5f);
            over.ellipse(250, 450, 350, 550);
            over.stroke();
        }

        stamper.close();

    }
}