package pt.ipp.isep.dei.esoft.project.tp.one.domain;

import java.util.Objects;

public class TaskCategory {

    private final String description;

    public TaskCategory(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaskCategory)) {
            return false;
        }
        TaskCategory that = (TaskCategory) o;
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
    public TaskCategory clone() {
        return new TaskCategory(this.description);
    }
}
