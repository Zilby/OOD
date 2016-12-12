package cs3500.hw04;

import org.junit.Test;

import cs3500.hw02.Card;
import cs3500.hw03.WhistModel;

import static cs3500.hw04.WhistModelCreator.ModelType.*;
import static org.junit.Assert.*;

public class TrumpTest {

  /**
   * Tests if the CardComparator compare function correctly compares two cards
   * of the same number but different suits
   */
  @Test
  public void cardComparatorSuitTest1() {
    CardComparator c = new CardComparator();
    Card c1 = new Card(Card.number.king, Card.suit.heart);
    Card c2 = new Card(Card.number.king, Card.suit.spade);
    assertEquals(c.compare(c1, c2), -1);
  }

  /**
   * Tests if the CardComparator compare function correctly compares two cards
   * of the same number but different suits (opposite result)
   */
  @Test
  public void cardComparatorSuitTest2() {
    CardComparator c = new CardComparator();
    Card c1 = new Card(Card.number.king, Card.suit.heart);
    Card c2 = new Card(Card.number.king, Card.suit.club);
    assertEquals(c.compare(c1, c2), 1);
  }

  /**
   * Tests if the CardComparator compare function correctly compares two cards
   * of the same suit but different numbers
   */
  @Test
  public void cardComparatorNumberTest1() {
    CardComparator c = new CardComparator();
    Card c1 = new Card(Card.number.seven, Card.suit.heart);
    Card c2 = new Card(Card.number.ace, Card.suit.heart);
    assertEquals(c.compare(c1, c2), 1);
  }

  /**
   * Tests if the CardComparator compare function correctly compares two cards
   * of the same suit but different numbers (opposite result)
   */
  @Test
  public void cardComparatorNumberTest2() {
    CardComparator c = new CardComparator();
    Card c1 = new Card(Card.number.king, Card.suit.diamond);
    Card c2 = new Card(Card.number.five, Card.suit.diamond);
    assertEquals(c.compare(c1, c2), -1);
  }

  /**
   * Tests if the CardComparator compare function correctly compares two
   * completely different cards
   */
  @Test
  public void cardComparatorDifferentTest() {
    CardComparator c = new CardComparator();
    Card c1 = new Card(Card.number.ace, Card.suit.spade);
    Card c2 = new Card(Card.number.four, Card.suit.heart);
    assertEquals(c.compare(c1, c2), 1);
  }

  /**
   * Tests if the CardComparator compare function correctly compares two
   * identical cards
   */
  @Test
  public void cardComparatorSameTest() {
    CardComparator c = new CardComparator();
    Card c1 = new Card(Card.number.ace, Card.suit.spade);
    Card c2 = new Card(Card.number.ace, Card.suit.spade);
    assertEquals(c.compare(c1, c2), 0);
  }

  /**
   * Tests if the WhistModelCreator can correctly create an instantiation of the
   * Trump class given an enum
   */
  @Test
  public void creatorTrumpTest() {
    WhistModelCreator c = new WhistModelCreator();
    assertTrue(c.create(TRUMP) instanceof TrumpWhistModel);
  }

  /**
   * Tests if the WhistModelCreator can correctly create an instantiation of the
   * Whist class given an enum
   */
  @Test
  public void creatorWhistTest() {
    WhistModelCreator c = new WhistModelCreator();
    assertTrue(c.create(NOTRUMP) instanceof WhistModel);
  }

  /**
   * Tests if the Trump Model's play function will throw an exception if given
   * an invalid player (negative number)
   */
  @Test(expected = IllegalArgumentException.class)
  public void playArgsTest1() {
    WhistModel w = new TrumpWhistModel();
    w.play(-1, 3);
  }

  /**
   * Tests if the Trump Model's play function will throw an exception if given
   * an invalid player (too big)
   */
  @Test(expected = IllegalArgumentException.class)
  public void playArgsTest2() {
    WhistModel w = new TrumpWhistModel();
    w.play(5, 3);
  }

