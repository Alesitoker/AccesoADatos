package boletin1.ejercicio10;

import boletin1.ejercicio08.object.Contact;
import com.thoughtworks.xstream.XStream;

import java.io.*;

public class Ejercicio10 {
    public static void main(String[] args) {
        ObjectInputStream read = null;
        Contact contact;
        ListContact contacts = new ListContact();
        File f = new File("C:\\zProyectos\\AccesoADatos\\src\\boletin1\\ejercicio10\\contact.xml");
//        File f = new File("D:\\Proyectos\\AccesoADatos\\src\\boletin1\\ejercicio10\\contact.xml");
        XStream xStream = new XStream();
        try {
            read = new ObjectInputStream(new FileInputStream(new File("C:\\zProyectos\\AccesoADatos\\Esnuevoo\\Comparacion11\\conSerializacion.dat")));
//            read = new ObjectInputStream(new FileInputStream(new File("D:\\Proyectos\\AccesoADatos\\Esnuevoo\\bi\\Objecto.txt")));
        } catch (IOException e) {

        }

        try {
            while (true) {
                contact = (Contact) read.readObject();
                contacts.addContact(contact);
            }
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
        try {
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            xStream.alias("Contact", Contact.class);
            xStream.alias("ListContact", ListContact.class);
            xStream.addImplicitCollection(ListContact.class, "contacts");
            xStream.toXML(contacts, new FileOutputStream(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

