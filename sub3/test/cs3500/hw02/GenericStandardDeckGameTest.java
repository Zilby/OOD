package cs3500.hw02;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw02.GenericStandardDeckGame;

import static org.junit.Assert.*;

public class GenericStandardDeckGameTest {

  /**
   * Tests if the getDeck method creates a deck of size 52
   */
  @Test
  public void getDeckTest1() {
    GenericStandardDeckGame g = new GenericStandardDeckGame();
    List<Card> deck = g.getDeck();
    assertEquals(deck.size(), 52);
  }

  /**
   * Tests if the getDeck method creates a deck with 4 of each card value
   */
  @Test
  public void getDeckTest2() {
    GenericStandardDeckGame g = new GenericStandardDeckGame();
    List<Card> deck = g.getDeck();
    for (int i = 2; i < 15; i++) {
      int counter = 0;
      for (Card c : deck) {
        if (c.getValue() == i) {
          counter++;
        }
      }
      assertEquals(counter, 4);
    }
  }

  /**
   * Tests if the getDeck method creates a deck with 13 of each suit
   */
  @Test
  public void getDeckTest3() {
    GenericStandardDeckGame g = new GenericStandardDeckGame();
    List<Card> deck = g.getDeck();
    String s = "♣♦♥♠";
    for (char ch : s.toCharArray()) {
      int counter = 0;
      for (Card c : deck) {
        if (c.getSuit().getValue() == ch) {
          counter++;
        }
      }
      assertEquals(counter, 13);
    }
  }

  /**
   * Tests if the startPlay method creates the correct number of players
   */
  @Test
  public void startPlayTest1() {
    GenericStandardDeckGame g = new GenericStandardDeckGame(3);
    assertEquals(g.getPlayers().size(), 3);
  }

  /**
   * Tests if the startPlay method distributes cards to each player in a
   * round-robin fashion
   */
  @Test
  public void startPlayTest2() {
    GenericStandardDeckGame g = new GenericStandardDeckGame();
    List<Card> deck = g.getDeck();
    int players = 4;
    int current = 0;
    g.startPlay(players, deck);
    for (Card c : deck) {
      assertTrue(g.getPlayers().get(current).contains(c));
      current++;
      if (current == players) {
        current = 0;
      }
    }
  }

  /**
   * Tests if the startPlay method distributes cards in the exact sequence as it
   * is provided by the deck.
   */
  @Test
  public void startPlayTest3() {
    GenericStandardDeckGame g = new GenericStandardDeckGame();
    List<Card> deck = g.getDeck();
    g.startPlay(4, deck);
    int previousIndex = 0;
    for (Card c : g.getPlayers().get(0)) {
      assertTrue(deck.indexOf(c) >= previousIndex);
      previousIndex = deck.indexOf(c);
    }
  }

  /**
   * Tests if the startPlay method throws an exception if it's given no players
   */
  @Test(expected = IllegalArgumentException.class)
  public void startPlayTest4() {
    GenericStandardDeckGame g = new GenericStandardDeckGame();
    g.startPlay(0, g.getDeck());
  }

  /**
   * Tests if the startPlay method throws an exception if it's given an invalid
   * number of players
   */
  @Test(expected = IllegalArgumentException.class)
  public void startPlayTest5() {
    GenericStandardDeckGame g = new GenericStandardDeckGame();
    g.startPlay(53, g.getDeck());
  }

  /**
   * Tests if the startPlay method throws an exception if there's not 52 cards
   * in the deck.
   */
  @Test(expected = IllegalArgumentException.class)
  public void startPlayTest6() {
    GenericStandardDeckGame g = new GenericStandardDeckGame();
    g.startPlay(5, new ArrayList<Card>());
  }

  /**
   * Tests if the getGameState method breaks if there are no players
   */
  @Test
  public void getGameStateTest1() {
    GenericStandardDeckGame g = new GenericStandardDeckGame();
    assertEquals(g.getGameState(), "Number of players: 0");
  }

  /**
   * Tests if the getGameState method works as expected and sorts the players'
   * cards according to suit and value
   */
  @Test
  public void getGameStateTest2() {
    GenericStandardDeckGame g = new GenericStandardDeckGame(4);
    assertEquals(g.getGameState(), "Number of players: 4" +
            "\nPlayer 1: A♣, K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣" +
            "\nPlayer 2: A♦, K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦" +
            "\nPlayer 3: A♥, K♥, Q♥, J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥" +
            "\nPlayer 4: A♠, K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠");
  }

  /**
   * Tests if the checkValidity method works on a valid deck
   */
  @Test
  public void checkValidityTest1() {
    GenericStandardDeckGame g = new GenericStandardDeckGame();
    assert (g.checkValidity(g.getDeck()));
  }

  /**
   * Tests if the checkValidity method returns false for an invalid deck
   */
  @Test
  public void checkValidityTest2() {
    GenericStandardDeckGame g = new GenericStandardDeckGame();
    List<Card> deck = g.getDeck();
    deck.set(32, new Card(Card.number.four, Card.suit.club));
    assert (!g.checkValidity(deck));
  }

}