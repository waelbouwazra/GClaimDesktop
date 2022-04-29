/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Entities.Article;
import Entities.Produit;
import Services.ArticleService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
//import javax.swing.text.Document;
import Services.ProduitService;
import com.itextpdf.text.Phrase;
import static com.itextpdf.text.pdf.BidiOrder.PDF;
import static com.itextpdf.text.pdf.PdfName.PDF;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Date;
import static javax.print.DocFlavor.BYTE_ARRAY.PDF;
import static javax.print.DocFlavor.INPUT_STREAM.PDF;

/**
 *
 * @author user
 */
public class PDFActualite {

 public void pdfGeneration() throws FileNotFoundException, DocumentException, MalformedURLException, IOException, URISyntaxException {
        Document document = new Document();
        ArticleService ps = new ArticleService ();
        List<Article> order= ps.ShowArticle();
       
        PdfWriter.getInstance(document, new FileOutputStream (new File ("Actualite.pdf")));
        document.open();
        document.addTitle("Article");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Gclaim");
        document.addCreator("Gclaim admin");
        Paragraph preface = new Paragraph();

        Paragraph titre =new Paragraph (" Gclaim articles");
        titre.setAlignment (Element.ALIGN_CENTER);

        // Will create: Report generated by: _name, _date
        preface.add(new Paragraph(
                "Report generated by: " + System.getProperty("user.name") + ", " + new Date ()));
        preface.add(new Paragraph(
                "This document describes all the bills "));
        preface.setAlignment (Element.ALIGN_CENTER);
        document.add (titre);
        document.add(preface);
      
        // Start a new page
//        document.newPage();

        PDFProd pdf=new PDFProd ();
        PdfPTable table = new PdfPTable(3);

        PdfPCell c1 = new PdfPCell(new Phrase("Titre"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("description"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);


        c1 = new PdfPCell(new Phrase("nombre de vue"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        

       

        table.setHeaderRows(1);
        for (Article o:order) {
            table.addCell(String.valueOf (o.getTitre()));
            table.addCell(String.valueOf (o.getDescription()));

            table.addCell(String.valueOf (o.getNbr_vu()));
         
            
        }

        document.add (table);
        document.close ();

 }
  
}