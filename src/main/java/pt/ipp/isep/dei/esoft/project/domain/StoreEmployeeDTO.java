package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;
import java.util.Objects;

public class StoreEmployeeDTO {
    
    private List<Employee> employees;

    private String storeDesignation;
    private int storeId;
    private int storeListing;

    public StoreEmployeeDTO(String designation, int id, int listing, List<Employee> employees) {
        this.storeDesignation = designation;
        this.storeId = id;
        this.storeListing = listing;
        this.employees = employees;
    }

    public String toStringStore() {
        return String.format("Name of the store: %s Id: %s Number of properties: %s", storeDesignation, storeId, storeListing);
    }

    public String toStringEmployee(Employee employee) {
        return String.format("Name: %s Passport: %s Tax: %s Phone Number: %s Address: %s Roles: %s",
                employee.getName(), employee.getPassportNumber(), employee.getTaxNumber(),
                employee.getPhoneNumber(), employee.getAddress(), employee.getRoles());
    }

    public String getStoreDesignation() {
        return storeDesignation;
    }

    public void setStoreDesignation(String storeDesignation) {
        this.storeDesignation = storeDesignation;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getStoreListing() {
        return storeListing;
    }

    public void setStoreListing(int storeListing) {
        this.storeListing = storeListing;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeDesignation, storeId, storeListing, employees);
    }
}
