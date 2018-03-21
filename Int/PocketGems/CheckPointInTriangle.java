import java.util.*;

public class Solution {
	public static void main(String[] args) {
        Pair a = new Pair(0, 0);
        Pair b = new Pair(0, 5);
        Pair c = new Pair(5, 0);
        Pair t = new Pair(2, 2);
        System.out.println(CheckTriangle.pointInTriangle(a, b, c, t));
	}
}

class CheckTriangle {
    public static boolean pointInTriangle(Pair a, Pair b, Pair c, Pair t) {
        boolean abt = side(a, b, t) >= 0;
        boolean bct = side(b, c, t) >= 0;
        boolean cat = side(c, a, t) >= 0;

        return abt && bct && cat;
    }

    private static int side(Pair a, Pair b, Pair t) {
        return (b.y - a.y) * (t.x - a.x) + (t.y - a.y) * (a.x - b.x);
    }
}

class Pair {
    int x;
    int y;

    public Pair(int a, int b) {
        x = a;
        y = b;
    }
}
