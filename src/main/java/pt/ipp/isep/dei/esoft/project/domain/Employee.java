package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Employee {
    private final Store store;
    private final Role role;

    public Employee(String email, String name, Store store, int phoneNumber, Role role,Address address,int taxNumber,int passportNumber) {

        super(email,passportNumber,taxNumber,name,address,phoneNumber);
        this.store = store;
        this.role = role;
    }

    public Store getStore() {
        return store;
    }

    public Role getRole() {
        return role;
    }

    public String toString(){
        return String.format(super.toString() + "funcionário da loja %s que desempenha o papel de %s",store.getDesignation(),role.getDescription());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return store.equals(employee.store) && role.equals(employee.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), store, role);
    }
}
