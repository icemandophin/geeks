/*
iterative approach:
one stack for cnt, the other for str
'[': push cnt and left part
']': pop cur str and cnt, build repeating str and push back
*/
class Solution {
    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int n = s.length();
        Stack<Integer> cnts = new Stack<Integer>();
        Stack<StringBuilder> strs = new Stack<StringBuilder>();
        // strs buttom/last element is final res
        strs.push(res);

        for (int i = 0; i < n; ++i) {
            char ch = s.charAt(i);

            if (ch == '[') {
                strs.push(new StringBuilder());
            } else if (Character.isDigit(ch)) {
                // convert cnt number and push to cnts
                int start = i;
                while (Character.isDigit(s.charAt(i + 1)) && i + 1 < n) {
                    i++;
                }
                cnts.push(Integer.parseInt(s.substring(start, i + 1)));
            } else if (ch == ']') {
                // cur strs top is decoded str
                int cnt = cnts.pop();
                StringBuilder str = strs.pop();
                // build repeated str and push to top
                for (int j = 0; j < cnt; ++j) {
                    strs.peek().append(str);
                }
            } else {
                // direct add single char
                strs.peek().append(ch);
            }
        }

        return res.toString();
    }
}

/*
recursive approach:
for "123[a[bc]]":
1. parse cnt number
2. recur solve [a[bc]] to str, then add cnt times into res
*/
class Solution {
    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            // get all digits
            if (Character.isDigit(c)) {
                // mark start index of digits
                int start = i++;
                // digits followed by str => s[i] = '[' when loop break
                while (i < n && Character.isDigit(s.charAt(i))) {
                    i++;
                }
                // convert str to cnt
                int cnt = Integer.parseInt(s.substring(start, i));
                // record number of '['
                int left = 1;
                // mark start index of repeated str
                start = ++i;
                // find outer "[ ]" of substring
                // then recur solve and add
                while (i < n) {
                    if (s.charAt(i) == '[') {
                        left++;
                    } else if (s.charAt(i) == ']') {
                        left--;

                        if (left == 0) {
                            break;
                        }
                    }

                    i++;
                }
                // get repeated str
                String str = decodeString(s.substring(start, i));
                // repeat add
                for (int j = 0; j < cnt; ++j) {
                    res.append(str);
                }
            } else {
                // directly add char to res
                res.append(c);
            }
        }

        return res.toString();
    }
}

/*
DFS approach
*/
public class Solution {
    public String decodeString(String s) {
        return decodeString(s, new int[] { 0 });
    }

    private String decodeString(String s, int[] idx) {
        StringBuilder result = new StringBuilder();

        while (idx[0] < s.length()) {
            char ch = s.charAt(idx[0]);

            if (Character.isDigit(ch)) {
                int start = idx[0]++;

                while (idx[0] < s.length() && Character.isDigit(s.charAt(idx[0]))) {
                    idx[0]++;
                }

                int count = Integer.parseInt(s.substring(start, idx[0]));

                idx[0]++;
                String str = decodeString(s, idx);

                for (int i = 0; i < count; i++) {
                    result.append(str);
                }
            } else if (ch == ']') {
                return result.toString();
            } else {
                result.append(ch);
            }

            idx[0]++;
        }

        return result.toString();
    }
}
