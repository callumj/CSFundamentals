public class Hashtable {

  private int DEFAULT_SIZE = 97;

  public int usedBuckets;
  public int activeObjects;
  public Item[] table;

  public Hashtable() {
    this.table = new Item[DEFAULT_SIZE];
    this.usedBuckets = 0;
    this.activeObjects = 0;
  }

  public Object get(Object key) {
    int bucketIndex = computeHash(key);
    if (this.table[bucketIndex] != null)
      return this.table[bucketIndex].getObject(key);
    else
      return null;
  }

  public void set(Object key, Object value) {
    int bucketIndex = computeHash(key);
    if (this.table[bucketIndex] == null)
      this.table[bucketIndex] = new Item(key, value);
    else
      this.table[bucketIndex].setObject(key, value);
  }

  public void printStats() {
    int index = 0;
    for(Item bucket : this.table) {
      System.out.println(index + ": " + (bucket == null ? "empty" : bucket.objectCount));

      index++;
    }
  }

  private int computeHash(Object key) {
    int hashCode = key.hashCode();
    return Math.abs(hashCode) % this.table.length;
  }

}