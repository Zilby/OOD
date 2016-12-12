package cs3500.music.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class MusicEditorTest {

  /**
   * Tests if Note.pitch correctly records order
   */
  @Test
  public void pitchOrderTest1() {
    assertEquals(Note.pitch.csharp.getOrder(), 2);
  }

  /**
   * Tests if Note.pitch correctly compares orders
   */
  @Test
  public void pitchOrderTest2() {
    assertTrue(Note.pitch.f.isValue(6));
  }

  /**
   * Tests if QuarteredNote's constructor will throw an exception if given an
   * illegal octave.
   */
  @Test(expected = IllegalArgumentException.class)
  public void noteConstructorTest1() {
    Note n = new QuarteredNote(Note.pitch.csharp, 11, 50, true);
  }

  /**
   * Tests if QuarteredNote's constructor will throw an exception if given an
   * illegal beat.
   */
  @Test(expected = IllegalArgumentException.class)
  public void noteConstructorTest2() {
    Note n = new QuarteredNote(Note.pitch.csharp, 6, -5, true);
  }

  /**
   * Tests if QuarteredNote's toString works correctly
   */
  @Test
  public void noteStringTest() {
    Note n = new QuarteredNote(Note.pitch.csharp, 6, 50, true);
    assertEquals(n.toString(), "C#6");
  }

  /**
   * Tests if QuarteredNote's getOrder works correctly
   */
  @Test
  public void noteOrderTest() {
    Note n = new QuarteredNote(Note.pitch.g, 3, 10, false);
    assertEquals(n.getOrder(), 308);
  }

  /**
   * Tests if QuarteredNote's equals method correctly compares same notes
   */
  @Test
  public void noteEqualsTest1() {
    Note n1 = new QuarteredNote(Note.pitch.e, 6, 20, false);
    Note n2 = new QuarteredNote(Note.pitch.e, 6, 15, true);
    assertTrue(n1.equals(n2));
  }

  /**
   * Tests if QuarteredNote's equals method correctly compares different notes
   */
  @Test
  public void noteEqualsTest2() {
    Note n1 = new QuarteredNote(Note.pitch.e, 6, 20, false);
    Note n2 = new QuarteredNote(Note.pitch.d, 6, 20, false);
    assertTrue(!n1.equals(n2));
  }

  /**
   * Tests if isQuiet correctly identifies if the song is empty (true)
   */
  @Test
  public void songQuietTest1() {
    Song s = new Song();
    assertTrue(s.isQuiet());
  }

  /**
   * Tests if isQuiet correctly identifies if the song is empty (false)
   */
  @Test
  public void songQuietTest2() {
    Song s = new Song();
    s.addNote(0, new QuarteredNote(Note.pitch.e, 6, 20, false));
    assertTrue(!s.isQuiet());
  }

  /**
   * Tests if addNote will throw an exception if given a bad index
   */
  @Test(expected = IllegalArgumentException.class)
  public void songExceptionTest1() {
    Song s = new Song();
    s.addNote(1, new QuarteredNote(Note.pitch.e, 6, 20, false));
  }

  /**
   * Tests if getNote will throw an exception if given a bad index
   */
  @Test(expected = IllegalArgumentException.class)
  public void songExceptionTest2() {
    Song s = new Song();
    s.getNote(-1);
  }

  /**
   * Tests if setNote will throw an exception if given a bad index
   */
  @Test(expected = IllegalArgumentException.class)
  public void songExceptionTest3() {
    Song s = new Song();
    s.setNote(3, new QuarteredNote(Note.pitch.e, 6, 20, false));
  }

  /**
   * Tests if removeNote will throw an exception if given a bad index
   */
  @Test(expected = IllegalArgumentException.class)
  public void songExceptionTest4() {
    Song s = new Song();
    s.removeNote(-9);
  }

  /**
   * Tests if getPitches will throw an exception if there are no pitches
   */
  @Test(expected = IllegalArgumentException.class)
  public void songExceptionTest5() {
    Song s = new Song();
    s.getPitches();
  }

  /**
   * Tests if addNote and getNote work correctly
   */
  @Test
  public void songAddGetTest() {
    Song s = new Song();
    s.addNote(0, new QuarteredNote(Note.pitch.e, 6, 20, false));
    s.addNote(1, new QuarteredNote(Note.pitch.dsharp, 6, 20, false));
    s.addNote(0, new QuarteredNote(Note.pitch.f, 6, 20, false));
    assertEquals(s.getNote(1), new QuarteredNote(Note.pitch.e, 6, 20, false));
  }

  /**
   * Tests if getFirst works correctly
   */
  @Test
  public void songGetFirstTest() {
    Song s = new Song();
    s.addNote(0, new QuarteredNote(Note.pitch.e, 6, 20, false));
    s.addNote(1, new QuarteredNote(Note.pitch.dsharp, 6, 20, false));
    s.addNote(0, new QuarteredNote(Note.pitch.f, 6, 20, false));
    assertEquals(s.getFirst(), new QuarteredNote(Note.pitch.f, 6, 20, false));
  }

  /**
   * Tests if getLast works correctly
   */
  @Test
  public void songGetLastTest() {
    Song s = new Song();
    s.addNote(0, new QuarteredNote(Note.pitch.e, 6, 20, false));
    s.addNote(1, new QuarteredNote(Note.pitch.dsharp, 6, 20, false));
    s.addNote(0, new QuarteredNote(Note.pitch.f, 6, 20, false));
    assertEquals(s.getLast(), new QuarteredNote(Note.pitch.dsharp, 6, 20,
            false));
  }

  /**
   * Tests if removeNote and getNote work correctly
   */
  @Test
  public void songRemoveGetTest() {
    Song s = new Song();
    s.addNote(0, new QuarteredNote(Note.pitch.e, 6, 20, false));
    s.addNote(1, new QuarteredNote(Note.pitch.dsharp, 6, 20, false));
    s.addNote(0, new QuarteredNote(Note.pitch.f, 6, 20, false));
    s.removeNote(1);
    assertEquals(s.getNote(1), new QuarteredNote(Note.pitch.dsharp, 6, 20,
            false));
  }

  /**
   * Tests if setNote and getNote work correctly
   */
  @Test
  public void songSetGetTest() {
    Song s = new Song();
    s.addNote(0, new QuarteredNote(Note.pitch.e, 6, 20, false));
    s.addNote(1, new QuarteredNote(Note.pitch.dsharp, 6, 20, false));
    s.addNote(0, new QuarteredNote(Note.pitch.f, 6, 20, false));
    s.setNote(1, new QuarteredNote(Note.pitch.g, 6, 20, false));
    assertEquals(s.getNote(1), new QuarteredNote(Note.pitch.g, 6, 20, false));
  }

  /**
   * Tests if length works correctly
   */
  @Test
  public void songLengthTest() {
    Song s = new Song();
    s.addNote(0, new QuarteredNote(Note.pitch.e, 6, 20, false));
    s.addNote(1, new QuarteredNote(Note.pitch.dsharp, 6, 20, false));
    s.addNote(0, new QuarteredNote(Note.pitch.f, 6, 20, false));
    assertEquals(s.length(), 3);
  }

  /**
   * Tests if getPitches works correctly
   */
  @Test
  public void songGetPitchesTest() {
    Song s = new Song();
    s.addNote(0, new QuarteredNote(Note.pitch.e, 6, 20, false));
    s.addNote(1, new QuarteredNote(Note.pitch.b, 3, 50, true));
    s.addNote(2, new QuarteredNote(Note.pitch.fsharp, 10, 1, false));
    String test = "";
    for (String st : s.getPitches()) {
      test += st;
    }
    assertEquals(test, "B3C4C#4D4D#4E4F4F#4G4G#4A4A#4B4C5C#5D5D#5E5F5F#5G5G"
            + "#5A5A#5B5C6C#6D6D#6E6F6F#6G6G#6A6A#6B6C7C#7D7D#7E7F7F#7G7G" +
            "#7A7A#7B7C8C#8D8D#8E8F8F#8G8G#8A8A#8B8C9C#9D9D#9E9F9F#9G9G#9A9A" +
            "#9B9C10C#10D10D#10E10F10F#10");
  }

  /**
   * Tests if addNote will throw an exception if given an invalid beat
   */
  @Test(expected = IllegalArgumentException.class)
  public void editorExceptionTest1() {
    MusicEditorModel m = new MusicEditorModel();
    m.addNote(Note.pitch.csharp, 5, 0, 2);
  }

  /**
   * Tests if addNote will throw an exception if given an invalid duration
   */
  @Test(expected = IllegalArgumentException.class)
  public void editorExceptionTest2() {
    MusicEditorModel m = new MusicEditorModel();
    m.addNote(Note.pitch.csharp, 5, 5, -1);
  }

  /**
   * Tests if removeNote will throw an exception if given an invalid beat
   */
  @Test(expected = IllegalArgumentException.class)
  public void editorExceptionTest3() {
    MusicEditorModel m = new MusicEditorModel();
    m.removeNote(Note.pitch.csharp, 5, 0);
  }

  /**
   * Tests if removeNote will throw an exception if given a note that doesn't
   * exist
   */
  @Test(expected = IllegalArgumentException.class)
  public void editorExceptionTest4() {
    MusicEditorModel m = new MusicEditorModel();
    m.addNote(Note.pitch.csharp, 5, 5, 1);
    m.removeNote(Note.pitch.csharp, 5, 6);
  }

  /**
   * Tests if getEditorView will display its special message if nothing has
   * been added yet
   */
  @Test
  public void editorViewTest() {
    MusicEditorModel m = new MusicEditorModel();
    assertEquals(m.getEditorView(), "Nothing here yet, let's make some music!");
  }

  /**
   * Tests if addNote will correctly add notes in the correct order so that
   * getEditorView correctly displays them (also checks that getEditorView is
   * working properly to display beats and pitches)
   */
  @Test
  public void editorAddTest1() {
    MusicEditorModel m = new MusicEditorModel();
    m.addNote(Note.pitch.csharp, 5, 13, 2);
    m.addNote(Note.pitch.dsharp, 4, 5, 1);
    m.addNote(Note.pitch.f, 4, 20, 3);
    m.addNote(Note.pitch.a, 5, 3, 5);
    assertEquals(m.getEditorView(), "     D#4  E4   F4   F#4  G4   G#4  A4   " +
            "A#4  B4   C5   C#5  D5   D#5  E5   F5   F#5  G5   G#5  A5   \n" +
            "  3                                                             " +
            "                                X \n" +
            "  4                                                             " +
            "                                | \n" +
            "  5   X                                                         " +
            "                                | \n" +
            "  6                                                             " +
            "                                | \n" +
            "  7                                                             " +
            "                                | \n" +
            "  8                                                             " +
            "                                  \n" +
            "  9                                                             " +
            "                                  \n" +
            " 10                                                             " +
            "                                  \n" +
            " 11                                                             " +
            "                                  \n" +
            " 12                                                             " +
            "                                  \n" +
            " 13                                                     X       " +
            "                                  \n" +
            " 14                                                     |       " +
            "                                  \n" +
            " 15                                                             " +
            "                                  \n" +
            " 16                                                             " +
            "                                  \n" +
            " 17                                                             " +
            "                                  \n" +
            " 18                                                             " +
            "                                  \n" +
            " 19                                                             " +
            "                                  \n" +
            " 20             X                                               " +
            "                                  \n" +
            " 21             |                                               " +
            "                                  \n" +
            " 22             |                                               " +
            "                                  ");
  }

  /**
   * Tests if addNote will correctly add notes that overlap and/or occur on
   * the same note/beat
   */
  @Test
  public void editorAddTest2() {
    MusicEditorModel m = new MusicEditorModel();
    m.addNote(Note.pitch.csharp, 5, 13, 2);
    m.addNote(Note.pitch.dsharp, 4, 5, 1);
    m.addNote(Note.pitch.f, 4, 20, 3);
    m.addNote(Note.pitch.a, 5, 3, 5);
    m.addNote(Note.pitch.csharp, 5, 5, 2);
    m.addNote(Note.pitch.dsharp, 4, 7, 1);
    m.addNote(Note.pitch.f, 4, 22, 3);
    m.addNote(Note.pitch.a, 5, 7, 5);
    assertEquals(m.getEditorView(), "     D#4  E4   F4   F#4  G4   G#4  A4   " +
            "A#4  B4   C5   C#5  D5   D#5  E5   F5   F#5  G5   G#5  A5   \n" +
            "  3                                                             " +
            "                                X \n" +
            "  4                                                             " +
            "                                | \n" +
            "  5   X                                                 X       " +
            "                                | \n" +
            "  6                                                     |       " +
            "                                | \n" +
            "  7   X                                                         " +
            "                                X \n" +
            "  8                                                             " +
            "                                | \n" +
            "  9                                                             " +
            "                                | \n" +
            " 10                                                             " +
            "                                | \n" +
            " 11                                                             " +
            "                                | \n" +
            " 12                                                             " +
            "                                  \n" +
            " 13                                                     X       " +
            "                                  \n" +
            " 14                                                     |       " +
            "                                  \n" +
            " 15                                                             " +
            "                                  \n" +
            " 16                                                             " +
            "                                  \n" +
            " 17                                                             " +
            "                                  \n" +
            " 18                                                             " +
            "                                  \n" +
            " 19                                                             " +
            "                                  \n" +
            " 20             X                                               " +
            "                                  \n" +
            " 21             |                                               " +
            "                                  \n" +
            " 22             X                                               " +
            "                                  \n" +
            " 23             |                                               " +
            "                                  \n" +
            " 24             |                                               " +
            "                                  ");
  }

  /**
   * Tests if addNote correctly adds and displays a large collection of notes
   */
  @Test
  public void editorAddTest3() {
    MusicEditorModel m = new MusicEditorModel();
    m.addNote(Note.pitch.csharp, 1, 13, 6);
    m.addNote(Note.pitch.g, 6, 5, 4);
    m.addNote(Note.pitch.f, 10, 20, 9);
    m.addNote(Note.pitch.a, 4, 3, 3);
    m.addNote(Note.pitch.b, 9, 5, 1);
    m.addNote(Note.pitch.gsharp, 2, 45, 7);
    m.addNote(Note.pitch.f, 3, 22, 15);
    m.addNote(Note.pitch.e, 7, 34, 2);
    m.addNote(Note.pitch.fsharp, 5, 13, 15);
    m.addNote(Note.pitch.a, 7, 54, 1);
    m.addNote(Note.pitch.f, 1, 23, 7);
    m.addNote(Note.pitch.a, 9, 8, 8);
    m.addNote(Note.pitch.fsharp, 10, 7, 4);
    m.addNote(Note.pitch.dsharp, 4, 15, 20);
    m.addNote(Note.pitch.e, 3, 30, 4);
    m.addNote(Note.pitch.a, 5, 33, 9);
    m.addNote(Note.pitch.csharp, 5, 34, 5);
    m.addNote(Note.pitch.g, 3, 21, 3);
    m.addNote(Note.pitch.f, 2, 28, 6);
    m.addNote(Note.pitch.a, 5, 6, 1);
    m.addNote(Note.pitch.gsharp, 5, 2, 3);
    m.addNote(Note.pitch.dsharp, 2, 3, 6);
    m.addNote(Note.pitch.f, 4, 22, 12);
    m.addNote(Note.pitch.a, 8, 7, 5);
    assertEquals(m.getEditorView(), "     C#1  D1   D#1  E1   F1   F#1  G1   " +
            "G#1  A1   A#1  B1   C2   C#2  D2   D#2  E2   F2   F#2  G2   G#2 " +
            " A2   A#2  B2   C3   C#3  D3   D#3  E3   F3   F#3  G3   G#3  A3 " +
            "  A#3  B3   C4   C#4  D4   D#4  E4   F4   F#4  G4   G#4  A4   " +
            "A#4  B4   C5   C#5  D5   D#5  E5   F5   F#5  G5   G#5  A5   A#5 " +
            " B5   C6   C#6  D6   D#6  E6   F6   F#6  G6   G#6  A6   A#6  B6 " +
            "  C7   C#7  D7   D#7  E7   F7   F#7  G7   G#7  A7   A#7  B7   C8" +
            "   C#8  D8   D#8  E8   F8   F#8  G8   G#8  A8   A#8  B8   C9   " +
            "C#9  D9   D#9  E9   F9   F#9  G9   G#9  A9   A#9  B9   C10  C#10" +
            " D10  D#10 E10  F10  F#10 \n" +
            "  2                                                             " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                         X                                      " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            "  3                                                             " +
            "            X                                                   " +
            "                                                                " +
            "                                  X                             " +
            "                         |                                      " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            "  4                                                             " +
            "            |                                                   " +
            "                                                                " +
            "                                  |                             " +
            "                         |                                      " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            "  5                                                             " +
            "            |                                                   " +
            "                                                                " +
            "                                  |                             " +
            "                                                                " +
            "                X                                               " +
            "                                                                " +
            "                                                                " +
            "                        X                                    \n" +
            "  6                                                             " +
            "            |                                                   " +
            "                                                                " +
            "                                                                " +
            "                              X                                 " +
            "                |                                               " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            "  7                                                             " +
            "            |                                                   " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                |                                               " +
            "                                                                " +
            "                  X                                             " +
            "                                                           X \n" +
            "  8                                                             " +
            "            |                                                   " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                |                                               " +
            "                                                                " +
            "                  |                                             " +
            "              X                                            | \n" +
            "  9                                                             " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                  |                                             " +
            "              |                                            | \n" +
            " 10                                                             " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                  |                                             " +
            "              |                                            | \n" +
            " 11                                                             " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                  |                                             " +
            "              |                                              \n" +
            " 12                                                             " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "              |                                              \n" +
            " 13   X                                                         " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "               X                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "              |                                              \n" +
            " 14   |                                                         " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "               |                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "              |                                              \n" +
            " 15   |                                                         " +
            "                                                                " +
            "                                                                " +
            "    X                                                           " +
            "               |                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "              |                                              \n" +
            " 16   |                                                         " +
            "                                                                " +
            "                                                                " +
            "    |                                                           " +
            "               |                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            " 17   |                                                         " +
            "                                                                " +
            "                                                                " +
            "    |                                                           " +
            "               |                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            " 18   |                                                         " +
            "                                                                " +
            "                                                                " +
            "    |                                                           " +
            "               |                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            " 19                                                             " +
            "                                                                " +
            "                                                                " +
            "    |                                                           " +
            "               |                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            " 20                                                             " +
            "                                                                " +
            "                                                                " +
            "    |                                                           " +
            "               |                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                      X      \n" +
            " 21                                                             " +
            "                                                                " +
            "                            X                                   " +
            "    |                                                           " +
            "               |                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                      |      \n" +
            " 22                                                             " +
            "                                                                " +
            "                  X         |                                   " +
            "    |         X                                                 " +
            "               |                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                      |      \n" +
            " 23                       X                                     " +
            "                                                                " +
            "                  |         |                                   " +
            "    |         |                                                 " +
            "               |                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                      |      \n" +
            " 24                       |                                     " +
            "                                                                " +
            "                  |                                             " +
            "    |         |                                                 " +
            "               |                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                      |      \n" +
            " 25                       |                                     " +
            "                                                                " +
            "                  |                                             " +
            "    |         |                                                 " +
            "               |                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                      |      \n" +
            " 26                       |                                     " +
            "                                                                " +
            "                  |                                             " +
            "    |         |                                                 " +
            "               |                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                      |      \n" +
            " 27                       |                                     " +
            "                                                                " +
            "                  |                                             " +
            "    |         |                                                 " +
            "               |                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                      |      \n" +
            " 28                       |                                     " +
            "                      X                                         " +
            "                  |                                             " +
            "    |         |                                                 " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                      |      \n" +
            " 29                       |                                     " +
            "                      |                                         " +
            "                  |                                             " +
            "    |         |                                                 " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            " 30                                                             " +
            "                      |                                         " +
            "             X    |                                             " +
            "    |         |                                                 " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            " 31                                                             " +
            "                      |                                         " +
            "             |    |                                             " +
            "    |         |                                                 " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            " 32                                                             " +
            "                      |                                         " +
            "             |    |                                             " +
            "    |         |                                                 " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            " 33                                                             " +
            "                      |                                         " +
            "             |    |                                             " +
            "    |         |                                                 " +
            "                              X                                 " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            " 34                                                             " +
            "                                                                " +
            "                  |                                             " +
            "    |                                                 X         " +
            "                              |                                 " +
            "                                                             X  " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            " 35                                                             " +
            "                                                                " +
            "                  |                                             " +
            "                                                      |         " +
            "                              |                                 " +
            "                                                             |  " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            " 36                                                             " +
            "                                                                " +
            "                  |                                             " +
            "                                                      |         " +
            "                              |                                 " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            " 37                                                             " +
            "                                                                " +
            "                                                                " +
            "                                                      |         " +
            "                              |                                 " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            " 38                                                             " +
            "                                                                " +
            "                                                                " +
            "                                                      |         " +
            "                              |                                 " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            " 39                                                             " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                              |                                 " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            " 40                                                             " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                              |                                 " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            " 41                                                             " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                              |                                 " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            " 42                                                             " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            " 43                                                             " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            " 44                                                             " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            " 45                                                             " +
            "                                     X                          " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            " 46                                                             " +
            "                                     |                          " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            " 47                                                             " +
            "                                     |                          " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            " 48                                                             " +
            "                                     |                          " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            " 49                                                             " +
            "                                     |                          " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            " 50                                                             " +
            "                                     |                          " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            " 51                                                             " +
            "                                     |                          " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            " 52                                                             " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            " 53                                                             " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                             \n" +
            " 54                                                             " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                      X                                         " +
            "                                                                " +
            "                                                             ");
  }

  /**
   * Tests if removeNote works correctly and will remove overlapping notes
   * and notes in the middle of their duration
   */
  @Test
  public void editorRemoveTest() {
    MusicEditorModel m = new MusicEditorModel();
    m.addNote(Note.pitch.csharp, 5, 13, 2);
    m.addNote(Note.pitch.dsharp, 4, 5, 1);
    m.addNote(Note.pitch.f, 4, 20, 3);
    m.addNote(Note.pitch.a, 5, 3, 5);
    m.addNote(Note.pitch.csharp, 5, 5, 2);
    m.addNote(Note.pitch.dsharp, 4, 7, 1);
    m.addNote(Note.pitch.f, 4, 22, 3);
    m.addNote(Note.pitch.a, 5, 7, 5);
    m.removeNote(Note.pitch.dsharp, 4, 5);
    m.removeNote(Note.pitch.f, 4, 21);
    assertEquals(m.getEditorView(), "     D#4  E4   F4   F#4  G4   G#4  A4   " +
            "A#4  B4   C5   C#5  D5   D#5  E5   F5   F#5  G5   G#5  A5   \n" +
            "  3                                                             " +
            "                                X \n" +
            "  4                                                             " +
            "                                | \n" +
            "  5                                                     X       " +
            "                                | \n" +
            "  6                                                     |       " +
            "                                | \n" +
            "  7   X                                                         " +
            "                                X \n" +
            "  8                                                             " +
            "                                | \n" +
            "  9                                                             " +
            "                                | \n" +
            " 10                                                             " +
            "                                | \n" +
            " 11                                                             " +
            "                                | \n" +
            " 12                                                             " +
            "                                  \n" +
            " 13                                                     X       " +
            "                                  \n" +
            " 14                                                     |       " +
            "                                  \n" +
            " 15                                                             " +
            "                                  \n" +
            " 16                                                             " +
            "                                  \n" +
            " 17                                                             " +
            "                                  \n" +
            " 18                                                             " +
            "                                  \n" +
            " 19                                                             " +
            "                                  \n" +
            " 20             X                                               " +
            "                                  \n" +
            " 21                                                             " +
            "                                  \n" +
            " 22             X                                               " +
            "                                  \n" +
            " 23             |                                               " +
            "                                  \n" +
            " 24             |                                               " +
            "                                  ");
  }

  /**
   * Tests if moveNote works correctly, removing the note and correctly
   * placing it at its new location with the same duration.
   */
  @Test
  public void editorMoveTest() {
    MusicEditorModel m = new MusicEditorModel();
    m.addNote(Note.pitch.csharp, 5, 13, 2);
    m.addNote(Note.pitch.dsharp, 4, 5, 1);
    m.addNote(Note.pitch.f, 4, 20, 3);
    m.addNote(Note.pitch.a, 5, 3, 5);
    m.addNote(Note.pitch.csharp, 5, 5, 2);
    m.addNote(Note.pitch.dsharp, 4, 7, 1);
    m.addNote(Note.pitch.f, 4, 22, 3);
    m.addNote(Note.pitch.a, 5, 7, 5);
    m.moveNote(Note.pitch.a, 5, 3, Note.pitch.a, 9, 8);
    m.moveNote(Note.pitch.f, 4, 20, Note.pitch.e, 3, 9);
    assertEquals(m.getEditorView(), "     E3   F3   F#3  G3   G#3  A3   A#3  " +
            "B3   C4   C#4  D4   D#4  E4   F4   F#4  G4   G#4  A4   A#4  B4  " +
            " C5   C#5  D5   D#5  E5   F5   F#5  G5   G#5  A5   A#5  B5   C6 " +
            "  C#6  D6   D#6  E6   F6   F#6  G6   G#6  A6   A#6  B6   C7   " +
            "C#7  D7   D#7  E7   F7   F#7  G7   G#7  A7   A#7  B7   C8   C#8 " +
            " D8   D#8  E8   F8   F#8  G8   G#8  A8   A#8  B8   C9   C#9  D9 " +
            "  D#9  E9   F9   F#9  G9   G#9  A9   \n" +
            "  5                                                          X  " +
            "                                               X                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "         \n" +
            "  6                                                             " +
            "                                               |                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "         \n" +
            "  7                                                          X  " +
            "                                                                " +
            "                       X                                        " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "         \n" +
            "  8                                                             " +
            "                                                                " +
            "                       |                                        " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "       X \n" +
            "  9   X                                                         " +
            "                                                                " +
            "                       |                                        " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "       | \n" +
            " 10   |                                                         " +
            "                                                                " +
            "                       |                                        " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "       | \n" +
            " 11                                                             " +
            "                                                                " +
            "                       |                                        " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "       | \n" +
            " 12                                                             " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "         \n" +
            " 13                                                             " +
            "                                               X                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "         \n" +
            " 14                                                             " +
            "                                               |                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "         \n" +
            " 15                                                             " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "         \n" +
            " 16                                                             " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "         \n" +
            " 17                                                             " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "         \n" +
            " 18                                                             " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "         \n" +
            " 19                                                             " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "         \n" +
            " 20                                                             " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "         \n" +
            " 21                                                             " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "         \n" +
            " 22                                                             " +
            "       X                                                        " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "         \n" +
            " 23                                                             " +
            "       |                                                        " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "         \n" +
            " 24                                                             " +
            "       |                                                        " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "                                                                " +
            "         ");
  }
}