public class ArrayStack extends StackCore {
  private Object[] alloc;
  private int size;

  public ArrayStack(int capacity) {
    this.alloc = new Object[capacity];
    this.size = 0;
  }

  public void push(Object obj) {
    if (obj == null)
      throw new IllegalArgumentException("Cannot be null");

    if (size == alloc.length)
      throw new java.nio.BufferOverflowException();
    alloc[size] = obj;
    size++;
  }

  public Object pop() {
    if (this.size == 0)
      return null;

    this.size -= 1;
    return this.alloc[this.size];
  }

  public Object peek() {
    if (this.size == 0)
      return null;

    return alloc[this.size - 1];
  }

  public int count() {
    return this.size;
  }
}