  /**
   * Tests if the Trump Model's play function will throw an exception if given
   * an invalid card index (too big)
   */
  @Test(expected = IllegalArgumentException.class)
  public void playArgsTest3() {
    WhistModel w = new TrumpWhistModel();
    w.play(2, 50);
  }

  /**
   * Tests if the Trump Model's play function will throw an exception if given
   * an invalid card index (negative number)
   */
  @Test(expected = IllegalArgumentException.class)
  public void playArgsTest4() {
    WhistModel w = new TrumpWhistModel();
    w.play(2, -4);
  }

  /**
   * Tests if the Trump Model's play function will correctly alter the gamestate
   * on the first turn. Also ensures that getGameState is working correctly
   */
  @Test
  public void playBodyTest1() {
    WhistModel w = new TrumpWhistModel();
    w.play(2, 3);
    assertEquals("Number of players: 4\n" +
            "Player 1: A♣, K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣\n" +
            "Player 2: A♦, K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦\n" +
            "Player 3: A♥, K♥, Q♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥\n" +
            "Player 4: A♠, K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Player 3 score: 0\n" +
            "Player 4 score: 0\n" +
            "Turn: Player 4\n" +
            "Current suit: ♥\n" +
            "Trump suit: ♣", w.getGameState());
  }

  /**
   * Tests if the Trump Model's play function will throw an exception if given a
   * card index specifying a card that cannot be played because it is of the
   * wrong suit even though the player has other cards of a valid suit.
   */
  @Test(expected = IllegalArgumentException.class)
  public void playBodyTest2() {
    WhistModel w = new TrumpWhistModel(7);
    w.play(0, 3);
    w.play(1, 7);
  }

  /**
   * Tests if the Trump Model's play function will allow a player to play a card
   * if given a card index of a card that is not the current suit but is the
   * trump suit
   */
  @Test
  public void playBodyTest3() {
    WhistModel w = new TrumpWhistModel(7);
    w.play(0, 3);
    w.play(1, 1);
    assertEquals(w.getGameState(), "Number of players: 7\n" +
            "Player 1: 9♣, 2♣, A♦, Q♥, 5♥, 10♠, 3♠\n" +
            "Player 2: J♣, 9♦, 2♦, A♥, 7♥, Q♠, 5♠\n" +
            "Player 3: K♣, 6♣, J♦, 4♦, 9♥, 2♥, A♠, 7♠\n" +
            "Player 4: 8♣, K♦, 6♦, J♥, 4♥, 9♠, 2♠\n" +
            "Player 5: 10♣, 3♣, 8♦, K♥, 6♥, J♠, 4♠\n" +
            "Player 6: Q♣, 5♣, 10♦, 3♦, 8♥, K♠, 6♠\n" +
            "Player 7: A♣, 7♣, Q♦, 5♦, 10♥, 3♥, 8♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Player 3 score: 0\n" +
            "Player 4 score: 0\n" +
            "Player 5 score: 0\n" +
            "Player 6 score: 0\n" +
            "Player 7 score: 0\n" +
            "Turn: Player 3\n" +
            "Current suit: ♦\n" +
            "Trump suit: ♣");
  }

  /**
   * Tests if the Trump Model's play function will select a card from the
   * ordered hand rather than the unordered hand
   */
  @Test
  public void playBodyTest4() {
    WhistModel w = new TrumpWhistModel(7);
    w.play(0, 5);
    assertEquals(w.getGameState(), "Number of players: 7\n" +
            "Player 1: 9♣, 2♣, A♦, 7♦, Q♥, 10♠, 3♠\n" +
            "Player 2: J♣, 4♣, 9♦, 2♦, A♥, 7♥, Q♠, 5♠\n" +
            "Player 3: K♣, 6♣, J♦, 4♦, 9♥, 2♥, A♠, 7♠\n" +
            "Player 4: 8♣, K♦, 6♦, J♥, 4♥, 9♠, 2♠\n" +
            "Player 5: 10♣, 3♣, 8♦, K♥, 6♥, J♠, 4♠\n" +
            "Player 6: Q♣, 5♣, 10♦, 3♦, 8♥, K♠, 6♠\n" +
            "Player 7: A♣, 7♣, Q♦, 5♦, 10♥, 3♥, 8♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Player 3 score: 0\n" +
            "Player 4 score: 0\n" +
            "Player 5 score: 0\n" +
            "Player 6 score: 0\n" +
            "Player 7 score: 0\n" +
            "Turn: Player 2\n" +
            "Current suit: ♥\n" +
            "Trump suit: ♣");
  }

