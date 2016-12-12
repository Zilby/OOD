////
//// YOU SHOULD NOT MODIFY THIS FILE
////
//// (You don't need to submit it, either.)
////
package cs3500.hw01.publication;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArticleTest {
  Publication turing = new Article("Computing machinery and intelligence", "A" +
          ". M. Turing", "Mind", 59, 236, 1950);

  @Test
  public void testCiteApa() {
    assertEquals("A. M. Turing (1950). Computing machinery and " +
            "intelligence. Mind, 59(236).", turing.citeApa());
  }

  @Test
  public void testCiteMla() {
    assertEquals("A. M. Turing. \"Computing machinery and " + "intelligence" +
            ".\" Mind 59.236 (1950).", turing.citeMla());
  }
}
