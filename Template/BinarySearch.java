int top = 0;
int end = a.length - 1;
while (top + 1 < end) {
    int mid = top + (end - top) / 2;
    // check mid value is smaller/bigger/fit target X
    if (check(mid) < X) {
        top = mid;
    }
    else {
        end = mid;
    }
}
