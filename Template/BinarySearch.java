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
