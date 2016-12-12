package cs3500.music.model;

public class Main {
  public static void main(String[] args) {
    MusicStaff m = new MusicStaff(1);
    MusicNote n1 = new MusicNote(MusicNote.Pitch.C, 30, 4);
    m.addNote(n1, 8);
    System.out.println(m.display());
  }
}

