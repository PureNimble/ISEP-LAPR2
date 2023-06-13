package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;
import java.util.Objects;

/**
 * The type Store employee dto.
 */
public class StoreEmployeeDTO {
    
    private List<Employee> employees;

    private String storeDesignation;
    private int storeId;
    private int storeListing;

    /**
     * Instantiates a new Store employee dto.
     *
     * @param designation the designation
     * @param id          the id
     * @param listing     the listing
     * @param employees   the employees
     */
    public StoreEmployeeDTO(String designation, int id, int listing, List<Employee> employees) {
        this.storeDesignation = designation;
        this.storeId = id;
        this.storeListing = listing;
        this.employees = employees;
    }

    /**
     * To string store string.
     *
     * @return the string
     */
    public String toStringStore() {
        return String.format("Name of the store: %s Id: %s Number of properties: %s", storeDesignation, storeId, storeListing);
    }

    /**
     * To string employee string.
     *
     * @param employee the employee
     * @return the string
     */
    public String toStringEmployee(Employee employee) {
        return String.format("Name: %s Passport: %s Tax: %s Phone Number: %s Address: %s Roles: %s",
                employee.getName(), employee.getPassportNumber(), employee.getTaxNumber(),
                employee.getPhoneNumber(), employee.getAddress(), employee.getRoles());
    }

    /**
     * Gets store designation.
     *
     * @return the store designation
     */
    public String getStoreDesignation() {
        return storeDesignation;
    }

    /**
     * Sets store designation.
     *
     * @param storeDesignation the store designation
     */
    public void setStoreDesignation(String storeDesignation) {
        this.storeDesignation = storeDesignation;
    }

    /**
     * Gets store id.
     *
     * @return the store id
     */
    public int getStoreId() {
        return storeId;
    }

    /**
     * Sets store id.
     *
     * @param storeId the store id
     */
    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    /**
     * Gets store listing.
     *
     * @return the store listing
     */
    public int getStoreListing() {
        return storeListing;
    }

    /**
     * Sets store listing.
     *
     * @param storeListing the store listing
     */
    public void setStoreListing(int storeListing) {
        this.storeListing = storeListing;
    }

    /**
     * Gets employees.
     *
     * @return the employees
     */
    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeDesignation, storeId, storeListing, employees);
    }
}
