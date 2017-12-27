public class Solution {
        TreeNode root;
        public TreeNode build(int s, int e) {
        if (s > e) {
            return null;
        }

        TreeNode root = new TreeNode(s, e, 0);

        if (s != e) {
            int m = s + (e - s) / 2;
            root.left = build(s, m);
            root.right = build(m + 1, e);
        } else {
            // got leaf node
            root.count = 0;
        }

        return root;
    }

    // query count in segment [a, b]
    public int query(TreeNode root, int s, int e) {
        if (root.start == s && root.end == e ) {
            return root.count;
        }

        int m = root.start + (root.end - root.start) / 2;
        // count in left/right child segment
        int lc = 0, rc = 0;
        if (s <= m) {
            if (m < e) {
                // break [s, e] to [s, m] & [m+1, e]
                lc = query(root.left, s, m);
            } else {
                // search in left child
                lc = query(root.left, s, e);
            }
        }

        if (m < e) {
            if (s <= m) {
                // break
                rc = query(root.right, m + 1, e);
            } else {
                // search in right child
                rc = query(root.right, s, e);
            }
        }

        // merge to final result
        return lc + rc;
    }

    // add value x to segment array value a[i]
    public void update(TreeNode root, int i, int x) {
        if (root.start == i && root.end == i) {
            // found target leaf node
            root.count += x;
            return;
        }

        int mid = root.start + (root.end - root.start) / 2;
        if (root.start <= i && i <= mid) {
            // update in left side
            update(root.left, i, x);
        }

        if (root.end >= i && i > mid) {
            // update in right
            update(root.right, i, x);
        }

        // update root count since one if its child segment got updated
        root.count = root.left.count + root.right.count;
    }

    public List<Integer> countOfSmallerNumberII(int[] a) {
        TreeNode root = build(0, 10000);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < a.length; ++i) {
            int cur = 0;
            if (a[i] > 0) {
                // get # of prev a[i] that smaller than a[i] so far
                // equal to count value in segment [0, a[i] - 1]
                cur = query(root, 0, a[i] - 1);
            }
            // add one count for leaf node [a[i], a[i]]
            update(root, a[i], 1);

            res.add(cur);
        }

        return res;
    }
}

// implement segment node, each node represents one segment
class TreeNode {
    // segment boundary
    public int start, end;
    // sum of targets in segment so far
    public int count;
    // child segments on left/right
    public TreeNode left, right;
    public TreeNode(int s, int e, int cnt) {
        start = s;
        end = e;
        count = cnt;
        left = right = null;
    }
}
