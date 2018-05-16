/**
 * https://en.wikipedia.org/wiki/Hilbert_curve
 * 希尔伯特曲线
 *
 * Calculate hc(x, y, iter)
 * x,y是坐标，iter是曲线迭代次数。
 *
 * Solution
 * - 找规律降迭代次数，分成四个象限，无论在那个迭代，曲线永远是 III -> II -> I -> IV
 * - 看当前坐标在第几象限，然后分别加上走过的象限点数，然后迭代数减一，确定新坐标，递归
 */
public class HilbertCurve {
  public int hc(int x, int y, int iter) {
    if (iter == 0) {
      return 1;
    }

    int len = (int)Math.pow(2, iter - 1);
    int areaCount = len * len;

    if (x < len && y < len) {
      // III
      return hc(y, x, iter - 1);
    } else if (x < len && y >= len) {
      // II
      return areaCount + hc(x, y - len, iter - 1);
    } else if (x >= len && y >= len) {
      // I
      return 2 * areaCount + hc(x - len, y - len, iter - 1);
    } else {
      // IV
      return 3 * areaCount + hc(len - 1 - y, 2 * len - 1 - x, iter - 1);
    }
  }
}

//class Main {
//  public static void main(String[] args) {
//    HilbertCurve hc = new HilbertCurve();
//    System.out.println(hc.hc(1,1,2));
//    System.out.println(hc.hc(0,1,1));
//    System.out.println(hc.hc(2,2,2));
//    System.out.println(hc.hc(1,3,2));
//    System.out.println(hc.hc(3,1,2));
//  }
//}
