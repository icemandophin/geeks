/*
split input by "/"
go to prev layer when ".." => use stack to push/pop
*/
class Solution {
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) {
            return "/";
        }

        StringBuilder res = new StringBuilder();
        Stack<String> stk = new Stack<>();
        String[] in = path.split("/");

        for (String a : in) {
            if (a.equals(".") || a.equals("")) {
                // skip item that does not make difference
                continue;
            } else if (a.equals("..")) {
                if (!stk.isEmpty()) {
                    // roll back to prev layer
                    stk.pop();
                }
            } else {
                // insert new item / go to next layer
                stk.push(a);
            }
        }
        // build final res
        for (String b : stk) {
            res.append("/").append(b);
        }
        // need to check res valid at the end for "/" scenario
        if (res.length() == 0) {
            return "/";
        } else {
            return res.toString();
        }
    }
}
