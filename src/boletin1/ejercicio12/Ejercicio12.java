package boletin1.ejercicio12;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class Ejercicio12 {
    public static void main(String[] args) {
        GestionXML gestorXML = new GestionXML();
        InputSource fileXML = new InputSource("C:\\zProyectos\\AccesoADatos\\src\\boletin1\\ejercicio12\\contact.xml");
        SAXParserFactory saxParserFactory = SAXParserFactory.newDefaultInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(new File("C:\\zProyectos\\AccesoADatos\\src\\boletin1\\ejercicio12\\contact.xml"), gestorXML);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
