package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;
import java.util.Objects;

public class StoreEmployeeDTO {
    
    private String employeeName;
    private int employeePassport;
    private int employeeTax;
    private long employeeNumber;
    private Address employeeAddress;
    private List<Role> roles;

    private String storeDesignation;
    private int storeId;
    private int storeListing;

    public StoreEmployeeDTO(String employeeName, int employeePassport, int employeeTax, long employeeNumber, Address employeeAddress, List<Role> employeeRoles) {
        this.employeeName = employeeName;
        this.employeePassport = employeePassport;
        this.employeeTax = employeeTax;
        this.employeeNumber = employeeNumber;
        this.employeeAddress = employeeAddress;
        this.roles = employeeRoles;
    }

    public StoreEmployeeDTO(String designation, int id, int listing) {
        this.storeDesignation = designation;
        this.storeId = id;
        this.storeListing = listing;
    }

    public String toStringStore() {
        return String.format("Name of the store: " + storeDesignation + " Id: " + storeId + " Number of properties: " + storeListing);
    }

    public String toStringEmployee(){
        return String.format("Name: " + employeeName + " Passport: " + employeePassport + " Tax: " + employeeTax + " Phone Number: " + employeeNumber + " Address: " + employeeAddress + " Roles: " + roles.toString());
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getEmployeePassport() {
        return employeePassport;
    }

    public void setEmployeePassport(int employeePassport) {
        this.employeePassport = employeePassport;
    }

    public int getEmployeeTax() {
        return employeeTax;
    }

    public void setEmployeeTax(int employeeTax) {
        this.employeeTax = employeeTax;
    }

    public long getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(long employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public Address getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(Address employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setEmployeeRoles(List<Role> roles) {
        this.roles = roles;
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



    /**
     * Checks if this AnnouncementRequestDto is equal to another object.
     *
     * @param o The object to compare against.
     * @return true if the objects are equal, false otherwise.
     */
    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreEmployeeDTO that = (StoreEmployeeDTO) o;
        return that.employeeTax && that.employeePassport && that.storeId && that.storeListing && storeDesignation.equals(that.storeDesignation) && employeeName.equals(that.employeeName) && employeeAddress.equals(that.employeeAddress) && roles.equals(that.roles);
    }*/

    @Override
    public int hashCode() {
        return Objects.hash(storeDesignation, storeId, storeListing, employeeName, employeePassport, employeeTax, employeeNumber, employeeAddress, roles);
    }
}
