package publication;

import org.testng.annotations.Test;
import static org.junit.Assert.*;

public class ArticleTest {
    Publication turing =
            new Article("Computing machinery and intelligence",
                    "A. M. Turing", "Mind", 59, 236, 1950);

    @Test
    public void testCiteApa() {
        assertEquals("A. M. Turing (1950). Computing machinery and "
                        + "intelligence. Mind, 59(236).",
                turing.citeApa());
    }

    @Test
    public void testCiteMla() {
        assertEquals("A. M. Turing. \"Computing machinery and "
                        + "intelligence.\" Mind 59.236 (1950).",
                turing.citeMla());
    }
}