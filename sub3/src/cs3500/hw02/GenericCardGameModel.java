/**
 * This is an interface for a generic card game. It is parameterized over the
 * type of card. It has methods to get a valid deck for this game, start play
 * with the provided deck and number of players, and get a representation of the
 * game state at any time
 */

package cs3500.hw02;

import java.util.List;

public interface GenericCardGameModel<K> {
  /**
   * Get a deck of all the valid playing cards for this game. Different card
   * games start with different decks. This method will return a deck of valid
   * playing cards in no particular order.
   *
   * A deck is invalid if it does not contain all the cards it should, or it
   * contains duplicate cards, or it contains invalid cards (e.g. invalid suit
   * and value)
   *
   * @return a List of K objects, where K is the type of card
   */
  List<K> getDeck();

  /**
   * Start playing with the given deck of cards and the given number of players.
   * This method distributes the deck AS IT IS GIVEN amongst the players, in
   * round-robin fashion e.g. if there are 3 players and 52 cards in the deck,
   * player 0 will get card nos 0,3,6,9,.. and player 1 will get card nos.
   * 1,4,7,10,... and so on.
   *
   * The deck is to be distributed in the exact sequence as it is provided.
   * Thus, NO SHUFFLING or REORDERING is allowed.
   *
   * If a deck is to be shuffled, it must be shuffled before calling this
   * method.
   *
   * @param numPlayers the number of players playing this game
   * @param deck       the deck of cards to be distributed among the players to
   *                   start the game
   */
  void startPlay(int numPlayers, List<K> deck);

  /**
   * This method will return a string that, when displayed, gives the current
   * state of the game.
   *
   * This string should be in the following format ({S} is space, {NL} is a
   * newline, text in italics are comments and do not appear in the string).
   * Replace placeholders with actual data as applicable:
   * <pre>
   * Number of players:{S}X{NL}
   * Player{S}1:{S}P11,{S}P12,{S}P13, ...{NL} <i>(Player 1 cards in sorted
   * order)</i>
   * Player{S}2:{S}P21,{S}P22,{S}P23, ...{NL} <i>(Player 2 cards in sorted
   * order)</i>
   * ...
   * Player{S}X:{S}PX1,{S}PX2,{S}PX3, ...{NL} <i>(Player X cards in sorted
   * order)</i>
   * </pre>
   *
   * @return a string that represents the current state of the game
   */
  String getGameState();

  /**
   * Returns the list of each player's hand of cards, each repesented as their
   * own list of cards.
   *
   * @return a 2D List of Cards
   */
  List<List<K>> getPlayers();
}
