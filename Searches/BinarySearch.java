import java.util.List;

/**
 * Binary search implementation for integers in given sorted collection
 */
public class BinarySearch {

    public int findGivenNumber(int numberToFind, List<Integer> sortedCollection) {
        int indexOfFirstElement = 0;
        int indexOfLastElement = sortedCollection.size() - 1;
        if (numberToFind < sortedCollection.get(indexOfFirstElement) ||
                numberToFind > sortedCollection.get(indexOfLastElement)) {
            return -1;
        }
        return findNumber(numberToFind, indexOfFirstElement, indexOfLastElement, sortedCollection);
    }

    private int findNumber(int numberToFind, int indexOfFirstElement, int indexOfLastElement, List<Integer> sortedCollection) {
        if (indexOfLastElement < indexOfFirstElement) {
            return -1;
        }
        int indexOfMiddleElement = (indexOfFirstElement + indexOfLastElement) >>> 1;
        if (sortedCollection.get(indexOfMiddleElement) > numberToFind) {
            return findNumber(numberToFind, indexOfFirstElement, indexOfMiddleElement - 1, sortedCollection);
        } else if (sortedCollection.get(indexOfMiddleElement) < numberToFind) {
            return findNumber(numberToFind, indexOfMiddleElement + 1, indexOfLastElement, sortedCollection);
        }
        return indexOfMiddleElement;
    }
}
