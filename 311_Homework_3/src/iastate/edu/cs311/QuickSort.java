/**
 * 
 */
package iastate.edu.cs311;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author aguibert
 * 
 */
public class QuickSort<E extends Comparable<? super E>> implements SortAnalysis<E> {

    private static Random rand = new Random();

    public QuickSort() {}

    @Override
    public int analyzeSort(ArrayList<E> list) {
        long startTime = System.currentTimeMillis();
        doQuickSort(list, 0, list.size() - 1);
        long endTime = System.currentTimeMillis();
        return (int) (endTime - startTime);
    }

    private void doQuickSort(ArrayList<E> list, int left, int right) {
        int pivotIndex = rand.nextInt() % list.size();
        E pivotValue = list.get(pivotIndex);

        // select element: arr[left] >= pivot
        while (left < right && list.get(left).compareTo(pivotValue) == -1)
            left++;

        // select element: arr[right] <= pivot
        while (left < right && list.get(right).compareTo(pivotValue) == 1)
            right--;

        // swap the elements
        E temp = list.get(left);
        list.set(left, list.get(right));
        list.set(right, temp);
        System.out.println("pivot is: " + pivotValue.toString());
    }
}