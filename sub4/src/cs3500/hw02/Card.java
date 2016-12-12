package cs3500.hw02;

/**
 * This is a class for a generic card. It is parameterized to have a value
 * (2-14, with cards higher than 10 being face cards) and a suit, representing a
 * standard card from a deck of 52 cards. It has both an implemented toString
 * method and implements comparable.
 */

public class Card implements Comparable<Card> {
  public enum number {
    two(2), three(3), four(4), five(5), six(6), seven(7), eight(8),
    nine(9), ten(10), jack(11), queen(12), king(13), ace(14);
    private final int value;

    number(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
  }

  public enum suit {
    club('♣'), diamond('♦'), heart('♥'), spade('♠');
    private final char value;

    suit(char value) {
      this.value = value;
    }

    public char getValue() {
      return value;
    }
  }

  private number n;
  private suit s;

  /**
   * Make a new card. This constructor will throw an exception if given an
   * invalid value or suit.
   *
   * @param n the value of the card (2-14)
   * @param s the suit of the card (♣♦♥♠)
   */
  public Card(number n, suit s) {
    this.n = n;
    this.s = s;
  }

  /**
   * Converts the card to its appropriate String value. This is represented as
   * value first, suit second with J, Q, K, and A representing the values
   * 11-14.
   */
  public String toString() {
    if (n.getValue() < 11) {
      return n.getValue() + Character.toString(s.getValue());
    } else {
      return "JQKA".charAt(n.getValue() - 11) + Character.toString(s.getValue
              ());
    }
  }

  /**
   * Compares two cards. Sorted order for a standard deck of cards is defined as
   * alphabetical order of suits (i.e., clubs, diamonds, hearts, spades). Within
   * each suit, cards should be ordered in descending order by number with aces
   * being highest (A, K, Q, J, 10, ..., 2).
   *
   * @param that the card being compared to
   */
  public int compareTo(Card that) {
    if (this.s.getValue() == that.s.getValue()) {
      return Integer.compare(this.n.getValue(), that.n.getValue()) * -1;
    } else {
      if ("♣♦♥♠".indexOf(this.s.getValue()) > "♣♦♥♠".indexOf(that.s.getValue
              ())) {
        return 1;
      } else {
        return -1;
      }
    }
  }

  public int getValue() {
    return n.getValue();
  }

  public suit getSuit() {
    return this.s;
  }
}
