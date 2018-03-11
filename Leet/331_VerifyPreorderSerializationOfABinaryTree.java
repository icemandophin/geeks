/*
In/Out degree approach: consider null as leaves, then
1. each non-null node provides 2 outdegree and 1 indegree (2 children and 1 parent), except root (pure outdegree)
2. each null node provides 0 outdegree and 1 indegree (0 child and 1 parent).
3. record diff = outdegree - indegree
valid check: diff >= 0 during traverse, and diff == 0 when finish

ref: https://www.hrwhisper.me/leetcode-verify-preorder-serialization-of-a-binary-tree/
*/
class Solution {
    public boolean isValidSerialization(String preorder) {
        // record diff of degree: indegree -1, outdegree +1
        // init as 1, since root node does NOT bring indegree
        int deg = 1;
        String[] nodes = preorder.split(",");

        for (String node : nodes) {
            // each node always bring 1 indegree
            --deg;
            // check if still valid
            if (deg < 0) {
                return false;
            }

            if (!node.equals("#")) {
                // non-null node => add outdegrees
                deg += 2;
            }
        }

        return deg == 0;
    }
}
