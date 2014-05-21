public class Hanoi {
  private int count;
  public Stack a;
  public Stack b;
  public Stack c;

  public Hanoi(int n) {
    this.a = new Stack();
    this.b = new Stack();
    this.c = new Stack();

    this.count = n;
    for(int index = n; index > 0; index--)
      a.push(index);
  }

  public void run() {
    Integer last;
    if (this.count % 2 == 0) {
      b.push(a.pop());
      c.push(a.pop());
      c.push(b.pop());
      last = (Integer)c.peek();
    } else {
      c.push(a.pop());
      b.push(a.pop());
      b.push(c.pop());
      last = (Integer)b.peek();
    }

    int iteration = 0;
    while(c.size() != count) {
      iteration += 1;
      int remaining = ((int)Math.pow(2, count) - 4);
      int total = remaining - iteration;
      if (iteration % 1000000 == 0) {
        System.out.println(total);
      }

      if (total <= 0)
        return;
      while(true) {
        java.util.ArrayList<Integer> used = new java.util.ArrayList<Integer>(3);

        Stack smallestFrom = null;
        Integer smallest = null;
        for (int i = 0; i < 3; i++) {
          Stack cur = this.get(i);

          if (cur.peek() == null)
            continue;

          if (cur.peek() == null || cur.peek() == last || used.contains((Integer)cur.peek()))
            continue;
          else if (smallest == null) {
            smallestFrom = cur;
            smallest = (Integer)cur.pop();
          } else if (smallest > (Integer)cur.peek()) {
            smallestFrom.push(smallest);

            smallest = (Integer)cur.pop();
            smallestFrom = cur;
          }
        }

        boolean success = false;
        Stack blank = null;
        Stack target = null;
        for (int i = 0; i < 3; i++) {
          Stack look = get(i);
          if (look == smallestFrom)
            continue;

          if (look.peek() != null && ((Integer)look.peek()) < smallest)
            continue;

          if (look.peek() != null && ((((Integer)look.peek()) % 2 == 0 && smallest % 2 == 0) || 
                                      (((Integer)look.peek()) % 2 != 0 && smallest % 2 != 0)))
            continue;

          if (look.peek() == null) {
            blank = look;
            continue;
          }

          success = true;
          target = look;
          break;
        }

        if (!success)
          target = blank;

        target.push(smallest);
        last = smallest;
        success = true;


        if (success)
          break;
        else {
          smallestFrom.push(smallest);
          used.add(smallest);
        }
      }
    }
  }

  public Stack get(int n) {
    switch (n) {
      case 0: return a;
      case 1: return b;
      case 2: return c;
    }
    return null;
  }

  public void printStacks() {
    for(int i = 0; i < 3; i++) {
      System.out.println(String.format("----%d----", i));
      Stack cur = get(i);

      Stack copy = new Stack();
      while(cur.size() != 0) {
        Object out = cur.pop();
        copy.push(out);
        System.out.println(out);
      }

      while (copy.size() != 0) {
        Object out = copy.pop();
        cur.push(out);
      }

      System.out.println("\r\n");
    }
  }
}