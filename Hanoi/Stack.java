public class Stack {
  private class Node {
    public Node next;
    public Object obj;

    public Node(Object obj, Node next) {
      this.next = next;
      this.obj = obj;
    }
  }

  private Node recent;
  private int objectCount;

  public Stack() {
    this.objectCount = 0;
  }

  public Object peek() {
    if (this.recent == null)
      return null;

    return this.recent.obj;
  }

  public void push(Object obj) {
    if (obj == null)
      return;

    this.recent = new Node(obj, this.recent);
    this.objectCount += 1;
  }

  public Object pop() {
    if (this.recent == null)
      return null;

    Object bkup = this.recent.obj;
    this.recent = this.recent.next;
    this.objectCount -= 1;

    return bkup;
  }

  public int size() {
    return this.objectCount;
  }
}