public class Main {

  public static void main(String[] args) {
    Node head = new Node(null, "1");

    Node two = head.append("2");
    Node three = head.insertAfter(two, "3");
    Node five = head.insertAfter(three, "5");
    Node four = head.insertAfter(three, "4");
    Node four2 = head.insertAfter(three, "4");
    Node three2 = head.insertAfter(four2, "3");

    printTraverse(head);

    deDupe(head);

    printTraverse(head);

    /*head.removeAfter(three);

    printTraverse(head);

    head.removeAfter(three);

    printTraverse(head);

    head.removeAfter(three);

    printTraverse(head);

    Node twoFive = head.insertAfter(two, "2.5");

    printTraverse(head);

    head.remove(twoFive);

    printTraverse(head);

    head = head.remove(head);

    printTraverse(head);

    deDupe(head);*/
  }

  public static void printTraverse(Node head) {
    System.out.println("-----");
    Object[] allObjects = head.allObjects();
    for (Object obj : allObjects)
      System.out.println(obj);
    System.out.printf("Objects: %d\r\n", head.nodeCount);
    System.out.println("-----");
  }

  public static void deDupe(Node head) {
    Node target = head;

    while (target != null) {
      Node child = target.next;
      Node pntr = target;

      while (child != null) {
        if (child.obj != target.obj) {
          pntr.next = child;
          pntr = pntr.next;
        }
        child = child.next;
      }
      target = target.next;
    }
  }
}