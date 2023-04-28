package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Role {
    private final String description;

    public Role(String description) {
        this.description = description;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    /**
     * This method returns the description of the task category.
     *
     * @return The description of the task category.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Clone method.
     *
     * @return A clone of the current task.
     */
    public Role clone() {
        return new Role(this.description);
    }
}
