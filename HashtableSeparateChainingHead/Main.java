import java.io.*;
import java.util.regex.*;
import java.util.HashMap;

public class Main {

  public static void main(String[] args) throws java.io.IOException {
    Hashtable h = new Hashtable();
    HashMap<String, String> checker = new HashMap<String, String>();

    Pattern pattern = Pattern.compile("^\\*\\*\\*\\s+START\\s+OF\\s+THIS\\s+PROJECT.+");
    Pattern definitionPattern = Pattern.compile("^[A-Z]+$");

    Boolean startingParse = false;
    Boolean seenSpace = false;
    Boolean receivingDefinition = false;
    StringBuffer definition = null;
    String word = null;
    BufferedReader br = new BufferedReader(new FileReader("dictionary.txt"));
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

    for(String key : checker.keySet()) {
      String checkerResult = checker.get(key);
      String other = (String)h.get(key);

      if (checkerResult != other) {
        System.out.println("Failure at: " + key);
        System.out.println("\t" + checkerResult);
        System.out.println("-----");
        System.out.println("\t" + other);
      }
    }
  }

}
