public class Main {

  public static void main(String[] args) {
    ArrayList h = new ArrayList();

    for(int i = 0; i < 1000; i++) {
      String str = Integer.toString(i);
      h.set(i, str);

      if (h.numberOfObjects() != (i + 1))
        System.out.println("Failure");
    }

    for(int i = 0; i < 1000; i++) {
      System.out.println(h.get(i));
    }

    for(int i = 999; i >= 0; i--) {
      h.set(i, null);

      if (h.numberOfObjects() != i)
        System.out.println("Failure");
    }

    h.set(12089, "Hello");
    h.set(1089, "Hello");
    h.set(12089, null);
  }
}