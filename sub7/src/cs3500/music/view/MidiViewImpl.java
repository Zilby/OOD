package cs3500.music.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.midi.*;
import javax.swing.*;

import cs3500.music.model.GenericMusicEditor;
import cs3500.music.model.Note;
import cs3500.music.model.Song;

/**
 * This is our implementation for our midi view. It is designed to play our song
 * when outputView is called and uses a synth, receiver and thread.sleep to
 * accomplish this.
 */
public class MidiViewImpl implements IView, ActionListener {
  private final MidiDevice synth;
  private final Receiver receiver;
  Song s;
  int tempo;
  int currentBeat;
  int currentIndex;
  Timer time;

  public MidiViewImpl(GenericMusicEditor ge) {
    Synthesizer tempSynth = null;
    Receiver tempReceiver = null;
    try {
      tempSynth = MidiSystem.getSynthesizer();
      tempReceiver = tempSynth.getReceiver();
      tempSynth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    this.synth = tempSynth;
    this.receiver = tempReceiver;
    this.s = ge.getSong();
    this.tempo = (ge.getTempo() / 1200);
    this.currentBeat = 0;
    this.currentIndex = 0;
    this.time = new Timer(tempo, this);
  }

  /**
   * Constructor for our mock synthesizer and receiver
   */
  public MidiViewImpl(GenericMusicEditor ge, StringBuilder s) {
    this.synth = new MockMidiDevice(s);
    Receiver tempReceiver = null;
    try {
      tempReceiver = synth.getReceiver();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    this.receiver = tempReceiver;
    this.s = ge.getSong();
    this.tempo = (ge.getTempo() / 1200);
    this.currentBeat = 0;
    this.currentIndex = 0;
    this.time = new Timer(tempo, this);
  }

  /**
   * Relevant classes and methods from the javax.sound.midi library: <ul>
   * <li>{@link MidiSystem#getSynthesizer()}</li> <li>{@link Synthesizer} <ul>
   * <li>{@link Synthesizer#open()}</li>
   * <li>{@link Synthesizer#getReceiver()}</li>
   * <li>{@link Synthesizer#getChannels()}</li> </ul> </li> <li>{@link Receiver}
   * <ul> <li>{@link Receiver#send(MidiMessage, long)}</li> <li>{@link
   * Receiver#close()}</li> </ul> </li> <li>{@link MidiMessage}</li> <li>{@link
   * ShortMessage}</li> <li>{@link MidiChannel} <ul> <li>{@link
   * MidiChannel#getProgram()}</li>
   * <li>{@link MidiChannel#programChange(int)}</li>
   * </ul> </li> </ul>
   *
   * @see <a href="https://en.wikipedia.org/wiki/General_MIDI">
   * https://en.wikipedia.org/wiki/General_MIDI </a>
   */

  /**
   * Plays our song with the correct timing, tempo and instruments.
   */
  public void playNote() throws InvalidMidiDataException, InterruptedException {
    int lastBeat = this.s.getLast().getBeat();
    while (currentIndex < s.length()) {
      Note n = s.getNote(currentIndex);
      if (n.getBeat() == currentBeat) {
        boolean last = true;
        if (!n.held()) {
          MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, n
                  .getInstrument(), n.getMidi(), n.getVolume());
          this.receiver.send(start, -1);
        } else {
          for (int i = currentIndex + 1; i < s.length(); i++) {
            if (s.getNote(i).getPitch().equals(n.getPitch()) && s.getNote(i)
                    .getBeat() == n.getBeat() + 1) {
              last = false;
              break;
            }
            if (s.getNote(i).getBeat() > n.getBeat() + 1) {
              break;
            }
          }
          if (last) {
            MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, n
                    .getInstrument(), n.getMidi(), n.getVolume());
            this.receiver.send(stop, this.synth.getMicrosecondPosition() +
                    200000);
          }
        }
        currentIndex++;
      } else {
        break;
      }
    }
    currentBeat++;
  }

  /**
   * Attempts to play our song, but will catch and print errors if encountered.
   */
  public void outputView() {
    time.start();
    while (time.isRunning()) {

    }
  }

  /**
   * plays the notes for the current beat and stops the timer if the song is
   * over
   *
   * @param e the action event being responded to
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      this.playNote();
      if (!(currentBeat <= this.s.getLast().getBeat())) {
        time.stop();
        this.receiver.close();
      }
    } catch (InvalidMidiDataException e1) {
      System.out.println("Encountered InvalidMidiDataException");
    } catch (InterruptedException e2) {
      System.out.println("Encountered InterruptedException");
    }
  }

  /**
   * Returns the timer for our view
   *
   * @return our timer
   */
  public Timer getTimer() {
    return this.time;
  }
}
