package boletin1.ejercicio10;

import boletin1.ejercicio08.object.Contact;

import java.util.ArrayList;
import java.util.List;

public class ListContact {
    private List<Contact> contacts = new ArrayList<>();

    public List<Contact> getContacts() {
        return contacts;
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public Contact getContact(int position) {
        return contacts.get(position);
    }
}
