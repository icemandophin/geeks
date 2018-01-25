/*
start function f1 can be ended by another start of f2
=> use stack to handle cur/prev func
output is sorted by id
=> store exec time in array
*/
class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        // store exec time
        int[] res = new int[n];
        // manage cur running func id
        Stack<Integer> sk = new Stack<>();
        // record last func start time
        int pre = 0;

        for (String log : logs) {
            String[] str = log.split(":");
            // get fid
            int id = Integer.parseInt(str[0]);
            // get cur time
            int cur = Integer.parseInt(str[2]);
            // get log type
            String op = str[1];

            if (op.equals("start")) {
                if (!sk.empty()) {
                    // end running func and update its exec time
                    // interrupted by new func
                    // at start time spot it is new func that runnign
                    res[sk.peek()] += cur - pre;
                }
                // start new func and update pre
                pre = cur;
                sk.push(id);
            } else {
                // end cur running func
                // not interrupted by anther func
                // hence +1s
                res[id] += cur - pre + 1;
                pre = cur + 1;
                sk.pop();
            }
        }

        return res;
    }
}
