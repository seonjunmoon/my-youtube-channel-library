package numberlist.primitivelist;

import java.util.logging.Level;
import java.util.logging.Logger;

import numberlist.InvalidIndexException;

/**
 * This class represent array list for Integer value
 *
 * @author Seonjun Mun
 * @version 2/4/2018
 */
class NumberArrayList implements java.io.Serializable {

    long[] list;
    int count;

    /**
     * This is the constructor for this class. This constructor instantiate the
     * object list with default size 10
     */
    public NumberArrayList() {
        list = new long[10];
    }

    /**
     * This method has a function to add a new number to the list with
     * restriction that the index value can't be less than zero or greater or
     * equal to count.
     *
     * @param index location in the array where the new object will be stored
     * @param value the number that will be stored
     */
    public void add(int index, long value) throws InvalidIndexException {
        if (index >= 0 && index <= count) {
            count++;
            if (list.length < count) {
                long[] doubledList = new long[list.length * 2];
                for (int i = 0; i < list.length; i++) {
                    doubledList[i] = list[i];
                }
                list = doubledList;
            }
            for (int i = (list.length - 1); i > index; i--) {
                list[i] = list[i - 1];
            }
            list[index] = value;
        } else {
            throw new InvalidIndexException(0, count, index);
        }
    }

    /**
     * This method for removing numbers in a specific index in the array
     *
     * @param index index where the number to be deleted is located
     */
    public void removeAt(int index) throws InvalidIndexException {
        if (index >= 0 && index < count) {
            for (int i = index; i < (list.length - 1); i++) {
                list[i] = list[i + 1];
            }
            count--;
        } else {
            throw new InvalidIndexException(0, count - 1, index);
        }
    }

    /**
     * This method remove the specific number in the array if the number can be
     * found in the array
     *
     * @param value the number that will be removed form the list.
     */
    public void remove(long value) {
        int index = find(value);
        if (index != -1) {
            try {
                removeAt(index);
            } catch (InvalidIndexException ex) {
                Logger.getLogger(NumberArrayList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * This method gets a number from specific index
     *
     * @param index index location in the array where the number will be
     *              returned
     * @return the number found in the index given
     */
    public long get(int index) throws InvalidIndexException {
        if (index >= 0 && index < count) {
            return list[index];
        } else {
            return Long.MIN_VALUE;
        }

    }

    /**
     * This is a getter for size. The number of the number in the list
     *
     * @param value target number to be found.
     * @return index number of the number in the list
     */
    public int find(long value) {
        for (int i = 0; i < count; i++) {
            if (list[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * This is a getter for size. The number of the object in the list
     *
     * @return number of the object in the list
     */
    public int size() {
        return count;
    }

    /**
     * Method that returning string value of the list ([....])
     *
     * @return its members list with opening and closing square bracket
     */
    @Override
    public String toString() {

        String print = "[";
        for (int i = 0; i < count; i++) {
            print += list[i] + ",";
        }
        String newPrint = print.replaceAll(",$", "]");

        return newPrint;
    }

}
