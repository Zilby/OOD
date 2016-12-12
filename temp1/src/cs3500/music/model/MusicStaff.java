package cs3500.music.model;

import java.util.ArrayList;

/**
 * Represents a series of notes
 */
public class MusicStaff {
  // why 16? (also what is this)
  private final int lengthOfStaff = 16;
  // what does this represent?
  private final int octave;
  private int totalDuration;
  private MusicNote note = new MusicNote();
  private ArrayList<ArrayList<IMusicNote>> staff = new ArrayList<>();

  public MusicStaff(int octave) {
    this.octave = octave;
    this.makeStaff();
  }

  private void makeStaff() {
    staff.clear();
    for (int i = 0; i < lengthOfStaff; i++) {
      staff.add(i, new ArrayList<>());
    }
  }

  private void calculateDuration() {
    ArrayList longestList = staff.get(0);
    for (ArrayList<IMusicNote> aStaff : staff) {
      if (aStaff.size() > longestList.size()) {
        longestList = aStaff;
      }
    }
    this.totalDuration = longestList.size();
  }

  public void addNote(MusicNote note, int octave)  {
    // the first || statement here is always true
    if ((note.pitch.ordinal() > 3 || note.pitch.ordinal() < 12) && this.octave == octave) {
      int noteList = note.pitch.ordinal() - 4;
      staff.get(noteList).add(note.startBeat, note);
      for (int i = note.startBeat + 1; i < note.duration; i++) {
        staff.get(noteList).add(i, new MusicNoteSustain());
      }
    }
    // the first || statement here is also always true
    else if ((note.pitch.ordinal() >= 0 || note.pitch.ordinal() < (lengthOfStaff / 2))
            && this.octave == octave - 1) {
      int noteList = note.pitch.ordinal() + (lengthOfStaff / 2);
      try {
        staff.get(noteList).add(note.startBeat, note);
      }
      catch (IndexOutOfBoundsException e) {
        for (int i = 0; i < note.startBeat; i++) {
          // you add a MusicRest here, but then you use try-catch later on,
          // so what happened to the MusicRest?
          staff.get(noteList).add(i, new MusicRest());
        }
        staff.get(noteList).add(note.startBeat, note);
      }
      for (int i = note.startBeat + 1; i <= note.duration + 1; i++) {
        staff.get(noteList).add(i, new MusicNoteSustain());
      }
    }
    // why does nothing happen if you try to add a note to an octave that's not
    // the same as this.octave or this.octave - 1 ?
    this.calculateDuration();
  }


  private String displayHeader() {
    String printHeader = "";

    for (int i = 4; i < 12; i++) {
      MusicNote.Pitch currentPitch = note.getPitch(i);
      if (octave < 10) {
        if (currentPitch.toString().length() == 2) {
          printHeader += ("  " + currentPitch.toString() + octave);
        }
        else {
          printHeader += ("   " + currentPitch.toString() + octave);
        }
      }
      else {
        if (currentPitch.toString().length() == 2) {
          printHeader += (" " + currentPitch.toString() + octave);
        }
        else {
          printHeader += ("  " + currentPitch.toString() + octave);
        }
      }
    }

    for (int i = 0; i < 8; i++) {
      MusicNote.Pitch currentPitch = note.getPitch(i);
      if (octave < 9) {
        if (currentPitch.toString().length() == 2) {
          printHeader += ("  " + currentPitch.toString() + (octave + 1));
        }
        else {
          printHeader += ("   " + currentPitch.toString() + (octave + 1));
        }
      }
      else {
        if (currentPitch.toString().length() == 2) {
          printHeader += (" " + currentPitch.toString() + (octave + 1));
        }
        else {
          printHeader += ("  " + currentPitch.toString() + (octave + 1));
        }
      }
    }
    printHeader += " ";

    return printHeader;
  }

  public String display() {
    String printStaff = displayHeader();
    for (int i = 0; i < totalDuration; i++) {
      printStaff += "\n ";
      for (int j = 0; j < lengthOfStaff; j++) {
        try {
          if (staff.get(j).get(i).getRepresentation().equals("  X  ")) {
            printStaff += "  X  ";
          } else if (staff.get(j).get(i).getRepresentation().equals("  |  ")) {
            printStaff += "  |  ";
          }
        }
        // why do you have MusicRest then lol
        catch (IndexOutOfBoundsException e) {
          printStaff += "     ";
        }
      }
    }

    return printStaff;

  }
}


