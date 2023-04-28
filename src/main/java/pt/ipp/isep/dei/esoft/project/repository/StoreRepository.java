package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StoreRepository {
    private final List<Store> stores = new ArrayList<>();

    public Store getStoreByDescription(String storeIDDescription) {

        Store newStore = new Store();

        for (Store store: stores) {
            if (store.getId() == Integer.parseInt(storeIDDescription)){
                newStore = store;
            }
        }

        return newStore;
    }


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

    private boolean validateStore(Store store) {
        boolean isValid = !stores.contains(store);
        return isValid;

    }

    public List<Store> getStores() {
        return stores;
    }

}
