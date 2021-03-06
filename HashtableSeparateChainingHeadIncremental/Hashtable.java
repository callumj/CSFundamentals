public class Hashtable {

  private int DEFAULT_SIZE = 97;

  public Item[] table;
  public Item[] futureTable; // indicates a table being moved to

  public Item[] settingTable;

  public Hashtable() {
    this.table = new Item[DEFAULT_SIZE];
    this.settingTable = this.table;
  }

  public Object get(Object key) {
    int bucketIndex = computeHash(key, isTwoTableSetup());

    Object result = null;
    if (this.settingTable[bucketIndex] != null)
      result = this.settingTable[bucketIndex].getObject(key);

      // fallback
    if (result == null && isTwoTableSetup()) {
      bucketIndex = computeHash(key, false);
      if (this.table[bucketIndex] != null)
        return this.table[bucketIndex].getObject(key);
    }

    return result;
  }

  public void set(Object key, Object value) {
    this.set(key, value, false, false);
  }

  private void set(Object key, Object value, boolean doNotResize, boolean noOverWrite) {
    int bucketIndex = computeHash(key, isTwoTableSetup());
    if (this.settingTable[bucketIndex] == null)
      this.settingTable[bucketIndex] = new Item(key, value);
    else
      this.settingTable[bucketIndex].setObject(key, value, noOverWrite);

    if (this.settingTable[bucketIndex].obj == null && this.settingTable[bucketIndex].next != null)
      this.settingTable[bucketIndex] = this.settingTable[bucketIndex].next;

    if (!doNotResize)
      this.resizeIfNeeded();
  }

  public int numberValues() {
    int base = this.valueCount(false);
    if (this.futureTable != null)
      base += this.valueCount(true);

    return base;
  }

  private int valueCount() {
    return this.valueCount(false);
  }

  private int valueCount(boolean useFuture) {
    int count = 0;
    for (Item bucket : (useFuture ? this.futureTable : this.table)) {
      if (bucket != null)
        count += bucket.objectCount;
    }
    return count;
  }

  public void printStats() {
    int index = 0;
    for (Item bucket : this.table) {
      System.out.println(index + ": " + (bucket == null ? "empty" : bucket.objectCount));

      index++;
    }
  }

  private void resizeIfNeeded() {
    int curCount = this.valueCount(false);
    if (curCount == 0 && this.futureTable != null && this.futureTable != this.table) {
      this.table = this.futureTable;
      this.settingTable = this.table;
      this.futureTable = null;
      curCount = this.valueCount(false);
    } else if (curCount == 0) {
      return;
    }

    if (((curCount * 1.0) / (this.settingTable.length * 1.0)) >= 0.75) {
          // resize!
      if (this.settingTable == this.table) {
        this.futureTable = new Item[this.table.length * 2];
        this.settingTable = this.futureTable;
      } else {
        System.out.println("\tNeed another resizing");
        // our future table has already outgrown, take evasive action
      }
    }

    incrementallyCopy(-1);
  }

  private void incrementallyCopy(int currentAmount) {
    if (this.futureTable == null)
      return;

    if (currentAmount == -1)
      currentAmount = this.valueCount(false);

    if (currentAmount == 0)
      return;

    int amountToCopy = ((int) Math.round(currentAmount * 0.5)) + 1;

    if (amountToCopy == 0) {
      return;
    }

    int index = 0;
    Item target = this.table[index];
    while (amountToCopy > 0) {
      if (target == null) {
        index++;
        if (index == this.table.length)
          break;
        target = this.table[index];
      } else {
        Item[] received = target.removeObjects(amountToCopy);

        for (Item item : received) {
          if (item == null || item.obj == null)
            continue;

          this.set(item.key, item.obj, true, true);
          amountToCopy--;
        }

        this.table[index] = target.next;
        this.set(target.key, target.obj, true, true);
        amountToCopy--;

        target = this.table[index];
      }
    }
  }

  private int computeHash(Object key, boolean useFuture) {
    Item[] target;
    if (useFuture)
      target = this.futureTable;
    else
      target = this.table;
    int hashCode = key.hashCode();
    return Math.abs(hashCode) % target.length;
  }

  private boolean isTwoTableSetup() {
    return this.settingTable != this.table;
  }

}