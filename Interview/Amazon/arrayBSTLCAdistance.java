package Amazon;
import java.util.*;

public class Solution {
    public static class TreeNode {
    	int val;
    	TreeNode left;
    	TreeNode right;
    	TreeNode(int x) {
    		val = x;
    	}
    }
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
    	int[] input = {5, 6, 3, 1, 2, 4};
    	System.out.println(bstDist(input, 6, 2, 6));
	}
    // main method to build BST and get dist
    public static int bstDist(int[] a,int n , int p, int q) {
    	if (a == null || a.length == 0){
    		return 0;
    	}
    	int res = 0;
    	// sort array for BST build
    	Arrays.sort(a);
    	// build BST
    	TreeNode root = sortedArrayToBST(a);
    	// check if p, q exist in BST
    	if (bstSearch(root, p) && bstSearch(root, q)) {
        	// find LCA of p and q
        	TreeNode lca = bstLca(root, p, q);
        	// find length between LCA - p and LCA - q
        	res += findDist(lca, p);
        	res += findDist(lca, q);
    	} else {
    		res = -1;
    	}
    	return res;
    }
	// build BST with sorted array
    public static TreeNode sortedArrayToBST(int[] nums) {
        return recurBuild(nums, 0, nums.length - 1);
    }
    static TreeNode recurBuild(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = recurBuild(nums, start, mid - 1);
        root.right = recurBuild(nums, mid + 1, end);
        return root;
    }
    public static boolean bstSearch(TreeNode root, int x) {
    	boolean res = false;
    	while (root != null) {
    		if (root.val == x) {
    			return true;
    		} else if (root.val > x) {
    			root = root.left;
    		} else {
    			root = root.right;
    		}
    	}
    	return res;
    }
    // find LCA in BST
    public static TreeNode bstLca(TreeNode root, int p, int q) {
        if (root == null) {
            return null;
        }
        while (true) {
            if (root.val > Math.max(p, q)) {
                // p, q both in left child
                root = root.left;
            } else if (root.val < Math.min(p, q)) {
                // both in right child
                root = root.right;
            }
            else {
                // cur root is LCA
                break;
            }
        }
        return root;
    }
    // calculate dist between root and target
    public static int findDist(TreeNode root, int x) {
    	if (root == null) {
    		return 0;
    	}
    	int res = 0;
    	while (root != null) {
    		if (root.val == x) {
    			break;
    		} else if (root.val > x) {
    			root = root.left;
    			res += 1;
    		} else {
    			root = root.right;
    			res += 1;
    		}
    	}
    	return res;
    }
    /************************************************************************/
    // build BST with insertion
    public TreeNode buildBST(int[] a) {
        TreeNode root = new TreeNode(a[0]);
        for (int i = 1; i<a.length; ++i) {
            createbst(root, a[i]);
        }
    }
    public static void createbst(TreeNode root, int val) {
        if (val < root.val) {
            if (root.left == null) {
                root.left = new TreeNode(val);
            } else {
                createbst(root.left,val);
            }
        }else {
            if (root.right == null){
                root.right = new TreeNode(val);
            }else{
                createbst(root.right,val);
            }
        }
    }
	// find LCA in binary tree
    public TreeNode binaryTreeLCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = binaryTreeLCA(root.left, p, q);
        TreeNode right = binaryTreeLCA(root.right, p, q);
        if (left == null && right == null) {
            // not found
            return null;
        } else if (left == null) {
            // both on right side, and right is LCA
            return right;
        } else if (right == null) {
            return left;
        } else {
            // one of left the other on right
            return root;
        }
    }
}

/******************************************************************************
Original Solution
******************************************************************************/
public class arrayBSTLCAdistance {
    public static class TreeNode {
    	int val;
    	TreeNode left;
    	TreeNode right;
    	TreeNode(int x) {
    		val = x;
    	}
    }

    public static void main(String[] args) {
    	Scanner in = new Scanner(System.in);
    	int[] input = {5,6,3,1,2,4};
    	System.out.println(bstdistance(input,6,2,6));
    }

    public static TreeNode LCA(TreeNode root, TreeNode node1,TreeNode node2){
    	if (root == null|| node1 == root || node2 == root){
    		return root;
    	}
    	if (node1.val < root.val && root.val < node2.val){
    		return root;
    	}else if (node1.val < root.val && node2.val < root.val){
    		return LCA(root.left,node1,node2);
    	}else if (node1.val > root.val && node2.val > root.val){
    		return LCA(root.right,node1,node2);
    	}else {
    		return null;
    	}
    }
    public static int findlen(TreeNode root,int in){
    	return finddistance(root,in)-1;
    }
    public static int finddistance(TreeNode root,int in){
    	if (root == null){
    		return 0;
    	}
    	int dis = 0;
    	if (root.val == in){
    		return dis + 1;
    	}else if (root.val < in ){
    		dis = finddistance(root.right,in);
    	}else {
    		dis = finddistance(root.left, in);
    	}
    	if (dis > 0){
    		return dis + 1;
    	}else{
    		return 0;
    	}
    }
    public static void createbst(TreeNode root, int val) {
    	if (val < root.val) {
    		if (root.left == null) {
    			root.left = new TreeNode(val);
    		} else {
    			createbst(root.left,val);
    		}
    	}else {
    		if (root.right == null){
    			root.right = new TreeNode(val);
    		}else{
    			createbst(root.right,val);
    		}
    	}
    }
    public static int bstdistance(int[] vals,int n , int node1, int node2){
    	if (vals == null || vals.length == 0){
    		return 0;
    	}
    	TreeNode root = new TreeNode(vals[0]);
    	for (int i =1 ;i<vals.length;i++){
    		createbst(root,vals[i]);
    	}
    	int len1 = findlen(root,node1);
    	int len2 = findlen(root,node2);
    	if (len1 == -1 || len2 == -1){
    		return -1;
    	}
    	int lowca = LCAval(root,node1,node2).val;
    	int mid = findlen(root,lowca);
    	return len1+len2-2*mid;

    }
    public static TreeNode LCAval (TreeNode root,int val1,int val2){
    	if (root == null || val1 == root.val || val2 == root.val){
    		return root;
    	}
    	if ((root.val > val1 && root.val < val2)|| (root.val == val1 || root.val == val2)){
    		return root;
    	}else if (root.val > val1 && root.val > val2){
    		return LCAval(root.left,val1,val2);
    	}else if (root.val < val1 && root.val < val2){
    		return LCAval(root.right,val1,val2);
    	}else {
    		return null;
    	}
    }
}
