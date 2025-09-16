package org.aaronmatta.system;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;

public class Principal {

    public static void main(String[] args) {
        String[][] paises = {
            {"Guatemala", "Ciudad de Guatemala", "17"},
            {"México", "Ciudad de México", "126"},
            {"España", "Madrid", "47"},
            {"Japón", "Tokio", "125"}
        };
        
        String rutaArchivo = "C:\\Users\\TheSn\\OneDrive\\Escritorio\\USAC\\4° Semestre\\Introduccion a la Programación y Computación 1\\Proyectos Laboratorio\\Actividad6\\Actividad6.pdf";
        
        Document documento = new Document();
        
        try{
            
            PdfWriter.getInstance(documento, new FileOutputStream(rutaArchivo));
            documento.open();
            
            PdfPTable tabla = new PdfPTable(3);
            
            PdfPCell h1 = new PdfPCell(new Paragraph("Pais"));
            h1.setBackgroundColor(new BaseColor(138, 149, 151));
            h1.setHorizontalAlignment(Element.ALIGN_CENTER);
            h1.setPadding(8);
            tabla.addCell(h1);
            
            PdfPCell h2 = new PdfPCell(new Paragraph("Capital"));
            h2.setBackgroundColor(new BaseColor(138, 149, 151));
            h2.setHorizontalAlignment(Element.ALIGN_CENTER);
            h2.setPadding(8);
            tabla.addCell(h2);
            
            PdfPCell h3 = new PdfPCell(new Paragraph("Poblacion (millones)"));
            h3.setBackgroundColor(new BaseColor(138, 149, 151));
            h3.setHorizontalAlignment(Element.ALIGN_CENTER);
            h3.setPadding(8);
            tabla.addCell(h3);
            
            for(int fila=0;fila<4;fila++){

                for(int columna=0;columna<3;columna++){
                    PdfPCell celdaColumnas = new PdfPCell(new Phrase(paises[fila][columna]));
                    celdaColumnas.setHorizontalAlignment(Element.ALIGN_LEFT);
                    celdaColumnas.setPadding(5);
                    tabla.addCell(celdaColumnas);
                }
            }
            
            documento.add(tabla);
            
            System.out.println("PDF creado en: "+rutaArchivo);
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(documento.isOpen()){
                documento.close();
            }
        }
    }
    
}
