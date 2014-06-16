public class Main {

  public static void main(String[] args) throws Exception {
    TTFTree<Integer> inst = new TTFTree<Integer>();
    inst.Insert(60);
    inst.Insert(20);
    inst.Insert(10);
    inst.Insert(30);
    inst.Insert(25);
    inst.Insert(50);
    inst.Insert(80);
    inst.Insert(55);
    inst.Insert(58);
    inst.Insert(57);

    inst.Print();
  }
}