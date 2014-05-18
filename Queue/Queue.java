public interface Queue {
  public void enqueue(Object obj);
  public Object dequeue();
  public Object peek();

  public int size();
}