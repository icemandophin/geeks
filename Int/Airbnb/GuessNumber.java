/**
 * Connected to a server, there is a number from 1111 to 6666
 * Send your guess to the server, it will return you with how many numbers are correct and on the
 * right position, how many numbers are correct but on the wrong position
 * Guess as less as possible
 *
 * For example
 * correct code: 3264
 * GUESS 1111 => 0 0 (no correct digits)
 * GUESS 1214 => 2 0 (digits 2 and 4 are correct and on correct position)
 * GUESS 6111 => 0 1 (digit 6 is present, but on a different position)
 * GUESS 6211 => 1 1 (digit 2 is not counted towards the second count!)
 *
 * Solution
 * 从1111开始猜，每次改变1位，比如以最高位为例，第一个Iteration猜 2111，3111，4111，5111
 * 如果改一个数字正确的变少了，说明这一位就是1
 * 如果改一个数字正确的变多了，说明这一位就是你现在猜的数字
 * 如果正确的数字一直是一样的，说明这一位是6
 *
 * worst case是6666，最多需要猜
 * 1111
 * 2111，3111，4111，5111
 * 1211，1311，1411，1511
 * 1121，1131，1141，1151
 * 1112，1113，1114，1115
 * 共17次
 *
 * 参考 http://www.1point3acres.com/bbs/thread-290126-1-1.html
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GuessNumber {
  private int count = 0;
  private List<Integer> target = new ArrayList<>();

  // Simulation method, to generate or reset the random number, don't have to focus on it
  public void reset() {
    target.clear();
    for (int i = 0; i < 4; ++i) {
      target.add((int)(Math.random() * 6) + 1);
    }
    count = 0;
  }

  // Simulation method, don't have to focus it
  public String sendAndReceive(String str) {
    if (str.toLowerCase().equals("start")) {
      reset();
      return "Ready, target # is " + target.get(0) + target.get(1) + target.get(2) + target.get(3);
    }
    System.out.println("Times of method call: " + ++count + ", coming number: " + str);
    int a = 0;
    List<Integer> copyOfTarget = new ArrayList<>(target);
    List<Integer> t = new ArrayList<>();
    List<Integer> g = new ArrayList<>();

    for (int i = 0; i < 4; ++i) {
      int digit = copyOfTarget.get(i);
      char c = str.charAt(i);

      if (digit == c - '0') {
        ++a;
      } else {
        t.add(digit);
        g.add(c - '0');
      }
    }

    int size = g.size();
    g.removeAll(t);
    int b = size - g.size();

    return a + " " + b;
  }

  public String guess() {
    String base = "1111";
    int firstResp = Integer.parseInt(sendAndReceive(base).split(" ")[0]);
    if (firstResp == 4) {
      return base;
    }

    char[] res = new char[4];
    Arrays.fill(res, '0');
    for (int i = 0; i < 4; i++) {
      int lastResp = firstResp;
      char[] charBase = base.toCharArray();
      for (int j = 2; j < 6; j++) {
        charBase[i] = (char)('0' + j);
        int resp = Integer.parseInt(sendAndReceive(new String(charBase)).split(" ")[0]);
        if (resp == 4) {
          return new String(charBase);
        }
        if (resp != lastResp) {
          res[i] = lastResp > resp ? '1' : (char)('0' + j);
          break;
        }
      }
      if (res[i] == '0') {
        res[i] = '6';
      }
    }

    return new String(res);
  }
}

//class Main {
//  public static void main(String[] args) {
//    GuessNumber gn = new GuessNumber();
//    System.out.println(gn.sendAndReceive("start"));
//    System.out.println("Result: " + gn.guess());
//    System.out.println(gn.sendAndReceive("start"));
//    System.out.println("Result: " + gn.guess());
//    System.out.println(gn.sendAndReceive("start"));
//    System.out.println("Result: " + gn.guess());
//    System.out.println(gn.sendAndReceive("start"));
//    System.out.println("Result: " + gn.guess());
//  }
//}
