import java.io.*;
import java.util.regex.*;
import java.util.HashMap;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) throws java.io.IOException {
    Hashtable h = new Hashtable();

    h.set("Hello", "Joe");

    for(int i = 0; i < 1000; i++) {
      String str = Integer.toString(i);
      h.set(str, str);
    }

    System.out.println(h.get("Hello"));

    for(int i = 0; i < 1000; i++) {
      String str = Integer.toString(i);
      System.out.println(h.get(str));
    }

    h = new Hashtable();

    performDictionaryCheck(h);
  }

  private static void performDictionaryCheck(Hashtable h) throws java.io.IOException {
    HashMap<String, String> checker = new HashMap<String, String>();

    Pattern pattern = Pattern.compile("^\\*\\*\\*\\s+START\\s+OF\\s+THIS\\s+PROJECT.+");
    Pattern definitionPattern = Pattern.compile("^[A-Z]+$");

    Boolean startingParse = false;
    Boolean seenSpace = false;
    Boolean receivingDefinition = false;
    StringBuffer definition = null;
    String word = null;
    BufferedReader br = new BufferedReader(new FileReader("dictionary.txt"));

    int words = 0;
    try {
      String line = br.readLine();
      while (line != null) {
        if (startingParse) {
          if (receivingDefinition && !definitionPattern.matcher(line).matches()) {
            definition.append(line);
          } else {
            if (seenSpace) {
              if (definitionPattern.matcher(line).matches()) {
                if (definition != null) {
                  // commit
                  words++;
                  String collapse = definition.toString();
                  h.set(word, collapse);
                  checker.put(word, collapse);
                  
                  if (h.get(word) != checker.get(word)) {
                    System.console();
                  }
                }

                receivingDefinition = true;
                word = line;
                definition = new StringBuffer();
              }
            }
          }
          seenSpace = line.length() == 0;
        } else {
          if (pattern.matcher(line).matches()) {
            startingParse = true;
          }
        }
        line = br.readLine();
      }
    } catch (Exception e) {

    } finally {
        br.close();
    }

    if (h.numberValues() != checker.values().size())
      System.out.println("Count incorrect.");

    ArrayList<Double> hTimes = new ArrayList<Double>(h.numberValues());
    ArrayList<Double> checkerTimes = new ArrayList<Double>(h.numberValues());

    for(String key : checker.keySet()) {
      double checkerStart = System.nanoTime();
      String checkerResult = checker.get(key);
      double checkerEnd = System.nanoTime() * 1.0;

      double hStart = System.nanoTime();
      String other = (String)h.get(key);
      double hEnd = System.nanoTime() * .10;

      double hDiff = (hEnd - hStart) / 1000.0;
      hTimes.add(hDiff);
      double checkerDiff = (checkerEnd - checkerStart) / 1000.0;
      checkerTimes.add(checkerDiff);

      if (checkerResult != other) {
        System.out.println("Failure at: " + key);
        System.out.println("\t" + checkerResult);
        System.out.println("-----");
        System.out.println("\t" + other);
      }
    }


    double hTotal = 0;
    for(Double val : hTimes)
      hTotal += val;

    double checkerTotal = 0;
    for(Double val : checkerTimes)
      checkerTotal += val;

    double hAvg = hTotal / Double.valueOf(hTimes.size());
    double checkerAvg = checkerTotal / Double.valueOf(checkerTimes.size());

    System.out.print("My avg time: " + hAvg);
    System.out.println("Java time: " + checkerAvg);
  }

}
