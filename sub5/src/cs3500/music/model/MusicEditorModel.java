package cs3500.music.model;

import java.util.ArrayList;

/**
 * This class is an implementation of the GenericMusicEditor interface and
 * represents a model of a music editor. The model maintains a Song s that
 * contains notes ordered by firstly the beat they occur in and secondly their
 * position on the chromatic scale. Every constructor and function maintains
 * this invariant in order for its functions to operate correctly.
 */
public class MusicEditorModel implements GenericMusicEditor {

  private Song s;

  public MusicEditorModel() {
    s = new Song();
  }

  public MusicEditorModel(Song s) {
    this.s = new Song();
    for (int i = 0; i < s.length(); i++) {
      this.addNoteHelper(s.getFirst().getPitch(), s.getFirst().getOctave(), s
              .getFirst().getBeat(), 1, s.getFirst().held());
    }
  }

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
  public void addNote(Note.pitch p, int octave, int beat, int duration) {
    if (beat < 1 || duration < 1) {
      throw new IllegalArgumentException("Bad input");
    }
    addNoteHelper(p, octave, beat, duration, false);
  }

  /**
   * Assists addNote by keeping track of the notes duration and whether it is
   * continued so that it can add the additional quartered notes to the
   * subsequent beats for the note's entire duration recursively.
   *
   * @param p         the pitch of the note
   * @param octave    the octave of the note
   * @param beat      the beat of the note
   * @param duration  the length of the note being added
   * @param continued whether the note has just been struck or is held
   */
  private void addNoteHelper(Note.pitch p, int octave, int beat, int
          duration, boolean continued) {
    Note n = new QuarteredNote(p, octave, beat, continued);
    int index = 0;
    if (!s.isQuiet()) {
      while (beat > this.s.getNote(index).getBeat() ||
              (beat == this.s.getNote(index).getBeat() && octave > this.s
                      .getNote(index).getOctave()) ||
              (p.getOrder() > this.s.getNote(index).getPitch().getOrder() &&
                      beat == this.s.getNote(index).getBeat() && octave ==
                      this.s.getNote(index).getOctave())) {
        index++;
        if (index == s.length()) {
          break;
        }
      }
    }
    if (s.isQuiet() || index == s.length()) {
      s.addNote(index, n);
    } else if (s.getNote(index).equals(n) && s.getNote(index).getBeat() == n
            .getBeat()) {
      if (!continued) {
        s.setNote(index, n);
      }
    } else {
      s.addNote(index, n);
    }
    if (duration > 1) {
      addNoteHelper(p, octave, beat + 1, duration - 1, true);
    }
  }

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
  public int removeNote(Note.pitch p, int octave, int beat) {
    if (beat < 1) {
      throw new IllegalArgumentException("Bad beat input");
    }
    Note n = new QuarteredNote(p, octave, beat, true);
    int beatsRemoved = 0;
    boolean first = true;
    int lastBeat = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.getNote(i).equals(n)) {
        if (first) {
          if (s.getNote(i).getBeat() == n.getBeat()) {
            lastBeat = s.getNote(i).getBeat();
            s.removeNote(i);
            first = false;
            i--;
            beatsRemoved++;
          }
        } else {
          if (s.getNote(i).getBeat() == (lastBeat + 1) && s.getNote(i).held()) {
            lastBeat = s.getNote(i).getBeat();
            s.removeNote(i);
            i--;
            beatsRemoved++;
          }
        }
      }
    }
    if (first) {
      throw new IllegalArgumentException("Note could not be found");
    }
    return beatsRemoved;
  }

  /**
   * Moves a note. The note can be moved to a new octave, pitch and/or beat, but
   * the duration will remain the same. Will throw an exception if the note
   * cannot be found or is given an invalid beat.
   *
   * @param p1      the pitch of the note being moved
   * @param octave1 the octave of the note being moved
   * @param beat1   the beat of the note being moved
   * @param p2      the pitch of the note's new location
   * @param octave2 the octave of the note's new location
   * @param beat2   the beat of the note's new location
   */
  public void moveNote(Note.pitch p1, int octave1, int beat1, Note.pitch p2,
                       int octave2, int beat2) {
    int duration = this.removeNote(p1, octave1, beat1);
    this.addNote(p2, octave2, beat2, duration);
  }

  /**
   * displays the song in a view. The view will contain only the pitches and
   * beats that are used in the song, and represent the notes by an 'X' for a
   * newly struck note and an '|' for a sustained note.
   *
   * @return a string representing the editor view
   */
  public String getEditorView() {
    if (s.length() == 0) {
      return "Nothing here yet, let's make some music!";
    } else {
      String output = "     ";
      int beat = s.getFirst().getBeat();
      ArrayList<String> pitches = s.getPitches();
      for (String st : pitches) {
        int i = st.length();
        output += st;
        while (i < 5) {
          output += " ";
          i++;
        }
      }
      int padding = ((Integer) s.getLast().getBeat()).toString().length() + 1;
      int index = 0;
      while (beat <= s.getLast().getBeat()) {
        output += "\n" + String.format("%" + padding + "d", beat);
        for (String st : pitches) {
          if (!(index == s.length()) && s.getNote(index).toString().equals
                  (st) && s.getNote(index).getBeat() == beat) {
            if (s.getNote(index).held()) {
              output += "   | ";
            } else {
              output += "   X ";
            }
            index++;
          } else {
            output += "     ";
          }
        }
        beat++;
      }
      return output;
    }
  }

  public String toString() {
    return this.getEditorView();
  }
}
