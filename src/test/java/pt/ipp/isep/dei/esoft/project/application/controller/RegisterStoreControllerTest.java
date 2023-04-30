package pt.ipp.isep.dei.esoft.project.application.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.StateRepository;
import pt.ipp.isep.dei.esoft.project.repository.StoreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RegisterStoreControllerTest {

    private StateRepository stateRepository;
    private StoreRepository storeRepository;
    private List<Store> stores;
    private State state;
    private List<District> districts;

    private City losAngeles;

    private State california;

    private District district1;

    @Test
    void registerStore() {
        // Adding store to the repository
        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store = new Store("Store1", 1, address, 123456789, "store1@test.com");
        Optional<Store> result = storeRepository.add(store);

        // Checking if store is added to the repository and returned correctly
        assertTrue(storeRepository.getStores().contains(store));
        assertTrue(result.isPresent());
        assertEquals(store, result.get());
    }



    @BeforeEach
    void setUp4() {
        stores = new ArrayList<>();
        storeRepository = new StoreRepository();
    }

    @Test
    void getStore() {
        Address address = new Address("Streett Test", 45672, new District("Test District"), new City("Test City"), new State("Test State"));
        Store store1 = new Store("Store1", 1, address, 123456789, "store1@test.com");
        Store store2 = new Store("Test Store", 2, address, 5551234, "test@store.com");
        stores.add(store1);
        stores.add(store2);
        storeRepository.add(store1);
        storeRepository.add(store2);

        assertEquals(stores, storeRepository.getStores());
    }

    @BeforeEach
    void setUp3() {
        stateRepository = new StateRepository();

        // Add some sample data to the repository for testing
        losAngeles = new City("Los Angeles");
        List<City> cities = new ArrayList<>();
        cities.add(losAngeles);

        district1 = new District("District 1",cities);
        List<District> districts = new ArrayList<>();
        districts.add(district1);

        california = new State("California",districts);


        stateRepository.add(california);
    }

        @Test
    void getCityByDescription() {
        City losAngeles = stateRepository.getCityByDescription("Los Angeles", district1);
        Assertions.assertEquals("Los Angeles", "Los Angeles");

        // Test for IllegalArgumentException if state doesn't exist
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            stateRepository.getCityByDescription("New York", new District("District 1"));
        });
    }

    @Test
    void getDistrictByDescription() {
        District district1 = stateRepository.getDistrictByDescription("District 1", california);
        Assertions.assertEquals("District 1", "District 1");

        // Test for IllegalArgumentException if state doesn't exist
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            stateRepository.getDistrictByDescription("District 1", new State("California"));
        });
    }

    @Test
    void getDistrict() {
        assertEquals(district1, california.getDistricts().get(0));
    }

    @Test
    void getCities() {
        assertEquals(losAngeles,district1.getCities().get(0));
    }

    @Test
    void getState() {
        assertEquals("California", california.getState());
    }

}