package cs3500.hw01.duration;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests for {@link Duration}s.
 */
public abstract class AbstractDurationTest {
  @Test
  public void formatExample1() {
    assertEquals("4 hours, 0 minutes, and 9 seconds", hms(4, 0, 9).format("%h" +
            " hours, %m minutes, and %s seconds"));
  }

  @Test
  public void formatExample2() {
    assertEquals("4:05:17", hms(4, 5, 17).format("%h:%M:%S"));
  }

  @Test
  public void formatTest1() {
    assertEquals("4245 total seconds.", hms(1, 10, 45).format("%t total " +
            "seconds."));
  }

  @Test
  public void formatTest2() {
    assertEquals("3 hours, 37 minutes, 18 seconds.", hms(3, 37, 18).format
            ("%h hours, %m minutes, %s seconds."));
  }

  @Test
  public void formatTest3() {
    assertEquals("03:05:09 with leading zeros", hms(3, 5, 9).format("%H:%M:%S" +
            " with leading zeros"));
  }

  @Test
  public void formatTest4() {
    assertEquals("13:55:39 without leading zeros", hms(13, 55, 39).format
            ("%H:%M:%S without leading zeros"));
  }

  @Test
  public void formatTest5() {
    assertEquals("% percents are fine.", hms(0, 0, 0).format("%% percents are" +
            " fine."));
  }

  @Test(expected = IllegalArgumentException.class)
  public void formatTest6() {
    hms(0, 0, 0).format("Bad Symbol: %d.");
  }

  @Test(expected = IllegalArgumentException.class)
  public void formatTest7() {
    hms(0, 0, 0).format("No symbol after %%: %");
  }

  /**
   * Constructs an instance of the class under test representing the duration
   * given in hours, minutes, and seconds.
   *
   * @param hours   the hours in the duration
   * @param minutes the minutes in the duration
   * @param seconds the seconds in the duration
   * @return an instance of the class under test
   */
  protected abstract Duration hms(int hours, int minutes, int seconds);

  /**
   * Constructs an instance of the class under test representing the duration
   * given in seconds.
   *
   * @param inSeconds the total seconds in the duration
   * @return an instance of the class under test
   */
  protected abstract Duration sec(long inSeconds);

  public final static class HmsDurationTest extends AbstractDurationTest {
    @Override
    protected Duration hms(int hours, int minutes, int seconds) {
      return new HmsDuration(hours, minutes, seconds);
    }

    @Override
    protected Duration sec(long inSeconds) {
      return new HmsDuration(inSeconds);
    }
  }

  public final static class CompactDurationTest extends AbstractDurationTest {
    @Override
    protected Duration hms(int hours, int minutes, int seconds) {
      return new CompactDuration(hours, minutes, seconds);
    }

    @Override
    protected Duration sec(long inSeconds) {
      return new CompactDuration(inSeconds);
    }
  }

  static long s1 = 328375982;
  static long s2 = 299699;

  Duration d1_23_45 = hms(1, 23, 45);
  Duration d2_03_00 = hms(2, 3, 0);
  Duration d457_03_00 = hms(457, 3, 0);

  @Test
  public void zeroIsZero() {
    assertEquals(0, hms(0, 0, 0).inSeconds());
  }

  @Test
  public void asHmsWorks() {
    assertEquals("1:23:45", d1_23_45.asHms());
    assertEquals("2:03:00", d2_03_00.asHms());
    assertEquals("457:03:00", d457_03_00.asHms());
  }

  @Test
  public void equalsWorks() {
    assertEquals(hms(1, 23, 45), d1_23_45);
    assertEquals(d1_23_45, d1_23_45);
    assertEquals(sec(s1), sec(s1));
    assertEquals(sec(s2), sec(s2));

    assertNotEquals(sec(s1), sec(s2));
    assertNotEquals(sec(s2), sec(s1));
    assertNotEquals(d1_23_45, d2_03_00);
    assertNotEquals(d2_03_00, d1_23_45);
    assertNotEquals(hms(1, 23, 44), hms(1, 23, 45));
  }

  @Test
  public void hashCodeIsHashOfSeconds() {
    assertEquals(Long.hashCode(s1), sec(s1).hashCode());
    assertEquals(Long.hashCode(s2), sec(s2).hashCode());
    assertEquals(Long.hashCode(d1_23_45.inSeconds()), d1_23_45.hashCode());
    assertEquals(Long.hashCode(d457_03_00.inSeconds()), d457_03_00.hashCode());
  }

  @Test
  public void hmsConstructorCarriesMinutesAndSeconds() {
    assertEquals("2:33:45", hms(1, 93, 45).asHms());
    assertEquals("2:34:08", hms(2, 33, 68).asHms());
    assertEquals("4:34:34", hms(2, 33, 7294).asHms());
  }

  @Test(expected = IllegalArgumentException.class)
  public void hmsConstructorDisallowsNegative() {
    hms(2, 4, -9);
  }

  @Test(expected = IllegalArgumentException.class)
  public void secondsConstructorDisallowsNegative() {
    sec(-1);
  }

  @Test
  public void inSecondsIsInverseOfSecondsConstructor() {
    assertEquals(s1, sec(s1).inSeconds());
    assertEquals(s2, sec(s2).inSeconds());

    assertEquals(sec(s1), sec(sec(s1).inSeconds()));
    assertEquals(sec(s2), sec(sec(s2).inSeconds()));

    assertEquals(d1_23_45, sec(d1_23_45.inSeconds()));
    assertEquals(d2_03_00, sec(d2_03_00.inSeconds()));
    assertEquals(d457_03_00, sec(d457_03_00.inSeconds()));
  }

  @Test
  public void _1_23_03_plus_2_14_45_is_3_37_48() {
    assertEquals(hms(3, 37, 48), hms(1, 23, 3).plus(hms(2, 14, 45)));
  }

  @Test
  public void plusCarries() {
    assertEquals(hms(3, 38, 18), hms(1, 23, 33).plus(hms(2, 14, 45)));
    assertEquals(hms(4, 19, 48), hms(1, 23, 3).plus(hms(2, 56, 45)));
    assertEquals(hms(4, 20, 18), hms(1, 23, 33).plus(hms(2, 56, 45)));
  }

  @Test
  public void addWorks() {
    assertEquals(s1 + s2, sec(s1).plus(sec(s2)).inSeconds());
    assertEquals(2 * s1 + s2, sec(2 * s1).plus(sec(s2)).inSeconds());
  }
}