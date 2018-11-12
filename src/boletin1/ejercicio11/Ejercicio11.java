package boletin1.ejercicio11;

import boletin1.ejercicio08.object.Contact;
import boletin1.ejercicio10.ListContact;
import com.thoughtworks.xstream.XStream;

import java.io.*;
import java.time.format.DateTimeFormatter;

public class Ejercicio11 {
    public static void main(String[] args) {
        int dat8 = 0, dat11 = 0;
        boolean esIgual = true;
        XStream xStream = new XStream();
        ListContact listContact = null;
        File f11 = new File("C:\\zProyectos\\AccesoADatos\\src\\boletin1\\ejercicio11", "Contact11.dat");
        File f8 = new File("C:\\zProyectos\\AccesoADatos\\Esnuevoo\\Comparacion11", "sinSerializacion.dat");
        DataOutputStream write = null;
        DataInputStream read11 = null;
        DataInputStream read8 = null;
        try {
            write = new DataOutputStream(new FileOutputStream("C:\\zProyectos\\AccesoADatos\\src\\boletin1\\ejercicio11\\Contact11.dat"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            f11.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            xStream.alias("Contact", Contact.class);
            xStream.alias("ListContact", ListContact.class);
            xStream.addImplicitCollection(ListContact.class, "contacts");
            listContact = (ListContact) xStream.fromXML(new FileInputStream("C:\\zProyectos\\AccesoADatos\\src\\boletin1\\ejercicio10\\contact.xml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < listContact.getContacts().size(); i++) {
            try {
                write.writeUTF(listContact.getContact(i).getName());
                write.writeInt(listContact.getContact(i).getPhoneNumber());
                write.writeUTF(listContact.getContact(i).getAddress());
                write.writeInt(listContact.getContact(i).getZipCode());
                write.writeUTF(listContact.getContact(i).getBirthdate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                write.writeBoolean(listContact.getContact(i).isDebt());
                write.writeDouble(listContact.getContact(i).getDebtAmount());
            }  catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            read8 = new DataInputStream(new FileInputStream("C:\\zProyectos\\AccesoADatos\\Esnuevoo\\Comparacion11\\sinSerializacion.dat"));
            read11 = new DataInputStream(new FileInputStream("C:\\zProyectos\\AccesoADatos\\src\\boletin1\\ejercicio11\\Contact11.dat"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            if (f11.length() == f8.length()) {
                while(esIgual && dat8 != -1 && dat11 != -1) {
                    dat8 = read8.read();
                    dat11 = read11.read();

                      esIgual = dat8 == dat11;
                }
            } else {
                esIgual = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("Los archivos son %s", esIgual ? "iguales" : "distintos");

    }
}
