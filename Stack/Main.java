public class Main {

  public static void main(String[] args) {

    LinkedStack stk = new LinkedStack();
    doStackyThings(stk);

    ArrayStack stk2 = new ArrayStack(10);
    doStackyThings(stk2);

    while(stk2.pop() != null) {}

    assert stk2.count() == 0;

    for(int i = 1; i <= 10; i++) {
      stk2.push(i);
    }

    boolean error = false;
    try {
      stk2.push(11);
    } catch (java.nio.BufferOverflowException e) {
      error = true;
    }
    assert error == true;
  }

  public static void doStackyThings(Stack stk) {
    stk.push("6");
    assert stk.peek() == "6";
    assert stk.count() == 1;

    stk.push("5");
    assert stk.peek() == "5";
    assert stk.count() == 2;

    stk.push("4");
    assert stk.peek() == "4";
    assert stk.count() == 3;

    stk.push("3");
    assert stk.peek() == "3";
    assert stk.count() == 4;

    stk.push("2");
    assert stk.peek() == "2";
    assert stk.count() == 5;

    stk.push("1");
    assert stk.peek() == "1";
    assert stk.count() == 6;

    assert stk.pop() == "1";
    assert stk.peek() == "2";
    assert stk.count() == 5;

    assert stk.pop() == "2";
    assert stk.peek() == "3";
    assert stk.count() == 4;

    stk.swap();
    assert stk.peek() == "4";
    stk.swap();

    assert stk.pop() == "3";
    assert stk.peek() == "4";
    assert stk.count() == 3;

    assert stk.pop() == "4";
    assert stk.peek() == "5";
    assert stk.count() == 2;

    assert stk.pop() == "5";
    assert stk.peek() == "6";
    assert stk.count() == 1;

    assert stk.pop() == "6";
    assert stk.peek() == null;
    assert stk.count() == 0;

    assert stk.pop() == null;
    assert stk.count() == 0;

    stk.push("cucumber");
    stk.push("banana");
    stk.push("apple");

    assert stk.count() == 3;

    stk.rotateRight(3);

    assert stk.count() == 3;

    assert stk.pop() == "banana";
    assert stk.pop() == "cucumber";
    assert stk.pop() == "apple";

    assert stk.count() == 0;

    stk.push("apple");
    stk.push("banana");
    stk.push("cucumber");

    stk.rotateLeft(3);

    assert stk.count() == 3;

    assert stk.pop() == "apple";
    assert stk.pop() == "cucumber";
    assert stk.pop() == "banana";

    assert stk.count() == 0;
  }
}