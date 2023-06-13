package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**
 * A class representing a role with a description.
 */
public class Role {

    /**

     The description of the role.
     */
    private final String description;

    /**
     * Creates a new Role object with the specified description.
     *
     * @param description the description of the role.
     */
    public Role(String description) {
        this.description = description;
    }
    /**

     Returns a string representation of the Role object.
     @return a string representation of the Role object.
     */
    public String toString() {
        return String.format(description);
    }
    /**

     Compares this Role object to the specified object for equality.
     @param o the object to compare to.
     @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }
        Role that = (Role) o;
        return description.equals(that.description);
    }
    /**

     Returns the hash code for this Role object.
     @return the hash code for this Role object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    /**
     * Returns the description of the role.
     *
     * @return the description of the role.
     */
    public String getDescription() {
        return description;
    }
    /**

     Creates and returns a copy of this Role object.
     @return a copy of this Role object.
     */
    public Role clone() {
        return new Role(this.description);
    }
}