  /**
   * Tests if the Trump Model's play function will correctly alter the current
   * player
   */
  @Test
  public void playAndCurrentTest() {
    WhistModel w = new TrumpWhistModel(7);
    w.play(0, 5);
    w.play(w.getCurrentPlayer(), 4);
    w.play(w.getCurrentPlayer(), 5);
    assertEquals(w.getCurrentPlayer(), 3);
  }

  /**
   * Tests if the Trump Model's getCurrentPlayer function will give an exception
   * if the game is over
   */
  @Test(expected = IllegalArgumentException.class)
  public void currentTest() {
    WhistModel w = new TrumpWhistModel(1);
    w.getCurrentPlayer();
  }

  /**
   * Tests if the Trump Model's isGameOver function returns true if the game is
   * over
   */
  @Test
  public void gameOverTest1() {
    WhistModel w = new TrumpWhistModel(1);
    assert (w.isGameOver());
  }

  /**
   * Tests if the Trump Model's isGameOver function returns false if the game
   * isn't over
   */
  @Test
  public void gameOverTest2() {
    WhistModel w = new TrumpWhistModel(5);
    assert (!w.isGameOver());
  }

  /**
   * Tests if the Trump Model's getGameState function works when game ends
   */
  @Test
  public void getGameStateTest() {
    WhistModel w = new TrumpWhistModel(1);
    assertEquals(w.getGameState(), "Number of players: 1\n" +
            "Player 1: A♣, K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, " +
            "A♦, K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♥, K♥, Q♥," +
            " J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♠, K♠, Q♠, J♠, 10♠, " +
            "9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠\n" +
            "Player 1 score: 0\n" +
            "Game over. Player 1 won.");
  }

  /**
   * Tests if the Trump Model's getGameState function correctly records score,
   * and if the play function is correctly handling score
   */
  @Test
  public void getGameStateScoreTest1() {
    WhistModel w = new TrumpWhistModel(5);
    for (int i = 0; i < 40; i++) {
      w.play(w.getCurrentPlayer(), 0);
    }
    assertEquals(w.getGameState(), "Number of players: 5\n" +
            "Player 1: 4♥, 10♠, 5♠\n" +
            "Player 2: A♠, 9♠, 4♠\n" +
            "Player 3: 8♠, 3♠\n" +
            "Player 4: 7♠, 2♠\n" +
            "Player 5: J♠, 6♠\n" +
            "Player 1 score: 2\n" +
            "Player 2 score: 0\n" +
            "Player 3 score: 0\n" +
            "Player 4 score: 3\n" +
            "Player 5 score: 3\n" +
            "Turn: Player 1\n" +
            "Current suit: To be decided.\n" +
            "Trump suit: ♣");
  }

