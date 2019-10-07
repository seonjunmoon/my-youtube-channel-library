package numberlist.objectlist;

import numberlist.InvalidIndexException;

/**
 * This class represent singly-linked list. This class act as a list, but
 * instead using array to hold its object, this class simple store the main
 * object and the object after it in its node
 *
 * @author Seonjun Mun
 * @version 2/4/2018
 */
public class ObjectLinkedList extends ObjectList implements Copiable {

    private Node firstNode;

    /**
     * Default constructor
     */
    public ObjectLinkedList() {

    }

    /**
     * This method adding the object to the list in the index position that user
     * input
     *
     * @param index index location where the new object will be located
     * @param obj   object that will be located in the list
     */
    @Override
    public void add(int index, Object obj) throws InvalidIndexException, UncopiableException {
        if (index > count || index < 0) {
            return;
        }
        //temp object to hold teh new obj
        Node newNode = new Node(obj);
        if (index == 0) {
            newNode.setNext(firstNode);
            firstNode = newNode;
        } else {
            Node currentNode = firstNode;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNext();
            }
            newNode.setNext(currentNode.getNext());
            currentNode.setNext(newNode);
        }
        count++;
    }

    /**
     * his method for removing an object in the specific index in the list
     *
     * @param index index location where the object to be deleted is located
     */
    @Override
    public void removeAt(int index) {
        if (index >= count || index < 0) {
            return;
        }

        if (index == 0) {
            firstNode = firstNode.getNext();
        } else {
            Node currentNode = firstNode;
            for (int i = 1; i < index; i++) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(currentNode.getNext().getNext());
        }
        count--;
    }

    /**
     * This method remove the specific object in the array if the object can be
     * found in the array
     *
     * @param obj object that will be removed form the list.
     */
    @Override
    public void remove(Object obj) {

        int index = find(obj);
        if (index == -1) {
            return;
        } else {
            removeAt(index);
        }

    }

    /**
     * this method gets an object from specific index
     *
     * @param index index location in the array where the object will be
     *              returned
     * @return object found in the index given
     */
    @Override
    public Object get(int index) {
        if (index >= count || index < 0) {
            return null;
        }
        if (index == 0) {
            return firstNode.getValue();
        }
        Node currentNode = firstNode;
        for (int i = 1; i <= index; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode.getValue();
    }

    /**
     * This method find the object that the user are looking for by returning
     * its index location in array and will return -1 if the object can't be
     * found
     *
     * @param obj object that user try to look in the array
     * @return index where object was found and it will return -1 if the object
     * is not in the array
     */
    @Override
    public int find(Object obj) {
        if (count == 0) {
            return -1;
        }
        if (firstNode.getValue() == obj) {
            return 0;
        }
        Node currentNode = firstNode;
        for (int i = 1; i < count; i++) {
            currentNode = currentNode.getNext();
            if (currentNode.getValue() == obj) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Method that returning string value from the list ([....])
     *
     * @return return its member list with opening and closing square bracket
     */
    @Override
    public String toString() {
        if (count == 0) {
            return "[]";
        }
        String print = "[";
        print += firstNode.getValue().toString() + ",";
        Node currentNode = firstNode;
        for (int i = 1; i < count; i++) {
            currentNode = currentNode.getNext();
            print += currentNode.getValue().toString() + ",";
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
     */
    @Override
    public Copiable deepCopy() throws InvalidIndexException, UncopiableException {
        ObjectLinkedList listCopy = new ObjectLinkedList();
        Node currentNode = firstNode;
        for (int i = 0; i < count; i++) {
            listCopy.add(i, ((Copiable) currentNode.getValue()).deepCopy());
            currentNode = currentNode.getNext();
        }
        return listCopy;
    }

}
