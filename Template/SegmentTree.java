// Definition of Segment Tree Node:
public class SegmentTreeNode {
    public int start, end;
    public Node left, right;
    // max value within this segment
    public int max;
    public SegmentTreeNode(int start, int end) {
        this.start = start;
        this.end = end;
        this.left = this.right = null;
        this.max = Integer.MAX_VALUE;
    }
}

// segment tree supports build, segment search
public class SegmentTree {
    /*
    * @param start: start value.
    * @param end: end value.
    * @return: The root of Segment Tree.
    */
    // build segment tree
    public SegmentTreeNode build(int start, int end) {
        // write your code here
        if(start > end) {  // check core case
            return null;
        }

        root = new SegmentTreeNode(start, end);

        if(start != end) {
            int mid = (start + end) / 2;
            root.left = build(start, mid);
            root.right = build(mid+1, end);
            root.max = Math.max(root.left.max, root.right.max);
        }
        return root;
    }

    // query for max value within given segment
    /*
    3 general scenarios for [a, b] and [start, end] of root:
             1: a-------b
                         2: a-------b
                                        3: a-------b
        |_______________________|_______________________|
       start                   mid                     end
    */
    public int query(SegmentTreeNode root, int a, int b) {
        // handle invalid
        if(a > b || root == null) {
            return Integer.MIN_VALUE;
        }
        // query entire segment
        if (a == root.start && b == root.end) {
            return root.max;
        }
        // binary partition and search left/right segment
        int mid = root.start + (root.end - root.start) / 2;
        // find max in left and right segment
        int lm = Integer.MIN_VALUE;
        int rm = Integer.MIN_VALUE;
        if (a <= mid) {
            if (mid < b) {
                // type 2 - further partition
                lm = query(root.left, a, mid);
                // if left is [a, mid], right must start from mid+1
                rm = query(root.right, mid+1, b);

            }
            else {
                // type 1 - search within left node
                lm = query(root.left, a, b);
            }
        }
        else {
            // type 3 - search within right node
            rm = query(root.right, a, b);
        }
        return Math.max(lm, rm);
    }

    /*
     * @param root: The root of segment tree.
     * @param index: index.
     * @param value: value
     * @return: N/A
     */
    // change leaf node [index, index] value to the new given val
    // ensure the tree's max value is also updated
    public void modify(SegmentTreeNode root, int index, int value) {
        // find target leaf ndoe
        if (root.start == index && root.end == index) {
            root.max = value;
            return;
        }
        int mid = root.start + (root.end - root.start) / 2;
        if (root.start <= index && index <= mid) {
            // index is in left segment
            modify(root.left, index, value);
        }
        if (mid < index && index <= root.end) {
            // index is in right segment
            modify(root.right, index, value);
        }
        // update max of root from left and right node
        root.max = Math.max(root.left.max, root.right.max);
    }
}
