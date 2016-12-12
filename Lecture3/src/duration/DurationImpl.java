package duration;

/**
 * Durations represented as hours, minutes, and seconds.
 */
public final class DurationImpl extends AbstractDuration {
  private final int hours;
  private final int minutes;
  private final int seconds;

  /**
   * Constructs a duration in terms of its length in hours, minutes, and seconds.
   *
   * @param hours   the number of hours
   * @param minutes the number of minutes
   * @param seconds the number of seconds
   * @throws IllegalArgumentException if any argument is negative
   */
  public DurationImpl(int hours, int minutes, int seconds) {
    if (hours < 0 || minutes < 0 || seconds < 0) {
      throw new IllegalArgumentException("must be non-negative");
    }

    if (seconds > 59) {
      minutes += seconds / 60;
      seconds %= 60;
    }

    if (minutes > 59) {
      hours += minutes / 60;
      minutes %= 60;
    }

    this.hours = hours;
    this.minutes = minutes;
    this.seconds = seconds;
  }

  /**
   * Constructs a duration in terms of its length in seconds.
   *
   * @param inSeconds the number of seconds (non-negative)
   * @throws IllegalArgumentException {@code inSeconds} is negative
   */
  public DurationImpl(long inSeconds) {
    if (inSeconds < 0) {
      throw new IllegalArgumentException("must be non-negative");
    }

    this.seconds = secondsOf(inSeconds);
    this.minutes = minutesOf(inSeconds);
    this.hours = hoursOf(inSeconds);
  }

  @Override
  public long inSeconds() {

    return 3600 * hours + 60 * minutes + seconds;
  }

  @Override
  public String asHms() {
    return asHms(hours, minutes, seconds);
  }

  @Override
  public Duration plus(Duration that) {
    return fromSeconds(this.inSeconds() + that.inSeconds());
  }

  protected Duration fromSeconds(long inSeconds) {
    return new DurationImpl(inSeconds);
  }
}
