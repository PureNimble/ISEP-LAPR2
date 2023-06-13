package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Store employee mapper.
 */
public class StoreEmployeeMapper {

    /**
     * To dto list.
     *
     * @param storeList    the store list
     * @param employeeList the employee list
     * @return the list
     */
    public List<StoreEmployeeDTO> toDTO(List<Store> storeList, List<Employee> employeeList){
        List<StoreEmployeeDTO> storeEmployeeDTOs = new ArrayList<>();
        
        for (Store store : storeList) {
            StoreEmployeeDTO storeEmployeeDTO;
            String storeName = store.getDesignation();
            int storeId = store.getId();
            int storeListing = store.getListing();
            
            List<Employee> employees = new ArrayList<>();
            for (Employee employee : employeeList) {
                if (employee.getStore().getDesignation().equals(storeName)) {
                    employees.add(employee);
                }
            }
            storeEmployeeDTO = toDtoObject(storeName, storeId, storeListing, employees);
            storeEmployeeDTOs.add(storeEmployeeDTO);
        }

        return storeEmployeeDTOs;
    }

    /**
     * To dto object store employee dto.
     *
     * @param storeName    the store name
     * @param storeId      the store id
     * @param storeListing the store listing
     * @param employee     the employee
     * @return the store employee dto
     */
    public StoreEmployeeDTO toDtoObject(String storeName, int storeId, int storeListing, List<Employee> employee) {

        StoreEmployeeDTO storeEmployeeDTO;
        storeEmployeeDTO = new StoreEmployeeDTO(storeName, storeId, storeListing, employee);
        return storeEmployeeDTO;
    }
    
}

