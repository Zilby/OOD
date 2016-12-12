package cs3500.hw04;

import java.util.Comparator;

import cs3500.hw02.Card;

/**
 * This is a class for comparing generic cards, and implements the compare
 * method, utilizing our generic cards' compareTo method.
 */
public class CardComparator implements Comparator<Card> {

  /**
   * Compares two cards. Sorted order for a standard deck of cards is defined as
   * alphabetical order of suits (i.e., clubs, diamonds, hearts, spades). Within
   * each suit, cards should be ordered in descending order by number with aces
   * being highest (A, K, Q, J, 10, ..., 2).
   *
   * @param c1 the first card being compared
   * @param c2 the second card being compared
   * @return an int representing the result of our comparison
   */
  public int compare(Card c1, Card c2) {
    return c1.compareTo(c2);
  }
}
