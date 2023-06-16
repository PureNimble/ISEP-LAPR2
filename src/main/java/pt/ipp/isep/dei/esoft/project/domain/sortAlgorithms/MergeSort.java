package pt.ipp.isep.dei.esoft.project.domain.sortAlgorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSort<T> implements SortAlgorithm<T> {
    private final Comparator<T> comparator;

    public MergeSort(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    /**
     * Sorts the given list of objects in ascending order using the merge sort algorithm.
     *
     * @param arrayToSort The list of objects to be sorted.
     * @return The sorted list of objects.
     */
    @Override
    public List<T> sort(List<T> arrayToSort) {
        if (arrayToSort.size() <= 1) {
            return arrayToSort;
        }

        int mid = arrayToSort.size() / 2;
        List<T> left = sort(arrayToSort.subList(0, mid));
        List<T> right = sort(arrayToSort.subList(mid, arrayToSort.size()));

        return merge(left, right);
    }

    /**
     * Merges two sorted lists into a single sorted list.
     *
     * @param left  The left sorted list.
     * @param right The right sorted list.
     * @return The merged sorted list.
     */
    private List<T> merge(List<T> left, List<T> right) {
        List<T> merged = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < left.size() && j < right.size()) {
            T leftValue = left.get(i);
            T rightValue = right.get(j);

            if (comparator.compare(leftValue, rightValue) <= 0) {
                merged.add(leftValue);
                i++;
            } else {
                merged.add(rightValue);
                j++;
            }
        }

        while (i < left.size()) {
            merged.add(left.get(i));
            i++;
        }

        while (j < right.size()) {
            merged.add(right.get(j));
            j++;
        }

        return merged;
    }
}
