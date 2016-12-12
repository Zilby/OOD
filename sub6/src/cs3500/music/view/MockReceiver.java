package cs3500.music.view;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

/**
 * Our mock receiver. This class will add any sent messages to its StringBuilder
 * for debugging.
 */
public class MockReceiver implements Receiver {
  private StringBuilder s;

  public MockReceiver(StringBuilder s) {
    this.s = s;
  }

  @Override
  public void send(MidiMessage message, long timeStamp) {
    if (message instanceof ShortMessage) {
      String command = ((ShortMessage) message).getCommand() + "";
      if (command.equals("144")) {
        command = "NOTE_ON ";
      } else if (command.equals("128")) {
        command = "NOTE_OFF";
      }
      s.append("Command: " + command +
              " Instrument: " + ((ShortMessage) message).getChannel() +
              " Pitch/Octave: " + ((ShortMessage) message).getData1() +
              " Volume: " + ((ShortMessage) message).getData2() +
              " Timestamp: " + timeStamp + "\n");
    } else {
      s.append(message.toString()).append(" Timestamp: ").append(timeStamp)
              .append("\n");

    }
  }

  @Override
  public void close() {
  }
}
