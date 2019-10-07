package numberlist.objectlist;

/**
 * This class represent a complex number in terms of a + bi
 *
 * @author Seonjun Mun
 * @version 2/4/2018
 */
public final class Complex implements Copiable, Comparable<Complex> {

    private double real;
    private double imaginary;

    /**
     * default constructor
     */
    public Complex() {
    }

    /**
     * the constructor that accept values from user
     *
     * @param real      a value that represent the real number in complex number
     * @param imaginary a value that represent the imaginary number in complex
     *                  number
     */
    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    /**
     * Getter for real number
     *
     * @return real number in complex number
     */
    public double getReal() {
        return real;
    }

    /**
     * Getter for imaginary number
     *
     * @return imaginary number (i) in complex number
     */
    public double getImaginary() {
        return imaginary;
    }

    /**
     * This method sum a complex number with another complex number
     *
     * @param other another complex number that its value will be added to the
     *              complex number
     * @return new complex number object with new value from the addition
     * process
     */
    public Complex add(Complex other) {
        Complex newComplex = new Complex(real + other.getReal(),
                imaginary + other.getImaginary());
        return newComplex;
    }

    /**
     * This method subtract a complex number with another complex number
     *
     * @param other another complex number that its value will subtract the
     *              complex number
     * @return new complex number object with new value from subtraction process
     */
    public Complex subtract(Complex other) {
        Complex newComplex = new Complex(real - other.getReal(),
                imaginary - other.getImaginary());
        return newComplex;
    }

    /**
     * This method output the string value
     *
     * @return complex number in term a + bi
     */
    @Override
    public String toString() {
        if (real == 0 && imaginary == 0) {
            return "0";
        } else if (real != 0 && imaginary == 0) {
            return real + "";
        } else if (real == 0 && imaginary != 0) {
            return imaginary + "i";
        } else if (imaginary < 0) {
            double newImaginary = Math.abs(imaginary);
            return real + " - " + newImaginary + "i";
        }
        return real + " + " + imaginary + "i";
    }

    /**
     * This method make a deep copy for complex number object
     *
     * @return new complex number object that has equal value to the original
     * number
     */
    @Override
    public Copiable deepCopy() {
        return new Complex(real, imaginary);
    }

    /**
     * This method make a comparison to the complex number Using the different
     * in the magnitude to determine which one is bigger.
     *
     * @param o complex number object to be compared
     * @return result from the comparison. It will yield positive value if the
     * complex number is bigger than its comparator, negative value if it's
     * smaller and 0 if they are equal
     */
    @Override
    public int compareTo(Complex o) {
        double complexNumber1 = ((Math.sqrt((real * real)
                + (imaginary * imaginary))));
        double complexNumber2 = (Math.sqrt((o.getReal() * o.getReal())
                + (o.imaginary * o.imaginary)));

        return (int) (complexNumber1 - complexNumber2);
    }

}
