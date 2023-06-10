package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

public class StoreEmployeeMapper {
     
    public List<StoreEmployeeDTO> toDTO(List<Store> storeList, List<Employee> employeeList){
        List<StoreEmployeeDTO> storeEmployeeDTOs = new ArrayList<>();

        for (Store store : storeList) {
            StoreEmployeeDTO storeEmployeeDTO;
            String storeName = store.getDesignation();
            int storeId = store.getId();
            int storeListing = store.getListing();
            storeEmployeeDTO = storeToDtoObject(storeName, storeId, storeListing);
            storeEmployeeDTOs.add(storeEmployeeDTO);

            for (Employee employee : employeeList) {
                if (employee.getStore().getDesignation().equals(storeName)) {
                    String employeeName = employee.getEmployeeName();
                    int employeePassport = employee.getPassportNumber();
                    int employeeTax = employee.getTaxNumber();
                    long employeeNumber = employee.getPhoneNumber();
                    List<Role> employeeRole = employee.getRoles();
                    Address employeeAddress = employee.getAddress();
                    storeEmployeeDTO = employeeToDtoObject(employeeName, employeePassport, employeeTax, employeeNumber, employeeAddress, employeeRole);
                    storeEmployeeDTOs.add(storeEmployeeDTO);
                }
            }
        }

        return storeEmployeeDTOs;
    }

    public StoreEmployeeDTO storeToDtoObject(String storeName, int storeId, int storeListing) {

        StoreEmployeeDTO storeEmployeeDTO;
        storeEmployeeDTO = new StoreEmployeeDTO(storeName, storeId, storeListing);
        return storeEmployeeDTO;
    }

    public StoreEmployeeDTO employeeToDtoObject(String employeeName, int employeePassport, int employeeTax, long employeeNumber, Address employeeAddress, List<Role> employeeRole) {

        StoreEmployeeDTO storeEmployeeDTO;
        storeEmployeeDTO = new StoreEmployeeDTO(employeeName, employeePassport, employeeTax, employeeNumber, employeeAddress, employeeRole);
        return storeEmployeeDTO;
    }
    
}

