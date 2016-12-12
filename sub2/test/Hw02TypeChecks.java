////
//// DO NOT MODIFY THIS FILE
////
//// You don't need to submit it, but you should make sure it compiles.
//// Further explanation appears below.
////

import java.util.List;


/**
 * This class is provided to check that your code implements the expected API.
 * If your code compiles with an unmodified version of this class, then it very
 * likely will also compile with the tests that we use to evaluate your code.
 *
 * Put this file in your test sources root
 */
public class Hw02TypeChecks {
  cs3500.hw02.GenericCardGameModel<?> gameModel = new cs3500.hw02
          .GenericStandardDeckGame();
  List<?> deck = gameModel.getDeck();

  public <K> void helper(cs3500.hw02.GenericCardGameModel<K> gameModel) {
    List<K> deck = gameModel.getDeck();
    gameModel.startPlay(5, deck);
    String state = gameModel.getGameState();
  }

  private Hw02TypeChecks() {
    throw new RuntimeException("uninstantiable");
  }
}
