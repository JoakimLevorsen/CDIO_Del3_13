package matador;

import java.util.ArrayList;
import java.util.Comparator;

public class ArrayFunctions {
    public static <T> ArrayList<T> getBiggest(T[] input, Comparator<T> compare) {
        ArrayList<T> biggestItems = new ArrayList<T>();
        for (T item : input) {
            // Check if biggestItem has items
            if (biggestItems.size() == 0) {
                biggestItems.add(item);
            } else {
                int comparisonResult = compare.compare(biggestItems.get(0), item);
                // Check if item has same size as current biggest items
                if (comparisonResult > 0) {
                    // This item is bigger than the current list, replace it
                    biggestItems = new ArrayList<T>();
                    biggestItems.add(item);
                } else if (comparisonResult == 0) {
                    // This item is the same size as the current list, add it
                    biggestItems.add(item);
                }
            }
        }
        return biggestItems;
    }

    public static <T> ArrayList<T> getBiggest(ArrayList<T> input, Comparator<T> compare) {
        ArrayList<T> biggestItems = new ArrayList<T>();
        for (T item : input) {
            // Check if biggestItem has items
            if (biggestItems.size() == 0) {
                biggestItems.add(item);
            } else {
                int comparisonResult = compare.compare(biggestItems.get(0), item);
                // Check if item has same size as current biggest items
                if (comparisonResult > 0) {
                    // This item is bigger than the current list, replace it
                    biggestItems = new ArrayList<T>();
                    biggestItems.add(item);
                } else if (comparisonResult == 0) {
                    // This item is the same size as the current list, add it
                    biggestItems.add(item);
                }
            }
        }
        return biggestItems;
    }
}