  /**
   * Tests if the Trump Model's getGameState function correctly records
   * score, with Trump cards played and if the play function is correctly
   * handling score
   */
  @Test
  public void getGameStateScoreTest2() {
    WhistModel w = new TrumpWhistModel(5);
    w.play(w.getCurrentPlayer(), 5);
    w.play(w.getCurrentPlayer(), 2);
    w.play(w.getCurrentPlayer(), 1);
    w.play(w.getCurrentPlayer(), 3);
    w.play(w.getCurrentPlayer(), 3);
    assertEquals(w.getGameState(), "Number of players: 5\n" +
            "Player 1: Q♣, 7♣, 2♣, K♦, 8♦, A♥, 9♥, 4♥, 10♠, 5♠\n" +
            "Player 2: J♣, 6♣, 7♦, 2♦, K♥, 8♥, 3♥, A♠, 9♠, 4♠\n" +
            "Player 3: 10♣, J♦, 6♦, Q♥, 7♥, 2♥, K♠, 8♠, 3♠\n" +
            "Player 4: A♣, 9♣, 4♣, 5♦, J♥, 6♥, Q♠, 7♠, 2♠\n" +
            "Player 5: K♣, 8♣, 3♣, 9♦, 4♦, 10♥, 5♥, J♠, 6♠\n" +
            "Player 1 score: 0\n" +
            "Player 2 score: 0\n" +
            "Player 3 score: 1\n" +
            "Player 4 score: 0\n" +
            "Player 5 score: 0\n" +
            "Turn: Player 1\n" +
            "Current suit: To be decided.\n" +
            "Trump suit: ♣");
  }

  /**
   * Tests if the Trump Model's getGameState function correctly displays a
   * winner for this game of Whist (player 3 wins)
   */
  @Test
  public void getGameStateWinTest1() {
    WhistModel w = new TrumpWhistModel(3);
    w.play(w.getCurrentPlayer(), 5);
    w.play(w.getCurrentPlayer(), 2);
    w.play(w.getCurrentPlayer(), 3);
    w.play(w.getCurrentPlayer(), 7);
    w.play(w.getCurrentPlayer(), 6);
    w.play(w.getCurrentPlayer(), 1);
    w.play(w.getCurrentPlayer(), 4);
    w.play(w.getCurrentPlayer(), 2);
    w.play(w.getCurrentPlayer(), 0);
    w.play(w.getCurrentPlayer(), 12);
    w.play(w.getCurrentPlayer(), 11);
    w.play(w.getCurrentPlayer(), 0);
    w.play(w.getCurrentPlayer(), 7);
    w.play(w.getCurrentPlayer(), 6);
    w.play(w.getCurrentPlayer(), 7);
    w.play(w.getCurrentPlayer(), 5);
    w.play(w.getCurrentPlayer(), 0);
    w.play(w.getCurrentPlayer(), 1);
    w.play(w.getCurrentPlayer(), 1);
    w.play(w.getCurrentPlayer(), 0);
    w.play(w.getCurrentPlayer(), 6);
    w.play(w.getCurrentPlayer(), 8);
    w.play(w.getCurrentPlayer(), 9);
    w.play(w.getCurrentPlayer(), 8);
    w.play(w.getCurrentPlayer(), 6);
    w.play(w.getCurrentPlayer(), 6);
    w.play(w.getCurrentPlayer(), 5);
    w.play(w.getCurrentPlayer(), 7);
    w.play(w.getCurrentPlayer(), 7);
    w.play(w.getCurrentPlayer(), 7);
    w.play(w.getCurrentPlayer(), 2);
    w.play(w.getCurrentPlayer(), 3);
    w.play(w.getCurrentPlayer(), 2);
    w.play(w.getCurrentPlayer(), 0);
    w.play(w.getCurrentPlayer(), 4);
    w.play(w.getCurrentPlayer(), 1);
    w.play(w.getCurrentPlayer(), 0);
    w.play(w.getCurrentPlayer(), 2);
    w.play(w.getCurrentPlayer(), 4);
    w.play(w.getCurrentPlayer(), 4);
    w.play(w.getCurrentPlayer(), 3);
    w.play(w.getCurrentPlayer(), 3);
    w.play(w.getCurrentPlayer(), 2);
    w.play(w.getCurrentPlayer(), 2);
    w.play(w.getCurrentPlayer(), 2);
    w.play(w.getCurrentPlayer(), 0);
    w.play(w.getCurrentPlayer(), 0);
    w.play(w.getCurrentPlayer(), 0);
    w.play(w.getCurrentPlayer(), 0);
    w.play(w.getCurrentPlayer(), 0);
    w.play(w.getCurrentPlayer(), 0);
    assertEquals(w.getGameState(), "Number of players: 3\n" +
            "Player 1: A♠\n" +
            "Player 2\n" +
            "Player 3\n" +
            "Player 1 score: 5\n" +
            "Player 2 score: 5\n" +
            "Player 3 score: 7\n" +
            "Game over. Player 3 won.");
  }

