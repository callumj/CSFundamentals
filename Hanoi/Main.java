public class Main {

  public static void main(String[] args) {

    Hanoi h = new Hanoi(10);
    h.run();

    h.printStacks();
  }
}