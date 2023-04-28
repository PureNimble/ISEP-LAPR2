package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StoreRepository {
    private final List<Store> stores = new ArrayList<>();

    public Store getStoreByDescription(String storeDescription) {

        Store newStore = new Store(storeDescription);
        Store store = null;
        if (stores.contains(newStore)) {
            store = stores.get(stores.indexOf(newStore));
        }
        if (store == null) {
            throw new IllegalArgumentException(
                    "Type of Business requested for [" + storeDescription + "] does not exist.");
        }
        return store;
    }


    public Optional<Store> add(Store store) {

        Optional<Store> newStore = Optional.empty();
        boolean operationSuccess = false;

        if (validateStore(store)) {
            newStore = Optional.of(store.clone());
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
