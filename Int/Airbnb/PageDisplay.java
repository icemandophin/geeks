/*
"host_id,listing_id,score,city",
  "1,28,300.1,San Francisco",
  "4,5,209.1,San Francisco",
  "20,7,208.1,San Francisco",
  "23,8,207.1,San Francisco",
  "16,10,206.1,Oakland",
  "1,16,205.1,San Francisco",
  "1,31,204.6,San Francisco",
  "6,29,204.1,San Francisco",
  "7,20,203.1,San Francisco",
  "8,21,202.1,San Francisco",
  "2,18,201.1,San Francisco",
  "2,30,200.1,San Francisco",
  "15,27,109.1,Oakland",
  "10,13,108.1,Oakland",
  "11,26,107.1,Oakland",
  "12,9,106.1,Oakland",
  "13,1,105.1,Oakland",
  "22,17,104.1,Oakland",
  "1,2,103.1,Oakland",
  "28,24,102.1,Oakland",
  "18,14,11.1,San Jose",
  "6,25,10.1,Oakland",
  "19,15,9.1,San Jose",
  "3,19,8.1,San Jose",
  "3,11,7.1,Oakland",
  "27,12,6.1,Oakland",
  "1,3,5.1,Oakland",
  "25,4,4.1,San Jose",
  "5,6,3.1,San Jose",
  "29,22,2.1,San Jose",
  "30,23,1.1,San Jose"

Sample output
# "1,28,300.1,San Francisco",
# "4,5,209.1,San Francisco",
# "20,7,208.1,San Francisco",
# "23,8,207.1,San Francisco",
# "16,10,206.1,Oakland",
# "6,29,204.1,San Francisco",
# "7,20,203.1,San Francisco",
# "8,21,202.1,San Francisco",
# "2,18,201.1,San Francisco",
# "15,27,109.1,Oakland",
# "10,13,108.1,Oakland",
# "11,26,107.1,Oakland",

# "1,16,205.1,San Francisco",
# "2,30,200.1,San Francisco",
# "12,9,106.1,Oakland",
# "13,1,105.1,Oakland",
# "22,17,104.1,Oakland",
# "28,24,102.1,Oakland",
# "18,14,11.1,San Jose",
# "6,25,10.1,Oakland",
# "19,15,9.1,San Jose",
# "3,19,8.1,San Jose",
# "27,12,6.1,Oakland",
# "25,4,4.1,San Jose",

# "1,31,204.6,San Francisco",
# "1,2,103.1,Oakland",
# "3,11,7.1,Oakland",
# "1,3,5.1,Oakland",
# "5,6,3.1,San Jose",
# "29,22,2.1,San Jose",
# "30,23,1.1,San Jose"
*/


import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static void main(String[] args) {

    int perPage = 12;

    ArrayList<String> input = new ArrayList<String>();
    // input.add("host_id,listing_id,score,city");
    input.add("1,28,300.1,San Francisco");
    input.add("4,5,209.1,San Francisco");
    input.add("20,7,208.1,San Francisco");
    input.add("23,8,207.1,San Francisco");
    input.add("16,10,206.1,Oakland");
    input.add("1,16,205.1,San Francisco");
    input.add("1,31,204.6,San Francisco");
    input.add("6,29,204.1,San Francisco");
    input.add("7,20,203.1,San Francisco");
    input.add("8,21,202.1,San Francisco");
    input.add("2,18,201.1,San Francisco");
    input.add("2,30,200.1,San Francisco");
    input.add("15,27,109.1,Oakland");
    input.add("10,13,108.1,Oakland");
    input.add("11,26,107.1,Oakland");
    input.add("12,9,106.1,Oakland");
    input.add("13,1,105.1,Oakland");
    input.add("22,17,104.1,Oakland");
    input.add("1,2,103.1,Oakland");
    input.add("28,24,102.1,Oakland");
    input.add("18,14,11.1,San Jose");
    input.add("6,25,10.1,Oakland");
    input.add("19,15,9.1,San Jose");
    input.add("3,19,8.1,San Jose");
    input.add("3,11,7.1,Oakland");
    input.add("27,12,6.1,Oakland");
    input.add("1,3,5.1,Oakland");
    input.add("25,4,4.1,San Jose");
    input.add("5,6,3.1,San Jose");
    input.add("29,22,2.1,San Jose");
    input.add("30,23,1.1,San Jose");

    printPages(input, perPage);
  }

  public static void printPages(List<String> input, int k) {
      if (input == null || input.size() == 0) {
        return;
      }

      Iterator<String> iterator = input.iterator();
      int page = 1;
      Set<Integer> visited = new HashSet<>();

      System.out.println("Page: " + page);

      while (iterator.hasNext()) {
          String cur = iterator.next();
          int hostId = getHostId(cur);

          if (!visited.contains(hostId)) {
            System.out.println(cur);
            visited.add(hostId);
            iterator.remove();
          }

          if (visited.size() == k || !iterator.hasNext()) {
              // cur page ends - reset hashset and iterator
              visited.clear();
              iterator = input.iterator();

              if (!input.isEmpty()) {
                  // move to next page for remaining items
                  page++;
                  System.out.println("page: " + page);
              }

          }
      }
  }

  private static int getHostId(String line) {
      String[] fields = line.split(",");

      return Integer.parseInt(fields[0]);
  }
}
