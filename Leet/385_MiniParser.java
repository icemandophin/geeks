/*
iterative approach:
If a[0] != '[' => contains single number => return
for each '[' => need create new layer => push stack
for each digit => just move 2 pointers (start and i) to update cur number
for each ',' => cur number ends => add to cur layer result/stack top
for each ']' => cur layer is part of upper layer => add to cur layer result/stack top
             => ']' indicates cur layer end => pop Stack
*/
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
        Stack<NestedInteger> sk = new Stack<>();
        // a[0] == '[' => push empty nested list to stack
        sk.push(res);
        int start = 1;
        // traverse string array
        for (int i = 1; i < s.length(); ++i) {
            if (a[i] == '[') {
                // push empty list as stack top
                NestedInteger em = new NestedInteger();
                // NestedInteger em is part of upper layer
                // hence add to cur stack top
                // notice it is always pass by ref - res contains all sub obj
                sk.peek().add(em);
                sk.push(em);
                // move start to next index
                start = i + 1;
            }
            if (a[i] == ',' || a[i] == ']') {
                if (i - start > 0) {
                    // convert substring to integer => to nested integer
                    NestedInteger num = new NestedInteger(Integer.parseInt(s.substring(start, i)));
                    // add num to current layer/stack top
                    sk.peek().add(num);
                }
                // move start to next index
                // handle next NestedInteger
                start = i + 1;
                // current layer end
                if (a[i] == ']') {
                    sk.pop();
                }
            }
        }
        // results added to res during stack ops
        return res;
    }
}

/*
Recursive:
traverse string, each time took one layer of []
and recursively resolve remaining substring
for pure numbers, directly add to res with others in the same layer
*/
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
                    // mark # of layers - for each [ +1, for each ] -1
                    int cnt = 1;
                    // find index of the right ']' that match current ']'
                    while (cnt > 0) {
                        if (a[i + 1] == '[') {
                            cnt++;
                        } else if (a[i + 1] == ']') {
                            cnt--;
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
