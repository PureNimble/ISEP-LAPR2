package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Bubble sort.
 *
 * @param <T> the type parameter
 */

public class BubbleSort<T> implements SortAlgorithm<T> {
    private final List<Message> arrayToSort;

    public BubbleSort(List<Message> arrayToSort) {
        this.arrayToSort = arrayToSort;
    }

    public List<Message> getArrayAfterSorting() {
        return arrayToSort;
    }

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