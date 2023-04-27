package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class User {
    private String email;
    private int passportNumber;

    private int taxNumber;

    private String name;

    private Address address;

    private int phoneNumber;

    public User(String email,  int passportNumber, int taxNumber, String name,Address address,int phoneNumber) {
        this.email = email;
        this.passportNumber = passportNumber;
        this.taxNumber = taxNumber;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public User(String email ,int passportNumber, int taxNumber, String name,int phoneNumber) {
        this.email = email;
        this.passportNumber = passportNumber;
        this.taxNumber = taxNumber;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public int getTaxNumber() {
        return taxNumber;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String toString(){
        return String.format("%s resident em %s que possui como documentos de identificação %s %s de email:%s e de numero de telefone %s , ",name,address,passportNumber,taxNumber,email,phoneNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return passportNumber == user.passportNumber && taxNumber == user.taxNumber && phoneNumber == user.phoneNumber && email.equals(user.email) && name.equals(user.name) && address.equals(user.address);
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



    public User clone() {
        return new User(this.email,this.passportNumber,this.taxNumber,this.name,this.address,this.phoneNumber);
    }
}
