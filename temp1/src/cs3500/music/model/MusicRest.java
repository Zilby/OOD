package cs3500.music.model;

/**
 * Represents a rest or lack of note
 */
public class MusicRest implements IMusicNote {
  public MusicRest() {

  }

  public String getRepresentation() {
    return "     ";
  }
}
