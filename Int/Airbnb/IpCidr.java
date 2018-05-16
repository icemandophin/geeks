/**
 * Given a start ip address and a number of ips we need to cover,
 * return a list of CIDR representation of the range.
 *
 * Example
 * Input: 10 IPs start from 255.0.0.7
 * Output: [255.0.0.7/32, 255.0.0.8/29, 255.0.0.16/32]
 */

import java.util.ArrayList;
import java.util.List;

public class IpCidr {
  public List<String> getCIDRs(String startIp, int range) {
    long start = ip2Long(startIp);
    long end = start + range - 1;

    List<String> res = new ArrayList<>();
    while (start <= end) {
      long maskCovered = start & (-start);
      int maskBits = (int)(Math.log(maskCovered) / Math.log(2));
      long remain = end - start + 1;
      int remainBits = (int)(Math.log(remain) / Math.log(2));

      StringBuilder sb = new StringBuilder();
      int actualBits = Math.min(maskBits, remainBits);
      res.add(sb.append(long2Ip(start)).append("/").append(32 - actualBits).toString());

      start += (long)Math.pow(2, actualBits);
    }

    return res;
  }

  // 256-based to 10-based
  private long ip2Long(String ip) {
    String[] parts = ip.split("\\.");
    long sum = 0;
    for (int i = 0; i < 4; i++) {
      sum += Long.parseLong(parts[i]);
      if (i < 3) {
        sum <<= 8;
      }
    }

    return sum;
  }

  // 10-based to 256-based
  private String long2Ip(long num) {
    StringBuilder sb = new StringBuilder();
    sb.append(num >> 24);
    sb.append(".");
    sb.append((num & 0x00FFFFFF) >> 16);
    sb.append(".");
    sb.append((num & 0x0000FFFF) >> 8);
    sb.append(".");
    sb.append((num & 0x000000FF));
    return sb.toString();
  }
}
