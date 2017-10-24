
class Solution {
    public NestedInteger deserialize(String s) {
        if (s == null) {
            return null;
        }
        // lots of char op, covert first
        char[] a = s.toCharArray();
        // notice there is no space in NestedInteger
        // if s not start with '[' => there is only one integer in a[]
        if (a[0] != '[') {
            return new NestedInteger(Integer.parseInt(s));
        }
        NestedInteger res = new NestedInteger();
        int i = 1;
        int start = 1;
        // traverse string array
        while (i < a.length) {
            switch (a[i]) {
                case '[':
                    int left = 1;
                    while (left > 0) {
                        if (a[i + 1] == '[') {
                            left++;
                        } else if (a[i + 1] == ']') {
                            left--;
                        }
                        i++;
                    }
                    break;
                case ',':
                case ']':
                    if (i > start) {
                        // convert remaining part and add to res as NestedInteger
                        res.add(deserialize(s.substring(start, i)));
                    }
                    start = i + 1;
                    break;
                default:
                    break;
            }
            i++;
        }
        return res;
    }
}

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
