import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {
    BinaryTree t = new BinaryTree(20, null, null);

    t.Add(10);
    t.Add(19);
    t.Add(25);
    t.Add(21);
    t.Add(29);
    t.Add(24);
    t.Add(28);
    t.Add(48);

    //printTree(t);
    //t.Find(28);

    t.Remove(20);
    t.Find(24);
    printTree(t);
  }

  public static void printTree(BinaryTree tree) {
    ArrayList<ArrayList<BinaryTree>> l = new ArrayList<ArrayList<BinaryTree>>();

    ArrayList<BinaryTree> first = new ArrayList<BinaryTree>();
    first.add(tree);
    l.add(first);

    int index = 0;
    boolean active = true;
    while (active) {
      ArrayList<BinaryTree> target = l.get(index);
      ArrayList<BinaryTree> insert = new ArrayList<BinaryTree>();

      boolean allNulls = true;
      for(BinaryTree val : target) {
        if (val == null) {
          insert.add(null);
          insert.add(null);
        } else {
          insert.add(val.left);
          insert.add(val.right);
          allNulls = false;
        }
      }

      l.add(insert);
      index += 1;
      if (allNulls)
        active = false;
    }

    for(ArrayList<BinaryTree> target : l) {
      System.out.print("...");
      for(BinaryTree val : target) {
        if (val == null) {
          System.out.print(".");
        } else {
          System.out.print(val.value);
        }
        System.out.print("...");
      }
      System.out.println();
    }
  }
}