package boletin1.ejercicio10;

import boletin1.ejercicio08.object.Contact;

import java.util.ArrayList;
import java.util.List;

public class ListContact {
    private List<Contact> contacts = new ArrayList<>();

    public List<Contact> getContacts() {
        return new ArrayList<>(contacts);
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }
}
