package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;

/**
 * The interface Sort algorithm.
 *
 * @param <T> the type parameter
 */
public interface SortAlgorithm <T> {
    /**
     * Sort list.
     *
     * @param arrayToSort the array to sort
     * @return the list
     */
    List<T> sort(List<T> arrayToSort);
}
