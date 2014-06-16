public class TTFTree<E extends java.lang.Comparable<E>> {

  public E First;
  public E Second;
  public E Third;

  public TTFTree<E> LessFirst;

  public TTFTree<E> GreaterFirst;

  public TTFTree<E> GreaterSecond;

  public TTFTree<E> GreaterThird;

  public TTFTree() {
    this.First = null;
    this.Second = null;
    this.Third = null;
  }

  public void Insert(E object) {
    if (this.Second != null && object.compareTo(this.Second) == -1)
      BumpSecondUp();

    if (this.First != null && object.compareTo(this.First) == -1)
      BumpFirstUp();


    if (this.First == null) {
      this.First = object;
    } else {
      int compareFirst = object.compareTo(this.First);
      if (compareFirst == -1) {
        AddToLessThanFirst(object);
      } else if (compareFirst == 0) {
        this.First = object;
      } else {
        if (this.Second == null) {
          this.Second = object;
        } else {
          int compareSecond = object.compareTo(this.Second);
          if (compareSecond == -1) {
            AddToGreaterThanFirst(object);
          } else if (compareSecond == 0) {
            this.Second = object;
          } else {
            if (this.Third == null) {
              this.Third = object;
            } else {
              int compareThird = object.compareTo(this.Third);
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
    System.out.format("%sSelf:\r\n", paddingString);
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
  }

  private void AddToLessThanFirst(E object) {
    if (this.LessFirst == null)
      this.LessFirst = new TTFTree<E>();

    this.LessFirst.Insert(object);
  }

  private void AddToGreaterThanFirst(E object) {
    if (this.GreaterFirst == null)
      this.GreaterFirst = new TTFTree<E>();

    this.GreaterFirst.Insert(object);
  }

  private void AddToGreaterThanSecond(E object) {
    if (this.GreaterSecond == null)
      this.GreaterSecond = new TTFTree<E>();

    this.GreaterSecond.Insert(object);
  }

  private void AddToGreaterThanThird(E object) {
    if (this.GreaterThird == null)
      this.GreaterThird = new TTFTree<E>();

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
      this.GreaterFirst = this.GreaterSecond;
      this.LessFirst = this.GreaterFirst;
    }
  }

}