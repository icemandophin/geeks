class TreeNode {
char val;
TreeNode left;
TreeNode right;
TreeNode(char x){
val = x;
}
}


public class Solution {
public TreeNode convert(String s) {
Map<Integer, TreeNode> map = new HashMap<Integer, TreeNode>();
for(int i = 0; i < s.length(); i += 2) {
map.put(i, new TreeNode(s.charAt(i)));
}
int i = 1, N = s.length();
Stack<TreeNode> stack = new Stack<TreeNode>();
while(i < N) {
if(i < N && s.charAt(i) == '?') {
TreeNode p = getNode(map, i - 1);
if(i - 2 > -1 && !stack.isEmpty() && s.charAt(i - 2) != ':') {
stack.peek().left = p;
}
stack.push(p);
i += 2;
}
else {
TreeNode top = stack.pop();
TreeNode right = getNode(map, i + 1);
top.right = right;
if(i - 2 > -1 && s.charAt(i - 2) != ':') {
TreeNode left = getNode(map, i - 1);
top.left = left;
}
i += 2;
}
}
return map.get(0);
}

private TreeNode getNode(Map<Integer, TreeNode> map, int k) {
// TODO Auto-generated method stub
return map.get(k);
}

public static void main(String[] args) {[br]	// TODO Auto-generated method stub
Solution s = new Solution();
s.convert("a?b?d?f:g?h:i:e:c?j?k:l:m");
}

}
