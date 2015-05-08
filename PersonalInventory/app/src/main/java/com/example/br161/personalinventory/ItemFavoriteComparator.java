package com.example.br161.personalinventory;

import java.util.Comparator;
/**
 * Created by Kyle on 5/8/2015.
 */
public class ItemFavoriteComparator implements Comparator<Item> {
    /**
     * Compares the two specified objects to determine their relative ordering. The ordering
     * implied by the return value of this method for all possible pairs of
     * {@code (lhs, rhs)} should form an <i>equivalence relation</i>.
     * This means that
     * <ul>
     * <li>{@code compare(a, a)} returns zero for all {@code a}</li>
     * <li>the sign of {@code compare(a, b)} must be the opposite of the sign of {@code
     * compare(b, a)} for all pairs of (a,b)</li>
     * <li>From {@code compare(a, b) > 0} and {@code compare(b, c) > 0} it must
     * follow {@code compare(a, c) > 0} for all possible combinations of {@code
     * (a, b, c)}</li>
     * </ul>
     *
     * @param item1 an {@code Object}.
     * @param item2 a second {@code Object} to compare with {@code lhs}.
     * @return an integer < 0 if {@code lhs} is less than {@code rhs}, 0 if they are
     * equal, and > 0 if {@code lhs} is greater than {@code rhs}.
     * @throws ClassCastException if objects are not of the correct type.
     */
    @Override
    public int compare(Item item1, Item item2) {
        if (item1.isFavorite() && !(item2.isFavorite()))
            return -1;
        else if (!(item1.isFavorite()) && item2.isFavorite())
            return 1;
        else
            return 0;
    }//end compare method
}//end ItemFavoriteComparator class
