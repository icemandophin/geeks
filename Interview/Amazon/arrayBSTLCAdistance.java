package Amazon;
import java.util.*;

public class arrayBSTLCAdistance {
    public static class TreeNode {
    	int val;
    	TreeNode left;
    	TreeNode right;
    	TreeNode(int x) {
    		val = x;
    	}
    }
	
	
    public static void main(String[] args){
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
    public static void createbst(TreeNode root, int val){
    	if (val < root.val){
    		if (root.left == null){
    			root.left = new TreeNode(val);
    		}else {
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
