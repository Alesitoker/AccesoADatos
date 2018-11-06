package boletin1.ejercicio09.creacion;

import boletin1.ejercicio08.object.Contact;

import java.io.*;
import java.time.format.DateTimeFormatter;

public class CreateRandonFile {
    public static void main(String[] args) {
        File file8 = new File("C:\\zProyectos\\AccesoADatos\\Esnuevoo\\bi\\Objecto.txt");
//        File file8 = new File("D:\\Proyectos\\AccesoADatos\\Esnuevoo\\bi\\Objecto.txt");
        ObjectInputStream read;
        RandomAccessFile randomFile = null;
        Contact contact;
        byte id = 0;
        StringBuffer buffer;
        try {
            read = new ObjectInputStream(new FileInputStream(file8));
            randomFile = new RandomAccessFile("C:\\zProyectos\\AccesoADatos\\Esnuevoo\\Random\\ridier.dat", "rw");
//            randomFile = new RandomAccessFile("D:\\Proyectos\\AccesoADatos\\Esnuevoo\\Random\\ridier.dat", "rw");
            try {
                while (true) {
                    contact = (Contact) read.readObject();

                    randomFile.writeInt(++id); // 4
                    randomFile.writeBoolean(true); // 1
                    buffer = new StringBuffer(contact.getName());
                    buffer.setLength(20);
                    randomFile.writeChars(buffer.toString()); // 40
                    randomFile.writeInt(contact.getPhoneNumber()); // 4
                    buffer = new StringBuffer(contact.getAddress());
                    buffer.setLength(35);
                    randomFile.writeChars(buffer.toString()); // 70
                    randomFile.writeInt(contact.getZipCode()); // 4
                    randomFile.writeChars(contact.getBirthdate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))); // 20
                    randomFile.writeBoolean(contact.isDebt()); // 1
                    randomFile.writeDouble(contact.getDebtAmount()); // 8
                    // Total: 152
                }
            } catch (EOFException e) {} catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            read.close();
            randomFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
