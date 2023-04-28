package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**

 The User class represents a user with personal information and contact details.
 */
public class User extends Person {
    public User(String email,  int passportNumber, int taxNumber, String name,Address address,int phoneNumber) {
        super(email,passportNumber,taxNumber,name,address,phoneNumber);
    }

    public User(String email ,int passportNumber, int taxNumber, String name,int phoneNumber) {
        super(email,passportNumber,taxNumber,name,phoneNumber);
    }
    public int compare(String email){
        return super.compare(email);
    }

}