  /**
   * Tests if the Trump Model's getGameState function correctly displays a
   * winner for this game of Whist (player 1 wins)
   */
  @Test
  public void getGameStateWinTest2() {
    WhistModel w = new TrumpWhistModel(3);
    w.play(w.getCurrentPlayer(), 5);
    w.play(w.getCurrentPlayer(), 2);
    w.play(w.getCurrentPlayer(), 3);
    w.play(w.getCurrentPlayer(), 7);
    w.play(w.getCurrentPlayer(), 6);
    w.play(w.getCurrentPlayer(), 1);
    w.play(w.getCurrentPlayer(), 4);
    w.play(w.getCurrentPlayer(), 2);
    w.play(w.getCurrentPlayer(), 0);
    w.play(w.getCurrentPlayer(), 12);
    w.play(w.getCurrentPlayer(), 11);
    w.play(w.getCurrentPlayer(), 0);
    w.play(w.getCurrentPlayer(), 7);
    w.play(w.getCurrentPlayer(), 6);
    w.play(w.getCurrentPlayer(), 7);
    w.play(w.getCurrentPlayer(), 5);
    w.play(w.getCurrentPlayer(), 0);
    w.play(w.getCurrentPlayer(), 1);
    w.play(w.getCurrentPlayer(), 1);
    w.play(w.getCurrentPlayer(), 0);
    w.play(w.getCurrentPlayer(), 6);
    w.play(w.getCurrentPlayer(), 8);
    w.play(w.getCurrentPlayer(), 9);
    w.play(w.getCurrentPlayer(), 8);
    w.play(w.getCurrentPlayer(), 6);
    w.play(w.getCurrentPlayer(), 6);
    w.play(w.getCurrentPlayer(), 5);
    w.play(w.getCurrentPlayer(), 7);
    w.play(w.getCurrentPlayer(), 7);
    w.play(w.getCurrentPlayer(), 7);
    w.play(w.getCurrentPlayer(), 2);
    w.play(w.getCurrentPlayer(), 3);
    w.play(w.getCurrentPlayer(), 2);
    w.play(w.getCurrentPlayer(), 0);
    w.play(w.getCurrentPlayer(), 4);
    w.play(w.getCurrentPlayer(), 1);
    w.play(w.getCurrentPlayer(), 0);
    w.play(w.getCurrentPlayer(), 2);
    w.play(w.getCurrentPlayer(), 4);
    w.play(w.getCurrentPlayer(), 4);
    w.play(w.getCurrentPlayer(), 3);
    w.play(w.getCurrentPlayer(), 3);
    w.play(w.getCurrentPlayer(), 3);
    w.play(w.getCurrentPlayer(), 2);
    w.play(w.getCurrentPlayer(), 1);
    w.play(w.getCurrentPlayer(), 1);
    w.play(w.getCurrentPlayer(), 0);
    w.play(w.getCurrentPlayer(), 1);
    w.play(w.getCurrentPlayer(), 0);
    w.play(w.getCurrentPlayer(), 0);
    w.play(w.getCurrentPlayer(), 0);
    assertEquals(w.getGameState(), "Number of players: 3\n" +
            "Player 1: 6♥\n" +
            "Player 2\n" +
            "Player 3\n" +
            "Player 1 score: 7\n" +
            "Player 2 score: 4\n" +
            "Player 3 score: 6\n" +
            "Game over. Player 1 won.");
  }
}
