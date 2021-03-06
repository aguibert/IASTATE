/**
 * 
 */
package se339.hw3.products;

/**
 * @author aguibert
 * 
 */
public interface Rentable
{
    public String getName();

    public double getRentalCost();

    public boolean isNewRelease();

    public abstract int daysOverdue();

    public int getDaysRented();

    public int getFrequentRenterPoints();
}
