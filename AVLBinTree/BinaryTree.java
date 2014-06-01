public class BinaryTree {
  public Integer value;

  public BinaryTree left;
  public BinaryTree right;

  public BinaryTree(Integer value, BinaryTree left, BinaryTree right) {
    this.value = value;
    this.left = left;
    this.right = right;
  }

  public void Find(Integer value) {
    Find(value, 1);
  }

  public void Find(Integer value, int tabAmount) {
    for(int i = 0; i < tabAmount; i++)
      System.out.print("\t");

    System.out.format("At %d", this.value);

    if (this.value == value) {
      System.out.println("Found!");
    } else if (this.value > value) {
      if (this.left != null) {
        System.out.println("Going left");
        this.left.Find(value, tabAmount + 1);
      }
      else
        System.out.println("Not found");
    } else {
      if (this.right != null) {
        System.out.println("Going right");
        this.right.Find(value, tabAmount + 1);
      }
      else
        System.out.println("Not found");
    }
  }

  public void Add(Integer value) {
    if (this.value == value)
      return;

    if (this.value == null) {
      this.value = value;
    } else {
      if (this.value > value) {
        if (this.left == null) {
          this.left = new BinaryTree(value, null, null);
        } else {
          this.left.Add(value);
        }
      } else {
        if (this.right == null) {
          this.right = new BinaryTree(value, null, null);
        } else {
          this.right.Add(value);
        }
      }
    }

    // we would trigger a rebalance here
  }

  public void Remove(Integer value) {
    if (this.value == null)
      return;

    if (this.value > value) {
      if (this.left != null)
        this.left.Remove(value);
    } else if (this.value < value) {
      if (this.right != null)
        this.right.Remove(value);
    } else {
      if (this.left != null && this.right != null) {
        BinaryTree node = this.right.MinNode();
        this.value = node.value;
        node.Remove(node.value);
      } else if (this.left != null) {
        this.value = this.left.value;
        BinaryTree existing = this.left;
        this.left = existing.left;
        this.right = existing.right;
      } else if (this.right != null) {
        this.value = this.right.value;
        BinaryTree existing = this.right;
        this.left = existing.left;
        this.right = existing.right;
      } else {
        this.value = null;
      }
    }

    if (this.left != null && this.left.value == null)
      this.left = null;

    if (this.right != null && this.right.value == null)
      this.right = null;
  }

  public BinaryTree MinNode() {
    if (this.left == null)
      return this;

    return this.left.MinNode();
  }
}