public class LinkedStack extends StackCore {
  private Node recent;
  private int objectCount;

  public LinkedStack() {
    this.objectCount = 0;
  }

  public LinkedStack(Object first) {
    this.recent = new Node(first, null);
    this.objectCount = 1;
  }

  public void push(Object obj) {
    if (obj == null)
      throw new IllegalArgumentException("Cannot be null");
    this.recent = new Node(obj, this.recent);
    this.objectCount += 1;
  }

  public Object pop() {
    if (this.recent == null)
      return null;

    Object save = this.recent.obj;
    this.recent = this.recent.next;
    this.objectCount -= 1;

    return save;
  }

  public Object peek() {
    if (this.recent == null)
      return null;

    return this.recent.obj;
  }

  public int count() {
    return this.objectCount;
  }
}