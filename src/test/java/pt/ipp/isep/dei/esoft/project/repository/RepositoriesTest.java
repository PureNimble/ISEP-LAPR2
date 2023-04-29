package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoriesTest {

    @Test
    void getInstance() {
        Repositories instance1 = Repositories.getInstance();
        Repositories instance2 = Repositories.getInstance();
        assertNotNull(instance1);
        assertNotNull(instance2);
        assertSame(instance1, instance2);
    }

    @Test
    void getRoleRepository() {
        Repositories repositories = Repositories.getInstance();
        assertNotNull(repositories.getRoleRepository(), "getRoleRepository should return a non-null object");

    }

    @Test
    void getAuthenticationRepository() {
        Repositories repositories = Repositories.getInstance();
        assertNotNull(repositories.getAuthenticationRepository(), "getAuthenticationRepository should return a non-null object");
    }

    @Test
    void getStoreRepository() {
        Repositories repositories = Repositories.getInstance();
        assertNotNull(repositories.getStoreRepository(), "getStoreRepository should return a non-null object");
    }

    @Test
    void getStateRepository() {
        Repositories repositories = Repositories.getInstance();
        assertNotNull(repositories.getStateRepository());
    }

    @Test
    void getEmployeeRepository() {
        Repositories repositories = Repositories.getInstance();
        assertNotNull(repositories.getEmployeeRepository());
    }

    @Test
    void getUserRepository() {
        Repositories repositories = Repositories.getInstance();
        assertNotNull(repositories.getUserRepository());
    }

    @Test
    void getPropertyTypeRepository() {
        Repositories repositories = Repositories.getInstance();
        assertNotNull(repositories.getPropertyTypeRepository());
    }

    @Test
    void getPublishedAnnouncementRepository() {
        Repositories repositories = Repositories.getInstance();
        assertNotNull(repositories.getPublishedAnnouncementRepository());
    }

    @Test
    void getTypeOfBusinessRepository() {
        Repositories repositories = Repositories.getInstance();
        assertNotNull(repositories.getTypeOfBusinessRepository());
    }

    @Test
    void getAnnouncementRequestRepository() {
        Repositories repositories = Repositories.getInstance();
        assertNotNull(repositories.getAnnouncementRequestRepository());
    }

    @Test
    void getComissionRepository() {
        Repositories repositories = Repositories.getInstance();
        assertNotNull(repositories.getComissionRepository());
    }

    @Test
    void getAvailableEquipmentRepository() {
        Repositories repositories = Repositories.getInstance();
        assertNotNull(repositories.getAvailableEquipmentRepository());
    }
}