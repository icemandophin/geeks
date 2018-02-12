/*
col relationship of nodes:
col[root.left] = col[root] - 1
col[root.right] = col[root] + 1

=> level order BFS
enqueue col val and node val
build map of col - node
*/
public class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        // queue for BFS node val
        Queue<TreeNode> queue = new LinkedList<>();
        // queue for col index (define root as 0)
        Queue<Integer> cols = new LinkedList<>();
        queue.offer(root);
        cols.offer(0);
        // record start/end index of col
        int min = 0;
        int max = 0;

        while (!queue.isEmpty()) {
            TreeNode peek = queue.poll();
            int col = cols.poll();
            // update min/max index value
            min = Math.min(min, col);
            max = Math.max(max, col);
            // add col index to map
            if (!map.containsKey(col)) {
                map.put(col, new ArrayList<>());
            }
            map.get(col).add(peek.val);
            // BFS traverse
            if (peek.left != null) {
                queue.offer(peek.left);
                cols.offer(col - 1);
            }

            if (peek.right != null) {
                queue.offer(peek.right);
                cols.offer(col + 1);
            }
        }
        // output node val per col index
        for (int i = min; i <= max; i++) {
            result.add(map.get(i));
        }

        return result;
    }
}
