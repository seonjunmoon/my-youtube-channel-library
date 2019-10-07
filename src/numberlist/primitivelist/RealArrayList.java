package numberlist.primitivelist;

import numberlist.InvalidIndexException;

/**
 * This class represent array class that have double value as its member
 *
 * @author Seonjun Mun
 * @version 2/4/2018
 */
public class RealArrayList extends FloatingPointArrayList {

    /**
     * this method add new value to the last index in the array
     *
     * @param value the value that will be stored in the list
     */
    public void add(double value) throws InvalidIndexException {
        super.add(super.size(), value);
    }

    /**
     * This method remove all value in parameter from the list
     *
     * @param value the value that will be deleted from the list
     */
    public void removeAll(double value) {
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
    public int findLast(double value) throws InvalidIndexException {

        for (int i = super.size() - 1; i >= 0; i--) {
            if (super.get(i) == value) {
                return i;
            }
        }
        return -1;
    }
}
