package boletin1.ejercicio09;

import utils.Teclado;

import java.io.File;
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
//        File f = new File("C:\\zProyectos\\AccesoADatos\\Esnuevoo\\Random\\ridier.dat");
        File f = new File("D:\\Proyectos\\AccesoADatos\\Esnuevoo\\Random\\ridier.dat");
        RandomAccessFile fileR = null;
        try {
//            fileR = new RandomAccessFile("C:\\zProyectos\\AccesoADatos\\Esnuevoo\\Random\\ridier.dat", "rw");
            fileR = new RandomAccessFile("D:\\Proyectos\\AccesoADatos\\Esnuevoo\\Random\\ridier.dat", "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        do {
            System.out.println("1.Consult all contacts.\n2.Consult a contact.\n3.Add a contact.\n4.Delete a contact.\n5.Modify debt.\n6.Compaction.\n7.Exit.");
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
                    System.out.println("Enter the contact id to consult:");
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
                    System.out.println("Enter the id of the contact to be deleted:");
                    id = Teclado.leerNumero(Teclado.Tipo.INT);
                    try {
                        deleteContact(fileR, id);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    System.out.println("Enter contact to modify debt:");
                    id = Teclado.leerNumero(Teclado.Tipo.INT);
                    try {
                        modifyDebt(fileR, id);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    try {
                        compact(fileR, f);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    exit = true;
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
        int position = 0, idCounter = 1;
        final int TOTAL_BYTES = 152;
        long size;

        if (fileR != null) {
            size = (int) fileR.length();

            while (position < size) {
                // Nos posicionamos en el boleano de borrado.
                fileR.seek(position + 4);
                if (fileR.readBoolean()) {
                    showContact(fileR, position);
                }

                position = TOTAL_BYTES * idCounter;
                fileR.seek(position);
                idCounter++;
            }
        }
    }

    private static void consultContact(RandomAccessFile fileR, int id) throws IOException {
        int position;
        final int TOTAL_BYTES = 152;
        long size;
        if (fileR != null) {
            size = fileR.length();
            position = TOTAL_BYTES*(id-1);
            if (position < size && position >= 0) {
                position = 152 * (id-1);
                fileR.seek(position + 4);
                if (fileR.readBoolean()) {
                    showContact(fileR, position);
                } else {
                    System.out.println("The contact does not exist");
                }
            } else {
                System.out.println("The contact does not exist");
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
                    System.out.printf("the date must be in the format dd-mm-yyyy\n");
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

    private static void deleteContact(RandomAccessFile fileR, int id) throws IOException {
        int position;
        final int TOTAL_BYTES = 152;
        if (fileR != null) {
            position = TOTAL_BYTES * (id-1);
            if (position < fileR.length()) {
                fileR.seek(position+4);
                fileR.writeBoolean(false);
            }  else {
                System.out.printf("The contact does not exist.");
            }
        }
    }

    private static void modifyDebt(RandomAccessFile fileR, int id) throws IOException {
        int position;
        double debtAmount;
        final int TOTAL_BYTES = 152, POSITION_DEBT = 144;
        if (fileR != null) {
            position = TOTAL_BYTES * (id-1);
            if (position < fileR.length()) {
                fileR.seek(position + POSITION_DEBT);
                System.out.println("How much money do you owe?");
                debtAmount = Teclado.leerNumero(Teclado.Tipo.DOUBLE);
                fileR.writeDouble(debtAmount);
            }
        }
    }

    private static void compact(RandomAccessFile fileR, File f) throws IOException {
        int position = 0, idContador = 0, newId = 0;
        final int TOTAL_BYTES = 152;
        RandomAccessFile compactor;
        File dir, fCompactator;
//        dir = new File("C:\\zProyectos\\AccesoADatos\\Esnuevoo\\Random");
//        fCompactator = new File(dir, "compactator.dat");
//        compactor = new RandomAccessFile("C:\\zProyectos\\AccesoADatos\\Esnuevoo\\Random\\compactator.dat", "rw");
        dir = new File("D:\\Proyectos\\AccesoADatos\\Esnuevoo\\Random");
        fCompactator = new File("D:\\Proyectos\\AccesoADatos\\Esnuevoo\\Random\\compactator.dat");
        File file = new File("D:\\Proyectos\\AccesoADatos\\Esnuevoo\\Random\\ridier.dat");
        compactor = new RandomAccessFile("D:\\Proyectos\\AccesoADatos\\Esnuevoo\\Random\\compactator.dat", "rw");

        fCompactator.createNewFile();
        if (fileR != null) {
            while (position < fileR.length()) {
                // Nos posicionamos en el boleano de borrado.
                fileR.seek(position + 4);
                if (fileR.readBoolean()) {
                    newId++;
                    makeCompaction(fileR, compactor, position, newId);
                }
                position = TOTAL_BYTES * idContador;
                fileR.seek(position);
                idContador++;
            }
            fileR.close();
            compactor.close();
            file.delete();
            fCompactator.renameTo(new File(dir, "ridier.dat"));
        }
    }

    private static void makeCompaction(RandomAccessFile fileToCompact, RandomAccessFile compactor, int position, int id) throws IOException {
        String name, address, birthdate;
        StringBuffer buffer;
        int i, phoneNumber, zipCode;
        boolean debt;
        double debtAmount;
        name = "";
        birthdate = "";
        address = "";

        fileToCompact.seek(position + 5);
        for (i = 0; i < 20; i++) {
            name += fileToCompact.readChar();
        }
        phoneNumber = fileToCompact.readInt();
        for (i = 0; i < 35; i++) {
            address += fileToCompact.readChar();
        }
        zipCode = fileToCompact.readInt();
        for (i = 0; i < 10; i++) {
            birthdate += fileToCompact.readChar();
        }
        debt = fileToCompact.readBoolean();
        debtAmount = fileToCompact.readDouble();

        compactor.writeInt(id); // 4
        compactor.writeBoolean(true); // 1
        buffer = new StringBuffer(name);
        buffer.setLength(20);
        compactor.writeChars(buffer.toString()); // 40
        compactor.writeInt(phoneNumber); // 4
        buffer = new StringBuffer(address);
        buffer.setLength(35);
        compactor.writeChars(buffer.toString()); // 70
        compactor.writeInt(zipCode); // 4
        compactor.writeChars(birthdate); // 20
        compactor.writeBoolean(debt); // 1
        compactor.writeDouble(debtAmount); // 8
    }

    private static void showContact(RandomAccessFile fileR, int posicion) throws IOException {
        String name, address, birthdate;
        int i, id, phoneNumber, zipCode;
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
