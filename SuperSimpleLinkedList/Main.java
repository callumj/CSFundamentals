public class Main {

  public static void main(String[] args) {
    Node head = new Node();
    head.obj = "1";

    Node two = new Node();
    two.obj = "2";

    Node three = new Node();
    three.obj = "3";

    Node four = new Node();
    four.obj = "4";

    Node five = new Node();
    five.obj = "5";

    head.append(three);
    head.insertBefore(three, two);
    head.insertAfter(three, five);
    head.insertBefore(five, four);

    printTraverse(head);

    Node zero = new Node();
    zero.obj = "0";

    head = head.insertBeginning(zero);

    printTraverse(head);

    Node six = new Node();
    six.obj = "6";
    five.insertAfter(null, six);

    Node fivePoint = new Node();
    fivePoint.obj = "5.5";
    five.append(fivePoint);

    printTraverse(head);

    head = head.remove(head);
    head = head.remove(head);
    head = head.remove(head);

    printTraverse(head);
  }

  public static void printTraverse(Node head) {
    System.out.println("-----");
    while(head != null) {
      System.out.println(head.obj);
      head = head.next;
    }
    System.out.println("-----");
  }
}