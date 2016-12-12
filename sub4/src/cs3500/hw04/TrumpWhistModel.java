package cs3500.hw04;

import java.util.Collections;
import java.util.List;

import cs3500.hw02.Card;
import cs3500.hw03.CardGameModel;
import cs3500.hw03.WhistModel;


/**
 * This is an implementation of the CardGameModel for Whist that extends the
 * WhistModel class. It has the added functionality Trump cards, modifying the
 * gameplay slightly to the new set of rules.
 */
public class TrumpWhistModel extends WhistModel implements CardGameModel<Card> {
  private Card.suit trumpSuit;

  public TrumpWhistModel() {
    this(4);
  }

  public TrumpWhistModel(int players) {
    this.startPlay(players, this.getDeck());
  }

  public TrumpWhistModel(int players, List<Card> deck) {
    this.startPlay(players, deck);
  }

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
   * This method additionally sets the first card in the deck's suit as the
   * trumpSuit for the game in this implementation
   *
   * @param numPlayers the number of players playing this game
   * @param deck       the deck of cards to be distributed among the players to
   *                   start the game
   */
  @Override
  public void startPlay(int numPlayers, List<Card> deck) {
    this.trumpSuit = deck.get(0).getSuit();
    super.startPlay(numPlayers, deck);
  }

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
   *
   * Special message
   * </pre>
   *
   * The special message will either be "Turn: Player X" with x being whose turn
   * it is to play next or "Game over. Player X won." if the game has ended. I
   * also added an extra line saying "Current suit: X" with x being the current
   * suit to be played for the trick so that it is easier for a player to
   * determine what cards can be legally played for their turn.
   *
   * This implementation adds "Trump suit: X" with x being the current trump
   * suit for the game to the special message as long as the game is not in a
   * game over state.
   *
   * @return a string that represents the current state of the game
   */
  @Override
  public String getGameState() {
    String result = super.getGameState();
    if (!this.isGameOver()) {
      result += "\nTrump suit: " + this.trumpSuit.getValue();
    }
    return result;
  }

  /**
   * Plays the card at index cardIdx in the set of cards for player number
   * playerNo, assuming both these values start at 0. It is also assumed that
   * the player's cards are sorted in the same way as getGameState(). This will
   * also throw an exception if given an invalid player or card number, or if
   * the player tries to play a card that is in violation of the current suit if
   * they're not starting a new trick if their hand also has a card of the same
   * suit. This function will also advance the current turn, update the player's
   * scores when a trick ends, update which player and card currently has the
   * highest value for the current trick, and who is the current player.
   *
   * This implementation adds in trump cards, allowing players to play a card of
   * a different suit as long as it's the same suit as the trump card, and makes
   * the current top card the card of the highest value of the trump suit (if
   * any of cards of the trump suit are played).
   *
   * @param playerNo the player playing the card
   * @param cardIdx  the index of the card being played
   */
  @Override
  public void play(int playerNo, int cardIdx) {
    if (playerNo >= this.getPlayers().size() || playerNo < 0) {
      throw new IllegalArgumentException("bad playerNo");
    }
    if (cardIdx >= this.getPlayers().get(playerNo).size() || cardIdx < 0) {
      throw new IllegalArgumentException("bad cardIdx");
    }
    List<Card> hand = this.getPlayers().get(playerNo);
    Collections.sort(hand);
    if (turn == 1) {
      this.currentSuit = hand.get(cardIdx).getSuit();
      this.topCard = hand.get(cardIdx);
      this.topPlayer = this.getCurrentPlayer();
    } else if (this.currentSuit.getValue() != hand.get(cardIdx).getSuit()
            .getValue()) {
      if (this.trumpSuit.getValue() == hand.get(cardIdx).getSuit().getValue()) {
        if (this.topCard.getSuit().getValue() != this.trumpSuit.getValue() ||
                this.topCard.getValue() < hand.get(cardIdx).getValue()) {
          this.topCard = hand.get(cardIdx);
          this.topPlayer = this.getCurrentPlayer();
        }
      } else {
        for (Card c : hand) {
          if (c.getSuit().getValue() == this.currentSuit.getValue()) {
            throw new IllegalArgumentException("Cannot play a card of a " +
                    "different suit while having a card of the same suit if " +
                    "it's not of the trump suit." +
                    ".");
          }
        }
      }
    } else if (this.topCard.getValue() < hand.get(cardIdx).getValue() && (this
            .topCard.getSuit().getValue() != this.trumpSuit.getValue() ||
            this.currentSuit.getValue() == this.trumpSuit.getValue())) {
      this.topCard = hand.get(cardIdx);
      this.topPlayer = this.getCurrentPlayer();
    }
    this.getPlayers().get(playerNo).remove(hand.get(cardIdx));
    if (playerNo >= this.getPlayers().size() - 1) {
      this.currentPlayer = 0;
    } else {
      this.currentPlayer = playerNo + 1;
    }
    if (this.turn >= this.totalPlayers) {
      this.turn = 1;
      scores.set(this.topPlayer, scores.get(this.topPlayer) + 1);
    } else {
      this.turn++;
    }
  }
}
