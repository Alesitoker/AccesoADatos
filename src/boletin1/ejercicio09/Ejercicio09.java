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
    private static boolean deleted = false;

    public static void main(String[] args) {
        int option;
        boolean exit = false;
        int id;
        RandomAccessFile fileR = null;
        try {
            fileR = new RandomAccessFile("C:\\zProyectos\\AccesoADatos\\Esnuevoo\\Random\\ridier.dat", "rw");
//            fileR = new RandomAccessFile("D:\\Proyectos\\AccesoADatos\\Esnuevoo\\Random\\ridier.dat", "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        do {
            System.out.println("1.Consult all contacts.\n2.Consult a contact.\n3.Add a contact.\n4.Delete a contact.\n5.Modify debt.\n6.Compaction.\n7.Exit.");
            option = Teclado.leerEntre(1, 7, Teclado.Incluido.TODOS, Teclado.Tipo.INT);

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
                    System.out.println("1.By the end.\n2.In the first free position.");
                    option = Teclado.leerEntre(1, 2, Teclado.Incluido.TODOS, Teclado.Tipo.INT);
                    switch (option) {
                        case 1:
                            try {
                                addContactLast(fileR);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        case 2:
                            try {
                                addContactBetween(fileR);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
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
                        fileR = compact(fileR);
                        System.out.println("The file has been compacted successfully");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    exit = true;
            }
        } while (!exit);
        try {
            if (fileR != null)
            fileR.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void consultContacts(RandomAccessFile fileR) throws IOException {
        int position = 0, idCounter = 1;
        final int TOTAL_BYTES = 152, SKIP_INT = 4;
        long size;

        if (fileR != null) {
            size = (int) fileR.length();

            while (position < size) {
                // Nos posicionamos en el boleano de deleted.
                fileR.seek(position + SKIP_INT);
                if (fileR.readBoolean()) {
                    showContact(fileR, position);
                }
                position = TOTAL_BYTES * idCounter;
                idCounter++;
            }
        }
    }

    private static void consultContact(RandomAccessFile fileR, int id) throws IOException {
        int position;
        final int TOTAL_BYTES = 152, SKIP_INT = 4;
        long size;
        if (fileR != null) {
            size = fileR.length();
            position = TOTAL_BYTES*(id-1);
            if (position < size && position >= 0) {
                fileR.seek(position + SKIP_INT);
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

    private static void addContactLast(RandomAccessFile fileR) throws IOException {
        String name, address;
        StringBuffer buffer;
        int zipCode, phoneNumber, id;
        final int TOTAL_BYTES = 152;
        long sizeFile, position;
        boolean debt, wrongData;
        LocalDate birthdate = null;
        double debtAmount = 0;

        if (fileR != null) {
            sizeFile = fileR.length();
            position = sizeFile - TOTAL_BYTES;

            fileR.seek(position);
            id = fileR.readInt();
            id++;
            newContact(fileR, id, sizeFile);
        }
    }

    private static void addContactBetween(RandomAccessFile fileR) throws IOException {
        long position = 0, size;
        int idCounter = 0, id;
        boolean noDelete = true;
        final int TOTAL_BYTES = 152, SKIP_INT = 4;
        if (fileR != null) {
            size = fileR.length();

            while (position < size && noDelete) {
                // Nos posicionamos en el boleano de deleted.
                fileR.seek(position + SKIP_INT);
                noDelete = fileR.readBoolean();
                if (!noDelete) {
                    fileR.seek(position);
                    id = fileR.readInt();
                    newContact(fileR, id, position);
                }
                idCounter++;
                position = TOTAL_BYTES * idCounter;
            }
            if (noDelete) {
                addContactLast(fileR);
//                System.out.println("We could not add a new contact");
            }
        }
    }

    private static void newContact(RandomAccessFile fileR, int id, long position) throws IOException {
        String name, address;
        int phoneNumber;
        boolean wrongData;
        int zipCode;
        double debtAmount = 0;
        LocalDate birthdate = null;
        boolean debt;
        StringBuffer buffer;

        System.out.printf("Id: %d\n", id);
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
                System.out.println("the date must be in the format dd-mm-yyyy");
            }
        } while (wrongData);
        debt = Teclado.leerBoolean("Do you owe money?", "Yes", "No");
        if (debt) {
            System.out.println("How much money do you owe?");
            debtAmount = Teclado.leerNumero(Teclado.Tipo.DOUBLE);
        }


        fileR.seek(position);
        fileR.writeInt(id);
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

    private static void deleteContact(RandomAccessFile fileR, int id) throws IOException {
        int position;
        final int TOTAL_BYTES = 152, SKIP_INT = 4;
        if (fileR != null) {
            position = TOTAL_BYTES * (id-1);
            if (position < fileR.length()) {
                fileR.seek(position + SKIP_INT);
                if (fileR.readBoolean()) {
                    fileR.seek(position + SKIP_INT);
                    fileR.writeBoolean(false);
                    if (!deleted) {
                        deleted = true;
                    }
                    System.out.println("The contact has been successfully removed");
                } else {
                    System.out.println("The contact does not exist.");
                }
            }  else {
                System.out.println("The contact does not exist.");
            }
        }
    }

    private static void modifyDebt(RandomAccessFile fileR, int id) throws IOException {
        int position;
        boolean debt;
        double debtAmount;
        final int TOTAL_BYTES = 152, POSITION_DEBT = 143, SKIP_INT = 4;
        if (fileR != null) {
            position = TOTAL_BYTES * (id-1);
            if (position < fileR.length()) {
                fileR.seek(position + SKIP_INT);
                if (fileR.readBoolean()) {
                    debt = Teclado.leerBoolean("Do you owe him money?", "Yes", "No");
                    fileR.seek(position + POSITION_DEBT);
                    if (debt) {
                        System.out.println("How much money do you owe?");
                        debtAmount = Teclado.leerNumero(Teclado.Tipo.DOUBLE);
                        fileR.writeBoolean(debt);
                        fileR.writeDouble(debtAmount);
                        System.out.println("The debt has been modified satisfactorily");
                    } else {
                        fileR.writeBoolean(debt);
                        fileR.writeDouble(0);
                    }
                } else {
                    System.out.println("The contact does not exist.");
                }
            }
        }
    }

    private static RandomAccessFile compact(RandomAccessFile fileR) throws IOException {
        int position = 0, idContador = 0, newId = 0;
        final int TOTAL_BYTES = 152, SKIP_INT = 4;
        RandomAccessFile compactor;
        File dir, fCompactator;
        dir = new File("C:\\zProyectos\\AccesoADatos\\Esnuevoo\\Random");
        fCompactator = new File("C:\\zProyectos\\AccesoADatos\\Esnuevoo\\Random\\compactator.dat");
        File fileRandom = new File("C:\\zProyectos\\AccesoADatos\\Esnuevoo\\Random\\ridier.dat");
//        dir = new File("D:\\Proyectos\\AccesoADatos\\Esnuevoo\\Random");
//        fCompactator = new File("D:\\Proyectos\\AccesoADatos\\Esnuevoo\\Random\\compactator.dat");
//        File fileRandom = new File("D:\\Proyectos\\AccesoADatos\\Esnuevoo\\Random\\ridier.dat");

        // comprobar si he borrado en otra ejecucion.
        if (!deleted) {
            checkDeleted(fileR);
        }
        if (fileR != null && deleted) {
            // iniciamos dentro para que no se cree un nuevo archivo en el caso que no se tenga que compactar.
            compactor = new RandomAccessFile("C:\\zProyectos\\AccesoADatos\\Esnuevoo\\Random\\compactator.dat", "rw");
//            compactor = new RandomAccessFile("D:\\Proyectos\\AccesoADatos\\Esnuevoo\\Random\\compactator.dat", "rw");
            while (position < fileR.length()) {
                // Nos posicionamos en el boleano de borrado.
                fileR.seek(position + SKIP_INT);
                if (fileR.readBoolean()) {
                    newId++;
                    makeCompaction(fileR, compactor, position, newId);
                }
                idContador++;
                position = TOTAL_BYTES * idContador;
            }
            fileR.close();
            compactor.close();
            fileRandom.delete();
            fCompactator.renameTo(new File(dir, "ridier.dat"));
            return new RandomAccessFile("C:\\zProyectos\\AccesoADatos\\Esnuevoo\\Random\\ridier.dat", "rw");
//            return new RandomAccessFile("D:\\Proyectos\\AccesoADatos\\Esnuevoo\\Random\\ridier.dat", "rw");
        }
        return null;
    }

    private static void checkDeleted(RandomAccessFile fileR) throws IOException {
        int position = 0, idCounter = 0;
        final int TOTAL_BYTES = 152, SKIP_INT = 4;
        long sizeTotal = fileR.length();

        while (position < sizeTotal && !deleted) {
            // Nos posicionamos en el boleano de borrado.
            fileR.seek(position + SKIP_INT);
            if (!fileR.readBoolean()) {
                deleted = true;
            }
            idCounter++;
            position = TOTAL_BYTES * idCounter;
        }
    }

    private static void makeCompaction(RandomAccessFile fileToCompact, RandomAccessFile compactor, int position, int id) throws IOException {
        String name, address, birthdate;
        StringBuffer buffer;
        int i, phoneNumber, zipCode;
        final int SKIP_BOOLEAN = 5;
        boolean debt;
        double debtAmount;
        name = "";
        birthdate = "";
        address = "";

        // Nos posicionamos despues del boolean.
        fileToCompact.seek(position + SKIP_BOOLEAN);
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

        compactor.writeInt(id);
        compactor.writeBoolean(true);
        buffer = new StringBuffer(name);
        buffer.setLength(20);
        compactor.writeChars(buffer.toString());
        compactor.writeInt(phoneNumber);
        buffer = new StringBuffer(address);
        buffer.setLength(35);
        compactor.writeChars(buffer.toString());
        compactor.writeInt(zipCode);
        compactor.writeChars(birthdate);
        compactor.writeBoolean(debt);
        compactor.writeDouble(debtAmount);
    }

    private static void showContact(RandomAccessFile fileR, int posicion) throws IOException {
        String name, address, birthdate;
        int i, id, phoneNumber, zipCode;
        final int SKIP_BOOLEAN = 1;
        boolean debt;
        double debtAmount;
        name = "";
        birthdate = "";
        address = "";

        fileR.seek(posicion);
        id = fileR.readInt();
        // Saltar posicion para no mostrar el boolean de borrado.
        fileR.skipBytes(SKIP_BOOLEAN);
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
