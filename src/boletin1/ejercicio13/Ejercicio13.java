package boletin1.ejercicio13;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ejercicio13 {
    public static void main(String[] args) {
        String styleSheet = "C:\\zProyectos\\AccesoADatos\\src\\boletin1\\ejercicio13\\contactPlantilla.xsl";
        String datosContact = "C:\\zProyectos\\AccesoADatos\\src\\boletin1\\ejercicio13\\contact.xml";
        File pagHtml = new File("C:\\zProyectos\\AccesoADatos\\src\\boletin1\\ejercicio13", "contactTable.html");
        FileOutputStream html = null;
        Result result;
        Transformer transformer;


        try {
            pagHtml.createNewFile();
            html = new FileOutputStream(pagHtml);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Source estilos = new StreamSource(styleSheet);
        Source datos = new StreamSource(datosContact);

        result = new StreamResult(html);

        try {
            transformer = TransformerFactory.newInstance().newTransformer(estilos);
            transformer.transform(datos, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
