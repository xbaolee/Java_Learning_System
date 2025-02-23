package adt;

import java.io.Serializable;

/**
 *
 * @author Jia Hao
 * @param <T>
 */
public class ArrayList<T> implements ListInterface<T>, Serializable {

    private T[] array;
    private int totalObject;
    private static final int DEFAULT_CAPACITY = 30;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        totalObject = 0;
        array = (T[]) new Object[initialCapacity];
    }

    @Override
    public boolean addObject(T newObject) {
        if (isArrayFull()) {
            doubleArray();
        }

        array[totalObject] = newObject;
        totalObject++;
        return true;
    }

    @Override
    public boolean add(int newPosition, T newObject) {
        boolean isSuccessful = true;

        if ((newPosition >= 1) && (newPosition <= totalObject + 1)) {
            if (isArrayFull()) {
                doubleArray();
            }
            makeRoom(newPosition);
            array[newPosition - 1] = newObject;
            totalObject++;
        } else {
            isSuccessful = false;
        }

        return isSuccessful;
    }

    @Override
    public boolean remove(T anObject) {
        for (int i = 0; i <= totalObject + 1; i++) {
            if (array[i].equals(anObject)) {
                removeGap(i + 1);
                totalObject--;
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        totalObject = 0;
    }

    @Override
    public boolean replace(T anObject, T newObject) {
        if (isListEmpty()) {
            return false;
        } else {
            int i = 1;

            while (i < totalObject && !array[i].equals(anObject)) {
                i++;
            }

            if (anObject.equals(array[i])) {
                array[i] = newObject;
                return true;
            }

            return false;
        }
    }

    @Override
    public T getObject(int positionIndex) {
        T result = null;

        if ((positionIndex >= 1) && (positionIndex <= totalObject)) {
            result = array[positionIndex - 1];
        }

        return result;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        for (int index = 0; !found && (index < totalObject); index++) {
            if (anEntry.equals(array[index])) {
                found = true;
            }
        }
        return found;
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Invalid operation");
    }

    @Override
    public int getNumberOfEntries() {
        throw new UnsupportedOperationException("Invalid operation");
    }

    @Override
    public T getEntry(int givenPosition) {
        throw new UnsupportedOperationException("Invalid operation");
    }

    @Override
    public boolean replace(int givenPosition, T newEntry) {
        throw new UnsupportedOperationException("Invalid operation");
    }

    @Override
    public void remove(int givenPosition) {
        throw new UnsupportedOperationException("Invalid operation");
    }

    @Override
    public void add(T newEntry) {
        throw new UnsupportedOperationException("Invalid operation");
    }

    @Override
    public int totalNumberOfObject() {
        return totalObject;
    }

    @Override
    public boolean isListEmpty() {
        return totalObject == 0;
    }

    @Override
    public boolean isListFull() {
        return false;
    }

    private void doubleArray() {
        T[] oldArray = array;
        array = (T[]) new Object[oldArray.length * 2];
        for (int i = 0; i < oldArray.length; i++) {
            array[i] = oldArray[i];
        }
    }

    private boolean isArrayFull() {
        return totalObject == array.length;
    }

    @Override
    public String toString() {
        String outputStr = "";
        for (int index = 0; index < totalObject; ++index) {
            outputStr += array[index] + "\n";
        }

        return outputStr;
    }

    /**
     * Task: Makes room for a new entry at newPosition. Precondition: 1 <=
     * newPosition <= numberOfEntries + 1; numberOfEntries is array's
     * numberOfEntries before addition.
     */
    private void makeRoom(int newPosition) {
        int newIndex = newPosition - 1;
        int lastIndex = totalObject - 1;

        // move each entry to next higher index, starting at end of
        // array and continuing until the entry at newIndex is moved
        for (int index = lastIndex; index >= newIndex; index--) {
            array[index + 1] = array[index];
        }
    }

    /**
     * Task: Shifts entries that are beyond the entry to be removed to the next
     * lower position. Precondition: array is not empty; 1 <= givenPosition <
     * numberOfEntries; numberOfEntries is array's numberOfEntries before
     * removal.
     */
    private void removeGap(int givenPosition) {
        // move each entry to next lower position starting at entry after the
        // one removed and continuing until end of array
        int removedIndex = givenPosition - 1;
        int lastIndex = totalObject - 1;

        for (int index = removedIndex; index < lastIndex; index++) {
            array[index] = array[index + 1];
        }
    }

    private void reduceArraySize() {
        T[] oldArray = array;
        int oldCapacity = oldArray.length / 2;

        array = array = (T[]) new Comparable[oldCapacity];

        System.arraycopy(oldArray, 0, array, 0, oldCapacity);
    }

}
