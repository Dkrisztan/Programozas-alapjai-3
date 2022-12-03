import sample.calc.Calculator;

public class Sum {
  public static void main(String[] args) {
    int sum = 0;
    Calculator ossz = new Calculator();
    for (int i = 0; i < args.length; i++) {
      sum = ossz.add(sum, Integer.parseInt(args[i]));
    }
    System.out.println(sum);
  }
}
