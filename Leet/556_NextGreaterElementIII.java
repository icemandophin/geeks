public class Solution {
    public int nextGreaterElement(int n) {
        char[] str = (n + "").toCharArray();

        int i = str.length - 2;

        while (i >= 0 && str[i] >= str[i + 1]) {
            i--;
        }

        if (i < 0) {
            return -1;
        }

        int j = str.length - 1;

        while (j >= 0 && str[j] <= str[i]) {
            j--;
        }

        swap(str, i, j);
        reverse(str, i + 1);

        try {
            return Integer.parseInt(new String(str));
        } catch (Exception e) {
            return -1;
        }
    }

    private void reverse(char[] str, int start) {
        int i = start;
        int j = str.length - 1;

        while (i < j) {
            swap(str, i, j);
            i++;
            j--;
        }
    }

    private void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }
}
