/*
Binary search for sqrt root
Pay attention to int overflow => no mid*mid but x/mid
(x/2 + 1)(x/2 + 1) >= x, hence right boundary can be x/2 + 1
*/

public int sqrt(int x) {
    if (x == 0)
        return 0;
    int left = 1, right = Integer.MAX_VALUE;
    while (true) {
        int mid = left + (right - left)/2;
        if (mid > x/mid) {
            right = mid - 1;
        } else {
            if (mid + 1 > x/(mid + 1))
                return mid;
            left = mid + 1;
        }
    }
}
