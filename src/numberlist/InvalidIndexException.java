package numberlist;

/**
 * @author Seonjun Mun
 * @version 2/4/2018
 */
public class InvalidIndexException extends Exception {

    private int lowIndex;
    private int highIndex;
    private int invalidIndex;

    public InvalidIndexException(int lowIndex, int highIndex, int invalidIndex) {
        super("Low index: " + lowIndex + "." + " High index: " + highIndex + "." + " Index entered: " + invalidIndex + ".");
        this.lowIndex = lowIndex;
        this.highIndex = highIndex;
        this.invalidIndex = invalidIndex;
    }

    public int getLowIndex() {
        return lowIndex;
    }

    public int getHighIndex() {
        return highIndex;
    }

    public int getInvalidIndex() {
        return invalidIndex;
    }

}
