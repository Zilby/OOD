package cs3500.hw03;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws IOException{
    Scanner sc = new Scanner(System.in);
    WhistController w = new WhistController(new InputStreamReader(System.in),
            System.out);
    System.out.println("Input how many players you would like for this game " +
            "of Whist: ");
    if(sc.hasNextInt()) {
      w.playGame(new WhistModel(), sc.nextInt());
    } else {
      System.out.println("Invalid player number input.");
    }
  }
}

