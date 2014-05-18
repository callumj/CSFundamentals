public class Main {

  public static void main(String[] args) {

    LinkedQueue qu = new LinkedQueue();
    doQueueyThings(qu);

    ArrayQueue qu2 = new ArrayQueue(6);
    doQueueyThings(qu2);

    while (qu2.dequeue() != null) {}

    qu2.enqueue("1");
    qu2.enqueue("2");
    qu2.enqueue("3");
    qu2.enqueue("4");
    qu2.enqueue("5");
    qu2.enqueue("6");

    assert qu2.dequeue() == "1";
    qu2.enqueue("7");

    assert qu2.dequeue() == "2";
    qu2.enqueue("8");

    assert qu2.dequeue() == "3";
    qu2.enqueue("9");

    assert qu2.dequeue() == "4";
    qu2.enqueue("10");

    assert qu2.dequeue() == "5";
    qu2.enqueue("11");

    assert qu2.dequeue() == "6";
    qu2.enqueue("12");

    assert qu2.dequeue() == "7";
    qu2.enqueue("13");

    assert qu2.dequeue() == "8";
    qu2.enqueue("14");

    assert qu2.dequeue() == "9";
    qu2.enqueue("15");

    assert qu2.dequeue() == "10";
    qu2.enqueue("16");
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