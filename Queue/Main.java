public class Main {

  public static void main(String[] args) {

    LinkedQueue qu = new LinkedQueue();
    doQueueyThings(qu);
  }

  public static void doQueueyThings(Queue qu) {
    qu.enqueue("1");
    qu.enqueue("2");
    qu.enqueue("3");
    qu.enqueue("4");
    qu.enqueue("5");

    assert qu.dequeue() == "1";
    assert qu.size() == 4;
    assert qu.dequeue() == "2";
    assert qu.size() == 3;
    assert qu.dequeue() == "3";
    assert qu.size() == 2;
    assert qu.dequeue() == "4";
    assert qu.size() == 1;
    assert qu.dequeue() == "5";
    assert qu.size() == 0;
    assert qu.dequeue() == null;
    assert qu.size() == 0;
    assert qu.dequeue() == null;
    assert qu.size() == 0;

    qu.enqueue("A");
    qu.enqueue("B");
    assert qu.size() == 2;

    assert qu.peek() == "A";
    assert qu.dequeue() == "A";
    assert qu.size() == 1;

    assert qu.peek() == "B";
    assert qu.dequeue() == "B";
    assert qu.size() == 0;

    assert qu.peek() == null;
    assert qu.dequeue() == null;
    assert qu.size() == 0;
  }
}