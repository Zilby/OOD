package cs3500.music.model;
import cs3500.music.model.MusicNote.Pitch;

/**
 * Represents a held note
 */
public class MusicNoteSustain implements IMusicNote {
  public MusicNoteSustain() {

  }

  public String getRepresentation() {
    return "  |  ";
  }

}
