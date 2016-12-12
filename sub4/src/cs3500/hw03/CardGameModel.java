package cs3500.hw03;

import cs3500.hw02.GenericCardGameModel;

/**
 * This is an interface for a card game. It is parameterized over the type of
 * card. It extends the GenericCardGameModel interface with the play function
 * that plays a card for a given player, a get current player function, and a
 * function that checks if the game is over.
 */
public interface CardGameModel<K> extends GenericCardGameModel<K> {

  /**
   * Plays the card at index cardIdx in the set of cards for player number
   * playerNo, assuming both these values start at 0. It is also assumed that
   * the player's cards are sorted in the same way as getGameState()
   *
   * @param playerNo the player playing the card
   * @param cardIdx  the index of the card being played
   */
  void play(int playerNo, int cardIdx);

  /**
   * Returns the player whose turn it is to play, starting with the value 0.
   * Throws an IllegalArgumentException if the game is over.
   *
   * @return an int representing the current player
   */
  int getCurrentPlayer();

  /**
   * Returns true if the game is over, false otherwise.
   *
   * @return returns a boolean representing whether the game is over
   */
  boolean isGameOver();
}
