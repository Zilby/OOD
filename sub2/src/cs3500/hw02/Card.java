package cs3500.hw02;

/**
 * This is a class for a generic card. It is parameterized to have a value
 * (2-14, with cards higher than 10 being face cards) and a suit, representing a
 * standard card from a deck of 52 cards. It has both an implemented toString
 * method and implements comparable.
 */
public class Card implements Comparable<Card> {
  int value;
  char suit;

  /**
   * Make a new card. This constructor will throw an exception if given an
   * invalid value or suit.
   *
   * @param value the value of the card (2-14)
   * @param suit  the suit of the card (♣♦♥♠)
   */
  public Card(int value, char suit) {
    if (value < 2 || value > 14) {
      throw new IllegalArgumentException("Invalid card value");
    } else if ("♣♦♥♠".indexOf(suit) == -1) {
      throw new IllegalArgumentException("Invalid suit");
    } else {
      this.value = value;
      this.suit = suit;
    }
  }

  /**
   * Converts the card to its appropriate String value. This is represented as
   * value first, suit second with J, Q, K, and A representing the values
   * 11-14.
   */
  public String toString() {
    if (value < 11) {
      return value + Character.toString(suit);
    } else {
      return "JQKA".charAt(value - 11) + Character.toString(suit);
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
    if (this.suit == that.suit) {
      return Integer.compare(this.value, that.value) * -1;
    } else {
      if ("♣♦♥♠".indexOf(this.suit) > "♣♦♥♠".indexOf(that.suit)) {
        return 1;
      } else {
        return -1;
      }
    }
  }
}
