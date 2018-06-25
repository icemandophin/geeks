int top = 0;
int end = a.length - 1;
while (top <= end) {
    int mid = top + (end - top) / 2;
    // check mid value is smaller/bigger/fit target X
    if (check(mid) == X) {
        return found;
    } else if (check(mid) < X) {
        top = mid + 1;
    }
    else {
        end = mid - 1;
    }
    // if not found, cur end is the place to insert new
    return end;
}

// find first show of target x
// descrese right boundary when mid == x
// when exit loop top contains the target index
private int findLeft(int[] a, int x) {
    int top = 0;
    int end = a.length - 1;

    while (top <= end) {
        int mid = top + (end - top) / 2;

        if (a[mid] < x) {
            top = mid + 1;
        } else {
            // this includes a[mid] == x scenario
            // when end = top + 1 => mid = top
            // top points to 1st index when loop exit
            end = mid - 1;
        }
    }

    if (top < a.length && a[top] == x) {
        return top;
        } else {
        return -1;
    }
}

private int findRight(int[] a, int x) {
    int top = 0;
    int end = a.length - 1;

    while (top <= end) {
        int mid = top + (end - top) / 2;

        if (a[mid] > x) {
            end = mid - 1;
        } else {
            // move top forward when a[mid] == x
            // => end points to last index when loop exit
            top = mid + 1;
       }
    }

    if (a[end] == x) {
        return end;
    } else {
        return -1;
    }
}

