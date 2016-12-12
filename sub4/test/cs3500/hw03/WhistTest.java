package cs3500.hw03;

import org.junit.Test;

import java.io.StringReader;

import static org.junit.Assert.*;

public class WhistTest {

  /**
   * Tests if the play function will throw an exception if given an invalid
   * player (negative number)
   */
  @Test(expected = IllegalArgumentException.class)
  public void playArgsTest1() {
    WhistModel w = new WhistModel();
    w.play(-1, 3);
  }

  /**
   * Tests if the play function will throw an exception if given an invalid
   * player (too big)
   */
  @Test(expected = IllegalArgumentException.class)
  public void playArgsTest2() {
    WhistModel w = new WhistModel();
    w.play(5, 3);
  }

  /**
   * Tests if the play function will throw an exception if given an invalid card
   * index (too big)
   */
  @Test(expected = IllegalArgumentException.class)
  public void playArgsTest3() {
    WhistModel w = new WhistModel();
    w.play(2, 50);
  }

  /**
   * Tests if the play function will throw an exception if given an invalid card
   * index (negative number)
   */
  @Test(expected = IllegalArgumentException.class)
  public void playArgsTest4() {
    WhistModel w = new WhistModel();
    w.play(2, -4);
  }

  /**
   * Tests if the play function will correctly alter the gamestate on the first
   * turn. Also ensures that getGameState is working correctly
   */
  @Test
  public void playBodyTest1() {
    WhistModel w = new WhistModel();
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
            "Current suit: ♥", w.getGameState());
  }

  /**
   * Tests if the play function will throw an exception if given a card index
   * specifying a card that cannot be played because it is of the wrong suit
   * even though the player has other cards of a valid suit.
   */
  @Test(expected = IllegalArgumentException.class)
  public void playBodyTest2() {
    WhistModel w = new WhistModel(7);
    w.play(0, 3);
    w.play(1, 1);
  }

  /**
   * Tests if the play function will select a card from the ordered hand rather
   * than the unordered hand
   */
  @Test
  public void playBodyTest3() {
    WhistModel w = new WhistModel(7);
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
            "Current suit: ♥");
  }

  /**
   * Tests if the play function will correctly alter the current player
   */
  @Test
  public void playAndCurrentTest() {
    WhistModel w = new WhistModel(7);
    w.play(0, 5);
    w.play(w.getCurrentPlayer(), 4);
    w.play(w.getCurrentPlayer(), 5);
    assertEquals(w.getCurrentPlayer(), 3);
  }

  /**
   * Tests if the getCurrentPlayer function will give an exception if the game
   * is over
   */
  @Test(expected = IllegalArgumentException.class)
  public void currentTest() {
    WhistModel w = new WhistModel(1);
    w.getCurrentPlayer();
  }

  /**
   * Tests if the isGameOver function returns true if the game is over
   */
  @Test
  public void gameOverTest1() {
    WhistModel w = new WhistModel(1);
    assert (w.isGameOver());
  }

  /**
   * Tests if the isGameOver function returns false if the game isn't over
   */
  @Test
  public void gameOverTest2() {
    WhistModel w = new WhistModel(5);
    assert (!w.isGameOver());
  }

  /**
   * Tests if the getGameState function works when game ends
   */
  @Test
  public void getGameStateTest1() {
    WhistModel w = new WhistModel(1);
    assertEquals(w.getGameState(), "Number of players: 1\n" +
            "Player 1: A♣, K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣, 5♣, 4♣, 3♣, 2♣, " +
            "A♦, K♦, Q♦, J♦, 10♦, 9♦, 8♦, 7♦, 6♦, 5♦, 4♦, 3♦, 2♦, A♥, K♥, Q♥," +
            " J♥, 10♥, 9♥, 8♥, 7♥, 6♥, 5♥, 4♥, 3♥, 2♥, A♠, K♠, Q♠, J♠, 10♠, " +
            "9♠, 8♠, 7♠, 6♠, 5♠, 4♠, 3♠, 2♠\n" +
            "Player 1 score: 0\n" +
            "Game over. Player 1 won.");
  }

  /**
   * Tests if the getGameState function correctly records score, and if the play
   * function is correctly handling score
   */
  @Test
  public void getGameStateTest2() {
    WhistModel w = new WhistModel(5);
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
            "Current suit: To be decided.");
  }

  /**
   * Tests if the play function will throw an exception if given a null game
   */
  @Test(expected = IllegalArgumentException.class)
  public void controllerPlayTest1() {
    WhistController w = new WhistController(new StringReader(""), System.out);
    w.playGame(null, 5);
  }

  /**
   * Tests if the play function will throw an exception if given too few
   * players
   */
  @Test(expected = IllegalArgumentException.class)
  public void controllerPlayTest2() {
    WhistController w = new WhistController(new StringReader(""), System.out);
    w.playGame(new WhistModel(), 1);
  }

  /**
   * Tests if the play function will throw an exception if given too many
   * players
   */
  @Test(expected = IllegalArgumentException.class)
  public void controllerPlayTest3() {
    WhistController w = new WhistController(new StringReader(""), System.out);
    w.playGame(new WhistModel(), 90);
  }
}