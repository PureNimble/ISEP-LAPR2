package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Bubble sort.
 *
 * @param <T> the type parameter
 */
public class BubbleSort<T> implements SortAlgorithm<T> {
    /**
     * The list of Message objects to be sorted.
     */
    private final List<Message> arrayToSort;

    /**
     * Instantiates a new Bubble sort.
     *
     * @param arrayToSort the array to sort
     */
    public BubbleSort(List<Message> arrayToSort) {
        this.arrayToSort = arrayToSort;
    }

    /**
     * Gets array after sorting.
     *
     * @return the array after sorting
     */
    public List<Message> getArrayAfterSorting() {
        return arrayToSort;
    }

    /**
     * Gets array to sort.
     *
     * @return the array to sort
     */
    public List<Message> getArrayToSort() {
        return arrayToSort;
    }

    /**
     * Sorts the given list of objects in ascending order based on their initial date.
     *
     * @param arrayToSort The list of objects to be sorted.
     * @return The sorted list of objects.
     */
    @Override
    public List<T> sort(List<T> arrayToSort) {
        int n = arrayToSort.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (((Message) arrayToSort.get(j)).getInitialDate().compareTo(((Message) arrayToSort.get(j + 1)).getInitialDate()) > 0) {
                    // Realiza a troca de posições entre os elementos
                    T temp = arrayToSort.get(j);
                    arrayToSort.set(j, arrayToSort.get(j + 1));
                    arrayToSort.set(j + 1, temp);
                }
            }
        }
        return arrayToSort;
    }
}