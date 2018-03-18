TreeNode {
int val；
TreeNode left，right， parent；
TreeNode(int x) {
val = x;
}
}
这个复杂度是O(lgN), 最差情况就是全部树是一条直线，复杂度O(N)
public class Solution {
public TreeNode nextLargest(TreeNode t) {
TreeNode temp = t; //没有最大值返回本身
if(t.right != null) return getLeftMost(t.right);
while(t != null && t.parent != null) {
TreeNode parent = t.parent;
if(parent.left == t) return parent;
t = t.parent;
}
return temp;
}

private TreeNode getLeftMost(TreeNode root) {
// TODO Auto-generated method stub
TreeNode p = root;
while(p.left != null) {
p = p.left;
}
return p;
}
}

TreeNode没有parent pointer但是给了root

public class Solution {
public TreeNode nextLargest(TreeNode root, TreeNode t) {
TreeNode temp = t;
if(t != null && t.right != null) return getLeftMost(t.right);
List<TreeNode> list = getAncenssor(root, t);
int i = 2;
while (list.size() - i > -1) {
TreeNode parent = list.get(list.size() - i);
if(parent.left == t) return parent;
t = parent;
i++;
}
return temp;
}
这个用了回溯来求根节点到t点的路径
private List<TreeNode> getAncenssor(TreeNode root, TreeNode t) {
// TODO Auto-generated method stub
List<TreeNode> res = new ArrayList<TreeNode>();
helper(root, t, new ArrayList<TreeNode>(), res);
return res;
}

private void helper(TreeNode root, TreeNode t, List<TreeNode> list, List<TreeNode> res) {
// TODO Auto-generated method stub
if(root == null) return;
list.add(root);
if(root == t) {
res.addAll(new ArrayList<TreeNode>(list));
return;
}
helper(root.left, t, list, res);
helper(root.right, t, list, res);
list.remove(list.size() - 1);
}

private TreeNode getLeftMost(TreeNode root) {
// TODO Auto-generated method stub
TreeNode p = root;
while(p.left != null) {
p = p.left;
}
return p;
}
}
