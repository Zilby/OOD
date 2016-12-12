package cs3500.hw03;

import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;

/**
 * This is an implementation of the IWhistController interface for a Whist card
 * game controller. It supports playing the game and takes in a Readable rd and
 * Appendable ap in its constructor.
 */
public class WhistController implements IWhistController {
  private Scanner input;
  private Formatter out;
  WhistModel game;

  public WhistController(Readable rd, Appendable ap) {
    this.input = new Scanner(rd);
    this.out = new Formatter(ap);
  }

  /**
   * playGame will start the provided game model with the given number of
   * players, throwing an exception if the model is null or the number of
   * players is invalid. It will also transmit the gamestate to our Appendable
   * object, and wait for user input for our Readable object. If given a 'q' it
   * will quit the game, and if given an invalid input it will ask the user to
   * try again.
   *
   * @param game       the game model to be used
   * @param numPlayers the number of players to be used
   */
  public void playGame(CardGameModel game, int numPlayers) {
    if (game == null || numPlayers > 52 || numPlayers < 2) {
      throw new IllegalArgumentException("Arguments are invalid");
    }
    this.game = new WhistModel(numPlayers);
    out.format(this.game.getGameState());
    while (!this.game.isGameOver()) {
      if (input.hasNextInt()) {
        try {
          this.game.play(this.game.getCurrentPlayer(), input.nextInt());
          out.format(this.game.getGameState());
        } catch (IllegalArgumentException i) {
          out.format("Try again. Invalid input.");
        }
      } else if (input.next().equals("q")) {
        out.format("Game quit prematurely.");
        break;
      } else {
        out.format("Try again. Invalid input.");
      }
    }
  }
}
