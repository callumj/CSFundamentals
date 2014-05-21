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

  public boolean isEmpty() {
    return count() == 0;
  }

  public static void reorder(LinkedStack current) {
    LinkedStack copy = new LinkedStack();

    while(!current.isEmpty())
      copy.push(current.pop());

    while(!copy.isEmpty()) {
      if (current.isEmpty() || ((Integer)current.peek()) > ((Integer)copy.peek()))
        current.push(copy.pop());
      else {
        LinkedStack temp = new LinkedStack();
        while(!current.isEmpty() && ((Integer)current.peek()) < ((Integer)copy.peek()))
          temp.push(current.pop());
        current.push(copy.pop());
        while(!temp.isEmpty())
          current.push(temp.pop());
      }
    }
  }
}