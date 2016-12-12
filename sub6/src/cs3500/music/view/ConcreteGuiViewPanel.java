package cs3500.music.view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import cs3500.music.model.GenericMusicEditor;
import cs3500.music.model.Note;
import cs3500.music.model.Song;


/**
 * The panel for our gui view. This draws all the notes, bars, pitches, and
 * other info for our song in the gui view.
 */
public class ConcreteGuiViewPanel extends JPanel {

  private Song s;
  private int x1;
  private int y1;
  private int y2;
  private boolean first;
  private ArrayList<GraphicsUpdater> drawCommands;

  public ConcreteGuiViewPanel(GenericMusicEditor e) {
    this.s = e.getSong();
    this.x1 = 30;
    this.y1 = 30;
    this.y2 = 30;
    for (String st : s.getPitches()) {
      y2 += 20;
    }
    this.first = true;
    this.drawCommands = new ArrayList<>();
  }

  /**
   * displays the song in a gui view. The view will contain only the pitches and
   * beats that are used in the song, and represent the notes by an black square
   * for a newly struck note and green boxes for subsequent sustained notes. We
   * chose to arrange the notes with the lower pitches on top so that when
   * turned 90 degrees counter-clockwise the layout would match that of a piano,
   * an instrument that is often used for generating notes.
   *
   * To alleviate load times, it will store the graphics draw commands in an
   * ArrayList<GraphicsUpdater> so that it doesn't have to derive them after
   * the first time.
   *
   * @param g our graphics being used
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (first) {
      GraphicsUpdater task;
      if (s.length() == 0) {
        g.drawString("Nothing here yet, let's make some music!", 25, 25);
      } else {
        int x;
        int y;
        int beat;
        ArrayList<String> pitches = s.getPitches();
        for (Note n : s) {
          x = 40;
          y = 30;
          int index = 0;
          while (!pitches.get(index).equals(n.toString())) {
            y += 20;
            index++;
          }
          beat = n.getBeat();
          int i = 0;
          while (i < beat) {
            x += 20;
            i++;
          }
          if (n.held()) {
            task = new GraphicsUpdater() {
              @Override
              public void run(Graphics gr) {
                gr.setColor(Color.GREEN);
              }
            };
            this.drawCommands.add(task);
          } else {
            task = new GraphicsUpdater() {
              @Override
              public void run(Graphics gr) {
                gr.setColor(Color.black);
              }
            };
            this.drawCommands.add(task);
          }
          final int xtemp = x;
          final int ytemp = y;
          task = new GraphicsUpdater() {
            @Override
            public void run(Graphics gr) {
              gr.drawRect(xtemp, ytemp, 20, 20);
              gr.fillRect(xtemp, ytemp, 20, 20);
            }
          };
          this.drawCommands.add(task);
        }
        task = new GraphicsUpdater() {
          @Override
          public void run(Graphics gr) {
            gr.setColor(Color.black);
          }
        };
        this.drawCommands.add(task);
        y = 45;
        x = 0;
        int height = 30;
        beat = s.getFirst().getBeat();
        int lastBeat = s.getLast().getBeat();
        while (lastBeat % 4 != 0) {
          lastBeat++;
        }
        final int ytemp2 = y;
        final int lasttemp = lastBeat;
        task = new GraphicsUpdater() {
          @Override
          public void run(Graphics gr) {
            gr.drawLine(40, ytemp2 - 15, 40 + (20 * lasttemp), ytemp2 - 15);
          }
        };
        this.drawCommands.add(task);
        for (String st : pitches) {
          final String stemp = st;
          final int xtemp = x;
          final int ytemp = y;
          task = new GraphicsUpdater() {
            @Override
            public void run(Graphics gr) {
              gr.drawString(stemp, xtemp, ytemp);
            }
          };
          this.drawCommands.add(task);
          y += 20;
          if (st.charAt(0) == 'B') {
            final int ytemp3 = y;
            final int lasttemp2 = lastBeat;
            task = new GraphicsUpdater() {
              @Override
              public void run(Graphics gr) {
                gr.drawRect(40, ytemp3 - 16, 20 * lasttemp2, 2);
                gr.fillRect(40, ytemp3 - 16, 20 * lasttemp2, 2);
              }
            };
            this.drawCommands.add(task);
          } else {
            final int ytemp3 = y;
            final int lasttemp2 = lastBeat;
            task = new GraphicsUpdater() {
              @Override
              public void run(Graphics gr) {
                gr.drawLine(40, ytemp3 - 15, 40 + (20 * lasttemp2), ytemp3 -
                        15);
              }
            };
            this.drawCommands.add(task);
          }
          height += 20;
        }
        y = 20;
        x = 40;
        while (beat <= lastBeat) {
          if (beat % 16 == 0) {
            final int ytemp = y;
            final int xtemp = x;
            final int beattemp = beat;
            task = new GraphicsUpdater() {
              @Override
              public void run(Graphics gr) {
                gr.drawString(beattemp + "", xtemp - 5, ytemp);
              }
            };
            this.drawCommands.add(task);
          }
          y += 10;
          if (beat % 4 == 0) {
            final int ytemp = y;
            final int xtemp = x;
            final int heighttemp = height;
            task = new GraphicsUpdater() {
              @Override
              public void run(Graphics gr) {
                gr.drawLine(xtemp, ytemp, xtemp, heighttemp);
              }
            };
            this.drawCommands.add(task);
          }
          beat++;
          x += 20;
          y = 20;
        }
      }
      for (GraphicsUpdater gu : drawCommands) {
        gu.run(g);
      }
      drawCommands.add(new GraphicsUpdater());
      this.first = false;
    } else {
      for (GraphicsUpdater gu : drawCommands) {
        gu.run(g);
      }
    }
  }

  /**
   * updates the graphics for our panel by moving the measure line each time
   * it is called.
   */
  public void updateGraphics() {
    GraphicsUpdater task = new GraphicsUpdater() {
      @Override
      public void run(Graphics gr) {
        gr.setColor(Color.RED);
        gr.drawLine(x1, y1, x1, y2);
      }
    };
    drawCommands.set(drawCommands.size() - 1, task);
    x1++;
  }

  /**
   * called on every actionEvent, onAction updates the graphics and returns
   * the current coordinates of the measure line.
   *
   * @return the current x coordinate of the measure line.
   */
  public int onAction() {
    for (int i = 20; i > 0; i--) {
      updateGraphics();
      this.repaint();
      this.revalidate();
    }
    return this.x1;
  }

  /**
   * updates the graphics for our panel to the new song s
   */
  public void onUpdate(Song s) {
    this.first = true;
    this.s = s;
    this.drawCommands = new ArrayList<>();
    this.repaint();
    this.revalidate();
  }

  /**
   * the preferred size of our panel. This is needed so that our scrolling knows
   * our desired size. This is determined by the number of pitches and beats in
   * the song.
   *
   * @return Dimension that we desire
   */
  @Override
  public Dimension getPreferredSize() {
    if (s.length() == 0) {
      return new Dimension(100, 20);
    } else {
      int height = (s.getPitches().size() + 4) * 20;
      int width = s.getLast().getBeat();
      while (width % 4 != 0) {
        width++;
      }
      width = (width + 4) * 20;
      return new Dimension(width, height);
    }
  }
}
