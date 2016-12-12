package cs3500.hw01.publication;

/**
 * Represents bibliographic information for webpages.
 */
public class Webpage implements Publication {
  private final String title, url, retrieved;

  /**
   * Constructs an article.
   *
   * @param title     the title of the webpage
   * @param url       the url of the webpage
   * @param retrieved the date the webpage was downloaded
   */

  public Webpage(String title, String url, String retrieved) {
    this.title = title;
    this.url = url;
    this.retrieved = retrieved;
  }

  @Override
  public String citeApa() {
    return title + ". Retrieved " + retrieved + ", from " + url + ".";
  }

  @Override
  public String citeMla() {
    return "\\\"" + title + ".\\\" Web. " +
            retrieved + " <" + url + ">.";
  }
}