public class Node {

  public class Enumerator implements java.util.Enumeration<Node> {

    private Node head;
    private Node current;

    public Enumerator(Node start) {
      this.head = start;
      this.current = this.head;
    }

    public boolean hasMoreElements() {
      return this.current != null;
    }

    public Node nextElement() {
      Node ret = this.current;
      this.current = this.current.next;
      return ret;
    }

  }

  public int nodeCount;

  public Node next;
  public Object obj;

  public Node(Node next, Object obj) {
    this.next = next;
    this.obj = obj;

    syncNodeCount();
  }

  public Node append(Object obj) {
    return insertAfter(this, obj);
  }

  public Node insertAfter(Node after, Object obj) {
    Node constr  = null;
    if (this.next == null) {
      constr = this.next = new Node(null, obj);
    } else if (this == after) {
      constr = this.next = new Node(this.next, obj);
    } else {
      constr = this.next.insertAfter(after, obj);
    }

    syncNodeCount();

    return constr;
  }

  public Node remove(Node removal) {
    if (removal == null)
      return this;

    if (this == removal) {
      return this.next;
    } else if (this.next == removal) {
      this.next = this.next.next;
    } else if (this.next != null) {
      this.next.remove(removal);
    }

    syncNodeCount();
    return this;
  }

  public void removeAfter(Node after) {
    if (this == after) {
      if (this.next != null)
        this.next = this.next.next;
    } else if (this.next != null) {
      this.next.removeAfter(after);
    }

    syncNodeCount();
  }

  public Enumerator enumerator() {
    return new Enumerator(this);
  }

  private void syncNodeCount() {
    if (this.obj != null)
      this.nodeCount = 1;
    else
      this.nodeCount = 0;

    if (this.next != null)
      this.nodeCount += this.next.nodeCount;
  }

  public Object[] allObjects() {
    Object[] alloc = new Object[this.nodeCount];
    Node target = this;
    int index = 0;
    while (target != null) {
      alloc[index] = target.obj;
      target = target.next;
      index++;
    }
    return alloc;
  }
}