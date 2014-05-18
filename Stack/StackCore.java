public abstract class StackCore implements Stack {
  public void duplicate() {
    Object popped = this.pop();
    this.push(popped);
    this.push(popped);
  }

  public void swap() {
    if (count() < 2)
      throw new IllegalArgumentException("Not enough items");

    Object one = this.pop();
    Object two = this.pop();

    this.push(one);
    this.push(two);
  }

  public void rotateRight(int n) {
    rotate(false, n);
  }

  public void rotateLeft(int n) {
    rotate(true, n);
  }

  private void rotate(boolean left, int n) {
    if (n > count())
      throw new IllegalArgumentException("Index out of bounds");

    Object[] alloc = new Object[n];
    for (int i = 0; i < n; i++) {
      alloc[i] = this.pop();
    }

    if (left) {
      for (int i = n - 2; i >= 0; i--) {
        this.push(alloc[i]);
      }
      this.push(alloc[n - 1]);
    } else {
      this.push(alloc[0]);
      for (int i = n - 1; i > 0; i--) {
        this.push(alloc[i]);
      }
    }
  }
}