package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.PublishedAnnouncement;
import pt.ipp.isep.dei.esoft.project.domain.Store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * The StoreRepository class is responsible for managing a list of stores.
 */
public class StoreRepository {
    /**
     * The list of stores managed by this repository.
     */
    private final List<Store> stores = new ArrayList<>();

    /**
     * Returns the store with the specified ID.
     *
     * @param storeIDDescription the description of the store ID
     * @return the store with the specified ID, or a new store object if not found
     */
    public Store getStoreByDescription(String storeIDDescription) {
        Store newStore = new Store();

        for (Store store : stores) {
            if (store.getId() == Integer.parseInt(storeIDDescription)) {
                newStore = store;
            }
        }

        return newStore;
    }

    /**
     * Adds a new store to the repository.
     *
     * @param store the store to add
     * @return an Optional containing the newly added store if successful, empty Optional otherwise
     */
    public Optional<Store> add(Store store) {
        Optional<Store> newStore = Optional.empty();
        boolean operationSuccess = false;

        if (validateStore(store)) {
            newStore = Optional.of(store);
            operationSuccess = stores.add(newStore.get());
        }

        if (!operationSuccess) {
            newStore = Optional.empty();
        }

        return newStore;
    }

    /**
     * Validates the specified store object.
     *
     * @param store the store object to validate
     * @return true if the store object is valid, false otherwise
     */
    private boolean validateStore(Store store) {
        boolean isValid = !stores.contains(store);
        return isValid;
    }

    /**
     * Returns a list of all stores managed by this repository.
     *
     * @return a list of all stores managed by this repository
     */
    public List<Store> getStores() {
        return stores;
    }

    public void getStoresProperty(List<PublishedAnnouncement> announcementList, List<Employee> employees) {

        List<Store> storesList = getStores();
        int counter = 0;
        for (Store stores : storesList) {
            for (Employee employee : employees) {
                if (employee.getStore().equals(stores)) {
                    for (PublishedAnnouncement publishedAnnouncement : announcementList) {
                        if (publishedAnnouncement.getAgent().equals(employee)) {
                            counter++;
                        }
                    }
                }
            }
            stores.setListing(counter);
            counter = 0;
        }
    }

    public List<Store> getStoresByMostListings() {
        List<Store> sortedStores = new ArrayList<>(stores);

        Collections.sort(sortedStores, compareToDescendingList);

        return sortedStores;
    }

    Comparator<Store> compareToDescendingList = new Comparator<Store>() {
        @Override
        public int compare(Store store1, Store store2) {
            int listing1 = store1.getListing();
            int listing2 = store2.getListing();

            if (listing2 < listing1) {
                return -1;
            } else if (listing2 > listing1) {
                return 1;
            } else {
                return 0;
            }
        }
    };

    public void createStoreByFileReading(ArrayList<String[]> arrayListStoreInformations) {

        int aux = 0;
        int id = 0;
        Address address;
        String designation;
        long phoneNumber;
        String email;

        for (String[] storeInformations : arrayListStoreInformations) {
                if (aux > 0){
                    id = Integer.parseInt(storeInformations[0]);
                    designation = storeInformations[1];
                    address = new Address(storeInformations[2]);
                    phoneNumber = Long.parseLong(storeInformations[3].replaceAll("-",""));
                    email = storeInformations[4];

                    Store store = new Store(designation,id,address,phoneNumber,email,0);

                    if (!stores.contains(store))
                        stores.add(store);
                }else {
                    aux = 1;
                }


        }


    }


}
