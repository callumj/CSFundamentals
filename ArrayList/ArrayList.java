public class ArrayList {

  private static final int INITIAL_SIZE = 2;

  private Object[] internal;
  private int objectCount;

  public ArrayList() {
    this(INITIAL_SIZE);
  }

  public ArrayList(int capacity) {
    this.internal = new Object[capacity];
    this.objectCount = 0;
  }

  public int numberOfObjects() {
    return this.objectCount;
  }

  public Object get(int index) throws IndexOutOfBoundsException {
    if (index >= internal.length)
      throw new IndexOutOfBoundsException();

    return internal[index];
  }

  public void set(int index, Object object) {
    if (object != null && (index >= this.internal.length || this.objectCount == this.internal.length)) {
      resizeUp(index);
    }

    Object cur = this.internal[index];
    int addition = 0;
    if (object == null && cur == null)
      return;
    else if (cur != null && object == null)
      addition = -1;
    else
      addition = 1;

    objectCount += addition;
    this.internal[index] = object;

    if (object == null)
      resizeDown();
  }

  private void resizeDown() {
    if (this.objectCount == 0) {
      this.resizeTo(INITIAL_SIZE);
      return;
    }

    if (((this.objectCount * 1.0) / (this.internal.length * 1.0)) <= 0.7) {
      // figure the max we can resize back down to
      int endsAt = this.internal.length - 1;
      for(int index = (this.internal.length - 1); index >= 0; index--) {
        if (this.internal[index] != null) {
          endsAt = index;
          break;
        }
      }
      resizeTo(endsAt + 1);
    }
  }

  private void resizeUp(int estimatedSize) {
    if (estimatedSize < (this.internal.length * 2)) {
      estimatedSize = this.internal.length * 2;
    } else {
      estimatedSize = (int)(estimatedSize * 1.5);
    }

    resizeTo(estimatedSize);
  }

  private void resizeTo(int size) {
    System.out.println("Resizing to " + size);

    Object[] resized = new Object[size];
    // copy everything in O(n)
    for(int index = 0; index < this.internal.length; index++) {
      if (this.internal[index] != null)
        resized[index] = this.internal[index];
    }

    this.internal = resized;
  }
}