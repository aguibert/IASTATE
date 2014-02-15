/**
 * 
 */
package iastate.edu.cs311;

import java.util.ArrayList;

/**
 * @author aguibert
 * 
 */
public class InsertionSort<E extends Comparable<? super E>> implements SortAnalysis<E> {

    public InsertionSort() {}

    @Override
    public int analyzeSort(ArrayList<E> list) {
        long startTime = System.currentTimeMillis();
        doInsertionSort(list);
        long endTime = System.currentTimeMillis();
        return (int) (endTime - startTime);
    }

    private void doInsertionSort(ArrayList<E> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).compareTo(list.get(i + 1)) == 1) {
                E elementToInsert = list.get(i + 1);
                // need to insert in sorted array... look backwards
                for (int k = i; k >= 0; k--) {
                    list.set(k + 1, list.get(k)); // slide the element 1 to the right
                    if (elementToInsert.compareTo(list.get(k)) != 1) {
                        // if elementToInsert <= list[k], insert element onto k
                        list.set(k, elementToInsert);
                    }
                }
            }
        }
    }
}