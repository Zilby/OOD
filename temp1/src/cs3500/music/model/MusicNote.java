package cs3500.music.model;

/**
 * A MusicNote represents a single note in a piece of music
 * A MusicNote has:
 *  - A Pitch, which is one of:
 *    - C, C#, D, D#, E, F, F#, G, G#, A, B, B#
 *  - An int octave
 *  - An int startBeat
 *  - An int duration
 */
public class MusicNote implements IMusicNote {
  public enum Pitch {
    C("C"), CSHARP("C#"), D("D"), DSHARP("D#"), E("E"), F("F"), FSHARP("F#"), G("G"),
    GSHARP("G#"), A("A"), ASHARP("A#"), B("B");

    private String name;

    Pitch(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return this.name;
    }
  }

  protected Pitch pitch;
  protected int startBeat;
  protected int duration;

  public MusicNote(Pitch pitch, int startBeat, int duration) {
    this.pitch = pitch;
    this.startBeat = startBeat;
    this.duration = duration;
  }

  public MusicNote() {
  }

  protected Pitch getPitch(int index) {
    Pitch goal = null;
    for (Pitch p : Pitch.values()) {
      if (p.ordinal() == index) {
        goal = p;
      }
    }
    return goal;
  }

  public String getRepresentation() {
    return "  X  ";
  }

}
