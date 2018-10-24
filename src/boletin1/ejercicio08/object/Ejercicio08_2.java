package boletin1.ejercicio08.object;

import utils.Teclado;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Ejercicio08_2 {
    public static void main(String[] args) {
        File f = new File("D:\\Proyectos\\AccesoADatos\\Esnuevoo\\bi\\Objecto.txt");
//        File f = new File("C:\\zProyectos\\AccesoADatos\\Esnuevoo\\bi\\Objecto.txt");
        File f2 = new File("D:\\Proyectos\\AccesoADatos\\Esnuevoo\\bi","Objecto.txt");
//        File f2 = new File("C:\\zProyectos\\AccesoADatos\\Esnuevoo\\bi","Objecto.txt");
        ObjectInputStream read;
        ObjectOutputStream write = null;
        String name, address;
        int phoneNumber, zipCode;
        LocalDate birthdate = null;
        boolean debt, wrongData, continueAdd;
        double debtAmount = 0.00;
        Contact contact;

        try {
            if (!f.exists()) {
                f2.createNewFile();
                write = new ObjectOutputStream(new FileOutputStream(f));
            }
            else {
                write = new MyObjectOutputStream(new FileOutputStream(f,true));
            }
        } catch (IOException e) {
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
            }

            try {
                contact = new Contact(name, phoneNumber, address, zipCode, birthdate, debt, debtAmount);
                write.writeObject(contact);
            } catch (IOException e) {
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
            read = new ObjectInputStream(new FileInputStream(f));

            try {
                while (true) {
                    contact = (Contact) read.readObject();
                    System.out.println(contact.toString());
                }
            } catch (EOFException e) {} catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            read.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
