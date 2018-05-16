/**
 * 具体的CSV格式规则可以看这个http://creativyst.com/Doc/Articles/CSV/CSV01.htm#EmbedBRs
 *
 * Example:
 * John,Smith,john.smith@gmail.com,Los Angeles,1
 *     => John|Smith|john.smith@gmail.com|Los Angeles|1
 *
 * Jane,Roberts,janer@msn.com,"San Francisco, CA",0
 *     => Jane|Roberts|janer@msn.com|San Francisco, CA|0
 *
 * "Alexandra ""Alex""",Menendez,alex.menendez@gmail.com,Miami,1
 *     => Alexandra "Alex"|Menendez|alex.menendez@gmail.com|Miami|1
 *
 * """Alexandra Alex"""
 *     => "Alexandra Alex"
 */

import java.util.ArrayList;
import java.util.List;

public class CSVParser {
  public String parseCSV(String str) {
    List<String> items = new ArrayList<>();
    boolean inQuote = false;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < str.length(); i++) {
      if (inQuote) {
        if (str.charAt(i) == '\"') {
          if (i < str.length() - 1 && str.charAt(i + 1) == '\"') {
            sb.append("\"");
            i++;
          } else {
            inQuote = false;
          }
        } else {
          sb.append(str.charAt(i));
        }
      } else {
        if (str.charAt(i) == '\"') {
          inQuote = true;
        } else if (str.charAt(i) == ',') {
          items.add(sb.toString());
          sb.setLength(0);
        } else {
          sb.append(str.charAt(i));
        }
      }
    }

    if (sb.length() > 0) {
      items.add(sb.toString());
    }
    return String.join("|", items);
  }
}

//class Main {
//  public static void main(String[] args) {
//    CSVParser p = new CSVParser();
//    System.out.println(p.parseCSV("\"\"\"Alexandra Alex\"\"\""));
//    System.out.println(p.parseCSV("\"Alexandra \"\"Alex\"\"\",Menendez,alex.menendez@gmail.com,Miami,1"));
//    System.out.println(p.parseCSV("Jane,Roberts,janer@msn.com,\"San Francisco, CA\",0"));
//    System.out.println(p.parseCSV("\"\"\"Alexandra\"\"\""));
//  }
//}
