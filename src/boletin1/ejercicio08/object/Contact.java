package boletin1.ejercicio08.object;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Contact implements Serializable {
    private static final long serialVersionUID = 1612259354624465048L;
    private String name, address;
    private int phoneNumber, zipCode;
    private LocalDate birthdate;
    private boolean debt;
    private double debtAmount;

    public Contact(String name, int phoneNumber, String address,int zipCode, LocalDate birthdate, boolean debt, double debtAmount) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.zipCode = zipCode;
        this.birthdate = birthdate;
        this.debt = debt;
        this.debtAmount = debtAmount;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nPhone number: %d\nAddress: %s\nZip code: %d\nDate of birth: %s\nDo I owe you money? %s\nHow much money do I owe? %.2f\n\n", name, phoneNumber, address, zipCode, birthdate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), debt? "Yes":"No", debtAmount);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public int getZipCode() {
        return zipCode;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public boolean isDebt() {
        return debt;
    }

    public double getDebtAmount() {
        return debtAmount;
    }
}
