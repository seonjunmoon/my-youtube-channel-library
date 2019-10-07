package numberlist.primitivelist;

import java.util.logging.Level;
import java.util.logging.Logger;

import numberlist.InvalidIndexException;

/**
 * This class represent array list to hold floating point values
 *
 * @author Seonjun Mun
 * @version 2/4/2018
 */
class FloatingPointArrayList {

    private NumberArrayList list;

    /**
     * constructor that instantiate the NumberArrayList object
     */
    public FloatingPointArrayList() {
        list = new NumberArrayList();
    }

    /**
     * this method add new value to the list and specific index
     *
     * @param index index Location in the array where the object will be stored
     * @param value the value that will be stored in the list
     */
    public void add(int index, double value) throws InvalidIndexException {
        list.add(index, Double.doubleToRawLongBits(value));
    }

    /**
     * This method for removing the number in the specific index in the array
     *
     * @param index index where the number to be deleted is located
     */
    public void removeAt(int index) throws InvalidIndexException {
        list.removeAt(index);
    }

    /**
     * This method remove the specific number in the array
     *
     * @param value the number that will be removed form the list.
     */
    public void remove(double value) {
        long newValue = Double.doubleToRawLongBits(value);
        list.remove(newValue);
    }

    /**
     * this method gets the number from specific index
     *
     * @param index index location in the array where the number is located
     * @return the number found in the index given, but if the number doesn't
     * exist in the list; it will very small number from Double class
     */
    public double get(int index) throws InvalidIndexException {
        return Double.longBitsToDouble(list.get(index));
    }

    /**
     * This method find the number that the user are looking for by returning
     * its index location in array and will return -1 if the number can't be
     * found
     *
     * @param value the number that user try to look in the array
     * @return index where then number was found and it will return -1 if the
     * number is not in the array
     */
    public int find(double value) {
        long doubleToLong = Double.doubleToRawLongBits(value);
        return list.find(doubleToLong);
    }

    /**
     * This is a getter for the size. The number of the object in the list
     *
     * @return number of the object in the list
     */
    public int size() {
        return list.size();
    }

    /**
     * Method that represent the list number in the array with opening and
     * closing square brackets. ex [1.0,2.0,3.0]
     *
     * @return list of the number in the array in proper format
     */
    @Override
    public String toString() {

        String print = "[";
        for (int i = 0; i < size(); i++) {
            try {
                print += get(i) + ",";
            } catch (InvalidIndexException ex) {
                Logger.getLogger(FloatingPointArrayList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String newPrint = print.replaceAll(",$", "]");

        return newPrint;
    }

}
