/*
detailed reference
*/
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;

        if (len % 2 == 0) {
            return (findKth(nums1, 0, nums2, 0, len / 2) + findKth(nums1, 0, nums2, 0, len / 2 + 1)) / 2.0;
        } else {
            return findKth(nums1, 0, nums2, 0, len / 2 + 1);
        }
    }

    private int findKth(int[] nums1, int i, int[] nums2, int j, int k) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        if (i >= len1) {
            return nums2[j + k - 1];
        }

        if (j >= len2) {
            return nums1[i + k - 1];
        }

        if (k == 1) {
            return Math.min(nums1[i], nums2[j]);
        }

        int idx1 = i + k / 2 - 1;
        int idx2 = j + k / 2 - 1;
        int num1 = idx1 < len1 ? nums1[idx1] : Integer.MAX_VALUE;
        int num2 = idx2 < len2 ? nums2[idx2] : Integer.MAX_VALUE;

        if (num1 < num2) {
            return findKth(nums1, idx1 + 1, nums2, j, k - k / 2);
        } else {
            return findKth(nums1, i, nums2, idx2 + 1, k - k / 2);
        }
    }
}
