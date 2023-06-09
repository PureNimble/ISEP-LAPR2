package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.PropertyType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The PropertyTypeRepository class represents a repository for PropertyType objects. It provides methods to retrieve,
 * add, and return a defensive copy of the list of PropertyType objects.
 */
public class PropertyTypeRepository {
    /**
     * Represents a list of property types.
     */
    private final List<PropertyType> propertyTypes = new ArrayList<>();

    /**
     * This method returns an existing PropertyType by its description.
     *
     * @param propertyTypeDescription The description of the PropertyType to be retrieved.
     * @return The PropertyType object.
     * @throws IllegalArgumentException if the PropertyType does not exist in the repository.
     */
    public PropertyType getPropertyTypeByDescription(String propertyTypeDescription) {

// Create a new PropertyType object with the specified description
        PropertyType newPropertyType = new PropertyType(propertyTypeDescription);

// Check if the repository contains the PropertyType object and return it if found
        PropertyType propertyType = null;
        if (propertyTypes.contains(newPropertyType)) {
            propertyType = propertyTypes.get(propertyTypes.indexOf(newPropertyType));
        }

// Throw an exception if the PropertyType object was not found
        if (propertyType == null) {
            throw new IllegalArgumentException(
                    "PropertyType requested for [" + propertyTypeDescription + "] does not exist.");
        }

        return propertyType;
    }

    /**
     * Adds a new PropertyType to the repository.
     *
     * @param propertyType The PropertyType object to be added to the repository.
     * @return An optional containing the cloned PropertyType object if the operation was successful, otherwise an empty Optional.
     */
    public Optional<PropertyType> add(PropertyType propertyType) {

        Optional<PropertyType> newPropertyType = Optional.empty();
        boolean operationSuccess = false;

// Validate the PropertyType object and add it to the repository if it is valid
        if (validatePropertyType(propertyType)) {
            newPropertyType = Optional.of(propertyType.clone());
            operationSuccess = propertyTypes.add(newPropertyType.get());
        }

// Return an empty Optional if the operation was not successful
        if (!operationSuccess) {
            newPropertyType = Optional.empty();
        }

        return newPropertyType;
    }

    /**

     Validates a PropertyType object to ensure that it does not already exist in the repository.
     @param propertyType The PropertyType object to be validated.
     @return true if the PropertyType object is valid, false otherwise.
     */
    private boolean validatePropertyType(PropertyType propertyType) {
        boolean isValid = !propertyTypes.contains(propertyType);
        return isValid;
    }

    /**
     * Returns a defensive (immutable) copy of the list of PropertyType objects in the repository.
     *
     * @return An immutable list of PropertyType objects.
     */
    public List<PropertyType> getPropertyTypes() {
// This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(propertyTypes);
    }
}
