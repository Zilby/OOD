package cs3500.music;

import cs3500.music.controller.MusicController;
import cs3500.music.model.GenericMusicEditor;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.util.*;
import cs3500.music.view.*;

import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

/**
 * Our main class
 */
public class MusicEditor {

  public static void main(String[] args) throws IOException,
          InvalidMidiDataException {
    MusicController m = new MusicController();
    GenericMusicEditor e = m.initializeEditor();
    IView view = m.initializeView(e);
    view.outputView();
  }
}