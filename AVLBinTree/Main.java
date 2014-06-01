import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {
    BinaryTree t = new BinaryTree(20, null, null);

    for (int i = 1; i < 1000000; i++)
      t.Add(i);

    System.out.println(t.value);
    t.Find(1);

    t.Add(2000001);
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