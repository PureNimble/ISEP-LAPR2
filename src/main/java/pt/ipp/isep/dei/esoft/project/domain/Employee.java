package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;
import java.util.Objects;

/**

 The Employee class represents an employee object that works at a specific store and has a specific role.

 It extends the Person class and inherits its properties (email, name, phoneNumber, address, taxNumber, passportNumber).
 */
public class Employee extends User {

    private final Store store;
    private List<Role> roles;

    public Employee(String email, int passportNumber, int taxNumber, String name, int phoneNumber, Store store, List<Role> roles,Address address) {
        super(email,passportNumber,taxNumber,name,address,phoneNumber);
        this.store = store;
        this.roles = roles;
    }

    public Store getStore() {
        return store;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public String toString(){
        return String.format(super.toString() + "\nfuncionário da loja %s que desempenha o papel de %s",store.getDesignation(),roles.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return store.equals(employee.store) && roles.equals(employee.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), store, roles);
    }
}
