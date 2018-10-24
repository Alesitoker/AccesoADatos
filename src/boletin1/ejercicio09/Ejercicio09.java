package boletin1.ejercicio09;

import utils.Teclado;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Ejercicio09 {
    public static void main(String[] args) {
        byte option;
        boolean exit = false;
        int id;
        RandomAccessFile fileR = null;
        try {
//            fileR = new RandomAccessFile("C:\\zProyectos\\AccesoADatos\\Esnuevoo\\Random\\ridier.dat", "rw");
            fileR = new RandomAccessFile("D:\\Proyectos\\AccesoADatos\\Esnuevoo\\Random\\ridier.dat", "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        do {
            System.out.println("1.Consultar todos los contactos.\n2.Consultar un contacto.\n3.Añadir un contacto.\n4.Eliminar un contacto.\n5.Modificar deuda.\n6.Compactación.\n7.Salir.");
            option = Teclado.leerNumero(Teclado.Tipo.BYTE);

            switch (option) {
                case 1:
                    try {
                        consultContacts(fileR);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("Introduce el id del contacto a consultar:");
                    id = Teclado.leerNumero(Teclado.Tipo.INT);
                    try {
                        consultContact(fileR, id);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        addContact(fileR);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    System.out.println("Introduce el id del contacto a borrar:");
                    id = Teclado.leerNumero(Teclado.Tipo.INT);
                    deleteContact(fileR, id);
                    break;
                case 5:
                    modifyDebt(fileR);
                    break;
                case 6:
                    compactar(fileR);
                    break;
                case 7:
                    exit = true;
            }
        } while (!exit);
        try {
            fileR.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void consultContacts(RandomAccessFile fileR) throws IOException {
        int posicion = 0, idContador = 1, i, phoneNumber = 0, zipCode = 0, id;
        final int TOTALBYTES = 152;
        long size = 0;
        String name = "", address = "", birthdate = "";
        boolean debt = false;
        double debtAmount = 0;

        if (fileR != null) {
            size = (int) fileR.length();

            while (posicion < size) {
                // Nos posicionamos en el boleano de borrado.
                fileR.seek(posicion + 4);
                if (fileR.readBoolean()) {
                    showContact(fileR, posicion);
                }

                posicion = TOTALBYTES * idContador;
                fileR.seek(posicion);
                idContador++;
            }
        }
    }

    private static void consultContact(RandomAccessFile fileR, int id) throws IOException {
        int posicion = 0, i, phoneNumber, zipCode;
        final int TOTALBYTES = 152;
        long size;
        String name = "", address = "", birthdate = "";
        double debtAmount;
        boolean debt;
        if (fileR != null) {
            size = fileR.length();
            posicion = TOTALBYTES*(id-1);
            if (posicion < size && posicion >= 0) {
                posicion = 152 * (id-1);
                fileR.seek(posicion + 4);
                if (fileR.readBoolean()) {
                    showContact(fileR, posicion);
                } else {
                    System.out.println("Contacto no existe.");
                }
            } else {
                System.out.println("Contacto no existe");
            }
        }
    }

    private static void addContact(RandomAccessFile fileR) throws IOException {
        String name, address;
        StringBuffer buffer;
        int zipCode, phoneNumber, id;
        long size, position;
        boolean debt, wrongData;
        LocalDate birthdate = null;
        double debtAmount = 0;

        if (fileR != null) {
            size = fileR.length();
            position = size - 152;
            fileR.seek(position);
            id = fileR.readInt();
            System.out.printf("Id: %d\n", id + 1);
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


            fileR.seek(size);
            fileR.writeInt(id + 1);
            fileR.writeBoolean(true);
            buffer = new StringBuffer(name);
            buffer.setLength(20);
            fileR.writeChars(buffer.toString());
            fileR.writeInt(phoneNumber);
            buffer = new StringBuffer(address);
            buffer.setLength(35);
            fileR.writeChars(buffer.toString());
            fileR.writeInt(zipCode);
            fileR.writeChars(birthdate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            fileR.writeBoolean(debt);
            fileR.writeDouble(debtAmount);
        }
    }

    private static void deleteContact(RandomAccessFile fileR, int id) {
        if (fileR != null) {

        }
    }

    private static void modifyDebt(RandomAccessFile fileR) {
        if (fileR != null) {

        }
    }

    private static void compactar(RandomAccessFile fileR) {
        if (fileR != null) {

        }
    }

    private static void showContact(RandomAccessFile fileR, int posicion) throws IOException {
        String name;
        String birthdate;
        String address;
        int id;
        int i;
        int phoneNumber;
        int zipCode;
        boolean debt;
        double debtAmount;
        name = "";
        birthdate = "";
        address = "";
        // volver a la posicion inicial.
        fileR.seek(posicion);

        id = fileR.readInt();
        // Saltar posicion para no mostrar el boolean de borrado.
        fileR.seek(posicion + 5);
        for (i = 0; i < 20; i++) {
            name += fileR.readChar();
        }
        phoneNumber = fileR.readInt();
        for (i = 0; i < 35; i++) {
            address += fileR.readChar();
        }
        zipCode = fileR.readInt();
        for (i = 0; i < 10; i++) {
            birthdate += fileR.readChar();
        }
        debt = fileR.readBoolean();
        debtAmount = fileR.readDouble();

        System.out.printf("Id: %d\nName: %s\nPhone number: %d\nAddress: %s\nZip code: %d\nDate of birth: %s\nDo I owe you money? %s\nHow much money do I owe? %.2f\n\n",
                id, name, phoneNumber, address, zipCode, birthdate, debt ? "Yes" : "No", debtAmount);
    }
}
