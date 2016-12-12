package cs3500.music.model;

/**
 * This is an interface for a generic music editor. Any implementing classes
 * must be able to add, remove and move notes as well as print an editor view.
 */
public interface GenericMusicEditor {

  /**
   * Adds a note to the song. This note will be added in sequential order
   * relative to its location in the song as well as its location in the
   * chromatic scale.
   *
   * @param p        the pitch of the note
   * @param octave   the octave of the note
   * @param beat     the beat of the note
   * @param duration the length of the note being added
   */
  void addNote(Note.pitch p, int octave, int beat, int duration);

  /**
   * Removes a note from the song. This will remove the entire length of the
   * note starting at the give beat until either the note ends or is struck
   * again.
   *
   * @param p      the pitch of the note
   * @param octave the octave of the note
   * @param beat   the beat of the note
   * @return int representing the number of beats removed from the song
   */
  int removeNote(Note.pitch p, int octave, int beat);

  /**
   * Moves a note. The note can be moved to a new octave, pitch and/or beat, but
   * the duration will remain the same.
   *
   * @param p1      the pitch of the note being moved
   * @param octave1 the octave of the note being moved
   * @param beat1   the beat of the note being moved
   * @param p2      the pitch of the note's new location
   * @param octave2 the octave of the note's new location
   * @param beat2   the beat of the note's new location
   */
  void moveNote(Note.pitch p1, int octave1, int beat1, Note.pitch p2, int
          octave2, int beat2);

  /**
   * displays the song in a view. The view will contain only the pitches and
   * beats that are used in the song, and represent the notes by an 'X' for a
   * newly struck note and an '|' for a sustained note.
   *
   * @return a string representing the editor view
   */
  String getEditorView();

}
