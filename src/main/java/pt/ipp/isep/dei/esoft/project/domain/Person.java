package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Person {
    private String email;
    private int passportNumber;

    private int taxNumber;

    private String name;

    private Address address;

    private int phoneNumber;

    public Person(String email, int passportNumber, int taxNumber, String name, Address address, int phoneNumber) {
        this.email = email;
        this.passportNumber = passportNumber;
        this.taxNumber = taxNumber;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Person(String email, int passportNumber, int taxNumber, String name, int phoneNumber) {
        this.email = email;
        this.passportNumber = passportNumber;
        this.taxNumber = taxNumber;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String toString(){
        return String.format("%s residente em %s que possui como documentos de identificação %s %s de email:%s e de numero de telefone %s , ",name,address,passportNumber,taxNumber,email,phoneNumber);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return passportNumber == person.passportNumber && taxNumber == person.taxNumber && phoneNumber == person.phoneNumber && email.equals(person.email) && name.equals(person.name) && address.equals(person.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, passportNumber, taxNumber, name, address, phoneNumber);
    }


    public int compare(String email){
        if (email.equals(this.email)){
            return 0;
        }
        return 1;
    }
}
