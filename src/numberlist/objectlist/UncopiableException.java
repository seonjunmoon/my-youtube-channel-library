package numberlist.objectlist;

/**
 * @author Seonjun Mun
 */
public class UncopiableException extends Exception {

    public UncopiableException() {
        super("Object is not copiable.");
    }
}
