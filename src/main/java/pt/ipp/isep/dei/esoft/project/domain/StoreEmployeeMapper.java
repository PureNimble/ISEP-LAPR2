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
            storeEmployeeDTO = toDtoObject(storeName, storeId, storeListing, null);
            storeEmployeeDTOs.add(storeEmployeeDTO);
            
            List<StoreEmployeeDTO> tempEmployeeDTOs = new ArrayList<>();
            for (Employee employee : employeeList) {
                if (employee.getStore().getDesignation().equals(storeName)) {
                    storeEmployeeDTO = toDtoObject(null, 0, 0, employee);
                    tempEmployeeDTOs.add(storeEmployeeDTO);
                }
            }
            storeEmployeeDTOs.addAll(tempEmployeeDTOs);
        }

        return storeEmployeeDTOs;
    }

    public StoreEmployeeDTO toDtoObject(String storeName, int storeId, int storeListing, Employee employee) {

        StoreEmployeeDTO storeEmployeeDTO;
        storeEmployeeDTO = new StoreEmployeeDTO(storeName, storeId, storeListing, employee);
        return storeEmployeeDTO;
    }
    
}

