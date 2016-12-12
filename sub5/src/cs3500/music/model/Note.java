package cs3500.music.model;

/**
 * This is an interface for a generic note. It defines the enum pitch to be a
 * string value with sequential order. Every note must be able to return its
 * pitch, octave, beat, sequential order in the music scale and whether it is
 * being held or has just been struck.
 */
public interface Note {
  public enum pitch {
    c("C", 1), csharp("C#", 2), d("D", 3), dsharp("D#", 4), e("E", 5),
    f("F", 6), fsharp("F#", 7), g("G", 8), gsharp("G#", 9), a("A", 10),
    asharp("A#", 11), b("B", 12);
    private String value;
    private int order;

    pitch(String value, int order) {
      this.value = value;
      this.order = order;
    }

    public String toString() {
      return this.value;
    }

    /**
     * Returns whether the given value is the same value for this pitch. The
     * value corresponds to where it is on a single octave scale.
     *
     * @param i a value representing this particular pitch
     * @return boolean representing whether the values match
     */
    public boolean isValue(int i) {
      return this.order == i;
    }

    /**
     * Returns the given value for this pitch. The value corresponds to where it
     * is on a single octave scale.
     *
     * @return int representing where the pitch lies on a single octave scale.
     */
    public int getOrder() {
      return this.order;
    }
  }

  /**
   * Returns the pitch of this note.
   *
   * @return pitch - the pitch of this note
   */
  pitch getPitch();

  /**
   * Returns the octave of this note.
   *
   * @return int representing the octave of this note
   */
  int getOctave();

  /**
   * Returns the beat of this note in the song (ie: when the note occurs)
   *
   * @return int representing the beat of this note
   */
  int getBeat();

  /**
   * Returns an integer representing where the note lies on the musical scale.
   *
   * @return int representing the order of this note
   */
  int getOrder();

  /**
   * Returns a boolean representing whether this note is just struck or whether
   * it is being held
   *
   * @return boolean representing whether the note is held
   */
  boolean held();
}
