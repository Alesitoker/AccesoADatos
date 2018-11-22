package boletin1.ejercicio15;

import boletin1.ejercicio08.object.Contact;
import boletin1.ejercicio10.ListContact;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Ejercicio15 {
    public static void main(String[] args) {
        File json = new File("C:\\zProyectos\\AccesoADatos\\src\\boletin1\\ejercicio15\\contact.json");
        File f15 = new File("C:\\zProyectos\\AccesoADatos\\src\\boletin1\\ejercicio15", "File15.txt");
        FileReader rd = null;
        FileWriter wr = null;
        String start = "PHONEBOOK";
        String end = "END OF THE PHONEBOOK";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ListContact listContact = new ListContact();
        Gson gson = new Gson();
        Type typeListContact = new TypeToken<ListContact>(){}.getType();

        try {
            rd = new FileReader(json);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        listContact = gson.fromJson(rd, typeListContact);

        try {
            f15.createNewFile();
            wr = new FileWriter(f15);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            wr.write(String.format("%090d\n", 0).replace("0", "*"));
            wr.write(String.format("%40s\n", start));
            wr.write(String.format("%090d\n", 0).replace("0", "*"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Contact contact: listContact.getContacts()) {
            try {
                wr.write(String.format("Name:%16s%s\n", "", contact.getName()));
                wr.write(String.format("Phone:%24s\n", contact.getPhoneNumber()));
                wr.write(String.format("Address:%13s%s\n", "", contact.getAddress()));
                wr.write(String.format("Zip code:%12s%s\n", "", contact.getZipCode()));
                wr.write(String.format("Birthdate:%21s\n", formatter.format(contact.getBirthdate())));
                wr.write(String.format("I owe money?:%8s%s\n", "", contact.isDebt() ? "Yes" : "No"));
                wr.write(String.format("How much should you? %f\n", contact.getDebtAmount()));
                wr.write(String.format("%090d\n", 0).replace("0", "*"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            wr.write(String.format("%50s\n", end));
            wr.write(String.format("%090d\n", 0).replace("0", "*"));
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
