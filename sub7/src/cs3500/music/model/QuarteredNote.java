package cs3500.music.model;

import java.util.Objects;

/**
 * This is a class for a quartered note that implements Note. A quartered note
 * always has a pitch value on the chromatic scale and is defined as being equal
 * to other notes if they share the same pitch and octave. The octave is
 * restricted to be between 1 and 10, and the toString method is overridden to
 * correctly output the note's pitch and octave.
 */
public class QuarteredNote implements Note {
  private pitch p;
  private int octave;
  private int beat;
  private boolean continued;
  private int instrument;
  private int volume;

  public QuarteredNote(pitch pitch, int octave, int beat, boolean continued,
                       int instrument, int volume) {
    if (octave > 10 || octave < 0 || beat < 0) {
      throw new IllegalArgumentException("Invalid octave or beat");
    }
    this.p = pitch;
    this.octave = octave;
    this.beat = beat;
    this.continued = continued;
    this.instrument = instrument;
    this.volume = volume;
  }

  public String toString() {
    return this.p.toString() + this.octave;
  }

  /**
   * Returns the pitch of this note.
   *
   * @return pitch - the pitch of this note
   */
  public pitch getPitch() {
    return this.p;
  }

  /**
   * Returns the octave of this note.
   *
   * @return int representing the octave of this note
   */
  public int getOctave() {
    return this.octave;
  }

  /**
   * Returns the beat of this note in the song (ie: when the note occurs)
   *
   * @return int representing the beat of this note
   */
  public int getBeat() {
    return this.beat;
  }

  /**
   * Returns an integer representing where the note lies on the musical scale.
   * The hundredths place represents the octave (with 0 being the 10th octave)
   * and the last two digits represent where the note lies on the musical scale
   *
   * @return int representing the order of this note
   */
  public int getOrder() {
    return (this.octave * 100) + this.p.getOrder();
  }

  /**
   * Returns a boolean representing whether this note is just struck or whether
   * it is being held
   *
   * @return boolean representing whether the note is held
   */
  public boolean held() {
    return continued;
  }

  /**
   * Returns an integer representing the midi instrument of this note
   *
   * @return int representing the instrument of this note
   */
  public int getInstrument() {
    return this.instrument;
  }

  /**
   * Returns an integer representing the volume of this note
   *
   * @return int representing the volume of this note
   */
  public int getVolume() {
    return this.volume;
  }

  /**
   * Returns an integer representing the midi value for the pitch and octave of
   * this note
   *
   * @return int representing the pitch and octave of this note
   */
  public int getMidi() {
    return (this.octave * 12) + this.p.getOrder() - 1;
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    if (!(that instanceof Note)) {
      return false;
    }
    return ((Note) that).getPitch().getOrder() == this.getPitch().getOrder()
            && ((Note) that).getOctave() == this.getOctave();
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getPitch().getOrder(), this.getOctave());
  }
}