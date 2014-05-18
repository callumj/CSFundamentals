public class Node {
  public Node next;
  public Object obj;

  public Node(Object obj, Node next) {
    this.next = next;
    this.obj = obj;
  }
}