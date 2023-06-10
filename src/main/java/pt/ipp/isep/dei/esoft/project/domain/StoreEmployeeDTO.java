package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class StoreEmployeeDTO {
    
    private Employee employee;

    private String storeDesignation;
    private int storeId;
    private int storeListing;

    public StoreEmployeeDTO(String designation, int id, int listing, Employee employee) {
        this.storeDesignation = designation;
        this.storeId = id;
        this.storeListing = listing;
        this.employee = employee;
    }

    public String toStringStore() {
        return String.format("Name of the store: " + storeDesignation + " Id: " + storeId + " Number of properties: " + storeListing);
    }

    public String toStringEmployee(){
        return String.format("Name: " + employee.getName() + " Passport: " + employee.getPassportNumber() + " Tax: " + employee.getTaxNumber() + " Phone Number: " + employee.getPhoneNumber() + " Address: " + employee.getAddress() + " Roles: " + employee.getRoles());
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

    public Employee getEmployee() {
        return employee;
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeDesignation, storeId, storeListing, employee);
    }
}
