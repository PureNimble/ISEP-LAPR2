package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.PropertyType;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTypeRepositoryTest {

    private PropertyTypeRepository propertyTypeRepository;

    @BeforeEach
    public void setUp() {
        propertyTypeRepository = new PropertyTypeRepository();
    }

    @Test
    void getPropertyTypeByDescription() {
        PropertyType propertyType1 = new PropertyType("House");
        propertyTypeRepository.add(propertyType1);
        PropertyType propertyType2 = new PropertyType("Apartment");
        propertyTypeRepository.add(propertyType2);
        PropertyType retrievedPropertyType = propertyTypeRepository.getPropertyTypeByDescription("House");
        Assertions.assertEquals(propertyType1, retrievedPropertyType);
        retrievedPropertyType = propertyTypeRepository.getPropertyTypeByDescription("Apartment");
        Assertions.assertEquals(propertyType2, retrievedPropertyType);
    }

    @Test
    void add() {
        PropertyType propertyType = new PropertyType("House");
        Optional<PropertyType> newPropertyType = propertyTypeRepository.add(propertyType);
        Assertions.assertTrue(newPropertyType.isPresent());
        Assertions.assertEquals(propertyType, newPropertyType.get());
        List<PropertyType> propertyTypes = propertyTypeRepository.getPropertyTypes();
        Assertions.assertEquals(1, propertyTypes.size());
        Assertions.assertEquals(propertyType, propertyTypes.get(0));
    }

    @Test
    void getPropertyTypes() {
        PropertyTypeRepository repository = new PropertyTypeRepository();
        PropertyType propertyType1 = new PropertyType("Residential");
        PropertyType propertyType2 = new PropertyType("Commercial");
        repository.add(propertyType1);
        repository.add(propertyType2);

        List<PropertyType> propertyTypes = repository.getPropertyTypes();

        // Ensure the list of task categories returned by the method is immutable
        Assertions.assertThrows(UnsupportedOperationException.class, () -> propertyTypes.add(new PropertyType("Industrial")));

        // Ensure the list contains the added property types
        Assertions.assertTrue(propertyTypes.contains(propertyType1));
        Assertions.assertTrue(propertyTypes.contains(propertyType2));
    }
}