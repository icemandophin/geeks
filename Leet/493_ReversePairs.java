/*
merge sort approach:
1. cut array into 2 recursively
2. merge left and right: index left < right
if a[left] > a[right] => all following left is bigger than right
=> there are mid - left + 1 pairs for cur a[right]

*/
/**
 * @param A an array
 * @return total of reverse pairs
 */
public long reversePairs(int[] A) {
    // Write your code here
    if(A == null || A.length == 0){
        return 0;
    }

    return mergeSort(A, 0, A.length - 1);
}

private long mergeSort(int[] A, int start, int end){
    if(start >= end){
        return 0;
    }

    long sum = 0;
    int mid = (start + end) / 2;
    sum += mergeSort(A, start, mid);
    sum += mergeSort(A, mid + 1, end);
    sum += merge(A, start, mid, end);

    return sum;
}

private long merge(int[] A, int start, int mid, int end) {
        int[] temp = new int[A.length];
        int left = start;
        int right = mid + 1;
        int index = start;
        long sum = 0;

        while (left <= mid && right <= end) {
            if (A[left] <= A[right]) {
                temp[index++] = A[left++];
            } else {
                // reverse pair found
                temp[index++] = A[right++];
                sum += mid - left + 1;
            }
        }

        while (left <= mid) {
            temp[index++] = A[left++];
        }

        while (right <= end) {
            temp[index++] = A[right++];
        }

        for(int i = start; i <= end; i++){
            A[i] = temp[i];
        }

        return sum;
    }
}
