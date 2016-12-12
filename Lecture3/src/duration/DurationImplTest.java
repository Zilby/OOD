package duration;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests for {@link DurationImpl}.
 */
public class DurationImplTest {
  static long s1 = 328375982;
  static long s2 = 299699;

  static Duration d1_23_45 = hms(1, 23, 45);
  static Duration d2_03_00 = hms(2, 3, 0);
  static Duration d457_03_00 = hms(457, 3, 0);

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

    assertEquals(sec(s1),
            sec(sec(s1).inSeconds()));
    assertEquals(sec(s2),
            sec(sec(s2).inSeconds()));

    assertEquals(d1_23_45,
            sec(d1_23_45.inSeconds()));
    assertEquals(d2_03_00,
            sec(d2_03_00.inSeconds()));
    assertEquals(d457_03_00,
            sec(d457_03_00.inSeconds()));
  }

  @Test
  public void _1_23_03_plus_2_14_45_is_3_37_48() {
    assertEquals(hms(3, 37, 48),
            hms(1, 23, 3).plus(hms(2, 14, 45)));
  }

  @Test
  public void plusCarries() {
    assertEquals(hms(3, 38, 18),
            hms(1, 23, 33).plus(hms(2, 14, 45)));
    assertEquals(hms(4, 19, 48),
            hms(1, 23, 3).plus(hms(2, 56, 45)));
    assertEquals(hms(4, 20, 18),
            hms(1, 23, 33).plus(hms(2, 56, 45)));
  }

  @Test
  public void addWorks() {
    assertEquals(s1 + s2, sec(s1).plus(sec(s2)).inSeconds());
    assertEquals(2 * s1 + s2, sec(2 * s1).plus(sec(s2)).inSeconds());
  }

  static Duration hms(int hours, int minutes, int seconds) {
    return new DurationImpl(hours, minutes, seconds);
  }

  static Duration sec(long inSeconds) {
    return new DurationImpl(inSeconds);
  }
}