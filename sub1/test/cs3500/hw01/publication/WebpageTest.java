package cs3500.hw01.publication;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zilby on 5/12/16.
 */
public class WebpageTest {
  Webpage example = new Webpage("Example Page", "http://www.example" +
          ".com/example", "May 13, 2016");

  @Test
  public void citeApa() throws Exception {
    assertEquals("Example Page. Retrieved May 13, 2016, " + "from http://www" +
            ".example.com/example.", example.citeApa());
  }

  @Test
  public void citeMla() throws Exception {
    assertEquals("\\\"Example Page.\\\" Web. May 13, 2016" + " <http://www" +
            ".example.com/example>.", example.citeMla());
  }

}