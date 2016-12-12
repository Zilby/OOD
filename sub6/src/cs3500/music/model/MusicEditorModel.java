package cs3500.music.model;

import cs3500.music.util.CompositionBuilder;

/**
 * This class is an implementation of the GenericMusicEditor interface and
 * represents a model of a music editor. The model maintains a Song s that
 * contains notes ordered by firstly the beat they occur in and secondly their
 * position on the chromatic scale. Every constructor and function maintains
 * this invariant in order for its functions to operate correctly.
 */
public class MusicEditorModel implements GenericMusicEditor {

  private Song s;
  private int tempo;

  public MusicEditorModel() {
    s = new Song();
    tempo = 4;
  }

  public MusicEditorModel(Song s, int tempo) {
    this.s = new Song();
    Note firstNote = s.getFirst();
    for (int i = 0; i < s.length(); i++) {
      this.addNoteHelper(firstNote.getPitch(), firstNote.getOctave(),
              firstNote.getBeat(), 1, firstNote.held(), firstNote
                      .getInstrument(), firstNote.getVolume());
    }
    this.tempo = tempo;
  }

  /**
   * Adds a note to the song. This note will be added in sequential order
   * relative to its location in the song as well as its location in the
   * chromatic scale.
   *
   * @param p          the pitch of the note
   * @param octave     the octave of the note
   * @param beat       the beat of the note
   * @param duration   the length of the note being added
   * @param instrument the instrument of the note
   * @param volume     the volume of the note
   */
  public void addNote(Note.pitch p, int octave, int beat, int duration, int
          instrument, int volume) {
    if (beat < 0 || duration < 1) {
      throw new IllegalArgumentException("Bad input. Beat: " + beat + " " +
              "Duration: " + duration);
    }
    addNoteHelper(p, octave, beat, duration, false, instrument, volume);
  }

  /**
   * Assists addNote by keeping track of the notes duration and whether it is
   * continued so that it can add the additional quartered notes to the
   * subsequent beats for the note's entire duration recursively.
   *
   * @param p          the pitch of the note
   * @param octave     the octave of the note
   * @param beat       the beat of the note
   * @param duration   the length of the note being added
   * @param continued  whether the note has just been struck or is held
   * @param instrument the instrument of the note
   * @param volume     the volume of the note
   */
  private void addNoteHelper(Note.pitch p, int octave, int beat, int
          duration, boolean continued, int instrument, int volume) {
    Note n = new QuarteredNote(p, octave, beat, continued, instrument, volume);
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
      addNoteHelper(p, octave, beat + 1, duration - 1, true, instrument,
              volume);
    }
  }

  /**
   * Removes a note from the song. This will remove the entire length of the
   * note starting at the give beat until either the note ends or is struck
   * again.
   *
   * @param p          the pitch of the note
   * @param octave     the octave of the note
   * @param beat       the beat of the note
   * @param instrument the instrument of the note
   * @param volume     the volume of the note
   * @return int representing the number of beats removed from the song
   */
  public int removeNote(Note.pitch p, int octave, int beat, int instrument,
                        int volume) {
    if (beat < 0) {
      throw new IllegalArgumentException("Bad beat input");
    }
    Note n = new QuarteredNote(p, octave, beat, true, instrument, volume);
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
   * @param p1         the pitch of the note being moved
   * @param octave1    the octave of the note being moved
   * @param beat1      the beat of the note being moved
   * @param p2         the pitch of the note's new location
   * @param octave2    the octave of the note's new location
   * @param beat2      the beat of the note's new location
   * @param instrument the instrument of the note
   * @param volume     the volume of the note
   */
  public void moveNote(Note.pitch p1, int octave1, int beat1, Note.pitch p2,
                       int octave2, int beat2, int instrument, int volume) {
    int duration = this.removeNote(p1, octave1, beat1, instrument, volume);
    this.addNote(p2, octave2, beat2, duration, instrument, volume);
  }

  /**
   * Returns the Song that has been edited by the editor.
   *
   * @return Song which is a holder for our list of sequential notes
   */
  public Song getSong() {
    return this.s;
  }

  /**
   * Gets the tempo for the song
   *
   * @return int that is the tempo of the composition
   */
  public int getTempo() {
    return this.tempo;
  }

  /**
   * Sets the tempo for the song to the given integer tempo
   *
   * @param tempo the tempo of the composition
   */
  public void setTempo(int tempo) {
    this.tempo = tempo;
  }

  /**
   * This class is nested builder that extends our CompositionBuilder class. It
   * is used to build a GenericMusicEditor using Midi inputs given by our
   * MusicReader class
   */
  public static final class Builder implements
          CompositionBuilder<GenericMusicEditor> {
    GenericMusicEditor genMusicEditor;

    public Builder() {
      this.genMusicEditor = new MusicEditorModel();
    }

    /**
     * Builds and returns our completed music editor
     *
     * @return GenericMusicEditor that is now complete
     */
    public GenericMusicEditor build() {
      return this.genMusicEditor;
    }

    /**
     * Sets the tempo of our music editor
     *
     * @param tempo the tempo we wish to set our song to
     * @return this object
     */
    public CompositionBuilder<GenericMusicEditor> setTempo(int tempo) {
      this.genMusicEditor.setTempo(tempo);
      return this;
    }

    /**
     * Adds a note to our editor and song.
     *
     * @param start      the beginning beat of the note
     * @param end        the end beat of the note
     * @param instrument the instrument of the note
     * @param pitch      the pitch of the note
     * @param volume     the volume of the note
     * @return this object
     */
    public CompositionBuilder<GenericMusicEditor> addNote(int start, int end,
                                                          int instrument, int
                                                                  pitch, int
                                                                  volume) {
      int octave;
      if (pitch < 0 || pitch > 127) {
        throw new IllegalArgumentException("Invalid pitch input: " + pitch);
      } else {
        octave = pitch / 12;
        pitch = pitch % 12;
        if (instrument == 16) {
          instrument = 1;
        }
        if (end - start == 0) {
          this.genMusicEditor.addNote(Note.pitch.values()[pitch], octave,
                  start, 1, instrument, volume);
        } else {
          this.genMusicEditor.addNote(Note.pitch.values()[pitch], octave,
                  start, end - start, instrument, volume);

        }
      }
      return this;
    }

  }
}