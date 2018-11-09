package boletin1.ejercicio08.noObject;

import utils.Teclado;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Ejercicio08 {
    public static void main(String[] args) {
        String name, address;
        int phoneNumber, zipCode;
        LocalDate birthdate = null;
        boolean debt, wrongData, continueAdd;
        double debtAmount;
//        File f = new File("D:\\Proyectos\\AccesoADatos\\Esnuevoo\\bi\\noObject.txt");
//        File f = new File("C:\\zProyectos\\AccesoADatos\\Esnuevoo\\bi\\noObject.txt");
        // Para el ejercicio 11.
        File f = new File("C:\\zProyectos\\AccesoADatos\\Esnuevoo\\Comparacion11\\sinSerializacion.dat");
        DataInputStream read;
        DataOutputStream write = null;

        try {
            write = new DataOutputStream(new FileOutputStream(f, true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        do {
            System.out.println("Enter contact name:");
            name = Teclado.leerString();
            do {
                System.out.println("Enter phone number:");
                phoneNumber = Teclado.leerNumero(Teclado.Tipo.INT);
                if (String.valueOf(phoneNumber).matches("[0-9]{9}")) {
                    wrongData = false;
                } else {
                    wrongData = true;
                    System.out.println("The phone number must have 9 digits");
                }
            } while (wrongData);
            System.out.println("Enter address:");
            address = Teclado.leerString();
            System.out.println("Enter zip code:");
            zipCode = Teclado.leerNumero(Teclado.Tipo.INT);
            do {
                System.out.println("Enter date of birth:");
                try {
                    birthdate = LocalDate.parse(Teclado.leerString(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    wrongData = false;
                } catch (DateTimeParseException e) {
                    wrongData = true;
                    System.out.printf("the date must be in the format dd-mm-yyyy");
                }
            } while (wrongData);
            debt = Teclado.leerBoolean("Do you owe money?", "Yes", "No");
            if (debt) {
                System.out.println("How much money do you owe?");
                debtAmount = Teclado.leerNumero(Teclado.Tipo.DOUBLE);
            } else {
                debtAmount = 0;
            }

            try {
                write.writeUTF(name);
                write.writeInt(phoneNumber);
                write.writeUTF(address);
                write.writeInt(zipCode);
                write.writeUTF(birthdate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                write.writeBoolean(debt);
                write.writeDouble(debtAmount);


            }  catch (IOException e) {
                e.printStackTrace();
            }
            continueAdd = Teclado.leerBoolean("Add another contact?", "Yes", "No");
        } while (continueAdd);
        try {
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            read = new DataInputStream(new FileInputStream(f));

            try {
                while (true) {
                    System.out.printf("Name: %s\nPhone number: %d\nAddress: %s\nZip code: %d\nDate of birth: %s\nDo I owe you money? %s\nHow much money do I owe? %.2f\n\n",
                            read.readUTF(), read.readInt(), read.readUTF(), read.readInt(), read.readUTF(), read.readBoolean()? "Yes":"No", read.readDouble());
                }
            } catch (EOFException e) {}
            read.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
}
