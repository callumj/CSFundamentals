public class ArrayQueue implements Queue {
  private Object[] alloc;
  private int front;
  private int cursor;

  private int size;

  public ArrayQueue(int capacity) {
    this.alloc = new Object[capacity];
    this.front = 0;
    this.cursor = 0;

    this.size = 0;
  }

  public void enqueue(Object obj) {
    if (obj == null)
      throw new IllegalArgumentException("Cannot be null");

    if (this.size == alloc.length)
      throw new java.nio.BufferOverflowException();

    this.alloc[this.cursor] = obj;
    this.cursor += 1;
    this.size += 1;

    if (this.cursor == this.alloc.length) {
      this.cursor = 0;
    }
  }

  public Object dequeue() {
    if (this.size == 0)
      return null;

    Object keep = this.alloc[this.front];
    this.front += 1;
    this.size -= 1;

    if (this.size == 0) {
      // reset
      this.front = 0;
      this.cursor = 0;
    } else if (this.front == this.alloc.length) {
      this.front = 0;
    }

    return keep;
  }

  public Object peek() {
    if (this.size == 0)
      return null;

    return this.alloc[this.front];
  }

  public int size() {
    return this.size;
  }
}