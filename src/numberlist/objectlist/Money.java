package numberlist.objectlist;

/**
 * This class represent money value that has dollar and cents as its value
 *
 * @author Seonjun Mun
 * @version 2/4/2018
 */
public final class Money implements Copiable, Comparable<Money>, java.io.Serializable {

    private long dollars;
    private byte cents;

    /**
     * Default constructor
     */
    public Money() {
    }

    /**
     * Constructor that accept dollar and cents as its initial value
     * <p>
     * Use negative dollars and negative cents if you're looking for a negative
     * money. If you have a positive dollars and a negative cents, it means the
     * dollar has positive value and the cents has a negative value, and vice
     * versa. Your total money will be sum of your dollar and cents regarding
     * its sign.
     *
     * @param dollars value for dollar value
     * @param cents   value for cents value
     */
    public Money(long dollars, byte cents) {
        long tempValue = (dollars * 100) + cents;
        this.dollars = tempValue / 100;
        this.cents = (byte) (tempValue % 100);

    }

    /**
     * Getter for dollars
     *
     * @return the value of dollar variable
     */
    public long getDollars() {
        return dollars;
    }

    /**
     * Getter for cents
     *
     * @return the value of cents
     */
    public byte getCents() {
        return cents;
    }

    /**
     * This method sum the money's values with another money
     *
     * @param other Money object that its values will be added the this money
     * @return new money object with new value from the addition process
     */
    public Money add(Money other) {
        long tempValue = ((dollars + other.getDollars()) * 100)
                + (cents + other.getCents());
        long sumDollars = tempValue / 100;
        byte sumCents = (byte) (tempValue % 100);

        Money newMoney = new Money(sumDollars,
                sumCents);
        return newMoney;
    }

    /**
     * This method subtract the money's values with another
     *
     * @param other Money object that its values will subtract this money value
     * @return new money object with new value from the subtraction process
     */
    public Money subtract(Money other) {
        long tempValue = ((dollars - other.getDollars()) * 100)
                + (cents - other.getCents());
        long sumDollars = tempValue / 100;
        byte sumCents = (byte) (tempValue % 100);

        Money newMoney = new Money(sumDollars,
                sumCents);
        return newMoney;
    }

    /**
     * Method that represent the money value in string format ex. $2.10
     *
     * @return money's value in proper format with dollar sign
     */
    @Override
    public String toString() {
        String moneyString = "$" + dollars + "." + cents;
        return moneyString;
    }

    /**
     * This method make a new copy from the object
     *
     * @return new money objects
     */
    @Override
    public Copiable deepCopy() {
        return new Money(dollars, cents);
    }

    /**
     * This method compare the values of moneys.
     *
     * @param o Money object that its value will compared to this money object
     * @return result from the comparison. It will yield positive integer if the
     * this money value is bigger than its comparator, negative integer if it's
     * smaller and 0 if they are equal
     */
    @Override
    public int compareTo(Money o) {
        long money1 = (dollars * 100) + cents;
        long money2 = (o.getDollars() * 100) + o.getCents();
        return (int) (money1 - money2);
    }

}
