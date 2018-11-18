package boletin1.ejercicio12;

import boletin1.ejercicio08.object.Contact;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GestionXML extends DefaultHandler {

    String locality, prefix, coinType, valor;
    File f = new File("C:\\zProyectos\\AccesoADatos\\src\\boletin1\\ejercicio12", "ficherin.txt");
    boolean salto = false;
    FileWriter wr = null;

    public GestionXML() {
        super();
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            wr = new FileWriter(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endDocument() throws SAXException {
        try {
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (!qName.equals("ListContact") && !qName.equals("Contact")) {
            locality = attributes.getValue("locality");
            prefix = attributes.getValue("prefix");
            coinType = attributes.getValue("coinType");
            salto = false;
        }
        if (salto) {
            try {
                wr.write("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String escrito = "";
        
        if (!qName.equals("ListContact") && !qName.equals("Contact")) {
            if (qName.equals("address") && locality != null) {
                escrito = String.format("locality: %s %s: %s ", locality, qName, valor);
            } else if (prefix != null) {
                escrito = String.format("prefix: %s %s: %s ", prefix, qName, valor);
            } else if (coinType != null) {
                escrito = String.format("%s: %s coin type: %s", qName, valor, coinType);
            } else if (qName.equals("debt")){
                escrito = String.format("%s: %s ", qName, valor.equals("true") ? "si" : "no");
            } else {
                escrito = String.format("%s: %s ", qName, valor);
            }

            try {
                wr.write(escrito);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            salto = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        valor = new String(ch, start, length);
    }
}
