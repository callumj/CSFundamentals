public class TTFTree<E extends java.lang.Comparable<E>> {

  private boolean isRoot;

  public E First;
  public E Second;
  public E Third;

  public TTFTree<E> LessFirst;

  public TTFTree<E> GreaterFirst;

  public TTFTree<E> GreaterSecond;

  public TTFTree<E> GreaterThird;

  public TTFTree(boolean isRoot) {
    this.isRoot = isRoot;
    this.First = null;
    this.Second = null;
    this.Third = null;
  }

  public TTFTree() {
    this(true);
  }

  public int nodesOnThisLevel() {
    int count = 0;
    if (this.First != null)
      count +=1;

    if (this.Second != null)
      count +=1;

    if (this.Third != null)
      count +=1;

    return count;
  }

  public boolean isLeaf() {
    return this.LessFirst == null && this.GreaterFirst == null && this.GreaterSecond == null && this.GreaterThird == null;
  }

  public void Insert(E object) throws Exception {
    reShuffleRootIfNeeded();

    if (isLeaf()) {
      if (this.Second != null && object.compareTo(this.Second) == -1)
        BumpSecondUp();

      if (this.First != null && object.compareTo(this.First) == -1)
        BumpFirstUp();
    }

    if (this.First == null) {
      this.First = object;
    } else {
      int compareFirst = object.compareTo(this.First);
      if (compareFirst == -1) {
        boolean restart = stealMiddleFromChild(this.LessFirst);
        if (restart) {
          this.Insert(object);
          return;
        }
        AddToLessThanFirst(object);
      } else if (compareFirst == 0) {
        this.First = object;
      } else {
        if (this.Second == null && isLeaf()) {
          this.Second = object;
        } else {
          int compareSecond = this.Second == null ? -1 : object.compareTo(this.Second);
          if (compareSecond == -1) {
            boolean restart = stealMiddleFromChild(this.GreaterFirst);
            if (restart) {
              this.Insert(object);
              return;
            }
            AddToGreaterThanFirst(object);
          } else if (compareSecond == 0) {
            this.Second = object;
          } else {
            if (this.Third == null && isLeaf()) {
              this.Third = object;
            } else {
              int compareThird = this.Third == null ? -1 : object.compareTo(this.Third);
              if (compareThird == -1) {
                boolean restart = stealMiddleFromChild(this.GreaterSecond);
                if (restart) {
                  this.Insert(object);
                  return;
                }
                AddToGreaterThanSecond(object);
              } else if (compareThird == 0) {
                this.Third = object;
              } else {
                boolean restart = stealMiddleFromChild(this.GreaterThird);
                if (restart) {
                  this.Insert(object);
                  return;
                }
                AddToGreaterThanThird(object);
              }
            }
          }
        }
      }
    }
  }

  public void Print() {
    Print(0);
  }

  public void Print(int padding) {
    String paddingString = "";
    for (int i = 0; i < padding; i++)
      paddingString += " ";

    String describeMe = isRoot ? "Root" : "Self";
    System.out.format("%s%s:\r\n", paddingString, describeMe);
    System.out.format("%s First: %s\r\n", paddingString, this.First == null ? "(null)" : this.First.toString());
    System.out.format("%s Second: %s\r\n", paddingString, this.Second == null ? "(null)" : this.Second.toString());
    System.out.format("%s Third: %s\r\n", paddingString, this.Third == null ? "(null)" : this.Third.toString());

    System.out.println();
    System.out.format("%sChildren:\r\n", paddingString);
    System.out.format("%s Left First:\r\n", paddingString);
    if (this.LessFirst != null) {
      this.LessFirst.Print(padding + 3);
    } else {
      System.out.format("%s  (null)\r\n", paddingString);
    }

    System.out.format("%s Left Second:\r\n", paddingString);
    if (this.GreaterFirst != null) {
      this.GreaterFirst.Print(padding + 3);
    } else {
      System.out.format("%s  (null)\r\n", paddingString);
    }

    System.out.format("%s Left Third:\r\n", paddingString);
    if (this.GreaterSecond != null) {
      this.GreaterSecond.Print(padding + 3);
    } else {
      System.out.format("%s  (null)\r\n", paddingString);
    }

    System.out.format("%s Right Third:\r\n", paddingString);
    if (this.GreaterThird != null) {
      this.GreaterThird.Print(padding + 3);
    } else {
      System.out.format("%s  (null)\r\n", paddingString);
    }
  }

  private void AddToLessThanFirst(E object) throws Exception {
    if (this.LessFirst == null)
      this.LessFirst = new TTFTree<E>(false);

    this.LessFirst.Insert(object);
  }

  private void AddToGreaterThanFirst(E object) throws Exception {
    if (this.GreaterFirst == null)
      this.GreaterFirst = new TTFTree<E>(false);

    this.GreaterFirst.Insert(object);
  }

  private void AddToGreaterThanSecond(E object) throws Exception {
    if (this.GreaterSecond == null)
      this.GreaterSecond = new TTFTree<E>(false);

    this.GreaterSecond.Insert(object);
  }

  private void AddToGreaterThanThird(E object) throws Exception {
    if (this.GreaterThird == null)
      this.GreaterThird = new TTFTree<E>(false);

    this.GreaterThird.Insert(object);
  }

  private void BumpSecondUp() {
    if (this.Third == null) {
      this.Third = this.Second;
      this.Second = null;
      this.GreaterSecond = this.GreaterThird;
      this.GreaterFirst = this.GreaterSecond;
    }
  }

  private void BumpFirstUp() {
    if (this.Second == null) {
      this.Second = this.First;
      this.First = null;
      this.GreaterSecond = this.GreaterFirst;
      this.GreaterFirst = this.LessFirst;
      this.LessFirst = null;
    }
  }

  private boolean stealMiddleFromChild(TTFTree<E> target) throws Exception {
    if (target == null)
      return false;

    if (target.nodesOnThisLevel() == 3) {
      E middleValue = target.Second;
      TTFTree<E> splitLeft = new TTFTree<E>(false);
      splitLeft.First = target.First;

      TTFTree<E> splitRight = new TTFTree<E>(false);
      splitRight.First = target.Third;

      System.out.println("Starting steal!");

      if (target == this.GreaterThird) {
        throw new Exception("I should not be a internal node with 3 nodes!");
      } else if (target == this.GreaterSecond) {
        // middle becomes third
        this.Third = middleValue;
        this.GreaterThird = splitRight;
        this.GreaterSecond = splitLeft;
        return true;
      } else if (target == this.GreaterFirst) {
        // middle becomes second
        this.Third = this.Second;
        this.GreaterThird = this.GreaterSecond;
        this.Second = middleValue;
        this.GreaterFirst = splitLeft;
        this.GreaterSecond = splitRight;
        return true;
      } else {
        // middle becomes first
        this.Third = this.Second;
        this.GreaterThird = this.GreaterSecond;
        this.Second = this.First;
        this.GreaterSecond = this.GreaterFirst;

        this.First = middleValue;
        this.LessFirst = splitLeft;
        this.GreaterFirst = splitRight;
        return true;
      }
    }

    return false;
  }

  private void reShuffleRootIfNeeded() {
    if (isRoot && nodesOnThisLevel() == 3) {
      E middle = this.Second;

      TTFTree<E> left = new TTFTree<E>(false);
      TTFTree<E> right = new TTFTree<E>(false);

      left.First = this.First;
      left.LessFirst = this.LessFirst;
      left.GreaterFirst = this.GreaterFirst;

      right.First = this.Third;
      right.LessFirst = this.GreaterSecond;
      right.GreaterFirst = this.GreaterThird;

      this.First = middle;
      this.Second = null;
      this.Third = null;
      this.LessFirst = left;
      this.GreaterFirst = right;
      this.GreaterSecond = null;
      this.GreaterThird = null;
    }
  }

}