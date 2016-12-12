package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import cs3500.music.model.GenericMusicEditor;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.CompositeView;
import cs3500.music.view.GuiView;
import cs3500.music.view.IView;
import cs3500.music.view.ViewFactory;

/**
 * This class the controller for our program. It initializes our editor and
 * the various views and sets up their keyboard and mouse handlers
 */
public class MusicController {

  /**
   * Initializes our editor by loading a file of midi music, processing it with
   * a MusicReader and building a new editor with a CompositionBuilder. If given
   * a bad file, it will request input again.
   *
   * @return GenericMusicEditor that has now been initialized with notes
   */
  public static GenericMusicEditor initializeEditor() {
    Scanner sc = new Scanner(System.in);
    CompositionBuilder<GenericMusicEditor> b = new MusicEditorModel.Builder();
    System.out.println("What piece of music would you like to use? ");
    String s = sc.nextLine();
    if (!s.equals("none")) {
      MusicReader mr = new MusicReader();
      try {
        Readable r = new FileReader(s);
        return mr.parseFile(r, b);
      } catch (FileNotFoundException e) {
        System.out.println("File not found. Try again.");
        return initializeEditor();
      }
    }
    return b.build();
  }

  /**
   * Initializes our view by creating the desired view with a user inputted
   * String and the given editor
   *
   * @param e the editor to be used to initialize our view
   * @return IView that has now been initialized to a specific editor and
   * implementing class.
   */
  public static IView initializeView(GenericMusicEditor e) {
    Scanner sc = new Scanner(System.in);
    ViewFactory v = new ViewFactory();
    System.out.println("What view would you like to use? " + "" +
            "(console/midi/gui/composite) ");
    String s = sc.nextLine();
    try {
      IView view = v.createView(s, e);
      if (view instanceof GuiView) {
        return initializeGuiView((GuiView) view);
      } else if (view instanceof CompositeView) {
        return initializeFullView((CompositeView) view);
      } else {
        return view;
      }
    } catch (IllegalArgumentException i) {
      System.out.println("Invalid view type. Try again.");
      return initializeView(e);
    }
  }

  /**
   * Initializes our gui view by adding in different runnable tasks for key and
   * mouse events.
   *
   * @param v  the view to be initialized
   * @return GuiView that has now been initialized with key and mouse events
   */
  public static GuiView initializeGuiView(GuiView v) {
    KeyboardHandler kh = new KeyboardHandler();
    MouseHandler mh = new MouseHandler();

    Runnable task;
    MouseRunnable task2;

    task = () -> {
      v.getHorizontalScroll().setValue(v.getHorizontalScroll().getMaximum());
    };

    kh.addReleasedRunnable(KeyEvent.VK_END, task);

    task = () -> {
      v.getHorizontalScroll().setValue(v.getHorizontalScroll().getMinimum());
    };

    kh.addReleasedRunnable(KeyEvent.VK_HOME, task);

    task = () -> {
      v.getHorizontalScroll().setValue(v.getHorizontalScroll().getValue() + 10);
    };

    kh.addPressedRunnable(KeyEvent.VK_RIGHT, task);

    task = () -> {
      v.getHorizontalScroll().setValue(v.getHorizontalScroll().getValue() - 10);
    };

    kh.addPressedRunnable(KeyEvent.VK_LEFT, task);

    task = () -> {
      v.getVerticalScroll().setValue(v.getVerticalScroll().getValue() - 10);
    };

    kh.addPressedRunnable(KeyEvent.VK_UP, task);

    task = () -> {
      v.getVerticalScroll().setValue(v.getVerticalScroll().getValue() + 10);
    };

    kh.addPressedRunnable(KeyEvent.VK_DOWN, task);

    task = () -> {
      v.addNote();
    };

    kh.addPressedRunnable(KeyEvent.VK_A, task);

    task2 = new MouseRunnable() {
      @Override
      public void run(MouseEvent e) {
        v.removeNote(e.getX(), e.getY());
      }
    };

    mh.addMouseRunnable(MouseEvent.MOUSE_RELEASED, task2);
    v.addKeyListener(kh);
    v.addMouseListener(mh);
    return v;
  }

  /**
   * Initializes our full view by adding in different runnable tasks for key and
   * mouse events.
   *
   * @param v the view to be initialized
   * @return CompositeView that has now been initialized with key and mouse events
   */
  public static CompositeView initializeFullView(CompositeView v) {
    KeyboardHandler kh = new KeyboardHandler();
    Runnable task;

    task = () -> {
      v.pause();
    };

    kh.addReleasedRunnable(KeyEvent.VK_P, task);

    v.getGui().addKeyListener(kh);
    return v;
  }
}
