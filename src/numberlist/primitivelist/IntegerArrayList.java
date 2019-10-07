package numberlist.primitivelist;

import numberlist.InvalidIndexException;

/**
 * This class represent array list for integer that inherit the array[] from the
 * NumberArrayList class
 *
 * @author Seonjun Mun
 * @version 2/4/2018
 */
public class IntegerArrayList extends NumberArrayList implements java.io.Serializable {

    /**
     * this method add new value to the list
     *
     * @param value the value that will be stored in the list
     */
    public void add(long value) throws InvalidIndexException {
        super.add(super.size(), value);
    }

    /**
     * This method remove all value in parameter fromm the list
     *
     * @param value the value that will be deleted from the list
     */
    public void removeAll(long value) {
        int index = super.find(value);

        while (index != -1) {
            super.remove(value);
            index = super.find(value);
        }
    }

    /**
     * This method find the last index from the value. It traverses back from
     * the end of the list
     *
     * @param value target value that the user try find in the list
     * @return the last index position where the value is found. Otherwise
     * return -1 if the value is not found
     */
    public int findLast(long value) throws InvalidIndexException {

        for (int i = super.size() - 1; i >= 0; i--) {
            if (super.get(i) == value) {
                return i;
            }
        }
        return -1;
    }

    public void set(int index, long value) {
        try {
            if (index < 0 || index > count - 1) {
                throw new InvalidIndexException(0, index, size() - 1);
            }
        } catch (InvalidIndexException ex) {
            return;
        }
        list[index] = value;
    }
}
