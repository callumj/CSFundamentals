public class Node {
  public Object obj;
  public Node next;

  public void append(Node insert) {
    insertAfter(this, insert);
  }

  public void insertAfter(Node after, Node insert) {
    if (after == this) {
      insert.next = this.next;
      this.next = insert;
    } else if (this.next == null) {
      this.next = insert;
    } else {
      Node target = this.next;
      while (target != after) {
        if (target.next != null)
          target = target.next;
        else
          break;
      }

      insert.next = target.next;
      target.next = insert;
    }
  }

  public Node insertBeginning(Node insert) {
    return insertBefore(this, insert);
  }

  public Node insertBefore(Node before, Node insert) {
    if (before == this) {
      insert.next = this;
      return insert;
    } else {
      Node target = this;
      while (target.next != before) {
        if (target.next != null)
          target = target.next;
        else
          break;
      }

      insert.next = target.next;
      target.next = insert;
      return this;
    }
  }

  public Node remove(Node removal) {
    if (removal == this) {
      return this.next;
    } else {
      Node target = this;
      while (target.next != removal) {
        if (target.next != null)
          target = target.next;
        else
          break;
      }

      if (target.next == null)
        return this;

      target.next = target.next.next;
      return this;
    }
  }
}