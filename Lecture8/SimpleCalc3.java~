package cs3500.lec08;

import java.util.Objects;
import java.util.Scanner;

/**
 * Demonstrates a simple command-line-based calculator
 */
public class SimpleCalc3 {
  public static void main(String[] args) {
    new Controller3().go(new Calculator());
  }
}

class Controller3 implements CalcController {
  public void go(Calculator calc) {
    Objects.requireNonNull(calc);
    int num1, num2;
    Scanner scan = new Scanner(System.in);
    num1 = scan.nextInt();
    num2 = scan.nextInt();
    System.out.printf("%d", calc.add(num1, num2));
  }
}

