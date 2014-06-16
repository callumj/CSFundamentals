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

  public void Insert(E object) {
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
        AddToLessThanFirst(object);
      } else if (compareFirst == 0) {
        this.First = object;
      } else {
        if (this.Second == null && isLeaf()) {
          this.Second = object;
        } else {
          int compareSecond = this.Second == null ? -1 : object.compareTo(this.Second);
          if (compareSecond == -1) {
            AddToGreaterThanFirst(object);
          } else if (compareSecond == 0) {
            this.Second = object;
          } else {
            if (this.Third == null && isLeaf()) {
              this.Third = object;
            } else {
              int compareThird = this.Third == null ? -1 : object.compareTo(this.Third);
              if (compareThird == -1) {
                AddToGreaterThanSecond(object);
              } else if (compareThird == 0) {
                this.Third = object;
              } else {
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

  private void AddToLessThanFirst(E object) {
    if (this.LessFirst == null)
      this.LessFirst = new TTFTree<E>(false);

    this.LessFirst.Insert(object);
  }

  private void AddToGreaterThanFirst(E object) {
    if (this.GreaterFirst == null)
      this.GreaterFirst = new TTFTree<E>(false);

    this.GreaterFirst.Insert(object);
  }

  private void AddToGreaterThanSecond(E object) {
    if (this.GreaterSecond == null)
      this.GreaterSecond = new TTFTree<E>(false);

    this.GreaterSecond.Insert(object);
  }

  private void AddToGreaterThanThird(E object) {
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

  private void stealMiddleFromChild() {

  }

  private void reShuffleRootIfNeeded() {
    if (isRoot && nodesOnThisLevel() == 3) {
      E middle = this.Second;

      System.out.println(middle);

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