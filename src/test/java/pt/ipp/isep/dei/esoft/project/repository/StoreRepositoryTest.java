package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StoreRepositoryTest {
    private StoreRepository storeRepository;
    private List<Store> stores;

    @BeforeEach
    void setUp() {
        stores = new ArrayList<>();
        storeRepository = new StoreRepository();
    }

    @Test
    void getStoreByDescription() {

        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store1 = new Store("Store1", 1, address, 123456789, "store1@test.com",0);
        Store store2 = new Store("Test Store", 2, address, 5551234, "test@store.com",0);

        stores.add(store1);
        stores.add(store2);
        storeRepository.add(store1);
        storeRepository.add(store2);

        Store result = storeRepository.getStoreByDescription("2");

        assertEquals(store2, result);
    }

    @Test
    void add() {

        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Store1", 1, address, 123456789, "store1@test.com",0);
        Optional<Store> result = storeRepository.add(store);

        assertTrue(storeRepository.getStores().contains(store));
        assertTrue(result.isPresent());
        assertEquals(store, result.get());
    }

    @Test
    void getStores() {

        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store1 = new Store("Store1", 1, address, 123456789, "store1@test.com",0);
        Store store2 = new Store("Test Store", 2, address, 5551234, "test@store.com",0);
        stores.add(store1);
        stores.add(store2);
        storeRepository.add(store1);
        storeRepository.add(store2);

        assertEquals(stores, storeRepository.getStores());
    }
}