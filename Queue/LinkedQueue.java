public class LinkedQueue implements Queue {
  private class Node {
    public Node next;
    public Node previous;
    public Object obj;

    public Node(Object obj, Node next, Node previous) {
      this.next = next;
      this.previous = previous;
      this.obj = obj;

      if (this.next != null)
        this.next.previous = this;
    }
  }

  private Node front;
  private Node back;

  private int count;

  public void enqueue(Object obj) {
    if (obj == null)
      throw new IllegalArgumentException("Cannot be null");

    if (this.front == null) {
      this.back = this.front = new Node(obj, null, null);
    } else {
      this.back = new Node(obj, this.back, null);
      if (this.front.previous == null)
        this.front.previous = this.back;
    }
    count += 1;
  }

  public Object dequeue() {
    if (this.front == null)
      return null;

    Object fetched = this.front.obj;
    this.front = this.front.previous;
    count -= 1;
    return fetched;
  }

  public Object peek() {
    if (this.front == null)
      return null;

    return this.front.obj;
  }

  public int size() {
    return count;
  }
}