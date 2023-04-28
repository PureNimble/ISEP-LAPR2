package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.PropertyType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PropertyTypeRepository {

    private final List<PropertyType> propertyTypes = new ArrayList<>();

    /**
     * This method returns an exsiting Task Category by its description.
     *
     * @param taskCategoryDescription The description of the task category to be created.
     * @return The task category.
     * @throws IllegalArgumentException if the task category does not exist, which should never happen.
     */
    public PropertyType getPropertyTypeByDescription(String propertyTypeDescription) {
        PropertyType newPropertyType = new PropertyType(propertyTypeDescription);
        PropertyType propertyType = null;
        if (propertyTypes.contains(newPropertyType)) {
            propertyType = propertyTypes.get(propertyTypes.indexOf(newPropertyType));
        }
        if (propertyType == null) {
            throw new IllegalArgumentException(
                    "Task Category requested for [" + propertyTypeDescription + "] does not exist.");
        }
        return propertyType;
    }

    public Optional<PropertyType> add(PropertyType propertyType) {

        Optional<PropertyType> newPropertyType = Optional.empty();
        boolean operationSuccess = false;

        if (validatePropertyType(propertyType)) {
            newPropertyType = Optional.of(propertyType.clone());
            operationSuccess = propertyTypes.add(newPropertyType.get());
        }

        if (!operationSuccess) {
            newPropertyType = Optional.empty();
        }

        return newPropertyType;
    }

    private boolean validatePropertyType(PropertyType propertyType) {
        boolean isValid = !propertyTypes.contains(propertyType);
        return isValid;
    }

    /**
     * This method returns a defensive (immutable) copy of the list of task categories.
     *
     * @return The list of task categories.
     */
    public List<PropertyType> getPropertyTypes() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(propertyTypes);
    }
}
