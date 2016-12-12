package cs3500.hw04;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import cs3500.hw03.WhistController;

import static cs3500.hw04.WhistModelCreator.ModelType.*;

public class Main {
  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(System.in);
    WhistController w = new WhistController(new InputStreamReader(System.in),
            System.out);
    System.out.println("Input how many players you would like for this game "
            + "of Whist: ");
    if (sc.hasNextInt()) {
      int i = sc.nextInt();
      System.out.println("Would you like to play with Trump cards? (y/n)");
      WhistModelCreator c = new WhistModelCreator();
      if (sc.next().equals("y")) {
        w.playGame(c.create(TRUMP), i);
      } else if (sc.next().equals("n")) {
        w.playGame(c.create(NOTRUMP), i);
      } else {
        System.out.println("Invalid input.");
      }
    } else {
      System.out.println("Invalid player number input.");
    }
  }
}

