package cs3500.hw03;

import java.io.IOException;

/**
 * This is an interface for a Whist card game controller. I supports only the
 * playGame functionality.
 */
public interface IWhistController {

  /**
   * playGame will start the provided game model with the given number of
   * players, throwing an exception if the model is null or the number of
   * players is invalid.
   *
   * @param game       the game model to be used
   * @param numPlayers the number of players to be used
   */
  <K> void playGame(CardGameModel<K> game, int numPlayers);
}
