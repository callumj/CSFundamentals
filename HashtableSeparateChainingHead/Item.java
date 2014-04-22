public class Item {
  public int hashCode;
  public Object key;
  public Object obj;
  public Item next;

  public int objectCount;

  public Item(Object key, Object obj) {
    this.hashCode = key.hashCode();
    this.key = key;
    this.obj = obj;
    if (obj != null)
      this.objectCount = 1;
  }

  public void setObject(Object incomingKey, Object incomingObj) {
    applyObject(incomingKey, incomingObj);
    
    // sync count
    if (this.obj == null)
      this.objectCount = 0;
    else
      this.objectCount = 1;
    
    if (this.next != null)
      this.objectCount += this.next.objectCount;
  }

  public Object getObject(Object lookingKey) {
    int incomingHashCode = lookingKey.hashCode();
    if (this.hashCode == incomingHashCode && (lookingKey == this.key || this.key.equals(lookingKey)))
    {      
      return obj;
    }
    else if (this.next != null)
      return this.next.getObject(lookingKey);
    else
      return null;
  }
  
  private void applyObject(Object incomingKey, Object incomingObj) {
    int incomingHashCode = incomingKey.hashCode();
    if (this.hashCode == incomingHashCode && (incomingKey == this.key || this.key.equals(incomingKey))) {
      this.obj = incomingObj;
    } else {
      if (this.hashCode < incomingHashCode) {

        if (incomingObj == null)
          return;

        Object movingKey = this.key;
        Object movingObject = this.obj;

        if (this.obj != null) {
          if (this.next == null) {
            this.next = new Item(movingKey, movingObject);
          } else {
            this.next.setObject(movingKey, movingObject);
          }
        }

        this.hashCode = incomingHashCode;
        this.key = incomingKey;
        this.obj = incomingObj;
      } else {
        if (this.next == null) {
          if (incomingObj == null)
            return;
          this.next = new Item(incomingKey, incomingObj);
        } else {
          if (incomingObj == null && incomingHashCode == this.next.hashCode) {
            if (this.next.next == null) {
              this.next = null;
            } else {
              this.next = this.next.next;
              return;
            }
          } else {
            this.next.setObject(incomingKey, incomingObj);
          }
        }
      }
    }
  }
}