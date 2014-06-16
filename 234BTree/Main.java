public class Main {

  public static void main(String[] args) {
    TTFTree<Integer> inst = new TTFTree<Integer>();
    inst.Insert(8);
    inst.Insert(10);
    inst.Insert(9);
    inst.Insert(-1);

    inst.Print();
  }
}