package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Merge sort.
 *
 * @param <T> the type parameter
 */
public class MergeSort<T> implements SortAlgorithm<T>{
    /**
     * The list of messages to be sorted.
     */
    private final List<Message> arrayToSort;

    /**
     * Instantiates a new Merge sort.
     *
     * @param arrayToSort the array to sort
     */
    public MergeSort(List<Message> arrayToSort) {
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
     * Divide array elements.
     *
     * @param indexStart the index start
     * @param indexEnd   the index end
     */
    public void divideArrayElements(int indexStart, int indexEnd) {

        if (indexStart < indexEnd && (indexEnd - indexStart) >= 1) {
            int middleElement = (indexEnd + indexStart) / 2;

            divideArrayElements(indexStart, middleElement);
            divideArrayElements(middleElement + 1, indexEnd);

            mergeArrayElements(indexStart, middleElement, indexEnd);
        }
    }

    /**
     * Merge array elements.
     *
     * @param indexStart  the index start
     * @param indexMiddle the index middle
     * @param indexEnd    the index end
     */
    public void mergeArrayElements(int indexStart, int indexMiddle, int indexEnd) {

        List<Message> tempArray = new ArrayList<>();

        int getLeftIndex = indexStart;
        int getRightIndex = indexMiddle + 1;

        while (getLeftIndex <= indexMiddle && getRightIndex <= indexEnd) {

            if (arrayToSort.get(getLeftIndex).getInitialDate().compareTo(arrayToSort.get(getRightIndex).getInitialDate()) <= 0) {

                tempArray.add(arrayToSort.get(getLeftIndex));
                getLeftIndex++;

            } else {

                tempArray.add(arrayToSort.get(getRightIndex));
                getRightIndex++;

            }
        }

        while (getLeftIndex <= indexMiddle) {
            tempArray.add(arrayToSort.get(getLeftIndex));
            getLeftIndex++;
        }

        while (getRightIndex <= indexEnd) {
            tempArray.add(arrayToSort.get(getRightIndex));
            getRightIndex++;
        }


        for (int i = 0; i < tempArray.size(); indexStart++) {
            arrayToSort.set(indexStart, tempArray.get(i++));

        }

    }
    /**
     * Sorts the given list of elements.
     *
     * @param arrayToSort The list of elements to be sorted.
     * @return The sorted list of elements.
     */
    @Override
    public List<T> sort(List<T> arrayToSort) {
        return null;
    }
}
