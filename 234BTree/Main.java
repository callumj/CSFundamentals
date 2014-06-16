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

    /*inst.Print();

    inst.Remove(80);
    inst.Remove(60);
    inst.Remove(25);

    inst.Print();*/

    TTFTree<Integer> inst2 = new TTFTree<Integer>(false);
    inst2.First = 20;
    inst2.Second = 40;

    inst2.LessFirst = new TTFTree<Integer>(false);
    inst2.LessFirst.First = 10;

    inst2.GreaterFirst = new TTFTree<Integer>(false);
    inst2.GreaterFirst.First = 30;
    inst2.GreaterFirst.LessFirst = new TTFTree<Integer>(false);
    inst2.GreaterFirst.LessFirst.First = 25;

    /*inst2.GreaterSecond = new TTFTree<Integer>(false);
    inst2.GreaterSecond.First = 50;*/

    inst2.Remove(25);

    inst2.Print();
  }
}