package numberlist.objectlist;

import numberlist.InvalidIndexException;

/**
 * This interface has single abstract method for deep copy
 *
 * @author Seonjun Mun
 * @version 2/4/2018
 */
public interface Copiable {

    /**
     * This method is an agreement that the class that implement this object has
     * to have a deep copy method
     *
     * @return new object with same value but different address in the heap
     * @throws numberlist.InvalidIndexException
     * @throws numberlist.objectlist.UncopiableException
     */
    Copiable deepCopy() throws InvalidIndexException, UncopiableException;

}
