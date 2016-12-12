package cs3500.hw02;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class CardTest {

  @Test
  public void cardConstructorTest() {
    Card c = new Card(Card.number.eight, Card.suit.diamond);
  }

  @Test
  public void cardStringTest1() {
    Card c = new Card(Card.number.eight, Card.suit.diamond);
    assertEquals(c.toString(), "8♦");
  }

  @Test
  public void cardStringTest2() {
    Card c = new Card(Card.number.king, Card.suit.club);
    assertEquals(c.toString(), "K♣");
  }

  @Test
  public void cardCompareAndSortTest() {
    Card c1 = new Card(Card.number.king, Card.suit.heart);
    Card c2 = new Card(Card.number.six, Card.suit.diamond);
    Card c3 = new Card(Card.number.ace, Card.suit.spade);
    Card c4 = new Card(Card.number.ten, Card.suit.heart);
    Card c5 = new Card(Card.number.seven, Card.suit.club);
    Card c6 = new Card(Card.number.nine, Card.suit.diamond);
    Card c7 = new Card(Card.number.eight, Card.suit.heart);
    Card c8 = new Card(Card.number.jack, Card.suit.club);
    Card c9 = new Card(Card.number.two, Card.suit.spade);
    Card c10 = new Card(Card.number.four, Card.suit.club);
    Card c11 = new Card(Card.number.seven, Card.suit.diamond);
    Card c12 = new Card(Card.number.queen, Card.suit.spade);
    ArrayList<Card> a = new ArrayList<Card>();
    a.add(c1);
    a.add(c2);
    a.add(c3);
    a.add(c4);
    a.add(c5);
    a.add(c6);
    a.add(c7);
    a.add(c8);
    a.add(c9);
    a.add(c10);
    a.add(c11);
    a.add(c12);
    ArrayList<Card> sorted = new ArrayList<Card>();
    sorted.add(c8);
    sorted.add(c5);
    sorted.add(c10);
    sorted.add(c6);
    sorted.add(c11);
    sorted.add(c2);
    sorted.add(c1);
    sorted.add(c4);
    sorted.add(c7);
    sorted.add(c3);
    sorted.add(c12);
    sorted.add(c9);
    Collections.sort(a);
    assertEquals(a, sorted);
  }
}