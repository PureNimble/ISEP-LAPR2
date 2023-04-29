package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**

 The AvailableEquipment class represents the equipment that is available for use.
 */
public class AvailableEquipment {
    private String availableEquipment;

    /**

     Constructs an AvailableEquipment object with the specified availableEquipment.
     @param availableEquipment The equipment that is available for use.
     */
    public AvailableEquipment(String availableEquipment) {
        this.availableEquipment = availableEquipment;
    }
    /**

     Returns the equipment that is available for use.
     @return The equipment that is available for use.
     */
    public String getAvailableEquipment() {
        return availableEquipment;
    }
    /**

     Returns a string representation of the AvailableEquipment object.
     @return A string representation of the AvailableEquipment object.
     */
    public String toString(){
        return String.format(availableEquipment);
    }
    /**

     Indicates whether some other object is "equal to" this one.
     @param o The object to compare this AvailableEquipment object against.
     @return True if the given object is equal to this AvailableEquipment object; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailableEquipment that = (AvailableEquipment) o;
        return availableEquipment.equals(that.availableEquipment);
    }
    /**

     Returns a hash code value for the AvailableEquipment object.
     @return A hash code value for the AvailableEquipment object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(availableEquipment);
    }
}
