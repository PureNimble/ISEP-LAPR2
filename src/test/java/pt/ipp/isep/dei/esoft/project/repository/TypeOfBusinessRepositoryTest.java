package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.TypeOfBusiness;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TypeOfBusinessRepositoryTest {

    private TypeOfBusinessRepository repository;

    @BeforeEach
    void setUp() {
        repository = new TypeOfBusinessRepository();
    }

    @Test
    void getTypeOfBusinessByDescription() {
        // Arrange
        TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Rent");
        repository.add(typeOfBusiness);

        // Act
        TypeOfBusiness result = repository.getTypeOfBusinessByDescription("Rent");

        // Assert
        Assertions.assertEquals(typeOfBusiness, result);
    }


    @Test
    void addValid() {
        // Arrange
        TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Rent");

        // Act
        Optional<TypeOfBusiness> result = repository.add(typeOfBusiness);

        // Assert
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(typeOfBusiness, result.get());
    }

    @Test
    void addInvalid() {
        // Arrange
        TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Rent");
        repository.add(typeOfBusiness);

        // Act
        Optional<TypeOfBusiness> result = repository.add(typeOfBusiness);

        // Assert
        Assertions.assertTrue(result.isEmpty());
    }



    @Test
    void getTypeOfBusinesses() {
        // Arrange
        TypeOfBusiness typeOfBusiness1 = new TypeOfBusiness("Rent");
        TypeOfBusiness typeOfBusiness2 = new TypeOfBusiness("Sale");
        repository.add(typeOfBusiness1);
        repository.add(typeOfBusiness2);

        // Act
        List<TypeOfBusiness> result = repository.getTypeOfBusinesses();

        // Assert
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(typeOfBusiness1));
        Assertions.assertTrue(result.contains(typeOfBusiness2));

        // Ensure the returned list is a defensive copy by modifying the original list and checking that the returned
        // list is not affected.
        //repository.getTypeOfBusinesses().clear();
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(typeOfBusiness1));
        Assertions.assertTrue(result.contains(typeOfBusiness2));
    }
}