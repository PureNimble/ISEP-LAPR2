package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

/**

 This class represents a role in a system.
 */
public class Role {
    private final String description;

    /**

     Creates a new role with the given description.
     @param description The description of the role.
     */
    public Role(String description) {
        this.description = description;
    }
    /**

     Checks if the current role is equal to the specified object.
     @param o The object to compare.
     @return true if the object is a Role and has the same description as the current role, false otherwise.
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

     Returns the hash code of the current role.
     @return The hash code of the current role.
     */
    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
    /**

     Returns the description of the role.
     @return The description of the role.
     */
    public String getDescription() {
        return description;
    }
    /**

     Creates a clone of the current role.
     @return A clone of the current role.
     */
    public Role clone() {
        return new Role(this.description);
    }
}
