public class BinaryTree {
  public Integer value;

  public BinaryTree left;
  public BinaryTree right;

  public int height;

  public BinaryTree(Integer value, BinaryTree left, BinaryTree right) {
    this.value = value;
    this.left = left;
    this.right = right;
    this.height = 1;
  }

  public boolean Find(Integer value) {
    return Find(value, 0);
  }

  public boolean Find(Integer value, int depth) {
    if (this.value == value) {
      System.out.format("Found %d, depth: %d\r\n", this.value, depth);
      return true;
    } else {
      BinaryTree target = this;

      while (target != null) {
        if (target.value == value) {
          System.out.format("Found %d, depth: %d\r\n", target.value, depth);
          return true;
        } else if (target.value > value) {
          target = target.left;
        } else {
          target = target.right;
        }
        depth = depth + 1;
      }
    }
    return false;
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

    recalculateHeight();

    // we would trigger a rebalance here
    rebalanceIfNeeded();
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

    recalculateHeight();

    // we would trigger a rebalance here
    rebalanceIfNeeded();
  }

  public BinaryTree MinNode() {
    if (this.left == null)
      return this;

    return this.left.MinNode();
  }

  public boolean isLeftHeavy() {
    if (this.left == null)
      return false;

    if (this.left != null && this.right == null)
      return true;
    
    return this.left.height > this.right.height;
  }

  public boolean isRightHeavy() {
    if (this.right == null)
      return false;

    if (this.right != null && this.left == null)
      return true;
    
    return this.right.height > this.left.height;
  }

  public void leftRotate() {
    Integer myValue = this.value;
    this.value = this.right.value;
    this.left = new BinaryTree(myValue, this.left, this.right.left);
    this.right = this.right.right;
  }

  public void rightRotate() {
    Integer myValue = this.value;
    this.value = this.left.value;
    this.right = new BinaryTree(myValue, this.left.right, this.right);
    this.left = this.left.left;
  }

  private void rebalanceIfNeeded() {
    int leftHeight = 0;
    int rightHeight = 0;
    
    if (this.left != null)
      leftHeight = this.left.height;

    if (this.right != null)
      rightHeight = this.right.height;

    int diff = leftHeight - rightHeight;

    if (diff >= 2) {
      //System.out.format("Tree is left heavy! Left: %d Right: %d\r\n", leftHeight, rightHeight);
      if (this.left.isRightHeavy()) {
        this.left.leftRotate();
        this.rightRotate();
      } else {
        this.rightRotate();
      }
    } else if (diff <= -2) {
      //System.out.format("Tree is right heavy! Left: %d Right: %d\r\n", leftHeight, rightHeight);
      if (this.right.isLeftHeavy()) {
        this.right.rightRotate();
        this.leftRotate();
      } else {
        this.leftRotate();
      }
    }
  }

  private void recalculateHeight() {
    if (this.left == null && this.right == null) {
      this.height = 1;
    } else if (this.left != null && this.right == null) {
      this.height = this.left.height + 1;
    } else if (this.right != null && this.left == null) {
      this.height = this.right.height + 1;
    } else {
      if (this.left.height > this.right.height)
        this.height = this.left.height + 1;
      else
        this.height = this.right.height + 1;
    }
  }
}