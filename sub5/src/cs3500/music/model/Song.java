package cs3500.music.model;

import java.util.ArrayList;

/**
 * This is a class for a song that is a collection of notes. Basic operations
 * can be done on the song such as adding notes to its array at various indexes.
 * The song can also return a string list of the range of pitches of the notes
 * in the song.
 */
public class Song {
  private ArrayList<Note> notes;

  public Song() {
    notes = new ArrayList<Note>();
  }

  public Song(ArrayList<Note> notes) {
    this.notes = notes;
  }

  /**
   * Returns whether there are any notes in the song yet
   *
   * @return boolean representing whether any notes have been added
   */
  public boolean isQuiet() {
    return notes.isEmpty();
  }

  /**
   * Returns the note at the given index of the notes array. Will throw an
   * exception if the index is out of bounds.
   *
   * @return Note of the given index
   */
  public Note getNote(int index) {
    if (index >= notes.size() || index < 0) {
      throw new IllegalArgumentException("Note index out of bounds");
    }
    return notes.get(index);
  }

  /**
   * Returns the first note in the song. If there are no notes, it will return
   * null.
   *
   * @return first note in the song
   */
  public Note getFirst() {
    if (notes.size() == 0) {
      return null;
    }
    return notes.get(0);
  }

  /**
   * Returns the last note in the song. If there are no notes, it will return
   * null.
   *
   * @return last note in the song
   */
  public Note getLast() {
    if (notes.size() == 0) {
      return null;
    }
    return notes.get(notes.size() - 1);
  }

  /**
   * Adds a note to the song at the given index. If the index given is out of
   * bounds it will throw an exception.
   */
  public void addNote(int index, Note n) {
    if (index > notes.size() || index < 0) {
      throw new IllegalArgumentException("Note index out of bounds: " + index);
    }
    notes.add(index, n);
  }

  /**
   * sets a note in the song to a new value. If the index given is out of bounds
   * it will throw an exception.
   */
  public void setNote(int index, Note n) {
    if (index >= notes.size() || index < 0) {
      throw new IllegalArgumentException("Note index out of bounds: " + index);
    }
    notes.set(index, n);
  }

  /**
   * removes a note from the song at the given index. If the index given is out
   * of bounds it will throw an exception.
   */
  public void removeNote(int index) {
    if (index >= notes.size() || index < 0) {
      throw new IllegalArgumentException("Note index out of bounds: " + index);
    }
    notes.remove(index);
  }

  /**
   * Returns the length of the song.
   *
   * @return int representing the length of the song.
   */
  public int length() {
    return notes.size();
  }

  /**
   * Returns a string list of the range of pitches of the notes in the song. The
   * first pitch will be the lowest pitch while the last will be the highest,
   * the rest of the array will contain every pitch in-between.
   *
   * @return ArrayList<String> representing all the pitches in the song.
   */
  public ArrayList<String> getPitches() {
    if (this.isQuiet()) {
      throw new IllegalArgumentException("No pitches to get");
    }
    Note lowest = this.getFirst();
    Note highest = this.getFirst();
    for (Note n : notes) {
      if (n.getOrder() < lowest.getOrder()) {
        lowest = n;
      }
      if (n.getOrder() > highest.getOrder()) {
        highest = n;
      }
    }
    ArrayList<String> output = new ArrayList<String>();
    int pitch = lowest.getOrder() % 100;
    boolean last = false;
    for (int i = lowest.getOctave(); i <= highest.getOctave(); i++) {
      while (pitch < 13) {
        for (Note.pitch p : Note.pitch.values()) {
          if (p.isValue(pitch)) {
            output.add(p.toString() + i);
            if (pitch == (highest.getOrder() % 100) && i == highest.getOctave
                    ()) {
              last = true;
            }
            break;
          }
        }
        if (last) {
          break;
        }
        pitch++;
      }
      pitch = 0;
    }
    return output;
  }
}
