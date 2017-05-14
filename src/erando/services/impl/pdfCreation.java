/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.services.impl;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;
import erando.models.Product;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author F.Mouhamed
 */
public class pdfCreation {
    public void genererpdf(String titre , float prix , String imageName,int id,String description)
            throws IOException,
			FileNotFoundException, DocumentException
    {
               
                PdfReader pdfTemplate = new PdfReader("template.pdf");
		FileOutputStream fileOutputStream = new FileOutputStream(id+".pdf");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfStamper stamper = new PdfStamper(pdfTemplate, fileOutputStream);
		stamper.setFormFlattening(true);
                stamper.getAcroFields().setField("titre", titre.toUpperCase());
                stamper.getAcroFields().setField("description", description);
                stamper.getAcroFields().setField("prix",""+prix);
                
		stamper.close();
		pdfTemplate.close();
              
    }
}
