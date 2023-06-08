package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Bubble sort.
 *
 * @param <T> the type parameter
 */
public class BubbleSort<T> implements SortAlgorithm<T>  {

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

    @Override
    public List<T> sort(List<T> arrayToSort) {
        return null;
    }

}