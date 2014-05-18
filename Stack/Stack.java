public interface Stack {
  public void push(Object obj);

  public Object pop();

  public Object peek();

  public int count();

  public void duplicate();

  public void swap();

  public void rotateRight(int n);

  public void rotateLeft(int n);
}