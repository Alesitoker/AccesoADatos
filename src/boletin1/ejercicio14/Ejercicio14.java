package boletin1.ejercicio14;

import boletin1.ejercicio08.object.Contact;
import boletin1.ejercicio10.ListContact;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;

import java.io.*;
import java.time.format.DateTimeFormatter;

public class Ejercicio14 {
    public static void main(String[] args) {
        XStream xStream = new XStream();
        ListContact listContact = null;
        File json = new File("C:\\zProyectos\\AccesoADatos\\src\\boletin1\\ejercicio14\\contact.json");
        FileWriter wr = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").setPrettyPrinting().create();

        xStream.alias("Contact", Contact.class);
        xStream.alias("ListContact", ListContact.class);
        xStream.addImplicitCollection(ListContact.class, "contacts");
        try {
            listContact = (ListContact) xStream.fromXML(new FileInputStream("C:\\zProyectos\\AccesoADatos\\src\\boletin1\\ejercicio14\\contact.xml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            wr = new FileWriter(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            wr.write(gson.toJson(listContact));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
