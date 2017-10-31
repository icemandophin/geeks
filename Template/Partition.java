/*
after the partition, returned pivot index divides array:
left [0 : pivot - 1], right [pivot + 1: N - 1]
*/
public int partition(int[] a, int l, int r) {
    // init left, right and pivot index
    int left = l;
    int right = r;
    // select random pivot and save its value in pivot
    int pivot = a[left];
    // partition array in place:
    // make left < pivot and right > pivot
    while (left < right) {
        // find 1st smaller element on right side
        while (left < right && a[right] >= pivot) {
            --right;
        }
        // swap and ensure left < pivot < right
        int trans = a[left];
        a[left] = a[right];
        a[right] = trans;
        // find 1st bigger element on left side
        while (left < right && a[left] <= pivot) {
            ++left;
        }
        // swap and ensure left < pivot < right
        trans = a[right];
        a[right] = a[left];
        a[left] = trans;
    }
    // put pivot value back to array
    a[left] = pivot;
    return left;
}
