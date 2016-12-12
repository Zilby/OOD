import java.nio.*;
import java.util.*;
import java.io.*;

interface CalcController {
  void go(Calculator calc);
}

class Calculator {
  public int add(int num1, int num2) {
    return num1 + num2;
  }
}

/*
class SimpleCalc3 {
    public static void main(String[] args) {
	new Controller3(new InputStreamReader(System.in), System.out).go(new Calculator());
    }
}
*/

class Controller3 implements CalcController {
    private Scanner input;
    private Formatter out;
    
    public void go(Calculator calc) {
	Objects.requireNonNull(calc);
	int num1, num2;
	num1 = input.nextInt();
	num2 = input.nextInt();
	out.format("%d", calc.add(num1, num2));
	out.flush();
    }

    public Controller3(Readable rd, Appendable ap) {
	this.input = new Scanner(rd);
	this.out = new Formatter(ap);
    }
}


class TestController3 {
    public static void main(String[] args) {	
	InputStream in = new ByteArrayInputStream("3 5".getBytes());
	ByteArrayOutputStream bytes = new ByteArrayOutputStream();
	PrintStream out = new PrintStream(bytes);
	CalcController controller3 = new Controller3(new InputStreamReader(in), out);
	controller3.go(new Calculator());
	System.out.println(new String(bytes.toByteArray()));
    }
}


