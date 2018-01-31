// route compression: father[x] = find(father[x]) => x become root
// parent's direct child => 1st find O(n), then O(1)
public class UnionFind {
    private int[] father = null;
    public int find(int x) {
        if (father[x] == x) {
            return x;
        }
        return father[x] = find(father[x]);
    }

    public void union (int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            father[rootA] = rootB;
        }
    }
}
