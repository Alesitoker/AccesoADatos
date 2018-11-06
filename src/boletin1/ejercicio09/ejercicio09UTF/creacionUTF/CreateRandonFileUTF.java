package boletin1.ejercicio09.ejercicio09UTF.creacionUTF;

import boletin1.ejercicio08.object.Contact;

import java.io.*;
import java.time.format.DateTimeFormatter;

public class CreateRandonFileUTF {
    public static void main(String[] args) {
        File file8 = new File("C:\\zProyectos\\AccesoADatos\\Esnuevoo\\bi\\Objecto.txt");
//        File file8 = new File("D:\\Proyectos\\AccesoADatos\\Esnuevoo\\bi\\Objecto.txt");
        ObjectInputStream read;
        RandomAccessFile randomFile = null;
        Contact contact;
        byte id = 0;
        String buffer;
        try {
            read = new ObjectInputStream(new FileInputStream(file8));
            randomFile = new RandomAccessFile("C:\\zProyectos\\AccesoADatos\\Esnuevoo\\Random\\ridierUTF.dat", "rw");
//            randomFile = new RandomAccessFile("D:\\Proyectos\\AccesoADatos\\Esnuevoo\\Random\\ridierUTF.dat", "rw");
            try {

                while (true) {
                    contact = (Contact) read.readObject();

                    randomFile.writeInt(++id); // 4
                    randomFile.writeBoolean(true); // 1
                    buffer = String.format("%-20s", contact.getName());
                    randomFile.writeUTF(buffer); // 22
                    randomFile.writeInt(contact.getPhoneNumber()); // 4
                    buffer = String.format("%-35s", contact.getAddress());
                    randomFile.writeUTF(buffer); // 37
                    randomFile.writeInt(contact.getZipCode()); // 4
                    randomFile.writeUTF(contact.getBirthdate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))); // 12
                    randomFile.writeBoolean(contact.isDebt()); // 1
                    randomFile.writeDouble(contact.getDebtAmount()); // 8
                    // Total: 93.
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
