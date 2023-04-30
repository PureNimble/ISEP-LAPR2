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

        TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Rent");
        repository.add(typeOfBusiness);

        TypeOfBusiness result = repository.getTypeOfBusinessByDescription("Rent");

        Assertions.assertEquals(typeOfBusiness, result);
    }


    @Test
    void addValid() {

        TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Rent");

        Optional<TypeOfBusiness> result = repository.add(typeOfBusiness);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(typeOfBusiness, result.get());
    }

    @Test
    void addInvalid() {

        TypeOfBusiness typeOfBusiness = new TypeOfBusiness("Rent");
        repository.add(typeOfBusiness);

        Optional<TypeOfBusiness> result = repository.add(typeOfBusiness);

        Assertions.assertTrue(result.isEmpty());
    }



    @Test
    void getTypeOfBusinesses() {

        TypeOfBusiness typeOfBusiness1 = new TypeOfBusiness("Rent");
        TypeOfBusiness typeOfBusiness2 = new TypeOfBusiness("Sale");
        repository.add(typeOfBusiness1);
        repository.add(typeOfBusiness2);

        List<TypeOfBusiness> result = repository.getTypeOfBusinesses();

        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(typeOfBusiness1));
        Assertions.assertTrue(result.contains(typeOfBusiness2));

        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(typeOfBusiness1));
        Assertions.assertTrue(result.contains(typeOfBusiness2));
    }
}