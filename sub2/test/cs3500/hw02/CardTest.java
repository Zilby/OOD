package cs3500.hw02;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class CardTest {

  @Test(expected = IllegalArgumentException.class)
  public void cardConstructorTest1() {
    Card c = new Card(1, '♦');
  }

  @Test(expected = IllegalArgumentException.class)
  public void cardConstructorTest2() {
    Card c = new Card(31, '♦');
  }

  @Test(expected = IllegalArgumentException.class)
  public void cardConstructorTest3() {
    Card c = new Card(8, 'c');
  }

  @Test
  public void cardConstructorTest4() {
    Card c = new Card(8, '♦');
  }

  @Test
  public void cardStringTest1() {
    Card c = new Card(8, '♦');
    assertEquals(c.toString(), "8♦");
  }

  @Test
  public void cardStringTest2() {
    Card c = new Card(13, '♣');
    assertEquals(c.toString(), "K♣");
  }

  @Test
  public void cardCompareAndSortTest() {
    Card c1 = new Card(13, '♥');
    Card c2 = new Card(6, '♦');
    Card c3 = new Card(14, '♠');
    Card c4 = new Card(10, '♥');
    Card c5 = new Card(7, '♣');
    Card c6 = new Card(9, '♦');
    Card c7 = new Card(8, '♥');
    Card c8 = new Card(11, '♣');
    Card c9 = new Card(2, '♠');
    Card c10 = new Card(4, '♣');
    Card c11 = new Card(7, '♦');
    Card c12 = new Card(12, '♠');
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