package numberlist.objectlist;

import numberlist.InvalidIndexException;

/**
 * This is abstract class that have all methods that needed for list
 *
 * @author Seonjun Mun
 * @version 2/4/2018
 */
abstract class ObjectList implements java.io.Serializable {

    int count;

    /**
     * This method has a function to add a new object to the list with
     * restriction for its index value can be less than zero or greater or equal
     * to count.
     *
     * @param index location in the array where the new object will be stored
     * @param obj   new object that will be stored
     */
    abstract void add(int index, Object obj) throws InvalidIndexException, UncopiableException;

    /**
     * This method for removing an object in the specific index in the array
     *
     * @param index index where the object to be deleted is located
     */
    abstract void removeAt(int index) throws InvalidIndexException;

    /**
     * This method remove the specific object in the array if the object can be
     * found in the array
     *
     * @param obj object that will be removed form the list.
     */
    abstract void remove(Object obj);

    /**
     * this method gets an object from specific index
     *
     * @param index index location in the array where the object will be
     *              returned
     * @return object found in the index given
     */
    abstract Object get(int index);

    /**
     * This method find the object that the user are looking for by returning
     * its index location in array and will return -1 if the object can't be
     * found
     *
     * @param obj object that user try to look in the array
     * @return index where object was found and it will return -1 if the object
     * is not in the array
     */
    abstract int find(Object obj);

    /**
     * This is a getter for size. The number of the object in the list
     *
     * @return number of the object in the list
     */
    public int size() {
        return count;
    }

}
