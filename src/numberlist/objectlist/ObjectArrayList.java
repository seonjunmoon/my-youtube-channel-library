package numberlist.objectlist;

import numberlist.InvalidIndexException;

/**
 * This is a class that can instantiate a new list that holds reference to the
 * objects as its values
 *
 * @author Seonjun Mun
 * @version 2/4/2018
 */
public class ObjectArrayList extends ObjectList implements Copiable, java.io.Serializable {

    private Object[] list;

    /**
     * This is the constructor for this class. This constructor instantiate the
     * object list with default size 10
     */
    public ObjectArrayList() {
        list = new Object[10];
    }

    /**
     * This method has a function to add a new object to the list with
     * restriction that the index value can't be less than zero or greater or
     * equal to count.
     *
     * @param index location in the array where the new object will be stored
     * @param obj   new object that will be stored
     */
    public void add(int index, Object obj) throws InvalidIndexException, UncopiableException {
        if (index < 0 || index > count) {
            return;
        }

        //create new list with double ist size if the number its objects equal 
        //to its capacity 
        if ((count + 1) >= list.length) {
            Object[] copyList = new Object[2 * list.length];
            for (int i = 0; i < count; i++) {
                copyList[i] = list[i];
            }
            list = copyList;
        }
        for (int i = count - 1; i >= index; i--) {
            list[i + 1] = list[i];
        }
        list[index] = obj;
        count++;

    }

    /**
     * This method for removing an object in the specific index in the array
     *
     * @param index index where the object to be deleted is located
     */
    public void removeAt(int index) {
        if (index >= 0 || index < count - 1) {
            for (int i = index; i < count - 1; i++) {
                list[i] = list[i + 1];
            }
            count--;
        } else if (index == (count - 1)) {
            count--;
        }
    }

    /**
     * This method remove the specific object in the array if the object can be
     * found in the array
     *
     * @param obj object that will be removed form the list.
     */
    public void remove(Object obj) {
        int index = find(obj);
        if (index != -1) {
            removeAt(index);
        }
    }

    /**
     * This method gets an object from specific index
     *
     * @param index index location in the array where the object will be
     *              returned
     * @return object found in the index given
     */
    public Object get(int index) {
        if (index >= 0 && index < count) {
            return list[index];
        } else {
            return null;
        }
    }

    /**
     * This is a getter for size. The number of the object in the list
     *
     * @param obj target object to be found.
     * @return index number of the object in the list
     */
    public int find(Object obj) {
        for (int i = 0; i < count; i++) {
            if (list[i] == obj) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Method that returning string value of the list ([....])
     *
     * @return return its member list with opening and closing square bracket
     */
    @Override
    public String toString() {

        String print = "[";
        for (int i = 0; i < count; i++) {
            print += list[i].toString() + ",";
        }
        String newPrint = print.replaceAll(",$", "]");

        return newPrint;
    }

    /**
     * This method write a deep copy of the array by passing out the reference
     * of the new object
     *
     * @return new object that has same value with original array by in
     * different heap location
     * @throws numberlist.InvalidIndexException
     * @throws numberlist.objectlist.UncopiableException
     */
    @Override
    public Copiable deepCopy() throws InvalidIndexException, UncopiableException {
        ObjectArrayList listCopy = new ObjectArrayList();
        for (int i = 0; i < count; i++) {
            listCopy.add(i, ((Copiable) list[i]).deepCopy());
        }
        return listCopy;
    }

    public void set(int index, Object obj) {
        try {
            if (!(obj instanceof Copiable)) {
                throw new UncopiableException();
            }
            if (index < 0 || index > size()) {
                throw new numberlist.InvalidIndexException(0, index, size());
            }
            list[index] = obj;
        } catch (Exception ex) {
            return;
        }
    }
}
