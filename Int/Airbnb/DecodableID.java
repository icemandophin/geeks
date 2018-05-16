/**
 * Often, we want to encode raw IDs in our database by hiding them behind some
 * 2-way decodeable hash. So, a URL which would have at one time been:
 *
 * https://www.airbnb.com/rooms/848662
 *
 * becomes
 *
 * https://www.airbnb.com/rooms/kljJJ324hjkS_
 *
 * We decode the ID kljJJ324hjkS_ to 848662 on our backend and serve the
 * relevant content. at some point, we start getting 404 errors from clients
 * requesting a certain URL of the form
 *
 * https://www.airbnb.com/rooms/kljjj324hjks_
 *
 * This can happen if certain clients, email services, or url shorteners "sanitize" the url.
 * Unfortunately, this change breaks decoding and the resource cannot be found.
 * To assess how big of a deal this is, we may want to recover the IDs of the targets that were 404ing.
 *
 * Your task:
 * Given a method decode(testEncStr) which will return the decoded int id if
 * testEncStr is decodeable or will throw an exception or return null (depending on the language)
 * if not, implement a new method decodeFind(String badEncStr) which takes a string and
 * returns the decoded int id.
 */
public class DecodableID {
  public Integer decode(String testEncStr) {
    return testEncStr.equals("kljJJ324hjkS_") ? 848662 : null;
  }

  public Integer decodeFind(String badEncStr) {
    if (badEncStr == null || badEncStr.length() == 0) {
      return -1;
    }
    return helper(badEncStr, 0, new StringBuilder());
  }

  private Integer helper(String str, int start, StringBuilder sb) {
    if (start == str.length()) {
      return decode(sb.toString());
    }

    char c = str.charAt(start);
    if (Character.isDigit(c)) {
      sb.append(c);
      Integer res = helper(str, start + 1, sb);
      if (res != null) {
        return res;
      }
      sb.setLength(sb.length() - 1);
    } else {
      sb.append(Character.toLowerCase(c));
      Integer res = helper(str, start + 1, sb);
      if (res != null) {
        return res;
      }
      sb.setLength(sb.length() - 1);
      sb.append(Character.toUpperCase(c));
      res = helper(str, start + 1, sb);
      if (res != null) {
        return res;
      }
      sb.setLength(sb.length() - 1);
    }

    return null;
  }
}

//class Main {
//  public static void main(String[] args) {
//    DecodableID did = new DecodableID();
//    System.out.println(did.decodeFind("kljjj324hjks_"));
//    System.out.println(did.decodeFind("kljjj324hjkd_"));
//  }
//}
