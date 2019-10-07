package numberlist.objectlist;

/**
 * This class represent the node for the linked list class
 *
 * @author Seonjun Mun
 * @version 2/4/2018
 */
class Node {

    private Node nextNode;
    private Object obj;

    /**
     * This constructor receive an object for its main body
     *
     * @param obj Object that will be added as main object in this node
     */
    public Node(Object obj) {
        this.obj = obj;
    }

    /**
     * This is a getter for the object in the node
     *
     * @return main object in the node
     */
    public Object getValue() {
        return obj;
    }

    /**
     * This is mutator for change the body object's reference
     *
     * @param obj new object ti replace the old object
     */
    public void setValue(Object obj) {
        this.obj = obj;
    }

    /**
     * this is a getter method for nextNode variable
     *
     * @return nextNode value
     */
    public Node getNext() {
        return nextNode;
    }

    /**
     * This is a setter to replace the object in the nextNode.
     *
     * @param nextNode the new node to replace the old node
     */
    public void setNext(Node nextNode) {
        this.nextNode = nextNode;
    }

}
