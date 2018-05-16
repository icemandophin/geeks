/**
 * 往一个int array 代表海拔的格子里倒水，打印出倒水后的图， 输入：int[] 海拔， int 水数量， int 倒得位置
 *
 * For example:
 * heights = [5,4,2,1,2,3,2,1,0,1,2,4]
 *
 * +
 * ++         +
 * ++   +     +
 * +++ +++   ++
 * ++++++++ +++
 *
 * Pour 8 units of water at index 5, then output
 *
 *      |
 *      v
 * 0123456789
 * +
 * ++         +
 * ++www+     +
 * +++w+++www++
 * ++++++++w+++
 *
 * 可以和面试官讨论assumptions
 * - 水滴优先往左流，没地流再往右流，也没地了就在当前位置涨
 * - 两边有无限高的墙挡着
 * - 水滴是一滴一滴的，不能分为小数，所以一滴水会一直往左走到尽头（其实是不符合物理规则的但理他呢。。）
 */
public class PourWater {
  public void pourWater(int[] heights, int water, int location) {
    int[] waters = new int[heights.length];
    int pourLocation;

    while (water > 0) {
      int left = location - 1;
      while (left >= 0) {
        if (heights[left] + waters[left] > heights[left + 1] + waters[left + 1]) {
          break;
        }
        left--;
      }
      if (heights[left + 1] + waters[left + 1] < heights[location] + waters[location]) {
        pourLocation = left + 1;
        waters[pourLocation]++;
        water--;
        continue;
      }

      int right = location + 1;
      while (right < heights.length) {
        if (heights[right] + waters[right] > heights[right - 1] + waters[right - 1]) {
          break;
        }
        right++;
      }
      if (heights[right - 1] + waters[right - 1] < heights[location] + waters[location]) {
        pourLocation = right - 1;
        waters[pourLocation]++;
        water--;
        continue;
      }

      pourLocation = location;
      waters[pourLocation]++;
      water--;
    }

    print(heights, waters);
  }

  private void print(int[] heights, int[] waters) {
    int n = heights.length;

    int maxHeight = 0;
    for (int i = 0; i < n; i++) {
      maxHeight = Math.max(maxHeight, heights[i] + waters[i]);
    }

    for (int height = maxHeight; height >= 0; height--) {
      for (int i = 0; i < n; i++) {
        if (height <= heights[i]) {
          System.out.print("+");
        } else if (height > heights[i] && height <= heights[i] + waters[i]) {
          System.out.print("W");
        } else {
          System.out.print(" ");
        }
      }
      System.out.println();
    }
    System.out.println();
  }
}

//public class Main {
//  public static void main(String[] args) {
//    PourWater pw = new PourWater();
//    int[] heights = {5,4,2,1,2,3,2,1,0,1,2,4};
//    for (int i = 1; i <= 9; i++) {
//      pw.pourWater(heights, i, 5);
//    }
//  }
//}
