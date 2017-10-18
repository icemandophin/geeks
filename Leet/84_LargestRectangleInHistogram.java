/*
ascending stack approach: each element push & pop one time - O(n)
reference:
http://www.cnblogs.com/lichen782/p/leetcode_Largest_Rectangle_in_Histogram.html
*/
public class Solution {
    public int largestRectangleArea(int[] h) {
        // write your code here
        Stack<Integer> sk = new Stack<Integer>();
        int N = h.length;
        int res = 0;
        int i, cur;
        // after traverse to last element h[N-1]
        // there can be remaining elements in stack
        // need to add fake element -1 at the end of array to pop them out
        // and calculate max rectangle them can make
        // hence i will take N-th iteration
        for (i = 0; i <= N; ++i) {
            // take fake -1 (any negative) to cleanup stack remainings
            cur = (i == N) ? -1 : h[i];
            while (!sk.isEmpty() && cur <= h[sk.peek()]) {
                // one edge is height of stack top element
                int top = sk.pop();
                // if stack is empty now, then h[top] is smallest bar so far
                // it can make rectangle back to bar h[0] => len = i
                // else out-boundary for 2nd edge is (sk.peek(), i)
                // hence its length is i-sk.peek()-1
                // Notice: have to use out-boundary to calculate len
                int len = (sk.isEmpty()) ? i : i - sk.peek() - 1;
                // update max rectangle area
                res = Math.max(res, h[top] * len);
            }
            // while stack empty or ascending, just push new elements to stack
            sk.push(i);
        }
        return res;
    }
}
